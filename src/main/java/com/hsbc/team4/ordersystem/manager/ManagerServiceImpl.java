package com.hsbc.team4.ordersystem.manager;

import com.hsbc.team4.ordersystem.common.factory.UUIDFactory;
import com.hsbc.team4.ordersystem.common.utils.ValidatorTools;
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

    private final IManagerRepository iManagerRepository;
    private final IManagerAccountService managerAccountService;

    @Autowired
    public ManagerServiceImpl(IManagerRepository iManagerRepository, IManagerAccountService managerAccountService) {
        this.iManagerRepository = iManagerRepository;
        this.managerAccountService = managerAccountService;
    }


    @Override
    public Manager findByWordNumber(String wordNumber) {
        if (ValidatorTools.isUsername(wordNumber)){
            return iManagerRepository.findByWorkNumber(wordNumber);
        }
        return null;
    }

    @Override
    public Page<Manager> findByStatus(int current, int pageSize, int status) {
        return null;
    }

    @Override
    public Manager addEntity(Manager manager) {
        if (iManagerRepository.findByWorkNumber(manager.getWorkNumber())==null){
            manager.setId(new UUIDFactory().getUUID());
            manager.setStatus(1);
            manager.setCreateTime(System.currentTimeMillis());
            ManagerAccount managerAccount = new ManagerAccount();
            managerAccount.setId(new UUIDFactory().getUUID());
            managerAccount.setName(manager.getWorkNumber());
            managerAccount.setPassword(manager.getWorkNumber());
            managerAccount.setStatus(1);
            managerAccountService.addEntity(managerAccount);
            return iManagerRepository.save(manager);
        }
        return null;
    }

    @Override
    public int updateStatusById(String id, int status) {
            return iManagerRepository.updateStatusById(id, status);
    }

    @Override
    public Manager updateEntity(Manager manager) {
        if(manager.getId()!=null&&!"".equals(manager.getId())){
            return iManagerRepository.save(manager);
        }
        return null;
    }

    @Override
    public Manager findById(String id) {
        return iManagerRepository.findByEntityId(id);
    }
}
