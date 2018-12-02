package org.jeecf.manager.module.userpower.controller;

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
		super.init("admin", "jeecm188988rmb?!J");
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
				.perform(MockMvcRequestBuilders.post("/userPower/sysUser/list").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		System.out.println(responseString);
	}

	@Test
	public void save() throws Exception {
		SysUser sysUser = new SysUser();
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(sysUser);
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/userPower/sysUser/save").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		System.out.println(responseString);
	}

	@Test
	public void delete() throws Exception {
		String id = "1";
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/userPower/sysUser/delete/" + id)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		System.out.println(responseString);
	}
	
	@Test
	public void roles() throws Exception {
		String userId = "1";
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/userPower/sysUser/roles/" + userId)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		System.out.println(responseString);
	}

}
