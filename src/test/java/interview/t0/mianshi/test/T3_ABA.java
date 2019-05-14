package interview.t0.mianshi.test;


import lombok.Data;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

public class T3_ABA {
    /**
     * 原子引用
     */
    @Test
    public void t1() {
        User zs = new User("zs");
        User ls = new User("ls");
        User ww = new User("ww");

        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        userAtomicReference.set(zs);

        System.out.println("---->"+userAtomicReference.compareAndSet(zs, ls)+" ");
        System.out.println("---->"+userAtomicReference.compareAndSet(ls, zs)+" ");
        System.out.println("---->"+userAtomicReference.compareAndSet(zs, ls)+" ");

    }

}

@Data
class User {
    String name;

    public User(String name) {
        this.name = name;
    }

}
