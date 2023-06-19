package com.feeder.feedersurvey.entity;

import com.feeder.feedersurvey.dto.FeederDto;
import com.feeder.feedersurvey.entity.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Table(name = "feeder")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Feeder extends BaseEntity {
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinTable(name = "feeder_substation_mapping", joinColumns = {@JoinColumn(name = "feeder_id")},
            inverseJoinColumns = {@JoinColumn(name = "substation_id")})
    private Set<Feeder> feeders;

    public FeederDto toDto(){
        return FeederDto.builder().id(this.getId()).name(this.getName()).build();
    }
}
