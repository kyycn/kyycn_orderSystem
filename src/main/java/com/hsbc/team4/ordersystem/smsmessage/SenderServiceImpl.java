package com.hsbc.team4.ordersystem.smsmessage;

import com.hsbc.team4.ordersystem.common.utils.PageableTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.smsmessage
 * @Description :
 * @Date : 2018/8/5
 */
@Service
public class SenderServiceImpl implements ISenderService {

    private final ISenderRepository iSenderRepository;

    @Autowired
    public SenderServiceImpl(ISenderRepository iSenderRepository) {
        this.iSenderRepository = iSenderRepository;
    }

    @Override
    public Page<Sender> findByStatus(int current, int pageSize, int status) {
        Pageable pageable= PageableTools.addTimeSortForDescAndPage(current,pageSize);
        return iSenderRepository.findByStatus(status,pageable);
    }

    @Override
    public Sender addEntity(Sender sender) {
        return iSenderRepository.save(sender);
    }

    @Override
    public int updateStatusById(String id, int status) {
        return iSenderRepository.updateStatusById(id,status);
    }

    @Override
    public Sender updateEntity(Sender sender) {
        return iSenderRepository.saveAndFlush(sender);
    }

    @Override
    public Sender findById(String id) {
        return iSenderRepository.findByEntityId(id);
    }
}
