package org.jeecf.manager.listen;

import org.jeecf.manager.subject.SubjectContext;
/**
 * 监听者 接口
 * @author jianyiming
 *
 */
public interface Listener {
	/**
	 * 条件成立通知
	 * @param context
	 */
	public void notice(SubjectContext context);

}
