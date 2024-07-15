package com.devoteam.devoteamPoc.Service.impl;

import com.devoteam.devoteamPoc.Dto.StaffDetailsDTO;
import com.devoteam.devoteamPoc.Dto.StaffProjetDTO;
import com.devoteam.devoteamPoc.Entity.StaffDetails;
import com.devoteam.devoteamPoc.Entity.StaffProjet;
import com.devoteam.devoteamPoc.Repo.StaffProjectRepository;
import com.devoteam.devoteamPoc.Service.StaffProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StaffProjetServiceImpl implements StaffProjetService {
    @Autowired
    private StaffProjectRepository staffProjectRepository;


    @Override
    public String AffecterProjet(StaffProjetDTO staffProjetDTO) {

        StaffProjet staffProjet = new StaffProjet();
        staffProjet.setUserId(staffProjetDTO.getUserId());
        staffProjet.setProjectTitle(staffProjetDTO.getProjectTitle());
        staffProjet.setProjectStartDate(staffProjetDTO.getProjectStartDate());
        staffProjet.setProjectEndDate(staffProjetDTO.getProjectEndDate());


        staffProjectRepository.save(staffProjet);
        return "details added";
    }

    @Override
    public List<StaffProjetDTO> getStaffProjectByUserId(String userId) {
        List<StaffProjet> staffProjetList = staffProjectRepository.findByUserId(userId);
        return staffProjetList.stream()
                .map(staffProjet -> {
                    StaffProjetDTO staffProjetDTO = new StaffProjetDTO();
                    staffProjetDTO.setId(staffProjet.getId());
                    staffProjetDTO.setUserId(staffProjet.getUserId());
                    staffProjetDTO.setProjectTitle(staffProjet.getProjectTitle());
                    staffProjetDTO.setProjectStartDate(staffProjet.getProjectStartDate());
                    staffProjetDTO.setProjectEndDate(staffProjet.getProjectEndDate());


                    return staffProjetDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public String updateStaffprojet(Long id, StaffProjetDTO staffProjetDTO) {
        Optional<StaffProjet> staffProjetOptional = staffProjectRepository.findById(id);

        // Vérifier si l'objet existe dans la base de données
        if (staffProjetOptional.isPresent()) {
            StaffProjet staffProjet = staffProjetOptional.get();

            // Mettre à jour les champs de l'objet StaffDetails avec les nouvelles valeurs
            staffProjet.setProjectTitle(staffProjetDTO.getProjectTitle());
            staffProjet.setProjectStartDate(staffProjetDTO.getProjectStartDate());
            staffProjet.setProjectEndDate(staffProjetDTO.getProjectEndDate());


            // Sauvegarder les modifications dans le repository
            staffProjectRepository.save(staffProjet);

            return" updated successfully.";
        } else {
            // Gérer le cas où l'objet n'est pas trouvé dans la base de données
            throw new NoSuchElementException("StaffDetails not found with id: " + id);
        }
    }

    @Override
    public String deleteStaffProjectByUserIdAndProjectTitle(String userId, String projectTitle) {
        List<StaffProjet> staffProjetList = staffProjectRepository.findByUserId(userId);

        // Filtrer les projets qui correspondent au projectTitle
        List<StaffProjet> projectsToDelete = staffProjetList.stream()
                .filter(project -> project.getProjectTitle().equals(projectTitle))
                .collect(Collectors.toList());
        System.out.println(projectTitle);
        System.out.println(projectsToDelete);




        if (!projectsToDelete.isEmpty()) {
            staffProjectRepository.deleteAll(projectsToDelete);
            return "Projects deleted successfully.";
        } else {
            throw new NoSuchElementException("No projects found for userId: " + userId + " with projectTitle: " + projectTitle);
        }
    }

}
