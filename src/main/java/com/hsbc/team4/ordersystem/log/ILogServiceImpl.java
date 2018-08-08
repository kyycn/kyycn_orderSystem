package com.hsbc.team4.ordersystem.log;

import org.springframework.beans.factory.annotation.Autowired;
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
}
