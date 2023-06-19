package com.feeder.feedersurvey.dto;

import com.feeder.feedersurvey.dto.base.BaseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class FeederDto extends BaseDto {
    private String name;
}
