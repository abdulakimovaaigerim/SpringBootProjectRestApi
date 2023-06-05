package peaksoft.dto.lesson;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadLessonRequest {
    private String lessonName;
    private Long courseId;
}
