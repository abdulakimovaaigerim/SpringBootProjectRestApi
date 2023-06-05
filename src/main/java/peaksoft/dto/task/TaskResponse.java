package peaksoft.dto.task;

import lombok.Builder;

@Builder
public record TaskResponse(

        Long id,
        String taskName,
        String taskText,
        String deadline
) {
}
