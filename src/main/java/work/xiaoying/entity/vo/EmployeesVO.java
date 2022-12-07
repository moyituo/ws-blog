package work.xiaoying.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import work.xiaoying.entity.Dept;
import work.xiaoying.entity.Employees;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class EmployeesVO extends Employees {

    private String deptName;
    private Integer staff;
    private String tel;

    public EmployeesVO (Employees employees){
        super(employees);
    }

    public void addDept(Dept dept){
        this.deptName=dept.getDeptName();
        this.staff=dept.getStaff();
        this.tel=dept.getTel();
    }
}
