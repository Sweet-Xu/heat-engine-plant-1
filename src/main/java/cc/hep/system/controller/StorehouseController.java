package cc.hep.system.controller;

import cc.hep.common.annotation.Log;
import cc.hep.common.controller.BaseController;
import cc.hep.common.domain.QueryRequest;
import cc.hep.common.domain.ResponseBo;import cc.hep.common.util.FileUtil;
import cc.hep.system.domain.Storehouse;
import cc.hep.system.service.StorehouseService;
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
public class StorehouseController extends BaseController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private static final String ON = "on";

    @Autowired
    private StorehouseService storehouseService;

    @Log("获取仓库信息")
    @RequestMapping("storehouse")
    @RequiresPermissions("storehouse:list")
    public String index() {
        return "system/storehouse/storehouse";
    }


    @RequestMapping("storehouse/getStorehouse")
    @ResponseBody
    public ResponseBo getStorehouse(Long storehouseId) {
        try {
            Storehouse storehouse = this.storehouseService.findById(storehouseId);
            return ResponseBo.ok(storehouse);
        } catch (Exception e) {
            log.error("获取仓库信息失败", e);
            return ResponseBo.error("获取仓库信息失败，请联系网站管理员！");
        }
    }

    @RequestMapping("storehouse/list")
    @RequiresPermissions("storehouse:list")
    @ResponseBody
    public Map<String, Object> storehouseList(QueryRequest request, Storehouse storehouse) {
        return super.selectByPageNumSize(request, () -> this.storehouseService.findAllStorehouse(storehouse, request));
    }

    @RequestMapping("storehouse/excel")
    @ResponseBody
    public ResponseBo equipmentExcel(Storehouse storehouse) {
        try {
            List<Storehouse> list = this.storehouseService.findAllStorehouse(storehouse,null);
            return FileUtil.createExcelByPOIKit("仓库表", list, Storehouse.class);
        } catch (Exception e) {
            log.error("导出仓库信息Excel失败", e);
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }

    @RequestMapping("storehouse/csv")
    @ResponseBody
    public ResponseBo equipmentCsv(Storehouse storehouse) {
        try {
            List<Storehouse> list = this.storehouseService.findAllStorehouse(storehouse,null);
            return FileUtil.createCsv("仓库表", list, Storehouse.class);
        } catch (Exception e) {
            log.error("获取仓库信息Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }

    @RequestMapping("storehouse/checkStorehouseName")
    @ResponseBody
    public boolean checkStorehouseName(String storehouseName, String oldStorehouseName) {
        if (StringUtils.isNotBlank(oldStorehouseName) && storehouseName.equalsIgnoreCase(oldStorehouseName)) {
            return true;
        }
        Storehouse result = this.storehouseService.findByName(storehouseName);
        return result == null;
    }

    @Log("新增仓库 ")
    @RequiresPermissions("storehouse:add")
    @RequestMapping("storehouse/add")
    @ResponseBody
    public ResponseBo addStorehouse(Storehouse storehouse) {
        try {
            if (ON.equalsIgnoreCase(storehouse.getStatus()))
                storehouse.setStatus(Storehouse.STATUS_USE);
            else
                storehouse.setStatus(storehouse.STATUS_UNUSE);
            this.storehouseService.addStorehouse(storehouse);
            return ResponseBo.ok("新增仓库成功！");
        } catch (Exception e) {
            log.error("新增仓库失败", e);
            return ResponseBo.error("新增仓库失败，请联系网站管理员！");
        }
    }

    @Log("删除仓库")
    @RequiresPermissions("storehouse:delete")
    @RequestMapping("storehouse/delete")
    @ResponseBody
    public ResponseBo deleteStorehouse(String ids) {
        try {
            this.storehouseService.deleteStorehouse(ids);
            return ResponseBo.ok("删除仓库成功！");
        } catch (Exception e) {
            log.error("删除仓库失败", e);
            return ResponseBo.error("删除仓库失败，请联系网站管理员！");
        }
    }

    @Log("修改仓库 ")
    @RequiresPermissions("storehouse:update")
    @RequestMapping("storehouse/update")
    @ResponseBody
    public ResponseBo updateEquipment(Storehouse storehouse) {
        try {
            if (ON.equalsIgnoreCase(storehouse.getStatus()))
                storehouse.setStatus(Storehouse.STATUS_USE);
            else
                storehouse.setStatus(Storehouse.STATUS_UNUSE);
            this.storehouseService.updateStorehouse(storehouse);
            return ResponseBo.ok("修改仓库成功！");
        } catch (Exception e) {
            log.error("修改仓库失败", e);
            return ResponseBo.error("修改仓库失败，请联系网站管理员！");
        }
    }
}
