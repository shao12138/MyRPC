package org.nudt;

import lombok.Data;

@Data   //set,get
public class Request {
    //请求服务
    private ServiceDescriptor service;
    //请求参数
    private Object[] parameter;
}
