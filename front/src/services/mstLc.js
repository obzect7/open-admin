import { axios } from "@/utils/request";


export function getMstLcList(params) {
  console.log('getMstLcList===',params)
  return axios({
    url: "/backed/lc/getMstLcList",
    method: "post",
    params,
  });
}

export function saveMstLc(params) {
  console.log('saveMstLc===', params)
   return axios.post("/backed/lc/saveMstLc", params)
}

