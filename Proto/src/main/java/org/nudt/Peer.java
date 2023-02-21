package org.nudt;

import lombok.AllArgsConstructor;
import lombok.Data;

//表示网络传输的一个端点
@Data   //set,get
@AllArgsConstructor    //所有参数的构造器
public class Peer {
    //地址
    private String host;
    //端口号
    private int port;
}
