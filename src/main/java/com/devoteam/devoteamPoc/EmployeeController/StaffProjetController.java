package com.devoteam.devoteamPoc.EmployeeController;


import com.devoteam.devoteamPoc.Dto.StaffDetailsDTO;
import com.devoteam.devoteamPoc.Dto.StaffProjetDTO;
import com.devoteam.devoteamPoc.Service.StaffProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/Staff-Projet")
public class StaffProjetController {

    @Autowired
    private StaffProjetService staffProjetService;

    @PostMapping("/affecter")
    public String AffecterProjet(@RequestBody StaffProjetDTO staffProjetDTO  ) {
        return staffProjetService.AffecterProjet(staffProjetDTO);
    }
    @GetMapping("/affichage")
    public ResponseEntity<List<StaffProjetDTO>> getStaffProjetDetailsByUserId (Principal principal) {
        List<StaffProjetDTO> staffProjetDTOList = staffProjetService.getStaffProjectByUserId(principal.getName());
        return ResponseEntity.ok().body(staffProjetDTOList);
    }

}
