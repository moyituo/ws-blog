package work.xiaoying.test;

import work.xiaoying.entity.Either;
import work.xiaoying.entity.User;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestEither {
    public static void main(String[] args) {
        List<Either<String, User>> eithers = Stream.iterate(1, i -> i + 1)
                .limit(100)
                .map(TestEither::readLine)
                .collect(Collectors.toList());
        Either<String, List<User>> either = Either.sequence(eithers, (s1, s2) -> s1 + "\n" + s2);
        if (either.isLeft()){
            System.out.println(either.getLeft());
        }else {
            either.getRight().forEach(System.out::println);
        }
    }

    public static Either<String, User> readLine(int i){
        if (new Random().nextInt(100)<=50){
            return Either.right(User.builder().nickName("xx").build());
        }else {
            return Either.left("第"+i+"行数据错误");
        }
    }
}
