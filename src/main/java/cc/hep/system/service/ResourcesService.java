package cc.hep.system.service;
import cc.hep.common.domain.QueryRequest;
import cc.hep.common.service.IService;
import cc.hep.system.domain.Resources;
import org.springframework.cache.annotation.CacheConfig;
import java.util.List;

@CacheConfig(cacheNames = "ResourcesService")
public interface ResourcesService extends IService<Resources> {

    List<Resources> findAllResources(Resources resources, QueryRequest request);

    Resources findByName(String resourcesName);

    Resources findById(Long resourcesId);

    void addResources(Resources resources);

    void updateResources(Resources resources);

    void deleteResources(String resourcesIds);

    void inResources(String resourcesId, String num, String remarks) throws Exception;

    void outResources(String resourcesId, String num, String remarks) throws Exception;

}
