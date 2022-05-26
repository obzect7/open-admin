package com.cxp.openadmin.domain.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "sys_role_menu_permission")
public class SysRoleMenuPermission {
    /**
     * 기본 키
     */
    @Id
    private Integer id;

    /**
     * role id
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 메뉴 아이디
     */
    @Column(name = "menu_id")
    private Integer menuId;

    /**
     * 작동하다
     */
    private String operation;

    /**
     * 생성 시간
     */
    @Column(name = "created_time")
    private Date createdTime;
}