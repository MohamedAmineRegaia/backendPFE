package com.devoteam.devoteamPoc.EmployeeController;

import com.devoteam.devoteamPoc.Dto.PropaleDTO;
import com.devoteam.devoteamPoc.Entity.HistoryEntry;
import com.devoteam.devoteamPoc.Service.PropaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/propale")
public class PropaleController {

    @Autowired
    private PropaleService propaleService;
    private static final Logger logger = LoggerFactory.getLogger(PropaleController.class);


    @PostMapping("/ajouter")
    public ResponseEntity<String> ajouterPropale(@RequestBody PropaleDTO propaleDTO, Principal principal) {
        logger.info("Received request to add Propale: {}", propaleDTO);

        String result = propaleService.addPropale(propaleDTO, principal.getName());
        logger.info("Propale added with result: {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/affichage")
    public ResponseEntity<List<PropaleDTO>> getPropalesByUserId(Principal principal) {
        List<PropaleDTO> propaleDTOList = propaleService.getPropalesByUserId(principal.getName());
        return ResponseEntity.ok().body(propaleDTOList);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PropaleDTO>> getAllPropales() {
        List<PropaleDTO> propaleDTOList = propaleService.getAllPropales();
        return ResponseEntity.ok().body(propaleDTOList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePropale(@PathVariable Long id, @RequestBody PropaleDTO propaleDTO,Principal principal) {
        try {
            String result = propaleService.updatePropale(id, propaleDTO,principal.getName());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePropale(@PathVariable Long id) {
        try {
            String result = propaleService.deletePropale(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/historique")
    public ResponseEntity<List<HistoryEntry>> getAllHistorique() {
        List<HistoryEntry> historique = propaleService.getAllHistorique();
        return ResponseEntity.ok().body(historique);
    }
    @GetMapping("/{id}")
    public PropaleDTO getPropaleById(@PathVariable Long id) {
        return propaleService.getPropaleById(id);
    }

    @PostMapping("/{propaleId}/assign-user/{userId}")
    public ResponseEntity<String> assignUserToProject(@PathVariable Long propaleId, @PathVariable String userId) {
        String result = propaleService.assignUserToProject(propaleId, userId);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/{propaleId}/unassign-user/{userId}")
    public ResponseEntity<String> unassignUserFromProject(@PathVariable Long propaleId, @PathVariable String userId) {
        String result = propaleService.unassignUserFromProject(propaleId, userId);
        return ResponseEntity.ok(result);
    }


}
