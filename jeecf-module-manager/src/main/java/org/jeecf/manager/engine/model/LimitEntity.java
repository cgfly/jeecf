package org.jeecf.manager.engine.model;
/**
 * 分页实体
 * @author jianyiming
 *
 */
public class LimitEntity {
	
	protected LimitEntity(){}
	
	private int startNo;
	
	private int size;

	public int getStartNo() {
		return startNo;
	}

	protected void setStartNo(int startNo) {
		this.startNo = startNo;
	}

	public int getSize() {
		return size;
	}

	protected void setSize(int size) {
		this.size = size;
	}
	
	public static class Builder {

		public static LimitEntity build(int startNo,int size) {
			LimitEntity limitEntity = new LimitEntity();
			limitEntity.setStartNo(startNo);
			limitEntity.setSize(size);
			return limitEntity;
		}
		
	}

}
