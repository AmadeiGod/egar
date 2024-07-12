package ru.egartech.Conventer;

import jakarta.persistence.AttributeConverter;

import java.util.Arrays;
import java.util.List;

import static java.lang.String.join;

public class CommaDelimitedStringsConverter implements AttributeConverter<List<String>,String> {
    @Override
    public String convertToDatabaseColumn(List<String> attributeValue) {
        if ( attributeValue == null ) {
            return null;
        }
        return join( ",", attributeValue );
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if ( dbData == null ) {
            return null;
        }
        return Arrays.asList( dbData.split( "," ) );
    }
}