export default {
  namespaced: true,
  state: {
    commoncodes: []
  },
  getters: {
    commoncodes: (state) => {
      if (!state.commoncodes) {
        try {
          state.commoncodes = state.commoncodes ? state.commoncodes : [];
        } catch (e) {
          console.error(e.message);
        }
      }
      return state.commoncodes;
    },
  },
  mutations: {
    setCommoncodes(state, commoncodes) {
      state.commoncodes = commoncodes;
    },
  },
};
