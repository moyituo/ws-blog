package work.xiaoying.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.xiaoying.entity.Dept;
import work.xiaoying.entity.Employees;
import work.xiaoying.entity.vo.EmployeesVO;
import work.xiaoying.mapper.EmployeesMapper;
import work.xiaoying.service.DeptService;
import work.xiaoying.service.EmployeesService;
import work.xiaoying.utils.ConvertUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

@Service
public class EmployeesServiceImpl extends ServiceImpl<EmployeesMapper, Employees> implements EmployeesService {

    @Autowired
    private DeptService deptService;

    @Override
    public EmployeesVO getOne(Integer id) {
        LambdaQueryWrapper<Employees> wrapper = Wrappers.<Employees>lambdaQuery().eq(Employees::getId, id);
        EmployeesVO employeesVO = ConvertUtils.toObj(getOne(wrapper), EmployeesVO::new);
        Optional.ofNullable(employeesVO).ifPresent(this::getDeptNameInfo);
        return employeesVO;
    }

    private void getDeptNameInfo(EmployeesVO employeesVO){
        LambdaQueryWrapper<Dept> wrapper = Wrappers.<Dept>lambdaQuery().eq(Dept::getId, employeesVO.getDeptId());
        Dept dept = deptService.getOne(wrapper);
        Optional.ofNullable(dept).ifPresent(e->employeesVO.setDeptName(e.getDeptName()));
    }

    @Override
    public List<EmployeesVO> getList() {
        List<Employees> employees = this.list();
        List<EmployeesVO> employeesVos = ConvertUtils.toList(employees, EmployeesVO::new);
        Set<Integer> deptIds = ConvertUtils.toSet(employeesVos, EmployeesVO::getDeptId);
        if (CollUtil.isNotEmpty(deptIds)){
            LambdaQueryWrapper<Dept> wrapper = Wrappers.<Dept>lambdaQuery().in(Dept::getId, deptIds).select(Dept::getId, Dept::getDeptName, Dept::getStaff, Dept::getTel);
            List<Dept> depts = deptService.list(wrapper);
            Map<Integer, Dept> deptMap = ConvertUtils.toMap(depts, Dept::getId, Function.identity());
            employeesVos.forEach(e-> e.addDept(deptMap.get(e.getDeptId())));
        }
        return employeesVos;
    }

    @Override
    public IPage<EmployeesVO> getPage() {
        Page<Employees> page = new Page<>(1, 5);
        Page<Employees> empPage = this.page(page);
        IPage<EmployeesVO> employeesVOIPage = ConvertUtils.toPage(empPage, EmployeesVO::new);
        Set<Integer> deptIds = ConvertUtils.toSet(employeesVOIPage.getRecords(), EmployeesVO::getDeptId);
        if (CollUtil.isNotEmpty(deptIds)){
            LambdaQueryWrapper<Dept> wrapper = Wrappers.<Dept>lambdaQuery().in(Dept::getId, deptIds);
            List<Dept> deptList = deptService.list(wrapper);
            Map<Integer, Dept> deptMap = ConvertUtils.toMap(deptList, Dept::getId, Function.identity());
            employeesVOIPage.getRecords().forEach(e-> e.addDept(deptMap.get(e.getDeptId())));
        }
        return employeesVOIPage;
    }
}
