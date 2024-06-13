package com.devoteam.devoteamPoc.Service.impl;

import com.devoteam.devoteamPoc.Dto.PropaleDTO;
import com.devoteam.devoteamPoc.Entity.Propale;
import com.devoteam.devoteamPoc.Repo.PropaleRepository;
import com.devoteam.devoteamPoc.Service.PropaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropaleServiceImpl implements PropaleService {

    @Autowired
    private PropaleRepository propaleRepository;

    @Override
    public String addPropale(PropaleDTO propaleDTO, String userId) {
        Propale propale = new Propale();
        propale.setUserId(userId);
        propale.setCompany_org(propaleDTO.getCompany_org());
        propale.setPropaleName(propaleDTO.getPropaleName());
        propale.setAccount_Type(propaleDTO.getAccount_Type());
        propale.setStatus(propaleDTO.getStatus());
        propale.setSubmissionDate(propaleDTO.getSubmissionDate());
        propale.setBid_manager(propaleDTO.getBid_manager());
        propale.setCRP_CRD(propaleDTO.getCRP_CRD());
        propale.setTypeOfRequest(propaleDTO.getTypeOfRequest());
        propale.setPractice(propaleDTO.getPractice());
        propale.setPrimaryVp(propaleDTO.getPrimaryVp());
        propale.setPrincipalVendor(propaleDTO.getPrincipalVendor());
        propale.setSecondaryVendor(propaleDTO.getSecondaryVendor());
        propale.setBudget_no_vat(propaleDTO.getBudget_no_vat());
        propale.setEstimated_value(propaleDTO.getEstimated_value());
        propale.setLgAmount(propaleDTO.getLgAmount());
        propale.setQA_deadline(propaleDTO.getQA_deadline());
        propale.setNoGoReason(propaleDTO.getNoGoReason());

        propaleRepository.save(propale);
        return "Propale added";
    }

    @Override
    public List<PropaleDTO> getPropalesByUserId(String userId) {
        List<Propale> propales = propaleRepository.findByUserId(userId);
        return propales.stream()
                .map(propale -> {
                    PropaleDTO propaleDTO = new PropaleDTO();
                    propaleDTO.setId(propale.getId());
                    propaleDTO.setUserId(propale.getUserId());
                    propaleDTO.setCompany_org(propale.getCompany_org());
                    propaleDTO.setPropaleName(propale.getPropaleName());
                    propaleDTO.setAccount_Type(propale.getAccount_Type());
                    propaleDTO.setStatus(propale.getStatus());
                    propaleDTO.setSubmissionDate(propale.getSubmissionDate());
                    propaleDTO.setBid_manager(propale.getBid_manager());
                    propaleDTO.setCRP_CRD(propale.getCRP_CRD());
                    propaleDTO.setTypeOfRequest(propale.getTypeOfRequest());
                    propaleDTO.setPractice(propale.getPractice());
                    propaleDTO.setPrimaryVp(propale.getPrimaryVp());
                    propaleDTO.setPrincipalVendor(propale.getPrincipalVendor());
                    propaleDTO.setSecondaryVendor(propale.getSecondaryVendor());
                    propaleDTO.setBudget_no_vat(propale.getBudget_no_vat());
                    propaleDTO.setEstimated_value(propale.getEstimated_value());
                    propaleDTO.setLgAmount(propale.getLgAmount());
                    propaleDTO.setQA_deadline(propale.getQA_deadline());
                    propaleDTO.setNoGoReason(propale.getNoGoReason());
                    return propaleDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<PropaleDTO> getAllPropales() {
        List<Propale> propales = propaleRepository.findAll();
        return propales.stream()
                .map(propale -> {
                    PropaleDTO propaleDTO = new PropaleDTO();
                    propaleDTO.setId(propale.getId());
                    propaleDTO.setUserId(propale.getUserId());
                    propaleDTO.setCompany_org(propale.getCompany_org());
                    propaleDTO.setPropaleName(propale.getPropaleName());
                    propaleDTO.setAccount_Type(propale.getAccount_Type());
                    propaleDTO.setStatus(propale.getStatus());
                    propaleDTO.setSubmissionDate(propale.getSubmissionDate());
                    propaleDTO.setBid_manager(propale.getBid_manager());
                    propaleDTO.setCRP_CRD(propale.getCRP_CRD());
                    propaleDTO.setTypeOfRequest(propale.getTypeOfRequest());
                    propaleDTO.setPractice(propale.getPractice());
                    propaleDTO.setPrimaryVp(propale.getPrimaryVp());
                    propaleDTO.setPrincipalVendor(propale.getPrincipalVendor());
                    propaleDTO.setSecondaryVendor(propale.getSecondaryVendor());
                    propaleDTO.setBudget_no_vat(propale.getBudget_no_vat());
                    propaleDTO.setEstimated_value(propale.getEstimated_value());
                    propaleDTO.setLgAmount(propale.getLgAmount());
                    propaleDTO.setQA_deadline(propale.getQA_deadline());
                    propaleDTO.setNoGoReason(propale.getNoGoReason());
                    return propaleDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public String updatePropale(Long id, PropaleDTO propaleDTO) {
        Optional<Propale> propaleOptional = propaleRepository.findById(id);
        if (propaleOptional.isPresent()) {
            Propale propale = propaleOptional.get();
            propale.setCompany_org(propaleDTO.getCompany_org());
            propale.setPropaleName(propaleDTO.getPropaleName());
            propale.setAccount_Type(propaleDTO.getAccount_Type());
            propale.setStatus(propaleDTO.getStatus());
            propale.setSubmissionDate(propaleDTO.getSubmissionDate());
            propale.setBid_manager(propaleDTO.getBid_manager());
            propale.setCRP_CRD(propaleDTO.getCRP_CRD());
            propale.setTypeOfRequest(propaleDTO.getTypeOfRequest());
            propale.setPractice(propaleDTO.getPractice());
            propale.setPrimaryVp(propaleDTO.getPrimaryVp());
            propale.setPrincipalVendor(propaleDTO.getPrincipalVendor());
            propale.setSecondaryVendor(propaleDTO.getSecondaryVendor());
            propale.setBudget_no_vat(propaleDTO.getBudget_no_vat());
            propale.setEstimated_value(propaleDTO.getEstimated_value());
            propale.setLgAmount(propaleDTO.getLgAmount());
            propale.setQA_deadline(propaleDTO.getQA_deadline());
            propale.setNoGoReason(propaleDTO.getNoGoReason());

            propaleRepository.save(propale);
            return "Propale updated successfully.";
        } else {
            throw new NoSuchElementException("Propale not found with id: " + id);
        }
    }

    @Override
    public String deletePropale(Long id) {
        Optional<Propale> propaleOptional = propaleRepository.findById(id);
        if (propaleOptional.isPresent()) {
            propaleRepository.deleteById(id);
            return "Propale deleted successfully.";
        } else {
            throw new NoSuchElementException("Propale not found with id: " + id);
        }
    }
}
