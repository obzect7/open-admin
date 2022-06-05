package com.cxp.openadmin.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@ToString
@Table(name = "cm_code_grp")
public class CmCodeGrp {
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