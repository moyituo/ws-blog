package work.xiaoying.test;

import work.xiaoying.entity.Dog;

public class MainTest {
    public static void main(String[] args) {
        Dog dog1 = new Dog(1, "mimi", 12, "白色");
        Dog dog2 = new Dog(2, "wangwang", 12, "白色");
        System.out.println(dog1.equals(dog2));
    }
}
