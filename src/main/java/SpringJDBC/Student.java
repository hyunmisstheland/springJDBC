package SpringJDBC;

public class Student {
    private Integer student_id;
    private String name;
    private String student_code;
    private String country;

    public Student(Integer student_id,String name, String student_code, String country){
        this.student_id = student_id;
        this.student_code = student_code;
        this.name = name;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Student{" +
                "student_id=" + student_id +
                ", name='" + name + '\'' +
                ", student_code='" + student_code + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudent_code() {
        return student_code;
    }

    public void setStudent_code(String student_code) {
        this.student_code = student_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
