package springbootfacebookclone.model;

import lombok.Data;
import javax.persistence.*;
import java.util.List;
import static javax.persistence.GenerationType.SEQUENCE;

@Data
@Entity(name = "person")
@Table(
        name = "person",
        uniqueConstraints = {
                @UniqueConstraint(name = "person_email_contraint", columnNames = "email")
        }
)
public class Person {
    @Id
    @SequenceGenerator(name = "person_sequence", sequenceName = "person_sequence", allocationSize = 1)
    @GeneratedValue(strategy  = SEQUENCE, generator = "person_sequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname", nullable = false, columnDefinition = "VARCHAR(45)")
    private String firstname;

    @Column(name = "lastname", nullable = false, columnDefinition = "VARCHAR(45)")
    private String lastname;

    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(45)")
    private String password;

    @Column(name = "email", nullable = false, columnDefinition = "VARCHAR(45)")
    private String email;

    @Column(name = "dateOfBirth", nullable = false, columnDefinition = "VARCHAR(45)")
    private String dob;

    @Column(name = "gender", nullable = false, columnDefinition = "VARCHAR(45)")
    private String gender;

    @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE)
    private List<Post> post;

    @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @OneToOne(mappedBy = "person")
    private Likes myLike;
}