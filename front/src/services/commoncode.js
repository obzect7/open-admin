import { axios, removeAuthorization } from "@/utils/request";


export function getCmCodeGrpList(params) {
  console.log('getCmCodeGrpList===',params)
  return axios({
    url: "/backed/commoncode/getCmCodeGrpList",
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

export function saveCmCodeGrp(params, config) {
  console.log('saveCmCodeGrp===',params)

  return axios.post("/backed/commoncode/saveCmCodeGrp", params, config)



/*
  return axios({
    url: "/backed/commoncode/saveCmCodeGrp",
    method: "post",
    params,
  });
*/
}

