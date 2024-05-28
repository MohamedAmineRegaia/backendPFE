package com.devoteam.devoteamPoc.Service.impl;

import com.devoteam.devoteamPoc.Dto.StaffCertificationDTO;
import com.devoteam.devoteamPoc.Dto.StaffProjetDTO;
import com.devoteam.devoteamPoc.Entity.StaffCertification;
import com.devoteam.devoteamPoc.Entity.StaffDetails;
import com.devoteam.devoteamPoc.Entity.StaffProjet;
import com.devoteam.devoteamPoc.Repo.StaffCertificationRepository;
import com.devoteam.devoteamPoc.Repo.StaffProjectRepository;
import com.devoteam.devoteamPoc.Service.StaffCertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StaffCertificationServiceImpl implements StaffCertificationService {


    @Autowired
    private StaffCertificationRepository staffCertificationRepository;
    @Override
    public String addCertification(StaffCertificationDTO staffCertificationDTO , String userId) {

        StaffCertification staffCertification = new StaffCertification();
        staffCertification.setUserId(userId);
        staffCertification.setCertification(staffCertificationDTO.getCertification());

        staffCertificationRepository.save(staffCertification);
        return "Certification added";
    }

    @Override
    public List<StaffCertificationDTO> getStaffCertificationByUserId(String userId) {

        List<StaffCertification> staffCertificationList = staffCertificationRepository.findByUserId(userId);
        return staffCertificationList.stream()
                .map(staffCertification -> {
                    StaffCertificationDTO staffCertificationDTO = new StaffCertificationDTO();
                    staffCertificationDTO.setId(staffCertification.getId());
                    staffCertificationDTO.setUserId(staffCertification.getUserId());
                    staffCertificationDTO.setCertification(staffCertification.getCertification());



                    return staffCertificationDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public String updateCertification(Long id, StaffCertificationDTO staffCertificationDTO) {
        Optional<StaffCertification> staffCertificationOptional = staffCertificationRepository.findById(id);

        // Vérifier si l'objet existe dans la base de données
        if (staffCertificationOptional.isPresent()) {
            StaffCertification staffCertification = staffCertificationOptional.get();

            staffCertification.setCertification(staffCertificationDTO.getCertification());

            staffCertificationRepository.save(staffCertification);

            return" updated successfully.";
        } else {
            // Gérer le cas où l'objet n'est pas trouvé dans la base de données
            throw new NoSuchElementException("StaffCertification not found with id: " + id);
        }
    }

}



