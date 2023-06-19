package com.feeder.feedersurvey.entity;

import com.feeder.feedersurvey.dto.SubstationDto;
import com.feeder.feedersurvey.entity.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@ToString
@Table(name = "substation")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Substation extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinTable(name = "feeder_substation_mapping", joinColumns = {@JoinColumn(name = "substation_id")},
            inverseJoinColumns = {@JoinColumn(name = "feeder_id")})
    private Set<Feeder> feeders;

    public SubstationDto toDto() {
        return SubstationDto.builder().id(this.getId()).name(this.getName())
                .feederList(this.feeders.stream().map(Feeder::toDto).collect(Collectors.toSet())).build();
    }
}
