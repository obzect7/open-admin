import { axios } from "@/utils/request";


export function getCustomerList(params) {
  console.log('getCustomerList===',params)
  return axios({
    url: "/front/customer/getCustomerList",
    method: "post",
    params,
  });
}

