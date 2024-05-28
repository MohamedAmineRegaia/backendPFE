package com.devoteam.devoteamPoc.Repo;


import com.devoteam.devoteamPoc.Entity.StaffDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffDetailsRepository extends JpaRepository<StaffDetails, Long> {
    List<StaffDetails> findByUserId(String userId);

}

