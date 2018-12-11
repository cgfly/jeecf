package org.jeecf.manager.module.config.controller;

import org.jeecf.common.model.Page;
import org.jeecf.common.model.Request;
import org.jeecf.manager.Application;
import org.jeecf.manager.module.config.model.domain.SysNamespace;
import org.jeecf.manager.module.config.model.query.SysNamespaceQuery;
import org.jeecf.manager.module.config.model.schema.SysNamespaceSchema;
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
 * 系统命名空间 测试
 * @author jianyiming
 *
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SysNamespaceControllerTest extends BaseMokMvc{
	
	@Before
	public void setUp() throws Exception {
		super.init("admin", "123456");
	}

	@Test
	public void list() throws Exception {
		Request<SysNamespaceQuery, SysNamespaceSchema> request = new Request<SysNamespaceQuery, SysNamespaceSchema>();
		Page page = new Page();
		page.setCurrent(1);
		page.setSize(8);
		SysNamespaceQuery query = new SysNamespaceQuery();
		request.setData(query);
		request.setPage(page);
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(request);
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/config/sysNamespace/list").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		System.out.println(responseString);
	}
	
	@Test
	public void save() throws Exception {
		SysNamespace sysNamespace = new SysNamespace();
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(sysNamespace);
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/config/sysNamespace/save").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		System.out.println(responseString);
	}
	
	@Test
	public void delete() throws Exception {
		String id = "";
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/config/sysNamespace/delete/"+id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		System.out.println(responseString);
	}
	
	@Test
	public void effect() throws Exception {
		String id = "";
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/config/sysNamespace/effect/"+id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		System.out.println(responseString);
	}

}
