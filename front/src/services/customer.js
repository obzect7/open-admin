import { axios } from "@/utils/request";


export function getCustomerList(params) {
  console.log('getCustomerList===',params)
  return axios({
    url: "/front/customer/getCustomerList",
    method: "post",
    params,
  });
}

export function saveCustomer(params) {
  console.log('saveCustomer===', params)

   return axios.post("/front/customer/saveCustomer", params)
  // return axios({
  //   url: "/backed/commoncode/saveCmCodeGrp",
  //   method: "post",
  //   params,
  // })
}

