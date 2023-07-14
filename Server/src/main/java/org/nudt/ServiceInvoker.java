package org.nudt;
//调用具体服务
public class ServiceInvoker {
    public Object invoke(ServiceInstance service, Request request) {
        return ReflectionUtils.invoke(service.getTarget(),service.getMethod(),request.getParameter());
    }
}
