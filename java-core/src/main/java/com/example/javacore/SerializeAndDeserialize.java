package com.example.javacore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @author wanghc
 **/
@Slf4j
public class SerializeAndDeserialize {

    @Builder
    @ToString
    @AllArgsConstructor
    public static class User implements Serializable {
        public static final long serialVersionUID = 1L;
        private String name;
        private Integer age;
        private transient String sex;
        @Builder.Default
        private static String signature = "你眼中的世界就是你自己的样子";
    }

    private static void serialize(User user) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("B:\\111.txt"))) {
            oos.writeObject(user);
        }
    }

    private static User deserialize() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("B:\\111.txt"))) {
            return (User) ois.readObject();
        }
    }

    public static void main(String[] args) throws Exception {
        User user = User.builder().name("test").age(18).sex("male").build();
        log.info("序列化前的结果: " + user);

        serialize(user);

        User dUser = deserialize();
        log.info("反序列化后的结果: " + dUser);
    }


}
