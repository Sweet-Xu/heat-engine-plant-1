package cc.hep.system.dao;

import java.util.List;

import cc.hep.common.config.MyMapper;
import cc.hep.system.domain.User;
import cc.hep.system.domain.UserWithRole;

public interface UserMapper extends MyMapper<User> {

	List<User> findUserWithDept(User user);
	
	List<UserWithRole> findUserWithRole(Long userId);
	
	User findUserProfile(User user);
}