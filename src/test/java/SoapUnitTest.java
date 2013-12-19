import com.soap.SOAPRequestCreator1;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: Rakesh Avasarala
 * Date: 12/19/13
 * Time: 9:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class SoapUnitTest {

    @Test
    public void test1() throws Exception {
        new SOAPRequestCreator1().createSOAPRequest();
    }
}
