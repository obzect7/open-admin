package com.cxp.openadmin.domain.entity;

import java.util.Date;
import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@ToString
@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * Id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String openid;

    private String unionid;

    private String nickname;

    private String mobile;

    private String gender;

    /**
     * 아바타 주소
     */
    @Column(name = "avatar_url")
    private String avatarUrl;

    /**
     * 상태, 1 활성화, 2 비활성화
     */
    private Integer status;

    /**
     * 생성 시간
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 시간 변경
     */
    @Column(name = "updated_time")
    private Date updatedTime;
}