package com.cxp.openadmin.aspect;

import com.alibaba.fastjson.JSON;
import com.cxp.openadmin.dao.SysOperationLogMapper;
import com.cxp.openadmin.domain.entity.SysOperationLog;
import com.cxp.openadmin.util.IpUtil;
import com.cxp.openadmin.util.JwtOperator;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.UserAgent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;

@Component
@Aspect
@Slf4j
public class SysOperationAspect {

    private List<String> operationUrls = Stream.of("/backed/system/login").collect(toList());

    @Autowired
    private JwtOperator jwtOperator;

    @Autowired
    private SysOperationLogMapper sysOperationLogMapper;

    @Around("execution(* com.cxp.openadmin.service.backed.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("===== 실행 시작 {}.{} =====",joinPoint.getTarget().getClass(),joinPoint.getSignature().getName());

        long begin = System.currentTimeMillis();
        // 대상 메서드 실행
        Object object = joinPoint.proceed();
        long end = System.currentTimeMillis();
        long takeTime = end-begin;

        //요청을 받음
        try {
            HttpServletRequest request = ((ServletRequestAttributes)
                    RequestContextHolder.getRequestAttributes()).getRequest();

            if(!request.getMethod().equals("GET") && !operationUrls.contains(request.getRequestURI())){
                _operationSyslog(request,joinPoint,takeTime);
            }
        }catch (Exception e){
            log.info("==== local call ======");
        }

        return object;
    }

    private void _operationSyslog(HttpServletRequest request, JoinPoint joinPoint, long takeTime){

        // 记录操作日志
        SysOperationLog sysOperationLog = new SysOperationLog();
        sysOperationLog.setUrl(request.getRequestURI());
        sysOperationLog.setMethod(request.getMethod());
        sysOperationLog.setIp(IpUtil.getIpAddress(request));
        sysOperationLog.setParams(JSON.toJSONString(joinPoint.getArgs()));
        sysOperationLog.setTakeTime((int) takeTime);

        String ua = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(ua);
        Browser browser = userAgent.getBrowser();
        sysOperationLog.setBrowser(browser.getName());
        sysOperationLog.setCreatedTime(new Date());

        String token = request.getHeader("Authorization");
        Claims claims = jwtOperator.getClaimsFromToken(token);
        sysOperationLog.setOperationId((Integer) claims.get("id"));

        sysOperationLogMapper.insertSelective(sysOperationLog);
    }

}
