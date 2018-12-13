package org.jeecf.manager.module.template.controller;

import java.util.ArrayList;
import java.util.List;

import org.jeecf.common.mapper.JsonMapper;
import org.jeecf.common.model.Request;
import org.jeecf.manager.Application;
import org.jeecf.manager.module.template.model.domain.GenTable;
import org.jeecf.manager.module.template.model.query.GenTableQuery;
import org.jeecf.manager.module.template.model.result.GenTableColumnResult;
import org.jeecf.manager.module.template.model.schema.GenTableSchema;
import org.jeecf.manager.test.helper.BaseMokMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * 业务表测试
 * @author jianyiming
 *
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class GenTableControllerTest extends BaseMokMvc{
	
	@Before
	public void setUp() throws Exception {
		super.init("admin", "123456");
	}
	
	@Test
	public void list() throws Exception {
		Request<GenTableQuery,GenTableSchema> request = new Request<GenTableQuery,GenTableSchema>();
		GenTableQuery query = new GenTableQuery();
		request.setData(query);
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(request);
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/template/genTable/list").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		assert JsonMapper.getJsonNode(responseString).get("success").asBoolean();
	}

	@Test
	public void getBaseTableList() throws Exception {
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/template/genTable/queryBaseTableList").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		assert JsonMapper.getJsonNode(responseString).get("success").asBoolean();
	}
	
	@Test
	public void getBaseTableColumnList() throws Exception{
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/template/genTable/queryBaseTableColumnList/sys_dict").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		assert JsonMapper.getJsonNode(responseString).get("success").asBoolean();
	}
	
	@Test
	public void dataOperation() throws Exception {
		GenTable genTable = new GenTable();
		genTable.setName("test");
		genTable.setClassName("test");
		List<GenTableColumnResult> genTableColumnList = new ArrayList<>();
		GenTableColumnResult genTableColumn = new GenTableColumnResult();
		genTableColumn.setComment("test");
		genTableColumn.setField("test");
		genTableColumn.setFormType(1);
		genTableColumn.setIsEdit(1);
		genTableColumn.setIsInsert(1);
		genTableColumn.setIsKey(1);
		genTableColumn.setIsList(1);
		genTableColumn.setIsNull(1);
		genTableColumn.setIsQuery(1);
		genTableColumn.setJdbcType("varchar");
		genTableColumn.setName("test");
		genTableColumn.setQueryType(1);
		genTableColumn.setSort(10);
		genTableColumnList.add(genTableColumn);
		genTable.setGenTableColumns(genTableColumnList);
		JsonNode saveNode = JsonMapper.getJsonNode(this.save(genTable));
		if (saveNode.get("success").asBoolean()) {
			genTable.setName("saveUpdate");
			genTable.setId(saveNode.get("data").get("id").asText());
			JsonNode updateNode = JsonMapper.getJsonNode(this.save(genTable));
			if (updateNode.get("success").asBoolean()) {
				JsonNode deleteNode = JsonMapper.getJsonNode(this.delete(genTable.getId()));
				assert deleteNode.get("success").asBoolean();
			}
			assert updateNode.get("success").asBoolean();
		}
		assert saveNode.get("success").asBoolean();
	}
	
	private String save(GenTable genTable) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(genTable);
		return mockMvc
				.perform(MockMvcRequestBuilders.post("/template/genTable/save").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
	}

	private String delete(String id) throws Exception {
		return mockMvc
				.perform(MockMvcRequestBuilders.post("/template/genTable/delete/"+id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
	}

}
