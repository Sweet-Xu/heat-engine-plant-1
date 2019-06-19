package cc.hep.system.controller;

import cc.hep.common.annotation.Log;
import cc.hep.common.controller.BaseController;
import cc.hep.common.domain.QueryRequest;
import cc.hep.common.domain.ResponseBo;
import cc.hep.common.util.FileUtil;
import cc.hep.system.domain.Resources;
import cc.hep.system.service.ResourcesService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ResourcesController  extends BaseController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ResourcesService resourcesService;

    @Log("获取物资信息")
    @RequestMapping("resources")
    @RequiresPermissions("resources:list")
    public String index() {
        return "system/resources/resources";
    }

    @Log("库存统计")
    @RequestMapping("resources/statistics")
    @RequiresPermissions("resources:statistics")
    public String statistics() {
        return "system/resources/statistics";
    }


    @RequestMapping("resources/getResources")
    @ResponseBody
    public ResponseBo getResources(Long resourcesId) {
        try {
            Resources resources = this.resourcesService.findById(resourcesId);
            return ResponseBo.ok(resources);
        } catch (Exception e) {
            log.error("获取物资信息失败", e);
            return ResponseBo.error("获取物资信息失败，请联系网站管理员！");
        }
    }

    @RequestMapping("resources/list")
    @RequiresPermissions("resources:list")
    @ResponseBody
    public Map<String, Object> resourcesList(QueryRequest request, Resources resources) {
        return super.selectByPageNumSize(request, () -> this.resourcesService.findAllResources(resources, request));
    }

    @RequestMapping("resources/excel")
    @ResponseBody
    public ResponseBo resourcesExcel(Resources resources) {
        try {
            List<Resources> list = this.resourcesService.findAllResources(resources,null);
            return FileUtil.createExcelByPOIKit("物资表", list, Resources.class);
        } catch (Exception e) {
            log.error("导出物资信息Excel失败", e);
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }

    @RequestMapping("resources/csv")
    @ResponseBody
    public ResponseBo resourcesCsv(Resources resources) {
        try {
            List<Resources> list = this.resourcesService.findAllResources(resources,null);
            return FileUtil.createCsv("物资表", list, Resources.class);
        } catch (Exception e) {
            log.error("获取物资信息Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }

    @RequestMapping("resources/checkResourcesName")
    @ResponseBody
    public boolean checkResourcesName(String resourcesName, String oldResourcesName) {
        if (StringUtils.isNotBlank(oldResourcesName) && resourcesName.equalsIgnoreCase(oldResourcesName)) {
            return true;
        }
        Resources result = this.resourcesService.findByName(resourcesName);
        return result == null;
    }

    @Log("新增物资 ")
    @RequiresPermissions("resources:add")
    @RequestMapping("resources/add")
    @ResponseBody
    public ResponseBo addResources(Resources resources) {
        try {
            this.resourcesService.addResources(resources);
            return ResponseBo.ok("新增物资成功！");
        } catch (Exception e) {
            log.error("新增物资失败", e);
            return ResponseBo.error("新增物资失败，请联系网站管理员！");
        }
    }

    @Log("删除物资")
    @RequiresPermissions("resources:delete")
    @RequestMapping("resources/delete")
    @ResponseBody
    public ResponseBo deleteResources(String ids) {
        try {
            this.resourcesService.deleteResources(ids);
            return ResponseBo.ok("删除物资成功！");
        } catch (Exception e) {
            log.error("删除物资失败", e);
            return ResponseBo.error("删除物资失败，请联系网站管理员！");
        }
    }

    @Log("修改物资 ")
    @RequiresPermissions("resources:update")
    @RequestMapping("resources/update")
    @ResponseBody
    public ResponseBo updateResources(Resources resources) {
        try {
            this.resourcesService.updateResources(resources);
            return ResponseBo.ok("修改物资成功！");
        } catch (Exception e) {
            log.error("修改物资失败", e);
            return ResponseBo.error("修改物资失败，请联系网站管理员！");
        }
    }

    @Log("入库 ")
    @RequiresPermissions("resources:in")
    @RequestMapping("resources/in")
    @ResponseBody
    public ResponseBo inResources(String resourcesId, String num, String remarks) {
        try {
            this.resourcesService.inResources(resourcesId,num, remarks);
            return ResponseBo.ok("入库成功！");
        } catch (Exception e) {
            log.error("入库失败", e);
            return ResponseBo.error("入库失败，请联系网站管理员！");
        }
    }

    @Log("出库 ")
    @RequiresPermissions("resources:out")
    @RequestMapping("resources/out")
    @ResponseBody
    public ResponseBo outResources(String resourcesId, String num, String remarks) {
        try {
            this.resourcesService.outResources(resourcesId,num, remarks);
            return ResponseBo.ok("出库成功！");
        } catch (Exception e) {
            log.error("出库失败", e);
            return ResponseBo.error("出库失败，请联系网站管理员！");
        }
    }

}
