package org.jeecf.manager.listen;

import org.jeecf.manager.common.enums.ActionTypeEnum;
import org.jeecf.manager.common.properties.ThreadLocalProperties;
import org.jeecf.manager.common.utils.SpringContextUtils;
import org.jeecf.manager.module.operation.model.domain.CustomerActionLog;
import org.jeecf.manager.module.operation.service.CustomerActionLogService;
import org.jeecf.manager.subject.SubjectContext;
import org.jeecf.manager.subject.UserContextField;

/**
 * 用户登出 监听
 * 
 * @author jianyiming
 *
 */
public class UserLogoutListener implements Listener {

	private ThreadLocalProperties threadLocalProperties = null;

	private CustomerActionLogService customerActionLogService = null;

	private void initParam() {
		if (threadLocalProperties == null) {
			threadLocalProperties = SpringContextUtils.getBean("threadLocalProperties", ThreadLocalProperties.class);
		}
		if (customerActionLogService == null) {
			customerActionLogService = SpringContextUtils.getBean("customerActionLogService",
					CustomerActionLogService.class);
		}
	}

	@Override
	public void notice(SubjectContext context) {
		initParam();
		String ip = threadLocalProperties.get("ip");
		CustomerActionLog customerActionLog = new CustomerActionLog();
		customerActionLog.setIp(ip);
		customerActionLog.setUserName(context.get(UserContextField.USER_NAME));
		customerActionLog.setActionType(ActionTypeEnum.LOGOUT.getCode());
		customerActionLog.setCreateBy(context.get(UserContextField.USER_ID));
		customerActionLog.setUpdateBy(context.get(UserContextField.USER_ID));
		customerActionLogService.insert(customerActionLog);
	}

}
