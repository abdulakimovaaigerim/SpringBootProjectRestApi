package peaksoft.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructors")
@Getter
@Setter
@NoArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(generator = "instructor_gen", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "instructor_gen", sequenceName = "instructor_seq", allocationSize = 1)
    private Long id;
    @NotEmpty(message = "First name should not be empty!")
    private String firstName;
    @NotEmpty(message = "Last name should not be empty!")
    private String lastName;
    @NotEmpty(message = "Phone number should not be empty!")
    @Pattern(regexp = "\\+996\\d{9}", message = "Phone number should start with +996 and consist of 13 characters!")
    private String phoneNumber;
    @NotEmpty(message = "Specialization name should not be empty!")
    private String specialization;

    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    private List<Company> companies = new ArrayList<>();

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();


    public void addCompany(Company company) {
        this.companies.add(company);
    }

    public void addCourse(Course course) {
        this.courses.add(course);

    }
}
