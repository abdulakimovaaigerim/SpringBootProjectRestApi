package peaksoft.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(generator = "company_gen", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "company_gen", sequenceName = "company_seq", allocationSize = 1)
    private Long id;
    @NotEmpty(message = "Name should not be empty!")
    private String name;
    @NotEmpty(message = "Country should not be empty!")
    private String country;

    @NotEmpty(message = "Address should not be empty!")
    private String address;

    @NotEmpty(message = "PhoneNumber should not be empty!")
    @Pattern(regexp = "\\+996\\d{9}", message = "Phone number should start with +996 and consist of 13 characters!")
    @Column(unique = true)
    private String phoneNumber;

    @ManyToMany(mappedBy = "companies", cascade = {
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.MERGE})
    private List<Instructor> instructors = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    private List<Course> courses = new ArrayList<>();

    public void addInstructor(Instructor instructor) {
        this.instructors.add(instructor);
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }
}
