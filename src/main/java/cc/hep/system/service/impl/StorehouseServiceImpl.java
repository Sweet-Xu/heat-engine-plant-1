package cc.hep.system.service.impl;

import cc.hep.common.domain.QueryRequest;
import cc.hep.common.service.impl.BaseService;
import cc.hep.system.dao.StorehouseMapper;
import cc.hep.system.domain.Equipment;
import cc.hep.system.domain.Storehouse;
import cc.hep.system.service.StorehouseService;
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

@Service("StorehouseService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class StorehouseServiceImpl  extends BaseService<Storehouse> implements StorehouseService {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StorehouseMapper storehouseMapper;

    @Override
    public List<Storehouse> findAllStorehouse(Storehouse storehouse, QueryRequest request) {
        try {
            return this.storehouseMapper.findAllStorehouse(storehouse);
        } catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }

    @Override
    public Storehouse findByName(String storehouseName) {
        Example example = new Example(Equipment.class);
        example.createCriteria().andCondition("lower(storehouse_name) =", storehouseName.toLowerCase());
        List<Storehouse> list = this.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Storehouse findById(Long storehouseId) {
        return this.selectByKey(storehouseId);
    }

    @Override
    public void addStorehouse(Storehouse storehouse) {
        this.save(storehouse);
    }

    @Override
    public void updateStorehouse(Storehouse storehouse) {
        this.updateNotNull(storehouse);
    }

    @Override
    public void deleteStorehouse(String storehouseIds) {
        List<String> list = Arrays.asList(storehouseIds.split(","));
        this.batchDelete(list, "storehouseId", Storehouse.class);
    }
}
