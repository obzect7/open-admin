<template>
  <page-layout title="주문 번호: 234231029431" logo="https://gw.alipayobjects.com/zos/rmsportal/nxkuOJlFJuAUhzlMTCEe.png" :avatar="currUser.avatar">
    <detail-list size="small" :col="2" slot="headerContent">
      <detail-list-item term="주문자">홍길동</detail-list-item>
      <detail-list-item term="제품 주문">XX 서비스</detail-list-item>
      <detail-list-item term="생성 시간">2022-05-22</detail-list-item>
      <detail-list-item term="관련 문서"><a>12421</a></detail-list-item>
      <detail-list-item term="유효일">2018-08-07 ~ 2018-12-11</detail-list-item>
      <detail-list-item term="주목">영업일 기준 2일 이내에 확인하십시오.</detail-list-item>
    </detail-list>
    <template slot="extra">
      <head-info title="상태" content="보류 중" />
      <head-info title="주문량" content="568000" />
    </template>
    <template slot="action">
      <a-button-group style="margin-right: 8px;">
        <a-button>저장1</a-button>
        <a-button>저장2</a-button>
        <a-button><a-icon type="ellipsis"/></a-button>
      </a-button-group>
      <a-button type="primary" >주요 작업</a-button>
    </template>
    <a-card :bordered="false" title="프로세스 진행">
      <a-steps :current="1" progress-dot :direction="isMobile ? 'vertical' : 'horizontal'">
        <a-step title="프로젝트 생성">
          <a-step-item-group :align="isMobile ? 'left' : 'center'" slot="description">
            <a-step-item link="/workplace" title="홍길동" icon="dingding-o"/>
            <a-step-item title="2016-12-12 12:32"/>
          </a-step-item-group>
        </a-step>
        <a-step title="부서 예비 검토">
          <a-step-item-group :align="isMobile ? 'left' : 'center'" slot="description">
            <a-step-item link="/form/step" title="김철수" icon="dingding-o" />
            <a-step-item link="/result/success" title="첨언" icon="bell"/>
          </a-step-item-group>
        </a-step>
        <a-step title="재무 검토">
        </a-step>
        <a-step title="마감">
        </a-step>
      </a-steps>
    </a-card>
    <a-card style="margin-top: 24px" :bordered="false" title="사용자 정보">
      <detail-list>
        <detail-list-item term="사용자 이름">승리호</detail-list-item>
        <detail-list-item term="회원 카드 번호">32943898021309809423</detail-list-item>
        <detail-list-item term="주민번호">3321944288191034921</detail-list-item>
        <detail-list-item term="연락처">18112345678</detail-list-item>
        <detail-list-item term="주소">경남 창원시 성산구 11번지</detail-list-item>
      </detail-list>
      <detail-list title="정보 그룹">
        <detail-list-item term="이런저런 데이터">725</detail-list-item>
        <detail-list-item term="데이터 업데이트 시간">2022-05-08</detail-list-item>
        <detail-list-item >&nbsp;</detail-list-item>
        <detail-list-item term="이런저런 데이터">725</detail-list-item>
        <detail-list-item term="데이터 업데이트 시간">2022-05-08</detail-list-item>
        <detail-list-item >&nbsp;</detail-list-item>
      </detail-list>
      <a-card type="inner" title="계층 정보 그룹">
        <detail-list title="그룹 이름" size="small">
          <detail-list-item term="운영자">홍길동</detail-list-item>
          <detail-list-item term="역할 코드">1234567</detail-list-item>
          <detail-list-item term="부서">XX 회사 - YY 부서</detail-list-item>
          <detail-list-item term="만료">2022-05-08</detail-list-item>
          <detail-list-item term="비고">이 설명은 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다...</detail-list-item>
        </detail-list>
        <a-divider style="margin: 16px 0" />
        <detail-list title="그룹 이름" size="small" :col="1">
          <detail-list-item term="과학적인 이름">샤이니</detail-list-item>
          <detail-list-item term="역할 코드">1234567</detail-list-item>
          <detail-list-item term="부서">XX 회사 - YY 부서</detail-list-item>
          <detail-list-item term="만료">2022-05-08</detail-list-item>
          <detail-list-item term="비고">이 설명은 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다...</detail-list-item>
        </detail-list>
        <a-divider style="margin: 16px 0" />
        <detail-list title="그룹 이름" size="small" :col="2">
          <detail-list-item term="과학적인 이름">테스트</detail-list-item>
          <detail-list-item term="역할 코드">1234567</detail-list-item>
          <detail-list-item term="부서">XX 회사 - YY 부서</detail-list-item>
          <detail-list-item term="过期时间">2018-08-08</detail-list-item>
          <detail-list-item term="비고">이 설명은 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다 매우 길다...</detail-list-item>
        </detail-list>
      </a-card>
    </a-card>
    <a-card style="margin-top: 24px" :bordered="false" title="최근 6개월간 이용자의 통화기록">
      <a-list></a-list>
    </a-card>
    <a-card
      style="margin-top: 24px"
      :bordered="false"
      :tabList="tabList"
      :activeTabKey="activeTabKey"
      @tabChange="(key) => {this.activeTabKey = key}"
    >
      <a-table
        v-if="activeTabKey === '1'"
        :columns="operationColumns"
        :dataSource="operation1"
        :pagination="false"
      />
      <a-table
        v-if="activeTabKey === '2'"
        :columns="operationColumns"
        :dataSource="operation2"
        :pagination="false"
      />
      <a-table
        v-if="activeTabKey === '3'"
        :columns="operationColumns"
        :dataSource="operation3"
        :pagination="false"
      />
    </a-card>
  </page-layout>
