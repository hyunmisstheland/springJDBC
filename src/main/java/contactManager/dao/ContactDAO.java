package contactManager.dao;

import contactManager.Model.Contact;

import java.util.List;

public interface ContactDAO {
    public int save(Contact contact);
    public Contact get(Integer id);
    public int update(Contact contact);
    public int delete(Integer id);
    public List<Contact> list();
}
