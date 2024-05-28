package com.devoteam.devoteamPoc.Repo;

import com.devoteam.devoteamPoc.Entity.StaffCertification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StaffCertificationRepository extends JpaRepository<StaffCertification,Long> {
    List<StaffCertification> findByUserId(String userId);
}
