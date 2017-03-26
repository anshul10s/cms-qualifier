package com.flipkart.qualifier.ifaces;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.flipkart.qualifier.model.converter.ComplexConverter;
import com.flipkart.qualifier.model.converter.FractionQConverter;
import com.flipkart.qualifier.model.converter.LinearQConverter;

import java.util.Optional;

/**
 * User: smriti.a
 * Date: 22/03/17
 * Time: 6:25 PM
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FractionQConverter.class, name = "FractionQConverter"),
        @JsonSubTypes.Type(value = LinearQConverter.class, name = "LinearQConverter"),
        @JsonSubTypes.Type(value = ComplexConverter.class, name = "ComplexConverter")})
public abstract class QualifierConverter {

    public static final QualifierConverter IDENTITY = new IdentityConverter();

    public abstract QualifierConverter inverse();

    public abstract double convert(double x);

    public abstract String conversionRepresentation(Optional<String> baseRepresentation);

    public QualifierConverter concatenate(QualifierConverter toConverter) {
        return (toConverter == IDENTITY) ? this : new ComplexConverter(toConverter, this);
    }

    private static final class IdentityConverter extends QualifierConverter {

        @Override
        public QualifierConverter inverse() {
            return this;
        }

        @Override
        public double convert(double x) {
            return x;
        }

        @Override
        public String conversionRepresentation(Optional<String> baseRepresentation) {
            return baseRepresentation.get();
        }

        @Override
        public QualifierConverter concatenate(QualifierConverter converter) {
            return converter;
        }


    }
}
