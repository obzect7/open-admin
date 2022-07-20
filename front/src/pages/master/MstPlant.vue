<template>
  <div :style="{ minHeight: '800px' }">
    <a-spin :spinning="loading" size="large">
      <a-form layout="horizontal">
        <a-row type="flex">
          <a-col :span="24" :xl="6" flex="auto">
            <a-form-item
                label="사업장코드"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}"
            >
              <a-input v-model="queryParam.plant_cd" @keyup.enter="searchData" placeholder="입력하세요."/>
            </a-form-item>
          </a-col>
          <a-col :span="24" :xl="6" flex="auto">
            <a-form-item
                label="사업장명"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}"
            >
              <a-input v-model="queryParam.plant_nm" @keyup.enter="searchData" placeholder="입력하세요."/>
            </a-form-item>
          </a-col>
          <a-col :span="24" :xl="6" flex="auto">
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
          <a-col :span="24" :xl="6" flex="180px">
              <span style="float: right; margin-top: 3px;">
                <a-button type="primary" icon="search" @click="searchData" :loading="loading">조회</a-button>
              <a-button style="margin-left: 8px" @click="pageReset">초기화</a-button>
              </span>
          </a-col>
        </a-row>
      </a-form>

      <a-row type="flex" justify="end" style="margin-top:10px; margin-bottom: 10px;">
        <a-space>
          <a-col :span="8">
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
        <a-col :span="24">
          <AUIGrid ref="mstPlantGrid" class="grid-wrap"
                   @cellEditBegin="CellEditBegin"
                   style="height:65vh"
                   v-model="gridData"
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
import {getCmCodeLoad, saveCmCodeGrp} from "@/services/commoncode";
import {getMstPlantList, saveMstPlant} from "@/services/plant";

export default {
  components: {
    AUIGrid
  },
  data: function () {
    return {
      loading: false,     //로딩바 유무
      useYnList: [],
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
    const that = this
    this.useYnList = await getCmCodeLoad('USEYN', '전체')

    // 그리드 칼럼 레이아웃 정의
    this.columnLayout = [
      {
        dataField: "plant_cd", headerText: "사업장코드", width: 120, headerStyle: "aui-grid-required-header",
        editRenderer: {
          type: "InputEditRenderer",

          // ID는 고유값만 가능하도록 에디팅 유효성 검사
          validator: function (oldValue, newValue, item, dataField) {
            console.log('item==', item)
            console.log('oldValue==', oldValue)
            console.log('newValue==', newValue)
            console.log('dataField==', dataField)
            if (oldValue != newValue) {
              // dataField 에서 newValue 값이 유일한 값인지 조사
              const isValid = that.$refs.mstPlantGrid.isUniqueValue(dataField, newValue)
              // this.$refs.mstPlantGrid.getcell

              // 리턴값은 Object 이며 validate 의 값이 true 라면 패스, false 라면 message 를 띄움
              return {"validate": isValid, "message": newValue + " 값은 고유값이 아닙니다.(이미 존재함) 다른 값을 입력해 주십시오."};
            }
          }
        }
      },
      {dataField: "plant_nm", headerText: "사업장명", width: 120, headerStyle: "aui-grid-required-header"},
      {dataField: "addr", headerText: "주소", style: "left-text"},
      {
        dataField: "use_yn", headerText: "사용여부", width: 140, headerStyle: "aui-grid-required-header",
        renderer: {
          type: "DropDownListRenderer",
          list: this.useYnList, //key-value Object 로 구성된 리스트
          keyField: "code", // key 에 해당되는 필드명
          valueField: "code_nm" // value 에 해당되는 필드명
        }
      },
      {dataField: "reg_id", headerText: "등록자", width: 80, editable: false},
      {dataField: "reg_dt", headerText: "등록일자", width: 80, editable: false},
      {dataField: "mod_id", headerText: "수정자", width: 80, editable: false},
      {dataField: "mod_dt", headerText: "수정일자", width: 80, editable: false}
    ]

    let grid = this.$refs.mstPlantGrid;

    // 그리드 생성
    grid.create(this.columnLayout, this.auigridProps);

    // 그리드 데이터 삽입하기
    grid.setGridData(this.gridData);
  },
  watch: {
    gridData: function (newVal, oldVal) {
      this.$refs.mstPlantGrid.setGridData(newVal);
    },
  },
  methods: {
    pageReset() {
      //페이지 초기화
      console.log('페이지 초기화')
      this.queryParam = {useYn: ""}
      this.gridData = [];
    },
    searchData() {
      console.log('조회를 시작합니다.', this.queryParam);
      this.loading = true
      return getMstPlantList(Object.assign(this.queryParam)).then(
          (res) => {
            console.log('res====', res)
            this.gridData = res.data
            setTimeout(() => this.loading = false, this.$gridDelayTime)
          }
      )
    },
    CellEditBegin(event) {
      //해당 필드는 update 불가, add 시 입력가능
      return this.$gridEditable(this.$refs.mstPlantGrid, event, ["plant_cd"])
    },
    addRow() {
      // 하단에 1행 추가
      // console.log('행추가 !!')
      let item = {use_yn: "Y", row_status: 'I'}
      this.$refs.mstPlantGrid.addRow(item, "last");
    },
    removeRow() {
      const list = this.$refs.mstPlantGrid.getCheckedRowItemsAll()
      if (list.length == 0) {
        this.$message.info('삭제할 행을 선택하세요.');
        return
      }
      this.$refs.mstPlantGrid.removeCheckedRows()
    },
    saveRow() {
      const data = this.$gridGetCudData(this.$refs.mstPlantGrid, ["plant_cd", "plant_nm", "use_yn"])
      if (data.length > 0) {
        this.loading = true
        saveMstPlant(data).then(
            (res) => {
              console.log('res====', res)
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
    },
  }
}

</script>

<style lang="less" scoped>

</style>