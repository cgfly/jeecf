package org.jeecf.manager.module.userpower.controller;

import java.util.ArrayList;
import java.util.List;

import org.jeecf.common.mapper.JsonMapper;
import org.jeecf.common.model.Request;
import org.jeecf.manager.Application;
import org.jeecf.manager.module.userpower.model.domain.SysUser;
import org.jeecf.manager.module.userpower.model.query.SysUserQuery;
import org.jeecf.manager.module.userpower.model.schema.SysUserSchema;
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
 * 系统用户测试
 * 
 * @author jianyiming
 *
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SysUserControllerTest extends BaseMokMvc {

	@Before
	public void setUp() throws Exception {
		super.init("admin", "123456");
	}

	@Test
	public void list() throws Exception {
		Request<SysUserQuery, SysUserSchema> request = new Request<SysUserQuery, SysUserSchema>();
		SysUserQuery query = new SysUserQuery();
		request.setData(query);
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(request);
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/userpower/sysUser/list").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		assert JsonMapper.getJsonNode(responseString).get("success").asBoolean();
	}

	@Test
	public void getTreeData() throws Exception {
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/userpower/sysUser/getTreeData")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		assert JsonMapper.getJsonNode(responseString).get("success").asBoolean();
	}

	@Test
	public void roles() throws Exception {
		String userId = "be50e868ce4841ebb63bb1694b2413ef";
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/userpower/sysUser/roles/" + userId)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		assert JsonMapper.getJsonNode(responseString).get("success").asBoolean();
	}
	
	@Test
	public void dataOperation() throws Exception {
		SysUser sysUser = new SysUser();
		sysUser.setUsername("test");
		sysUser.setPassword("test");
		sysUser.setName("test");
		List<String> sysRoleIds = new ArrayList<>();
		sysRoleIds.add("3");
		sysUser.setSysRoleIds(sysRoleIds);
		sysUser.setSysOfficeId(1);
		JsonNode saveNode = JsonMapper.getJsonNode(this.save(sysUser));
		if(saveNode.get("success").asBoolean()) {
			sysUser.setName("saveUpdate");
			sysUser.setId(saveNode.get("data").get("id").asText());
			sysUser.setSysRoleIds(null);
			sysUser.setSysOfficeId(null);
			JsonNode updateNode = JsonMapper.getJsonNode(this.save(sysUser));
			if(updateNode.get("success").asBoolean()) {
				JsonNode deleteNode = JsonMapper.getJsonNode(this.delete(sysUser.getId()));
				assert deleteNode.get("success").asBoolean();
			}
			assert updateNode.get("success").asBoolean();
		}
		assert saveNode.get("success").asBoolean();
	}

	private String save(SysUser sysUser) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(sysUser);
		return mockMvc
				.perform(MockMvcRequestBuilders.post("/userpower/sysUser/save").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
	}

	private String delete(String id) throws Exception {
		return mockMvc
				.perform(MockMvcRequestBuilders.post("/userpower/sysUser/delete/" + id)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
	}

}
