package com.feeder.feedersurvey.repository;

import com.feeder.feedersurvey.entity.Role;
import com.feeder.feedersurvey.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
