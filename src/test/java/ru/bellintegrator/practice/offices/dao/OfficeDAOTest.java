package ru.bellintegrator.practice.offices.dao;

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
import ru.bellintegrator.practice.offices.model.Office;
import ru.bellintegrator.practice.offices.view.OfficeFilterView;
import ru.bellintegrator.practice.offices.view.OfficeView;
import ru.bellintegrator.practice.organizations.dao.OrganizationDAO;
import ru.bellintegrator.practice.organizations.model.Organization;

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
public class OfficeDAOTest {
    @Autowired
    private OfficeDAO dao;
    @Autowired
    private OrganizationDAO organizationDao;

    @Test
    public void getOfficesByOrgId() throws Exception {
        OfficeFilterView view = new OfficeFilterView(1L,  1l,  "Офис1",  true);
        List<Office> list = dao.getOfficesByOrgId( view);
        Assert.assertNotNull(list);
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void getOfficeById() throws Exception {
        Office office = dao.getOfficeById(1L);
        Assert.assertNotNull(office);
        Assert.assertNotNull(office.getName());
    }

    @Test
    public void updateOffice() throws Exception {
        Office office  =   dao.getOfficeById(1L);
        Assert.assertNotNull(office);

        office.setName("Офис1Изменен");
        office.setPhone("123");



        int result =  dao.updateOffice(office);

        Assert.assertTrue(result>0);
        Office office1  =   dao.getOfficeById(1L);
        Assert.assertEquals(office, office1);

    }

    @Test
    public void saveOffice() throws Exception {
        Office office = new Office();
        office.setName("Офис тест");
        office.setPhone("123445");
        office.setAddress("ул. Коммунистическая,1");
        Organization org = organizationDao.getOrgById(1l);

        Assert.assertNotNull(org);
        office.setOrganizationId(org);

        dao.saveOffice(office);

        OfficeFilterView view = new OfficeFilterView();
        view.setOrgId(1l);
        List<Office> offices = dao.getOfficesByOrgId(view);
        Office office1 = offices.get(offices.size()-1);
        Assert.assertNotNull(office1);
        Assert.assertTrue(offices.contains(office));

    }

    @Test
    public void deleteOffice() throws Exception {
        OfficeFilterView view = new OfficeFilterView();
        view.setOrgId(1l);
        List<Office> list = dao.getOfficesByOrgId(view);
        Assert.assertNotNull(list);
        Assert.assertFalse(list.isEmpty());

        Office office = list.get(list.size()-1);
        Assert.assertNotNull(office);
        dao.deleteOffice(office.getId());

        list = dao.getOfficesByOrgId(view);
        Assert.assertFalse(list.contains(office));
    }

}