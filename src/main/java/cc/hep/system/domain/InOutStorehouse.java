package cc.hep.system.domain;

import cc.hep.common.annotation.ExportConfig;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//物资实体类
@Table(name = "t_in_out_storehouse")
public class InOutStorehouse implements Serializable {
    private static final long serialVersionUID = -4859536617666815969L;

    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "in_out_storehouse_id")
    private Long inOutStorehouseId;

    @Column(name = "resources_id")
    private Long resourcesId;

    @Transient
    @ExportConfig(value = "物资名")
    private String resourcesName;


    @Column(name = "type")
    @ExportConfig(value = "类型")
    private String type;

    @Column(name = "num")
    @ExportConfig(value = "数量")
    private double num;

    @Column(name = "time")
    @ExportConfig(value = "时间")
    private Date time;

    @Column(name = "REMARKS")
    @ExportConfig(value = "备注")
    String remarks;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getInOutStorehouseId() {
        return inOutStorehouseId;
    }

    public void setInOutStorehouseId(Long inOutStorehouseId) {
        this.inOutStorehouseId = inOutStorehouseId;
    }

    public Long getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(Long resourcesId) {
        this.resourcesId = resourcesId;
    }

    public String getResourcesName() {
        return resourcesName;
    }

    public void setResourcesName(String resourcesName) {
        this.resourcesName = resourcesName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "InOutStorehouse{" +
                "inOutStorehouseId=" + inOutStorehouseId +
                ", resourcesId='" + resourcesId + '\'' +
                ", resourcesName='" + resourcesName + '\'' +
                ", resourcesName=" + resourcesName +
                ", type='" + type + '\'' +
                ", num='" + num + '\'' +
                ", time='" + time + '\'' +
                ", remarks='" + remarks + '\'' +
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
        return inOutStorehouseId;
    }


}
