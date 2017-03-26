package com.flipkart.qualifier;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.qualifier.exception.QualifierException;
import com.flipkart.qualifier.ifaces.QualifierConverter;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

/**
 * User: smriti.a
 * Date: 23/03/17
 * Time: 3:31 PM
 */
class QualifierLibInitialiser {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static void init() throws Exception {
        Properties qualifierSystems = new Properties();
        String systemFileName = "qualifier-family.cfg";
        InputStream inputStream = QualifierLibInitialiser.class.getClassLoader().getResourceAsStream(systemFileName);
        if (inputStream != null) {
            qualifierSystems.load(inputStream);
            for (String systemName : qualifierSystems.stringPropertyNames()) {
                String[] qualifierList = qualifierSystems.getProperty(systemName).split(",");
                for (String qualifierName : qualifierList) {
                    QualifierRegistry.registerQualifier(new QualifierImpl(qualifierName, systemName));
                }
                QualifierRegistry.registerQualifierSystem(new QualifierSystemImpl(systemName, Arrays.asList(qualifierList)));
            }

            String conversionFileName = "conversion.cfg";
            inputStream = QualifierLibInitialiser.class.getClassLoader().getResourceAsStream(conversionFileName);
            Properties conversions = new Properties();
            if (inputStream != null) {
                conversions.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + conversionFileName + "' not found in the classpath");
            }

            for (String key : conversions.stringPropertyNames()) {
                String convertor = conversions.getProperty(key);
                String[] qualifierArr = key.split("_");
                QualifierImpl sourceQualifier = (QualifierImpl) QualifierRegistry.getQualifier(qualifierArr[0]);
                QualifierImpl targetQualifier = (QualifierImpl) QualifierRegistry.getQualifier(qualifierArr[1]);
                if (sourceQualifier == null) {
                    throw new QualifierException(qualifierArr[0] + " is not registered under any family");
                }
                if (targetQualifier == null) {
                    throw new QualifierException(qualifierArr[1] + " is not registered under any family");
                }
                if (!sourceQualifier.getQualifierSystemName().equals(targetQualifier.getQualifierSystemName())) {
                    throw new QualifierException("Invalid conversion specification from " + qualifierArr[0] + " to " + qualifierArr[1]);
                }
                sourceQualifier.getQualifierConverterMap().put(qualifierArr[1], objectMapper.readValue(convertor, QualifierConverter.class));
            }
        } else {
            throw new FileNotFoundException("property file '" + systemFileName + "' not found in the classpath");
        }
    }
}
