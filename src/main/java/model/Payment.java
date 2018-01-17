package model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Payment  extends BaseEntity {
    @NonNull
    @OneToOne
    private Position position;
    @NonNull
    private BigDecimal amount;
}
