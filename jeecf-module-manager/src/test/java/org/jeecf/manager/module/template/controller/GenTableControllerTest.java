package org.jeecf.manager.module.template.controller;

import org.jeecf.common.model.Request;
import org.jeecf.manager.Application;
import org.jeecf.manager.module.template.model.domain.GenTable;
import org.jeecf.manager.module.template.model.query.GenTableColumnQuery;
import org.jeecf.manager.module.template.model.query.GenTableQuery;
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
		System.out.println(responseString);
	}

	@Test
	public void save() throws Exception {
		GenTable genTable = new GenTable();
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(genTable);
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/template/genTable/save").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		System.out.println(responseString);
	}

	@Test
	public void delete() throws Exception {
		String id = "1";
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/template/genTable/delete/"+id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		System.out.println(responseString);
	}
	
	
	@Test
	public void getBaseTableList() throws Exception {
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/template/genTable/queryBaseTableList").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		System.out.println(responseString);
	}
	
	@Test
	public void getBaseTableColumnList() throws Exception{
		GenTableColumnQuery genTableColumn = new GenTableColumnQuery();
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(genTableColumn);
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/template/genTable/queryBaseTableColumnList").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		System.out.println(responseString);
	}

}
