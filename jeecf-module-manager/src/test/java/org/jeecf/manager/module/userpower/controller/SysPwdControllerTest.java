package org.jeecf.manager.module.userpower.controller;

import org.jeecf.common.mapper.JsonMapper;
import org.jeecf.manager.Application;
import org.jeecf.manager.module.userpower.model.domain.SysPwd;
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
 * 修改密码 测试
 * @author jianyiming
 *
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SysPwdControllerTest extends BaseMokMvc {
	
	@Before
	public void setUp() throws Exception {
		super.init("admin", "123456");
	}

	@Test
	public void save() throws Exception {
		SysPwd sysPwd = new SysPwd();
		sysPwd.setPassword("123456");
		sysPwd.setNewPassword("123456");
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(sysPwd);
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/userpower/sysPwd/save").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		assert JsonMapper.getJsonNode(responseString).get("success").asBoolean();
	}
	
}
