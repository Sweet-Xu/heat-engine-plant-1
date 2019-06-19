package cc.hep.system.controller;

import cc.hep.common.annotation.Log;
import cc.hep.common.controller.BaseController;
import cc.hep.common.domain.QueryRequest;
import cc.hep.common.domain.ResponseBo;
import cc.hep.common.util.FileUtil;
import cc.hep.system.domain.InOutStorehouse;

import cc.hep.system.service.InOutStorehouseService;

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
public class InOutStorehouseController  extends BaseController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private InOutStorehouseService inOutStorehouseService;

    @Log("获取入库信息")
    @RequestMapping("inStorehouse")
    @RequiresPermissions("inStorehouse:list")
    public String inStorehouseIndex() {
        return "system/inoutStorehouse/inStorehouse";
    }

    @Log("获取出库信息")
    @RequestMapping("outStorehouse")
    @RequiresPermissions("outStorehouse:list")
    public String outStorehouseIndex() {
        return "system/inoutStorehouse/outStorehouse";
    }

    @RequestMapping("inStorehouse/list")
    @RequiresPermissions("inStorehouse:list")
    @ResponseBody
    public Map<String, Object>inStorehouseList(QueryRequest request, InOutStorehouse inStorehouse) {
        System.out.println(inStorehouse);
        return super.selectByPageNumSize(request, () -> this.inOutStorehouseService.findAllInStorehouse(inStorehouse, request));
    }

    @RequestMapping("outStorehouse/list")
    @RequiresPermissions("outStorehouse:list")
    @ResponseBody
    public Map<String, Object>outStorehouseList(QueryRequest request, InOutStorehouse outStorehouse) {
        return super.selectByPageNumSize(request, () -> this.inOutStorehouseService.findAllOutStorehouse(outStorehouse, request));
    }

    @RequestMapping("inStorehouse/excel")
    @ResponseBody
    public ResponseBo inStorehouse(InOutStorehouse inStorehouse) {
        try {
            List<InOutStorehouse> list = this.inOutStorehouseService.findAllInStorehouse(inStorehouse,null);
            return FileUtil.createExcelByPOIKit("入库表", list, InOutStorehouse.class);
        } catch (Exception e) {
            log.error("导出物资信息Excel失败", e);
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }

    @RequestMapping("outStorehouse/excel")
    @ResponseBody
    public ResponseBo outStorehouse(InOutStorehouse outStorehouse) {
        try {
            List<InOutStorehouse> list = this.inOutStorehouseService.findAllOutStorehouse(outStorehouse,null);
            return FileUtil.createExcelByPOIKit("入库表", list, InOutStorehouse.class);
        } catch (Exception e) {
            log.error("导出物资信息Excel失败", e);
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }

    @RequestMapping("inStorehouse/csv")
    @ResponseBody
    public ResponseBo inStorehousesCsv(InOutStorehouse inStorehouse) {
        try {
            List<InOutStorehouse> list = this.inOutStorehouseService.findAllInStorehouse(inStorehouse,null);
            return FileUtil.createCsv("出库表", list, InOutStorehouse.class);
        } catch (Exception e) {
            log.error("获取物资信息Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }

    @RequestMapping("outStorehouse/csv")
    @ResponseBody
    public ResponseBo outStorehouseCsv(InOutStorehouse outStorehouse) {
        try {
            List<InOutStorehouse> list = this.inOutStorehouseService.findAllOutStorehouse(outStorehouse,null);
            return FileUtil.createCsv("出库表", list, InOutStorehouse.class);
        } catch (Exception e) {
            log.error("获取物资信息Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }

    @Log("入库 ")
    @RequiresPermissions("inStorehouse:add")
    @RequestMapping("inStorehouse/add")
    @ResponseBody
    public ResponseBo addInStorehouse(InOutStorehouse inOutStorehouse) {
        try {
            this.inOutStorehouseService.addInOutStorehouse(inOutStorehouse);
            return ResponseBo.ok("入库成功！");
        } catch (Exception e) {
            log.error("入库失败", e);
            return ResponseBo.error("入库失败，请联系网站管理员！");
        }
    }

    @Log("出库 ")
    @RequiresPermissions("outStorehouse:add")
    @RequestMapping("outStorehouse/add")
    @ResponseBody
    public ResponseBo addOutStorehouse(InOutStorehouse inOutStorehouse) {
        try {
            this.inOutStorehouseService.addInOutStorehouse(inOutStorehouse);
            return ResponseBo.ok("出库成功！");
        } catch (Exception e) {
            log.error("出库失败", e);
            return ResponseBo.error("出库失败，请联系网站管理员！");
        }
    }

}
