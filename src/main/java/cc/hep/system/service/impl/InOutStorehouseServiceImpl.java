package cc.hep.system.service.impl;

import cc.hep.common.domain.QueryRequest;
import cc.hep.common.service.impl.BaseService;
import cc.hep.system.dao.InOutStorehouseMapper;
import cc.hep.system.domain.InOutStorehouse;
import cc.hep.system.service.InOutStorehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("InOutStorehouseService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class InOutStorehouseServiceImpl extends BaseService<InOutStorehouse> implements InOutStorehouseService {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private InOutStorehouseMapper inOutStorehouseMapper;

    @Override
    public List<InOutStorehouse> findAllInStorehouse(InOutStorehouse inStorehouse, QueryRequest request) {
        try {
            return this.inOutStorehouseMapper.findAllInStorehouse(inStorehouse);
        } catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<InOutStorehouse> findAllOutStorehouse(InOutStorehouse outStorehouse, QueryRequest request) {
        try {
            return this.inOutStorehouseMapper.findAllOutStorehouse(outStorehouse);
        } catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<InOutStorehouse> findAllInOutStorehouse(InOutStorehouse inOutStorehouse, QueryRequest request) {
        try {
            return this.inOutStorehouseMapper.findAllInOutStorehouse(inOutStorehouse);
        } catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
}

    @Override
    public void addInOutStorehouse(InOutStorehouse inoutStorehouse) {
        //先增加/建设库存，然后记录日志
        /*if(inoutStorehouse.getType().equals("0")) {
            this.resourcesMapper.outResources(inoutStorehouse.getResourcesId()+"",inoutStorehouse.getNum()+"",inoutStorehouse.getRemarks());
        }
        else if(inoutStorehouse.getType().equals("1")) {
            this.resourcesMapper.inResources(inoutStorehouse.getResourcesId()+"",inoutStorehouse.getNum()+"",inoutStorehouse.getRemarks());
        }*/
        this.save(inoutStorehouse);
    }

    @Override
    public void updateInOutStorehouse(InOutStorehouse inStorehouse) {
        this.updateNotNull(inStorehouse);
    }

    @Override
    public void deletInOutStorehouse(String InOutStorehouseIds) {
        List<String> list = Arrays.asList(InOutStorehouseIds.split(","));
        this.batchDelete(list, "inOutStorehouseId", InOutStorehouse.class);
    }


}
