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
@XmlRootElement(name = "department")
public class Department extends BaseEntity{
    @NonNull
    private String name;
}
