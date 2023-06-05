package peaksoft.dto.lesson;

import lombok.*;

@Builder
public record LessonRequest (
        String lessonName,
        String description
){



}
