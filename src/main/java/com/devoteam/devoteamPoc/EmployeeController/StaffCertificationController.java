package com.devoteam.devoteamPoc.EmployeeController;


import com.devoteam.devoteamPoc.Dto.StaffCertificationDTO;
import com.devoteam.devoteamPoc.Dto.StaffDetailsDTO;
import com.devoteam.devoteamPoc.Dto.StaffProjetDTO;
import com.devoteam.devoteamPoc.Service.StaffCertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/Staff-Certification")
public class StaffCertificationController {

    @Autowired
    private StaffCertificationService staffCertificationService;

    @PostMapping("/ajouter")
    public ResponseEntity<String> ajouterCertification(
            @RequestParam("certification") String certification,
            @RequestParam(value = "image", required = false) MultipartFile image,
            Principal principal) {
        StaffCertificationDTO staffCertificationDTO = new StaffCertificationDTO();
        staffCertificationDTO.setCertification(certification);
        if (image != null && !image.isEmpty()) {
            try {
                staffCertificationDTO.setImage(image.getBytes());
            } catch (IOException e) {
                return new ResponseEntity<>("Failed to process image", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        String result = staffCertificationService.addCertification(staffCertificationDTO, principal.getName());
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    @GetMapping("/affichage")
    public ResponseEntity<List<StaffCertificationDTO>> getStaffCertificationByUserId (Principal principal) {
        List<StaffCertificationDTO> staffCertificationDTOList = staffCertificationService.getStaffCertificationByUserId(principal.getName());
        return ResponseEntity.ok().body(staffCertificationDTOList);
    }
    @GetMapping("/affichage/{userId}")
    public ResponseEntity<List<StaffCertificationDTO>> getStaffCertificationByUserIdforManager (@PathVariable String userId) {
        List<StaffCertificationDTO> staffCertificationDTOList = staffCertificationService.getStaffCertificationByUserId(userId);
        return ResponseEntity.ok().body(staffCertificationDTOList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCertifiaction(@PathVariable Long id, @RequestBody StaffCertificationDTO staffCertificationDTO) {
        try {
            String result = staffCertificationService.updateCertification(id, staffCertificationDTO);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public String deleteCertification(@PathVariable Long id) {

        staffCertificationService.deleteCertification(id);
        return "certif deleted";
    }
}
