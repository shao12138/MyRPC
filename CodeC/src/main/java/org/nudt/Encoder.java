package org.nudt;

//序列化
public interface Encoder {
    byte[] encode(Object obj);
}
