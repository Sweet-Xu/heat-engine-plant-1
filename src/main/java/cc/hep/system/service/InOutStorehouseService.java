package cc.hep.system.service;

import cc.hep.common.domain.QueryRequest;
import cc.hep.common.service.IService;
import cc.hep.system.domain.InOutStorehouse;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;

@CacheConfig(cacheNames = "InOutStorehouseService")
public interface InOutStorehouseService extends IService<InOutStorehouse> {
    List<InOutStorehouse> findAllInStorehouse(InOutStorehouse inStorehouse, QueryRequest request);

    List<InOutStorehouse> findAllOutStorehouse(InOutStorehouse outStorehouse, QueryRequest request);

    List<InOutStorehouse> findAllInOutStorehouse(InOutStorehouse inOutStorehouse, QueryRequest request);

    void addInOutStorehouse(InOutStorehouse inoutStorehouse);


    void updateInOutStorehouse(InOutStorehouse inStorehouse);


    void deletInOutStorehouse(String InOutStorehouseIds);

}
