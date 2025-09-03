package exercise;

final class EmployeeCSVAdapter implements Employee {
  private final EmployeeCSV adaptee;

  EmployeeCSVAdapter(EmployeeCSV adaptee) { this.adaptee = adaptee; }

  @Override public String getId() {
    String[] t = adaptee.tokens();
    return t.length > 0 ? t[0] : "";
  }

  @Override public String getFirstName() {
    String[] t = adaptee.tokens();
    return t.length > 1 ? t[1] : "";
  }

  @Override public String getLastName() {
    String[] t = adaptee.tokens();
    return t.length > 2 ? t[2] : "";
  }

  @Override public String getEmail() {
    String[] t = adaptee.tokens();
    return t.length > 3 ? t[3] : "";
  }
}


