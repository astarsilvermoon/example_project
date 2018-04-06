package ru.bellintegrator.practice.dictionaries.dao;

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
import ru.bellintegrator.practice.dictionaries.model.DocType;

import java.util.List;

/**
 * Created by Alena on 28.03.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class DocTypeDAOTest {
    @Autowired
    private DocTypeDAO dao;
    @Test
    public void getAll() throws Exception {
    List<DocType> list =  dao.getAll();
        Assert.assertNotNull(list);
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void getByNameAndCode() throws Exception {
        DocType docType = dao.getByNameAndCode("Паспорт иностранного гражданина", "10");
        Assert.assertNull(docType);

        docType = dao.getByNameAndCode("Паспорт гражданина Российской Федерации", "21");
        Assert.assertNotNull(docType);
    }

}