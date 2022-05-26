package com.cxp.openadmin.domain.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "sys_menu")
public class SysMenu {
    /**
     * 기본 키
     */
    @Id
    private Integer id;

    /**
     * 길
     */
    private String path;

    /**
     * 메뉴 숨김 여부, 0: 숨김 없음, 1: 숨김
     */
    private Integer invisible;

    /**
     * 상위 노드 ID
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 메뉴 이름
     */
    private String name;

    /**
     * 아이콘
     */
    private String icon;

    /**
     * 메뉴 숨김 여부, 0: 캐시하지 않음, 1: 캐시
     */
    @Column(name = "cache_able")
    private Integer cacheAble;

    /**
     * 메뉴 정렬
     */
    private Integer sort;

    /**
     * 구성 요소 이름
     */
    private String component;

    /**
     * 운영 권한
     */
    private String permissions;

    /**
     * 생성 시간
     */
    @Column(name = "created_time")
    private Date createdTime;
}