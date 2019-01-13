package org.jeecf.manager.engine.model.update;

import java.util.List;

import org.jeecf.manager.engine.model.BaseTable;
import org.jeecf.manager.engine.utils.JniValidate;

/**
 * 更新表
 * 
 * @author jianyiming
 *
 */
public class UpdateTable extends BaseTable {
	
	protected UpdateTable() {
	}
    /**
     * 主键
     */
	private Object id;
	
	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	private List<UpdateTableColumn> updateTableColumns;

	public List<UpdateTableColumn> getUpdateTableColumns() {
		return updateTableColumns;
	}

	public void setUpdateTableColumns(List<UpdateTableColumn> updateTableColumns) {
		this.updateTableColumns = updateTableColumns;
	}

	public static class Builder {

		public static UpdateTable build(String name, String tableName) {
			UpdateTable updateTable = new UpdateTable();
			updateTable.setName(JniValidate.columnValidate(name));
			updateTable.setTableName(JniValidate.columnValidate(tableName));
			return updateTable;
		}

	}

}
