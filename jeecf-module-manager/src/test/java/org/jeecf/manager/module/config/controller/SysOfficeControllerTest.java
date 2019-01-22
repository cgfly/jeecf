package org.jeecf.manager.module.config.controller;

import org.jeecf.common.mapper.JsonMapper;
import org.jeecf.common.model.Request;
import org.jeecf.manager.Application;
import org.jeecf.manager.module.config.model.domain.SysOffice;
import org.jeecf.manager.module.config.model.query.SysOfficeQuery;
import org.jeecf.manager.module.config.model.schema.SysOfficeSchema;
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
 * 组织结构测试
 * 
 * @author jianyiming
 *
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SysOfficeControllerTest extends BaseMokMvc {

    @Before
    public void setUp() throws Exception {
        super.init("admin", "123456");
    }

    @Test
    public void list() throws Exception {
        Request<SysOfficeQuery, SysOfficeSchema> request = new Request<SysOfficeQuery, SysOfficeSchema>();
        SysOfficeQuery query = new SysOfficeQuery();
        request.setData(query);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(request);
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/config/sysOffice/list").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
        assert JsonMapper.getJsonNode(responseString).get(SUCCESS).asBoolean();
    }

    @Test
    public void getTreeData() throws Exception {
        SysOfficeQuery query = new SysOfficeQuery();
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(query);
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/config/sysOffice/getTreeData").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
        assert JsonMapper.getJsonNode(responseString).get(SUCCESS).asBoolean();
    }

    @Test
    public void dataOperation() throws Exception {
        SysOffice sysOffice = new SysOffice();
        sysOffice.setName("test");
        sysOffice.setEnname("test");
        sysOffice.setSort(10);
        JsonNode saveNode = JsonMapper.getJsonNode(this.save(sysOffice));
        if (saveNode.get(SUCCESS).asBoolean()) {
            sysOffice.setName("saveUpdate");
            sysOffice.setId(saveNode.get("data").get("id").asText());
            JsonNode updateNode = JsonMapper.getJsonNode(this.save(sysOffice));
            if (updateNode.get(SUCCESS).asBoolean()) {
                JsonNode deleteNode = JsonMapper.getJsonNode(this.delete(sysOffice.getId()));
                assert deleteNode.get(SUCCESS).asBoolean();
            }
            assert updateNode.get(SUCCESS).asBoolean();
        }
        assert saveNode.get(SUCCESS).asBoolean();
    }

    private String save(SysOffice sysOffice) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(sysOffice);
        return mockMvc.perform(MockMvcRequestBuilders.post("/config/sysOffice/save").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
    }

    private String delete(String id) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post("/config/sysOffice/delete/" + id).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
    }

}
