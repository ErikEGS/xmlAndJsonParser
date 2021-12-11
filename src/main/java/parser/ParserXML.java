package parser;

import static constants.Constants.TEG_AGE;
import static constants.Constants.TEG_ID;
import static constants.Constants.TEG_LASTNAME;
import static constants.Constants.TEG_NAME;
import static constants.Constants.TEG_NAME_MAIN;
import static constants.Constants.TEG_PERSON;
import static constants.Constants.TEG_PERSONS;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import model.Person;
import model.Root;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParserXML {

  public Root parse() {

    Root root = new Root();
    Document doc = buildDocument();

    if (doc == null) {
      return null;
    }

    Node rootNode = doc.getFirstChild();

    NodeList rootChilds = rootNode.getChildNodes();

    String mainName = null;
    Node personsNode = null;

    for (int i = 0; i < rootChilds.getLength(); i++) {
      if (rootChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
        continue;
      }

      switch (rootChilds.item(i).getNodeName()) {
        case TEG_NAME_MAIN: {
          mainName = rootChilds.item(i).getTextContent();
          break;
        }
        case TEG_PERSONS: {
          personsNode = rootChilds.item(i);
          break;
        }
      }
    }

    if (personsNode == null) {
      return null;
    }

    List<Person> personList = parsePersons(personsNode);

    root.setName(mainName);
    root.setPerson(personList);

    return root;

  }

  private Document buildDocument() {
    File file = new File("persons.xml");
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    Document doc;
    try {
      return doc = dbf.newDocumentBuilder().parse(file);
    } catch (IOException | ParserConfigurationException | SAXException e) {
      System.out.println("Parsing Error  " + e);
      return null;
    }
  }

  private List<Person> parsePersons(Node personsNode) {

    List<Person> personList = new ArrayList<>();
    NodeList personsChailds = personsNode.getChildNodes();

    for (int i = 0; i < personsChailds.getLength(); i++) {

      if (personsChailds.item(i).getNodeType() != Node.ELEMENT_NODE) {
        continue;
      }
      if (!personsChailds.item(i).getNodeName().equals(TEG_PERSON)) {
        continue;
      }

      Person person = parsPerson(personsChailds.item(i));
      personList.add(person);
    }

    return personList;

  }


  private Person parsPerson(Node personNode) {

    NodeList personChailds = personNode.getChildNodes();
    int id = 0;
    String name = "";
    String lastName = "";
    int age = 0;

    for (int j = 0; j < personChailds.getLength(); j++) {

      if (personChailds.item(j).getNodeType() != Node.ELEMENT_NODE) {
        continue;
      }

      switch (personChailds.item(j).getNodeName()) {
        case TEG_ID: {
          id = Integer.parseInt(personChailds.item(j).getTextContent());
          break;
        }
        case TEG_NAME: {
          name = personChailds.item(j).getTextContent();
          break;
        }
        case TEG_LASTNAME: {
          lastName = personChailds.item(j).getTextContent();
          break;
        }
        case TEG_AGE: {
          age = Integer.parseInt(personChailds.item(j).getTextContent());
          break;
        }
      }
    }

    return new Person(id, name, lastName, age);
  }

}