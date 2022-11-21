package work.xiaoying.result;

import lombok.Data;
import static work.xiaoying.constant.HttpResponseConstants.*;

/**
 * 统一返回结果
 *
 * @author 小樱
 * @date 2022/11/21
 */
@Data
public class R<T> {

    /**
     * 返回状态码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    private R() {
    }

    public static <T> R<T> success() {
        R<T> resultUtil = new R<>();
        resultUtil.setCode(SUCCESS_CODE);
        resultUtil.setMessage(SUCCESS_MESSAGE);
        return resultUtil;
    }

    public static <T> R<T> success(T data) {
        R<T> resultUtil = success();
        resultUtil.setData(data);
        return resultUtil;
    }

    public static <T> R<T> success(String message, T data) {
        R<T> resultUtil = success();
        resultUtil.setMessage(message);
        resultUtil.setData(data);
        return resultUtil;
    }

    public static <T> R<T> success(Integer code, String message, T data) {
        R<T> resultUtil = new R<>();
        resultUtil.setCode(code);
        resultUtil.setMessage(message);
        resultUtil.setData(data);
        return resultUtil;
    }

    public static <T> R<T> fail() {
        R<T> resultUtil = new R<>();
        resultUtil.setCode(FAIL_CODE);
        resultUtil.setMessage(FAIL_MESSAGE);
        return resultUtil;
    }

    public static <T> R<T> fail(T data) {
        R<T> resultUtil = fail();
        resultUtil.setData(data);
        return resultUtil;
    }

    public static <T> R<T> fail(String message, T data) {
        R<T> resultUtil = fail();
        resultUtil.setMessage(message);
        resultUtil.setData(data);
        return resultUtil;
    }

    public static <T> R<T> fail(Integer code, String message) {
        R<T> resultUtil = fail();
        resultUtil.setCode(code);
        resultUtil.setMessage(message);
        return resultUtil;
    }

    public static <T> R<T> fail(Integer code, String message, T data) {
        R<T> resultUtil = new R<>();
        resultUtil.setCode(code);
        resultUtil.setMessage(message);
        resultUtil.setData(data);
        return resultUtil;
    }
}
