package com.devoteam.devoteamPoc.Service;

import com.devoteam.devoteamPoc.Dto.StaffCertificationDTO;


import java.util.List;

public interface StaffCertificationService {
    String addCertification(StaffCertificationDTO staffCertificationDTO,String userId);
    List<StaffCertificationDTO> getStaffCertificationByUserId(String userId);
    String updateCertification(Long id, StaffCertificationDTO staffCertificationDTO);
    String deleteCertification(Long id);

}
