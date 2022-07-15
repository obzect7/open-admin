import { axios } from "@/utils/request";


export function getMstPlantList(params) {
  console.log('getMstPlantList===',params)
  return axios({
    url: "/backed/plant/getMstPlantList",
    method: "post",
    params,
  });
}

export function saveMstPlant(params) {
  console.log('saveMstPlant===', params)
   return axios.post("/backed/plant/saveMstPlant", params)
}