<template>
  <a-spin :spinning="loading" size="large">

  <div :bordered="false" :style="{ minHeight: '800px' }">
      <a-form layout="horizontal" >
          <a-row type="flex">
            <a-col :span="24" :xl="6" flex="auto" >
              <a-form-item
                  label="거래처코드"
                  :labelCol="{span: 5}"
                  :wrapperCol="{span: 18, offset: 1}"
              >
                <a-input v-model="queryParam.custCd" placeholder="입력하세요." />
              </a-form-item>
            </a-col>
            <a-col :span="24" :xl="6" flex="auto" >
              <a-form-item
                  label="거래처명"
                  :labelCol="{span: 5}"
                  :wrapperCol="{span: 18, offset: 1}"
              >
                <a-input v-model="queryParam.custNm" placeholder="입력하세요." />
              </a-form-item>
            </a-col>
            <a-col :span="24" :xl="6" flex="auto" >
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
            <a-col :span="24" :xl="6" flex="180px" >
              <span style="float: right; margin-top: 3px;">
                <a-button type="primary" icon="search" @click="search" :loading="loading">조회</a-button>
                <a-button style="margin-left: 8px" @click="pageReset" >초기화</a-button>
              </span>
            </a-col>
          </a-row>
      </a-form>

      <a-row type="flex" justify="end" style="margin-top:10px; margin-bottom: 10px;">
        <a-space >
          <a-col  :span="8">
            <a-button type="primary" @click="addRow" size="small">
              <a-icon type="plus-square"/>
              추가
            </a-button>
          </a-col>
          <a-col :span="8">
            <a-button type="primary" @click="removeRow" size="small">
              <a-icon type="delete"/>
              삭제
            </a-button>
          </a-col>
          <a-col :span="8">
            <a-button type="primary" @click="saveRow" size="small">
              <a-icon type="save"/>
              저장
            </a-button>
          </a-col>
        </a-space>
      </a-row>
      <a-row>
        <AUIGrid ref="custGrid" class="grid-wrap"
                 @cellEditBegin = "CellEditBegin"
                 style="height:65vh">
        </AUIGrid>
      </a-row>

  </div>
  </a-spin>
</template>



<script>



// AUIGrid 컴포넌트

import AUIGrid from "@/components/auigrid/import/AUIGrid-Vue.js/AUIGrid";
import {getCustomerList, saveCustomer} from "@/services/customer";
import {getCmCodeLoad} from "@/services/commoncode";
const useYnList= []
const business_item_list= []
const business_type_list= []

export default {

  components : {
    AUIGrid
  },
  data: function () {
    return {
      loading: false,     //로딩바 유무
      useYnList,
      business_item_list, //업태
      business_type_list, //종목

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
    this.business_item_list = await getCmCodeLoad('BUSINESS_ITEM')
    this.business_type_list = await getCmCodeLoad('BUSINESS_TYPE')

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
      {dataField: "business_type", headerText: "업태", width: 140, style: "left-text ",
        renderer: {
          type: "DropDownListRenderer",
          list: this.business_type_list, //key-value Object 로 구성된 리스트
          keyField: "code", // key 에 해당되는 필드명
          valueField: "code_nm" // value 에 해당되는 필드명
        }
      },
      {dataField: "business_item", headerText: "종목", width: 140, style: "left-text ",
        renderer: {
          type: "DropDownListRenderer",
          list: this.business_item_list, //key-value Object 로 구성된 리스트
          keyField: "code", // key 에 해당되는 필드명
          valueField: "code_nm" // value 에 해당되는 필드명
        }
      },
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
  watch:{
    gridData: function (newVal, oldVal) {
      this.$refs.custGrid.setGridData(newVal);
    },
  },
  methods : {
    pageReset(){
      //페이지 초기화
      console.log('페이지 초기화')
      this.queryParam = {useYn: "Y"}
      this.gridData = [];
    },
    search(){
      console.log('조회를 시작합니다.',this.queryParam);
      this.loading = true
      return getCustomerList(Object.assign(this.queryParam)).then(

          (res) => {
            console.log('res====',res)
            this.$refs.custGrid.setGridData(res.data);
            //return res.data;
            setTimeout(() => this.loading = false, this.$gridDelayTime)
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

      const list = this.$refs.custGrid.getCheckedRowItemsAll()
      if(list.length == 0){
        this.$message.info('삭제할 행을 선택하세요.');
        return
      }
      this.$refs.custGrid.removeCheckedRows()
    },
    saveRow(){
        const data = this.$gridGetCudData(this.$refs.custGrid,["plant_cd", "owner_cd", "cust_cd", "cust_nm","use_yn"])
        if (data.length > 0) {
          this.loading = true
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
    },
  }
}

</script>

<style lang="less" scoped>
</style>