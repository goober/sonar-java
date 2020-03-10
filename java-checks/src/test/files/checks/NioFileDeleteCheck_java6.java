import java.io.File;
import java.nio.file.Path;
class A {
  public void cleanUp(Path path) {
    File file = new File(path);
    if (!file.delete()) {
    }
    java.nio.file.Files.delete(path); // compliant
  }
}
