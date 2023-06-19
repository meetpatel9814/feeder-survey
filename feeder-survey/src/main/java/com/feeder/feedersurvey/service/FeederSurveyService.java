package com.feeder.feedersurvey.service;

import com.feeder.feedersurvey.dto.FeederDetailDto;
import com.feeder.feedersurvey.dto.FeederDto;
import com.feeder.feedersurvey.dto.SubstationDto;
import com.feeder.feedersurvey.entity.Feeder;
import com.feeder.feedersurvey.entity.FeederDetail;
import com.feeder.feedersurvey.entity.Substation;
import com.feeder.feedersurvey.repository.FeederDetailRepository;
import com.feeder.feedersurvey.repository.FeederRepository;
import com.feeder.feedersurvey.repository.SubstationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FeederSurveyService {

    @Autowired
    private FeederDetailRepository feederDetailRepository;
    @Autowired
    private FeederRepository feederRepository;
    @Autowired
    private SubstationRepository substationRepository;

    public void saveFeederDetail(FeederDetailDto feederDetailDto) {
        validateFeederDetail(feederDetailDto);
        FeederDetail feederDetail = feederDetailDto.toEntity();
        feederDetail.setFeeder(feederRepository.findById(feederDetail.getFeederId()).orElseThrow(() -> new RuntimeException("Unable Feeder master data for " + feederDetail.getFeederId())));
        feederDetail.setSubstation(substationRepository.findById(feederDetail.getSubstationId()).orElseThrow(() -> new RuntimeException("Unable Substation master data for " +feederDetail.getSubstationId())));
        feederDetailRepository.save(feederDetail);
    }

    private void validateFeederDetail(FeederDetailDto feederDetailDto) {

    }

    public List<SubstationDto> getSubstations() {
        List<Substation> substations = substationRepository.findAll();
        return substations.stream().map(Substation::toDto).collect(Collectors.toList());
    }

    public List<FeederDetailDto> fetchFeederDetail(FeederDetailDto feederDetailDto) {
        List<FeederDetail> feederDetailList = feederDetailRepository.findFeederDetails(feederDetailDto);
        return feederDetailList.stream().map(FeederDetail::toDto).collect(Collectors.toList());
    }

    public List<FeederDto> fetchFeeders() {
        List<Feeder> feederList = feederRepository.findAll();
        return feederList.stream().map(Feeder::toDto).collect(Collectors.toList());
    }
}
