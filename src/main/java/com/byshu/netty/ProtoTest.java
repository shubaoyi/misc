package com.byshu.netty;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author byshu
 * @desc
 */
public class ProtoTest {

    public static void main(String[] args) {
        try {
            /** Step1：生成 User 对象 */
            UserProto.User.Builder personBuilder = UserProto.User.newBuilder();
            personBuilder.setId(1);
            personBuilder.setAge(20);
            personBuilder.setGender(1);
            personBuilder.setName("jane");
            UserProto.User user = personBuilder.build();

            /** byte[] 序列化和反序列化 */
            /*byte[] bytes = user.toByteArray();
            UserProto.User user2 = UserProto.User.parseFrom(bytes);
            System.out.println(user2);*/


            /** ByteString user */
            /*ByteString byteString = user.toByteString();
            System.out.println(byteString.toString());
            UserProto.User user2 = UserProto.User.parseFrom(byteString);
            System.out.println(user2);*/


            /** InputStream 序列化和反序列化 */
            // 粘包,将一个或者多个protobuf 对象字节写入 stream
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            user.writeDelimitedTo(bos);
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            UserProto.User user2 = UserProto.User.parseDelimitedFrom(bis);
            System.out.println(user2);

        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
