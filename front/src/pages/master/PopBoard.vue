<template>
  <a-card :body-style="{padding: '5px'}" :bordered="false" >
    <a-form :form="form" id="PopBoard" layout="horizontal" >
      <div>
        <a-row >
          <a-col :md="24" :sm="24" >
            <a-form-item label="제목" :labelCol="{span: 2}" :wrapperCol="{span: 21, offset: 1}" :colon=false layout="inline">
              <a-input v-model="param.post_tit"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row >
          <a-col :md="12" :sm="24" >
            <a-form-item label="작성일시" :labelCol="{span: 5}" :wrapperCol="{span: 18, offset: 1}" :colon=false>
              <a-input v-model="param.reg_dt"
                       disabled
              />
            </a-form-item>
          </a-col>
          <a-col :md="12" :sm="24" >
            <a-form-item label="작성자" :labelCol="{span: 5}" :wrapperCol="{span: 18, offset: 1}" :colon=false>
              <a-input v-model="param.reg_nm"
                       disabled
              />
            </a-form-item>
          </a-col>
          <a-col :md="12" :sm="24" >
            <a-form-item label="게시기간" :labelCol="{span: 5}" :wrapperCol="{span: 18, offset: 1}" :colon=false>
              <a-range-picker
                  :value="[moment(param.post_sd), moment(param.post_ed)]"
                  style="width: 100%"
                  @change="postDtChange"
              />
            </a-form-item>
          </a-col>
          <a-col :md="12" :sm="24" >
            <a-form-item label="공지팝업여부" :labelCol="{span: 5}" :wrapperCol="{span: 18, offset: 1}" :colon=false>
              <a-checkbox v-model="param.noti_yn">
              </a-checkbox>
            </a-form-item>
          </a-col>
        </a-row>
          <a-col>
            <a-textarea name="remark"
                        v-model="param.post_cont"
            />
          </a-col>
        <a-row >
        </a-row>
      </div>

      <a-row>
        <a-col>
          <a-form-item style="margin-top: 24px" :wrapperCol="{span: 10, offset: 7}">
            <a-button type="primary" style="margin-left: 8px" @click="saveBoard" > <a-icon type="save" />저장</a-button>
            <a-button type="primary" style="margin-left: 8px" @click="deleteBoard" v-show="!popinit.isNew" > <a-icon type="delete" />삭제</a-button>
            <a-button type="primary" style="margin-left: 8px" @click="close" > <a-icon type="close" />닫기</a-button>
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
  </a-card>
</template>

<script>
import {saveBoard} from "@/services/board";
import moment from 'moment';

export default {
  name : "PopBoard",
  data () {
    return {
      param : {
        plant_cd: '',
        owner_cd: '',
        row_status: '',

        board_id: '',
        post_no: '',
        post_grp_no: '',
        post_grp_sn: '',
        post_lvl: '',
        post_tit: '',
        post_cont: '',
        noti_yn: 'true',
        reg_dt: '',
        reg_id: '',
        mod_id: '',
        mod_nm: '',
        reg_nm: '',
        post_sd: '',
        post_ed: '',
      },
      form: this.$form.createForm(this)
    }
  },
  props : {
    popinit: {}
  },
  created() {
    //수정
    if(this.popinit.isNew === false) {
      this.param = this.popinit;
      this.param.noti_yn = this.popinit.noti_yn == 'Y' ? true : false ;
      this.param.row_status = 'U';
    }
    //신규
    else if(this.popinit.isNew === true) {
      const date = new Date();
      this.param.post_sd = date.getFullYear() + '-' + (('0' + (date.getMonth() + 1)).slice(-2)) + '-' + '01';
      this.param.post_ed = date.getFullYear() + '-' + (('0' + (date.getMonth() + 1)).slice(-2)) + '-' + date.getDate();
      this.param.reg_dt  = date.getFullYear() + '-' + (('0' + (date.getMonth() + 1)).slice(-2)) + '-' + date.getDate()
                     + ' ' +  ('0' + date.getHours()).slice(-2) + ':' + ('0' + date.getMinutes()).slice(-2) + ':' + ('0' + date.getSeconds()).slice(-2);
      this.param.reg_id = this.$store.state.account.user.username
      this.param.reg_nm = this.$store.state.account.user.name
      this.param.mod_id = this.$store.state.account.user.username
      this.param.mod_nm = this.$store.state.account.user.name

      this.param.board_id = '1';
      this.param.post_grp_no = '1';
      this.param.post_grp_sn = '1';
      this.param.post_lvl = '1'

      this.param.row_status = 'I';
      this.param.noti_yn = true;
    }

  },
  mounted() {
  },
  computed: {
  },
  watch :{
  },
  methods : {
    moment,
    close(){
      this.$emit("closepop", '')
    },
    saveBoard(){
      this.form.validateFields((err) => {
        if(!err) {
          saveBoard(this.param).then(this.aftersaveuser)
        }
      })
    },
    deleteBoard(){
      this.param.row_status = 'D';
      saveBoard(this.param).then(this.aftersaveuser)
    },
    aftersaveuser(res) {
      if (res.code == '200') {
        if(this.param.row_status == 'I'){
          this.$message.success('저장 완료되었습니다.', 3)
          this.$emit("closepop", '')
        }else if(this.param.row_status == 'U'){
          this.$message.success('수정 완료되었습니다.', 3)
          this.$emit("closepop", '')
        }else if(this.param.row_status == 'D'){
          this.$message.success('삭제 완료되었습니다.', 3)
          this.$emit("closepop", '')
        }
      }
    },
    postDtChange(event, dateString){
      this.param.post_sd = dateString[0].replace(/-/g,'');
      this.param.post_ed = dateString[1].replace(/-/g,'');
    },
  },
}
</script>

<style>
.ant-row{
  margin-bottom: 0
}
</style>
