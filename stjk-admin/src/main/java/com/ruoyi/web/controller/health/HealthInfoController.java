package com.ruoyi.web.controller.health;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.system.domain.health.HealthItem;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.health.IHealthItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.health.HealthInfo;
import com.ruoyi.system.service.health.IHealthInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.annotation.Resource;

/**
 * 重大阳性信息Controller
 * 
 * @author ruoyi
 * @date 2021-04-01
 */
@RestController
@RequestMapping("/health/info")
public class HealthInfoController extends BaseController
{
    @Resource
    private IHealthInfoService healthInfoService;
    @Resource
    private ISysDeptService sysDeptService;
    @Resource
    private IHealthItemService healthItemService;

    /**
     * 查询重大阳性信息列表
     */
    @PreAuthorize("@ss.hasPermi('health:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(HealthInfo healthInfo)
    {
        startPage();
        List<HealthInfo> list = healthInfoService.selectHealthInfoList(healthInfo);
        for (HealthInfo h:list) {
            SysDept dept = sysDeptService.selectDeptById(h.getDeptId());
            HealthItem healthItem = healthItemService.selectHealthItemById(h.getHealthItemId());
            if(dept!=null){
               h.setDeptName(dept.getDeptName());
           }
            if(healthItem!=null){
                h.setHealthItemName(healthItem.getHealthItemName());
            }
        }
        return getDataTable(list);
    }

    /**
     * 导出重大阳性信息列表
     */
    @PreAuthorize("@ss.hasPermi('health:info:export')")
    @Log(title = "重大阳性信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(HealthInfo healthInfo)
    {
        List<HealthInfo> list = healthInfoService.selectHealthInfoList(healthInfo);
        ExcelUtil<HealthInfo> util = new ExcelUtil<HealthInfo>(HealthInfo.class);
        return util.exportExcel(list, "info");
    }

    /**
     * 获取重大阳性信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('health:info:query')")
    @GetMapping(value = "/{infoId}")
    public AjaxResult getInfo(@PathVariable("infoId") Long infoId)
    {
        return AjaxResult.success(healthInfoService.selectHealthInfoById(infoId));
    }

    /**
     * 新增重大阳性信息
     */
    @PreAuthorize("@ss.hasPermi('health:info:add')")
    @Log(title = "重大阳性信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HealthInfo healthInfo)
    {
        return toAjax(healthInfoService.insertHealthInfo(healthInfo));
    }

    /**
     * 修改重大阳性信息
     */
    @PreAuthorize("@ss.hasPermi('health:info:edit')")
    @Log(title = "重大阳性信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HealthInfo healthInfo)
    {
        return toAjax(healthInfoService.updateHealthInfo(healthInfo));
    }

    /**
     * 删除重大阳性信息
     */
    @PreAuthorize("@ss.hasPermi('health:info:remove')")
    @Log(title = "重大阳性信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{infoIds}")
    public AjaxResult remove(@PathVariable Long[] infoIds)
    {
        return toAjax(healthInfoService.deleteHealthInfoByIds(infoIds));
    }
}
