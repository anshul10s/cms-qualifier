package com.flipkart.qualifier;

import com.flipkart.qualifier.exception.ConversionException;
import com.flipkart.qualifier.exception.QualifierException;
import com.flipkart.qualifier.ifaces.Qualifier;
import com.flipkart.qualifier.ifaces.QualifierConverter;
import com.flipkart.qualifier.ifaces.QualifierSystem;
import lombok.Data;
import org.apache.commons.collections.MapUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * User: smriti.a
 * Date: 23/03/17
 * Time: 1:13 PM
 */


@Data
public class QualifierImpl implements Qualifier {

    String name;
    String qualifierSystemName;
    Map<String, QualifierConverter> qualifierConverterMap;

    protected QualifierImpl(String name, String qualifierSystemName) {
        this.name = name;
        this.qualifierSystemName = qualifierSystemName;
        this.qualifierConverterMap = new HashMap<String, QualifierConverter>();
    }

    public double convert(double value, Qualifier targetQualifier) throws ConversionException {
        if (targetQualifier != null) {
            QualifierConverter qualifierConverter = qualifierConverterMap.get(targetQualifier.getName());
            if (qualifierConverter == null) {
                throw new ConversionException("Incompatible conversion to unit :" + targetQualifier);
            }
            return qualifierConverter.convert(value);
        }
        throw new ConversionException("Invalid target unit");
    }

    public QualifierSystem getQualifierSystem() throws QualifierException {
        return QualifierRegistry.getQualifierSystem(this.qualifierSystemName);
    }

    public Set<String> getConvertibleUnits() {
        if (MapUtils.isNotEmpty(qualifierConverterMap)) {
            qualifierConverterMap.keySet();
        }
        return null;
    }

    public QualifierConverter getConverterTo(Qualifier targetUnit) {
        if (targetUnit != null) {
            return qualifierConverterMap.get(targetUnit.getName());
        }
        return null;
    }

    public boolean isCompatible(Qualifier qualifier) {
        if (qualifier != null) {
            return qualifierConverterMap.containsKey(qualifier.getName());
        }
        return false;
    }

    public boolean equals(Object otherQualifier) {
        Qualifier that = (Qualifier) otherQualifier;
        return this.name.equals(that.getName()) && this.getQualifierSystemName().equals(that.getQualifierSystemName());
    }
}
