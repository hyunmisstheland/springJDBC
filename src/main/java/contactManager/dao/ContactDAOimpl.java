package contactManager.dao;

import contactManager.Model.Contact;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ContactDAOimpl implements  ContactDAO{
    private JdbcTemplate jdbcTemplate;

    public ContactDAOimpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int save(Contact contact) {
        String sql = "INSERT INTO contact (name, email, address,phone) VALUES (?,?,?,?)";
        return jdbcTemplate.update(sql,contact.getName(),contact.getEmail(), contact
        .getAddress(),contact.getPhone());
    }
    @Override
    public Contact get(Integer id) {
        String sql = "SELECT * FROM contact WHERE contact_id = " + id;
//        RowMapper<Contact> rowMapper = new RowMapper() {
//            @Override
//            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Integer contact_id = rs.getInt("contact_id");
//                String name = rs.getString("name");
//                String address = rs.getString("address");
//                String phone = rs.getString("phone");
//                String email = rs.getString("email");
//
//                return new Contact(contact_id,name, address, phone, email);
//            }
//        };
//        Contact contact =  jdbcTemplate.queryForObject(sql, rowMapper);
//        System.out.println(contact.getName());
//        System.out.println(contact.getEmail());
//        System.out.println(contact.getPhone());
//        System.out.println(contact.getAddress());
//        return contact;
        ResultSetExtractor<Contact> extractor = new ResultSetExtractor() {
            @Override
            public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()){
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    return new Contact(id,name, address, phone, email);
                }
                return null;
            }
        };
        return jdbcTemplate.query(sql, extractor);
    };

    @Override
    public int update(Contact contact) {
        String sql = "UPDATE contact SET name = ? , email = ?, address= ?, phone = ? WHERE  contact_id = ?";
        return jdbcTemplate.update(sql, contact.getName(),contact.getAddress(), contact.getEmail(), contact.getPhone(),contact.getContact_id());
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM contact WHERE contact_id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Contact> list() {
        String sql = "SELECT * FROM contact";
        RowMapper rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer contact_id = rs.getInt("contact_id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String email = rs.getString("email");

                return new Contact(contact_id, name, address, phone, email);
            }
        };
        List<Contact> contactList = jdbcTemplate.query(sql, rowMapper);
        for(Contact aCon : contactList){
            System.out.println(aCon.toString());;
        }
        return jdbcTemplate.query(sql, rowMapper);
    }
}
