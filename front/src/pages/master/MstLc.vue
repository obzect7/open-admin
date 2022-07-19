<template>

  <div :style="{ minHeight: '800px' }">
    <a-spin :spinning="loading" size="large">

      <wh-popup v-if="this.$store.state.modal.wh_popup"
                :visible="this.$store.state.modal.wh_popup"
                :cellClickinfo="this.cellClickinfo"
                @selectWh="selectGridWh"
      ></wh-popup>
      <a-form layout="horizontal">
        <a-row type="flex">
          <a-col :span="24" :xl="6" flex="auto">
            <a-form-item
                label="사업장"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}"
            >
              <a-select v-model="queryParam.plant_cd" placeholder="선택하세요." @change="plantChange" >
                <a-select-option :key="item.plant_cd" :value="item.plant_cd" v-for="(item, index) in this.plantList">
                  {{ item.plant_nm }}
                </a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
          <a-col :span="24" :xl="6" flex="auto">
            <a-form-item
                label="창고"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}"
            >
              <a-select mode="multiple"
                        placeholder="선택하세요."
                        @change="whChange">
                <a-select-option :key="item.wh_cd" :value="item.wh_cd" v-for="(item, index) in whList">
                  {{ item.wh_nm }}
                </a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
          <a-col :span="24" :xl="6" flex="auto">
            <a-form-item
                label="사용여부"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}"
            >
              <a-radio-group default-value="Y" button-style="solid" v-model="queryParam.use_yn">
                <a-radio-button :key="item.code" :value="item.code" v-for="(item, index) in useYnList">
                  {{ item.code_nm }}
                </a-radio-button>
              </a-radio-group>

            </a-form-item>
          </a-col>
          <a-col :span="24" :xl="6" flex="180px">
              <span style="float: right; margin-top: 3px;">
                <a-button type="primary" icon="search" @click="searchData" :loading="loading">조회</a-button>
              <a-button style="margin-left: 8px" @click="pageReset">초기화</a-button>
              </span>
          </a-col>
        </a-row>
        <a-row type="flex">

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
          <AUIGrid ref="mstLcGrid" class="grid-wrap"
                   v-model="gridData"
                   @cellEditBegin="CellEditBegin"
                   @cellClick="cellClickHandler"
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
import {getCmCodeLoad, getPlantComboList, getWhComboList} from "@/services/commoncode";
import {getMstLcList, saveMstPlant} from "@/services/mstLc";
import WhPopup from "@/pages/components/modal/WhPopup";
import {mapMutations} from "vuex";
import {saveMstLc} from "@/services/mstLc";

