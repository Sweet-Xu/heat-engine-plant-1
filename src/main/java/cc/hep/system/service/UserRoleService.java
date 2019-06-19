package cc.hep.system.service;

import cc.hep.common.service.IService;
import cc.hep.system.domain.UserRole;

public interface UserRoleService extends IService<UserRole> {

	void deleteUserRolesByRoleId(String roleIds);

	void deleteUserRolesByUserId(String userIds);
}
