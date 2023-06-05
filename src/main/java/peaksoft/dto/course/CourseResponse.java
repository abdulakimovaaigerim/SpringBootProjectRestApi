package peaksoft.dto.course;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CourseResponse(
        Long id,
        String courseName,

        LocalDate dateOfStart,
        String description

) {
}
