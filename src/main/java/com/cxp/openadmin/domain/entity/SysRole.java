package com.cxp.openadmin.domain.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "sys_role")
public class SysRole {
    /**
     * 기본 키
     */
    @Id
    private Integer id;

    /**
     * 권한 고유 키
     */
    @Column(name = "unique_key")
    private String uniqueKey;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 주목
     */
    private String remark;

    /**
     * 생성 시간
     */
    @Column(name = "created_time")
    private Date createdTime;
}