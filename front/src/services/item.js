import { axios } from "@/utils/request";


export function getItemList(params) {
    console.log('getItemList===',params)
    return axios({
        url: "/front/item/getItemList",
        method: "post",
        params,
    });
}

export function saveItem(params) {
    console.log('saveItem===', params)

    return axios.post("/front/item/saveItem", params)
    // return axios({
    //   url: "/backed/commoncode/saveCmCodeGrp",
    //   method: "post",
    //   params,
    // })
}

export function saveFileGrpSeq(params) {
    console.log('saveFileGrpSeq', params)
    return axios.post("/front/item/saveFileGrpSeq", params)
}



