<template>
  <a-modal title="품번 검색 팝업" :visible="this.modalstatus" :footer="null" @cancel="cancelEvent" :width="650"
           :dialogStyle="{height:50}">
    <a-spin :spinning="loading" size="large">
      <a-row>
        <a-col :span="24">
          <a-form layout="horizontal">
            <div>
              <a-row>
                <a-col :md="10" :sm="24">
                  <a-form-item
                      label="품번"
                      :labelCol="{span: 5}"
                      :wrapperCol="{span: 18, offset: 1}"
                  >
                    <a-input v-model="queryParam.item_cd" @keyup.enter="searchData" placeholder="입력하세요."/>
                  </a-form-item>
                </a-col>
                <a-col :md="10" :sm="24">
                  <a-form-item
                      label="품명"
                      :labelCol="{span: 5}"
                      :wrapperCol="{span: 18, offset: 1}"
                  >
                    <a-input v-model="queryParam.item_nm" @keyup.enter="searchData" placeholder="입력하세요."/>
                  </a-form-item>
                </a-col>
                <a-col :md="4" :sm="24">
                <span style="float: right; margin-top: 3px;">
                  <a-button type="primary" icon="search" @click="searchData" :loading="loading">조회</a-button>
                </span>
                </a-col>
              </a-row>
            </div>
          </a-form>
        </a-col>
      </a-row>

      <a-row>
        <a-col :md="24" :sm="24">
          <AUIGrid ref="itemPopupGrid" class="grid-wrap"
                   style="height:65vh"
                   @cellDoubleClick="itemSelected"
          >
          </AUIGrid>
        </a-col>
      </a-row>
    </a-spin>
  </a-modal>
</template>

<script>
import AUIGrid from "@/components/auigrid/import/AUIGrid-Vue.js/AUIGrid";
import {mapMutations, mapState} from "vuex";
import {getCmCodeLoad} from "@/services/commoncode";
import {getItemList} from "@/services/item";

export default {
  components: {
    AUIGrid
  },

  data() {
    return {
      loading: false,     //로딩바 유무
      queryParam: {},     // 쿼리 매개변수
      columnLayout: [],  //컬럼 레이아웃
      gridData: [],      // 그리드 데이터
      useYnList: [],
      bodyStyle: {
        height: 50,
      },
      // 그리드 속성 정의
      auigridProps: {
        // 편집 가능 여부 (기본값 : false)
        editable: false,

        // 엑스트라 체크박스 표시 설정
        showRowCheckColumn: false,

        // 전체 체크박스 표시 설정
        showRowAllCheckBox: false,

        // 셀 선택모드 (기본값: singleCell)
        selectionMode: "multipleCells",
        showStateColumn: false,
      },
    }
  },
  props: {
    callid:null,
    callType:null
  },

  async mounted() {
    this.useYnList = await getCmCodeLoad('USEYN', '전체')

    // 그리드 칼럼 레이아웃 정의
    this.columnLayout = [
      {dataField: "plant_cd", headerText: "사업장", width: 120, visible: false},
      {dataField: "owner_cd", headerText: "화주코드", width: 140, visible: false},

      {dataField: "item_cd", headerText: "품번", width: 120, visible: true},
      {dataField: "item_nm", headerText: "품명", width: 120, visible: true, style: "left-text "},
      {dataField: "spec", headerText: "규격", width: 120, visible: true, style: "left-text "},
      {dataField: "unit", headerText: "단위", width: 120, visible: true},
      {dataField: "class1", headerText: "구분1", width: 120, visible: true},
      {dataField: "class2", headerText: "구분2", width: 120, visible: true},
      {dataField: "class3", headerText: "구분3", width: 120, visible: true},
      {dataField: "boxqty", headerText: "Box/Qty", width: 120, visible: true, style: "right-text "},
      {dataField: "pltqty", headerText: "Plt/Qty", width: 120, visible: true, style: "right-text "},
      {dataField: "in_price1", headerText: "입고단가", width: 120, visible: true, style: "right-text "},
      {dataField: "out_price", headerText: "출고단가", width: 120, visible: true, style: "right-text "},
      {dataField: "lot_yn", headerText: "LOT관리여부", width: 120, visible: true},
      {dataField: "fifo_yn", headerText: "선입선출여부", width: 120, visible: true},
      {dataField: "appro_inv", headerText: "적정재고", width: 120, visible: true, style: "right-text "},
      {dataField: "in_wh_cd", headerText: "입고창고", width: 120, visible: true},
      {dataField: "in_lc_cd", headerText: "입고로케이션", width: 120, visible: true},
      {dataField: "remark", headerText: "비고", width: 140, style: "left-text "},
      {
        dataField: "use_yn", headerText: "사용여부", width: 140,
        renderer: {
          type: "DropDownListRenderer",
          list: this.useYnList, //key-value Object 로 구성된 리스트
          keyField: "code", // key 에 해당되는 필드명
          valueField: "value" // value 에 해당되는 필드명
        }
      },
      {dataField: "reg_id", headerText: "등록자", width: 140, editable: false},
      {dataField: "reg_dt", headerText: "등록일자", width: 140, editable: false},
      {dataField: "mod_id", headerText: "수정자", width: 140, editable: false},
      {dataField: "mod_dt", headerText: "수정일자", width: 140, editable: false}
    ]

    // 그리드 생성
    this.$refs.itemPopupGrid.create(this.columnLayout, this.auigridProps);

    // 그리드 데이터 삽입하기
    // this.$refs.itemPopupGrid.setGridData(this.gridData);

  },
  created() {
    console.log('callid====',this.callid)
  },
  computed: {
    ...mapState('modal', ['modalstatus']),
  },
  watch: {},
  methods: {
    ...mapMutations('modal', ['setModalstatus']),
    cancelEvent() {
      this.setModalstatus(false)
    },
    searchData() {
      console.log('조회를 합니다.')
      this.loading = true
      return getItemList(Object.assign(this.queryParam)).then(
          (res) => {
            this.$refs.itemPopupGrid.setGridData(res.data);
            setTimeout(() => this.loading = false, process.env.VUE_DELAY_TIME)
          }
      )
    },itemSelected(event){
      console.log('event ==',event)
      console.log('callid ==',this.callid)
      console.log('callType ==',this.callType)

      this.setModalstatus(false)
    }
  }
}
</script>

<style scoped>
.popupStyle {
  height: 100px;
}
</style>
