package com.feeder.feedersurvey.repository;

import com.feeder.feedersurvey.entity.Feeder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeederRepository extends JpaRepository<Feeder, Integer> {
}
