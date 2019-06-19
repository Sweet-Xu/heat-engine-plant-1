package cc.hep.system.service;

import cc.hep.common.domain.QueryRequest;
import cc.hep.common.service.IService;
import cc.hep.system.domain.Equipment;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;

@CacheConfig(cacheNames = "EquipmentService")
public interface EquipmentService  extends IService<Equipment> {

    List<Equipment> findAllEquipments(Equipment equipment, QueryRequest request);

    Equipment findByName(String equipmentName);

    Equipment findById(Long equipmentId);

    void addEquipment(Equipment equipment);

    void updateEquipment(Equipment equipment);

    void deleteEquipments(String equipmentIds);
}
