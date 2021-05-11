package springbootfacebookclone.model;

import lombok.Data;
import javax.persistence.*;
@Data
@Entity
public class Comment {
    @Id
    @SequenceGenerator(
            name = "comment_sequence",
            sequenceName = "comment_sequence",
            allocationSize = 1)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "comment",
            nullable = false,
            columnDefinition = "VARCHAR(45)"
    )
    private String comment;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private Person person;
    @ManyToOne
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private Post post;
}