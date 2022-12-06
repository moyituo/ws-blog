package work.xiaoying.controller;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.xiaoying.entity.Person;
import work.xiaoying.result.R;


/**
 *
 *
 * @author 小樱
 * @date 2022/12/06
 */
@RestController
@RequestMapping("/blog/person")
public class PersonController {

    @PostMapping("valid")
    public R<Person> valid(@RequestBody @Validated Person person){
        return R.success(person);
    }
}
