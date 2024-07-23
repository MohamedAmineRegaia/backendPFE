package com.devoteam.devoteamPoc.Repo;

import com.devoteam.devoteamPoc.Entity.Propale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface propale2Repo  extends JpaRepository<Propale,Long> {
}
