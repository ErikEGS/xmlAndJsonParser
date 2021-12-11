import model.Root;
import parser.GsonParser;
import parser.JsonSimpleParser;
import parser.ParserXML;

public class Main {

  public static void main(String[] args) {

    // Parsing XML whit method DOM
    ParserXML xmlParser = new ParserXML();
    Root root = xmlParser.parse();
    System.out.println(root);

    // Parsing Gson Simple
    JsonSimpleParser jsonSimpleParser = new JsonSimpleParser();
    root = jsonSimpleParser.parse();
    System.out.println(root);

    // Parsing Gson
    GsonParser gsonParser = new GsonParser();
    root = gsonParser.parse();
    System.out.println(root);

  }
}
