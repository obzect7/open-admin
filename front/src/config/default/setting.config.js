// 이 구성은 시스템의 기본 설정이며, 설정 항목을 수정하려면 src/config/config.js에 수정된 항목을 추가하면 됩니다. 이 파일에서 직접 수정할 수도 있습니다.
module.exports = {
  lang: "us", //언어, KR(한국어), CN(간체), HK(번체), 기타 언어도 확장 가능
  theme: {
    //主题
    color: "#1890ff", //테마 색상
    mode: "dark", //테마 모드 dark、 light , night
    success: "#52c41a", //성공 색상
    warning: "#faad14", //경고 색상
    error: "#f5222f", //잘못된 색상
  },
  layout: "side", //네비게이션 레이아웃， side , head
  fixedHeader: false, //고정 헤더 상태 표시줄，true:고정，false:비고정
  fixedSideBar: true, //고정 사이드바，true:고정，false:비고정
  fixedTabs: false, //고정 탭 헤더，true:고정，false:비고정
  pageWidth: "fixed", //内容区域宽度，fixed:固定宽度，fluid:流式宽度
  weekMode: false, //色弱模式，true:开启，false:不开启
  multiPage: false, //多页签模式，true:开启，false:不开启
  cachePage: true, //是否缓存页面数据，仅多页签模式下生效，true 缓存, false 不缓存
  hideSetting: false, //隐藏设置抽屉，true:隐藏，false:不隐藏
  systemName: "Open Admin", //系统名称
  copyright: "2021 Open Admin 出品", //copyright
  showPageTitle: true, //是否显示页面标题（PageLayout 布局中的页面标题），true:显示，false:不显示
  filterMenu: true, //根据权限过滤菜单，true:过滤，false:不过滤
  animate: {
    //动画设置
    disabled: false, //禁用动画，true:禁用，false:启用
    name: "bounce", //动画效果，支持的动画效果可参考 ./animate.config.js
    direction: "left", //动画方向，切换页面时动画的方向，参考 ./animate.config.js
  },
  footerLinks: [
    //页面底部链接，{link: '链接地址', name: '名称/显示文字', icon: '图标，支持 ant design vue 图标库'}
    {
      link: "https://github.com/cxp1539/open-admin",
      icon: "github"
    },
  ],
};