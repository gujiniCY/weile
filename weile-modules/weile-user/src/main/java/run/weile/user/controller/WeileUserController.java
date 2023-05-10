package run.weile.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import run.weile.common.core.utils.poi.ExcelUtil;
import run.weile.common.core.web.controller.BaseController;
import run.weile.common.core.web.domain.AjaxResult;
import run.weile.common.core.web.page.TableDataInfo;
import run.weile.common.log.annotation.Log;
import run.weile.common.log.enums.BusinessType;
import run.weile.common.security.annotation.RequiresPermissions;
import run.weile.user.domain.WeileUser;
import run.weile.user.service.IWeileUserService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户管理Controller
 *
 * @author songhaozhi
 * @date 2023-05-10
 */
@RestController
@RequestMapping("/user")
public class WeileUserController extends BaseController {
    @Autowired
    private IWeileUserService weileUserService;

    /**
     * 查询用户管理列表
     */
    @RequiresPermissions("user:user:list")
    @GetMapping("/list")
    public TableDataInfo list(WeileUser weileUser) {
        startPage();
        List<WeileUser> list = weileUserService.selectWeileUserList(weileUser);
        return getDataTable(list);
    }

    /**
     * 导出用户管理列表
     */
    @RequiresPermissions("user:user:export")
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WeileUser weileUser) {
        List<WeileUser> list = weileUserService.selectWeileUserList(weileUser);
        ExcelUtil<WeileUser> util = new ExcelUtil<WeileUser>(WeileUser.class);
        util.exportExcel(response, list, "用户管理数据");
    }

    /**
     * 获取用户管理详细信息
     */
    @RequiresPermissions("user:user:query")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId) {
        return success(weileUserService.selectWeileUserByUserId(userId));
    }

    /**
     * 新增用户管理
     */
    @RequiresPermissions("user:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WeileUser weileUser) {
        return toAjax(weileUserService.insertWeileUser(weileUser));
    }

    /**
     * 修改用户管理
     */
    @RequiresPermissions("user:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WeileUser weileUser) {
        return toAjax(weileUserService.updateWeileUser(weileUser));
    }

    /**
     * 删除用户管理
     */
    @RequiresPermissions("user:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds) {
        return toAjax(weileUserService.deleteWeileUserByUserIds(userIds));
    }
}
