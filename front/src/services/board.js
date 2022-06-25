import { axios } from "@/utils/request";

export function getBoardList(params) {
    console.log('getBoardList===',params)
    return axios({
        url: "/front/board/getBoardList",
        method: "post",
        params,
    });
}

export function saveBoard(params) {
    console.log('saveItem===', params)
    return axios({
        url: "/front/board/saveBoard",
        method: "post",
        params,
    });
}