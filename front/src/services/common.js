//import {CODEGRPLIST, CODELIST, SAVECODEGRP, SAVECODECODE} from '@/services/api'
//import {request, METHOD, axios} from '@/utils/request'
import {axios} from '@/utils/request'


/**
 * 사용자조회
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function getFileGrpSeq() {
  const param = {
    'test': '11111'
  }
  const response = await axios.post("/common/getFileGrpSeq", param, {})
  let   data     = response.data
  console.log('data ==',data)
  return data
}
