export default {
  namespaced: true,
  state: {
    item_popup: false,
    file_popup: false,
    file_grp_seq: '',
    cust_popup: false,
    wh_popup: false,
    lc_popup: false,
    selectedrow:{},
    selectedrows:[]
  },
  getters: {
    item_popup: (state) => {
      return state.item_popup;
    }, file_popup: (state) => {
      return state.file_popup;
    }
    ,file_grp_seq: (state) => {
      return state.file_grp_seq;
    },
    cust_popup: (state) => {
      return state.cust_popup;
    },
    wh_popup: (state) => {
      return state.wh_popup;
    },
    lc_popup: (state) => {
      return state.lc_popup;
    },
    selectedrow: (state) => {
      return state.selectedrow;
    },
    selectedrows: (state) => {
      return state.selectedrows;

    },
  },
  mutations: {
    setItem_popup(state, item_popup) {
      state.item_popup = item_popup;
      // localStorage.setItem(process.env.VUE_APP_USER_KEY, JSON.stringify(user));
    },
    setFile_popup(state, file_popup) {
      state.file_popup = file_popup;
    },
    setFile_grp_seq(state, file_grp_seq) {
      state.file_grp_seq = file_grp_seq;
    },
    setCust_popup(state, cust_popup) {
      state.cust_popup = cust_popup;
    },
    setWh_popup(state, wh_popup) {
      state.wh_popup = wh_popup;
    },
    setLc_popup(state, lc_popup) {
      state.lc_popup = lc_popup;
    },
    setSelectedrow(state, selectedrow) {
      state.selectedrow = selectedrow;
    },
    setSelectedrows(state, selectedrows) {
      state.selectedrows = selectedrows;
    },
  },
};
