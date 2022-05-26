<template>
  <div class="card-list">
    <a-list
      :grid="{gutter: 24, lg: 3, md: 2, sm: 1, xs: 1}"
      :dataSource="dataSource"
    >
      <a-list-item slot="renderItem" slot-scope="item">
        <template v-if="item.add">
          <a-button class="new-btn" type="dashed">
            <a-icon type="plus" />제품 추가
          </a-button>
        </template>
        <template v-else>
          <a-card :hoverable="true">
            <a-card-meta >
              <div style="margin-bottom: 3px" slot="title">{{item.title}}</div>
              <a-avatar class="card-avatar" slot="avatar" :src="item.avatar" size="large" />
              <div class="meta-content" slot="description">{{item.content}}</div>
            </a-card-meta>
            <a slot="actions">작업 하나</a>
            <a slot="actions">작업 하나</a>
          </a-card>
        </template>
      </a-list-item>
    </a-list>
  </div>
</template>

<script>
const dataSource = []
dataSource.push({
  add: true
})
for (let i = 0; i < 11; i++) {
  dataSource.push({
    title: 'Alipay',
    avatar: 'https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png',
    content: '중급 제품의 연구 개발 과정에서 설계 사양과 구현 방법이 다르지만 유사한 페이지와 구성 요소가 많이 있으며 이러한 유사한 구성 요소는 표준 사양 집합으로 추출됩니다.。'
  })
}

export default {
  name: 'CardList',
  data () {
    return {
      desc: '단락: Ant Financial의 서비스 디자인 플랫폼인 Ant.design은 최소한의 워크로드로 Ant Financial 생태계에 원활하게 액세스하고 설계 및 개발에 걸친 경험 솔루션을 제공합니다.',
      linkList: [
        {icon: 'rocket', href: '/#/', title: '快速开始'},
        {icon: 'info-circle-o', href: '/#/', title: '产品简介'},
        {icon: 'file-text', href: '/#/', title: '产品文档'}
      ],
      extraImage: 'https://gw.alipayobjects.com/zos/rmsportal/RzwpdLnhmvDJToTdfDPe.png',
      dataSource
    }
  }
}
</script>

<style lang="less" scoped>
  .card-avatar {
    width: 48px;
    height: 48px;
    border-radius: 48px;
  }
  .new-btn{
    border-radius: 2px;
    width: 100%;
    height: 187px;
  }
  .meta-content{
    position: relative;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    height: 64px;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
  }

</style>
