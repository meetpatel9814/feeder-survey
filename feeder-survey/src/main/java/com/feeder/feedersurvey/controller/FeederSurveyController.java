package com.feeder.feedersurvey.controller;

import com.feeder.feedersurvey.dto.FeederDetailDto;
import com.feeder.feedersurvey.dto.FeederDto;
import com.feeder.feedersurvey.dto.LoginDto;
import com.feeder.feedersurvey.dto.SubstationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.feeder.feedersurvey.dto.base.ApiResponseDto;
import com.feeder.feedersurvey.service.FeederSurveyService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FeederSurveyController {

	@Autowired
	private FeederSurveyService feederSurveyService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping
	@RequestMapping("/auth")
	public ApiResponseDto login(@RequestBody LoginDto loginDto) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginDto.getUserName(), loginDto.getPwd()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ApiResponseDto(HttpStatus.OK.value(), null, "Login successfully.");
	}
	
	@RequestMapping("/feeder-detail/save")
	@PostMapping
	public ApiResponseDto saveFeederDetails(@RequestBody FeederDetailDto feederDetailDto) {
		 feederSurveyService.saveFeederDetail(feederDetailDto);
		return new ApiResponseDto(HttpStatus.OK.value(), null, "Feeder Details saved successfully.");
	}

	@RequestMapping("/feeders")
	@GetMapping
	public ApiResponseDto<FeederDto> getFeeders(@RequestBody FeederDetailDto feederDetailDto) {
		List<FeederDto> feederDtoList = feederSurveyService.fetchFeeders();
		return new ApiResponseDto(HttpStatus.OK.value(), feederDtoList, "Feeder Details saved successfully.");
	}

	@RequestMapping("/substations")
	@GetMapping
	public ApiResponseDto<List<SubstationDto>> getSubstations() {
		List<SubstationDto> substationDtoList = feederSurveyService.getSubstations();
		return new ApiResponseDto(HttpStatus.OK.value(), substationDtoList, "Substation Details saved successfully.");
	}


	@RequestMapping("/feeder-detail/search")
	@PostMapping
	public ApiResponseDto<List<FeederDetailDto>> fetchFeederDetail(@RequestBody FeederDetailDto feederDetailDto) {
		List<FeederDetailDto> feederDetailDtoList = feederSurveyService.fetchFeederDetail(feederDetailDto);
		return new ApiResponseDto(HttpStatus.OK.value(), feederDetailDtoList, "Substation Details saved successfully.");
	}
}