</template>

<script>
import PageLayout from '@/layouts/PageLayout'
import DetailList from '@/components/tool/DetailList'
import AStepItem from '@/components/tool/AStepItem'
import {mapState} from 'vuex'
import HeadInfo from '@/components/tool/HeadInfo';

const operation1 = [
  {
    key: 'op1',
    type: '구독 관계가 적용됩니다.',
    name: '홍길동',
    status: 'agree',
    updatedAt: '2022-05-03  19:23:12',
    memo: '-'
  },
  {
    key: 'op2',
    type: '재무 검토',
    name: '김철수',
    status: 'reject',
    updatedAt: '2022-05-03  19:23:12',
    memo: '합격하지 못한 이유'
  },
  {
    key: 'op3',
    type: '부서 예비 검토',
    name: '승리호',
    status: 'agree',
    updatedAt: '2022-05-03  19:23:12',
    memo: '-'
  },
  {
    key: 'op4',
    type: '주문을 제출',
    name: '김테수',
    status: 'agree',
    updatedAt: '2022-05-03  19:23:12',
    memo: '메모를 테스트 합니다.'
  },
  {
    key: 'op5',
    type: '주문 생성',
    name: '권지용',
    status: 'agree',
    updatedAt: '2022-05-03  19:23:12',
    memo: '-'
  }
]

const operation2 = [
  {
    key: 'op2',
    type: '재무검토',
    name: '홍길동',
    status: 'reject',
    updatedAt: '2022-05-03  19:23:12',
    memo: '합격하지 못한 이유123123'
  },
  {
    key: 'op3',
    type: '부서예비검토',
    name: '나홍진',
    status: 'agree',
    updatedAt: '2022-05-03  19:23:12',
    memo: '-'
  },
  {
    key: 'op4',
    type: '주문을 제출',
    name: '김쿠쿠',
    status: 'agree',
    updatedAt: '2022-05-03  19:23:12',
    memo: '메모메모모모모모모모ㅗ'
  }
]

const operation3 = [
  {
    key: 'op2',
    type: '재무검토',
    name: '홍길동',
    status: 'reject',
    updatedAt: '2022-05-03  19:23:12',
    memo: '합격하지 못한 이유'
  },
  {
    key: 'op3',
    type: '부서예비검토',
    name: '나홍진',
    status: 'agree',
    updatedAt: '2022-05-03  19:23:12',
    memo: '-'
  }
]

const operationColumns = [
  {
    title: '작업 유형',
    dataIndex: 'type',
    key: 'type'
  },
  {
    title: '운영자',
    dataIndex: 'name',
    key: 'name'
  },
  {
    title: '결과',
    dataIndex: 'status',
    key: 'status'
  },
  {
    title: '가동시간',
    dataIndex: 'updatedAt',
    key: 'updatedAt'
  },
  {
    title: '메모',
    dataIndex: 'memo',
    key: 'memo'
  }
]


const DetailListItem = DetailList.Item
const AStepItemGroup = AStepItem.Group

const tabList = [
  {
    key: '1',
    tab: '작업 로그 1'
  },
  {
    key: '2',
    tab: '작업 로그 2'
  },
  {
    key: '3',
    tab: '작업 로그 3'
  }
]

export default {
  name: 'AdvancedDetail',
  components: {HeadInfo, AStepItemGroup, AStepItem, DetailListItem, DetailList, PageLayout},
  data () {
    return {
      tabList,
      operationColumns,
      operation1,
      operation2,
      operation3,
      activeTabKey: '2'
    }
  },
  computed: {
    ...mapState("account", { currUser: "user" }),
    ...mapState('setting', ['isMobile'])
  },
  methods: {
    onTabChange (key) {
      console.log(key)
    }
  },
}
</script>

<style lang="less" scoped>
</style>
