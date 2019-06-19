package cc.hep.system.dao;

import cc.hep.common.config.MyMapper;
import cc.hep.system.domain.Storehouse;

import java.util.List;

public interface StorehouseMapper extends MyMapper<Storehouse> {
    List<Storehouse> findAllStorehouse(Storehouse Storehouse);
}
