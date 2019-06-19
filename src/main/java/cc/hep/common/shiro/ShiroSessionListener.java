package cc.hep.common.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
//session会话监听类
public class ShiroSessionListener implements SessionListener{

	private final AtomicInteger sessionCount = new AtomicInteger(0);

	//会话创建
	@Override
	public void onStart(Session session) {
		System.out.println("---------------------------------------------------------");
		System.out.println("会话创建");
		sessionCount.incrementAndGet();
	}
	//会话停止
	@Override
	public void onStop(Session session) {
		System.out.println("---------------------------------------------------------");
		System.out.println("会话停止");
		sessionCount.decrementAndGet();
	}
	//会话过期
	@Override
	public void onExpiration(Session session) {
		System.out.println("---------------------------------------------------------");
		System.out.println("会话过期");
		sessionCount.decrementAndGet();
	}
}
