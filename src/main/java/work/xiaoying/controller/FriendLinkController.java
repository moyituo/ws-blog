package work.xiaoying.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import work.xiaoying.constant.ErrorMessage;
import work.xiaoying.entity.dto.frient.FriendLinkDTO;
import work.xiaoying.entity.vo.friend.FriendLinkVO;
import work.xiaoying.result.R;
import work.xiaoying.service.FriendLinkService;
import work.xiaoying.validator.InsertSequence;
import work.xiaoying.validator.UpdateSequence;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Api(tags = "友链模块")
@Validated
@RestController
@RequestMapping("/blog/link")
public class FriendLinkController {

    private final FriendLinkService friendLinkService;

    public FriendLinkController(FriendLinkService friendLinkService) {
        this.friendLinkService = friendLinkService;
    }

    @GetMapping("list")
    @ApiOperation("友链列表")
    public R<List<FriendLinkVO>> list() {
        return friendLinkService.linkList();
    }

    @DeleteMapping("delete")
    @ApiOperation("删除友链")
    public R<String> delete(@NotNull(message = ErrorMessage.PARAMETER_ERROR)
                            @Min(value = 1, message = ErrorMessage.PARAMETER_ERROR) Integer id) {
        return friendLinkService.delete(id);
    }

    @PutMapping("edit")
    @ApiOperation("编辑友链")
    public R<String> edit(@RequestBody @Validated({UpdateSequence.class}) FriendLinkDTO friendLinkDTO){
        return friendLinkService.edit(friendLinkDTO);
    }

    @PostMapping("add")
    @ApiOperation("添加友链")
    public R<String> add(@RequestBody @Validated({InsertSequence.class}) FriendLinkDTO friendLinkDTO){
        return friendLinkService.add(friendLinkDTO);
    }
}
