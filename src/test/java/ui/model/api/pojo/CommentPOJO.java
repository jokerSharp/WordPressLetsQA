package ui.model.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentPOJO {
    private Integer id;
    private Integer post;
    private Integer author;
    private String content;
}
