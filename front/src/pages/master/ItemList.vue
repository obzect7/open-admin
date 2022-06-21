<template>
  <a-spin :spinning="loading" size="large">
<!--    <div >-->
<!--      <PopItem v-if="isPopUp" @closepop="closePopItem" :popinit="this.popinit" />-->
<!--    </div>-->
    <a-modal
        :title="'품번등록'"
        style="top: 5px"
        :width="1100"
        v-model="isPopUp"
        :footer="null"
    >
      <PopItem v-if="isPopUp" @closepop="closePopItem" :popinit="this.popinit" />
    </a-modal>
    <div :bordered="false" :style="{ minHeight: '800px' }"  v-show="!isPopUp">
      <div >
        <a-form layout="horizontal" >
          <div >
            <a-row >
              <a-col :md="7" :sm="24" >
                <a-form-item
                    label="품번"
                    :labelCol="{span: 5}"
                    :wrapperCol="{span: 18, offset: 1}"
                >
                  <a-input v-model="queryParam.item_cd" placeholder="입력하세요." />
                </a-form-item>
              </a-col>
              <a-col :md="7" :sm="24" >
                <a-form-item
                    label="품명"
                    :labelCol="{span: 5}"
                    :wrapperCol="{span: 18, offset: 1}"
                >
                  <a-input v-model="queryParam.item_nm" placeholder="입력하세요." />
                </a-form-item>
              </a-col>
              <a-col :md="7" :sm="24" >
                <a-form-item
                    label="사용여부"
                    :labelCol="{span: 5}"
                    :wrapperCol="{span: 18, offset: 1}"
                >
                  <a-select v-model="queryParam.use_yn" placeholder="선택하세요.">
                    <a-select-option :key="item.code" :value="item.code" v-for="(item, index) in useYnList">{{item.code_nm}}</a-select-option>
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
          <div>
            <a-button-group>
              <a-button type="primary" @click="addItem"> <a-icon type="plus-square" />추가 </a-button>
            </a-button-group>
          </div>

          <a-col :md="24" :sm="24">
            <AUIGrid ref="itemGrid" class="grid-wrap"
                     @cellDoubleClick="cellDoubleClickHandler"
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
import {getItemList} from "@/services/item";
import PopItem from "@/pages/master/PopItem";
import {getCmCodeLoad} from "@/services/commoncode";
const useYnList= []

export default {

  components : {
    PopItem,
    AUIGrid
  },
  data: function () {
    return {
      loading: false,     //로딩바 유무
      isPopUp : false,    //팝업호출여부
      useYnList,

      popinit : {

        plant_cd : '10000',
        owner_cd : '10000',
        item_cd : '',
        item_nm : '',
        spec : '',
        unit : '',
        class1 : '',
        class2 : '',
        class3 : '',
        boxqty : '',
        pltqty : '',
        in_price1 : '',
        out_price1 : '',
        lot_yn : '',
        fifo_yn : '',
        appro_inv : '',
        in_wh_cd : '',
        in_lc_cd : '',
        remark : '',
        use_yn : '',
        reg_id : '',
        reg_dt : '',
        mod_id : '',
        mod_dt : '',
        isNew : null,
      },
      // 쿼리 매개변수
      queryParam: {use_yn : "Y"},

      columnLayout : [],

      // 그리드 속성 정의
      auigridProps : {
        // 편집 가능 여부 (기본값 : false)
        editable : false,

        // 엑스트라 체크박스 표시 설정
        showRowCheckColumn : false,

        // 전체 체크박스 표시 설정
        showRowAllCheckBox : false,

        // 셀 선택모드 (기본값: singleCell)
        selectionMode : "multipleCells",
        showStateColumn : false,
      },
      // 그리드 데이터
      gridData : []
    }
  },
  beforeMount() {

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


    let grid = this.$refs.itemGrid;

    // 그리드 생성

    grid.create(this.columnLayout, this.auigridProps);


    // 그리드 데이터 삽입하기
    grid.setGridData(this.gridData);
  },
  methods : {
    search(){
      console.log('조회를 시작합니다.',this.queryParam);
      this.loading = true
      return getItemList(Object.assign(this.queryParam)).then(

          (res) => {
            console.log('res====',res)
            this.$refs.itemGrid.setGridData(res.data);
            //return res.data;
            setTimeout(() => this.loading = false, process.env.VUE_DELAY_TIME)
          }
      )
    },
    cellDoubleClickHandler(event){

      console.log("event.item===", event.item)
      //this.popinit = event.item;
      //console.log("popinit===", this.popinit)
      let POpParam = event.item;
      Object.assign(POpParam, {['isNew']: false})

      //console.log("POpParam===", POpParam)

      this.openPopItem(POpParam)
      this.isPopUp = true
    },
    openPopItem(param){
      // this.popinit = {};
      this.popinit = param;

      console.log("param===", param)

      this.isPopUp = true
    },
    closePopItem(){
      //console.log('sssss')
      this.isPopUp = false
      this.search()
      //this.$router.go()
    },
    addItem(){

      const param =  {
            plant_cd : '10000',
            owner_cd : '10000',
            item_cd : '',
            item_nm : '',
            spec : '',
            unit : '',
            class1 : '',
            class2 : '',
            class3 : '',
            boxqty : '0',
            pltqty : '0',
            in_price1 : '0',
            out_price1 : '0',
            lot_yn : 'N',
            fifo_yn : 'N',
            appro_inv : '0',
            in_wh_cd : '',
            in_lc_cd : '',
            remark : '',
            use_yn : 'Y',
            isNew : true,
      }

      this.openPopItem(param)
      //this.popinit = param;
      //this.isPopUp = true
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