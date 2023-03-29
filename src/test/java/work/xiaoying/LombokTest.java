package work.xiaoying;

import lombok.val;

import java.util.ArrayList;

public class LombokTest {
    public static void main(String[] args) {
        val list=new ArrayList<String>();
        list.add("张三");
        list.add("李四");
        for (String s : list) {
            System.out.println(s);
        }
    }
}
