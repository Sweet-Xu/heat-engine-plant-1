package cc.hep.system.dao;

import cc.hep.common.config.MyMapper;
import cc.hep.system.domain.Equipment;

import java.util.List;

public interface EquipmentMapper extends MyMapper<Equipment> {
    List<Equipment> findAllEquipments(Equipment equipment);
}
