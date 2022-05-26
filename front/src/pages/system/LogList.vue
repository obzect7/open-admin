<template>
  <a-card :bordered="false" :style="{ minHeight: '560px' }">
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="48">
          <a-col :md="7" :sm="16">
            <a-form-item label="운영자">
              <a-select
                @change="change"
                v-model="queryParam.operationId"
                placeholder="선택하세요."
                default-value="0"
              >
                <a-select-option value>전체</a-select-option>
                <a-select-option
                  :key="index"
                  v-for="(item, index) in sysUserList"
                  :value="item.id"
                  >{{ item.name }}</a-select-option
                >
              </a-select>
            </a-form-item>
          </a-col>

          <a-col :md="14" :sm="24">
            <a-form-item label="검토시간">
              <a-form-item :style="{ display: 'inline-block' }">
                <a-date-picker
                  :format="dateFormat"
                  @change="change"
                  v-model="queryParam.startTime"
                  style="width: 100%"
                />
              </a-form-item>
              <span
                :style="{
                  display: 'inline-block',
                  width: '24px',
                  textAlign: 'center',
                }"
                >-</span
              >
              <a-form-item
                :style="{ display: 'inline-block', width: 'calc(50% - 12px)' }"
              >
                <a-date-picker
                  :format="dateFormat"
                  @change="change"
                  v-model="queryParam.endTime"
                  style="width: 100%"
                />
              </a-form-item>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>
    </div>

    <s-table
      ref="list"
      rowKey="id"
      size="default"
      :columns="columns"
      :data="loadData"
      :pageURI="true"
      :queryParam="queryParam"
    >
      <span slot="operationId" slot-scope="operationId">{{
        getOpertationName(operationId)
      }}</span>

      <span slot="url" slot-scope="url">
        <a-tag>{{ url }}</a-tag>
      </span>

      <span slot="browser" slot-scope="browser">
        <a-tag>{{ browser }}</a-tag>
      </span>

      <span slot="params" slot-scope="params" :title="params">
        <span :title="params">{{ params | slice }}</span>
      </span>
    </s-table>
  </a-card>
</template>

<script>
import moment from "moment";
import STable from "@/components/table";
import { getSysUserList, getLogList } from "@/services/system";

export default {
  name: "LogList",
  components: {
    STable,
  },
  data() {
    return {
      // 쿼리 매개변수
      queryParam: {},
      modelTitle: "작업 로그",
      visible: false,
      labelCol: {
        xs: { span: 24 },
        sm: { span: 5 },
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 16 },
      },
      sysUserList: [],
      dateFormat: "YYYY-MM-DD",
      // 헤더
      columns: [
        {
          title: "Id",
          dataIndex: "id",
        },
        {
          title: "운영자",
          dataIndex: "operationId",
          scopedSlots: { customRender: "operationId" },
        },
        {
          title: "요청 URL",
          dataIndex: "url",
          scopedSlots: { customRender: "url" },
        },
        {
          title: "요청 IP",
          dataIndex: "ip",
        },
        {
          title: "브라우저",
          dataIndex: "browser",
        },
        {
          title: "파라미터",
          dataIndex: "params",
          width: 380,
          scopedSlots: { customRender: "params" },
        },
        {
          title: "실행 시간(ms)",
          dataIndex: "takeTime",
        },
        {
          title: "요청 시간",
          dataIndex: "createdTime",
          key: "createdTime",
        },
      ],
      loadData: (parameter) => {
        return getLogList(Object.assign(this.queryParam, parameter)).then(
          (res) => {
            return res.data;
          }
        );
      },
    };
  },
  created() {
    this.getSysUserList();
  },
  filters: {
    slice(text) {
      if (text && text.length > 118) {
        return text.slice(0, 118) + "...";
      } else {
        return text;
      }
    },
  },
  methods: {
    change() {
      this.$refs.list.refresh();
    },
    getSysUserList() {
      getSysUserList().then((res) => {
        this.sysUserList = res.data;
      });
    },
    getOpertationName(operationId) {
      let res = this.sysUserList.find((item) => {
        return item.id == operationId;
      });
      if (res) {
        return res.name;
      } else {
        return "";
      }
    },
  },
};
</script>
