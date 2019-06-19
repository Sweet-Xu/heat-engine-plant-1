package cc.hep.system.dao;

import cc.hep.common.config.MyMapper;
import cc.hep.system.domain.InOutStorehouse;

import java.util.List;

public interface InOutStorehouseMapper extends MyMapper<InOutStorehouse> {
    List<InOutStorehouse> findAllInStorehouse(InOutStorehouse inStorehouse);

    List<InOutStorehouse> findAllOutStorehouse(InOutStorehouse outStorehouse);

    List<InOutStorehouse> findAllInOutStorehouse(InOutStorehouse inOutStorehouse);

}
