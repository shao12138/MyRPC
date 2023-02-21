package org.nudt;

import java.util.List;

//选择哪个Server去连接
public interface TransportSelector {
    /*初始化
     * peers：可以连接的server端点信息
     * count：client与server建立多少个连接
     * clazz：client实现class*/
    void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz);

    //选择一个transport与Server做交互
    TransportClient select();

    //释放client
    void release(TransportClient client);

    //关闭client
    void close();
}
