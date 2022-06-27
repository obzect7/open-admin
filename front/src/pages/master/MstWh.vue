<template>

  <div :style="{ minHeight: '800px' }">
    <a-spin :spinning="loading" size="large">
      <a-row>
        <a-col :span="24">
          <a-form layout="horizontal">
            <div>
              <a-row>
                <a-col :md="5" :sm="24">
                  <a-form-item
                      label="창고코드"
                      :labelCol="{span: 5}"
                      :wrapperCol="{span: 18, offset: 1}"
                  >
                    <a-input v-model="queryParam.plant_cd" @keyup.enter="searchData" placeholder="입력하세요."/>
                  </a-form-item>
                </a-col>
                <a-col :md="5" :sm="24">
                  <a-form-item
                      label="창고명"
                      :labelCol="{span: 5}"
                      :wrapperCol="{span: 18, offset: 1}"
                  >
                    <a-input v-model="queryParam.plant_nm" @keyup.enter="searchData" placeholder="입력하세요."/>
                  </a-form-item>
                </a-col>
                <a-col :md="5" :sm="24">
                  <a-form-item
                      label="창고유형"
                      :labelCol="{span: 5}"
                      :wrapperCol="{span: 18, offset: 1}"
                  >
                    <a-select v-model="queryParam.wh_type" placeholder="선택하세요.">
                      <a-select-option :key="item.code" :value="item.code" v-for="(item, index) in WH_TYPE">
                        {{ item.code_nm }}
                      </a-select-option>
                    </a-select>
                  </a-form-item>
                </a-col>
                <a-col :md="5" :sm="24">
                  <a-form-item
                      label="사용여부"
                      :labelCol="{span: 5}"
                      :wrapperCol="{span: 18, offset: 1}"
                  >
                    <a-select v-model="queryParam.use_yn" placeholder="선택하세요.">
                      <a-select-option :key="item.code" :value="item.code" v-for="(item, index) in useYnList">
                        {{ item.code_nm }}
                      </a-select-option>
                    </a-select>
                  </a-form-item>
                </a-col>
                <a-col :md="4" :sm="24">
              <span style="float: right; margin-top: 3px;">
                <a-button type="primary" icon="search" @click="searchData" :loading="loading">조회</a-button>
              <a-button style="margin-left: 8px" @click="pageReset">초기화</a-button>
              </span>
                </a-col>
              </a-row>
            </div>
          </a-form>
        </a-col>
      </a-row>

      <a-row type="flex" justify="end" >
        <a-col  :span="3.5">
          <a-button-group>
            <a-space>
            <a-button type="primary" @click="addRow" size="small">
              <a-icon type="plus-square"/>
              추가
            </a-button>
            <a-button type="primary" @click="removeRow" size="small">
              <a-icon type="delete"/>
              삭제
            </a-button>
            <a-button type="primary" @click="saveRow" size="small">
              <a-icon type="save"/>
              저장
            </a-button>
            </a-space>
          </a-button-group>
        </a-col>
      </a-row>

      <a-row >
        <a-col :md="24" :sm="24" >
          <AUIGrid ref="mstWhGrid" class="grid-wrap"
                   @cellEditBegin="CellEditBegin"
                   style="height:65vh"
          >
          </AUIGrid>
        </a-col>
      </a-row>
    </a-spin>
  </div>
</template>

<script>
// AUIGrid 컴포넌트
import AUIGrid from "@/components/auigrid/import/AUIGrid-Vue.js/AUIGrid";
import {getCustomerList, saveCustomer} from "@/services/customer";
import {getCmCodeLoad} from "@/services/commoncode";
import {getMstWhList,saveMstWh} from "@/services/mstWh";

