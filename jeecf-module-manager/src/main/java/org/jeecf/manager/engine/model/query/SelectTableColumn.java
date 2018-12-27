package org.jeecf.manager.engine.model.query;

import org.jeecf.manager.engine.model.BaseTableColumn;
import org.jeecf.manager.engine.utils.JniValidate;

/**
 * 查询表字段
 * 
 * @author jianyiming
 *
 */
public class SelectTableColumn extends BaseTableColumn {
	
	protected SelectTableColumn() {}
	
	public static class Builder {
		
		public static SelectTableColumn build(String name, String columnName) {
			SelectTableColumn selectTableColumn = new SelectTableColumn();
			selectTableColumn.setColumnName(JniValidate.columnValidate(columnName));
			selectTableColumn.setName(name);
			return selectTableColumn;
		}
	}

}
