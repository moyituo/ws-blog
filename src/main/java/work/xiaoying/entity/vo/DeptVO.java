package work.xiaoying.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.xiaoying.entity.Dept;
import work.xiaoying.entity.Employees;

import java.util.List;
@Data
@EqualsAndHashCode(callSuper = true)
public class DeptVO extends Dept {

    private List<Employees> employees;

    public DeptVO(Dept dept){
        super(dept);
    }
}
