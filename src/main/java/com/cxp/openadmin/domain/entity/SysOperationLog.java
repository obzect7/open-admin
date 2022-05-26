package com.cxp.openadmin.domain.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "sys_operation_log")
public class SysOperationLog {
    /**
     * 기본 키 ID
     */
    @Id
    private Integer id;

    /**
     * 운영자 ID
     */
    @Column(name = "operation_id")
    private Integer operationId;

    /**
     * 작업 유형
     */
    private String url;

    /**
     * 작동 방법
     */
    private String method;

    /**
     * 운영 내용
     */
    private String params;

    /**
     * 브라우저 유형
     */
    private String browser;

    /**
     * 작업 IP
     */
    private String ip;

    /**
     * 실행 시간
     */
    @Column(name = "take_time")
    private Integer takeTime;

    /**
     * 생성 시간
     */
    @Column(name = "created_time")
    private Date createdTime;
}