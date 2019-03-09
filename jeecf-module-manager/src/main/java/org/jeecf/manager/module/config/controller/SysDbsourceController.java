package org.jeecf.manager.module.config.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecf.common.enums.DelFlagEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.Request;
import org.jeecf.common.model.Response;
import org.jeecf.common.utils.DesEncryptUtils;
import org.jeecf.common.utils.HumpUtils;
import org.jeecf.manager.common.controller.CurdController;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.common.enums.UsableEnum;
import org.jeecf.manager.common.utils.NamespaceUtils;
import org.jeecf.manager.common.utils.UserUtils;
import org.jeecf.manager.config.DynamicDataSourceContextHolder;
import org.jeecf.manager.engine.model.schema.SchemaTable;
import org.jeecf.manager.engine.model.schema.SchemaTableColumn;
import org.jeecf.manager.module.config.model.domain.SysDbsource;
import org.jeecf.manager.module.config.model.po.SysDbsourcePO;
import org.jeecf.manager.module.config.model.query.SysDbsourceQuery;
import org.jeecf.manager.module.config.model.result.SysDbsourceResult;
import org.jeecf.manager.module.config.model.schema.SysDbsourceSchema;
import org.jeecf.manager.module.config.service.SysDbsourceService;
import org.jeecf.manager.module.template.facade.GenTableFacade;
import org.jeecf.manager.module.template.model.domain.GenTable;
import org.jeecf.manager.module.template.model.po.GenTablePO;
import org.jeecf.manager.module.template.model.query.GenTableQuery;
import org.jeecf.manager.module.template.model.result.GenTableColumnResult;
import org.jeecf.manager.module.template.model.result.GenTableResult;
import org.jeecf.manager.module.template.service.GenTableService;
import org.jeecf.manager.proxy.TargetTableProxy;
import org.jeecf.manager.validate.groups.Add;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 系统数据源
 * 
 * @author GloryJian
 * @version 1.0
 */
@Controller
@RequestMapping(value = { "config/sysDbsource" })
@Api(value = "系统数据源  api", tags = { "系统数据源接口" })
public class SysDbsourceController implements CurdController<SysDbsourceQuery, SysDbsourceResult, SysDbsourceSchema, SysDbsource> {

    @Autowired
    private SysDbsourceService sysDbsourceService;

    @Autowired
    private GenTableFacade genTableFacade;

    @Autowired
    private GenTableService genTableService;

    @Autowired
    private TargetTableProxy targetTableProxy;

    @Value("${encrypt.key}")
    private String encryptKey;

    @GetMapping(value = { "", "index" })
    @RequiresPermissions(" ${permission.sysDbsource.view} ")
    @ApiOperation(value = "视图", notes = "查看系统数据源视图")
    @Override
    public String index(ModelMap map) {
        return "module/config/sysDbsource";
    }

    @PostMapping(value = { "list" })
    @ResponseBody
    @RequiresPermissions(" ${permission.sysDbsource.view} ")
    @ApiOperation(value = "列表", notes = "查询系统数据源列表")
    @Override
    public Response<List<SysDbsourceResult>> list(@RequestBody Request<SysDbsourceQuery, SysDbsourceSchema> request) {
        SysDbsourceSchema schema = request.getSchema();
        if (schema == null) {
            schema = new SysDbsourceSchema();
            request.setSchema(schema);
        }
        schema.setPassword(false);
        Response<List<SysDbsourceResult>> sysDbsourceRes = sysDbsourceService.findPageByAuth(new SysDbsourcePO(request));
        List<SysDbsourceResult> sysDbsourceList = sysDbsourceRes.getData();
        if (CollectionUtils.isNotEmpty(sysDbsourceList)) {
            sysDbsourceList.forEach(sysDbsourceEnum -> {
                sysDbsourceEnum.setUsableName(UsableEnum.getName(sysDbsourceEnum.getUsable()));
            });
        }
        return sysDbsourceRes;
    }

    @PostMapping(value = { "save" })
    @ResponseBody
    @RequiresPermissions("${permission.sysDbsource.edit}")
    @ApiOperation(value = "更新", notes = "更新系统数据源数据")
    @Override
    public Response<SysDbsourceResult> save(@RequestBody @Validated({ Add.class }) SysDbsource sysDbsource) {
        if (sysDbsource.isNewRecord()) {
            SysDbsourceQuery query = new SysDbsourceQuery();
            query.setKeyName(sysDbsource.getKeyName());
            List<SysDbsourceResult> sysDbsourcceList = sysDbsourceService.findList(new SysDbsourcePO(query)).getData();
            if (CollectionUtils.isNotEmpty(sysDbsourcceList)) {
                throw new BusinessException(BusinessErrorEnum.DATA_EXIT);
            }
            try {
                sysDbsource.setPassword(DesEncryptUtils.encryptWithBase64(sysDbsource.getPassword(), encryptKey));
            } catch (Exception e) {
                throw new BusinessException(BusinessErrorEnum.ENCRYPT_FAIL);
            }
        }
        return sysDbsourceService.saveByAuth(sysDbsource);
    }

