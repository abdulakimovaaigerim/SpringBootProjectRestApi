package peaksoft.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(generator = "task_gen", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "task_gen", sequenceName = "task_seq", allocationSize = 1)
    private Long id;
    @NotEmpty(message = "task should not be empty!")
    private String taskName;
    @NotEmpty(message = "Name should not be empty!")
    private String taskText;
    @NotEmpty(message = "Deadline should not be empty!")
    @Future(message = "deadline must be future time!")
    private String deadline;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.MERGE},
            fetch = FetchType.EAGER)
    private Lesson lesson;

}
