package peaksoft.dto.instructor;

import lombok.Builder;

@Builder
public record InstructorRequest(
        String firstName,
        String lastName,
        String phoneNumber,
        String specialization
) {
}
