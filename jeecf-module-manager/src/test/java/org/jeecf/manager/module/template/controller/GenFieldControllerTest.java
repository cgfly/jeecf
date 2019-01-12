package org.jeecf.manager.module.template.controller;

import java.util.ArrayList;
import java.util.List;

import org.jeecf.common.mapper.JsonMapper;
import org.jeecf.common.model.Request;
import org.jeecf.manager.Application;
import org.jeecf.manager.module.template.model.domain.GenField;
import org.jeecf.manager.module.template.model.domain.GenFieldColumn;
import org.jeecf.manager.module.template.model.query.GenFieldQuery;
import org.jeecf.manager.module.template.model.schema.GenFieldSchema;
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
 * 模版参数测试
 * 
 * @author jianyiming
 *
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class GenFieldControllerTest extends BaseMokMvc {

	@Before
	public void setUp() throws Exception {
		super.init("admin", "123456");
	}

	@Test
	public void list() throws Exception {
		Request<GenFieldQuery, GenFieldSchema> request = new Request<GenFieldQuery, GenFieldSchema>();
		GenFieldQuery query = new GenFieldQuery();
		request.setData(query);
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(request);
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/template/genField/list").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		assert JsonMapper.getJsonNode(responseString).get(SUCCESS).asBoolean();
	}

	@Test
	public void column() throws Exception {
		String genFieldId = "1";
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/template/genField/column/" + genFieldId)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		assert JsonMapper.getJsonNode(responseString).get(SUCCESS).asBoolean();
	}

	@Test
	public void dataOperation() throws Exception {
		GenField genField = new GenField();
		genField.setName("test");
		genField.setDescription("test");
		List<GenFieldColumn> fieldColumnList = new ArrayList<>();
		GenFieldColumn genFieldColumn = new GenFieldColumn();
		genFieldColumn.setName("test");
		genFieldColumn.setIsNull(1);
		genFieldColumn.setDescrition("test");
		fieldColumnList.add(genFieldColumn);
		genField.setGenFieldColumn(fieldColumnList);
		JsonNode saveNode = JsonMapper.getJsonNode(this.save(genField));
		if (saveNode.get(SUCCESS).asBoolean()) {
			genField.setName("saveUpdate");
			genField.setId(saveNode.get("data").get("id").asText());
			JsonNode updateNode = JsonMapper.getJsonNode(this.save(genField));
			if (updateNode.get(SUCCESS).asBoolean()) {
				JsonNode deleteNode = JsonMapper.getJsonNode(this.delete(genField.getId()));
				assert deleteNode.get(SUCCESS).asBoolean();
			}
			assert updateNode.get(SUCCESS).asBoolean();
		}
		assert saveNode.get(SUCCESS).asBoolean();
	}

	private String save(GenField genField) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(genField);
		return mockMvc
				.perform(MockMvcRequestBuilders.post("/template/genField/save").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
	}

	private String delete(String id) throws Exception {
		return mockMvc
				.perform(MockMvcRequestBuilders.post("/template/genField/delete/" + id)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
	}

}
