import TabsView from "@/layouts/tabs/TabsView";
import BlankView from "@/layouts/BlankView";
import PageView from "@/layouts/PageView";

// 일반 라우팅 구성
export const commonRoutes = [
  {
    path: "/login",
    name: "로그인 페이지",
    component: () => import("@/pages/login"),
  },
  {
    path: "*",
    name: "404",
    component: () => import("@/pages/exception/404"),
  },
  {
    path: "/403",
    name: "403",
    component: () => import("@/pages/exception/403"),
  },
];

export const defaultSettingRoutes = [
  {
    path: 'setting',
    name: '개인 설정',
    meta: {
      invisible: true,
    },
    component: () => import("@/pages/setting/Index"),
    redirect: "/setting/base",
    children: [
      {
        path: 'base',
        name: '기본 설정',
        component: () => import("@/pages/setting/BaseSetting"),
      },
      {
        path: 'security',
        name: '보안 설정',
        component: () => import("@/pages/setting/Security"),
      },
      {
        path: 'custom',
        name: '개인화',
        component: () => import("@/pages/setting/Custom"),
      },
    ]
  }
];

// 프런트 엔드 라우팅 테이블
export const constantRouterComponents = {
  TabsView: TabsView,
  PageView: PageView,
  BlankView: BlankView,
  Login: () => import("@/pages/login"),
  "403": () => import(/* webpackChunkName: "error" */ "@/pages/exception/403"),
  "404": () => import(/* webpackChunkName: "error" */ "@/pages/exception/404"),
  Workplace: () => import("@/pages/workplace"),
  UserList: () => import("@/pages/user/UserList"),
  SystemUserList: () => import("@/pages/system/UserList"),
  MenuList: () => import("@/pages/system/MenuList"),
  RoleList: () => import("@/pages/system/RoleList"),
  LogList: () => import("@/pages/system/LogList"),
  FormBasic: () => import("@/pages/form/basic"),
  FormStep: () => import("@/pages/form/step"),
  FormAdvance: () => import("@/pages/form/advance"),
  QueryList: () => import("@/pages/list/QueryList"),
  StandardList: () => import("@/pages/list/StandardList"),
  CardList: () => import("@/pages/list/CardList"),
  AdvancedDetail: () => import("@/pages/detail/AdvancedDetail"),
  Success: () => import("@/pages/result/Success"),
  Error: () => import("@/pages/result/Error"),
  SeniorTable: () => import("@/pages/components/table"),
  AuthForm: () => import("@/pages/form/basic"),
  CommonCodeList: () => import("@/pages/system/CommonCodeList"),
};