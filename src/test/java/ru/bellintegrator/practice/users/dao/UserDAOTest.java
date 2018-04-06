package ru.bellintegrator.practice.users.dao;

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
import ru.bellintegrator.practice.dictionaries.dao.CountryCodeDAO;
import ru.bellintegrator.practice.dictionaries.dao.DocTypeDAO;
import ru.bellintegrator.practice.dictionaries.model.CountryCode;
import ru.bellintegrator.practice.dictionaries.model.DocType;
import ru.bellintegrator.practice.offices.dao.OfficeDAO;
import ru.bellintegrator.practice.offices.model.Office;
import ru.bellintegrator.practice.userdocs.dao.UserDocDAO;
import ru.bellintegrator.practice.userdocs.model.UserDoc;
import ru.bellintegrator.practice.users.model.User;
import ru.bellintegrator.practice.users.view.UserFilterView;
import ru.bellintegrator.practice.users.view.UserView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class UserDAOTest {
    @Autowired
    private UserDAO dao;
    @Autowired
    private OfficeDAO officeDAO;
    @Autowired
    private CountryCodeDAO countryCodeDAO;
    @Autowired
    private DocTypeDAO docTypeDAO;
    @Autowired
    private UserDocDAO userDocDAO;
    @Autowired
     private EntityManager em;

    @Test
    public void getAll() throws Exception {
        List<User> list = dao.getAll(1l, null);
        Assert.assertNotNull(list);
        Assert.assertFalse(list.isEmpty());

        List<User> list1 = dao.getAll(1l, new UserFilterView( 1l,  "Иванов",  "Иван", null, null, null, null));
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1.size()==1);

        Assert.assertFalse(list.equals(list1));
    }

    @Test
    public void getUserById() throws Exception {
        User user = dao.getUserById(1l);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getFirstName());
    }

    @Test
    public void updateUser() throws Exception {
        User user = dao.getUserById(3l);
        Assert.assertNotNull(user);

        User updated = user;
        updated.setFirstName("Колышкин");
        Office office = officeDAO.getOfficeById(1l);
        updated.setOffice(office);

        UserDoc doc = new UserDoc();
                doc.setDocNumber("777");
                doc.setUser(updated);

                DocType type = docTypeDAO.getByNameAndCode("Паспорт гражданина Российской Федерации", "21");
                doc.setDocType(type);
                doc.setDocDate(new SimpleDateFormat("dd-MM-yyyy").parse("12-01-2000"));

                CountryCode countryCode = countryCodeDAO.getByNameAndCode("РОССИЯ", "643");
                doc.setCountryCode(countryCode);

        updated.setUserDoc(doc);
        em.merge(updated);

        int result = dao.updateUser(updated);

        Assert.assertTrue(result>0);

        User user1 = dao.getUserById(3l);
        User user2 = dao.getUserById(1l);
        User user3 = dao.getUserById(2l);
        Assert.assertNotNull(user1);
    }

    @Test
    public void saveUser() throws Exception {
        User user = new User();
        user.setFirstName("Клишин");
        user.setPhone("1111");
        user.setPosition("заместитель ген директора");
        user.setLastName("Олег");

        Office office = officeDAO.getOfficeById(1l);
        Assert.assertNotNull(office);
        user.setOffice(office);
        boolean result = dao.saveUser(user);
        Assert.assertTrue(result);

        List<User> list = dao.getAll(1l, null);
        Assert.assertNotNull(list);
        Assert.assertTrue(list.contains(user));

    }

    @Test
    public void deleteUser() throws Exception {
        List<User> list = dao.getAll(1l, null);
        Assert.assertNotNull(list);
        Assert.assertFalse(list.isEmpty());

        User user = list.get(list.size()-1);
        Assert.assertNotNull(user);

       dao.deleteUser(user.getId());
        list = dao.getAll(1l, null);
        Assert.assertFalse(list.contains(user));
    }

}