    @PostMapping(value = { "invalid/{id}" })
    @ResponseBody
    @RequiresPermissions("${permission.sysDbsource.edit}")
    @ApiOperation(value = "失效", notes = "失效系统数据源数据")
    public Response<SysDbsourceResult> invalid(@PathVariable("id") String id) {
        SysDbsource sysDbsource = sysDbsourceService.get(new SysDbsource(id)).getData();
        if (sysDbsource != null) {
            String keyName = sysDbsource.getKeyName();
            String defaultKeyName = DynamicDataSourceContextHolder.getDafualtKey();
            if (!defaultKeyName.equals(keyName)) {
                String currentKeyName = DynamicDataSourceContextHolder.getCurrentDataSourceValue();
                if (!currentKeyName.equals(keyName)) {
                    SysDbsource invalidDb = new SysDbsource();
                    invalidDb.setId(id);
                    invalidDb.setDelFlag(DelFlagEnum.YES.getCode());
                    return sysDbsourceService.saveByAuth(invalidDb);
                }
                throw new BusinessException(BusinessErrorEnum.DARASOURCE_KEY_IS_CURRENT);
            }
            throw new BusinessException(BusinessErrorEnum.DARASOURCE_KEY_IS_DEFAULT);
        }
        throw new BusinessException(BusinessErrorEnum.DATA_NOT_EXIT);
    }

    @PostMapping(value = { "active/{id}" })
    @ResponseBody
    @RequiresPermissions("${permission.sysDbsource.edit}")
    @ApiOperation(value = "激活", notes = "激活系统数据源数据")
    public Response<Integer> active(@PathVariable("id") String id) {
        SysDbsource sysDbSource = new SysDbsource(id);
        sysDbSource.setDelFlag(DelFlagEnum.NO.getCode());
        sysDbsourceService.saveByAuth(sysDbSource);
        return new Response<>(1);
    }

    @PostMapping(value = { "effect/{id}" })
    @ResponseBody
    @RequiresPermissions("${permission.sysDbsource.view}")
    @ApiOperation(value = "生效", notes = "生效选中系统数据源")
    public Response<Integer> effect(@NotEmpty @PathVariable("id") String id) {
        SysDbsource sysDbSource = sysDbsourceService.getByAuth(new SysDbsource(id)).getData();
        if (sysDbSource != null) {
            DynamicDataSourceContextHolder.setCurrentDataSourceValue(sysDbSource.getKeyName(),Integer.valueOf(sysDbSource.getId()), sysDbSource.getUsable());
            return new Response<Integer>(1);
        }
        throw new BusinessException(BusinessErrorEnum.DATA_NOT_EXIT);
    }

    @PostMapping(value = { "syncGen/{id}" })
    @ResponseBody
    @RequiresPermissions("${permission.sysDbsource.edit}")
    @ApiOperation(value = "同步", notes = "同步虚表数据")
    public Response<Integer> syncGen(@NotNull @PathVariable("id") Integer id) {
        String currentKeyName = DynamicDataSourceContextHolder.getCurrentDataSourceValue();
        Response<SysDbsourceResult> dbSourceRes = sysDbsourceService.get(new SysDbsource(String.valueOf(id)));
        if (dbSourceRes.isSuccess() && dbSourceRes.getData() != null) {
            SysDbsourceResult sysDbSource = dbSourceRes.getData();
            if (currentKeyName.equals(sysDbSource.getKeyName())) {
                Response<List<SchemaTable>> schemaTableRes = targetTableProxy.findTableList(NamespaceUtils.getNamespace(UserUtils.getCurrentUserId()));
                if (CollectionUtils.isNotEmpty(schemaTableRes.getData())) {
                    schemaTableRes.getData().forEach(schemaTable -> {
                        GenTableQuery queryTable = new GenTableQuery();
                        queryTable.setName(schemaTable.getName());
                        List<GenTableResult> genTableList = genTableService.findListByAuth(new GenTablePO(queryTable)).getData();
                        if (CollectionUtils.isEmpty(genTableList)) {
                            GenTable genTable = new GenTable();
                            BeanUtils.copyProperties(schemaTable, genTable);
                            genTable.setClassName(HumpUtils.lineToHump(genTable.getName()));
                            Response<List<SchemaTableColumn>> genTableColumnRes = targetTableProxy.findTableColumn(schemaTable.getName());
                            List<GenTableColumnResult> genTableColumnList = new ArrayList<>();
                            genTableColumnRes.getData().forEach(column -> {
                                GenTableColumnResult result = new GenTableColumnResult();
                                BeanUtils.copyProperties(column, result);
                                result.setField(HumpUtils.lineToHump(result.getName()));
                                genTableColumnList.add(result);
                            });
                            genTable.setGenTableColumns(genTableColumnList);
                            genTableFacade.saveTable(genTable);
                        }
                    });
                }
                return new Response<Integer>(1);
            }
            throw new BusinessException(BusinessErrorEnum.DB_CONNECT_NOT_EFFECT);
        }
        throw new BusinessException(BusinessErrorEnum.DATA_NOT_EXIT);

    }

    @PostMapping(value = { "delete/{id}" })
    @ResponseBody
    @RequiresPermissions("${permission.sysDbsource.edit}")
    @ApiOperation(value = "删除", notes = "删除系统数据源数据")
    @Override
    public Response<Integer> delete(@PathVariable("id") String id) {
        return sysDbsourceService.deleteByAuth(new SysDbsource(id));
    }

}