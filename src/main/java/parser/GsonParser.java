package parser;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
import model.Root;

public class GsonParser {

  public Root parse() {

    Gson gson = new Gson();

    try (FileReader reader = new FileReader("persons.json")) {
      return gson.fromJson(reader, Root.class);
    } catch (IOException e) {
      System.out.println("Parsing error " + e);
    }
    return null;
  }

}
