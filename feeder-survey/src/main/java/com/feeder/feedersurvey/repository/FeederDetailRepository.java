package com.feeder.feedersurvey.repository;

import com.feeder.feedersurvey.entity.FeederDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeederDetailRepository extends JpaRepository<FeederDetail, Integer>, FeederDetailsCustomRepository {
    List<FeederDetail> findByFeederId(Integer feederId);
}
