import { axios } from "@/utils/request";

// 게시판 조회
export function getChartList(params) {
    console.log('getChartList===',params)
    return axios({
        url: "/front/chart/getChartList",
        method: "post",
        params,
    });
}