package com.feeder.feedersurvey.repository;

import com.feeder.feedersurvey.entity.Substation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubstationRepository extends JpaRepository<Substation, Integer> {
}
