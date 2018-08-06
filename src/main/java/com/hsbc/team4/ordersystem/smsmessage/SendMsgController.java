package com.hsbc.team4.ordersystem.smsmessage;

import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
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
@RequestMapping("/sendMsg")
public class SendMsgController {
    private final ISendMsgService iSendMsgService;
    private final ResponseResults responseResults;
    @Autowired
    public SendMsgController(ISendMsgService iSendMsgService, ResponseResults responseResults) {
        this.iSendMsgService = iSendMsgService;
        this.responseResults = responseResults;
    }

    /**
     * deleteSendMsgById
     * @param id
     * @return String
     */
    @DeleteMapping("/{id}")
    public ResponseResults deleteSendMsgById(@PathVariable String  id){
        return responseResults.responseBySuccess("ok",iSendMsgService.updateStatusById(id,1));
    }

    /**
     * querySendMsgById
     * @param id
     * @return Role
     */
    @GetMapping("/{id}")
    public ResponseResults querySenderById(@PathVariable String id){
        return responseResults.responseBySuccess("ok",iSendMsgService.findById(id));
    }

    /**
     * get SendMsgList
     * @param current
     * @param pageSize
     * @param status
     * @return  ResponseResults
     */
    @GetMapping("/{status}")
    public ResponseResults getSenderList(@RequestParam(value = "current",defaultValue = "0") int current,
                                         @RequestParam(value = "current",defaultValue = "10") int pageSize,
                                         @PathVariable int status){
        return responseResults.responseBySuccess("ok",iSendMsgService.findByStatus(current,pageSize,status));
    }

}
