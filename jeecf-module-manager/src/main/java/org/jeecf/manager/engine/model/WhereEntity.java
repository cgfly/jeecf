package org.jeecf.manager.engine.model;

import org.jeecf.manager.engine.enums.WhereConnectorEnum;
import org.jeecf.manager.engine.enums.WhereExpressEnum;
import org.jeecf.manager.engine.utils.JniValidate;
/**
 * 查询条件实体
 * @author jianyiming
 *
 */
public class WhereEntity {

	private String column;

	private String express;
	
	private String  connector;

	private String value;

	public String getColumn() {
		return column;
	}

	protected void setColumn(String column) {
		this.column = column;
	}

	public String getExpress() {
		return express;
	}

	protected void setExpress(String express) {
		this.express = express;
	}

	public String getValue() {
		return value;
	}

	protected void setValue(String value) {
		this.value = value;
	}
	
	public String getConnector() {
		return connector;
	}

	protected void setConnector(String connector) {
		this.connector = connector;
	}



	public static class Builder {
		
		public static WhereEntity buildOr(String column,WhereExpressEnum expressEnum, String value) {
			return build(column, WhereConnectorEnum.OR.getName(),expressEnum.getName(),value);
		}

		public static WhereEntity buildAnd(String column,WhereExpressEnum expressEnum, String value) {
			return build(column, WhereConnectorEnum.AND.getName(),expressEnum.getName(), value);
		}

		private static WhereEntity build(String column, String connector,String express, String value) {
			WhereEntity whereEntity = new WhereEntity();
			column = JniValidate.columnValidate(column);
			whereEntity.setColumn(column);
			whereEntity.setConnector(connector);
			whereEntity.setExpress(express);
			whereEntity.setValue(value);
			return whereEntity;
		}
	}

}
