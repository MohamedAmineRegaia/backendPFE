package com.devoteam.devoteamPoc.EmployeeController;

import com.devoteam.devoteamPoc.Dto.StaffDetailsDTO;
import com.devoteam.devoteamPoc.Service.StaffDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/staff-details")
public class StaffDetailsController {
    @Autowired
    private StaffDetailsService staffDetailsService;

    @PostMapping
    public String createStaffDetails(@RequestBody StaffDetailsDTO staffDetailsDTO , Principal principal) {
        return staffDetailsService.createStaffDetails(staffDetailsDTO, principal.getName());
    }
    @GetMapping("/details")
    public ResponseEntity<List<StaffDetailsDTO>> getStaffDetailsByUserId (Principal principal) {
        List<StaffDetailsDTO> staffDetailsDTOList = staffDetailsService.getStaffDetailsByUserId(principal.getName());
        return ResponseEntity.ok().body(staffDetailsDTOList);
    }
    @GetMapping("/details/manager")
    public ResponseEntity<List<StaffDetailsDTO>> getStaffDetailsByUserIdforManager (@RequestParam String UserId) {
        List<StaffDetailsDTO> staffDetailsDTOList = staffDetailsService.getStaffDetailsByUserId(UserId);
        return ResponseEntity.ok().body(staffDetailsDTOList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStaffDetails(@PathVariable Long id, @RequestBody StaffDetailsDTO staffDetailsDTO) {
        try {
            String result = staffDetailsService.updateStaffDetails(id, staffDetailsDTO);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
