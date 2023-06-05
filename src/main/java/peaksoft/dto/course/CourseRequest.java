package peaksoft.dto.course;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CourseRequest(
        String courseName,
        LocalDate dateOfStart,
        String description

) {
}
