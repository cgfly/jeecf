package org.jeecf.gen.chain;

/**
 * 责任链抽象表示
 * 
 * @author jianyiming
 * @since 1.0
 */
public abstract class AbstractHandler {
    /**
     * 责任链上下文
     */
    protected ChainContext chainContext = null;
    /**
     * 上下参数
     */
    protected ContextConfigParams contextParams = null;

    /**
     * 责任链初始方法
     * 
     * @param context
     */
    public void init(ChainContext context) {
        this.chainContext = context;
        this.contextParams = context.getContextParams();
    }

    /**
     * 责任链进程
     */
    public abstract void process();

}
