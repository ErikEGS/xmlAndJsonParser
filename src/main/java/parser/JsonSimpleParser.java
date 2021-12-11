package parser;

import static constants.Constants.TEG_AGE;
import static constants.Constants.TEG_ID;
import static constants.Constants.TEG_LASTNAME;
import static constants.Constants.TEG_NAME;
import static constants.Constants.TEG_NAME_MAIN;
import static constants.Constants.TEG_PERSON;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Person;
import model.Root;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonSimpleParser {

  public Root parse() {

    Root root = new Root();
    JSONParser parser = new JSONParser();

    try (FileReader reader = new FileReader("persons.json")) {

      JSONObject jsonObject = (JSONObject) parser.parse(reader);
      root.setName((String) jsonObject.get(TEG_NAME_MAIN));

      JSONArray jsonArray = (JSONArray) jsonObject.get(TEG_PERSON);

      List<Person> personList = new ArrayList<>();

      for (int i = 0; i < jsonArray.size(); i++) {
        JSONObject jb = (JSONObject) jsonArray.get(i);

        long id = (Long) jb.get(TEG_ID);
        String name = (String) jb.get(TEG_NAME);
        String lastName = (String) jb.get(TEG_LASTNAME);
        long age = (Long) jb.get(TEG_AGE);

        Person person = new Person((int) id, name, lastName, (int) age);
        personList.add(person);

      }

      root.setPerson(personList);
      return root;

    } catch (IOException | ParseException e) {
      System.out.println("Parsing error " + e);
    }

    return null;
  }

}
