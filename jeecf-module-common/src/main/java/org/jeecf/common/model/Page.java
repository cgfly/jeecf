package org.jeecf.common.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页实体
 * 
 * @author jianyiming
 *
 */
@ApiModel(value = "page", description = "分页实体")
public class Page implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", name = "current")
    private int current;

    /**
     * 每页大小
     */
    @ApiModelProperty(value = "每页大小", name = "size")
    private int size;

    /**
     * 总共条数
     */
    @ApiModelProperty(value = "总共条数", name = "total")
    private int total;

    /**
     * 起始条数
     */
    @ApiModelProperty(value = "起始条数", name = "startNo")
    private int startNo;

    public Page() {

    }

    /**
     * 
     * @param current
     * @param size
     */
    public Page(int current, int size) {
        this(current, size, 0);
    }

    /**
     * 
     * @param current
     * @param size
     * @param total
     */
    public Page(int current, int size, int total) {
        this.current = current;
        this.size = size;
        this.total = total;
        this.setStartNo();
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStartNo() {
        return startNo;
    }

    public void setStartNo() {
        // 设置起始条数
        if (this.current >= 0 && this.size > 0) {
            if (this.current < 1) {
                this.current = 1;
            }
            this.startNo = (this.current - 1) * this.size;
        }
    }

}
