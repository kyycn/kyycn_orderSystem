package com.hsbc.team4.ordersystem.smsmessage;

import com.hsbc.team4.ordersystem.common.utils.HttpUtil;
import com.hsbc.team4.ordersystem.common.utils.PageableTools;
import com.hsbc.team4.ordersystem.common.utils.RandomStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.smsmessage
 * @Description :
 * @Date : 2018/8/5
 */
@Service
@Slf4j
public class SendMsgServiceImpl implements ISendMsgService{

    private final ISendMsgRepository iSendMsgRepository;

    @Autowired
    public SendMsgServiceImpl(ISendMsgRepository iSendMsgRepository) {
        this.iSendMsgRepository = iSendMsgRepository;
    }

    @Override
    public Page<SendMsg> findByStatus(int current, int pageSize, int status) {
        Pageable pageable= PageableTools.addTimeSortForDescAndPage(current,pageSize);
        return iSendMsgRepository.findByStatus(status,pageable);
    }

    @Override
    public SendMsg addEntity(SendMsg sendMsg) {
        return iSendMsgRepository.save(sendMsg);
    }

    @Override
    public int updateStatusById(String id, int status) {
        return iSendMsgRepository.updateStatusById(id,status);
    }

    @Override
    public SendMsg updateEntity(SendMsg sendMsg) {
        return iSendMsgRepository.saveAndFlush(sendMsg);
    }

    @Override
    public SendMsg findById(String id) {
        return iSendMsgRepository.findByEntityId(id);
    }
    /**
     * SMS
     */
    @Override
    public  String execute(Sender sender, SendMsg sendMsg) {
        String tmpSmsContent = null;
        String code= RandomStringUtils.getRandomInt(6);
        try{
            tmpSmsContent = URLEncoder.encode(sender.getTemplateId(), "UTF-8");
        }catch(Exception e){
            log.warn("the exception",e);
        }
        StringBuilder stringBuilder=new StringBuilder();
        //url
        String url = sender.getBaseUrl();
        stringBuilder.append("accountSid=");
        stringBuilder.append(sender.getAccountId());
        stringBuilder.append("&to=");
        stringBuilder.append(sendMsg.getPhone());
        stringBuilder.append("&param=");
        stringBuilder.append(code);
        stringBuilder.append(",");
        stringBuilder.append(sendMsg.getExpirationTime());
        stringBuilder.append("&templateid=");
        stringBuilder.append(tmpSmsContent);
        stringBuilder.append(HttpUtil.createCommonParam(sender));

        String result = HttpUtil.post(url, stringBuilder.toString());
        log.info("result:" + System.lineSeparator() + result);
        return code;
    }
}
