package org.jeecf.manager.module.userpower.controller;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * 系统权限测试
 * @author jianyiming
 *
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SysPowerControllerTest extends BaseMokMvc {
	
	@Before
	public void setUp() throws Exception {
		super.init("admin", "jeecm188988rmb?!J");
	}

	@Test
	public void list() throws Exception {
		SysPowerQuery query = new SysPowerQuery();
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(query);
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/userPower/sysPower/list").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		System.out.println(responseString);
	}

	@Test
	public void save() throws Exception {
		SysPower sysPower = new SysPower();
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(sysPower);
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/userPower/sysPower/save").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		System.out.println(responseString);
	}

	@Test
	public void delete() throws Exception {
		String id = "1";
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/userPower/sysPower/delete/" + id)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		System.out.println(responseString);
	}

}
