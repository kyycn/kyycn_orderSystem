package com.hsbc.team4.ordersystem.manager;

import com.hsbc.team4.ordersystem.common.base.BaseEntity;
import com.hsbc.team4.ordersystem.common.factory.UUIDFactory;
import com.hsbc.team4.ordersystem.common.utils.BeanValidator;
import com.hsbc.team4.ordersystem.common.utils.PageableTools;
import com.hsbc.team4.ordersystem.common.utils.ValidatorTools;
import com.hsbc.team4.ordersystem.common.utils.WorkNumberUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanClassLoaderAware;
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
    private final UUIDFactory uuidFactory;
    private final BeanValidator beanValidator;
    private final WorkNumberUtil workNumberUtil;
    private final IDepartmentService departmentService;

    @Autowired
    public ManagerServiceImpl(IManagerRepository iManagerRepository, IManagerAccountService managerAccountService, UUIDFactory uuidFactory, BeanValidator beanValidator, WorkNumberUtil workNumberUtil, IDepartmentService departmentService) {
        this.iManagerRepository = iManagerRepository;
        this.managerAccountService = managerAccountService;
        this.uuidFactory = uuidFactory;
        this.beanValidator = beanValidator;
        this.workNumberUtil = workNumberUtil;
        this.departmentService = departmentService;
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
        return iManagerRepository.findByStatus(status, PageableTools.basicPage(current,pageSize));
    }

    /**
     * @Description set the workNumber for manager, update department, add ManagerAccount, add manager
     * @Date: 14:55 2018-08-13
     * @Param manager
     * @return com.hsbc.team4.ordersystem.manager.Manager
     */
    @Override
    public Manager addEntity(Manager manager) {
        if (!beanValidator.validateObject(manager).isEmpty()){
            return null;
        }
        Department department = departmentService.findByName(manager.getDepartment());
        if (department != null){
            String workNumber = workNumberUtil.getWordNumber(department.getId(), department.getOrderNum()+1);
            department.setOrderNum(department.getOrderNum()+1);
            departmentService.updateEntity(department);
            manager.setId(uuidFactory.getUUID());
            manager.setWorkNumber(workNumber);
            manager.setStatus(1);
            manager.setCreateTime(System.currentTimeMillis());
            ManagerAccount managerAccount = new ManagerAccount();
            managerAccount.setId(uuidFactory.getUUID());
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
        ManagerAccount account = managerAccountService.findByName(findById(id).getWorkNumber());
        managerAccountService.updateStatusById(account.getId(), status);
        return iManagerRepository.updateStatusById(id, status);
    }

    @Override
    public Manager updateEntity(Manager manager) {
        if (!beanValidator.validateObject(manager).isEmpty()
                || departmentService.findByName(manager.getDepartment())==null){
            return null;
        }
        return iManagerRepository.save(manager);
    }

    @Override
    public Manager findById(String id) {
        if (StringUtils.isNotBlank(id)){
            return iManagerRepository.findByEntityId(id);
        }
        return null;
    }
}
