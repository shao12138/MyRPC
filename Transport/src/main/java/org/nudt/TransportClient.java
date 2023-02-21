package org.nudt;

import java.io.InputStream;

/*
 * 创建连接
 * 发送数据，并且等待响应
 * 关闭连接*/
public interface TransportClient {
    void connect(Peer peer);

    InputStream write(InputStream data);

    void close();
}
