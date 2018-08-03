package com.hsbc.team4.ordersystem.manager;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
* ManagerServiceImpl Tester.
*
* @author <Authors name>
* @since <pre>???? 3, 2018</pre>
* @version 1.0
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerServiceImplTest {

    @Autowired
    private IManagerService managerService ;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: findByName(String name)
    *
    */
    @Test
    public void testFindByName() throws Exception {
        System.out.println(managerService.findByName("Cady"));
    }

    /**
    *
    * Method: findByStatus(int current, int pageSize, int status)
    *
    */
    @Test
    public void testFindByStatus() throws Exception {
        //
    }

    /**
    *
    * Method: addEntity(Manager manager)
    *
    */
    @Test
    public void testAddEntity() throws Exception {
        Manager manager = new Manager();
        manager.setName("accountName");
        manager.setDepartment("dev");
        manager.setWorkNumber("7958");
        manager.setId(UUID.randomUUID().toString());
        manager.setStatus(1);
        managerService.addEntity(manager);
    }

    /**
    *
    * Method: updateStatusById(String id, int status)
    *
    */
    @Test
    public void testUpdateStatusById() throws Exception {
        managerService.updateStatusById("1",0);
    }

    /**
    *
    * Method: updateEntity(Manager manager)
    *
    */
    @Test
    public void testUpdateEntity() throws Exception {
        Manager manager = managerService.findById("1");
        manager.setWorkNumber("666");
        managerService.updateEntity(manager);
    }

    /**
    *
    * Method: findById(String id)
    *
    */
    @Test
    public void testFindById() throws Exception {
        System.out.println(managerService.findById("1"));
    }


}
