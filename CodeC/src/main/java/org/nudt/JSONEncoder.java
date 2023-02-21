package org.nudt;

import com.alibaba.fastjson.JSON;

//基于JSON的序列化实现
public class JSONEncoder implements Encoder {
    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}
