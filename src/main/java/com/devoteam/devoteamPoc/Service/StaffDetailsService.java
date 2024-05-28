package com.devoteam.devoteamPoc.Service;

import com.devoteam.devoteamPoc.Dto.StaffDetailsDTO;

import java.util.List;

public interface StaffDetailsService {
    String createStaffDetails(StaffDetailsDTO staffDetailsDTO, String UserId);
    String updateStaffDetails(Long id, StaffDetailsDTO staffDetailsDTO);


    /*
    void deleteStaffDetails(Long id);
    List<StaffDetailsDTO> getAllStaffDetails();

    StaffDetailsDTO getStaffDetailsById(Long id);

     */
    List<StaffDetailsDTO> getStaffDetailsByUserId(String userId);


}
