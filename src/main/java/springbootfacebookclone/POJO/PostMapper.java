package springbootfacebookclone.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.nio.file.Path;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostMapper {
    private Long id;
    private String title;
    private String body;
    private String imageName;
    private String name;
    private String numEmail;
    private int noLikes;
    private int noComments;
    private boolean likedPost;
    private Path path;
}
