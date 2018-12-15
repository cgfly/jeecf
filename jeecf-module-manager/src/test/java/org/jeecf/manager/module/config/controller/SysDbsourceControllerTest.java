package org.jeecf.manager.module.config.controller;

import org.jeecf.common.mapper.JsonMapper;
import org.jeecf.common.model.Page;
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

import com.fasterxml.jackson.databind.JsonNode;
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
		Page page = new Page();
		page.setCurrent(1);
		page.setSize(8);
		request.setData(query);
		request.setPage(page);
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(request);
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/config/sysDbsource/list").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		assert JsonMapper.getJsonNode(responseString).get(SUCCESS).asBoolean();
	}
	
	@Test
	public void effect() throws Exception {
		String keyName = "defaultDataSourceKey";
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/config/sysDbsource/effect/"+keyName).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		assert JsonMapper.getJsonNode(responseString).get(SUCCESS).asBoolean();
	}
	
	@Test
	public void dataOperation() throws Exception {
		SysDbsource sysDbsource = new SysDbsource();
		sysDbsource.setKeyName("test");
		sysDbsource.setUserName("root");
		sysDbsource.setPermission("config:sysNamespace:work");
		sysDbsource.setPassword("123456");
		sysDbsource.setUrl("jdbc:mysql://127.0.0.1:3306/jeecf?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull");
		JsonNode saveNode = JsonMapper.getJsonNode(this.save(sysDbsource));
		if(saveNode.get(SUCCESS).asBoolean()) {
			sysDbsource.setKeyName("saveUpdate");
			sysDbsource.setId(saveNode.get("data").get("id").asText());
			JsonNode updateNode = JsonMapper.getJsonNode(this.save(sysDbsource));
			if(updateNode.get(SUCCESS).asBoolean()) {
				JsonNode deleteNode = JsonMapper.getJsonNode(this.delete(sysDbsource.getId()));
				assert deleteNode.get(SUCCESS).asBoolean();
			}
			assert updateNode.get(SUCCESS).asBoolean();
		}
		assert saveNode.get(SUCCESS).asBoolean();
	}
	
	private String save(SysDbsource sysDbsource) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(sysDbsource);
		return mockMvc
				.perform(MockMvcRequestBuilders.post("/config/sysDbsource/save").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
	}
	
	private String delete(String id) throws Exception {
		return mockMvc
				.perform(MockMvcRequestBuilders.post("/config/sysDbsource/delete/"+id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
	}
	

}
