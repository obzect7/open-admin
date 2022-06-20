package com.cxp.openadmin.domain.dto.front;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BoardDto {

    private String row_status;      //row 상태
    private String board_id;        //게시판 ID
    private String post_no;         //게시물 번호
    private String post_grp_no;     //게시물 그룹 번호
    private String post_grp_sn;     //게시물 그룹 일련번호
    private String post_lvl;        //게시물 Level
    private String cate_id;         //카테고리 ID
    private String post_tit;        //게시물 제목
    private String post_cont;       //게시물 내용
    private String post_sd;         //게시물 시작일
    private String post_ed;         //게시물 종료일
    private String reg_nm;          //등록자 명
    private String reg_email;       //등록자 이메일
    private String mod_nm;          //수정자 명
    private String mod_email;       //수정자 이메일
    private String view_cnt;        //조회수
    private String attfile_cnt;     //첨부수
    private String comnt_cnt;       //댓글수
    private String noti_yn;         //공지사항 여부
    private String main_noti_yn;    //메인 공지사항 여부
    private String file_grp_seq;    //첨부그룹번호
    private String del_yn;          //삭제 여부
    private String secrt_yn;        //비밀글 여부

    private String reg_id;
    private Timestamp reg_dt;
    private String mod_id;
    private Timestamp mod_dt;
}
