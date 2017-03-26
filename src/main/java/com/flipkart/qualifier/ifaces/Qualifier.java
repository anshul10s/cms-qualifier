package com.flipkart.qualifier.ifaces;

import com.flipkart.qualifier.exception.ConversionException;
import com.flipkart.qualifier.exception.QualifierException;

import java.util.Set;

/**
 * User: smriti.a
 * Date: 25/03/17
 * Time: 4:42 PM
 */

public interface Qualifier {

    public String getName();

    public String getQualifierSystemName();

    public double convert(double value, Qualifier targetQualifier) throws ConversionException;

    public QualifierSystem getQualifierSystem() throws QualifierException;

    public Set<String> getConvertibleUnits();

    public QualifierConverter getConverterTo(Qualifier targetUnit);

    public boolean isCompatible(Qualifier qualifier);
}