export default {
  components: {
    AUIGrid
  },
  data: function () {
    return {
      loading: false,     //로딩바 유무
      useYnList: [],
      WH_TYPE:[],
      // 쿼리 매개변수
      queryParam: {useYn: ""},
      columnLayout: [],
      // 그리드 속성 정의
      auigridProps: {
        // 편집 가능 여부 (기본값 : false)
        editable: true,

        // 엑스트라 체크박스 표시 설정
        showRowCheckColumn: true,

        // 전체 체크박스 표시 설정
        showRowAllCheckBox: true,

        // 셀 선택모드 (기본값: singleCell)
        selectionMode: "multipleCells",
        showStateColumn: true,
      },

      // 그리드 데이터
      gridData: []
    }

  },
  beforeMount() {

  },
  async mounted() {

    this.useYnList = await getCmCodeLoad('USEYN', '전체')
    this.WH_TYPE   = await getCmCodeLoad('WH_TYPE', '전체')

    // 그리드 칼럼 레이아웃 정의
    this.columnLayout = [
      {dataField: "plant_cd", headerText: "사업장코드", width: 120, },
      {dataField: "wh_cd", headerText: "창고코드", width: 120, headerStyle: "aui-grid-required-header"},
      {dataField: "wh_nm", headerText: "창고명", width: 120, headerStyle: "aui-grid-required-header"},
      {
        dataField: "wh_type", headerText: "창고유형", width: 140, headerStyle: "aui-grid-required-header",
        renderer: {
          type: "DropDownListRenderer",
          list: this.WH_TYPE, //key-value Object 로 구성된 리스트
          keyField: "code", // key 에 해당되는 필드명
          valueField: "code_nm" // value 에 해당되는 필드명
        }
      },
      {
        dataField: "use_yn", headerText: "사용여부", width: 140, headerStyle: "aui-grid-required-header",
        renderer: {
          type: "DropDownListRenderer",
          list: this.useYnList, //key-value Object 로 구성된 리스트
          keyField: "code", // key 에 해당되는 필드명
          valueField: "code_nm" // value 에 해당되는 필드명
        }
      },
      {dataField: "remark", headerText: "비고" },
      {dataField: "reg_id", headerText: "등록자", width: 80, editable: false},
      {dataField: "reg_dt", headerText: "등록일자", width: 80, editable: false},
      {dataField: "mod_id", headerText: "수정자", width: 80, editable: false},
      {dataField: "mod_dt", headerText: "수정일자", width: 80, editable: false}
    ]

    let grid = this.$refs.mstWhGrid;

    // 그리드 생성
    grid.create(this.columnLayout, this.auigridProps);

    // 그리드 데이터 삽입하기
    grid.setGridData(this.gridData);
  },
  watch:{
    gridData: function (newVal, oldVal) {
      this.$refs.mstWhGrid.setGridData(newVal);
    },
  },
  methods: {
    pageReset(){
      //페이지 초기화
      console.log('페이지 초기화')
      this.queryParam = {useYn: ""}
      this.gridData = [];
    },
    searchData() {
      console.log('조회를 시작합니다.', this.queryParam);
      this.loading = true
      return getMstWhList(Object.assign(this.queryParam)).then(
          (res) => {
            console.log('res====', res)
            this.$refs.mstWhGrid.setGridData(res.data);
            setTimeout(() => this.loading = false, this.$gridDelayTime)
          }
      )
    },
    CellEditBegin(event) {
      //해당 필드는 update 불가, add 시 입력가능
      return this.$gridEditable(this.$refs.mstWhGrid,event,["plant_cd","wh_cd"])
    },
    addRow() {
      // 하단에 1행 추가
      // console.log('행추가 !!')
      let item = { use_yn: "Y", row_status: 'I'};
      this.$refs.mstWhGrid.addRow(item, "last");
    },
    removeRow() {
      this.$refs.mstWhGrid.removeCheckedRows();
    },
    saveRow() {
      // const isValid = this.$refs.mstWhGrid.validateChangedGridData(["plant_cd", "wh_cd", "use_yn"], "필수입력 입니다.");
      const isValid = true
      if (isValid) {
        const data = this.$gridGetCudData(this.$refs.mstWhGrid)
        if (data.length > 0) {
          console.log("data===", data)
          saveMstWh(data).then(
              (res) => {
                if (res.code == 200) {
                  this.$message.success('저장완료');
                  this.searchData()
                } else {
                  this.$message.error(res.message);
                }
              },
              error => {
                console.log('error ==== ', error)
              }
          )
        }
      }
    },
  }
}

</script>

<style lang="less" scoped>

</style>