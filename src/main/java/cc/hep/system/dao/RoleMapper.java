package cc.hep.system.dao;

import java.util.List;

import cc.hep.common.config.MyMapper;
import cc.hep.system.domain.Role;
import cc.hep.system.domain.RoleWithMenu;

public interface RoleMapper extends MyMapper<Role> {
	
	List<Role> findUserRole(String userName);
	
	List<RoleWithMenu> findById(Long roleId);
}