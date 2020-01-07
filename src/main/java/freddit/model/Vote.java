package freddit.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
@NoArgsConstructor
//@Data // includes getter/setter and withArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Vote {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private short direction;

    @NonNull
    @ManyToOne
    private Link link;

}
