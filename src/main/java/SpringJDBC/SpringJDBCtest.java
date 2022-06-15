package SpringJDBC;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SpringJDBCtest {
    public static void main(String[] args){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/students");
        dataSource.setUsername("nthuynh");
        dataSource.setPassword("1406");

        JdbcTemplate newTem = new JdbcTemplate(dataSource);
//        String sql = "SELECT * FROM student WHERE student_code = '20202111'";
        String sql = "SELECT * FROM student WHERE student_id = 9";
        RowMapper<Student> rowMapper = new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer student_id = rs.getInt("student_id");
                String name = rs.getString("name");
                String student_code = rs.getString("student_code");
                String country = rs.getString("country");
                return new Student(student_id,name, student_code,country);
            }
        };
//        List<Student> listStudent = newTem.query(sql, rowMapper);
        Student aStudent = newTem.queryForObject(sql,rowMapper);

//        for(Student aStudent:listStudent){
//            System.out.println(aStudent.toString());
//        }
        System.out.println(aStudent.toString());

    }
}
