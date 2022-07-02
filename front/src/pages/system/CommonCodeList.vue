<template>
  <a-spin :spinning="loading" size="large">
    <a-row>
      <a-col :span="24">
        <a-form layout="horizontal">
          <div>
            <a-row>
              <a-col :md="7" :sm="24">
                <a-form-item
                    label="그룹코드"
                    :labelCol="{span: 5}"
                    :wrapperCol="{span: 18, offset: 1}"
                >
                  <a-input v-model="queryParam.groupCd" placeholde1="입력하세요."/>
                </a-form-item>
              </a-col>
              <a-col :md="7" :sm="24">
                <a-form-item
                    label="품번"
                    :labelCol="{span: 5}"
                    :wrapperCol="{span: 18, offset: 1}"
                >
                  <a-input-group >
                    <a-row :gutter="8">
                      <a-col :span="10">
                        <a-input-search placeholder="" enter-button @search="onSearchAckey" v-model="queryParam.item_cd" />
                      </a-col>
                      <a-col :span="14">
                        <a-input default-value="" v-model="queryParam.item_nm">
                        <a-icon slot="addonAfter" type="close-circle" @click="resetAckey" />
                        </a-input>
                      </a-col>
                    </a-row>
                  </a-input-group>

                  <item-popup v-if="this.$store.state.modal.item_popup" :visible="this.$store.state.modal.item_popup"
                              :callType="'input'" @closepopItem="closepopItem"></item-popup>
                </a-form-item>
              </a-col>
              <a-col :md="7" :sm="24">
                <a-form-item
                    label="사용여부"
                    :labelCol="{span: 5}"
                    :wrapperCol="{span: 18, offset: 1}"
                >
                  <a-select v-model="queryParam.useYn"  >
                    <a-select-option :key="item.code" :value="item.code" v-for="(item, index) in useYnList">{{item.code_nm}}</a-select-option>
<!--                    <a-select-option value="Y">사용</a-select-option>-->
<!--                    <a-select-option value="N">미사용</a-select-option>-->
                  </a-select>
                </a-form-item>
              </a-col>
              <a-col :md="7" :sm="24">
                <a-form-item
                    label="그룹코드명"
                    :labelCol="{span: 5}"
                    :wrapperCol="{span: 18, offset: 1}"
                >
                  <a-input v-model="queryParam.groupNm" placeholder="입력하세요."/>
                </a-form-item>
              </a-col>
              <a-col :md="3" :sm="24">
                <span style="float: right; margin-top: 3px;">
                  <a-button type="primary" icon="search" @click="searchMaster" :loading="loading">조회</a-button>
                  <a-button style="margin-left: 8px">초기화</a-button>
                </span>
              </a-col>
            </a-row>
          </div>
        </a-form>

      </a-col>
    </a-row>

    <a-row>

      <a-col :md="10" :sm="24">
        <div>
          <a-button-group>
            <a-button type="primary" @click="masterAddRow">
              <a-icon type="plus-square"/>
              추가
            </a-button>
            <a-button type="primary" @click="masterRemoveRow">
              <a-icon type="delete"/>
              삭제
            </a-button>
            <a-button type="primary" @click="saveMaster">
              <a-icon type="save"/>
              저장
            </a-button>
            <a-button type="primary" @click="downLoadExcel('grid1')">
              <a-icon type="file-excel"/>
              다운로드
            </a-button>

          </a-button-group>
        </div>
        <AUIGrid ref="myGrid1" class="grid-wrap"
                 @cellClick="cellClickHandler"
                 @cellEditBegin="cellEditBeginMaster"
                 style="height:65vh"
        >
        </AUIGrid>
      </a-col>
      <a-col :md="14" :sm="24">
        <div>
          <a-button type="primary" @click="detailAddRow">
            <a-icon type="plus-square"/>
            추가
          </a-button>
          <a-button type="primary" @click="detailRemoveRow">
            <a-icon type="delete"/>
            삭제
          </a-button>
          <a-button type="primary" @click="saveDetail">
            <a-icon type="save"/>
            저장
          </a-button>
          <a-button type="primary" @click="downLoadExcel('grid2')">
            <a-icon type="file-excel"/>
            다운로드
          </a-button>
        </div>
        <AUIGrid ref="myGrid2" class="grid-wrap"
                 style="height:65vh"
                 @cellEditBegin="cellEditBeginDetail"
        >
        </AUIGrid>
      </a-col>
    </a-row>
  </a-spin>
</template>


<script>


// AUIGrid 컴포넌트

