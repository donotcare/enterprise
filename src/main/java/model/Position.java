package model;

import lombok.*;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "position")
public class Position extends BaseEntity {
    @NonNull
    private String name;
}
