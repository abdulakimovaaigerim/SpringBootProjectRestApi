package peaksoft.dto.student;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import peaksoft.enums.StudyFormat;

@Builder
public record StudentRequest(
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
//        @Enumerated(EnumType.STRING)
        StudyFormat studyFormat

) {

}
