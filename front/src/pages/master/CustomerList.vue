<template>
  <a-spin :spinning="loading" size="large">

  <div :bordered="false" :style="{ minHeight: '800px' }">
    <div >
      <a-form layout="horizontal" >
        <div >
          <a-row >
            <a-col :md="7" :sm="24" >
              <a-form-item
                  label="거래처코드"
                  :labelCol="{span: 5}"
                  :wrapperCol="{span: 18, offset: 1}"
              >
                <a-input v-model="queryParam.custCd" placeholder="입력하세요." />
              </a-form-item>
            </a-col>
            <a-col :md="7" :sm="24" >
              <a-form-item
                  label="거래처명"
                  :labelCol="{span: 5}"
                  :wrapperCol="{span: 18, offset: 1}"
              >
                <a-input v-model="queryParam.custNm" placeholder="입력하세요." />
              </a-form-item>
            </a-col>
            <a-col :md="7" :sm="24" >
              <a-form-item
                  label="사용여부"
                  :labelCol="{span: 5}"
                  :wrapperCol="{span: 18, offset: 1}"
              >
                <a-select v-model="queryParam.useYn" placeholder="선택하세요.">
                  <a-select-option :key="item.code" :value="item.code" v-for="(item, index) in useYnList">{{item.code_nm}}</a-select-option>
<!--                  <a-select-option value="Y">사용</a-select-option>-->
<!--                  <a-select-option value="N">미사용</a-select-option>-->
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="3" :sm="24" >
              <span style="float: right; margin-top: 3px;">
                <a-button type="primary" icon="search" @click="search" :loading="loading">조회</a-button>
              <a-button style="margin-left: 8px">초기화</a-button>
              </span>
            </a-col>
          </a-row>
        </div>
      </a-form>
    </div>

    <div>
      <a-row>

        <a-col :md="24" :sm="24">
          <div>

            <a-button-group>
              <a-button type="primary" @click="addRow"> <a-icon type="plus-square" />추가 </a-button>
              <a-button type="primary" @click="removeRow"> <a-icon type="delete" />삭제 </a-button>
              <a-button type="primary" @click="saveRow"> <a-icon type="save" />저장 </a-button>
            </a-button-group>
          </div>
          <AUIGrid ref="custGrid" class="grid-wrap"
                   @cellEditBegin = "CellEditBegin"
                   style="height:65vh"
          >
          </AUIGrid>
        </a-col>
      </a-row>
    </div>


  </div>
  </a-spin>
</template>



<script>



// AUIGrid 컴포넌트

import AUIGrid from "@/components/auigrid/import/AUIGrid-Vue.js/AUIGrid";
import {getCustomerList, saveCustomer} from "@/services/customer";
import {getCmCodeLoad} from "@/services/commoncode";
const useYnList= []

export default {

  components : {
    AUIGrid
  },
  data: function () {
    return {
      loading: false,     //로딩바 유무
      delayTime: 1000,    //로딩 딜레이
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
    //this.useYnList = [ {"code":"Y", "value":"사용"}, {"code":"N", "value":"미사용"}]
  },
  async mounted() {

    this.useYnList = await getCmCodeLoad('USEYN', '전체')

    // 그리드 칼럼 레이아웃 정의
    this.columnLayout = [
      {dataField: "plant_cd", headerText: "사업장", width: 120, visible: false},
      {dataField: "owner_cd", headerText: "화주코드", width: 140, visible: false},
      {dataField: "cust_cd", headerText: "거래처코드", width: 140, headerStyle: "aui-grid-required-header",},
      {
        dataField: "cust_nm",
        headerText: "거래처명",
        width: 140,
        style: "left-text ",
        headerStyle: "aui-grid-required-header",
      },
      {dataField: "cust_desc", headerText: "거래처약어", width: 140, style: "left-text "},
      {dataField: "ceo", headerText: "대표자명", width: 140, style: "left-text "},
      {dataField: "business_no", headerText: "사업자번호", width: 140},
      {dataField: "corp_no", headerText: "법인번호", width: 140},
      {dataField: "tel", headerText: "대표번호", width: 140},
      {dataField: "post_no", headerText: "우편번호", width: 140},
      {dataField: "addr1", headerText: "주소1", width: 140, style: "left-text "},
      {dataField: "addr2", headerText: "주소2", width: 140, style: "left-text "},
      {dataField: "fax", headerText: "팩스번호", width: 140},
      {dataField: "email", headerText: "이메일", width: 140, style: "left-text "},
      {dataField: "business_type", headerText: "업태", width: 140, style: "left-text "},
      {dataField: "business_item", headerText: "종목", width: 140, style: "left-text "},
      {dataField: "first_day", headerText: "최초거래일", width: 140},
      {dataField: "remark", headerText: "비고", width: 140, style: "left-text "},
      {
        dataField: "use_yn", headerText: "사용여부", width: 140, headerStyle: "aui-grid-required-header",
        renderer: {
          type: "DropDownListRenderer",
          list: this.useYnList, //key-value Object 로 구성된 리스트
          keyField: "code", // key 에 해당되는 필드명
          valueField: "code_nm" // value 에 해당되는 필드명
        }
      },
      {dataField: "reg_id", headerText: "등록자", width: 140, editable: false},
      {dataField: "reg_dt", headerText: "등록일자", width: 140, editable: false},
      {dataField: "mod_id", headerText: "수정자", width: 140, editable: false},
      {dataField: "mod_dt", headerText: "수정일자", width: 140, editable: false}
    ]


    let grid = this.$refs.custGrid;

    // 그리드 생성

    grid.create(this.columnLayout, this.auigridProps);


    // 그리드 데이터 삽입하기
    grid.setGridData(this.gridData);
  },
  methods : {
    search(){
      console.log('조회를 시작합니다.',this.queryParam);
      this.loading = true
      return getCustomerList(Object.assign(this.queryParam)).then(

          (res) => {
            console.log('res====',res)
            this.$refs.custGrid.setGridData(res.data);
            //return res.data;
            setTimeout(() => this.loading = false, 500)
          }
      )
    },
    CellEditBegin(event){

      // console.log('event===', event)
      // console.log('event.dataField===', event.dataField)
      //return false

      const grid = this.$refs.custGrid;
      let rowIdField = event.dataField;
      let rowIndex = event.rowIndex;
      let row_status = grid.getCellValue(rowIndex, 'row_status')

      // console.log("rowIndex===", rowIndex)
      // console.log("rowIdField===", rowIdField)
      // console.log("row_status===", row_status)

      if(rowIdField == 'cust_cd' && row_status != 'I'){
        return false
      }
      return true
    },
    addRow(){
      // 하단에 1행 추가
      // console.log('행추가 !!')
      let item = {plant_cd : 10000, owner_cd : 10000, use_yn : "Y", row_status : 'I'};


      this.$refs.custGrid.addRow(item, "last");
    },
    removeRow(){
      this.$refs.custGrid.removeCheckedRows();
    },
    saveRow(){

      const isValid = this.$refs.custGrid.validateChangedGridData(["plant_cd", "owner_cd", "cust_cd", "cust_nm","use_yn"], "필수입력 입니다.");

      if(isValid){

        const data = this.$getCrdData(this.$refs.custGrid)

        if (data.length > 0) {

          console.log("data===", data)
          saveCustomer(data).then(
              (res) => {
                if (res.code == 200) {
                  this.search()
                } else {
                  this.$message.error(res.message);
                }
              }
          )
        }
      }
    },
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