package model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;


@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString(callSuper = true)
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
