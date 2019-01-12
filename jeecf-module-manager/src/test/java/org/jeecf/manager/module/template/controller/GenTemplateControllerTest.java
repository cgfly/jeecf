package org.jeecf.manager.module.template.controller;

import java.util.ArrayList;
import java.util.List;

import org.jeecf.common.mapper.JsonMapper;
import org.jeecf.common.model.Request;
import org.jeecf.manager.Application;
import org.jeecf.manager.gen.model.GenParams;
import org.jeecf.manager.gen.model.GenTemplateEntity;
import org.jeecf.manager.module.template.model.domain.GenTemplate;
import org.jeecf.manager.module.template.model.query.GenTemplateQuery;
import org.jeecf.manager.module.template.model.schema.GenTemplateSchema;
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
 * 模版配置 测试
 * 
 * @author jianyiming
 *
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class GenTemplateControllerTest extends BaseMokMvc {

	@Before
	public void setUp() throws Exception {
		super.init("admin", "123456");
	}

	@Test
	public void list() throws Exception {
		Request<GenTemplateQuery, GenTemplateSchema> request = new Request<GenTemplateQuery, GenTemplateSchema>();
		GenTemplateQuery query = new GenTemplateQuery();
		request.setData(query);
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(request);
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/template/genTemplate/list")
						.contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		assert JsonMapper.getJsonNode(responseString).get(SUCCESS).asBoolean();
	}

	@Test
	public void getField() throws Exception {
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/template/genTemplate/field")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		assert JsonMapper.getJsonNode(responseString).get(SUCCESS).asBoolean();
	}

	@Test
	public void params() throws Exception {
		String genFieldId = "1";
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/template/genTemplate/params/" + genFieldId)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		assert JsonMapper.getJsonNode(responseString).get(SUCCESS).asBoolean();
	}

	@Test
	public void findTable() throws Exception {
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/template/genTemplate/queryTableList")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		assert JsonMapper.getJsonNode(responseString).get(SUCCESS).asBoolean();
	}

	@Test
	public void getLanguages() throws Exception {
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/template/genTemplate/getLanguages")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		assert JsonMapper.getJsonNode(responseString).get(SUCCESS).asBoolean();
	}
	
	@Test
	public void gen() throws Exception {
		GenTemplateEntity entity = new GenTemplateEntity();
		entity.setTemplateId(15);
		entity.setTableName("sys_dict");
		List<GenParams> genParamsList = new ArrayList<>();
		GenParams packageParam = new GenParams();
		packageParam.setName("packageName");
		packageParam.setValue("ee");
		genParamsList.add(packageParam);
		GenParams filterParam = new GenParams();
		filterParam.setName("filterField");
		filterParam.setValue("sysNamespaceId");
		genParamsList.add(filterParam);
		GenParams groupParam = new GenParams();
		groupParam.setName("groupField");
		groupParam.setValue("type");
		genParamsList.add(groupParam);
		entity.setParams(genParamsList);
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(entity);
		String responseString = mockMvc
				.perform(MockMvcRequestBuilders.post("/template/genTemplate/gen")
						.contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
		assert JsonMapper.getJsonNode(responseString).get(SUCCESS).asBoolean();
	}

	@Test
	public void dataOperation() throws Exception {
		GenTemplate genTemplate = new GenTemplate();
		genTemplate.setFileBasePath("1243fe3ef/k.zip");
		genTemplate.setName("test");
		genTemplate.setDescription("test");
		genTemplate.setLanguage(1);
		JsonNode saveNode = JsonMapper.getJsonNode(this.save(genTemplate));
		if (saveNode.get(SUCCESS).asBoolean()) {
			genTemplate.setName("saveUpdate");
			genTemplate.setId(saveNode.get("data").get("id").asText());
			JsonNode updateNode = JsonMapper.getJsonNode(this.save(genTemplate));
			if (updateNode.get(SUCCESS).asBoolean()) {
				JsonNode deleteNode = JsonMapper.getJsonNode(this.delete(genTemplate.getId()));
				assert deleteNode.get(SUCCESS).asBoolean();
			}
			assert updateNode.get(SUCCESS).asBoolean();
		}
		assert saveNode.get(SUCCESS).asBoolean();
	}

	private String save(GenTemplate genTemplate) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(genTemplate);
		return mockMvc
				.perform(MockMvcRequestBuilders.post("/template/genTemplate/save")
						.contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
	}

	private String delete(String id) throws Exception {
		return mockMvc
				.perform(MockMvcRequestBuilders.post("/template/genTemplate/delete/" + id)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
				.getResponse().getContentAsString();
	}

}
