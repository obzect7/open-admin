import { axios, removeAuthorization } from "@/utils/request";


export function getCmCodeGrpList(params) {
  console.log('getCmCodeGrpList===',params)
  return axios({
    url: "/backed/commoncode/getCmCodeGrpList",
    method: "post",
    params,
  });
}

export function getCmCodeListAllUseStore(params) {
  console.log('getCmCodeListAllUseStore===',params)
  return axios({
    url: "/backed/commoncode/getCmCodeListAllUseStore",
    method: "post",
    params,
  });
}

export function getCmCodeList(params) {
  console.log('getCmCodeList===',params)
  return axios({
    url: "/backed/commoncode/getCmCodeList",
    method: "post",
    params,
  });
}

// 화면 시작시 공통코드 로딩
// group_cd = group_cd
// option = "전체" or "선택" or null
export async function getCmCodeLoad(group_cd,option) {
  console.log('getCmCodeList===',group_cd)
  const param = {
    group_cd: group_cd
  }
  const response = await axios.post("/backed/commoncode/getCmCodeLoad", param, {})
  let   data     = response.data
  if(option != null && option != ""){
      let firstRow = new Object()
          firstRow.group_cd = group_cd
          firstRow.code     = ''
          firstRow.code_nm = option
          firstRow.code_nm_en = option
          firstRow.data1 = ''
          firstRow.data2 = ''
          firstRow.data3 = ''
          firstRow.data4 = ''
          firstRow.data5 = ''
          firstRow.data6 = ''
          firstRow.data7 = ''
          firstRow.data8 = ''
          firstRow.data9 = ''
          firstRow.data10 = ''
          firstRow.rem = ''

    data.unshift(firstRow)
  }
  console.log('data ==',data)
  return data
}

export function saveCmCodeGrp(params) {
  console.log('saveCmCodeGrp===',params)

  return axios.post("/backed/commoncode/saveCmCodeGrp", params, {})
  // return axios({
  //   url: "/backed/commoncode/saveCmCodeGrp",
  //   method: "post",
  //   params,
  // },{
  //   'Content-Type' : 'application/json'
  // });

/*
  return axios({
    url: "/backed/commoncode/saveCmCodeGrp",
    method: "post",
    params,
  });
*/
}

export function saveCmCode(params) {
  console.log('saveCmCode===',params)

  return axios.post("/backed/commoncode/saveCmCode", params, {})
}

