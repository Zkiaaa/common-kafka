package com.demo.kafka.serialize;

import java.io.*;

import kafka.serializer.Decoder;
import kafka.serializer.Encoder;
import kafka.utils.VerifiableProperties;
import lombok.extern.slf4j.Slf4j;

/**
 * kafka消息类型转换
 * @author Air
 * @version Id: MessageSerializer.java, v 0.1 2017.5.20 10:58 Air Exp $$
 */
@Slf4j
public class MessageSerializer<V> implements Encoder<V>, Decoder<V> {

    public MessageSerializer() {
    }

    public MessageSerializer(VerifiableProperties properties) {
        log.debug("serializer properties ：{}", properties);
    }

    public V fromBytes(byte[] bytes) {
        V obj = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            obj = (V) ois.readObject();

        } catch (IOException ex) {
            log.error("message unSerializer IOException：{}", ex);
        } catch (ClassNotFoundException ex) {
            log.error("message serializer ClassNotFoundException：{}", ex);
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                log.error("message unSerializer IOException：{}", e);
            }
        }
        return obj;
    }

    public byte[] toBytes(V o) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(o);
            oos.flush();
            bytes = bos.toByteArray();
        } catch (IOException ex) {
            log.error("message serializer IOException：{}", ex);
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                log.error("message serializer IOException：{}", e);
            }
        }
        return bytes;
    }
}
