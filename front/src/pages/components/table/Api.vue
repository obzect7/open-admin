<template>
  <div class="api">
    <div class="introduce">
      <h2 class="title">说明</h2>
      <p class="content">
        AdvanceTable은 Ant Design Vue Table 구성 요소 패키지를 기반으로 하며 모든 API를 지원합니다. <br/>
        테이블 표시의 동적 구성 및 검색 조건의 동적 구성이 필요한 일부 시나리오에서 사용할 수 있는 <em>열 설정</em> 및 <em>검색 제어 구성</em> 기능을 주로 추가했습니다. <br/>
        사용법은 기본적으로 antd 테이블과 동일합니다. 다음과 같이 일부 API를 추가했습니다.：
      </p>
    </div>
    <api-table :api-source="apiSource" />
    <api-table type="event" title="이벤트" :api-source="events" />
    <api-table title="Column" :api-source="columnApi" />
    <api-table title="Search" :api-source="searchApi" />
  </div>
</template>

<script>
  import ApiTable from '@/components/table/api/ApiTable'
  export default {
    name: 'Api',
    components: {ApiTable},
    data() {
      return {
        apiSource: [
          {
            key: 0,
            param: '<a href="https://www.antdv.com/components/table-cn/#API" target="_blank">Ant Design Vue Table API</a>',
            desc: 'Ant Design Vue Table 컴포넌트의 모든 API 지원',
            type: '--',
            default: '--',
          },
          {
            key: 1,
            param: 'title',
            desc: '테이블 제목',
            type: 'string | slot',
            default: '\'高级表格\''
          },
          {
            key: 2,
            param: 'formatConditions',
            desc: `검색 조건 값의 형식 지정 여부에 대한 형식 지정 규칙은 <a>검색 구성</a>을 참조하세요.。
                   <br/>false: 검색 입력 컨트롤의 원래 값을 가져옵니다. <br/>true: 검색 입력 컨트롤의 형식이 지정된 값을 가져옵니다.`,
            type: 'boolean',
            default: 'false',
          },
          {
            key: 3,
            param: 'columns',
            desc: `테이블 열 구성, <a>컬럼 구성</a> 참조`,
            type: 'array',
            default: '--',
          }
        ],
        events: [
          {
            key: 0,
            param: '<a href="https://www.antdv.com/components/table-cn/#API" target="_blank">Ant Design Vue Table Events API</a>',
            desc: 'Ant Design Vue Table의 모든 이벤트 지원',
            callback: '--',
          },
          {
            key: 1,
            param: 'search',
            desc: '검색 조건이 변경될 때 트리거됨',
            callback: 'Function(conditions, searchOptions: [{field, value, format}])',
          },
          {
            key: 2,
            param: 'refresh',
            desc: '헤더 새로 고침 아이콘을 클릭하면 트리거됩니다.',
            callback: 'Function(conditions, searchOptions: [{field, value, format}])',
          },
          {
            key: 3,
            param: 'reset',
            desc: '컬럼구성 재설정 버튼을 클릭하면 발생',
            callback: 'Function(conditions, searchOptions: [{field, value, format}])',
          },
        ],
        columnApi: [
          {
            key: 0,
            param: '<a href="https://www.antdv.com/components/table-cn/#API" target="_blank">Ant Design Vue Table Column API</a>',
            desc: '모든 API를 구성하는 Ant Design Vue Table 구성 요소 열 지원',
            type: '--',
            default: '--'
          },
          {
            key: 1,
            param: 'searchAble',
            desc: '컬럼검색 활성화 여부',
            type: 'boolean',
            default: 'false'
          },
          {
            key: 2,
            param: 'dataType',
            desc: `데이터 유형, 이 구성은 열 검색 입력 컨트롤의 유형을 결정하며 열 검색 입력 컨트롤과의 해당 관계는 다음과 같습니다.：<br/>
                   string: string<br/>
                   boolean: boolean<br/>
                   select: select<br/>
                   date: date<br/>
                   time: time<br/>
                   datetime: datetime`,
            type: `'string' | 'boolean' | 'select' | 'date' | 'time' | 'datetime'`,
            default: `'string'`
          },
          {
            key: 3,
            param: 'search',
            desc: '열 검색 구성, <a>검색 구성</a> 참조',
            type: 'object',
            default: '--'
          },
        ],
        searchApi: [
          {
            key: 0,
            param: 'format',
            desc: `列搜索输入控件值的格式化配置。<br/>如果输入控件支持格式化，则可设置该值为字符串，如日期输入组件，可设为为 'YYYY-MM-DD'。
                   <br/>不支持格式化的输入控件，可设置为一个接收控件的输入值作为参数的函数，如 (value) => {return \`prefix\${value}\`}。`,
            type: 'string | Function(value)',
            default: '取输入控件默认的格式化配置'
          },
          {
            key: 1,
            param: 'selectOptions',
            desc: `select 数据类型的下拉输入组件的选项配置，可参考 <a href="https://www.antdv.com/components/select-cn/#API" target="_blank">Ant Design Vue Select Option props Api</a>`,
            type: 'array',
            default: '--'
          }
        ],
      }
    }
  }
</script>

<style scoped lang="less">
.api{
  .introduce{
    padding: 16px;
    .content{
      em{
        margin: 0 4px;
        color: @primary-color;
      }
    }
  }
}
</style>