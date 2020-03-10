class A {
  void doStuff() {
    Class.forName("org.h2.Driver");
    Class.forName("java.lang.String");
    Class.forName(javax.print.ServiceUIFactory.DIALOG_UI);
  }
}
