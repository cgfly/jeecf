package org.jeecf.manager.gen.model.rule;

import java.util.List;

/**
 * 规则实体
 * 
 * @author jianyiming
 *
 */
public class RuleContext {

	/**
	 * 规则名字
	 */
	private String name = "defualt";

	/**
	 * 是否获取数据
	 */
	private boolean data = false;
	/**
	 * 过滤组合
	 */
	private List<FilterEntity> filterEntitys;
	/**
	 * 策略实体
	 */
	private StrategyEntity strategyEntity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isData() {
		return data;
	}

	public void setData(boolean data) {
		this.data = data;
	}

	public List<FilterEntity> getFilterEntitys() {
		return filterEntitys;
	}

	public void setFilterEntitys(List<FilterEntity> filterEntitys) {
		this.filterEntitys = filterEntitys;
	}

	public StrategyEntity getStrategyEntity() {
		return strategyEntity;
	}

	public void setStrategyEntity(StrategyEntity strategyEntity) {
		this.strategyEntity = strategyEntity;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object o){ 
		if((o instanceof RuleContext) && ((RuleContext)o).name == null && name == null){
			return true;
		}
		return (o instanceof RuleContext) && (name.equals(((RuleContext)o).name));
	}
	

}
