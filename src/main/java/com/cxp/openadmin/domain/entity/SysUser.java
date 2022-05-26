package com.cxp.openadmin.domain.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "sys_user")
public class SysUser {
    /**
     * 기본 키
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 사용자 이름
     */
    private String username;

    /**
     * 비밀번호
     */
    private String password;

    /**
     * 이름
     */
    private String name;

    /**
     * 화신
     */
    private String avatar;

    /**
     * 캐릭터 아이디
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 상태, 1 활성화, 2 비활성화
     */
    private Integer status;

    /**
     * 생성 시간
     */
    @Column(name = "created_time")
    private Date createdTime;
}