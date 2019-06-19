package cc.hep.system.domain;

import cc.hep.common.annotation.ExportConfig;
import javax.persistence.*;
import java.io.Serializable;

//物资实体类
@Table(name = "t_resources")
public class Resources implements Serializable {
    private static final long serialVersionUID = -4852732617666815969L;

    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "resources_id")
    private Long resourcesId;

    @Column(name = "resources_no")
    @ExportConfig(value = "物资编号")
    private String resourcesNo;

    @Column(name = "resources_name")
    @ExportConfig(value = "物资名")
    private String resourcesName;

    @Column(name = "type")
    @ExportConfig(value = "种类")
    private String type;

    @Column(name = "price")
    @ExportConfig(value = "单价")
    private double price;

    @Column(name = "weight")
    @ExportConfig(value = "剩余重量")
    private double weight;

    @Column(name = "storehouse_id")
    private Long storehouseId;

    @Transient
    @ExportConfig(value = "所属仓库")
    private String storehouseName;

    @Transient
    @ExportConfig(value = "所在位置")
    private String address;


    @Column(name = "REMARKS")
    @ExportConfig(value = "备注")
    private String remarks;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(Long resourcesId) {
        this.resourcesId = resourcesId;
    }

    public String getResourcesNo() {
        return resourcesNo;
    }

    public void setResourcesNo(String resourcesNo) {
        this.resourcesNo = resourcesNo;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Long getStorehouseId() {
        return storehouseId;
    }

    public void setStorehouseId(Long storehouseId) {
        this.storehouseId = storehouseId;
    }

    public String getStorehouseName() {
        return storehouseName;
    }

    public void setStorehouseName(String storehouseName) {
        this.storehouseName = storehouseName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Resources{" +
                "resourcesId=" + resourcesId +
                ", resourcesNo='" + resourcesNo + '\'' +
                ", resourcesName='" + resourcesName + '\'' +
                ", type=" + type +
                ", price='" + price + '\'' +
                ", weight='" + weight + '\'' +
                ", storehouseId='" + storehouseId + '\'' +
                ", storehouseName='" + storehouseName + '\'' +
                ", address='" + address + '\'' +
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
        return resourcesId;
    }


}
