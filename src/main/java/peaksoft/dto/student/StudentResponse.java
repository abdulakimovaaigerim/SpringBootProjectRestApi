package peaksoft.dto.student;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import peaksoft.enums.StudyFormat;

@Getter
@Setter
@Builder
@NoArgsConstructor

public class StudentResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private StudyFormat studyFormat;
    private Boolean isBlocked;

    public StudentResponse(Long id, String firstName, String lastName, String email, String phoneNumber, StudyFormat studyFormat, Boolean isBlocked) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.studyFormat = studyFormat;
        this.isBlocked = isBlocked;
    }
}

