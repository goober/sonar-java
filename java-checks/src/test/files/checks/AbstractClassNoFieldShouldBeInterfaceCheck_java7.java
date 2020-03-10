import com.google.auto.value.AutoOneOf;
import com.google.auto.value.AutoValue;
import org.immutables.value.Value;

abstract class A {
  private int b;

  abstract void method();
}
abstract class B {
  int method(){
    return 1;
  }
  class F {}
}
class C {
  int method(){
    return 1;
  }
}
