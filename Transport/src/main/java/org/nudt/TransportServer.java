package org.nudt;

/*启动，监听
 * 接受请求
 * 关闭监听*/
public interface TransportServer {
    void init(int prot, RequestHandler handler);

    void start();

    void stop();
}
