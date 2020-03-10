class A{
  @SomeAnnotations({
      @SomeAnnotation("a"),
      @SomeAnnotation("b"),
      @SomeAnnotation("c"),
  })
  void methodOne() {}

  @java.lang.annotation.Repeatable
  @interface SomeAnnotation {

  }
}
