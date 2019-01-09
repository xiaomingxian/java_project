package test;

import java.util.UUID;

public class UUIDTest {


    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);

        String replace = uuid.toString().replace("-", "");
        System.out.println(replace);
    }
}
