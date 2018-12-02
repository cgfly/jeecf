package org.jeecf.manager.subject;

import org.jeecf.manager.listen.Listener;
/**
 * 监听者 主题接口
 * @author jianyiming
 *
 */
public interface Subject {
	/**
	 * 主题注册
	 * @param topic
	 * @param listener
	 */
	public void register(String topic,Listener listener);

}
