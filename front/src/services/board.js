import { axios } from "@/utils/request";

export function getBoardList(params) {
    console.log('getBoardList===',params)
    return axios({
        url: "/front/board/getBoardList",
        method: "post",
        params,
    });
}