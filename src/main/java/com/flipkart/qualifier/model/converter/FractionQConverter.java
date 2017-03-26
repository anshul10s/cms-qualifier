package com.flipkart.qualifier.model.converter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.flipkart.qualifier.ifaces.QualifierConverter;

import java.util.Optional;

/**
 * User: smriti.a
 * Date: 23/03/17
 * Time: 9:25 AM
 */
public class FractionQConverter extends QualifierConverter {

    private final long _divisor;

    private final long _dividend;

    @JsonCreator
    public FractionQConverter(@JsonProperty("dividend") long dividend, @JsonProperty("divisor") long divisor) {
        if (divisor < 0l)
            throw new IllegalArgumentException("Negative divisor, Divisor should be greater than 0");
        if (dividend == divisor)
            throw new IllegalArgumentException("Identity converter not allowed");
        _dividend = dividend;
        _divisor = divisor;
    }

    @Override
    public QualifierConverter inverse() {
        return _dividend < 0 ? new FractionQConverter(-_divisor, -_dividend)
                : new FractionQConverter(_divisor, _dividend);
    }

    @Override
    public double convert(double x) {
        return x * _dividend / _divisor;
    }

    @Override
    public String conversionRepresentation(Optional<String> baseRepresentation) {
        if (baseRepresentation.isPresent())
            return "(" + baseRepresentation.get() + "*" + _dividend + ")" + "/" + _divisor;
        else
            return _dividend + "/" + _divisor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FractionQConverter)) return false;

        FractionQConverter that = (FractionQConverter) o;

        if (_divisor != that._divisor) return false;
        return _dividend == that._dividend;

    }

    @Override
    public int hashCode() {
        int result = (int) (_divisor ^ (_divisor >>> 32));
        result = 31 * result + (int) (_dividend ^ (_dividend >>> 32));
        return result;
    }

    @JsonProperty("divisor")
    public long get_divisor() {
        return _divisor;
    }

    @JsonProperty("dividend")
    public long get_dividend() {
        return _dividend;
    }
}
