package org.nudt;

import com.alibaba.fastjson2.JSON;

//基于JSON的反序列化
public class JSONDecoder implements Decoder {
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
