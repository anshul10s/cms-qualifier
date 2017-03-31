package com.flipkart.qualifier;

import com.flipkart.qualifier.exception.QualifierException;
import com.flipkart.qualifier.ifaces.Qualifier;
import com.flipkart.qualifier.ifaces.QualifierSystem;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * User: smriti.a
 * Date: 23/03/17
 * Time: 3:38 PM
 */
public class QualifierRegistry {

    private static final Map<String, Qualifier> qualifierMap = new HashMap<String, Qualifier>();
    private static final Map<String, QualifierSystem> qualifierSystemMap = new HashMap<String, QualifierSystem>();

    static {
        try {
            QualifierLibInitialiser.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static void registerQualifier(Qualifier qualifier) {
        qualifierMap.put(qualifier.getName(), qualifier);
    }

    public static Qualifier getQualifier(String qualifierName) throws QualifierException {
        if (StringUtils.isEmpty(qualifierName) || qualifierMap.get(qualifierName) == null) {
            //ReviewComment: If library initialisation failed at line 26, user will see this exception and will interpret that qualifier is wrong, allthough lib failed to load. Capture a boolean suggesting load was succesful or not and give correct run time error message
            throw new QualifierException("Invalid Qualifier Name : " + qualifierName);
        }
        return qualifierMap.get(qualifierName);
    }

    // ReviewComment: This method is public and returning the handle to modify a private variable, ideally this set return should be immutable
    public static Set<String> getAllQualifiers() {
        return qualifierMap.keySet();
    }

    protected static void registerQualifierSystem(QualifierSystemImpl qualifierSystem) {
        qualifierSystemMap.put(qualifierSystem.getName(), qualifierSystem);
    }

    public static QualifierSystem getQualifierSystem(String qualifierSystem) throws QualifierException {
        if (StringUtils.isEmpty(qualifierSystem) || qualifierSystemMap.get(qualifierSystem) == null) {
            throw new QualifierException("Invalid Qualifier Sytem Name : " + qualifierSystem);
        }
        return qualifierSystemMap.get(qualifierSystem);
    }
}
