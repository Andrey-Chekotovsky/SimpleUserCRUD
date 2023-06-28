package com.checkotovsky.SimpleUserCRUD.Models;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RoleConverter implements AttributeConverter<Role, String> {

    @Override
    public String convertToDatabaseColumn(Role role) {
        if (role == null)
            return null;

        switch (role) {
            case Customer:
                return "ROLE_CUSTOMER";
            case Admin:
                return "ROLE_ADMIN";
            default:
                throw new IllegalArgumentException(role + " not supported.");
        }
    }

    @Override
    public Role convertToEntityAttribute(String dbData) {
        if (dbData == null)
            return null;
        if(dbData.equals("ROLE_CUSTOMER"))
            return Role.Customer;
        else if(dbData.equals("ROLE_ADMIN"))
            return Role.Admin;
        else
            throw new IllegalArgumentException(dbData + " not supported.");
    }

}