import AUIGrid from "@/components/auigrid/import/AUIGrid-Vue.js/AUIGrid";
import {getCmCodeGrpList, getCmCodeList, getCmCodeLoad, saveCmCode, saveCmCodeGrp} from "@/services/commoncode";
import ItemPopup from "@/pages/components/modal/ItemPopup";
import {mapMutations} from "vuex";
import {saveMstPlant} from "@/services/plant";

const useYnList = []
export default {

  components: {
    ItemPopup,
    AUIGrid
  },
  data: function () {
    return {
      loading: false,     //로딩바 유무
      masterRow: {},       //그룹코드 정보
      useYnList,
      // 쿼리 매개변수
      queryParam: {
        item_cd:"",
        item_nm:"",
      },
      // 그리드 칼럼 레이아웃 정의
      columnLayoutHD: [],
      columnLayoutDT: [],

      // 그리드 속성 정의
      auigridProps: {
        // 엑스트라 체크박스 표시 설정
        showRowCheckColumn: true,
        // 엑스트라 체크박스에 shiftKey + 클릭으로 다중 선택 할지 여부 (기본값 : false)
        enableRowCheckShiftKey: true,
        // 전체 체크박스 표시 설정
        showRowAllCheckBox: true,
        // 편집 가능 여부 (기본값 : false)
        editable: true,
        // 셀 선택모드 (기본값: singleCell)
        selectionMode: "multipleCells",
        //필터사용유무
        enableFilter : true,
      },

      // 그리드 데이터
      gridDataHD: [],
      gridDataDT: [],
      visible: false,

    }

  },

  async beforeMount() {
    // let test = this.$store.state.commcd.commoncodes
    // const test222 = this.$store.getters["commcd/commoncodes"];
    // // const test55 = localStorage.getItem('commoncodes')
    // // // test = JSON.parse(test.filter(cm => cm.group_cd == "USEYN"))
    // console.log('test11111111 === ', test)
    // // console.log('test55 === ', test55)
    // // this.useYnList = [{"code": "Y", "value": "사용"}, {"code": "N", "value": "미사용"}]
    // const useyn = await getCmCodeLoad('USEYN')
    // console.log('useyn===', useyn)
    // this.useYnList = useyn
    // console.log('useYnList===', this.useYnList)
  },
  async mounted() {
    console.log('mounted @@@@@@@@@@####### = ')
    this.useYnList = await getCmCodeLoad('USEYN','전체')
    this.columnLayoutHD = [
      {
        dataField: "group_cd",
        headerText: "그룹코드",
        headerStyle: "aui-grid-required-header",
        style: "left-text",
        width: 120
      },
      {
        dataField: "group_nm",
        headerText: "그룹코드명",
        headerStyle: "aui-grid-required-header",
        width: 140,
        style: "left-text "
      },
      {
        dataField: "use_yn", headerText: "사용여부", headerStyle: "aui-grid-required-header", width: 120,
        renderer: {
          type: "DropDownListRenderer",
          list: this.useYnList, //key-value Object 로 구성된 리스트
          keyField: "code", // key 에 해당되는 필드명
          valueField: "code_nm" // value 에 해당되는 필드명
        },
      },
      {dataField: "rem", headerText: "비고"}
    ]

    this.columnLayoutDT = [
      {dataField: "code", headerText: "코드ID", filter : {showIcon : true}, width: 120 },
      {dataField: "code_nm", headerText: "코드명", filter : {showIcon : true}, width: 140},
      {dataField:"sort",			headerText:"정렬",			width:100,		dataType:"numeric",
        renderer : {
          type : "NumberStepRenderer",
          min : 1,
          max : 99,
          step : 1,
          inputHeight : 25, // input 높이 지정
          textEditable : true
        }
      },
      {
        dataField: "use_yn", headerText: "사용여부", width: 120,
        renderer: {
          type: "DropDownListRenderer",
          list: this.useYnList, //key-value Object 로 구성된 리스트
          keyField: "code", // key 에 해당되는 필드명
          valueField: "code_nm" // value 에 해당되는 필드명
        },
      },
      {dataField: "data1", headerText: "속성1", width: 120},
      {dataField: "data2", headerText: "속성2", width: 120},
      {dataField: "data3", headerText: "속성3", width: 120},
      {dataField: "data4", headerText: "속성4", width: 120},
      {dataField: "data5", headerText: "속성5", width: 120},
      {dataField: "data6", headerText: "속성6", width: 120},
      {dataField: "data7", headerText: "속성7", width: 120},
    ]

    let grid1 = this.$refs.myGrid1;
    let grid2 = this.$refs.myGrid2;

    // 그리드 생성

    grid1.create(this.columnLayoutHD, this.auigridProps);
    grid2.create(this.columnLayoutDT, this.auigridProps);

    // 그리드 데이터 삽입하기
    grid1.setGridData(this.gridDataHD);
    grid2.setGridData(this.gridDataDT);

  }, created() {
    // this.$refs.myGrid1.resize(null,1200)
    // this.$refs.myGrid2.resize(null,1200)
  },
  methods: {
    ...mapMutations('modal', ['setModalstatus']),
    searchMaster() {
      console.log('조회를 시작합니다.', this.queryParam);
      this.loading = true
      getCmCodeGrpList(Object.assign(this.queryParam)).then(
          (res) => {
            console.log('res====', res)
            this.$refs.myGrid1.setGridData(res.data);
            // 실제로 새로 고침은 매우 빠르며, 이 지연을 추가하는 것은 순전히 로딩 상태를 잠시 동안 표시하여 사용자가 새로 고침 과정을 인지할 수 있도록 하기 위한 것입니다.
            setTimeout(() => this.loading = false, this.$gridDelayTime)
            //return res.data;
          }
      )
    }, searchDetail() {
      this.loading = true
      getCmCodeList(Object.assign(this.masterRow)).then(
          (res) => {
            console.log('res====@@@@@', res)
            this.$refs.myGrid2.setGridData(res.data);
            // this.loading = false
            setTimeout(() => this.loading = false, this.$gridDelayTime)
            //return res.data;
          }
      )
    },
    masterAddRow() {
      // 하단에 1행 추가
      console.log('행추가 !!')
      let item = {use_yn: "Y", row_status: 'I'};
      this.$refs.myGrid1.addRow(item, "last");
      this.$refs.myGrid2.clearGridData();
    },
    masterRemoveRow() {
      // 체크된 행 삭제 처리
      this.$refs.myGrid1.removeCheckedRows();
    },
    detailAddRow() {

      let master = this.$refs.myGrid1.getSelectedRows()
      if (master.length > 0) {

        // 하단에 1행 추가
        console.log('행추가 !!')
        let item = {use_yn: "Y", row_status: 'I', group_cd: master[0].group_cd};
        this.$refs.myGrid2.addRow(item, "last");
      } else {
        this.$message.warn('그룹코드를 선택하세요.', 3)
      }
      console.log('test == ', master);
    },
    detailRemoveRow() {
      // 체크된 행 삭제 처리
      this.$refs.myGrid2.removeCheckedRows();
    }, cellClickHandler(event) {
      // 셀클릭 이벤트 핸들링
      console.log('cell click ===', event)
      if (event.dataField == "group_cd" && event.item.row_status != "I") {
        this.masterRow = event.item
        this.searchDetail()
      } else {
        this.$refs.myGrid2.clearGridData();
      }
    },
    cellEditBeginMaster(event) {
      //해당 필드는 update 불가, add 시 입력가능
      return this.$gridEditable(this.$refs.myGrid1,event,["group_cd"])
    },cellEditBeginDetail(event) {
      //해당 필드는 update 불가, add 시 입력가능
      return this.$gridEditable(this.$refs.myGrid2,event,["code"])
    }, saveMaster() {
      const data = this.$gridGetCudData(this.$refs.myGrid1,["group_cd", "code", "code_nm"])
      if(data.length){
        saveCmCodeGrp(data).then(
            (res) => {
              console.log('res====', res)
              if (res.code == 200) {
                this.searchMaster()
              } else {
                this.$message.error(res.message);
              }
              //return res.data;
            },
            error => {
              console.log('error ==== ', error)
            }
        )
      }

    }, saveDetail() {

      const data = this.$gridGetCudData(this.$refs.myGrid2,["code", "code_nm"])
      if(data.length){
        saveCmCode(data).then(
            (res) => {
              console.log('res====', res)
              if (res.code == 200) {
                this.searchMaster()
              } else {
                this.$message.error(res.message);
              }
              //return res.data;
            },
            error => {
              console.log('error ==== ', error)
            }
        )
      }

    },
    downLoadExcel(id) {
      // 내보내기 실행
      if(id == "grid1"){
        this.$refs.myGrid1.exportToXlsx({
          isRowStyleFront: false,
        })
      }else{
        this.$refs.myGrid2.exportToXlsx({
          isRowStyleFront: false,
        })
      }


    },
    onSearchAckey(){
      console.log('팝업 띄우는 쌤플')
      this.setModalstatus(true)
    },resetAckey(){
      console.log('!@@@@@@@@@@@@')
      this.queryParam.item_cd  = ''
      this.queryParam.item_nm  = ''
    },
    //품번팝업에서 선택한 대상
    closepopItem(event) {
      console.log("event=======", event)
      this.queryParam.item_cd = event.item_cd
      this.queryParam.item_nm = event.item_nm
      this.setModalstatus(false)
    }
  }
}

</script>

<style lang="less" scoped>

</style>