module.exports = {
  messages: {
    CN: {
      home: {name: '메인'},
    },
    US: {
      home: {name: 'home'},
      user: {name: '사용자관리',
        list: {name: '리스트'},
      }
    },
    KO: {
      workplace: {name: '워크플레이스'},
      user: {
        name: '사용자관리',
        user: {
          list: {name: '사용자 리스트'},
        },
      },
      system: {
        name: '시스템 관리',
          system: {
            users: {name: '사용자관리'},
            menus: {name: '메뉴관리'},
            menus_grid: {name: '메뉴관리_그리드'},
            roles: {name: 'ROLE관리'},
            logs: {name: '시스템로그'},
            commoncode: {name: '공통코드 관리'},
            commoncode_free: {name: '공통코드_무료그리드'},
            board: {name: '게시판관리'},
          },
      },
      master : {
        name : '마스터관리',
        master:{
          CustomerList: {name: '거래처 관리'},
          ItemList: {name: '품번 관리'},
          MstPlant: {name: '사업장 관리'},
          MstWh: {name: '창고 관리'},
          MstLc: {name: '로케이션 관리'},
        }
      },
      form: {
        name: 'FORM 샘플',
        form:{
          basic: {name: '기본 FORM'},
          step: {name: 'STEP FORM'},
          advance: {name: '다른 FORM'},
        }
      }
      ,list: {
        name: '리스트 샘플',
        list:{
          query: {name:'쿼리 리스트'},
          primary: {name:'Primary'},
          card: {name:'카드 리스트'},
        }
      },details: {
        name: '상세보기 샘플',
        details:{
          advance:{name:'샘플보기'}
        }
      },result: {
        name: '결과 페이지 샘플',
        result:{
          success:{name:'성공페이지'},
          error:{name:'에러페이지'},
        }
      },components: {
        name: '컴포넌트 샘플',
        components:{
          table: {name: '테이블'},
        }
      },
      users: {name: '사용자 관리'},
      setting: {name: '설정',
        base: {name: '기본 설정'},
        security: {name: '보안 설정'},
        custom: {name: '커스텀 설정'},
      },
      chart: {name: '차트 샘플',
        chart: {
          basicChart: {name: '베이직 차트'},
        },
      }
    }
  }
}
