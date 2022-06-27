import { axios } from "@/utils/request";


export function getMstWhList(params) {
  console.log('getMstWhList===',params)
  return axios({
    url: "/backed/wh/getMstWhList",
    method: "post",
    params,
  });
}

export function saveMstWh(params) {
  console.log('saveMstWh===', params)
   return axios.post("/backed/wh/saveMstWh", params)
}

