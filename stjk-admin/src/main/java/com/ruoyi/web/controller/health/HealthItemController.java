package com.ruoyi.web.controller.health;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.domain.health.HealthItem;
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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.annotation.Resource;

/**
 * 重大阳性项目Controller
 * 
 * @author Mrguo
 * @date 2021-03-30
 */
@RestController
@RequestMapping("/health/item")
public class HealthItemController extends BaseController
{
    @Resource
    private IHealthItemService healthItemService;
    @Resource
    private SysDeptMapper sysDeptMapper;


    /**
     * 查询重大阳性项目列表
     */
    @PreAuthorize("@ss.hasPermi('health:item:list')")
    @GetMapping("/list")
    public TableDataInfo list(HealthItem healthItem)
    {
        startPage();
        List<HealthItem> list = healthItemService.selectHealthItemList(healthItem);
        for (HealthItem heal:list) {
            SysDept sysDept = sysDeptMapper.selectDeptById(heal.getDeptId());
            if(sysDept!=null){
                heal.setDeptName(sysDept.getDeptName());
            }

        }
        for (HealthItem li:list) {
            System.out.println(li);
        }
        return getDataTable(list);
    }

    /**
     * 导出重大阳性项目列表
     */
    @PreAuthorize("@ss.hasPermi('health:item:export')")
    @Log(title = "重大阳性项目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(HealthItem healthItem)
    {
        List<HealthItem> list = healthItemService.selectHealthItemList(healthItem);
        for (HealthItem heal:list) {
            SysDept sysDept = sysDeptMapper.selectDeptById(heal.getDeptId());
            heal.setDeptName(sysDept.getDeptName());
        }
        ExcelUtil<HealthItem> util = new ExcelUtil<HealthItem>(HealthItem.class);
        return util.exportExcel(list, "item");
    }

    /**
     * 获取重大阳性项目详细信息
     */
    @PreAuthorize("@ss.hasPermi('health:item:query')")
    @GetMapping(value = "/{healthItemId}")
    public AjaxResult getInfo(@PathVariable("healthItemId") Long healthItemId)
    {
        return AjaxResult.success(healthItemService.selectHealthItemById(healthItemId));
    }

    /**
     * 新增重大阳性项目
     */
    @PreAuthorize("@ss.hasPermi('health:item:add')")
    @Log(title = "重大阳性项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HealthItem healthItem)
    {
        return toAjax(healthItemService.insertHealthItem(healthItem));
    }

    /**
     * 修改重大阳性项目
     */
    @PreAuthorize("@ss.hasPermi('health:item:edit')")
    @Log(title = "重大阳性项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HealthItem healthItem)
    {
        return toAjax(healthItemService.updateHealthItem(healthItem));
    }

    /**
     * 删除重大阳性项目
     */
    @PreAuthorize("@ss.hasPermi('health:item:remove')")
    @Log(title = "重大阳性项目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{healthItemIds}")
    public AjaxResult remove(@PathVariable Long[] healthItemIds)
    {
        return toAjax(healthItemService.deleteHealthItemByIds(healthItemIds));
    }
}
