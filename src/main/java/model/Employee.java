package model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString(callSuper = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "employee")
public class Employee extends BaseEntity {
    @NonNull
    private String name;
    @NonNull
    @OneToOne
    private Department department;
    @NonNull
    private String city;
    @NonNull
    @OneToOne
    private Position position;
    @NonNull
    @OneToOne
    private Payment payment;
    @NonNull
    private String login;
    @NonNull
    private String password;


}
