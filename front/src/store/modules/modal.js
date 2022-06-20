export default {
  namespaced: true,
  state: {
    modalstatus: false,
    selectedrow:{},
    selectedrows:[]
  },
  getters: {
    modalstatus: (state) => {
      return state.modalstatus;
    },
    selectedrow: (state) => {
      return state.selectedrow;
    },
    selectedrows: (state) => {
      console.log('test중입니다.')
      return state.selectedrows;

    },
  },
  mutations: {
    setModalstatus(state, modalstatus) {
      state.modalstatus = modalstatus;
      // localStorage.setItem(process.env.VUE_APP_USER_KEY, JSON.stringify(user));
    },
    setSelectedrow(state, selectedrow) {
      state.selectedrow = selectedrow;
    },
    setSelectedrows(state, selectedrows) {
      state.selectedrows = selectedrows;
      // localStorage.setItem(process.env.VUE_APP_USER_KEY, JSON.stringify(user));
    },
  },
};
