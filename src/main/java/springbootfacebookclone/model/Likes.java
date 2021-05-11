package springbootfacebookclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
public class Likes {
    @Id
    @SequenceGenerator(
            name = "like_sequence",
            sequenceName = "like_sequence",
            allocationSize = 1)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private Person person;

    @OneToOne(optional = false)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private Post post;
}