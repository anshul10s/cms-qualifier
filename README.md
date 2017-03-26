# cms-qualifier
Qualifier conversions 

To use the library, add maven dependency 
<dependency>
       <groupId>com.flipkart</groupId>
       <artifactId>qualifier</artifactId>
       <version>1.0-SNAPSHOT</version>
</dependency>

Sample code to convert m to cm :
 Qualifier source = QualifierRegistry.getQualifier("m");
 Qualifier target = QualifierRegistry.getQualifier("cm");
 double result = source.convert(5.0, target);
