package org.jeecf.manager.common.chain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 责任链上下文
 * 
 * @author jianyiming
 *
 */
public class ChainContext {
    /**
     * 存储上下文参数
     */
    private Map<String, Object> params = new HashMap<String, Object>();
    /**
     * 责任链集合
     */
    private List<AbstractHandler> handlers = null;
    /**
     * 责任链指针
     */
    private int index = -1;
    /**
     * 责任链长度
     */
    private int size = -1;
    /**
     * 状态标志
     */
    private boolean flag = true;

    /**
     * 构造器 初始化上下文
     * 
     * @param handlers
     */
    public ChainContext(List<AbstractHandler> handlers) {
        this.handlers = handlers;
        int size = handlers.size();
        if (size > 0) {
            this.size = size;
        }
    }

    /**
     * 赋值上下文参数
     * 
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        params.put(key, value);
    }

    /**
     * 获取上下文参数
     * 
     * @param key
     * @return
     */
    public Object get(String key) {
        return params.get(key);
    }

    /**
     * 执行下一责任节点
     */
    public void next() {
        if (this.index < this.size - 1 && this.isFlag()) {
            this.index++;
            AbstractHandler handler = handlers.get(this.index);
            handler.init(this);
            handler.process();
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}
