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
                return "Customer";
            case Admin:
                return "Admin";
            default:
                throw new IllegalArgumentException(role + " not supported.");
        }
    }

    @Override
    public Role convertToEntityAttribute(String dbData) {
        if (dbData == null)
            return null;
        if(dbData.equals("Customer"))
            return Role.Customer;
        else if(dbData.equals("Admin"))
            return Role.Admin;
        else
            throw new IllegalArgumentException(dbData + " not supported.");
    }

}