package com.feeder.feedersurvey.dto.base;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto<T> {
	private Integer status;
	private T content;
	private String message;
}
