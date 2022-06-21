<template>
  <a-card :body-style="{padding: '5px'}" :bordered="false" >
    <a-form :form="form" id="PopBoard" layout="horizontal" >
      <div>
        <a-row >
          <a-col :md="12" :sm="24" >
            <a-form-item label="제목" :labelCol="{span: 5}" :wrapperCol="{span: 18, offset: 1}" :colon=false layout="inline">
              <a-input v-model="post_tit"/>
            </a-form-item>
          </a-col>
          <a-col :md="12" :sm="24" >
            <a-form-item label="작성일시" :labelCol="{span: 5}" :wrapperCol="{span: 18, offset: 1}" :colon=false>
              <a-input v-model="reg_dt"
                       :readonly="true"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row >
          <a-col :md="12" :sm="24" >
            <a-form-item label="작성자" :labelCol="{span: 5}" :wrapperCol="{span: 18, offset: 1}" :colon=false>
              <a-input v-model="reg_nm"
                       :readonly="true"/>
            </a-form-item>
          </a-col>
          <a-col :md="12" :sm="24" >
            <a-form-item label="게시기간" :labelCol="{span: 5}" :wrapperCol="{span: 18, offset: 1}" :colon=false>
              <a-range-picker/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row >
          <a-col :md="12" :sm="24" >
            <a-form-item label="공지팝업여부" :labelCol="{span: 5}" :wrapperCol="{span: 18, offset: 1}" :colon=false>
              <a-checkbox :checked="noti_yn"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row >
          <a-col>
              <a-textarea name="remark"
                          v-model="post_cont"
              />
          </a-col>
        </a-row>
      </div>

      <a-row>
        <a-col :md="24" :sm="24" >
          <a-form-item style="margin-top: 24px" :wrapperCol="{span: 10, offset: 7}">
            <a-button type="primary" style="margin-left: 8px" @click="saveBoard" > <a-icon type="save" />저장</a-button>
            <a-button type="primary" style="margin-left: 8px" @click="deleteItem" v-show="!popinit.isNew" > <a-icon type="delete" />삭제</a-button>
            <a-button type="primary" style="margin-left: 8px" @click="close" > <a-icon type="close" />닫기</a-button>
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
  </a-card>
</template>

<script>
import {saveItem} from "@/services/item";

export default {
  name : "PopBoard",
  data () {
    return {
      plant_cd : '',
      owner_cd : '',

      board_id : '',
      post_tit : '',
      post_cont : '',
      noti_yn : '',
      reg_dt : '',
      reg_id : '',
      reg_nm : '',
      form: this.$form.createForm(this)
    }
  },
  props : {
    popinit : {}
  },
  created() {
    console.log(this.popinit);

    //수정일시
    if(this.popinit.isNew === false) {
      this.post_tit = this.popinit.post_tit;
      this.post_cont = this.popinit.post_cont;
      this.reg_dt = this.popinit.reg_dt;
      this.reg_nm = this.popinit.reg_nm;
      this.reg_id = this.popinit.reg_id;
      this.noti_yn = this.popinit.noti_yn == 'Y' ? true : false;
    }
  },
  mounted() {
  },
  computed: {
  },
  watch :{
  },
  methods : {
    close(){
      this.$emit("closepop", '')
    },
    saveBoard(){
      console.log('saveItem')
      this.form.validateFields((err) => {

        if(!err) {

          let insUserid = this.$store.state.account.user.username;

          this.popinit.reg_id = insUserid;
          this.popinit.mod_id = insUserid;
          //console.log(this.popinit.isNew);

          if(this.popinit.isNew)
          {
            this.popinit.row_status = "I";
          }else
          {
            this.popinit.row_status = "U";
          }

          console.log('popinit.item_cd ==', this.popinit.item_cd);

          let data = [];
          data.push(this.popinit);
          console.log("popinit===", this.popinit);
          saveItem(data).then(this.aftersaveuser)
        }

      })

    },
    aftersaveuser(res) {

      if (res.code == '200') {

        this.$message.success('저장완료되었습니다.', 3)
        this.$emit("closepop", '')

      }
    },
    deleteItem(){
      //console.log('saveItem')
      let insUserid = this.$store.state.account.user.username;

      this.popinit.mod_id = insUserid;

      this.popinit.row_status = "D";
      let data = [];
      data.push(this.popinit);
      //console.log("popinit===", this.popinit);
      saveItem(data).then(this.aftersaveuser)
    },
  }

}
</script>

<style scoped>

</style>
