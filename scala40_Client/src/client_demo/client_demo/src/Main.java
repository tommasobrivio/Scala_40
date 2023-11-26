import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main {

    static String FILE_XML="src/config.xml";
    public static void main(String[] args) throws Exception {
        

        String[] info = leggiXML();

        TCP protocol= new TCP(info[0], Integer.parseInt(info[1]));

        protocol.start();

        while(!protocol.connesso()){}


        protocol.send("richiesta client;pescaMazzo");
        String risposta=protocol.read();

        System.out.println(risposta);

    }


    static String[] leggiXML() throws ParserConfigurationException, SAXException, IOException{
        String[] tmp= new String[2];

        File xml = new File(FILE_XML);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xml);

        NodeList nodeList = doc.getElementsByTagName("socket");
        Node node = nodeList.item(0);

        if (node.getNodeType() == Node.ELEMENT_NODE){
            Element e = (Element) node;
            tmp[0] = e.getElementsByTagName("ip").item(0).getTextContent();
            tmp[1] = e.getElementsByTagName("port").item(0).getTextContent();
        }

        return tmp;
    }
}
