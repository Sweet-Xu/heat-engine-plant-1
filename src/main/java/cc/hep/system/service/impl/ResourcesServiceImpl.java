package cc.hep.system.service.impl;

import cc.hep.common.domain.QueryRequest;
import cc.hep.common.service.impl.BaseService;
import cc.hep.system.dao.ResourcesMapper;
import cc.hep.system.domain.Equipment;
import cc.hep.system.domain.InOutStorehouse;
import cc.hep.system.domain.Resources;
import cc.hep.system.service.InOutStorehouseService;
import cc.hep.system.service.ResourcesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service("ResourcesService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ResourcesServiceImpl  extends BaseService<Resources> implements ResourcesService {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ResourcesMapper resourcesMapper;
    @Autowired
    private InOutStorehouseService inOutStorehouseService;

    @Override
    public List<Resources> findAllResources(Resources resources, QueryRequest request) {
        try {
            return this.resourcesMapper.findAllResources(resources);
        } catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }

    @Override
    public Resources findByName(String resourcesName) {
        Example example = new Example(Equipment.class);
        example.createCriteria().andCondition("lower(resources_name) =", resourcesName.toLowerCase());
        List<Resources> list = this.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Resources findById(Long resourcesId) {
        return this.selectByKey(resourcesId);
    }

    @Override
    @Transactional
    public void addResources(Resources resources) {
        this.save(resources);
    }

    @Override
    @Transactional
    public void updateResources(Resources resources) {
        this.updateNotNull(resources);
    }

    @Override
    @Transactional
    public void deleteResources(String equipmentIds) {
        List<String> list = Arrays.asList(equipmentIds.split(","));
        this.batchDelete(list, "resourcesId", Resources.class);
    }

    @Override
    public void inResources (String resourcesId, String num, String remarks) throws Exception {
        try {
            InOutStorehouse inOutStorehouse = new InOutStorehouse();
            inOutStorehouse.setNum(Integer.parseInt(num));
            inOutStorehouse.setResourcesId((long)Integer.parseInt(resourcesId));
            inOutStorehouse.setRemarks(remarks);
            inOutStorehouse.setTime(new Date());
            inOutStorehouse.setType("1");
            inOutStorehouseService.addInOutStorehouse(inOutStorehouse);
           this.resourcesMapper.inResources(resourcesId,num,remarks);
        } catch (Exception e) {
            log.error("error", e);
            throw new Exception("入库失败");
        }
    }

    @Override
    public void outResources(String resourcesId, String num, String remarks) throws Exception {
        try {
            InOutStorehouse inOutStorehouse = new InOutStorehouse();
            inOutStorehouse.setNum(Integer.parseInt(num));
            inOutStorehouse.setResourcesId((long)Integer.parseInt(resourcesId));
            inOutStorehouse.setRemarks(remarks);
            inOutStorehouse.setTime(new Date());
            inOutStorehouse.setType("0");
            inOutStorehouseService.addInOutStorehouse(inOutStorehouse);
            this.resourcesMapper.outResources(resourcesId,num,remarks);
        } catch (Exception e) {
            log.error("error", e);
            throw new Exception("入库失败");
        }
    }
}
