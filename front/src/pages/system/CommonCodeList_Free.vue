<template>
  <div :bordered="false" :style="{ minHeight: '560px' }">
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="48">



        </a-row>
      </a-form>
    </div>
    <div>
      <a-row>

        <a-col :md="10" :sm="24">
          <div>
            <a-button type="primary">추가</a-button>
            <a-button type="primary">삭제</a-button>
            <a-button type="primary">저장</a-button>
          </div>

          <ag-grid-vue
              style="width: 100%; height: 650px;"
              class="ag-theme-alpine"
              :defaultColDef="defaultColDef"
              :columnDefs="columnDefsHd"
              :rowData="rowDataHd"
          >
          </ag-grid-vue>
        </a-col>

        <a-col :md="14" :sm="24">
          <div>
            <a-button type="primary">추가</a-button>
            <a-button type="primary">삭제</a-button>
            <a-button type="primary">저장</a-button>
          </div>

          <ag-grid-vue
              style="width: 100%; height: 650px"
              class="ag-theme-alpine"
              :defaultColDef="defaultColDef"
              :columnDefs="columnDefsDt"
              :rowData="rowDataDt"
          >
          </ag-grid-vue>
        </a-col>
      </a-row>
    </div>
  </div>
</template>

<script>
import "ag-grid-community/dist/styles/ag-grid.css";
import "ag-grid-community/dist/styles/ag-theme-alpine.css";
import { AgGridVue } from "ag-grid-vue";

export default {
  name: "App",
  data() {
    return {
      defaultColDef:null,
      columnDefsHd: null,
      columnDefsDt: null,
      rowDataHd: null,
      rowDataDt: null,
    };
  },
  components: {
    AgGridVue,
  },
  beforeMount() {
    this.columnDefsHd = [
      { headerName:"그룹코드", field: "groupCd", width:120 },
      { headerName:"코드명", field: "groupNm" , width:120},
      { headerName:"사용여부", field: "useYn", width:120},
      { headerName:"비고", field: "rem", width:120},
    ];
    this.columnDefsDt = [
      { headerName:"코드", field: "make" },
      { headerName:"코드ID", field: "model" },
      { headerName:"코드ID", field: "price"},
    ];

    this.defaultColDef = {
      // set every column width
      width: 100,
      // make every column editable
      editable: true,
      // make every column use 'text' filter by default
      filter: 'agTextColumnFilter',
    };

    this.rowDataHd = [
      { groupCd: "DELYN", groupNm: "삭제여부", useYn: "Y", rem:"참고사항1" },
      { groupCd: "GENDER", groupNm: "성별", useYn: "Y", rem:"참고사항2" },
      { groupCd: "SOCIAL_CD", groupNm: "소셜구분", useYn: "Y", rem:"참고사항3" },
      { groupCd: "USERGB", groupNm: "사용자구분", useYn: "Y", rem:"참고사항4" },
      { groupCd: "USEYN", groupNm: "사용여부", useYn: "Y", rem:"참고사항5" },
    ];

    this.rowDataDt = [
      { make: "Toyota", model: "Celica", price: 35000 },
      { make: "Ford", model: "Mondeo", price: 32000 },
      { make: "Porsche", model: "Boxster", price: 72000 },
    ];
  },
};
</script>