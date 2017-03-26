package com.flipkart.qualifier.model.converter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.flipkart.qualifier.ifaces.QualifierConverter;

import java.util.Optional;

/**
 * User: smriti.a
 * Date: 23/03/17
 * Time: 9:24 AM
 */

public class ComplexConverter extends QualifierConverter {
    private final QualifierConverter _first;

    private final QualifierConverter _second;

    @JsonProperty("first")
    public QualifierConverter get_first() {
        return _first;
    }

    @JsonProperty("second")
    public QualifierConverter get_second() {
        return _second;
    }

    @JsonCreator
    public ComplexConverter(@JsonProperty("first") QualifierConverter first, @JsonProperty("second") QualifierConverter second) {
        _first = first;
        _second = second;
    }

    @Override
    public QualifierConverter inverse() {
        return new ComplexConverter(_second.inverse(), _first.inverse());
    }

    @Override
    public double convert(double x) {
        return _second.convert(_first.convert(x));
    }

    @Override
    public String conversionRepresentation(Optional<String> baseRepresentation) {

        return _second.conversionRepresentation(Optional.of("(" + _first.conversionRepresentation(baseRepresentation) +
                ")"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComplexConverter)) return false;

        ComplexConverter that = (ComplexConverter) o;

        if (_first != null ? !_first.equals(that._first) : that._first != null) return false;
        return !(_second != null ? !_second.equals(that._second) : that._second != null);

    }

    @Override
    public int hashCode() {
        int result = _first != null ? _first.hashCode() : 0;
        result = 31 * result + (_second != null ? _second.hashCode() : 0);
        return result;
    }
}
