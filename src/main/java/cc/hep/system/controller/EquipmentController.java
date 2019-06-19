package cc.hep.system.controller;

import cc.hep.common.annotation.Log;
import cc.hep.common.controller.BaseController;
import cc.hep.common.domain.QueryRequest;
import cc.hep.common.domain.ResponseBo;
import cc.hep.common.util.FileUtil;
import cc.hep.system.domain.Equipment;
import cc.hep.system.service.EquipmentService;
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
public class EquipmentController extends BaseController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EquipmentService equipmentService;

    @Log("获取设备信息")
    @RequestMapping("equipment")
    @RequiresPermissions("equipment:list")
    public String index() {
        return "system/equipment/equipment";
    }


    @RequestMapping("equipment/getEquipment")
    @ResponseBody
    public ResponseBo getEquipment(Long equipmentId) {
        try {
            Equipment equipment = this.equipmentService.findById(equipmentId);
            return ResponseBo.ok(equipment);
        } catch (Exception e) {
            log.error("获取设备信息失败", e);
            return ResponseBo.error("获取设备信息失败，请联系网站管理员！");
        }
    }

    @RequestMapping("equipment/list")
    @RequiresPermissions("equipment:list")
    @ResponseBody
    public Map<String, Object> equipmentList(QueryRequest request, Equipment equipment) {
        return super.selectByPageNumSize(request, () -> this.equipmentService.findAllEquipments(equipment, request));
    }

    @RequestMapping("equipment/excel")
    @ResponseBody
    public ResponseBo equipmentExcel(Equipment equipment) {
        try {
            List<Equipment> list = this.equipmentService.findAllEquipments(equipment,null);
            return FileUtil.createExcelByPOIKit("设备表", list, Equipment.class);
        } catch (Exception e) {
            log.error("导出设备信息Excel失败", e);
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }

    @RequestMapping("equipment/csv")
    @ResponseBody
    public ResponseBo equipmentCsv(Equipment equipment) {
        try {
            List<Equipment> list = this.equipmentService.findAllEquipments(equipment,null);
            return FileUtil.createCsv("设备表", list, Equipment.class);
        } catch (Exception e) {
            log.error("获取设备信息Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }

    @RequestMapping("equipment/checkEquipmentName")
    @ResponseBody
    public boolean checkEquipmentName(String equipmentName, String oldEquipmentName) {
        if (StringUtils.isNotBlank(oldEquipmentName) && equipmentName.equalsIgnoreCase(oldEquipmentName)) {
            return true;
        }
        Equipment result = this.equipmentService.findByName(equipmentName);
        return result == null;
    }

    @Log("新增设备 ")
    @RequiresPermissions("equipment:add")
    @RequestMapping("equipment/add")
    @ResponseBody
    public ResponseBo addEquipment(Equipment equipment) {
        try {
            this.equipmentService.addEquipment(equipment);
            return ResponseBo.ok("新增设备成功！");
        } catch (Exception e) {
            log.error("新增设备失败", e);
            return ResponseBo.error("新增设备失败，请联系网站管理员！");
        }
    }

    @Log("删除设备")
    @RequiresPermissions("equipment:delete")
    @RequestMapping("equipment/delete")
    @ResponseBody
    public ResponseBo deleteEquipment(String ids) {
        try {
            this.equipmentService.deleteEquipments(ids);
            return ResponseBo.ok("删除设备成功！");
        } catch (Exception e) {
            log.error("删除设备失败", e);
            return ResponseBo.error("删除设备失败，请联系网站管理员！");
        }
    }

    @Log("修改物资 ")
    @RequiresPermissions("equipment:update")
    @RequestMapping("equipment/update")
    @ResponseBody
    public ResponseBo updateEquipment(Equipment equipment) {
        try {
            this.equipmentService.updateEquipment(equipment);
            return ResponseBo.ok("修改设备成功！");
        } catch (Exception e) {
            log.error("修改设备失败", e);
            return ResponseBo.error("修改设备失败，请联系网站管理员！");
        }
    }
}
