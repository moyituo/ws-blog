package work.xiaoying.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import work.xiaoying.entity.Employees;
import work.xiaoying.entity.vo.EmployeesVO;

import java.util.List;

public interface EmployeesService extends IService<Employees> {

    EmployeesVO getOne(Integer id);

    List<EmployeesVO> getList();

    IPage<EmployeesVO> getPage();
}
