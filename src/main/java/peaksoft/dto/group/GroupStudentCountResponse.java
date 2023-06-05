package peaksoft.dto.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GroupStudentCountResponse {
  private  Long groupId;
  private Long studentCount;

}
