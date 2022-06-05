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

        <a-col :md="10" :sm="24">
          <div>
            <a-button type="primary">추가</a-button>
            <a-button type="primary">삭제</a-button>
            <a-button type="primary">저장</a-button>
          </div>
          <AUIGrid ref="myGrid1" class="grid-wrap">
          </AUIGrid>
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
      // 그리드 칼럼 레이아웃 정의
      columnLayoutHD : [{
        dataField : "groupCd",
        headerText : "그룹코드",
        width : 120
      }, {
        dataField : "groupNm",
        headerText : "그룹코드명",
        width : 140
      }, {
        dataField : "useYn",
        headerText : "사용여부",
        width : 120
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
        dataField : "codeNm",
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

  },
  methods : {
    search(){
      console.log('조회를 시작합니다.',this.queryParam);
      return getCmCodeGrpList(this.queryParam).then(
          (res) => {
            return res.data;
          }
      );

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