package freddit.model;

import freddit.service.BeanUtil;
import lombok.*;
import org.ocpsoft.prettytime.PrettyTime;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
@NoArgsConstructor
//@Data // includes getter/setter and withArgsConstructor
@RequiredArgsConstructor
@Getter @Setter
public class Comment extends Auditable{

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String body;

    // link
    @ManyToOne
    @NonNull
    private Link link;

    @OneToOne
    private User user;

    public String getPrettyTime() {
        PrettyTime pt = BeanUtil.getBean(PrettyTime.class);
        return pt.format(convertToDateViaInstant(getCreationDate()));
    }

    private Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
    }
}
