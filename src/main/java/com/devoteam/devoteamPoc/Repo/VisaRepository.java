package com.devoteam.devoteamPoc.Repo;

import com.devoteam.devoteamPoc.Entity.StaffDetails;
import com.devoteam.devoteamPoc.Entity.Visa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisaRepository extends JpaRepository<Visa,Long> {
    List<Visa> findByUserId(String userId);
}
