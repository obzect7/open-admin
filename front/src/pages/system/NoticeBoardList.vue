<template>
  <a-spin :spinning="loading" size="large">
    <a-modal
        :title="'게시판 등록'"
        style="top: 5px"
        :width="1100"
        v-model="isPopUp"
        :footer="null"
    >
      <PopBoard v-if="isPopUp" @closepop="closePopBoard" :popinit="this.popinit" />
    </a-modal>
    <div :bordered="false" v-show="!isPopUp">
      <div >
        <a-form layout="horizontal" >
          <div name="filter" style="background: white; padding: 10px;">
            <a-row >
              <a-col :md="2" :sm="10" >
                  <a-select v-model="queryParam.search_type" placeholder="선택" >
                    <a-select-option value="E">전체</a-select-option>
                    <a-select-option value="T" >제목</a-select-option>
                    <a-select-option value="C">내용</a-select-option>
                  </a-select>
              </a-col>
              <a-col :md="5" :sm="10" style="padding-left: 5px">
                <a-input v-model="queryParam.search_comment"
                         placeholder="입력하세요."
                         @keyup.enter="search"/>
              </a-col>
              <span style="float: right; margin-top: 3px;">
                <a-button type="primary" icon="search" @click="search" :loading="loading">조회</a-button>
              </span>
            </a-row>
          </div>
        </a-form>
      </div>

      <div name="grid" style="padding-top: 10px;">
          <div style="background: white; padding: 10px ">
            <a-button-group style="padding-bottom: 10px">
              <a-button type="primary" @click="addBoard"> <a-icon type="plus-square" />글쓰기</a-button>
            </a-button-group>
            <AUIGrid ref="itemGrid" class="grid-wrap"
                     @cellDoubleClick="cellDoubleClickHandler"
                     style="height: 65vh;"
            />
          </div>
      </div>
    </div>
  </a-spin>
</template>

<script>
// AUIGrid 컴포넌트
import AUIGrid from "@/components/auigrid/import/AUIGrid-Vue.js/AUIGrid";
import {getBoardList} from "@/services/board";
import PopBoard from "@/pages/master/PopBoard";

export default {
  name: "NoticeBoardList",
  components : {
    PopBoard,
    AUIGrid
  },
  data: function () {
    return {
      loading: false,     //로딩바 유무
      delayTime: 1000,    //로딩 딜레이
      isPopUp : false,    //팝업호출여부
      // 쿼리 매개변수
      queryParam: { search_type : "E" },
      columnLayout : [],
      // 그리드 속성 정의
      auigridProps : {
        // 편집 가능 여부 (기본값 : false)
        editable : false,
        // 엑스트라 체크박스 표시 설정
        showRowCheckColumn : false,
        // 전체 체크박스 표시 설정
        showRowAllCheckBox : false,
        // 셀 선택모드 (기본값: singleCell)
        selectionMode : "multipleCells",
        showStateColumn : false,
      },
      popinit: {},
      // 그리드 데이터
      gridData : []
    }
  },
  beforeMount() {

  },
  mounted(){
    // 그리드 칼럼 레이아웃 정의
    this.columnLayout = [
      {dataField : "plant_cd",    headerText : "사업장",   width : 120, visible : false},
      {dataField : "owner_cd",    headerText : "화주코드",  width : 140, visible : false},

      {dataField : "post_no",     headerText : "등록번호",   width : 100, visible : true },
      {dataField : "post_tit",    headerText : "제목",      width : 250, visible : true, style: "left-text "},
      {dataField : "view_cnt",    headerText : "조회수",    width : 100, visible : true },
      {dataField : "attfile_cnt", headerText : "첨부문서",    width : 100, visible : true },
      {dataField : "noti_yn",     headerText : "표시여부",    width : 100, visible : true },
      {dataField : "reg_nm",      headerText : "등록자",    width : 100, editable : false},
      {dataField : "reg_dt",      headerText : "등록일자",  width : 120, editable : false},
      {dataField : "mod_nm",      headerText : "수정자",   width : 100, editable : false},
      {dataField : "mod_dt",      headerText : "수정일자",  width : 120, editable : false}
    ]
    let grid = this.$refs.itemGrid;
    // 그리드 생성
    grid.create(this.columnLayout, this.auigridProps);
    // 그리드 데이터 삽입하기
    grid.setGridData(this.gridData);
  },
  methods : {
    search(){
      this.loading = true
      return getBoardList(Object.assign(this.queryParam)).then(
          (res) => {
            console.log('res====',res)
            this.$refs.itemGrid.setGridData(res.data);
            //return res.data;
            setTimeout(() => this.loading = false, 500)
          }
      )
    },
    cellDoubleClickHandler(event){
      console.log("event.item===", event.item)
      let POpParam = event.item;
      Object.assign(POpParam, {['isNew']: false})
      this.openPopBoard(POpParam)
      this.isPopUp = true
    },
    openPopBoard(param){
      this.popinit = param;
      this.isPopUp = true
    },
    closePopBoard(){
      //console.log('sssss')
      this.isPopUp = false
      this.search()
      //this.$router.go()
    },
    addBoard(){
      this.openPopBoard({['isNew']: true});
    }
  }
}
</script>

<style>
.left-text {
  text-align: left;
}
.right-text {
  text-align: right;
}
.showcase2-complete-red {
  color: #ff0000;
}
</style>