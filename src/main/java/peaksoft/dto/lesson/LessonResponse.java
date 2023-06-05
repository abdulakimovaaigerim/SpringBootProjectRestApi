package peaksoft.dto.lesson;

import lombok.Builder;

@Builder
public record LessonResponse(
        Long id,
        String lessonName) {
}
