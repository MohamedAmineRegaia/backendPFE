package com.devoteam.devoteamPoc.Dto;

import java.util.Date;

public record UserRegistrationRecord(String username, String email, String firstName, String lastName, String password, String disponibilite, String profession, Date date_deb_projet , Date date_fin_projet) {
}