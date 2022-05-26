<template>
  <div>
    <a-card :bordered="false">
      <div style="display: flex; flex-wrap: wrap">
          <head-info title="내 할 일" content="8개의 미션" :bordered="true"/>
          <head-info title="이번 주 작업의 평균 처리 시간" content="32분" :bordered="true"/>
          <head-info title="이번 주에 완료된 작업 수" content="24"/>
      </div>
    </a-card>
    <a-card
      style="margin-top: 24px"
      :bordered="false"
      title="표준 목록"
    >
      <div slot="extra">
        <a-radio-group>
          <a-radio-button>전체</a-radio-button>
          <a-radio-button>진행중</a-radio-button>
          <a-radio-button>대기중</a-radio-button>
        </a-radio-group>
        <a-input-search style="margin-left: 16px; width: 272px;" />
      </div>
      <a-button type="dashed" style="width: 100%" icon="plus">추가</a-button>
      <a-list size="large" :pagination="{showSizeChanger: true, showQuickJumper: true, pageSize: 5, total: 50}">
        <a-list-item :key="i" v-for="i in 5">
          <a-list-item-meta
            description="손이 닿지 않는 안에 있는 것, 손이 닿지 않는 곳에 있는 것"
          >
            <a-avatar slot="avatar" size="large" shape="square" src="https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png"/>
            <a slot="title">AliPay</a>
          </a-list-item-meta>
          <div slot="actions">
            <a @click="editRecord()">수정</a>
          </div>
          <div slot="actions">
            <a-dropdown>
              <a-menu slot="overlay">
                <a-menu-item><a>수정</a></a-menu-item>
                <a-menu-item><a>삭제</a></a-menu-item>
              </a-menu>
              <a>더보기<a-icon type="down"/></a>
            </a-dropdown>
          </div>
          <div class="list-content">
            <div class="list-content-item">
              <span>Owner</span>
              <p>홍길동</p>
            </div>
            <div class="list-content-item">
              <span>시작 시간</span>
              <p>2018-07-26 22:44</p>
            </div>
            <div class="list-content-item">
              <a-progress :percent="80" style="width: 180px" />
            </div>
          </div>
        </a-list-item>
      </a-list>
    </a-card>
  </div>
</template>

<script>
import HeadInfo from '../../components/tool/HeadInfo'
export default {
  name: 'StandardList',
  components: {HeadInfo},
  methods: {
    editRecord(key) {
    }
  }
}
</script>

<style lang="less" scoped>
  .list-content-item{
    color: @text-color-second;
    display: inline-block;
    vertical-align: middle;
    font-size: 14px;
    margin-left: 40px;
    span{
      line-height: 20px;
    }
    p{
      margin: 4px 0 0;
      line-height: 22px;
    }
  }
</style>
