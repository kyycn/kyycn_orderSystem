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
     * @return sender
     */
    @PostMapping("/")
    public ResponseResults saveSender(@RequestBody Sender sender){
        return responseResults.responseBySuccess("ok",iSenderService.addEntity(sender));
    }

    /**
     * updateSender
     * @param sender
     * @return sender
     */
    @PutMapping("/")
    public ResponseResults updateSender(@RequestBody  Sender  sender){
        return responseResults.responseBySuccess("ok",iSenderService.updateEntity(sender));
    }

    /**
     * deleteSenderById
     * @param id
     * @return String
     */
    @DeleteMapping("/{id}")
    public ResponseResults deleteSenderById(@PathVariable String  id){
        return responseResults.responseBySuccess("ok",iSenderService.updateStatusById(id,1));
    }

    /**
     * querySenderById
     * @param id
     * @return Role
     */
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
    @GetMapping("/{status}")
    public ResponseResults getSenderList(@RequestParam(value = "current",defaultValue = "0") int current,
                                       @RequestParam(value = "current",defaultValue = "10") int pageSize,
                                       @PathVariable int status){
        return responseResults.responseBySuccess("ok",iSenderService.findByStatus(current,pageSize,status));
    }
}
