<template>
  <div :bordered="false" :style="{ minHeight: '800px' }">
    <div >
      <a-form layout="horizontal" >
        <div >
          <a-row >
            <a-col :md="7" :sm="24" >
              <a-form-item
                  label="품번"
                  :labelCol="{span: 5}"
                  :wrapperCol="{span: 18, offset: 1}"
              >
                <a-input v-model="queryParam.item_cd" placeholder="입력하세요." />
              </a-form-item>
            </a-col>
            <a-col :md="7" :sm="24" >
              <a-form-item
                  label="품명"
                  :labelCol="{span: 5}"
                  :wrapperCol="{span: 18, offset: 1}"
              >
                <a-input v-model="queryParam.item_nm" placeholder="입력하세요." />
              </a-form-item>
            </a-col>
            <a-col :md="7" :sm="24" >
              <a-form-item
                  label="사용여부"
                  :labelCol="{span: 5}"
                  :wrapperCol="{span: 18, offset: 1}"
              >
                <a-select v-model="queryParam.use_yn" placeholder="선택하세요.">
                  <a-select-option value="Y">사용</a-select-option>
                  <a-select-option value="N">미사용</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="3" :sm="24" >
              <a-button  @click="search" style="margin-left: 4px;margin-bottom: 4px; background-color: #A50000; color: white">조회</a-button>
              <a-button style="margin-left: 8px">초기화</a-button>
            </a-col>
          </a-row>
        </div>
      </a-form>
    </div>

    <div>
      <a-row>

        <a-col :md="24" :sm="24">
          <AUIGrid ref="itemGrid" class="grid-wrap" />
        </a-col>
      </a-row>
    </div>


  </div>

</template>



<script>



// AUIGrid 컴포넌트

import AUIGrid from "@/components/auigrid/import/AUIGrid-Vue.js/AUIGrid";
import {getItemList} from "@/services/item";
const useYnList= []

export default {

  components : {
    AUIGrid
  },
  data: function () {
    return {
      useYnList,

      // 쿼리 매개변수
      queryParam: {useYn : "Y"},

      columnLayout : [],

      // 그리드 속성 정의
      auigridProps : {
        // 편집 가능 여부 (기본값 : false)
        editable : true,

        // 엑스트라 체크박스 표시 설정
        showRowCheckColumn : true,

        // 전체 체크박스 표시 설정
        showRowAllCheckBox : true,

        // 셀 선택모드 (기본값: singleCell)
        selectionMode : "multipleCells",
        showStateColumn : true,
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

      {dataField : "item_cd",   headerText : "품번",      width : 120, visible : true },
      {dataField : "item_nm",   headerText : "품명",      width : 120, visible : true, style: "left-text "},
      {dataField : "spec",      headerText : "규격",      width : 120, visible : true, style: "left-text "},
      {dataField : "unit",      headerText : "단위",      width : 120, visible : true },
      {dataField : "class1",    headerText : "구분1",     width : 120, visible : true },
      {dataField : "class2",    headerText : "구분2",     width : 120, visible : true },
      {dataField : "class3",    headerText : "구분3",      width : 120, visible : true},
      {dataField : "boxqty",    headerText : "Box/Qty",   width : 120, visible : true, style: "right-text "},
      {dataField : "pltqty",    headerText : "Plt/Qty",   width : 120, visible : true, style: "right-text "},
      {dataField : "in_price1", headerText : "입고단가",    width : 120, visible : true, style: "right-text "},
      {dataField : "out_price", headerText : "출고단가",    width : 120, visible : true, style: "right-text "},
      {dataField : "lot_yn",    headerText : "LOT관리여부", width : 120, visible : true },
      {dataField : "fifo_yn",   headerText : "선입선출여부", width : 120, visible : true },
      {dataField : "appro_inv", headerText : "적정재고",    width : 120, visible : true, style: "right-text "},
      {dataField : "in_wh_cd",  headerText : "입고창고",    width : 120, visible : true },
      {dataField : "in_lc_cd",  headerText : "입고로케이션", width : 120, visible : true },
      {dataField : "remark",    headerText : "비고",        width : 140, style: "left-text "},
      {dataField : "use_yn",    headerText : "사용여부",    width : 140,
        renderer : {
          type : "DropDownListRenderer",
          list : this.useYnList, //key-value Object 로 구성된 리스트
          keyField : "code", // key 에 해당되는 필드명
          valueField : "value" // value 에 해당되는 필드명
        }
      },
      {dataField : "reg_id",      headerText : "등록자",    width : 140, editable : false},
      {dataField : "reg_dt",      headerText : "등록일자",  width : 140, editable : false},
      {dataField : "mod_id",      headerText : "수정자",   width : 140, editable : false},
      {dataField : "mod_dt",      headerText : "수정일자",  width : 140, editable : false}
    ]


    let grid = this.$refs.itemGrid;

    // 그리드 생성

    grid.create(this.columnLayout, this.auigridProps);


    // 그리드 데이터 삽입하기
    grid.setGridData(this.gridData);
  },
  methods : {
    search(){
      console.log('조회를 시작합니다.',this.queryParam);
      return getItemList(Object.assign(this.queryParam)).then(

          (res) => {
            console.log('res====',res)
            this.$refs.itemGrid.setGridData(res.data);
            //return res.data;
          }
      )
    }
  }
}

</script>

<style lang="less" scoped>
.search{
  margin-bottom: 54px;
}
.fold{
  width: calc(100% - 216px);
  display: inline-block
}
.operator{
  margin-bottom: 18px;
}
@media screen and (max-width: 900px) {
  .fold {
    width: 100%;
  }
}
</style>

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