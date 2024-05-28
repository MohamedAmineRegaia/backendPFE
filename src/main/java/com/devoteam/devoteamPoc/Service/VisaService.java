package com.devoteam.devoteamPoc.Service;

import com.devoteam.devoteamPoc.Dto.VisaDTO;

import java.util.List;

public interface VisaService {
    String addVisa(VisaDTO visaDTO, String UserId);
    List<VisaDTO> getVisaByUserId(String userId);
    String updateVida(Long id, VisaDTO visaDTO);
}
