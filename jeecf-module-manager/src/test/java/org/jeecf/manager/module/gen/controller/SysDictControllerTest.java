package org.jeecf.manager.module.gen.controller;

import org.jeecf.common.model.Request;
import org.jeecf.manager.Application;
import org.jeecf.manager.module.gen.model.domian.SysDict;
import org.jeecf.manager.module.gen.model.query.SysDictQuery;
import org.jeecf.manager.module.gen.model.schema.SysDictSchema;
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
 * 系统数字字典 测试
 * 
 * @author jianyiming
 *
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SysDictControllerTest extends BaseMokMvc {

	@Before
	public void setUp() throws Exception {
		super.init("admin", "123456");
	}

	@Test
	public void list() throws Exception {
		Request<SysDictQuery, SysDictSchema> request = new Request<SysDictQuery, SysDictSchema>();
		SysDictQuery query = new SysDictQuery();
		request.setData(query);
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(request);
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/gen/sysDict/list").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		System.out.println(responseString);
	}

	@Test
	public void save() throws Exception {
		SysDict sysDict = new SysDict();
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(sysDict);
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/gen/sysDict/save").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		System.out.println(responseString);
	}

	@Test
	public void delete() throws Exception {
		String id = "1";
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/gen/sysDict/delete/"+id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		System.out.println(responseString);
	}

	public void genCreate() throws Exception {
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/gen/sysDict/genCreate")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		System.out.println(responseString);
	}

	
	public void downloads() throws Exception {
		String basePath = "";
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/gen/sysDict/"+basePath)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		System.out.println(responseString);
	}

}
