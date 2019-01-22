package org.jeecf.manager.subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecf.manager.common.utils.UserUtils;
import org.jeecf.manager.listen.Listener;
import org.jeecf.manager.module.userpower.model.domain.SysUser;

/**
 * 用户主题
 * 
 * @author jianyiming
 *
 */
public class UserSubject implements Subject {

    public static final String[] TOPIC_ELEMENTS = {"login", "logout"};

    private Map<Listener, String> listeners = new HashMap<>();

    @Override
    public void register(String topic, Listener listener) {
        String[] topics = UserSubject.TOPIC_ELEMENTS;
        for (String tp : topics) {
            if (tp.equals(topic)) {
                listeners.put(listener, topic);
                return;
            }
        }
    }

    private List<Listener> getListeners(String topic) {
        List<Listener> listenersTopic = new ArrayList<>();
        for (Map.Entry<Listener, String> entry : listeners.entrySet()) {
            if (entry.getValue().equals(topic)) {
                listenersTopic.add(entry.getKey());
            }
        }
        return listenersTopic;
    }

    /**
     * 用户登录后 监听
     * 
     * @param user
     */
    public void updateLogin(SysUser sysUser) {
        SubjectContext context = new SubjectContext();
        Map<String, String> data = new HashMap<>(10);
        data.put(UserContextField.USER_ID, UserUtils.getCurrentUserId());
        data.put(UserContextField.USER_NAME, sysUser.getUsername());
        context.setData(data);
        List<Listener> listenersTopic = getListeners(TOPIC_ELEMENTS[0]);
        for (Listener listener : listenersTopic) {
            listener.notice(context);
        }
    }

    /**
     * 用户退出后 监听
     * 
     * @param user
     */
    public void updateLogout(SysUser sysUser) {
        SubjectContext context = new SubjectContext();
        Map<String, String> data = new HashMap<>(10);
        data.put(UserContextField.USER_ID, sysUser.getId());
        data.put(UserContextField.USER_NAME, sysUser.getUsername());
        context.setData(data);
        List<Listener> listenersTopic = getListeners(TOPIC_ELEMENTS[1]);
        for (Listener listener : listenersTopic) {
            listener.notice(context);
        }
    }

}
