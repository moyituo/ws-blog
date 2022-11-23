package work.xiaoying.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import work.xiaoying.constant.ErrorMessage;
import work.xiaoying.entity.dto.bar.BarInfoDTO;
import work.xiaoying.entity.vo.bar.NavBarVO;
import work.xiaoying.result.R;
import work.xiaoying.service.NavBarService;
import work.xiaoying.validator.InsertSequence;
import work.xiaoying.validator.UpdateSequence;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Api(tags = "导航栏模块")
@Validated
@RestController
@RequestMapping("/blog/tool")
public class NavBarController {

    private final NavBarService navBarService;

    public NavBarController(NavBarService navBarService) {
        this.navBarService = navBarService;
    }

    @GetMapping("bar")
    @ApiOperation("获取导航栏")
    public R<List<NavBarVO>> getBarList() {
        return R.success(navBarService.getBarList());
    }

    @PostMapping("add")
    @ApiOperation("添加导航栏")
    public R<String> add(@RequestBody @Validated({InsertSequence.class}) BarInfoDTO barInfoDTO) {
        return navBarService.add(barInfoDTO);
    }

    @PutMapping("/edit")
    @ApiOperation("编辑导航栏")
    public R<String> edit(@RequestBody @Validated({UpdateSequence.class}) BarInfoDTO barInfoDTO) {
        return navBarService.edit(barInfoDTO);
    }

    @DeleteMapping("delete")
    @ApiOperation("删除导航栏")
    public R<String> delete(@NotNull(message = ErrorMessage.PARAMETER_ERROR)
                            @Min(value = 1, message = ErrorMessage.PARAMETER_ERROR)
                            Integer id) {
        return navBarService.delete(id);
    }

}
