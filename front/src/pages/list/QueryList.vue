<template>
  <a-card>
    <div :class="advanced ? 'search' : null">
      <a-form layout="horizontal">
        <div :class="advanced ? null: 'fold'">
          <a-row >
          <a-col :md="8" :sm="24" >
            <a-form-item
              label="규칙 번호"
              :labelCol="{span: 5}"
              :wrapperCol="{span: 18, offset: 1}"
            >
              <a-input placeholder="입력하세요." />
            </a-form-item>
          </a-col>
          <a-col :md="8" :sm="24" >
            <a-form-item
              label="사용 상태"
              :labelCol="{span: 5}"
              :wrapperCol="{span: 18, offset: 1}"
            >
              <a-select placeholder="선택하세요.">
                <a-select-option value="1">신품</a-select-option>
                <a-select-option value="2">사용중</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
          <a-col :md="8" :sm="24" >
            <a-form-item
              label="통화 수"
              :labelCol="{span: 5}"
              :wrapperCol="{span: 18, offset: 1}"
            >
              <a-input-number style="width: 100%" placeholder="입력하세요." />
            </a-form-item>
          </a-col>
        </a-row>
          <a-row v-if="advanced">
          <a-col :md="8" :sm="24" >
            <a-form-item
              label="업데이트됨"
              :labelCol="{span: 5}"
              :wrapperCol="{span: 18, offset: 1}"
            >
              <a-date-picker style="width: 100%" placeholder="업데이트 날짜를 입력하세요." />
            </a-form-item>
          </a-col>
          <a-col :md="8" :sm="24" >
            <a-form-item
              label="사용 상태"
              :labelCol="{span: 5}"
              :wrapperCol="{span: 18, offset: 1}"
            >
              <a-select placeholder="선택하세요.">
                <a-select-option value="1">신품</a-select-option>
                <a-select-option value="2">사용중</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
          <a-col :md="8" :sm="24" >
            <a-form-item
              label="비고"
              :labelCol="{span: 5}"
              :wrapperCol="{span: 18, offset: 1}"
            >
              <a-input placeholder="입력하세요." />
            </a-form-item>
          </a-col>
        </a-row>
        </div>
        <span style="float: right; margin-top: 3px;">
          <a-button type="primary">조회</a-button>
          <a-button style="margin-left: 8px">초기화</a-button>
          <a @click="toggleAdvanced" style="margin-left: 8px">
            {{advanced ? '열기' : '닫기'}}
            <a-icon :type="advanced ? 'up' : 'down'" />
          </a>
        </span>
      </a-form>
    </div>
    <div>
      <a-space class="operator">
        <a-button @click="addNew" type="primary">추가</a-button>
        <a-button >대량 작업</a-button>
        <a-dropdown>
          <a-menu @click="handleMenuClick" slot="overlay">
            <a-menu-item key="delete">삭제</a-menu-item>
            <a-menu-item key="audit">승인</a-menu-item>
          </a-menu>
          <a-button>
            더 많은 액션 <a-icon type="down" />
          </a-button>
        </a-dropdown>
      </a-space>
      <standard-table
        :columns="columns"
        :dataSource="dataSource"
        :selectedRows.sync="selectedRows"
        @clear="onClear"
        @change="onChange"
        @selectedRowChange="onSelectChange"
      >
        <div slot="description" slot-scope="{text}">
          {{text}}
        </div>
        <div slot="action" slot-scope="{record}">
          <a style="margin-right: 8px">
            <a-icon type="plus"/>추가
          </a>
          <a style="margin-right: 8px">
            <a-icon type="edit"/>수정
          </a>
          <a @click="deleteRecord(record.key)" v-auth="`delete`">
            <a-icon type="delete" />삭제1
          </a>
        </div>
        <template slot="statusTitle">
          <a-icon @click.native="onStatusTitleClick" type="info-circle" />
        </template>
      </standard-table>
    </div>
  </a-card>
</template>

<script>
import StandardTable from '@/components/table/StandardTable'
const columns = [
  {
    title: '규칙 번호',
    dataIndex: 'no'
  },
  {
    title: '설명',
    dataIndex: 'description',
    scopedSlots: { customRender: 'description' }
  },
  {
    title: '서비스 호출 수',
    dataIndex: 'callNo',
    sorter: true,
    needTotal: true,
    customRender: (text) => text + ' 건'
  },
  {
    dataIndex: 'status',
    needTotal: true,
    slots: {title: 'statusTitle'}
  },
  {
    title: '업데이트 시간',
    dataIndex: 'updatedAt',
    sorter: true
  },
  {
    title: '액션',
    scopedSlots: { customRender: 'action' }
  }
]

const dataSource = []

for (let i = 0; i < 100; i++) {
  dataSource.push({
    key: i,
    no: 'NO ' + i,
    description: '설명을 합니다. 설명을 합니다.',
    callNo: Math.floor(Math.random() * 1000),
    status: Math.floor(Math.random() * 10) % 4,
    updatedAt: '2022-05-26'
  })
}

export default {
  name: 'QueryList',
  components: {StandardTable},
  data () {
    return {
      advanced: true,
      columns: columns,
      dataSource: dataSource,
      selectedRows: []
    }
  },
  authorize: {
    deleteRecord: 'delete',
  },
  methods: {
    deleteRecord(key) {
      this.dataSource = this.dataSource.filter(item => item.key !== key)
      this.selectedRows = this.selectedRows.filter(item => item.key !== key)
    },
    toggleAdvanced () {
      this.advanced = !this.advanced
    },
    remove () {
      this.dataSource = this.dataSource.filter(item => this.selectedRows.findIndex(row => row.key === item.key) === -1)
      this.selectedRows = []
    },
    onClear() {
      this.$message.info('모든 체크 라인을 지웠습니다')
    },
    onStatusTitleClick() {
      this.$message.info('상태 표시줄 헤더를 클릭했습니다.')
    },
    onChange() {
      this.$message.info('양식 상태가 변경됨')
    },
    onSelectChange() {
      this.$message.info('선택한 행이 변경됨')
    },
    addNew () {
      this.dataSource.unshift({
        key: this.dataSource.length,
        no: 'NO ' + this.dataSource.length,
        description: '설명설명설명설명44444 설명5555',
        callNo: Math.floor(Math.random() * 1000),
        status: Math.floor(Math.random() * 10) % 4,
        updatedAt: '2022-05-26'
      })
    },
    handleMenuClick (e) {
      if (e.key === 'delete') {
        this.remove()
      }
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
