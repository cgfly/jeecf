package org.jeecf.manager.module.userpower.controller;

import org.jeecf.common.mapper.JsonMapper;
import org.jeecf.manager.Application;
import org.jeecf.manager.module.userpower.model.domain.SysPower;
import org.jeecf.manager.module.userpower.model.query.SysPowerQuery;
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
 * 系统权限测试
 * 
 * @author jianyiming
 *
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SysPowerControllerTest extends BaseMokMvc {

	@Before
	public void setUp() throws Exception {
		super.init("admin", "123456");
	}

	@Test
	public void list() throws Exception {
		SysPowerQuery query = new SysPowerQuery();
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(query);
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/userpower/sysPower/list").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		assert JsonMapper.getJsonNode(responseString).get("success").asBoolean();
	}

	@Test
	public void getTreeData() throws Exception {
		SysPowerQuery query = new SysPowerQuery();
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(query);
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/userpower/sysPower/getTreeData")
						.contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		assert JsonMapper.getJsonNode(responseString).get("success").asBoolean();
	}

	@Test
	public void dataOperation() throws Exception {
		SysPower sysPower = new SysPower();
		sysPower.setName("test");
		sysPower.setPermission("test:base");
		sysPower.setSort(10);
		JsonNode saveNode = JsonMapper.getJsonNode(this.save(sysPower));
		if (saveNode.get("success").asBoolean()) {
			sysPower.setName("saveUpdate");
			sysPower.setId(saveNode.get("data").get("id").asText());
			JsonNode updateNode = JsonMapper.getJsonNode(this.save(sysPower));
			if (updateNode.get("success").asBoolean()) {
				JsonNode deleteNode = JsonMapper.getJsonNode(this.delete(sysPower.getId()));
				assert deleteNode.get("success").asBoolean();
			}
			assert updateNode.get("success").asBoolean()
					&& updateNode.get("data").get("name").asText().equals("saveUpdate");
		}
		assert saveNode.get("success").asBoolean();
	}

	public String save(SysPower sysPower) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(sysPower);
		return mockMvc
				.perform(MockMvcRequestBuilders.post("/userpower/sysPower/save").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
	}

	private String delete(String id) throws Exception {
		return mockMvc
				.perform(MockMvcRequestBuilders.post("/userpower/sysPower/delete/" + id)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
	}

}
