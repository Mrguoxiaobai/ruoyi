package com.ruoyi.web.controller.ecg;

import java.util.List;


import com.ruoyi.system.domain.ecg.EcgYearCount;
import com.ruoyi.system.service.ecg.IEcgYearCountService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 历年体检数据Controller
 * 
 * @author ruoyi
 * @date 2021-03-30
 */
@RestController
@RequestMapping("/ecg/pcount")
public class EcgYearCountController extends BaseController
{
    @Resource
    private IEcgYearCountService ecgYearCountService;
    /**
     * 查询历年体检数据列表
     */
   // @PreAuthorize("@ss.hasPermi('ecg:pcount:list')")
    @GetMapping("/list")
    public TableDataInfo list(EcgYearCount ecgYearCount)
    {
        startPage();
        List<EcgYearCount> list = ecgYearCountService.selectEcgYearCountList(ecgYearCount);
        return getDataTable(list);
    }

    /**
     * 导出历年体检数据列表
     */
    @PreAuthorize("@ss.hasPermi('ecg:pcount:export')")
    @Log(title = "历年体检数据", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EcgYearCount ecgYearCount)
    {
        List<EcgYearCount> list = ecgYearCountService.selectEcgYearCountList(ecgYearCount);
        ExcelUtil<EcgYearCount> util = new ExcelUtil<EcgYearCount>(EcgYearCount.class);
        return util.exportExcel(list, "pcount");
    }

    /**
     * 获取历年体检数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('ecg:pcount:query')")
    @GetMapping(value = "/{yearCountId}")
    public AjaxResult getInfo(@PathVariable("yearCountId") Long yearCountId)
    {
        return AjaxResult.success(ecgYearCountService.selectEcgYearCountById(yearCountId));
    }

    /**
     * 新增历年体检数据
     */
    @PreAuthorize("@ss.hasPermi('ecg:pcount:add')")
    @Log(title = "历年体检数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EcgYearCount ecgYearCount)
    {
        return toAjax(ecgYearCountService.insertEcgYearCount(ecgYearCount));
    }

    /**
     * 修改历年体检数据
     */
    @PreAuthorize("@ss.hasPermi('ecg:pcount:edit')")
    @Log(title = "历年体检数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EcgYearCount ecgYearCount)
    {
        return toAjax(ecgYearCountService.updateEcgYearCount(ecgYearCount));
    }

    /**
     * 删除历年体检数据
     */
    @PreAuthorize("@ss.hasPermi('ecg:pcount:remove')")
    @Log(title = "历年体检数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{yearCountIds}")
    public AjaxResult remove(@PathVariable Long[] yearCountIds)
    {
        return toAjax(ecgYearCountService.deleteEcgYearCountByIds(yearCountIds));
    }
}
