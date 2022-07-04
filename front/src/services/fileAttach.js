import { axios, removeAuthorization } from "@/utils/request";


export function getFileList(params) {
  console.log('getFileList===',params)
  return axios({
    url: "/backed/commoncode/getCmCodeGrpList",
    method: "post",
    params,
  });
}

