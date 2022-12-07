package work.xiaoying.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@TableName("t_dept")
@EqualsAndHashCode(callSuper = true)
public class Dept extends Model<Dept> {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String deptName;
    private Integer staff;
    private String tel;


    public Dept (Dept dept){
        Optional.ofNullable(dept).ifPresent(e->{
            this.id=e.getId();
            this.deptName=e.getDeptName();
        });
    }

}
