package com.cxp.openadmin.exception;

import com.cxp.openadmin.configuration.ExceptionCodeConfiguration;
import com.cxp.openadmin.exception.http.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionAdvice {

    final static Logger logger = LoggerFactory.getLogger(GlobalExceptionAdvice.class);

    @Autowired
    private ExceptionCodeConfiguration exceptionCodeConfiguration;

    /**
     * PK 에러 잡아주는 Exception 전용 핸들러 추가 22.07.19 mslee
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value= DuplicateKeyException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse duplicationException(HttpServletRequest request, Exception e){
        ExceptionResponse result = new ExceptionResponse(99998,e.toString(),request.getMethod()+':'+request.getRequestURI());
        logger.error(e.toString());
        return result;
    }

    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleException(HttpServletRequest request, Exception e){
        ExceptionResponse result = new ExceptionResponse(99999,e.toString(),request.getMethod()+':'+request.getRequestURI());
        logger.error(e.toString());
        return result;
    }


    @ExceptionHandler(value= HttpException.class)
    public ResponseEntity<ExceptionResponse> handleHttpException(HttpServletRequest request, HttpException e){
        ExceptionResponse result = new ExceptionResponse(e.getCode(),exceptionCodeConfiguration.getMessageByCode(e.getCode()),request.getMethod()+':'+request.getRequestURI());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //HttpStatus status = HttpStatus.resolve(e.getHttpStatusCode());
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<ExceptionResponse> re = new ResponseEntity<>(result,headers,status);
        return re;
    }

}