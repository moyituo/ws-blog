package work.xiaoying;

import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;

public class ChineseSort {
    public static void main(String[] args) {
        String[] users={"张三","李四","王五","赵六","赵"};
        Arrays.sort(users, Collator.getInstance(Locale.CHINA));
        for (String user : users) {
            System.out.println(user);
        }
    }
}
