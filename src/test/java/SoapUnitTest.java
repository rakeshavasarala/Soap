import com.soap.StringToSOAPUtils;
import com.soap.SOAPRequestCreator1;
import org.junit.Test;

import javax.xml.soap.SOAPException;
import java.io.IOException;

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


    @Test
    public void test2() throws IOException, SOAPException {

        String message = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "<SOAP-ENV:Header/>\n" +
                "<SOAP-ENV:Body>\n" +
                "<ndc:AirShoppingRQ xmlns:ndc=\"http://www.iata.org/IATA/NDC\" xmlns:com=\"http://www.iata.org/IATA/NDC/common\">\n" +
                "   <ndc:OriginDestination>\n" +
                "      <ndc:Departure>\n" +
                "         <ndc:CityCode>OriginDestination[0].Departure.CityCode</ndc:CityCode>\n" +
                "      </ndc:Departure>\n" +
                "      <ndc:TravelDateTime>\n" +
                "         <ndc:DateData>\n" +
                "            <ndc:Date>OriginDestination[0].Date</ndc:Date>\n" +
                "         </ndc:DateData>\n" +
                "         <ndc:TimeData>\n" +
                "            <ndc:Time>OriginDestination[0].Time</ndc:Time>\n" +
                "         </ndc:TimeData>\n" +
                "      </ndc:TravelDateTime>\n" +
                "      <ndc:Arrival>\n" +
                "         <ndc:CityCode>OriginDestination[0].Arrival.CityCode</ndc:CityCode>\n" +
                "      </ndc:Arrival>\n" +
                "   </ndc:OriginDestination>\n" +
                "   <ndc:OriginDestination>\n" +
                "      <ndc:Departure>\n" +
                "         <ndc:CityCode>OriginDestination[1].Departure.CityCode</ndc:CityCode>\n" +
                "      </ndc:Departure>\n" +
                "      <ndc:TravelDateTime>\n" +
                "         <ndc:DateData>\n" +
                "            <ndc:Date>OriginDestination[1].Date</ndc:Date>\n" +
                "         </ndc:DateData>\n" +
                "         <ndc:TimeData>\n" +
                "            <ndc:Time>OriginDestination[1].Time</ndc:Time>\n" +
                "         </ndc:TimeData>\n" +
                "      </ndc:TravelDateTime>\n" +
                "      <ndc:Arrival>\n" +
                "         <ndc:CityCode>OriginDestination[1].Arrival.CityCode</ndc:CityCode>\n" +
                "      </ndc:Arrival>\n" +
                "   </ndc:OriginDestination>\n" +
                "   <ndc:TravelerCount>\n" +
                "      <ndc:Traveler com:PaxType=\"ADT\">PaxType.ADT</ndc:Traveler>\n" +
                "      <ndc:Traveler com:PaxType=\"CHD\">PaxType.CHD</ndc:Traveler>\n" +
                "      <ndc:Traveler com:PaxType=\"INF\">PaxType.INF</ndc:Traveler>\n" +
                "   </ndc:TravelerCount>\n" +
                "   <ndc:AttributeData>\n" +
                "      <ndc:Cabin>Cabin</ndc:Cabin>\n" +
                "   </ndc:AttributeData>\n" +
                "</ndc:AirShoppingRQ>\n" +
                "</SOAP-ENV:Body></SOAP-ENV:Envelope>";

        new StringToSOAPUtils().getSOAPMessageFromString(message);

    }
}
