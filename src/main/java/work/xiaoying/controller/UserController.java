package work.xiaoying.controller;


import cn.dev33.satoken.stp.StpUtil;
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

    // 测试登录，浏览器访问： http://localhost:8081/user/doLogin?username=zhang&password=123456
    @RequestMapping("doLogin")
    public String doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            return "登录成功";
        }
        return "登录失败";
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

}
