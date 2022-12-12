package work.xiaoying.entity;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 要么
 *
 * @author 小樱
 * @date 2022/12/13
 */
public class Either<L,R> {

    private L left;
    private R right;

    public L getLeft() {
        return left;
    }

    public void setLeft(L left) {
        this.left = left;
    }

    public R getRight() {
        return right;
    }

    public void setRight(R right) {
        this.right = right;
    }

    public boolean isLeft(){
        return left!=null;
    }

    public boolean isRight(){
        return right!=null;
    }

    public static <L,R> Either<L,R> left(L exception){
        Either<L,R> e=new Either<>();
        e.left=exception;
        return e;
    }

    public static <L,R> Either<L,R> right(R value){
        Either<L,R> e=new Either<>();
        e.right=value;
        return e;
    }

    public <T> Either<L,T> map(Function<R,T> function){
        if (isLeft()){
            return left(left);
        }else {
            return right(function.apply(right));
        }
    }

    public static <L,R> Either<L,List<R>> sequence(List<Either<L,R>> eitherList, BinaryOperator<L> accumulator){
        if (eitherList.stream().allMatch(Either::isRight)){
            return right(eitherList.stream().map(Either::getRight).collect(Collectors.toList()));
        }else {
            return left(eitherList.stream()
                    .filter(Either::isLeft)
                    .map(Either::getLeft)
                    .reduce(accumulator)
                    .orElse(null));

        }
    }
}
