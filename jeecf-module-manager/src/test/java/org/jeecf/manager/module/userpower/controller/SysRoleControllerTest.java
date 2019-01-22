package org.jeecf.manager.module.userpower.controller;

import java.util.ArrayList;
import java.util.List;

import org.jeecf.common.mapper.JsonMapper;
import org.jeecf.common.model.Request;
import org.jeecf.manager.Application;
import org.jeecf.manager.module.userpower.model.domain.SysRole;
import org.jeecf.manager.module.userpower.model.query.SysRoleQuery;
import org.jeecf.manager.module.userpower.model.schema.SysRoleSchema;
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
 * 系统角色测试
 * 
 * @author jianyiming
 *
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SysRoleControllerTest extends BaseMokMvc {

    @Before
    public void setUp() throws Exception {
        super.init("admin", "123456");
    }

    @Test
    public void list() throws Exception {
        Request<SysRoleQuery, SysRoleSchema> request = new Request<SysRoleQuery, SysRoleSchema>();
        SysRoleQuery query = new SysRoleQuery();
        request.setData(query);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(request);
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/userpower/sysRole/list").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
        assert JsonMapper.getJsonNode(responseString).get(SUCCESS).asBoolean();
    }

    @Test
    public void getTree() throws Exception {
        String roleId = "4";
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/userpower/sysRole/getTree/" + roleId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
        assert JsonMapper.getJsonNode(responseString).get(SUCCESS).asBoolean();
    }

    @Test
    public void getAllTree() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/userpower/sysRole/getAllTree").contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
        assert JsonMapper.getJsonNode(responseString).get(SUCCESS).asBoolean();
    }

    @Test
    public void dataOperation() throws Exception {
        SysRole sysRole = new SysRole();
        sysRole.setName("test");
        sysRole.setEnname("test");
        List<String> powerIds = new ArrayList<>();
        powerIds.add("30");
        powerIds.add("31");
        sysRole.setSysPowerIds(powerIds);
        JsonNode saveNode = JsonMapper.getJsonNode(this.save(sysRole));
        if (saveNode.get(SUCCESS).asBoolean()) {
            sysRole.setName("saveUpdate");
            sysRole.setId(saveNode.get("data").get("id").asText());
            sysRole.setSysPowerIds(null);
            JsonNode updateNode = JsonMapper.getJsonNode(this.save(sysRole));
            if (updateNode.get(SUCCESS).asBoolean()) {
                JsonNode deleteNode = JsonMapper.getJsonNode(this.delete(sysRole.getId()));
                assert deleteNode.get(SUCCESS).asBoolean();
            }
            assert updateNode.get(SUCCESS).asBoolean();
        }
        assert saveNode.get(SUCCESS).asBoolean();
    }

    private String save(SysRole sysRole) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(sysRole);
        return mockMvc.perform(MockMvcRequestBuilders.post("/userpower/sysRole/save").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
    }

    private String delete(String id) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post("/userpower/sysRole/delete/" + id).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
    }
}
