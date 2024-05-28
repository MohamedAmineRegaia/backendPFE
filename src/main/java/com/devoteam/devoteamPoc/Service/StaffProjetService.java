package com.devoteam.devoteamPoc.Service;

import com.devoteam.devoteamPoc.Dto.StaffCertificationDTO;
import com.devoteam.devoteamPoc.Dto.StaffDetailsDTO;
import com.devoteam.devoteamPoc.Dto.StaffProjetDTO;
import com.devoteam.devoteamPoc.Entity.StaffProjet;

import java.util.List;

public interface StaffProjetService {
        String AffecterProjet(StaffProjetDTO staffProjetDTO);
        List<StaffProjetDTO> getStaffProjectByUserId(String userId);
        String updateStaffprojet(Long id, StaffProjetDTO staffProjetDTO);


}
