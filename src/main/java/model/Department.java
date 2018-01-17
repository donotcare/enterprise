package model;

import lombok.*;

import javax.persistence.Entity;
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Department extends BaseEntity{
    @NonNull
    private String name;
}
