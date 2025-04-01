package customer.test_xb;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Portal {
    private static final Logger LOGGER = Logger.getLogger(Portal.class.getName());

    public static void main(String[] args) throws IOException {
        Portal portal = new Portal();
        Document document = null;
        try {
            document = portal.createDocumentWithRequest();
            portal.addHeaderToRequest(document);
            portal.outputXmlContent(document);
        } catch (ParserConfigurationException | TransformerException e) {
            LOGGER.log(Level.SEVERE, "Exception occurred", e);
        }
    }

    public Document createDocumentWithRequest() throws ParserConfigurationException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        Element request = document.createElement("REQUEST");
        document.appendChild(request); // 将request添加到document中

        return document;
    }

    public void addHeaderToRequest(Document document) {
        NodeList requestNodes = document.getElementsByTagName("REQUEST");
        if (requestNodes.getLength() > 0) {
            Element request = (Element) requestNodes.item(0);

            Element header = document.createElement("HEADER");
            Element serviceId = document.createElement("SERVICEID");
            Element authorizationCode = document.createElement("authorizationCode");
            Element dataCreatedTime = document.createElement("DATACREATEDTIME");

            Element testXk = document.createElement("TESTXK");

            serviceId.setTextContent("VCFQAS");
            authorizationCode.setTextContent("CbIIux4J");
            dataCreatedTime.setTextContent("20241007");
            testXk.setTextContent("testXk123");
            header.appendChild(serviceId);
            header.appendChild(authorizationCode);
            header.appendChild(dataCreatedTime);
            header.appendChild(testXk);
            request.appendChild(header); // 将header添加到request中
        }
    }

    public void outputXmlContent(Document document) throws TransformerException, IOException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        try (StringWriter writer = new StringWriter()) {
            StreamResult result = new StreamResult(writer);
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.transform(source, result);
            // LOGGER.info(writer.toString().replace(
            // "<REQUEST><HEADER><SERVICEID>VCFQAS</SERVICEID><authorizationCode>CbIIux4J</authorizationCode><DATACREATEDTIME>20241007</DATACREATEDTIME></HEADER></REQUEST>",
            // "testXk123"));
            String a = writer.toString().replace("testXk123",
                    "<REQUEST><HEADER><SERVICEID>VCFQAS</SERVICEID><authorizationCode>CbIIux4J</authorizationCode><DATACREATEDTIME>20241007</DATACREATEDTIME></HEADER></REQUEST>");
            System.out.println(a);
        }
    }
}