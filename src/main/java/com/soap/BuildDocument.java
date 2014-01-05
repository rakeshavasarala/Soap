package com.soap;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class BuildDocument {

    private static DocumentBuilderFactory dbf = DocumentBuilderFactory
            .newInstance();

    public static void main(String[] args) throws Exception {
        BuildDocument domTest = new BuildDocument();
        domTest.testXmlDocumentWithNamespaces();
    }

    public void testXmlDocumentWithNamespaces() throws Exception {
        DocumentBuilder db = dbf.newDocumentBuilder();
        DOMImplementation domImpl = db.getDOMImplementation();
        Document document = buildExampleDocumentWithNamespaces(domImpl);
        serialize(domImpl, document);
    }

    private Document buildExampleDocumentWithNamespaces(
            DOMImplementation domImpl) throws ParserConfigurationException {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        /*Document document = domImpl.createDocument("http://www.iata.org/IATA/NDC",
                "ndc:AirShoppingRQ", null);*/
        Document document = docBuilder.newDocument();

        Element element = document.createElementNS("http://another.namespace",
                "element");
        document.getDocumentElement().appendChild(element);
        return document;
    }

    private void serialize(DOMImplementation domImpl, Document document) {
        DOMImplementationLS ls = (DOMImplementationLS) domImpl;
        LSSerializer lss = ls.createLSSerializer();
        LSOutput lso = ls.createLSOutput();
        lso.setByteStream(System.out);
        lss.write(document, lso);
    }

}
