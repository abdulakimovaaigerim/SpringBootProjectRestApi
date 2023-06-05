package peaksoft.dto.task;

import lombok.Builder;

@Builder
public record TaskRequest(
        String taskName,
        String taskText,
        String deadline
) {
}
