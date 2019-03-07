package org.jeecf.manager.module.operation.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecf.common.model.Request;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.controller.BaseController;
import org.jeecf.manager.common.enums.ActionTypeEnum;
import org.jeecf.manager.module.operation.model.po.CustomerActionLogPO;
import org.jeecf.manager.module.operation.model.query.CustomerActionLogQuery;
import org.jeecf.manager.module.operation.model.result.CustomerActionLogResult;
import org.jeecf.manager.module.operation.model.schema.CustomerActionLogSchema;
import org.jeecf.manager.module.operation.service.CustomerActionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 客户操作日志 controller
 * 
 * @author jianyiming
 *
 */
@Controller
@RequestMapping(value = { "operation/customerActionLog" })
@Api(value = "customerActionLog api", tags = { "客户操作日志" })
public class CustomerActionLogController implements BaseController {

    @Autowired
    private CustomerActionLogService customerActionLogService;

    @GetMapping(value = { "", "index" })
    @RequiresPermissions("${permission.customerActionLog.view}")
    @ApiOperation(value = "视图", notes = "查看api视图")
    @Override
    public String index(ModelMap map) {
        return "module/operation/customerActionLog";
    }

    @PostMapping(value = { "list" })
    @ResponseBody
    @RequiresPermissions("${permission.customerActionLog.view}")
    @ApiOperation(value = "列表", notes = "客户操作日志列表")
    public Response<List<CustomerActionLogResult>> list(@RequestBody Request<CustomerActionLogQuery, CustomerActionLogSchema> request) {
        Response<List<CustomerActionLogResult>> customerActionLogRes = customerActionLogService.findPageByAuth(new CustomerActionLogPO(request));
        customerActionLogRes.getData().forEach(customerActionLogResult -> {
            customerActionLogResult.setActionTypeName(ActionTypeEnum.getName(customerActionLogResult.getActionType()));
        });
        return customerActionLogRes;
    }

}
