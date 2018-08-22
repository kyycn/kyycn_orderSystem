package com.hsbc.team4.ordersystem.log;

import com.hsbc.team4.ordersystem.common.utils.PageableTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.log
 * @Description :
 * @Date : 2018/8/6
 */
@Service
public class ILogServiceImpl implements ILogService{
    private final ILogRepository iLogRepository;

    @Autowired
    public ILogServiceImpl(ILogRepository iLogRepository) {
        this.iLogRepository = iLogRepository;
    }

    @Override
    public Log insertLog(Log log) {
        return iLogRepository.save(log);
    }

    @Override
    public Page<Log> findByOperateTypeContains(String operateType,int current,int pageSize) {
        Pageable pageable= PageableTools.addTimeSortForDescAndPage(current,pageSize);
        return iLogRepository.findByOperateTypeContains(operateType,pageable);
    }

    @Override
    public Page<Log> findAll(int current, int pageSize) {
        Pageable pageable= PageableTools.basicPage(current,pageSize);
        return iLogRepository.findAll(pageable);
    }


}
