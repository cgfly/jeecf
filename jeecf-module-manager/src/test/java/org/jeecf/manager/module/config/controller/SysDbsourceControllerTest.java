package org.jeecf.manager.module.config.controller;

import org.jeecf.common.model.Request;
import org.jeecf.manager.Application;
import org.jeecf.manager.module.config.model.domain.SysDbsource;
import org.jeecf.manager.module.config.model.query.SysDbsourceQuery;
import org.jeecf.manager.module.config.model.schema.SysDbsourceSchema;
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
 * 系统数据源 测试
 * @author jianyiming
 *
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SysDbsourceControllerTest extends BaseMokMvc {
	
	@Before
	public void setUp() throws Exception {
		super.init("admin", "123456");
	}
	
	@Test
	public void list() throws Exception {
		Request<SysDbsourceQuery, SysDbsourceSchema> request = new Request<SysDbsourceQuery, SysDbsourceSchema>();
		SysDbsourceQuery query = new SysDbsourceQuery();
		request.setData(query);
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(request);
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/config/sysDbsource/list").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		System.out.println(responseString);
	}
	
	@Test
	public void save() throws Exception {
		SysDbsource sysDbsource = new SysDbsource();
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(sysDbsource);
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/config/sysDbsource/save").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		System.out.println(responseString);
	}
	
	@Test
	public void delete() throws Exception {
		String id = "";
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/config/sysDbsource/delete/"+id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		System.out.println(responseString);
	}
	
	@Test
	public void effect() throws Exception {
		String keyName = "";
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/config/sysDbsource/effect/"+keyName).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		System.out.println(responseString);
	}
	

}
