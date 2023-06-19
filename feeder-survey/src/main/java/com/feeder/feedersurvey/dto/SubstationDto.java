package com.feeder.feedersurvey.dto;

import com.feeder.feedersurvey.dto.base.BaseDto;
import com.feeder.feedersurvey.entity.Substation;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SubstationDto extends BaseDto {
    private String name;
    private Set<FeederDto> feederList;

    public Substation toEntity(){
        return Substation.builder().id(this.getId()).build();
    }
}
