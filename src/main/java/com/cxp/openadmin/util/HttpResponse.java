package com.cxp.openadmin.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "HttpResponse")
@Data
public class HttpResponse {

    @ApiModelProperty(name = "code",value = "리턴코드 200은 정상, 나머지는 비정상",required = true)
    private Integer code;

    @ApiModelProperty(name = "message",value = "해당 정보",required = true)
    private String message;

    @ApiModelProperty(name = "data",value = "응답 데이터",required = true)
    private Object data;

    public HttpResponse(Object data) {
        this.code = 200;
        this.message = "OK";
        this.data = data;
    }

    public static HttpResponse success(Object data) {
        return new HttpResponse(data);
    }

    public static HttpResponse success() {
        return new HttpResponse(null);
    }

}
