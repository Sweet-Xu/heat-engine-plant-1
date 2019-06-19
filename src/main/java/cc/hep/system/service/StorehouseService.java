package cc.hep.system.service;

import cc.hep.common.domain.QueryRequest;
import cc.hep.system.domain.Storehouse;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;

@CacheConfig(cacheNames = "StorehouseService")
public interface StorehouseService {
    List<Storehouse> findAllStorehouse(Storehouse storehouse, QueryRequest request);

    Storehouse findByName(String storehouseName);

    Storehouse findById(Long storehouseId);

    void addStorehouse(Storehouse storehouse);

    void updateStorehouse(Storehouse storehouse);

    void deleteStorehouse(String storehouseIds);
}
