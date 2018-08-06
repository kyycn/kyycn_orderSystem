package com.hsbc.team4.ordersystem.emailmessage;

import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.emailmessage
 * @Description :
 * @Date : 2018/8/5
 */
@Controller
@RequestMapping("/mailSender")
@Api("MailSenderController")
public class MailSenderController {
    private final IMailSenderService iMailSenderService;
    private final ResponseResults responseResults;

    @Autowired
    public MailSenderController(IMailSenderService iMailSenderService, ResponseResults responseResults) {
        this.iMailSenderService = iMailSenderService;
        this.responseResults = responseResults;
    }
    /**
     * saveMailSender
     * @param mailSender
     * @return MailSender
     */
    @ApiOperation(value = "saveMailSender",notes = "mailSender",httpMethod = "POST")
    @ApiImplicitParam(name = "mailSender",value = "mailSender",dataType="MailSender")
    @PostMapping("/")
    public ResponseResults saveMailSender(@RequestBody MailSender mailSender){
        return responseResults.responseBySuccess("ok",iMailSenderService.addEntity(mailSender));
    }

    /**
     * updateMailSender
     * @param mailSender
     * @return MailSender
     */
    @ApiOperation(value = "updateMailSender",notes = "mailSender",httpMethod = "PUT")
    @ApiImplicitParam(name = "mailSender",value = "mailSender",dataType="mailSender")
    @PutMapping("/")
    public ResponseResults updateMailSender(@RequestBody  MailSender  mailSender){
        return responseResults.responseBySuccess("ok",iMailSenderService.updateEntity(mailSender));
    }

    /**
     * deleteMailSender
     * @param id
     * @return String
     */
    @ApiOperation(value = "deleteMailSenderById",notes = "deleteMailSenderById",httpMethod = "DELETE")
    @ApiImplicitParam(name = "id",value = "id",dataType="String")
    @DeleteMapping("/{id}")
    public ResponseResults deleteMailSenderById(@PathVariable String  id){
        return responseResults.responseBySuccess("ok",iMailSenderService.updateStatusById(id,1));
    }

    /**
     * queryMailSenderById
     * @param id
     * @return MailSender
     */
    @ApiOperation(value = "queryMailSenderById",notes = "queryMailSenderById",httpMethod = "GET")
    @ApiImplicitParam(name = "id",value = "id",dataType="String")
    @GetMapping("/{id}")
    public ResponseResults queryMailSenderById(@PathVariable String id){
        return responseResults.responseBySuccess("ok",iMailSenderService.findById(id));
    }

    /**
     * getMailSenderList
     * @param current
     * @param pageSize
     * @param status
     * @return
     */
    @ApiOperation(value = "queryMailSenderById",notes = "queryMailSenderById",httpMethod = "GET")
    @GetMapping("/{status}")
    public ResponseResults getMailSenderList(@RequestParam(value = "current",defaultValue = "0") int current,
                                       @RequestParam(value = "current",defaultValue = "10") int pageSize,
                                       @PathVariable int status){
        return responseResults.responseBySuccess("ok",iMailSenderService.findByStatus(current,pageSize,status));
    }

}
