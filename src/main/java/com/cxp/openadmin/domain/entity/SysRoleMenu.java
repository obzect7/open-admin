package com.cxp.openadmin.domain.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "sys_role_menu")
public class SysRoleMenu {
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
     * 메뉴 ID
     */
    @Column(name = "menu_ids")
    private String menuIds;

    /**
     * 생성 시간
     */
    @Column(name = "created_time")
    private Date createdTime;
}