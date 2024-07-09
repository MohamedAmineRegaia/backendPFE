package com.devoteam.devoteamPoc.Repo;

import com.devoteam.devoteamPoc.Entity.Propale;
import com.devoteam.devoteamPoc.Entity.StaffCertification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface PropaleRepository extends JpaRepository<Propale,Long> {
    List<Propale> findByUserId(String userId);

}
