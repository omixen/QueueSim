/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.net.URL;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import java.io.File;


/**
 *
 * @author Cody
 */
public class Config {


    private Document config;
    private Hashtable<String, CustomerType> allTypes;
    private ArrayList<Queue> allQueues;
    private ArrayList<ServiceStation> allStations;
    private ArrayList<String> allCustomers;
    private int sleepTime = 200;
    private long tick = 0;
    private long maxTicks = 1800;


    public Config(String url) throws Exception {
        File fXmlFile = new File(url);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        this.config = dBuilder.parse(fXmlFile);

        //optional, but recommended
        //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
        this.config.getDocumentElement().normalize();

        this.allTypes = new Hashtable<String, CustomerType>();
        this.allCustomers = new ArrayList<String>();
        this.allQueues = new ArrayList<Queue>();
        this.allStations = new ArrayList<ServiceStation>();
        //read through the xml
        this.init();
    }

    private void init() {
        this.initCustomerTypes();
        this.initQueues();
        this.initStations();
    }

    public void initStations(){

        NodeList nList = this.config.getElementsByTagName("service_station");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);


            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;
                String name = eElement.getAttribute("id");
                ServiceStation ss = new ServiceStation();
                ss.setId(name);

                NodeList aqList = (NodeList) eElement.getElementsByTagName("assigned_queue");
                ArrayList<Queue> queues = new ArrayList<Queue>();
                for (int temp2 = 0; temp2 < aqList.getLength(); temp2++) {

                    Node pNode = aqList.item(temp2);

                    Element pElement = (Element) pNode;

                    String value = pElement.getTextContent();
                    for (Queue q : allQueues) {
                        if(q.getId() == value) {
                             queues.add(q);
                        }
                    }
                }
                ss.setQueues(queues);

                NodeList acList = (NodeList) eElement.getElementsByTagName("allowed_customer_type");
                ArrayList<String> types = new ArrayList<String>();
                for (int temp2 = 0; temp2 < acList.getLength(); temp2++) {
                    Node pNode = acList.item(temp2);
                    Element pElement = (Element) pNode;
                    String value = pElement.getTextContent();
                    types.add(value);
                }
                ss.setTypes(types);

                allStations.add(ss);
            }
        }
    }

    public void initQueues(){

        NodeList nList = this.config.getElementsByTagName("queue");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Queue q = new Queue();
                ArrayList<String> custTypes = new ArrayList<String>();

                Element eElement = (Element) nNode;
                String name = eElement.getAttribute("id");
                q.setId(name);

                NodeList pList = (NodeList) eElement.getElementsByTagName("allowed_customer_type");
                for (int temp2 = 0; temp2 < pList.getLength(); temp2++) {

                    Node pNode = pList.item(temp2);

                    Element pElement = (Element) pNode;

                    String value = pElement.getTextContent();
                    custTypes.add(value);
                }
                q.setTypes(custTypes);
                allQueues.add(q);
            }
        }
    }

    public void initCustomerTypes(){

        NodeList nList = this.config.getElementsByTagName("customer_type");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;
                String name = eElement.getAttribute("id");
                CustomerType ct = new CustomerType();
                ct.setName(name);

                NodeList pList = (NodeList) eElement.getElementsByTagName("property");
                for (int temp2 = 0; temp2 < pList.getLength(); temp2++) {
                    Node pNode = pList.item(temp2);
                    Element pElement = (Element) pNode;

                    String label = pElement.getAttribute("id");
                    String value = pElement.getTextContent();

                    if(label == "name") {
                        ct.setName(label);
                    }else if(label == "description") {
                        ct.setDescription(value);
                    }else if(label == "serviceTime") {
                        ct.setServiceTime(Integer.parseInt(value));
                    }else if(label == "totalCustomers") {
                        ct.setTotalCustomers(Integer.parseInt(value));
                    }
                }
                allTypes.put(name, ct);
            }

        }
    }

    public void initSettings(){
    /*
        NodeList nList = this.config.getElementsByTagName(type_name);
        Hashtable<String, String> types = new Hashtable<String, String>();

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;
                NodeList pList = (NodeList) eElement.getElementsByTagName("property");

                if ( eElement.getAttribute("id").toString() != "") {
                    String label1 = eElement.getAttribute("id").toString() + "." + "id";
                    String value2 = eElement.getAttribute("id").toString();
                    types.put(label1, value2);
                }

                if ( eElement.getAttribute("type").toString() != "") {
                    String label1 = eElement.getAttribute("id").toString() + "." + "type";
                    String value2 = eElement.getAttribute("type").toString();
                    types.put(label1, value2);
                }
                for (int temp2 = 0; temp2 < pList.getLength(); temp2++) {
                    Node pNode = pList.item(temp2);
                    Element pElement = (Element) pNode;
                    String label = pElement.getAttribute("id");
                    String value = pElement.getTextContent();
                    types.put(label, value);

                }
            }
        }

        return types;
        */
    }

    public Hashtable<String, CustomerType> getAllTypes() {
        return allTypes;
    }

    public ArrayList<Queue> getAllQueues() {
        return allQueues;
    }

    public ArrayList<ServiceStation> getAllStations() {
        return allStations;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public long getTick() {
        return tick;
    }

    public long getMaxTicks() {
        return maxTicks;
    }
}