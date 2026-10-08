package fu.DE201056.Chapter6_Slot9_Demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Họ tên không được để trống")
    private String name;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Tuổi không được để trống")
    @Min(value = 18, message = "Tuổi phải từ 18 trở lên")
    @Max(value = 30, message = "Tuổi không quá 30")
    private Integer age;

    @NotNull(message = "Chuyên ngành không được để trống")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "major_name", referencedColumnName = "name")
    private Major major;

    @NotNull(message = "GPA không được để trống")
    @Min(value = 0, message = "GPA từ 0.0 đến 4.0")
    @Max(value = 4, message = "GPA từ 0.0 đến 4.0")
    private Double gpa;

    public Student() {}

    public Student(String name, String email, Integer age, Major major, Double gpa) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.major = major;
        this.gpa = gpa;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public Major getMajor() { return major; }
    public void setMajor(Major major) { this.major = major; }
    public Double getGpa() { return gpa; }
    public void setGpa(Double gpa) { this.gpa = gpa; }
}