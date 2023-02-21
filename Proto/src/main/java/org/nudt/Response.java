package org.nudt;

import lombok.Data;

//表示RPC的响应
@Data   //set,get
public class Response {
    //服务返回信息：0成功，非0失败
    private int code = 0;
    //具体错误信息
    private String message = "ok";
    //返回数据
    private Object data;
}
