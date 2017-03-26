import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.qualifier.ifaces.QualifierConverter;
import com.flipkart.qualifier.model.converter.ComplexConverter;
import com.flipkart.qualifier.model.converter.FractionQConverter;
import com.flipkart.qualifier.model.converter.LinearQConverter;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * User: smriti.a
 * Date: 23/03/17
 * Time: 10:06 AM
 */

public class SerializerTest {

    ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testFractionConverterDeserializer() throws IOException {
        String json = "{\"type\":\"FractionQConverter\",\"divisor\":6,\"dividend\":5}";
        QualifierConverter qualifierConverter = objectMapper.readValue(json, QualifierConverter.class);
        QualifierConverter qualifierConverterExpected = new FractionQConverter(5, 6);
        assert (qualifierConverter.getClass() == FractionQConverter.class);
        assert qualifierConverter.equals(qualifierConverterExpected);
    }

    @Test
    public void testFractionConverterSerializer() throws IOException {
        String json = "{\"type\":\"FractionQConverter\",\"divisor\":6,\"dividend\":5}";
        QualifierConverter qualifierConverter = new FractionQConverter(5, 6);
        assert (objectMapper.writeValueAsString(qualifierConverter).length() > 3);
    }


    @Test
    public void testComplexConverterDeserializer() throws IOException {
        String json = "{\"type\":\"ComplexConverter\",\"first\":{\"type\":\"FractionQConverter\",\"dividend\":5,\"divisor\":6},\"second\":{\"type\":\"FractionQConverter\",\"dividend\":5,\"divisor\":4}}";
        QualifierConverter qualifierConverter = objectMapper.readValue(json, QualifierConverter.class);
        assert (qualifierConverter.getClass() == ComplexConverter.class);
    }

    @Test
    public void testComplexConverterSerializer() throws IOException {
        QualifierConverter qualifierConverter = new ComplexConverter(new FractionQConverter(5, 6), new LinearQConverter(4));
        assert (objectMapper.writeValueAsString(qualifierConverter).length() > 3);
    }

    @Test
    public void testLinearConverterDeserializer() throws IOException {
        String json = "{\"type\":\"LinearQConverter\",\"constant\":4.0}";
        QualifierConverter qualifierConverter = objectMapper.readValue(json, QualifierConverter.class);
        assert (qualifierConverter.getClass() == LinearQConverter.class);
    }

    @Test
    public void testLinearConverterSerializer() throws IOException {
        QualifierConverter qualifierConverter = new FractionQConverter(5, 6);
        System.out.println(objectMapper.writeValueAsString(qualifierConverter));
        assert (objectMapper.writeValueAsString(qualifierConverter).length() > 3);
    }
}
