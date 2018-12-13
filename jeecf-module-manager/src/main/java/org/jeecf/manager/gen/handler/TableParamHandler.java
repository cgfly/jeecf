package org.jeecf.manager.gen.handler;

import java.util.Map;

import org.jeecf.manager.common.chain.AbstractHandler;
import org.jeecf.manager.common.chain.ChainContext;
import org.jeecf.manager.common.enums.EnumUtils;
import org.jeecf.manager.gen.builder.GoBuilder;
import org.jeecf.manager.gen.builder.JavaBuilder;
import org.jeecf.manager.gen.language.java.model.JavaTable;

/**
 * 表参数责任链
 * 
 * @author jianyiming
 *
 */
public class TableParamHandler extends AbstractHandler {

	private static JavaBuilder javaBuilder = new JavaBuilder();

	private static GoBuilder goBuilder = new GoBuilder();

	@Override
	public void init(ChainContext context) {
		this.chainContext = context;
	}

	@Override
	public void process() {
		@SuppressWarnings("unchecked")
		Map<String, Object> params = (Map<String, Object>) this.chainContext.get("params");
		Integer tableId = (Integer) this.chainContext.get("tableId");
		Integer language = (Integer) this.chainContext.get("language");
		Object baseTable = new JavaTable();
		String data = "{}";
		if (tableId != null && tableId > 0) {
			if (language == EnumUtils.Language.JAVA.getCode()) {
				baseTable = javaBuilder.build(tableId);
			    data = javaBuilder.getData();
			} else if (language == EnumUtils.Language.GO.getCode()) {
				baseTable = goBuilder.build(tableId);
				data = javaBuilder.getData();
			} 
		}
		params.put("table", baseTable);
		params.put("data", data);
		this.chainContext.put("params", params);
		this.chainContext.next();
	}

}
