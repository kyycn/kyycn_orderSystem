package com.hsbc.team4.ordersystem.emailmessage;

import com.hsbc.team4.ordersystem.common.utils.PageableTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.emailmessage
 * @Description :
 * @Date : 2018/8/5
 */
@Service
public class MailMsgServiceImpl implements IMailMsgService{
    private final IMailMsgRepository iMailMsgRepository;

    @Autowired
    public MailMsgServiceImpl(IMailMsgRepository iMailMsgRepository) {
        this.iMailMsgRepository = iMailMsgRepository;
    }

    @Override
    public Page<MailMsg> findByStatus(int current, int pageSize, int status) {
        Pageable pageable= PageableTools.addTimeSortForDescAndPage(current,pageSize);
        return iMailMsgRepository.findByStatus(status,pageable);
    }

    @Override
    public MailMsg addEntity(MailMsg mailMsg) {
        return iMailMsgRepository.save(mailMsg);
    }

    @Override
    public int updateStatusById(String id, int status) {
        return iMailMsgRepository.updateStatusById(id,status);
    }

    @Override
    public MailMsg updateEntity(MailMsg mailMsg) {
        return iMailMsgRepository.saveAndFlush(mailMsg);
    }

    @Override
    public MailMsg findById(String id) {
        return iMailMsgRepository.findByEntityId(id);
    }
}
