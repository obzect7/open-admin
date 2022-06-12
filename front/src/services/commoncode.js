import { axios, removeAuthorization } from "@/utils/request";


export function getCmCodeGrpList(params) {
  console.log('getCmCodeGrpList===',params)
  return axios({
    url: "/backed/commoncode/getCmCodeGrpList",
    method: "post",
    params,
  });
}

export function getCmCodeListAllUseStore(params) {
  console.log('getCmCodeListAllUseStore===',params)
  return axios({
    url: "/backed/commoncode/getCmCodeListAllUseStore",
    method: "post",
    params,
  });
}

export function getCmCodeList(params) {
  console.log('getCmCodeList===',params)
  return axios({
    url: "/backed/commoncode/getCmCodeList",
    method: "post",
    params,
  });
}

export function saveCmCodeGrp(params) {
  console.log('saveCmCodeGrp===',params)

  return axios.post("/backed/commoncode/saveCmCodeGrp", params, {})
  // return axios({
  //   url: "/backed/commoncode/saveCmCodeGrp",
  //   method: "post",
  //   params,
  // },{
  //   'Content-Type' : 'application/json'
  // });

/*
  return axios({
    url: "/backed/commoncode/saveCmCodeGrp",
    method: "post",
    params,
  });
*/
}

export function saveCmCode(params) {
  console.log('saveCmCode===',params)

  return axios.post("/backed/commoncode/saveCmCode", params, {})
}

