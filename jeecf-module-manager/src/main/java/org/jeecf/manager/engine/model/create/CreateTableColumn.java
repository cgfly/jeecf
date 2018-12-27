package org.jeecf.manager.engine.model.create;

import org.jeecf.manager.engine.enums.DefaultValueEnum;
import org.jeecf.manager.engine.enums.NullModelEnum;
import org.jeecf.manager.engine.enums.PrimaryKeyEnum;
import org.jeecf.manager.engine.enums.TableTypeEnum;
import org.jeecf.manager.engine.exception.TableColumnCommentEmptyException;
import org.jeecf.manager.engine.exception.TableColumnNullException;
import org.jeecf.manager.engine.exception.TableColumnTypeEmptyException;
import org.jeecf.manager.engine.model.AbstractTableColumn;
import org.jeecf.manager.engine.utils.JniValidate;

import com.alibaba.druid.util.StringUtils;

/**
 * 创建表 列表
 * 
 * @author jianyiming
 *
 */
public class CreateTableColumn extends AbstractTableColumn {
	
	protected CreateTableColumn() {}
    /**
     * 类型
     */
	private String type;
    /**
     * 为空
     */
	private String nullModel;
    /**
     * 默认值
     */
	private String defaultValue;
    /**
     * 注释
     */
	private String comment;
    /**
     * 主键
     */
	private String primaryKey;

	public String getType() {
		return type;
	}

	protected void setType(String type) {
		this.type = type;
	}

	public String getNullModel() {
		return nullModel;
	}

	protected void setNullModel(String nullModel) {
		this.nullModel = nullModel;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	protected void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getComment() {
		return comment;
	}

	protected void setComment(String comment) {
		this.comment = comment;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	protected void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public static Builder builder() {
		return new Builder(new CreateTableColumn());
	}

	public static class Builder {

		private CreateTableColumn createTableColumn;

		protected Builder() {
		};

		protected Builder(CreateTableColumn createTableColumn) {
			this.createTableColumn = createTableColumn;
		};

		public Builder isPrimaryKey(boolean auto) {
			if (auto) {
				this.createTableColumn.setPrimaryKey(PrimaryKeyEnum.AUTO.getName());
			} else {
				this.createTableColumn.setPrimaryKey(PrimaryKeyEnum.NOT_AUTO.getName());
			}
			return this;
		}

		public Builder setType(String type) {
			this.createTableColumn.setType(JniValidate.typeValidate(type));
			return this;
		}

		public Builder setColumnName(String columnName) {
			this.createTableColumn.setColumnName(JniValidate.columnValidate(columnName));
			return this;
		}

		public Builder setComment(String comment) {
			this.createTableColumn.setComment(comment);
			return this;
		}

		public Builder setNullModel(NullModelEnum nullModelEnum) {
			this.createTableColumn.setNullModel(nullModelEnum.getName());
			return this;
		}

		public Builder setDefaultValue(String defaultValue) {
			this.createTableColumn.setDefaultValue(JniValidate.columnValidate(defaultValue));
			return this;
		}

		public CreateTableColumn build() {
			if (StringUtils.isEmpty(this.createTableColumn.getColumnName())) {
				throw new TableColumnNullException();
			}
			if (StringUtils.isEmpty(this.createTableColumn.getType())) {
				throw new TableColumnTypeEmptyException();
			}
			if (StringUtils.isEmpty(this.createTableColumn.getNullModel())) {
				this.createTableColumn.setNullModel(NullModelEnum.NOT_NULL.getName());
			}
			if (StringUtils.isEmpty(this.createTableColumn.getComment())) {
				throw new TableColumnCommentEmptyException();
			}
			if (StringUtils.isEmpty(this.createTableColumn.getDefaultValue())) {
				String type = this.createTableColumn.getType();
				String[] types = type.split("\\(");
                if(TableTypeEnum.containsWithNumber(types[0]) || TableTypeEnum.containsWithFloat(types[0])) {
                	this.createTableColumn.setDefaultValue(DefaultValueEnum.ZERO.getName());
                } else if(TableTypeEnum.containsWithStr(types[0])) {
                	this.createTableColumn.setDefaultValue(DefaultValueEnum.EMPTY_STRING.getName());
                } else if(TableTypeEnum.containsWithDate(types[0])) {
                	this.createTableColumn.setDefaultValue(DefaultValueEnum.CURRENT_TIMESTAMP.getName());
                }
			}

			return this.createTableColumn;
		}

	}

}
