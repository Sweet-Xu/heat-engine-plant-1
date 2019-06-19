package cc.hep.system.service;

import java.util.List;

import cc.hep.system.domain.UserOnline;

public interface SessionService {

	List<UserOnline> list();

	boolean forceLogout(String sessionId);
}
