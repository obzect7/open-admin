<template>
  <a-card :body-style="{padding: '24px 32px'}" :bordered="false" >
    <a-form :form="form" id="PopItemfrm" layout="horizontal" >
      <div>
        <a-row >
          <a-col :md="12" :sm="24" >
            <a-form-item
                label="품번"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}"
            >
              <a-input name="item_cd"
                       :readonly="!popinit.isNew"
                       :v-model="popinit.item_cd"
                       v-decorator="['item_cd', {initialValue: popinit.item_cd,rules: [{ required: true, message: '품번을 입력하세요.', whitespace: true}]}]"
              />
            </a-form-item>
          </a-col>

          <a-col :md="12" :sm="24" >
            <a-form-item
                label="품명"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}"
            >
              <a-input name="item_nm"
                       :v-model="popinit.item_nm"
                       v-decorator="['item_nm', {initialValue: popinit.item_nm,rules: [{ required: true, message: '품명을 입력하세요.', whitespace: true}]}]"
              />
            </a-form-item>
          </a-col>
        </a-row>

        <a-row >
          <a-col :md="12" :sm="24" >
            <a-form-item
                label="규격"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}"
            >

              <a-input name="spec"
                       :v-model="popinit.spec"
                       v-decorator="['spec', {initialValue: popinit.spec,rules: [{ required: false, whitespace: true}]}]"
              />
            </a-form-item>
          </a-col>

          <a-col :md="12" :sm="24" >
            <a-form-item
                label="단위"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}"
            >

              <a-select v-model="popinit.unit" name="unit" placeholder="선택하세요.">
                <a-select-option :value="'EA'" >EA</a-select-option>
                <a-select-option :value="'BOX'" >BOX</a-select-option>
                <a-select-option :value="'PLT'" >PLT</a-select-option>
                <a-select-option :value="'KG'" >KG</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
        </a-row>

        <a-row >
          <a-col :md="12" :sm="24" >
            <a-form-item
                label="구분1"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}"
            >
              <a-select v-model="popinit.class1" name="class1" placeholder="선택하세요.">
                <a-select-option :value="'class11'" >대구분1</a-select-option>
                <a-select-option :value="'class12'" >대구분2</a-select-option>
                <a-select-option :value="'class13'" >대구분3</a-select-option>
                <a-select-option :value="'class14'" >대구분4</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>

          <a-col :md="12" :sm="24" >
            <a-form-item
                label="구분2"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}"
            >
              <a-select v-model="popinit.class2" name="class2" placeholder="선택하세요.">
                <a-select-option :value="'class21'" >중구분1</a-select-option>
                <a-select-option :value="'class22'" >중구분2</a-select-option>
                <a-select-option :value="'class23'" >중구분3</a-select-option>
                <a-select-option :value="'class24'" >중구분4</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
        </a-row>

        <a-row >
          <a-col :md="12" :sm="24" >
            <a-form-item
                label="구분3"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}"
            >
              <a-select v-model="popinit.class3" name="class3" placeholder="선택하세요.">
                <a-select-option :value="'class31'" >소구분1</a-select-option>
                <a-select-option :value="'class32'" >소구분2</a-select-option>
                <a-select-option :value="'class33'" >소구분3</a-select-option>
                <a-select-option :value="'class34'" >소구분4</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>


          <a-col :md="12" :sm="24" >
            <a-form-item
                label="적정재고"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}"
            >
              <a-input name="appro_inv"
                       type="number"
                       :v-model="popinit.appro_inv"
                       v-decorator="['적정재고', {initialValue: popinit.appro_inv,rules: [{ required: true, whitespace: true}]}]"
              />
            </a-form-item>
          </a-col>
        </a-row>

        <a-row >
          <a-col :md="12" :sm="24" >
            <a-form-item
                label="팔렛트당수량"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}"
            >
              <a-input name="pltqty"
                       type="number"
                       :v-model="popinit.pltqty"
                       v-decorator="['팔렛트당수량', {initialValue: popinit.pltqty,rules: [{ required: true, whitespace: true}]}]"
              />
            </a-form-item>
          </a-col>

          <a-col :md="12" :sm="24" >
            <a-form-item
                label="박스당수량"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}"
            >
              <a-input name="boxqty"
                       type="number"
                       :v-model="popinit.boxqty"
                       v-decorator="['박스당수량', {initialValue: popinit.boxqty,rules: [{ required: true, whitespace: true}]}]"
              />
            </a-form-item>
          </a-col>
        </a-row>

        <a-row >
          <a-col :md="12" :sm="24" >
            <a-form-item
                label="입고가"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}"
            >

              <a-input name="in_price1"
                       type="number"
                       :v-model="popinit.in_price1"
                       v-decorator="['입고가', {initialValue: popinit.in_price1,rules: [{ required: true, whitespace: true}]}]"
              />
            </a-form-item>
          </a-col>

          <a-col :md="12" :sm="24" >
            <a-form-item
                label="출고가"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}"
            >

              <a-input name="out_price1"
                       type="number"
                       :v-model="popinit.out_price1"
                       v-decorator="['출고가', {initialValue: popinit.out_price1,rules: [{ required: true, whitespace: true}]}]"
              />
            </a-form-item>
          </a-col>
        </a-row>

        <a-row >
          <a-col :md="12" :sm="24" >
            <a-form-item
                label="LOT관리여부"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}"
            >
              <a-select v-model="popinit.lot_yn" name="lot_yn" placeholder="선택하세요.">
                <a-select-option :value="'Y'" >관리</a-select-option>
                <a-select-option :value="'N'" >미관리</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>

          <a-col :md="12" :sm="24" >
            <a-form-item
                label="선입선출여부"
               :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}"
            >
              <a-select v-model="popinit.fifo_yn" name="fifo_yn" placeholder="선택하세요.">
                <a-select-option :value="'Y'" >관리</a-select-option>
                <a-select-option :value="'N'" >미관리</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
        </a-row>

        <a-row >
          <a-col :md="12" :sm="24" >
            <a-form-item
                label="입고창고"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}"
            >
              <a-input name="in_wh_cd"
                       v-decorator="['in_wh_cd', {initialValue: popinit.in_wh_cd,rules: [{ required: false, whitespace: true}]}]"
              />
            </a-form-item>
          </a-col>

          <a-col :md="12" :sm="24" >
            <a-form-item
                label="입고로케이션"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}"
            >
              <a-input name="in_lc_cd"
                       v-decorator="['in_lc_cd', {initialValue: popinit.in_lc_cd,rules: [{ required: false, whitespace: true}]}]"
              />
            </a-form-item>
          </a-col>
        </a-row>

        <a-row >
          <a-col :md="12" :sm="24" >
            <a-form-item
                label="사용여부"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}"
            >
              <a-select v-model="popinit.use_yn" name="use_yn" placeholder="선택하세요.">
                <a-select-option :value="'Y'" >사용</a-select-option>
                <a-select-option :value="'N'" >미사용</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
        </a-row>

        <a-row >
          <a-col :md="12" :sm="24" >
            <a-form-item
                label="비고사항"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}"
            >
              <a-textarea name="remark"
                       v-decorator="['remark', {initialValue: popinit.remark,rules: [{ required: false, whitespace: true}]}]"
              />
            </a-form-item>
          </a-col>


        </a-row>

      </div>
      <a-row>
        <a-col :md="24" :sm="24" >
          <a-form-item style="margin-top: 24px" :wrapperCol="{span: 10, offset: 7}">
            <a-button type="primary" style="margin-left: 8px" @click="saveItem" > <a-icon type="save" />저장</a-button>
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

  data () {
    return {

      plant_cd : '',
      owner_cd : '',
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
      form: this.$form.createForm(this)
    }
  },
  props : {
    popinit : {
      plant_cd : '',
      owner_cd : '',
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
      isNew : null,

    }
  },
  created() {
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
    saveItem(){
      console.log('saveItem')
      this.form.validateFields((err) => {

        if(!err) {

          // let myForm = document.getElementById('PopItemfrm');
          // let formData = new FormData(myForm);
          // const param = {};
          // // need to convert it before using not with XMLHttpRequest
          // for (let [key, val] of formData.entries()) {
          //   Object.assign(param, {[key]: val})
          // }
          //
          // let insUserid = this.$store.state.account.user.username;
          // Object.assign(param, {['reg_id']: insUserid})
          // Object.assign(param, {['mod_id']: insUserid})
          //
          // if(this.popinit.isNew)
          // {
          //   Object.assign(param, {['row_status']: 'I'})
          // }else
          // {
          //   Object.assign(param, {['row_status']: 'U'})
          // }



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

           let data = [];
           data.push(this.popinit);
          //console.log("popinit===", this.popinit);
          saveItem(data).then(this.aftersaveuser)
        }

      })

    },
    aftersaveuser(res) {
      //console.log('res==', res)
      const loginRes = res.data
      if (loginRes.code == '200') {

        this.$message.success('저장완료되었습니다.', 3)
        this.$emit("closepop", '')

      }
    },
    deleteUser(){
      //console.log('deleteUser')
      //console.log('userid', this.userid)

      const data = {};
      let myForm = document.getElementById('PopItemfrm');
      let formData = new FormData(myForm);
      for (let [key, val] of formData.entries()) {
        Object.assign(data, {[key]: val})
      }

      Object.assign(data, {['rowStatus']: 'D'})
      // console.log('data', data)
      //(data).then(this.aftersaveuser)

    },
    deleteItem(){
      console.log('saveItem')
      this.form.validateFields((err) => {

        if(!err) {

          // let myForm = document.getElementById('PopItemfrm');
          // let formData = new FormData(myForm);
          // const data = {};
          // // need to convert it before using not with XMLHttpRequest
          // for (let [key, val] of formData.entries()) {
          //   Object.assign(data, {[key]: val})
          // }



          let insUserid = this.$store.state.account.user.username;

          this.popinit.reg_id = insUserid;
          this.popinit.mod_id = insUserid;
          this.popinit.row_status = "D";


          // Object.assign(data, {['reg_id']: insUserid})
          // Object.assign(data, {['mod_id']: insUserid})
          // Object.assign(data, {['row_status']: 'I'})

          //console.log("popinit===", this.popinit);
          let data = [];
          data.push(this.popinit);

          console.log("data===", data);

          //saveItem(data).then(this.aftersaveuser)
        }

      })

    },
  }

}
</script>

<style scoped>

</style>
