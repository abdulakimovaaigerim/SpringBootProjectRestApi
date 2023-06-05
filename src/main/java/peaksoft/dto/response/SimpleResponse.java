package peaksoft.dto.response;

import lombok.Builder;

@Builder
public record SimpleResponse(
        String status,
        String message
) {
}
