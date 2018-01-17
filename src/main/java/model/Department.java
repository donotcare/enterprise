package model;

import lombok.*;

import javax.persistence.Entity;
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Department extends BaseEntity{
    @NonNull
    private String name;
}
