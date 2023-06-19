package com.feeder.feedersurvey.repository;

import com.feeder.feedersurvey.dto.FeederDetailDto;
import com.feeder.feedersurvey.entity.FeederDetail;

import java.util.List;

public interface FeederDetailsCustomRepository {
    List<FeederDetail> findFeederDetails(FeederDetailDto feederDetailDto);
}
