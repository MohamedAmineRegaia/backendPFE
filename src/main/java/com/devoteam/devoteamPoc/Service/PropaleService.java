package com.devoteam.devoteamPoc.Service;

import com.devoteam.devoteamPoc.Dto.PropaleDTO;
import com.devoteam.devoteamPoc.Dto.VisaDTO;
import com.devoteam.devoteamPoc.Entity.HistoryEntry;

import java.util.List;

public interface PropaleService {
    String addPropale(PropaleDTO propaleDTO, String UserId);
    List<PropaleDTO> getPropalesByUserId(String userId);
    List<PropaleDTO> getAllPropales();
    String updatePropale(Long id, PropaleDTO propaleDTO,String userId);
    String deletePropale(Long id);

     List<HistoryEntry> getAllHistorique() ;
    PropaleDTO getPropaleById(Long id);
    String assignUserToProject(Long propaleId, String userId);
    String unassignUserFromProject(Long propaleId, String userId);
}
