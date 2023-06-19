package com.feeder.feedersurvey.dto;

import com.feeder.feedersurvey.dto.base.BaseDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginDto extends BaseDto {
    private String userName;
    private String pwd;
}
