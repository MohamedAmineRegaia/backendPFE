package com.devoteam.devoteamPoc.EmployeeController;

import com.devoteam.devoteamPoc.Dto.StaffCertificationDTO;
import com.devoteam.devoteamPoc.Dto.VisaDTO;
import com.devoteam.devoteamPoc.Service.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/visa")
public class visaController {

    @Autowired
    private VisaService visaService;


    @PostMapping("/ajouter")
    public String ajouterVisa(@RequestBody VisaDTO visaDTO , Principal principal ) {
            return visaService.addVisa(visaDTO, principal.getName());
    }
    @GetMapping("/affichage")
    public ResponseEntity<List<VisaDTO>> getVisaByUserId (Principal principal) {
        List<VisaDTO> visaDTOList = visaService.getVisaByUserId(principal.getName());
        return ResponseEntity.ok().body(visaDTOList);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateVisa(@PathVariable Long id, @RequestBody VisaDTO visaDTO) {
        try {
            String result = visaService.updateVida(id, visaDTO);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
