package contactManager.Test;

import contactManager.Model.Contact;
import contactManager.dao.ContactDAO;
import contactManager.dao.ContactDAOimpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;


class ContactDAOTest {
    private DriverManagerDataSource dataSource;
    private ContactDAO dao;
    @Test
    void save() {
        getDAO();
        Contact contact = new Contact("Huynh", "nthuynh@cmcglobal.vn","0865448936","TX-HN");
        int result = dao.save(contact);
        Assertions.assertTrue(result > 0);
    }

    private void getDAO() {
        dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/contacts");
        dataSource.setUsername("nthuynh");
        dataSource.setPassword("1406");

        dao = new ContactDAOimpl(dataSource);
    }

    @Test
    void get() {
        getDAO();
        Integer contactId = 2;
        Contact contact = dao.get(contactId);
        Assertions.assertTrue(contact != null);
    }

    @Test
    void update() {
        getDAO();
        Contact contact = new Contact(2,"Uyen","baouyen091100@gmail.com","0344337220","HN");
        int result = dao.update(contact);
        Assertions.assertTrue(result > 0);
    }

    @Test
    void delete() {
        getDAO();
        Integer contactId = 1;
        int result = dao.delete(contactId);
        Assertions.assertTrue(result > 0);
    }

    @Test
    void list() {
        getDAO();
        List<Contact> contactList = dao.list();
        Assertions.assertTrue(contactList.size() > 0);
    }
}