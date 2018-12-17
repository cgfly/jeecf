package org.jeecf.manager.module.gen.controller;

import org.jeecf.common.mapper.JsonMapper;
import org.jeecf.common.model.Request;
import org.jeecf.manager.Application;
import org.jeecf.manager.module.gen.model.domian.SysTableDict;
import org.jeecf.manager.module.gen.model.query.SysTableDictQuery;
import org.jeecf.manager.module.gen.model.schema.SysTableDictSchema;
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
 * 表组字典测试
 * @author jianyiming
 *
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SysTableDictControllerTest extends BaseMokMvc{
	
	@Before
	public void setUp() throws Exception {
		super.init("admin", "123456");
	}

	@Test
	public void list() throws Exception {
		Request<SysTableDictQuery, SysTableDictSchema> request = new Request<SysTableDictQuery, SysTableDictSchema>();
		SysTableDictQuery query = new SysTableDictQuery();
		request.setData(query);
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(request);
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/gen/sysTableDict/list").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		assert JsonMapper.getJsonNode(responseString).get(SUCCESS).asBoolean();
	}
	
	@Test
	public void dataOperation() throws Exception {
		SysTableDict sysTableDict = new SysTableDict();
		sysTableDict.setName("test1");
		sysTableDict.setComment("test");
		sysTableDict.setTableName("sys_dict");
		sysTableDict.setField("sysDict");
		sysTableDict.setDescription("11111");
		JsonNode saveNode = JsonMapper.getJsonNode(this.save(sysTableDict));
		if(saveNode.get(SUCCESS).asBoolean()) {
			sysTableDict.setName("saveUpdate");
			sysTableDict.setId(saveNode.get("data").get("id").asText());
			JsonNode updateNode = JsonMapper.getJsonNode(this.save(sysTableDict));
			if(updateNode.get(SUCCESS).asBoolean()) {
				JsonNode deleteNode = JsonMapper.getJsonNode(this.delete(sysTableDict.getId()));
				assert deleteNode.get(SUCCESS).asBoolean();
			}
			assert updateNode.get(SUCCESS).asBoolean() && updateNode.get("data").get("name").asText().equals("saveUpdate");
		}
		assert saveNode.get(SUCCESS).asBoolean();
	}

	private String save(SysTableDict sysTableDict) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(sysTableDict);
		return mockMvc
				.perform(MockMvcRequestBuilders.post("/gen/sysTableDict/save").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
	}

	private String delete(String id) throws Exception {
		return mockMvc
				.perform(MockMvcRequestBuilders.post("/gen/sysTableDict/delete/"+id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
	}
	
	public void tables() throws Exception {
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/gen/sysTableDict/tables").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		assert JsonMapper.getJsonNode(responseString).get(SUCCESS).asBoolean();
	}

}
