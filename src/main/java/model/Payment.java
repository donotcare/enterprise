package model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "payment")
public class Payment extends BaseEntity {
    @NonNull
    @OneToOne
    private Position position;
    @NonNull
    private BigDecimal amount;
}