export default {
  components: {
    WhPopup,
    AUIGrid
  },
  data: function () {
    return {
      loading: false,  //로딩바 유무
      plantList:[],   //사업장 리스트
      whList:[],      //창고 리스트
      useYnList: [],
      znList: [],
      // 쿼리 매개변수
      queryParam: {useYn: "",plant_cd:"",wh_cd:""},
      columnLayout: [],
      // 그리드 속성 정의
      auigridProps: {
        // 실제 조회 Field 또는 가상 Field 모두 가능 ( 가상 Field 일 경우 추가 row는 uid 생성되나, 조회된 row는 uid 존재 안함//실제 field면 조회 field 값, 추가면 _$uid 값이 field 값으로 생성됨.  )
        rowIdField: "uid",
        // 편집 가능 여부 (기본값 : false)
        editable: true,

        // 엑스트라 체크박스 표시 설정
        showRowCheckColumn: true,

        // 전체 체크박스 표시 설정
        showRowAllCheckBox: false,

        // 셀 선택모드 (기본값: singleCell)
        selectionMode: "multipleCells",
        showStateColumn: true,
      },

      // 그리드 데이터
      gridData: [],

      //그릳드 선택cell 정보
      cellClickinfo: {
        rowIndex: '',
        dataField: '',
      }
    }

  },
  beforeMount() {

  },
  async mounted() {

    this.useYnList = await getCmCodeLoad('USEYN', '전체')
    this.znList    = await getCmCodeLoad('ZN_CD', '선택')
    this.plantList = await getPlantComboList('전체')
    console.log('plantList==',this.plantList)


    // 그리드 칼럼 레이아웃 정의
    this.columnLayout = [
      {dataField: "plant_cd", headerText: "사업장코드", width: 120, headerStyle: "aui-grid-required-header"},
      {dataField: "plant_nm", headerText: "사업장명", width: 120, editable: false},
      {
        dataField: "wh_cd", headerText: "창고코드", width: 120, headerStyle: "aui-grid-required-header", editable: true,
        renderer: {
          type: "IconRenderer",
          iconWidth: 20, // icon 가로 사이즈, 지정하지 않으면 24로 기본값 적용됨
          iconHeight: 20,
          iconPosition: "aisleRight",  // 아이콘 위치
          iconTableRef: {"default": this.$searchIcon},
          onClick: this.openWhPopup
        },
      },
      {dataField: "wh_nm", headerText: "창고명", width: 120, editable: false},
      {dataField: "lc_cd", headerText: "위치코드", width: 120, headerStyle: "aui-grid-required-header"},
      {dataField: "lc_nm", headerText: "위치명", width: 120, headerStyle: "aui-grid-required-header"},
      {
        dataField: "zn_cd", headerText: "구역", width: 120,
        renderer: {
          type: "DropDownListRenderer",
          list: this.znList, //key-value Object 로 구성된 리스트
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
      {dataField: "remark", headerText: "비고", width: 140, style: "left-text" , editable: true},
      {dataField: "reg_id", headerText: "등록자", width: 80, editable: false},
      {dataField: "reg_dt", headerText: "등록일자", width: 80, editable: false},
      {dataField: "mod_id", headerText: "수정자", width: 80, editable: false},
      {dataField: "mod_dt", headerText: "수정일자", width: 80, editable: false}
    ]

    // 그리드 생성
    this.$refs.mstLcGrid.create(this.columnLayout, this.auigridProps);

    // 그리드 데이터 삽입하기
    // this.$refs.mstLcGrid.setGridData(this.gridData);
  },
  watch: {
    gridData: function (newVal, oldVal) {
      this.$refs.mstLcGrid.setGridData(newVal);
    },
  },
  methods: {
    ...mapMutations('modal', ['setWh_popup']),
    pageReset() {
      //페이지 초기화
      console.log('페이지 초기화')
      this.queryParam = {useYn: ""}
      this.gridData = [];
    },
    searchData() {
      console.log('조회를 시작합니다.', this.queryParam);
      this.loading = true
      return getMstLcList(Object.assign(this.queryParam)).then(
          (res) => {
            console.log('res====', res)
            this.gridData = res.data
            setTimeout(() => this.loading = false, this.$gridDelayTime)
          }
      )
    },
    CellEditBegin(event) {
      //해당 필드는 update 불가, add 시 입력가능
      // return this.$gridEditable(this.$refs.mstLcGrid, event, ["plant_cd", "plant_nm"])
    },
    cellClickHandler(event) {


    },
    selectGridWh(event) {
      console.log("event====", event)
      const rowIndex = event.cellClickinfo.rowIndex;

      //console.log("rowIndex====", rowIndex)
      this.$refs.mstLcGrid.setCellValue(rowIndex, "wh_cd", event.wh_cd)
      this.$refs.mstLcGrid.setCellValue(rowIndex, "wh_nm", event.wh_nm)

      this.setWh_popup(false)

    },
    addRow() {
      // 하단에 1행 추가
      // console.log('행추가 !!')
      let item = {use_yn: "Y", row_status: 'I'};
      this.$refs.mstLcGrid.addRow(item, "last");
    },
    removeRow() {
      this.$refs.mstLcGrid.removeCheckedRows();
    },
    saveRow() {
      const isValid = this.$refs.mstLcGrid.validateChangedGridData(["plant_cd", "wh_cd", "lc_cd", "lc_nm", "use_yn"], "필수입력 입니다.");
      if (isValid) {
        const data = this.$gridGetCudData(this.$refs.mstLcGrid)
        if (data.length > 0) {
          console.log("data===", data)
          saveMstLc(data).then(
              (res) => {
                if (res.code == 200) {
                  this.$message.success('저장완료');
                  this.searchData()
                } else {
                  this.$message.error(res.message);
                }
              }
          )
        }
      }
    },
    openWhPopup(event) {
      console.log('event==', event)
      const rowaddYn = this.$refs.mstLcGrid.isAddedById(event.item.uid)
      if (rowaddYn) {
        this.setWh_popup(true)
      }
    },
    async plantChange(value) {
      console.log('value ====',value)
      this.whList = await getWhComboList(value,)
    },
    whChange(value){
      const test = value
      this.queryParam.wh_cd = ''+value
      console.log('@@@@@@@@@',this.queryParam.wh_cd)
    }
  }
}

</script>

<style lang="less" scoped>

</style>