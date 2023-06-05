package peaksoft.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(generator = "course_gen", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "course_gen", sequenceName = "course_seq", allocationSize = 1)
    private Long id;
    @NotEmpty(message = "CourseName should not be empty!")
    private String courseName;

    @NotNull(message = "date should not be empty!")
    @Future(message = "date must be future time!")
    private LocalDate dateOfStart;

    @NotEmpty(message = "Description should not be empty!")
    private String description;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.MERGE})
    private Company company;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.MERGE, CascadeType.REMOVE})
    private Instructor instructor;

    @OneToMany(mappedBy = "course", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    private List<Lesson> lessons;

    @ManyToMany(mappedBy = "courses", cascade = {
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.MERGE})
    private List<Group> groups = new ArrayList<>();


    public void addLesson(Lesson lesson) {
        this.lessons.add(lesson);
    }

    public void addGroup(Group group) {
        this.groups.add(group);
    }
}
