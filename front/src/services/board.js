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

export function getBoardComtList(params) {
    console.log('getBoardComtList===',params)
    return axios({
        url: "/front/board/getBoardComtList",
        method: "post",
        params,
    });
}

export function saveBoardComtList(params) {
    console.log('saveBoardComtList===', params)
    return axios({
        url: "/front/board/saveBoardComtList",
        method: "post",
        params,
    });
}