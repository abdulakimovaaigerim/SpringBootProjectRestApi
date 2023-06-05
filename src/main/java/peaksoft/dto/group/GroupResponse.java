package peaksoft.dto.group;

import lombok.Builder;

@Builder
public record GroupResponse(
        Long id,
        String groupName,
        String imageLink,
        String description
) {

}
