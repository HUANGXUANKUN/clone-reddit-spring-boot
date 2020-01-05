package freddit.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Data // includes getter/setter and withArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String body;
}
