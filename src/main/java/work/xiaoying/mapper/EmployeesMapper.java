package work.xiaoying.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import work.xiaoying.entity.Employees;

@Mapper
public interface EmployeesMapper extends BaseMapper<Employees> {
}
