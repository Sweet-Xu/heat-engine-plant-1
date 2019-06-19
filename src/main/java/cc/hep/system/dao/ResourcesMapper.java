package cc.hep.system.dao;
import cc.hep.common.config.MyMapper;
import cc.hep.system.domain.Resources;

import java.util.List;

public interface ResourcesMapper extends MyMapper<Resources> {
    List<Resources> findAllResources(Resources resources);

    void inResources(String resourcesId, String num, String remarks);

    void outResources(String resourcesId, String num, String remarks);
}
