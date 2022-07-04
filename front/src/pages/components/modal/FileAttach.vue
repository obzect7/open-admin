<template>
  <a-modal title="첨부파일 팝업" :visible="this.$store.state.modal.file_popup" :footer="null" @cancel="cancelEvent" :width="650"
           :dialogStyle="{height:50}" >
    <a-spin :spinning="loading" size="large">
      <a-upload
          name="file"
          :multiple="true"
          :action="fileactions"
          :headers="headers"
          @change="handleChange"
      >
        <a-button> <a-icon type="upload" /> 파일업로드 </a-button>
      </a-upload>
    </a-spin>
  </a-modal>
</template>

<script>
import {mapMutations, mapState} from "vuex";
import {getFileList} from "@/services/fileAttach";
import {getItemList} from "@/services/item";
import Cookie from "js-cookie";


export default {
  components: {
  },

  data() {
    return {
      headers: {
        authorization: Cookie.get('Authorization'),
      },
      fileactions: process.env.VUE_APP_API_BASE_URL+'/common/upload',
      loading: false,     //로딩바 유무
      queryParam: {},     // 쿼리 매개변수
      columnLayout: [],  //컬럼 레이아웃
      gridData: [],      // 그리드 데이터
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
    callType:null
  },

  async mounted() {

  },
  created() {
    // this.searchData()
  },
  computed: {
    ...mapState('modal', ['file_popup']),
  },
  watch: {
  },
  methods: {
    ...mapMutations('modal', ['setFile_popup']),
    handleChange(info) {
      if (info.file.status !== 'uploading') {
        console.log(info.file, info.fileList);
      }
      if (info.file.status === 'done') {
        this.$message.success(`${info.file.name} 파일업로드 성공`);
      } else if (info.file.status === 'error') {
        this.$message.error(`${info.file.name} 파일 업로드 실패.`);
      }
    },
    cancelEvent() {
      this.setFile_popup(false)
    },
    searchData() {

      this.loading = true
      return getFileList(Object.assign(this.queryParam)).then(
          (res) => {
            this.$refs.filePopupGrid.setGridData(res.data);
            setTimeout(() => this.loading = false, this.$gridDelayTime)
          }
      )
    },itemSelected(event){
      console.log('event ==',event)
      console.log('callType ==',this.callType)

      this.$emit("closepopItem", event.item)
      //this.$emit(this.parent.queryParam.ackey = 'aaaa')

    },
    handleFileChange(e) {
      this.file_name = e.target.files[0].name;
    }
  }
}
</script>

<style scoped>
.popupStyle {
  height: 100px;
}
</style>
