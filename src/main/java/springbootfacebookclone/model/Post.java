package springbootfacebookclone.model;

import lombok.Data;
import javax.persistence.*;
import java.util.List;
import static javax.persistence.GenerationType.SEQUENCE;
@Data
@Entity
public class Post {
    @Id
    @SequenceGenerator(
            name = "post_sequence",
            sequenceName = "post_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy  = SEQUENCE,
            generator = "post_sequence"
    )
    @Column(
            name = "postId",
            updatable = false
    )
    private Long postId;

    @Column(
            name = "title",
            nullable = false,
            columnDefinition = "VARCHAR(45)"
    )
    private String title;

    @Column(
            name = "body",
            nullable = false,
            columnDefinition = "VARCHAR(45)"
    )
    private String body;

    @Column(
            name = "imageName",
            nullable = false,
            columnDefinition = "VARCHAR(45)"
    )
    private String imageName;

//    @Column(
//            name = "name",
//            nullable = false,
//            columnDefinition = "VARCHAR(45)"
//    )
//    private String name;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private Person person;

    @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @OneToOne(mappedBy = "post")
    private Likes mylike;
}