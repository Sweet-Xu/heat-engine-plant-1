package cc.hep.system.domain;
import cc.hep.common.annotation.ExportConfig;

import javax.persistence.*;
import java.io.Serializable;
//仓库实体类
@Table(name = "t_storehouse")
public class Storehouse  implements Serializable{
    private static final long serialVersionUID = -4852738687666810959L;

    /**
     * 仓库状态
     */
    public static final String STATUS_USE = "1";

    public static final String STATUS_UNUSE = "0";

    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "storehouse_id")
    private Long storehouseId;

    @Column(name = "storehouse_name")
    @ExportConfig(value = "仓库名称")
    private String storehouseName;

    @Column(name = "USER_ID")
    private Long userId;

    @Transient
    @ExportConfig(value = "仓库负责人")
    private String userName;

    @Column(name = "address")
    @ExportConfig(value = "仓库地址")
    private String address;

    @Column(name = "REMARKS")
    @ExportConfig(value = "备注")
    private String remarks;

    @Column(name = "STATUS")
    @ExportConfig(value = "状态", convert = "s:0=闲置,1=使用")
    private String status;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static String getStatusUse() {
        return STATUS_USE;
    }

    public static String getStatusUnuse() {
        return STATUS_UNUSE;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Storehouse{" +
                "storehouseId=" + storehouseId +
                ", storehouseName='" + storehouseName + '\'' +
                ", userId='" + userId + '\'' +
                ", userName=" + userName +
                ", address='" + address + '\'' +
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
        return storehouseId;
    }
}
