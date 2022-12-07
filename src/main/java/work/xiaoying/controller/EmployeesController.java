package work.xiaoying.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.xiaoying.entity.vo.EmployeesVO;
import work.xiaoying.result.R;
import work.xiaoying.service.EmployeesService;

import java.util.List;

@RestController
@RequestMapping("employees")
@Slf4j
public class EmployeesController {

    @Autowired
    private EmployeesService employeesService;

    @GetMapping("/getById/{id}")
    public R<EmployeesVO> getById(Integer id){
        return R.success(employeesService.getOne(id));
    }

    @GetMapping("/list")
    public R<List<EmployeesVO>> list(){
        return R.success(employeesService.getList());
    }

    @GetMapping("/page")
    public R<IPage<EmployeesVO>> page(){
        return R.success(employeesService.getPage());
    }
}
