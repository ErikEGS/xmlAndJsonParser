package model;

import java.util.List;

public class Root {

  private String name;
  private List<Person> person;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Person> getPerson() {
    return person;
  }

  public void setPerson(List<Person> person) {
    this.person = person;
  }

  @Override
  public String toString() {
    return "Root{" +
        "name='" + name + '\'' +
        ", persons=" + person +
        '}';
  }

}
