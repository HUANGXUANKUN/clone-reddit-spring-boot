package freddit.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Data // includes getter/setter and withArgsConstructor
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
