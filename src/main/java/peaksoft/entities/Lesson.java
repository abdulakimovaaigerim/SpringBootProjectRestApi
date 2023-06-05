package peaksoft.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(generator = "lesson_generator", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "lesson_generator", sequenceName = "lesson_seq", allocationSize = 1)
    private Long id;
    @NotEmpty(message = "Lesson name should not be empty!")
    private String lessonName;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Course course;

    @OneToMany(mappedBy = "lesson", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    private List<Task> tasks = new ArrayList<>();


    public void addTask(Task task) {
        this.tasks.add(task);
    }
}
