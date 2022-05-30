//import {CODEGRPLIST, CODELIST, SAVECODEGRP, SAVECODECODE} from '@/services/api'
//import {request, METHOD, axios} from '@/utils/request'
import {axios} from '@/utils/request'


/**
 * 사용자조회
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function selectCodeGrpList(params) {
  // console.log('CODEGRPLIST ==',CODEGRPLIST)
  //return request(CODEGRPLIST, METHOD.POST, params)

  return axios({
    url: `/commcode/getCommonCodeGrpList`,
    method: "post",
    params,
  });


}
export async function searchDtlCode(params) {
  // console.log('CODELIST ==',CODELIST)

  //return request(CODELIST, METHOD.POST, params)
  return axios({
    url: ``,
    method: "post",
    params,
  });
}

export async function savecodeGrp(params) {
  //console.log('SAVECODEGRP ==',SAVECODEGRP)
  //console.log('login password ==',password)
  //return request(SAVECODEGRP, METHOD.POST, params)
  return axios({
    url: ``,
    method: "post",
    params,
  });
}

export async function savecodeCode(params) {
  //console.log('SAVECODECODE ==',SAVECODECODE)
  //console.log('login password ==',password)
  //return request(SAVECODECODE, METHOD.POST, params)
  return axios({
    url: ``,
    method: "post",
    params,
  });
}

export default {
}
