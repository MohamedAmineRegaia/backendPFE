package com.devoteam.devoteamPoc.Service.impl;

import com.devoteam.devoteamPoc.Dto.StaffDetailsDTO;
import com.devoteam.devoteamPoc.Entity.StaffDetails;
import com.devoteam.devoteamPoc.Repo.StaffDetailsRepository;
import com.devoteam.devoteamPoc.Service.StaffDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StaffDetailsServiceImpl implements StaffDetailsService {

    @Autowired
    private StaffDetailsRepository staffDetailsRepository;

    @Override
    public String createStaffDetails(StaffDetailsDTO staffDetailsDTO,String UserId) {
        StaffDetails staffDetails = new StaffDetails();
        staffDetails.setUserId(UserId);
        staffDetails.setCertif(staffDetailsDTO.getCertifications());
        staffDetails.setVisa(staffDetailsDTO.getVisa());
        staffDetails.setProjectTitle(staffDetailsDTO.getProjectTitle());
        staffDetails.setProjectStartDate(staffDetailsDTO.getProjectStartDate());
        staffDetails.setProjectEndDate(staffDetailsDTO.getProjectEndDate());
        staffDetails.setDisponibilte(staffDetailsDTO.getDisponibilte());


       staffDetailsRepository.save(staffDetails);
        return "details added";
    }

    @Override
    public List<StaffDetailsDTO> getStaffDetailsByUserId(String userId) {
        List<StaffDetails> staffDetailsList = staffDetailsRepository.findByUserId(userId);
        return staffDetailsList.stream()
                .map(staffDetails -> {
                    StaffDetailsDTO staffDetailsDTO = new StaffDetailsDTO();
                    staffDetailsDTO.setId(staffDetails.getId());
                    staffDetailsDTO.setCertifications(staffDetails.getCertif());
                    staffDetailsDTO.setVisa(staffDetails.getVisa());
                    staffDetailsDTO.setProjectTitle(staffDetails.getProjectTitle());
                    staffDetailsDTO.setProjectStartDate(staffDetails.getProjectStartDate());
                    staffDetailsDTO.setProjectEndDate(staffDetails.getProjectEndDate());
                    staffDetailsDTO.setDisponibilte(staffDetailsDTO.getDisponibilte());

                    return staffDetailsDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public String updateStaffDetails(Long id, StaffDetailsDTO staffDetailsDTO) {
        // Récupérer l'objet StaffDetails correspondant à l'ID depuis le repository
        Optional<StaffDetails> staffDetailsOptional = staffDetailsRepository.findById(id);

        // Vérifier si l'objet existe dans la base de données
        if (staffDetailsOptional.isPresent()) {
            StaffDetails staffDetails = staffDetailsOptional.get();

            // Mettre à jour les champs de l'objet StaffDetails avec les nouvelles valeurs
            staffDetails.setCertif(staffDetailsDTO.getCertifications());
            staffDetails.setVisa(staffDetailsDTO.getVisa());
            staffDetails.setProjectTitle(staffDetailsDTO.getProjectTitle());
            staffDetails.setProjectStartDate(staffDetailsDTO.getProjectStartDate());
            staffDetails.setProjectEndDate(staffDetailsDTO.getProjectEndDate());
            staffDetails.setDisponibilte(staffDetailsDTO.getDisponibilte());


            // Sauvegarder les modifications dans le repository
            staffDetailsRepository.save(staffDetails);

            return" updated successfully.";
        } else {
            // Gérer le cas où l'objet n'est pas trouvé dans la base de données
            throw new NoSuchElementException("StaffDetails not found with id: " + id);
        }
    }

/*


    @Override
    public void deleteStaffDetails(Long id) {
        // Supprimer l'objet StaffDetails correspondant à l'ID de la base de données
    }

    @Override
    public List<StaffDetailsDTO> getAllStaffDetails() {
        // Récupérer tous les objets StaffDetails de la base de données et les convertir en StaffDetailsDTO
    }

    @Override
    public StaffDetailsDTO getStaffDetailsById(Long id) {
        // Récupérer l'objet StaffDetails correspondant à l'ID de la base de données et le convertir en StaffDetailsDTO
    }

 */
}
