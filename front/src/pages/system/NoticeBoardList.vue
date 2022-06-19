<template>
  <a-spin :spinning="loading" size="large">
    <div :bordered="false" v-show="!isPopUp">
      <div >
        <a-form layout="horizontal" >
          <div name="filter" style="background: white; padding: 10px;">
            <a-row >
              <a-col :md="2" :sm="10" >
                  <a-select v-model="queryParam.use_yn" placeholder="선택">
                    <a-select-option value="E">전체</a-select-option>
                    <a-select-option value="T">제목</a-select-option>
                    <a-select-option value="C">내용</a-select-option>
                  </a-select>
              </a-col>
              <a-col :md="5" :sm="10" style="padding-left: 5px">
                <a-input v-model="queryParam.item_nm" placeholder="입력하세요." />
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
              <a-button type="primary"> <a-icon type="plus-square" />글쓰기</a-button>
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
import {getItemList} from "@/services/item";
import PopItem from "@/pages/master/PopItem";
const useYnList= []

export default {
  name: "NoticeBoardList",
  components : {
    PopItem,
    AUIGrid
  },
  data: function () {
    return {
      loading: false,     //로딩바 유무
      delayTime: 1000,    //로딩 딜레이
      isPopUp : false,    //팝업호출여부
      // 쿼리 매개변수
      queryParam: {useYn : "Y"},
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
      // 그리드 데이터
      gridData : []
    }
  },
  beforeMount() {
    this.useYnList = [ {"code":"Y", "value":"사용"}, {"code":"N", "value":"미사용"}]
  },
  mounted(){
    // 그리드 칼럼 레이아웃 정의
    this.columnLayout = [
      {dataField : "plant_cd",    headerText : "사업장",   width : 120, visible : false},
      {dataField : "owner_cd",    headerText : "화주코드",  width : 140, visible : false},

      {dataField : "post_no",     headerText : "등록번호",   width : 100, visible : true },
      {dataField : "post_tit",    headerText : "제목",      width : 250, visible : true, style: "left-text "},
      {dataField : "post_cout",   headerText : "조회수",    width : 100, visible : true },
      {dataField : "attifile_cnt",headerText : "첨부문서",    width : 100, visible : true },
      {dataField : "noti_yn",     headerText : "표시여부",    width : 100, visible : true },
      {dataField : "reg_id",      headerText : "등록자",    width : 100, editable : false},
      {dataField : "reg_dt",      headerText : "등록일자",  width : 120, editable : false},
      {dataField : "mod_id",      headerText : "수정자",   width : 100, editable : false},
      {dataField : "mod_dt",      headerText : "수정일자",  width : 120, editable : false}
    ]
    let grid = this.$refs.itemGrid;
    // 그리드 생성
    grid.create(this.columnLayout, this.auigridProps);
    // 그리드 데이터 삽입하기
    grid.setGridData(this.gridData);
    //그리드 사이즈 재조절
    grid.resize();
  },
  methods : {
    search(){
      console.log('조회를 시작합니다.',this.queryParam);
      this.loading = true
      return getItemList(Object.assign(this.queryParam)).then(

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
      //this.popinit = event.item;
      //console.log("popinit===", this.popinit)
      let POpParam = event.item;
      Object.assign(POpParam, {['isNew']: false})
      this.openPopItem(POpParam)
      this.isPopUp = true
    },
    openPopItem(param){
      // this.popinit = {};
      this.popinit = param;
      this.isPopUp = true
    },
    closePopItem(){
      //console.log('sssss')
      this.isPopUp = false
      this.search()
      //this.$router.go()
    },
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