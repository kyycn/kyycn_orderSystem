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
public class MailSenderServiceImpl implements IMailSenderService{
    private final IMailSenderRepository iMailSenderRepository;

    @Autowired
    public MailSenderServiceImpl(IMailSenderRepository iMailSenderRepository) {
        this.iMailSenderRepository = iMailSenderRepository;
    }

    @Override
    public Page<MailSender> findByStatus(int current, int pageSize, int status) {
        Pageable pageable= PageableTools.addTimeSortForDescAndPage(current,pageSize);
        return iMailSenderRepository.findByStatus(status,pageable);
    }

    @Override
    public MailSender addEntity(MailSender mailSender) {
        return iMailSenderRepository.save(mailSender);
    }

    @Override
    public int updateStatusById(String id, int status) {
        return iMailSenderRepository.updateStatusById(id,status);
    }

    @Override
    public MailSender updateEntity(MailSender mailSender) {
        return iMailSenderRepository.saveAndFlush(mailSender);
    }

    @Override
    public MailSender findById(String id) {
        return iMailSenderRepository.findByEntityId(id);
    }
}
