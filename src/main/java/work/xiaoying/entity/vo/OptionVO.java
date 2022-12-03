package work.xiaoying.entity.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 选择签证官
 *
 * @author 小樱
 * @date 2022/12/04
 */
@Data
@Builder
public class OptionVO<T> {

    private String label;

    private T value;
}
