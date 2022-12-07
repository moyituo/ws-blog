package work.xiaoying.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("t_employees")
@EqualsAndHashCode(callSuper = true)
public class Employees extends Model<Employees> {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;

    private Integer age;
    private Integer deptId;

    public Employees(Employees employees){
        if (employees!=null){
            this.id=employees.getId();
            this.name=employees.getName();
            this.deptId=employees.getDeptId();
        }
    }
}
