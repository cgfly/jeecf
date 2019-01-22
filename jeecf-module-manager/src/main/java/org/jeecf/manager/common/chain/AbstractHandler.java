package org.jeecf.manager.common.chain;

/**
 * 责任链抽象表示
 * 
 * @author jianyiming
 *
 */
public abstract class AbstractHandler {
    /**
     * 责任链上下文
     */
    protected ChainContext chainContext = null;

    /**
     * 责任链初始方法
     * 
     * @param context
     */
    public void init(ChainContext context) {
        this.chainContext = context;
    }

    /**
     * 责任链进程
     */
    public abstract void process();

}
