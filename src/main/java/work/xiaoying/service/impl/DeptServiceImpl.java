package work.xiaoying.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.xiaoying.entity.Dept;
import work.xiaoying.entity.Employees;
import work.xiaoying.entity.vo.DeptVO;
import work.xiaoying.mapper.DeptMapper;
import work.xiaoying.service.DeptService;
import work.xiaoying.service.EmployeesService;
import xin.altitude.cms.common.util.ColUtils;
import xin.altitude.cms.common.util.EntityUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    @Autowired
    private EmployeesService employeesService;

    public DeptVO getById(Long id){
        Dept dept = this.getById(id);
        DeptVO deptVO = EntityUtils.toObj(dept, DeptVO::new);
        List<Employees> employeesList = employeesService.list(Wrappers.<Employees>lambdaQuery().eq(Employees::getDeptId, deptVO.getId()));
        deptVO.setEmployees(employeesList);
        return deptVO;
    }

    public List<DeptVO> deptList(){
        List<Dept> depts = this.list();
        List<DeptVO> deptVOS = EntityUtils.toList(depts, DeptVO::new);
        Set<Integer> deptIds = EntityUtils.toSet(deptVOS, DeptVO::getId);
        if (CollUtil.isNotEmpty(deptIds)){
            LambdaQueryWrapper<Employees> wrapper = Wrappers.<Employees>lambdaQuery().in(Employees::getDeptId, deptIds);
            List<Employees> employees = employeesService.list(wrapper);
            Map<Integer, Optional<Employees>> map = employees.stream().collect(Collectors
                    .groupingBy(Employees::getDeptId, Collectors.maxBy(Comparator.comparing(Employees::getAge))));
            deptVOS.forEach(d-> d.setEmployees((List<Employees>) ColUtils.toCol(map.get(d.getId()).orElseGet(Employees::new))));
        }
        return deptVOS;
    }
}
