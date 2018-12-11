package org.jeecf.manager.module.config.controller;

import org.jeecf.common.mapper.JsonMapper;
import org.jeecf.common.model.Request;
import org.jeecf.manager.Application;
import org.jeecf.manager.module.config.model.domain.SysMenu;
import org.jeecf.manager.module.config.model.query.SysMenuQuery;
import org.jeecf.manager.module.config.model.schema.SysMenuSchema;
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
 * 系统菜单 测试
 * @author jianyiming
 *
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SysMenuControllerTest extends BaseMokMvc {

	@Before
	public void setUp() throws Exception {
		super.init("admin", "123456");
	}

	@Test
	public void list() throws Exception {
		Request<SysMenuQuery, SysMenuSchema> request = new Request<SysMenuQuery, SysMenuSchema>();
		SysMenuQuery query = new SysMenuQuery();
		request.setData(query);
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(request);
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/config/sysMenu/list").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		assert JsonMapper.getJsonNode(responseString).get("success").asBoolean();
	}
	
	@Test
	public void getTreeData() throws Exception {
		SysMenuQuery query = new SysMenuQuery();
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(query);
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/config/sysMenu/getTreeData").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		assert JsonMapper.getJsonNode(responseString).get("success").asBoolean();
	}
	
	@Test
	public void dataOperation() throws Exception {
		SysMenu sysMenu = new SysMenu();
		sysMenu.setIsIcon(1);
		sysMenu.setRoute(1);
		sysMenu.setLabel("test");
		sysMenu.setSort(10);
		sysMenu.setName("test");
		sysMenu.setPermission("test:base");
		JsonNode saveNode = JsonMapper.getJsonNode(this.save(sysMenu));
		if(saveNode.get("success").asBoolean()) {
			sysMenu.setName("saveUpdate");
			sysMenu.setId(saveNode.get("data").get("id").asText());
			JsonNode updateNode = JsonMapper.getJsonNode(this.save(sysMenu));
			if(updateNode.get("success").asBoolean()) {
				JsonNode deleteNode = JsonMapper.getJsonNode(this.delete(sysMenu.getId()));
				assert deleteNode.get("success").asBoolean();
			}
			assert updateNode.get("success").asBoolean();
		}
		assert saveNode.get("success").asBoolean();
	}
	
	
	private String save(SysMenu sysMenu ) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(sysMenu);
		return mockMvc
				.perform(MockMvcRequestBuilders.post("/config/sysMenu/save").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
	}
	
	private String delete(String id) throws Exception {
		return   mockMvc
				.perform(MockMvcRequestBuilders.post("/config/sysMenu/delete/"+id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
	}
	

}
