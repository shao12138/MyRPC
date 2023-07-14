package org.nudt;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class RpcServer {
    private RpcServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    public RpcServer(RpcServerConfig config) {
        this.config = config;
        //net
        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(), this.handler);
        //序列化
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        //反序列化
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        //service
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    public <T> void register(Class<T> interfaceClass, T bean) {
        serviceManager.register(interfaceClass, bean);
    }

    public void start() {
        this.net.start();
    }

    public void stop() {
        this.net.stop();
    }

    private RequestHandler handler = new RequestHandler() {
        public void onRequest(InputStream revice, OutputStream toResponse) {
            Response resp = new Response();
            try {
                byte[] inBytes = IOUtils.readFully(revice, revice.available());
                Request request = decoder.decode(inBytes, Request.class);
                log.info("get request:{}", request);
                ServiceInstance sis = serviceManager.lookup(request);
                Object ret = serviceInvoker.invoke(sis, request);
                resp.setData(ret);
            } catch (Exception e) {
                resp.setData(1);
                resp.setMessage("RpcServer got error:" + e.getClass() + " : " + e.getMessage());
            } finally {
                byte[] outBytes = encoder.encode(resp);
                try {
                    toResponse.write(outBytes);
                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                }
            }

        }
    };
}
