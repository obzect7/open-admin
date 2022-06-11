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
            <a-button type="primary" @click="masterAddRow"> <a-icon type="plus-square" />추가 </a-button>
            <a-button type="primary" @click="masterRemoveRow"> <a-icon type="delete" />삭제 </a-button>
            <a-button type="primary" @click="saveMaster"> <a-icon type="save" />저장 </a-button>
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
          <a-button type="primary" @click="detailAddRow"> <a-icon type="plus-square" />추가 </a-button>
          <a-button type="primary" @click="detailRemoveRow"> <a-icon type="delete" />삭제 </a-button>
          <a-button type="primary" @click="saveDetail"> <a-icon type="save" />저장 </a-button>
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
import {getCmCodeGrpList, getCmCodeList, saveCmCode, saveCmCodeGrp} from "@/services/commoncode";

const useYnList = []
export default {

  components: {
    AUIGrid
  },
  data: function () {
    return {
      loading: false,     //로딩바 유무
      delayTime: 1000,    //로딩 딜레이
      masterRow:{},       //그룹코드 정보
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
      gridDataDT: []

    }

  },

  beforeMount() {
    this.useYnList = [{"code": "Y", "value": "사용"}, {"code": "N", "value": "미사용"}]
  },
  mounted() {
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
          valueField: "value" // value 에 해당되는 필드명
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
          valueField: "value" // value 에 해당되는 필드명
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
    this.$refs.myGrid1.resize(null,1200)
    this.$refs.myGrid2.resize(null,1200)
  },
  methods: {
    searchMaster() {
      console.log('조회를 시작합니다.', this.queryParam);
      this.loading = true
      getCmCodeGrpList(Object.assign(this.queryParam)).then(
          (res) => {
            console.log('res====', res)
            this.$refs.myGrid1.setGridData(res.data);
            // 실제로 새로 고침은 매우 빠르며, 이 지연을 추가하는 것은 순전히 로딩 상태를 잠시 동안 표시하여 사용자가 새로 고침 과정을 인지할 수 있도록 하기 위한 것입니다.
            setTimeout(() => this.loading = false, 500)
            //return res.data;
          }
      )
    },searchDetail() {
      this.loading = true
      getCmCodeList(Object.assign(this.masterRow)).then(
          (res) => {
            console.log('res====@@@@@', res)
            this.$refs.myGrid2.setGridData(res.data);
            // this.loading = false
            setTimeout(() => this.loading = false, 500)
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
        let item = {use_yn: "Y", row_status: 'I',group_cd:master[0].group_cd};
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
      if (event.dataField == "group_cd" && event.item.row_status !="I") {
        this.masterRow = event.item
        this.searchDetail()
      }else{
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
      const isValid = this.$refs.myGrid1.validateChangedGridData(["group_cd", "group_nm", "use_yn"], "필수입력 입니다.");

      if (isValid) {
        console.log(' this.$store.state.account.user.username===', this.$store.state.account.user.username)
        const grid = this.$refs.myGrid1;
        // let items = grid.getCheckedRowItems();
        // console.log("items===", JSON.stringify(items))

        let addedRowItems = grid.getAddedRowItems(); // 추가된 행 아이템들(배열)
        let editedRowItems = grid.getEditedRowItems(); // 수정된 행 아이템들(배열) (수정되지 않은 칼럼들의 값도 가지고 있음)
        let removedRowItems = grid.getRemovedItems(); // 삭제된 행 아이템들(배열)

        // console.log("addedRowItems===", JSON.stringify(addedRowItems))
        // console.log("editedRowItems===", JSON.stringify(editedRowItems))
        // console.log("removedRowItems===", JSON.stringify(removedRowItems))

        let data = [];
        if (addedRowItems.length > 0) {
          for (let i = 0; i < addedRowItems.length; i++) {
            let addItem = addedRowItems[i]
            Object.assign(addItem, {['row_status']: 'I'})
            Object.assign(addItem, {['regId']: this.$store.state.account.user.username})
            Object.assign(addItem, {['modId']: this.$store.state.account.user.username})
            //console.log("editedItem==", editedItem)
            data.push(addItem)
          }
          //data.add = addedRowItems;
        }
        if (editedRowItems.length > 0) {
          for (let i = 0; i < editedRowItems.length; i++) {
            let editedItem = editedRowItems[i]
            Object.assign(editedItem, {['row_status']: 'U'})
            Object.assign(editedItem, {['modId']: this.$store.state.account.user.username})
            //console.log("editedItem==", editedItem)
            data.push(editedItem)
          }
          //data.update = editedRowItems;
        }
        if (removedRowItems.length > 0) {
          for (let i = 0; i < removedRowItems.length; i++) {
            let removeItem = removedRowItems[i]
            Object.assign(removeItem, {['row_status']: 'D'})
            //console.log("editedItem==", editedItem)
            data.push(removeItem)
          }
          //data.remove = removedRowItems;
        }
        // if (data.add || data.update || data.remove) {
        if (data.length > 0) {
          //alert("저장 로직 작성하세요");
          console.log("data===", data)
          saveCmCodeGrp(data).then(
              (res) => {
                console.log('res====', res)
                if (res.code == 200) {
                  this.searchMaster()
                } else {
                  this.$message.error(res.message);
                }
                //return res.data;
              }
          )
        } else {
          this.$message.warn('추가, 수정, 삭제된 행이 없습니다.', 3)
        }
      }

    }, saveDetail() {
      const isValid = this.$refs.myGrid2.validateChangedGridData(["group_cd", "code", "code_nm"], "필수입력 입니다.");

      if (isValid) {
        console.log(' this.$store.state.account.user.username===', this.$store.state.account.user.username)
        const grid = this.$refs.myGrid2;
        // let items = grid.getCheckedRowItems();
        // console.log("items===", JSON.stringify(items))

        let addedRowItems = grid.getAddedRowItems(); // 추가된 행 아이템들(배열)
        let editedRowItems = grid.getEditedRowItems(); // 수정된 행 아이템들(배열) (수정되지 않은 칼럼들의 값도 가지고 있음)
        let removedRowItems = grid.getRemovedItems(); // 삭제된 행 아이템들(배열)

        // console.log("addedRowItems===", JSON.stringify(addedRowItems))
        // console.log("editedRowItems===", JSON.stringify(editedRowItems))
        // console.log("removedRowItems===", JSON.stringify(removedRowItems))

        let data = [];
        if (addedRowItems.length > 0) {
          for (let i = 0; i < addedRowItems.length; i++) {
            let addItem = addedRowItems[i]
            Object.assign(addItem, {['row_status']: 'I'})
            Object.assign(addItem, {['regId']: this.$store.state.account.user.username})
            Object.assign(addItem, {['modId']: this.$store.state.account.user.username})
            //console.log("editedItem==", editedItem)
            data.push(addItem)
          }
          //data.add = addedRowItems;
        }
        if (editedRowItems.length > 0) {
          for (let i = 0; i < editedRowItems.length; i++) {
            let editedItem = editedRowItems[i]
            Object.assign(editedItem, {['row_status']: 'U'})
            Object.assign(editedItem, {['modId']: this.$store.state.account.user.username})
            //console.log("editedItem==", editedItem)
            data.push(editedItem)
          }
          //data.update = editedRowItems;
        }
        if (removedRowItems.length > 0) {
          for (let i = 0; i < removedRowItems.length; i++) {
            let removeItem = removedRowItems[i]
            Object.assign(removeItem, {['row_status']: 'D'})
            //console.log("editedItem==", editedItem)
            data.push(removeItem)
          }
          //data.remove = removedRowItems;
        }
        // if (data.add || data.update || data.remove) {
        if (data.length > 0) {
          //alert("저장 로직 작성하세요");
          console.log("data===", data)
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
                console.log('error ==== ',error)
              }
          )
        } else {
          this.$message.warn('추가, 수정, 삭제된 행이 없습니다.', 3)
        }
      }
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