package com.hsbc.team4.ordersystem.log;

import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.log
 * @Description :
 * @Date : 2018/8/9
 */
@Controller
@RequestMapping("/log")
public class LogController {
    private final ILogService iLogService;
    private final ResponseResults responseResults;

    @Autowired
    public LogController(ILogService iLogService, ResponseResults responseResults) {
        this.iLogService = iLogService;
        this.responseResults = responseResults;
    }

    @ApiOperation(value = "searchLikeOperateType",notes = "the param is a current,pageSize,searchKey",httpMethod = "GET",response = ResponseResults.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "the current page", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "the page show size", dataType = "Integer"),
            @ApiImplicitParam(name = "searchKey", value = "searchKey", required = true, dataType = "String"),
    })
    @GetMapping("/searchLikeOperateType/{searchKey}")
    public ResponseResults searchLikeOperateType(@RequestParam(required = false,defaultValue = "0") int current, @RequestParam(required = false,defaultValue = "10") int pageSize, @PathVariable String searchKey){
        Page<Log> roles=iLogService.findByOperateTypeContains(searchKey,current,pageSize);
        if(roles!=null){
            return responseResults.responseBySuccess("ok",roles);
        }
        return responseResults.responseByErrorMessage("searchLikeOperateType Failure,please try again");
    }




}
