package cc.hep.system.domain;

import cc.hep.common.annotation.ExportConfig;

import javax.persistence.*;
import java.io.Serializable;

//设备实体类
@Table(name = "t_equipment")
public class Equipment implements Serializable {
    private static final long serialVersionUID = -4852732617666810959L;

    /**
     * 设备状态
     */
    public static final String STATUS_VALID = "1";

    public static final String STATUS_REPAIR = "0";

    public static final String STATUS_SCRAP = "-1";


    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "EQUIPMENT_ID")
    private Long equipmentId;

    @Column(name = "EQUIPMENT_NAME")
    @ExportConfig(value = "设备名称")
    private String equipmentName;

    @Column(name = "MODEL")
    @ExportConfig(value = "设备类型")
    private String model;

    @Column(name = "SUPPLIER")
    @ExportConfig(value = "供应商")
    private String supplier;

    @Column(name = "DESCRIBE0")
    @ExportConfig(value = "描述")
    private String describe;

    @Column(name = "USER_ID")
    private Long userId;

    @Transient
    @ExportConfig(value = "负责人")
    private String userName;


    @Column(name = "REMARKS")
    @ExportConfig(value = "备注")
    private String remarks;

    @Column(name = "STATUS")
    @ExportConfig(value = "状态", convert = "s:0=维修中,1=正在使用,2=报废")
    private String status = STATUS_VALID;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static String getStatusValid() {
        return STATUS_VALID;
    }

    public static String getStatusRepair() {
        return STATUS_REPAIR;
    }

    public static String getStatusScrap() {
        return STATUS_SCRAP;
    }

    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "equipmentId=" + equipmentId +
                ", equipmentName='" + equipmentName + '\'' +
                ", model='" + model + '\'' +
                ", supplier=" + supplier +
                ", describe='" + describe + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", remarks='" + remarks + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    /**
     * shiro-redis v3.1.0 必须要有getAuthCacheKey()或者getId()方法
     * # Principal id field name. The field which you can get unique id to identify this principal.
     * # For example, if you use UserInfo as Principal class, the id field maybe userId, userName, email, etc.
     * # Remember to add getter to this id field. For example, getUserId(), getUserName(), getEmail(), etc.
     * # Default value is authCacheKey or id, that means your principal object has EquipmentMapper method called "getAuthCacheKey()" or "getId()"
     * @return userId as Principal id field name
     */
    public Long getAuthCacheKey() {
        return equipmentId;
    }


}
