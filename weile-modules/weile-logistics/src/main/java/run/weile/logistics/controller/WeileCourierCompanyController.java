package run.weile.logistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import run.weile.common.core.utils.poi.ExcelUtil;
import run.weile.common.core.web.controller.BaseController;
import run.weile.common.core.web.domain.AjaxResult;
import run.weile.common.core.web.page.TableDataInfo;
import run.weile.common.log.annotation.Log;
import run.weile.common.log.enums.BusinessType;
import run.weile.common.security.annotation.RequiresPermissions;
import run.weile.logistics.domain.WeileCourierCompany;
import run.weile.logistics.service.IWeileCourierCompanyService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 快递公司代码Controller
 *
 * @author songhaozhi
 * @date 2023-05-17
 */
@RestController
@RequestMapping("/courierCompany")
public class WeileCourierCompanyController extends BaseController {
    @Autowired
    private IWeileCourierCompanyService weileCourierCompanyService;

    /**
     * 查询快递公司代码列表
     */
    @RequiresPermissions("logistics:courierCompany:list")
    @GetMapping("/list")
    public TableDataInfo list(WeileCourierCompany weileCourierCompany) {
        startPage();
        List<WeileCourierCompany> list = weileCourierCompanyService.selectWeileCourierCompanyList(weileCourierCompany);
        return getDataTable(list);
    }

    /**
     * 导出快递公司代码列表
     */
    @RequiresPermissions("logistics:courierCompany:export")
    @Log(title = "快递公司代码", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WeileCourierCompany weileCourierCompany) {
        List<WeileCourierCompany> list = weileCourierCompanyService.selectWeileCourierCompanyList(weileCourierCompany);
        ExcelUtil<WeileCourierCompany> util = new ExcelUtil<WeileCourierCompany>(WeileCourierCompany.class);
        util.exportExcel(response, list, "快递公司代码数据");
    }

    /**
     * 获取快递公司代码详细信息
     */
    @RequiresPermissions("logistics:courierCompany:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(weileCourierCompanyService.selectWeileCourierCompanyById(id));
    }

    /**
     * 新增快递公司代码
     */
    @RequiresPermissions("logistics:courierCompany:add")
    @Log(title = "快递公司代码", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WeileCourierCompany weileCourierCompany) {
        return toAjax(weileCourierCompanyService.insertWeileCourierCompany(weileCourierCompany));
    }

    /**
     * 修改快递公司代码
     */
    @RequiresPermissions("logistics:courierCompany:edit")
    @Log(title = "快递公司代码", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WeileCourierCompany weileCourierCompany) {
        return toAjax(weileCourierCompanyService.updateWeileCourierCompany(weileCourierCompany));
    }

    /**
     * 删除快递公司代码
     */
    @RequiresPermissions("logistics:courierCompany:remove")
    @Log(title = "快递公司代码", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(weileCourierCompanyService.deleteWeileCourierCompanyByIds(ids));
    }
}
