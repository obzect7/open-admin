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
                  <a-input v-model="queryParam.groupCd" placeholder="입력하세요."/>
                </a-form-item>
              </a-col>
              <a-col :md="7" :sm="24">
                <a-form-item
                    label="거래처"
                    :labelCol="{span: 5}"
                    :wrapperCol="{span: 18, offset: 1}"
                >
                  <a-input-search id="input_ackey" placeholder="선택하세요" enter-button @search="onSearchAckey" v-model="queryParam.ackey" />
                  <item-popup v-if="this.$store.state.modal.modalstatus" :visible="this.$store.state.modal.modalstatus"
                              :callType="'input'"></item-popup>
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
                 @cellEditBegin="CodecellEditBegin"
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
      queryParam: {},
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
        selectionMode: "multipleCells"
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
      {dataField: "code", headerText: "코드ID", width: 120},
      {dataField: "code_nm", headerText: "코드명", width: 140},
      {dataField: "sort", headerText: "정렬순서", width: 120},
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
            setTimeout(() => this.loading = false, process.env.VUE_DELAY_TIME)
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
            setTimeout(() => this.loading = false, process.env.VUE_DELAY_TIME)
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
    CodecellEditBegin(event) {

      // console.log('event===', event)
      // console.log('event.dataField===', event.dataField)
      //return false

      const grid = this.$refs.myGrid1;
      let rowIdField = event.dataField;
      let rowIndex = event.rowIndex;
      let row_status = grid.getCellValue(rowIndex, 'row_status')

      // console.log("row_status===", row_status)

      if (rowIdField == 'group_cd' && row_status != 'I') {
        return false
      }
      return true
    }, saveMaster() {
      const data = this.$getCudData(this.$refs.myGrid1,["group_cd", "code", "code_nm"])
      // if (data.add || data.update || data.remove) {
      //alert("저장 로직 작성하세요");
      console.log("data===", data)
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
        console.log(' this.$store.state.account.user.username===', this.$store.state.account.user.username)
        const data = this.$getCudData(this.$refs.myGrid2,["group_cd", "code", "code_nm"])
        // if (data.add || data.update || data.remove) {
        //alert("저장 로직 작성하세요");
        console.log("data===", data)
        if(data.length){
          saveCmCode(data).then(
              (res) => {
                console.log('res====', res)
                if (res.code == 200) {
                  this.searchDetail()
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

    }, downLoadExcel() {

      let grid = this.$refs.myGrid1;
      console.log('grid ==',grid)
      // 내보내기 실행
      grid.exportToXlsx({
        isRowStyleFront: false,
      });
      // if (grid == "grid1") {
      //   this.$refs.myGrid2.exportToTxt({})

      // } else {
      //   this.$refs.myGrid2.exportToTxt({})
      // }
    },
    onSearchAckey(){
      console.log('팝업 띄우는 쌤플')
      this.setModalstatus(true)
    }
  }
}

</script>

<style lang="less" scoped>
.search {
  margin-bottom: 54px;
}

.fold {
  width: calc(100% - 216px);
  display: inline-block
}

.operator {
  margin-bottom: 18px;
}

.left-text {
  text-align: left;
}

.right-text {
  text-align: right;
}

.my-right-column {
  text-align: right;
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