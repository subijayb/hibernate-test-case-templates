package org.hibernate.bugs;


import jakarta.persistence.AttributeConverter;

public class UserValueConverter implements AttributeConverter<UserValue, String> {

    @Override
    public String convertToDatabaseColumn(final UserValue attribute) {
        return null;
    }

    @Override
    public UserValue convertToEntityAttribute(final String dbData) {
        return null;
    }
}