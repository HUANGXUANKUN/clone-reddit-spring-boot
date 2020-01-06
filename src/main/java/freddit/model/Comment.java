package freddit.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
//@Data // includes getter/setter and withArgsConstructor
@RequiredArgsConstructor
@Getter @Setter
@ToString
public class Comment extends Auditable{

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String body;

    // link
    @ManyToOne
    private Link link;
}
