package com.flipkart.qualifier;

import com.flipkart.qualifier.ifaces.QualifierSystem;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * User: smriti.a
 * Date: 23/03/17
 * Time: 1:13 PM
 */


@Data
public class QualifierSystemImpl implements QualifierSystem {

    String name;
    List<String> qualifierList;

    protected QualifierSystemImpl(String name) {
        this.name = name;
        this.qualifierList = new ArrayList<String>();
    }

    protected QualifierSystemImpl(String name, List<String> qualifierList) {
        this.name = name;
        this.qualifierList = qualifierList;
    }
}
