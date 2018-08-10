package com.hsbc.team4.ordersystem.manager;

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
 * @Date date: 2018-08-06
 */
@Service
public class ManagerAccountServiceImpl implements IManagerAccountService {

    private final IManagerAccountRepository managerAccountRepository;

    @Autowired
    public ManagerAccountServiceImpl(IManagerAccountRepository managerAccountRepository) {
        this.managerAccountRepository = managerAccountRepository;
    }

    @Override
    public Page<ManagerAccount> findByStatus(int current, int pageSize, int status) {
        return null;
    }

    @Override
    public ManagerAccount addEntity(ManagerAccount managerAccount) {
        return managerAccountRepository.save(managerAccount);
    }

    @Override
    public int updateStatusById(String id, int status) {
        return managerAccountRepository.updateStatusById(id, status);
    }

    @Override
    public ManagerAccount updateEntity(ManagerAccount managerAccount) {
        if (ValidatorTools.isPassword(managerAccount.getPassword())){
            return managerAccountRepository.save(managerAccount);
        }
        return null;
    }

    @Override
    public ManagerAccount findById(String id) {
        return managerAccountRepository.findByEntityId(id);
    }

    @Override
    public ManagerAccount findByName(String name) {
        return managerAccountRepository.findByName(name);
    }
}
