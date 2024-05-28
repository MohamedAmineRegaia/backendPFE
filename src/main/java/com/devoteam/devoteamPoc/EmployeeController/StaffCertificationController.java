package com.devoteam.devoteamPoc.EmployeeController;


import com.devoteam.devoteamPoc.Dto.StaffCertificationDTO;
import com.devoteam.devoteamPoc.Dto.StaffDetailsDTO;
import com.devoteam.devoteamPoc.Dto.StaffProjetDTO;
import com.devoteam.devoteamPoc.Service.StaffCertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/Staff-Certification")
public class StaffCertificationController {

    @Autowired
    private StaffCertificationService staffCertificationService;

    @PostMapping("/ajouter")
    public String ajouterCertification(@RequestBody StaffCertificationDTO staffCertificationDTO , Principal principal ) {
        return staffCertificationService.addCertification(staffCertificationDTO, principal.getName());
    }
    @GetMapping("/affichage")
    public ResponseEntity<List<StaffCertificationDTO>> getStaffCertificationByUserId (Principal principal) {
        List<StaffCertificationDTO> staffCertificationDTOList = staffCertificationService.getStaffCertificationByUserId(principal.getName());
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
}
