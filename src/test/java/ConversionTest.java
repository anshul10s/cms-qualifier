import com.flipkart.qualifier.QualifierRegistry;
import com.flipkart.qualifier.exception.ConversionException;
import com.flipkart.qualifier.exception.QualifierException;
import com.flipkart.qualifier.ifaces.Qualifier;
import org.junit.Test;

/**
 * User: smriti.a
 * Date: 23/03/17
 * Time: 4:26 PM
 */

public class ConversionTest {

    @Test
    public void testMetreToCm() throws QualifierException, ConversionException {
        Qualifier qualifier = QualifierRegistry.getQualifier("m");
        Qualifier target = QualifierRegistry.getQualifier("cm");
        double cm = qualifier.convert(5, target);
        assert (cm == 500);
    }

    @Test
    public void testLinearConverter() throws QualifierException, ConversionException {
        Qualifier qualifier = QualifierRegistry.getQualifier("rupees");
        Qualifier target = QualifierRegistry.getQualifier("dollar");
        double b = qualifier.convert(4, target);
        assert (b == 69);
    }

    @Test
    public void testComplexConverter() throws QualifierException, ConversionException {
        Qualifier qualifier = QualifierRegistry.getQualifier("g");
        Qualifier target = QualifierRegistry.getQualifier("kg");
        double b = qualifier.convert(64, target);
        assert (b == 10);
    }

}
