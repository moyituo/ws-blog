package work.xiaoying.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.xiaoying.entity.vo.NavBarVO;
import work.xiaoying.result.R;
import work.xiaoying.service.NavBarService;

import java.util.List;

@Api(tags = "导航栏模块")
@RestController
@RequestMapping("/blog/tool")
public class NavBarController {

    @Autowired
    private NavBarService navBarService;

    @GetMapping("bar")
    @ApiOperation("获取导航栏")
    public R<List<NavBarVO>> getBarList(){
        return R.success(navBarService.getBarList());
    }
}
