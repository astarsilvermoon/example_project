package ru.bellintegrator.practice.organizations.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.organizations.model.Organization;
import ru.bellintegrator.practice.organizations.view.OrganizationFilterView;
import ru.bellintegrator.practice.organizations.view.OrganizationView;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Alena on 29.03.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class OrganizationDAOTest {
    @Autowired
    private OrganizationDAO dao;
    @Autowired
    private EntityManager em;

    @Test
    public void getAll() throws Exception {
        List<Organization> list = dao.getAll(null);
        Assert.assertNotNull(list);
        Assert.assertFalse(list.isEmpty());

        List<Organization> list1 = dao.getAll(new OrganizationFilterView("Ромашка", true,  "581234567891"));
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1.size()==1);

        Assert.assertFalse(list.equals(list1));
    }

    @Test
    public void getOrgById() throws Exception {
        Organization org = dao.getOrgById(1l);
        Assert.assertNotNull(org);
        Assert.assertNotNull(org.getName());
    }

    @Test
    public void updateOrg() throws Exception {
        Organization organization = dao.getOrgById(1l);
        Assert.assertNotNull(organization);

        organization.setName("РомашкаТест");
        organization.setPhone("333");

        int result = dao.updateOrg(organization);
        Assert.assertTrue(result>0);

        Organization organization1 = dao.getOrgById(1l);
        Assert.assertNotNull(organization1);
    }

    @Test
    public void saveOrg() throws Exception {
    Organization org = new Organization();
        org.setName("Тест");
        org.setPhone("123");
        org.setAddress("ул Володарского");
        org.setKpp("2222");
        org.setIsActive(true);
        org.setFullName("Тестовая");
        org.setInn("555");

        boolean res = dao.saveOrg(org);
        Assert.assertTrue(res);

        List<Organization> list = dao.getAll(null);
        Assert.assertNotNull(list);
        Assert.assertTrue(list.contains(org));
    }

    @Test
    public void deleteOrg() throws Exception {
        List<Organization> list = dao.getAll(null);
        Assert.assertNotNull(list);
        Assert.assertFalse(list.isEmpty());

        Organization org = list.get(list.size()-1);
        Assert.assertNotNull(org);
        dao.deleteOrg(org.getId());

        list = dao.getAll(null);
        Assert.assertFalse(list.contains(org));
    }

}