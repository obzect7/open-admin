package com.cxp.openadmin.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@ToString
@Table(name = "cm_board")
public class Board {
    /**
     * 기본 키
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 게시판 ID
     */
    private String board_id;

    /**
     * 게시물 번호
     */
    private String post_no;

    /**
     * 게시물 그룹 번호
     */
    private String post_grp_no;

    /**
     * 게시물 그룹 일련번호
     */
    private String post_grp_sn;

    /**
     * 게시물 Level
     */
    private String post_lvl;

    /**
     * 카테고리 ID
     */
    private String cate_id;

    /**
     * 게시물 제목
     */
    private String post_tit;

    /**
     * 게시물 내용
     */
    private String post_cont;

    /**
     * 게시물 시작일
     */
    private String post_sd;

    /**
     * 게시물 종료일
     */
    private String post_ed;

    /**
     * 등록자 명
     */
    private String reg_nm;

    /**
     * 등록자 이메일
     */
    private String reg_email;

    /**
     * 수정자 명
     */
    private String mod_nm;

    /**
     * 수정자 이메일
     */
    private String mod_email;

    /**
     * 조회수
     */
    private String view_cnt;

    /**
     * 첨부수
     */
    private String attfile_cnt;

    /**
     * 댓글수
     */
    private String comnt_cnt;

    /**
     * 공지사항 여부
     */
    private String noti_yn;

    /**
     * 메인 공지사항 여부
     */
    private String main_noti_yn;

    /**
     * 첨부그룹번호
     */
    private String file_grp_seq;

    /**
     * 삭제 여부
     */
    private String del_yn;

    /**
     * 비밀글 여부
     */
    private String secrt_yn;

    /**
     * 등록자
     */
    private String reg_id;

    /**
     * 등록일시
     */
    private Date reg_dt;

    /**
     * 수정자
     */
    private String mod_id;

    /**
     * 수정일시
     */
    private Date mod_dt;

}