package com.hsbc.team4.ordersystem.smsmessage;

import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.smsmessage
 * @Description :
 * @Date : 2018/8/5
 */
@RestController
@RequestMapping("/sender")
public class SenderController {

    private final ISenderService iSenderService;
    private final ResponseResults responseResults;

    @Autowired
    public SenderController(ISenderService iSenderService, ResponseResults responseResults) {
        this.iSenderService = iSenderService;
        this.responseResults = responseResults;
    }

    /**
     * saveSender
     * @param sender
     * @return ResponseResults
     */
    @ApiOperation(value = "saveSender",notes = "saveSender",httpMethod = "POST")
    @ApiImplicitParam(name = "sender",value = "sender",dataType="Sender")
    @PostMapping("/")
    public ResponseResults saveSender(@RequestBody Sender sender){
        return responseResults.responseBySuccess("ok",iSenderService.addEntity(sender));
    }

    /**
     * updateSender
     * @param sender
     * @return sender
     */
    @ApiOperation(value = "updateSender",notes = "updateSender",httpMethod = "PUT")
    @ApiImplicitParam(name = "sender",value = "sender",dataType="Sender")
    @PutMapping("/")
    public ResponseResults updateSender(@RequestBody  Sender  sender){
        return responseResults.responseBySuccess("ok",iSenderService.updateEntity(sender));
    }

    /**
     * deleteSenderById
     * @param id
     * @return String
     */
    @ApiOperation(value = "deleteSenderById",notes = "deleteSenderById",httpMethod = "DELETE")
    @ApiImplicitParam(name = "id",value = "id",dataType="String")
    @DeleteMapping("/{id}")
    public ResponseResults deleteSenderById(@PathVariable String  id){
        return responseResults.responseBySuccess("ok",iSenderService.updateStatusById(id,1));
    }

    /**
     * querySenderById
     * @param id
     * @return Role
     */
    @ApiOperation(value = "querySenderById",notes = "querySenderById",httpMethod = "GET")
    @ApiImplicitParam(name = "id",value = "id",dataType="String")
    @GetMapping("/{id}")
    public ResponseResults querySenderById(@PathVariable String id){
        return responseResults.responseBySuccess("ok",iSenderService.findById(id));
    }

    /**
     * get SenderList
     * @param current
     * @param pageSize
     * @param status
     * @return  ResponseResults
     */
    @ApiOperation(value = "getSenderList",notes = "getSenderList",httpMethod = "GET")
    @GetMapping("/{status}")
    public ResponseResults getSenderList(@RequestParam(value = "current",defaultValue = "0") int current,
                                       @RequestParam(value = "current",defaultValue = "10") int pageSize,
                                       @PathVariable int status){
        return responseResults.responseBySuccess("ok",iSenderService.findByStatus(current,pageSize,status));
    }
}
