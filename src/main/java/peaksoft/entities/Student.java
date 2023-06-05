package peaksoft.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.StudyFormat;


@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(generator = "student_gen", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "student_gen", sequenceName = "student_seq", allocationSize = 1)
    private Long id;

    @NotEmpty(message = "First name should not be empty!")
    private String firstName;
    @NotEmpty(message = "Last name should not be empty!")
    private String lastName;
    @NotEmpty(message = "PhoneNumber name should not be empty!")
    @Pattern(regexp = "\\+996\\d{9}", message = "Phone number should start with +996 and consist of 13 characters!")
    private String phoneNumber;
    @Column(unique = true)
    @NotEmpty(message = "Email should not be empty!")
    @Email(message = "Please provide a valid email address!")
    private String email;

    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;
    private Boolean isBlocked;


    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Group group;

}
