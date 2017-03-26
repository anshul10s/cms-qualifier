package com.flipkart.qualifier.model.converter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.flipkart.qualifier.ifaces.QualifierConverter;

import java.util.Optional;

/**
 * User: smriti.a
 * Date: 23/03/17
 * Time: 9:23 AM
 */

public class LinearQConverter extends QualifierConverter {

    private final double _constant;

    @JsonProperty("constant")
    public double get_constant() {
        return _constant;
    }

    @JsonCreator
    public LinearQConverter(@JsonProperty("constant") double constant) {
        if (constant == 0.0)
            throw new IllegalArgumentException("Provide a valid non-zero constant");
        _constant = constant;
    }


    public QualifierConverter inverse() {
        return new LinearQConverter(-_constant);
    }


    public double convert(double x) {
        return x + _constant;
    }


    public String conversionRepresentation(Optional<String> baseRepresentation) {
        if (_constant > 0)
            return baseRepresentation.isPresent() ? baseRepresentation.get() + "+" + _constant : "+" + _constant;
        else
            return baseRepresentation.isPresent() ? baseRepresentation.get() + "-" + (-_constant) : "-" + (-_constant);

    }

    @Override
    public QualifierConverter concatenate(QualifierConverter toConverter) {
        if (toConverter instanceof LinearQConverter) {
            double constant = _constant + ((LinearQConverter) toConverter)._constant;
            return valueOf(constant);
        } else {
            return super.concatenate(toConverter);
        }
    }

    private static QualifierConverter valueOf(double constant) {
        float asFloat = (float) constant;
        return asFloat == 0.0f ? QualifierConverter.IDENTITY : new LinearQConverter(constant);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinearQConverter)) return false;

        LinearQConverter that = (LinearQConverter) o;

        return Double.compare(that._constant, _constant) == 0;

    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(_constant);
        return (int) (temp ^ (temp >>> 32));
    }
}
