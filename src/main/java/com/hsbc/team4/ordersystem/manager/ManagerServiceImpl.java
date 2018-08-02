package com.hsbc.team4.ordersystem.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author:Cady
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.manager
 * @Description:
 * @Date date: 2018-08-02
 */
@Service
public class ManagerServiceImpl implements IManagerService{

    private IManagerRepository iManagerRepository;

    @Autowired
    public ManagerServiceImpl(IManagerRepository iManagerRepository) {
        this.iManagerRepository = iManagerRepository;
    }

    /**
     * @Description find manager by name through the repository
     * @Date: 20:22 2018-08-02
     * @Param name
     * @return com.hsbc.team4.ordersystem.manager.Manager
     */
    @Override
    public Manager findByName(String name) {
        return iManagerRepository.findByName(name);
    }

    @Override
    public Page<Manager> findByStatus(int current, int pageSize, int status) {
        return null;
    }

    @Override
    public Manager addEntity(Manager manager) {
        return null;
    }

    @Override
    public int updateStatusById(String id, int status) {
        return 0;
    }

    @Override
    public Manager updateEntity(Manager manager) {
        return null;
    }

    @Override
    public Manager findById(String id) {
        return null;
    }
}
