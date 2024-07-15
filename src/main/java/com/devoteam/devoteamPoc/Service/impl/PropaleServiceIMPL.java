package com.devoteam.devoteamPoc.Service.impl;

import com.devoteam.devoteamPoc.Dto.ProjectAssignmentDTO;
import com.devoteam.devoteamPoc.Dto.PropaleDTO;
import com.devoteam.devoteamPoc.EmployeeController.NotificationController;
import com.devoteam.devoteamPoc.Entity.HistoryEntry;
import com.devoteam.devoteamPoc.Entity.ProjectAssignment;
import com.devoteam.devoteamPoc.Entity.Propale;
import com.devoteam.devoteamPoc.Repo.PropaleRepository;
import com.devoteam.devoteamPoc.Service.PropaleService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PropaleServiceIMPL implements PropaleService {

    @Autowired
    private PropaleRepository propaleRepository;
    @Value("testrealm")
    private String realm;
    private Keycloak keycloak;

    private NotificationController notificationController;

    public PropaleServiceIMPL(Keycloak keycloak, NotificationController notificationController) {

        this.keycloak = keycloak;
        this.notificationController= notificationController;
    }

    public UserRepresentation getUserById(String userId) {
        UserResource userResource = getUsersResource().get(userId);
        UserRepresentation userRepresentation = userResource.toRepresentation();
        return userRepresentation;
    }
    private UsersResource getUsersResource() {
        RealmResource realm1 = keycloak.realm(realm);
        return realm1.users();
    }
    @Override
    public String addPropale(PropaleDTO propaleDTO, String userId) {
        UserRepresentation userRepresentation = getUserById(userId);
        String fullName = userRepresentation.getFirstName() + " " + userRepresentation.getLastName();

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
        propale.setNoGoDescription(propaleDTO.getNoGoDescription());
        propale.setReviewDate(propaleDTO.getReviewDate());


        HistoryEntry entry = new HistoryEntry();
        entry.setAction("ajoute");
        entry.setUser(fullName);
        entry.setTimestamp(new Date());
        entry.setPropaleName(propaleDTO.getPropaleName());

        List<HistoryEntry> history = new ArrayList<>();
        history.add(entry);
        propale.setHistoryEntries(history);


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
                    propaleDTO.setNoGoDescription(propale.getNoGoDescription());
                    propaleDTO.setReviewDate(propale.getReviewDate());
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
                    propaleDTO.setNoGoDescription(propale.getNoGoDescription());
                    propaleDTO.setReviewDate(propale.getReviewDate());



                    return propaleDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public String updatePropale(Long id, PropaleDTO propaleDTO, String userId) {
        Optional<Propale> propaleOptional = propaleRepository.findById(id);
        if (propaleOptional.isPresent()) {
            UserRepresentation userRepresentation = getUserById(userId);
            String fullName = userRepresentation.getFirstName() + " " + userRepresentation.getLastName();

            Propale propale = propaleOptional.get();
            List<HistoryEntry> history = propale.getHistoryEntries();

            // Comparer les champs et ajouter des entrées d'historique si nécessaire
            addHistoryEntryIfChanged(history, "company_org", propale.getCompany_org(), propaleDTO.getCompany_org(), fullName, propale.getPropaleName());
            addHistoryEntryIfChanged(history, "propaleName", propale.getPropaleName(), propaleDTO.getPropaleName(), fullName, propale.getPropaleName());
            addHistoryEntryIfChanged(history, "account_Type", propale.getAccount_Type(), propaleDTO.getAccount_Type(), fullName, propale.getPropaleName());
            addHistoryEntryIfChanged(history, "status", propale.getStatus(), propaleDTO.getStatus(), fullName, propale.getPropaleName());
            addHistoryEntryIfChanged(history, "submissionDate", propale.getSubmissionDate(), propaleDTO.getSubmissionDate(), fullName, propale.getPropaleName());
            addHistoryEntryIfChanged(history, "bid_manager", propale.getBid_manager(), propaleDTO.getBid_manager(), fullName, propale.getPropaleName());
            addHistoryEntryIfChanged(history, "CRP_CRD", propale.getCRP_CRD(), propaleDTO.getCRP_CRD(), fullName, propale.getPropaleName());
            addHistoryEntryIfChanged(history, "typeOfRequest", propale.getTypeOfRequest(), propaleDTO.getTypeOfRequest(), fullName, propale.getPropaleName());
            addHistoryEntryIfChanged(history, "practice", propale.getPractice(), propaleDTO.getPractice(), fullName, propale.getPropaleName());
            addHistoryEntryIfChanged(history, "primaryVp", propale.getPrimaryVp(), propaleDTO.getPrimaryVp(), fullName, propale.getPropaleName());
            addHistoryEntryIfChanged(history, "principalVendor", propale.getPrincipalVendor(), propaleDTO.getPrincipalVendor(), fullName, propale.getPropaleName());
            addHistoryEntryIfChanged(history, "secondaryVendor", propale.getSecondaryVendor(), propaleDTO.getSecondaryVendor(), fullName, propale.getPropaleName());
            addHistoryEntryIfChanged(history, "budget_no_vat", propale.getBudget_no_vat(), propaleDTO.getBudget_no_vat(), fullName, propale.getPropaleName());
            addHistoryEntryIfChanged(history, "estimated_value", propale.getEstimated_value(), propaleDTO.getEstimated_value(), fullName, propale.getPropaleName());
            addHistoryEntryIfChanged(history, "lgAmount", propale.getLgAmount(), propaleDTO.getLgAmount(), fullName, propale.getPropaleName());
            addHistoryEntryIfChanged(history, "QA_deadline", propale.getQA_deadline(), propaleDTO.getQA_deadline(), fullName, propale.getPropaleName());
            addHistoryEntryIfChanged(history, "noGoReason", propale.getNoGoReason(), propaleDTO.getNoGoReason(), fullName, propale.getPropaleName());
            addHistoryEntryIfChanged(history, "noGoDescription", propale.getNoGoDescription(), propaleDTO.getNoGoDescription(), fullName, propale.getPropaleName());
            addHistoryEntryIfChanged(history, "ReviewDate", propale.getReviewDate(), propaleDTO.getReviewDate(), fullName, propale.getPropaleName());


            // Mettre à jour les champs
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
            propale.setNoGoDescription(propaleDTO.getNoGoDescription());
            propale.setReviewDate(propaleDTO.getReviewDate());



            propale.setHistoryEntries(history);
            propaleRepository.save(propale);
            return "Propale updated successfully.";
        } else {
            throw new NoSuchElementException("Propale not found with id: " + id);
        }
    }

    private void addHistoryEntryIfChanged(List<HistoryEntry> history, String field, Object oldValue, Object newValue, String user, String propaleName) {
        if ((oldValue != null && !oldValue.equals(newValue)) || (oldValue == null && newValue != null)) {
            HistoryEntry entry = new HistoryEntry();
            entry.setAction(field + " a été modifié");
            entry.setUser(user);
            entry.setTimestamp(new Date());
            entry.setPropaleName(propaleName);
            entry.setOldValue(oldValue != null ? oldValue.toString() : "null");
            entry.setNewValue(newValue != null ? newValue.toString() : "null");
            history.add(entry);
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



    @Override
    public List<HistoryEntry> getAllHistorique() {
        List<Propale> propales = propaleRepository.findAll();
        return propales.stream()
                .flatMap(propale -> propale.getHistoryEntries().stream())
                .collect(Collectors.toList());
    }



    @Override
    public PropaleDTO getPropaleById(Long id) {
        Optional<Propale> propaleOptional = propaleRepository.findById(id);
        if (propaleOptional.isPresent()) {
            Propale propale = propaleOptional.get();
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
            propaleDTO.setNoGoDescription(propale.getNoGoDescription());
            propaleDTO.setReviewDate(propale.getReviewDate());

            List<ProjectAssignmentDTO> assignmentDTOs = propale.getProjectAssignments().stream()
                    .map(assignment -> {
                        ProjectAssignmentDTO assignmentDTO = new ProjectAssignmentDTO();
                        assignmentDTO.setId(assignment.getId());
                        assignmentDTO.setUserId(assignment.getUserId());
                        assignmentDTO.setAssignmentDate(assignment.getAssignmentDate());
                        assignmentDTO.setUsername(assignment.getUsername());
                        return assignmentDTO;
                    })
                    .collect(Collectors.toList());

            propaleDTO.setProjectAssignments(assignmentDTOs);

            return propaleDTO;
        } else {
            throw new NoSuchElementException("Propale not found with id: " + id);
        }
    }

    @Override
    public String assignUserToProject(Long propaleId, String userId) {
        Optional<Propale> propaleOptional = propaleRepository.findById(propaleId);
        UserRepresentation userRepresentation = getUserById(userId);

        if (propaleOptional.isPresent() && userRepresentation != null) {
            Propale propale = propaleOptional.get();

            ProjectAssignment assignment = new ProjectAssignment();
            assignment.setPropale(propale);
            assignment.setUserId(userId);
            assignment.setUsername(userRepresentation.getUsername());
            assignment.setAssignmentDate(new Date());

            propale.getProjectAssignments().add(assignment);
            propaleRepository.save(propale);

            notificationController.notifyUser(userId, "You have been assigned to a new project.");

            return "User assigned to project successfully.";
        } else {
            throw new NoSuchElementException("Propale or User not found.");
        }
    }


    @Override
    public String unassignUserFromProject(Long propaleId, String userId) {
        Optional<Propale> propaleOptional = propaleRepository.findById(propaleId);

        if (propaleOptional.isPresent()) {
            Propale propale = propaleOptional.get();
            List<ProjectAssignment> assignments = propale.getProjectAssignments();

            // Trouvez l'affectation à supprimer
            ProjectAssignment assignmentToRemove = null;
            for (ProjectAssignment assignment : assignments) {
                if (assignment.getUserId().equals(userId)) {
                    assignmentToRemove = assignment;
                    break;
                }
            }

            // Si l'affectation est trouvée, la supprimer de la collection
            if (assignmentToRemove != null) {
                assignments.remove(assignmentToRemove);
                propaleRepository.save(propale);
                return "User unassigned from project successfully.";
            } else {
                return "Assignment not found.";
            }
        } else {
            throw new NoSuchElementException("Propale not found.");
        }
    }



}
