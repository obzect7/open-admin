import { axios, removeAuthorization } from "@/utils/request";

export function getFileList(params) {
  console.log('getFileList===',params)
  return axios({
    url: "/backed/commoncode/getCmCodeGrpList",
    method: "post",
    params,
  });
}

export function saveFileInfo(params) {
  console.log('saveItem===', params)

  return axios.post("/front/file/saveFile", params)
  // return axios({
  //   url: "/backed/commoncode/saveCmCodeGrp",
  //   method: "post",
  //   params,
  // })
}

