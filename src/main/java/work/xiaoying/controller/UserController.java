package work.xiaoying.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.xiaoying.entity.User;
import work.xiaoying.result.R;
import work.xiaoying.service.UserService;

import java.util.Date;

/**
 * 用户控制器
 *
 * @author 小樱
 * @date 2022/11/22
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("/blog/userInfo")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("简单测试")
    @GetMapping("getUser")
    public R<User> getUser(){
        return R.success(User.builder().id(1)
                .nickName("小樱")
                .avatar("https://")
                .desc("简介")
                .email("23224@qq.com")
                .webSite("https://")
                .disabled(0)
                .createTime(new Date())
                .updateTime(new Date()).build());
    }
}
