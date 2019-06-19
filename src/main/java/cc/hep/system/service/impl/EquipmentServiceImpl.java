package cc.hep.system.service.impl;

import cc.hep.common.domain.QueryRequest;
import cc.hep.common.service.impl.BaseService;
import cc.hep.system.dao.EquipmentMapper;
import cc.hep.system.domain.Equipment;
import cc.hep.system.service.EquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("EquipmentService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class EquipmentServiceImpl  extends BaseService<Equipment> implements EquipmentService {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public List<Equipment> findAllEquipments(Equipment equipment, QueryRequest request) {
        try {
            return this.equipmentMapper.findAllEquipments(equipment);
        } catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }

    @Override
    public Equipment findByName(String equipmentName) {
        Example example = new Example(Equipment.class);
        example.createCriteria().andCondition("lower(equipment_name) =", equipmentName.toLowerCase());
        List<Equipment> list = this.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    @Transactional
    public void addEquipment(Equipment equipment) {
        this.save(equipment);
    }

    @Override
    @Transactional
    public void deleteEquipments(String equipmentIds) {
        List<String> list = Arrays.asList(equipmentIds.split(","));
        this.batchDelete(list, "equipmentId", Equipment.class);
    }

    @Override
    public Equipment findById(Long equipmentId) {
        return this.selectByKey(equipmentId);
    }

    @Override
    @Transactional
    public void updateEquipment(Equipment equipment) {
        this.updateNotNull(equipment);
    }

}
