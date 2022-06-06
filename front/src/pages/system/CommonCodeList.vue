<template>
  <div :bordered="false" :style="{ minHeight: '800px' }">
    <div >
      <a-form layout="horizontal" >
        <div >
          <a-row >
            <a-col :md="7" :sm="24" >
              <a-form-item
                  label="그룹코드"
                  :labelCol="{span: 5}"
                  :wrapperCol="{span: 18, offset: 1}"
              >
                <a-input v-model="queryParam.groupCd" placeholder="입력하세요." />
              </a-form-item>
            </a-col>
            <a-col :md="7" :sm="24" >
              <a-form-item
                  label="사용여부"
                  :labelCol="{span: 5}"
                  :wrapperCol="{span: 18, offset: 1}"
              >
                <a-select v-model="queryParam.useYn" placeholder="선택하세요.">
                  <a-select-option value="Y">사용</a-select-option>
                  <a-select-option value="N">미사용</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="7" :sm="24" >
              <a-form-item
                  label="그룹코드명"
                  :labelCol="{span: 5}"
                  :wrapperCol="{span: 18, offset: 1}"
              >
                <a-input v-model="queryParam.groupNm" placeholder="입력하세요." />
              </a-form-item>
            </a-col>
            <a-col :md="3" :sm="24" >
              <a-button type="primary" @click="search">조회</a-button>
              <a-button style="margin-left: 8px">초기화</a-button>
            </a-col>
          </a-row>
        </div>
      </a-form>
    </div>

    <div>
      <a-row>

        <a-col :md="9" :sm="24">
          <div>
            <a-button type="primary" @click="masterAddRow">추가</a-button>
            <a-button type="primary">삭제</a-button>
            <a-button type="primary">저장</a-button>
          </div>
          <AUIGrid ref="myGrid1" class="grid-wrap">
          </AUIGrid>
        </a-col>
        <a-col :md="1" :sm="24">
          <a-divider />
        </a-col>
        <a-col :md="14" :sm="24">
          <div>
            <a-button type="primary">추가</a-button>
            <a-button type="primary">삭제</a-button>
            <a-button type="primary">저장</a-button>
          </div>
          <AUIGrid ref="myGrid2" class="grid-wrap">
          </AUIGrid>
        </a-col>
      </a-row>
    </div>


  </div>

</template>



<script>



// AUIGrid 컴포넌트

import AUIGrid from "@/components/auigrid/import/AUIGrid-Vue.js/AUIGrid";
import {getCmCodeGrpList} from "@/services/commoncode";

export default {

  components : {
    AUIGrid
  },
  data: function () {
    return {
      // 쿼리 매개변수
      queryParam: {},
      useYnList:[],
      // 그리드 칼럼 레이아웃 정의
      columnLayoutHD : [{
        dataField : "group_cd",
        headerText : "그룹코드",
        width : 120
      }, {
        dataField : "group_nm",
        headerText : "그룹코드명",
        width : 140
      }, {
        dataField : "use_yn",
        headerText : "사용여부",
        width : 120,
        editRenderer : {
          type : "ComboBoxRenderer",
          list : this.useYnList, //key-value Object 로 구성된 리스트
          keyField : "code", // key 에 해당되는 필드명
          valueField : "value", // value 에 해당되는 필드명
        },
        labelFunction : function(rowIndex, columnIndex, value, item) {
          if(value == "Y")
            return "사용";
          else
            return "미사용";
        },

      }, {
        dataField : "rem",
        headerText : "비고",
        width : 120
      }
      ]
      ,columnLayoutDT : [{
        dataField : "code",
        headerText : "코드ID",
        width : 120
      }, {
        dataField : "code_nm",
        headerText : "코드명",
        width : 140
      }, {
        dataField : "sort",
        headerText : "정렬순서",
        width : 120
      },{
        dataField : "data1",
        headerText : "속성1",
        width : 120
      },{
        dataField : "data2",
        headerText : "정렬순서",
        width : 120
      },{
        dataField : "data3",
        headerText : "정렬순서",
        width : 120
      },{
        dataField : "data4",
        headerText : "정렬순서",
        width : 120
      },{
        dataField : "data5",
        headerText : "정렬순서",
        width : 120
      },{
        dataField : "data5",
        headerText : "정렬순서",
        width : 120
      },{
        dataField : "data6",
        headerText : "정렬순서",
        width : 120
      },
      ],

      // 그리드 속성 정의

      auigridProps : {

        // 엑스트라 체크박스 표시 설정
        showRowCheckColumn : true,

        // 엑스트라 체크박스에 shiftKey + 클릭으로 다중 선택 할지 여부 (기본값 : false)
        enableRowCheckShiftKey : true,

        // 전체 체크박스 표시 설정
        showRowAllCheckBox : true,

        // 편집 가능 여부 (기본값 : false)
        editable : true,
        // 셀 선택모드 (기본값: singleCell)
        selectionMode : "multipleCells"

      },

      // 그리드 데이터
      gridDataHD : [],
      gridDataDT : []

    }

  },
  mounted(){

    let grid1 = this.$refs.myGrid1;
    let grid2 = this.$refs.myGrid2;

    // 그리드 생성

    grid1.create(this.columnLayoutHD, this.auigridProps);
    grid2.create(this.columnLayoutDT, this.auigridProps);

    // 그리드 데이터 삽입하기
    grid1.setGridData(this.gridDataHD);
    grid2.setGridData(this.gridDataDT);

  },created() {
    this.useYnList = [{"code":"", "value":"전체"}, {"code":"Y", "value":"사용"}, {"code":"N", "value":"미사용"}]
  },
  methods : {
    search(){
      console.log('조회를 시작합니다.',this.queryParam);
      return getCmCodeGrpList(Object.assign(this.queryParam)).then(
          (res) => {
            console.log('res====',res)
            this.$refs.myGrid1.setGridData(res.data);
            //return res.data;
          }
      )
    },
    masterAddRow(){
      // 하단에 1행 추가
      console.log('행추가 !!')
      let item = {};
      this.$refs.myGrid1.addRow(item, "last");
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