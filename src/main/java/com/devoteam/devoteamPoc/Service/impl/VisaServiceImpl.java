package com.devoteam.devoteamPoc.Service.impl;

import com.devoteam.devoteamPoc.Dto.StaffCertificationDTO;
import com.devoteam.devoteamPoc.Dto.VisaDTO;
import com.devoteam.devoteamPoc.Entity.StaffCertification;
import com.devoteam.devoteamPoc.Entity.StaffDetails;
import com.devoteam.devoteamPoc.Entity.Visa;
import com.devoteam.devoteamPoc.Repo.VisaRepository;
import com.devoteam.devoteamPoc.Service.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VisaServiceImpl implements VisaService {


    @Autowired
    private VisaRepository visaRepository;
    @Override
    public String addVisa(VisaDTO visaDTO, String UserId) {
        Visa visa = new Visa();
        visa.setUserId(UserId);
        visa.setVisa(visaDTO.getVisa());
        visaRepository.save(visa);
        return "Visa added";
    }

    @Override
    public List<VisaDTO> getVisaByUserId(String userId) {

        List<Visa> visaList = visaRepository.findByUserId(userId);
        return visaList.stream()
                .map(visa -> {
                    VisaDTO visaDTO = new VisaDTO();
                    visaDTO.setId(visa.getId());
                    visaDTO.setUserId(visa.getUserId());
                    visaDTO.setVisa(visa.getVisa());



                    return visaDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public String updateVida(Long id, VisaDTO visaDTO) {

        Optional<Visa> visaOptional = visaRepository.findById(id);

        // Vérifier si l'objet existe dans la base de données
        if (visaOptional.isPresent()) {
            Visa visa = visaOptional.get();

            visa.setVisa(visaDTO.getVisa());

            visaRepository.save(visa);

            return" updated successfully.";
        } else {
            // Gérer le cas où l'objet n'est pas trouvé dans la base de données
            throw new NoSuchElementException("Visa not found with id: " + id);
        }

    }

    @Override
    public String deleteVisa(Long id) {
        Optional<Visa> visaOptional = visaRepository.findById(id);
        if (visaOptional.isPresent()) {
            visaRepository.deleteById(id);
            return "Visa deleted successfully.";
        } else {
            throw new NoSuchElementException("Visa not found with id: " + id);
        }
    }
}
