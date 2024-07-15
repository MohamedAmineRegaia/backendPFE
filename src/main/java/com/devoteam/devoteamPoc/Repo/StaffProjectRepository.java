package com.devoteam.devoteamPoc.Repo;

import com.devoteam.devoteamPoc.Entity.StaffProjet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffProjectRepository extends JpaRepository<StaffProjet,Long> {
    List<StaffProjet> findByUserId(String userId);
    List<StaffProjet> findByUserIdAndProjectTitle(String userId, String projectTitle);


}
