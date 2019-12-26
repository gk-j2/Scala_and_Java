package fintech.lecture10;

import java.io.InputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;

class Slide02Example02 {

  public static final void main(String[] args) throws IOException {
    File file = new File("/etc/passwd");
    try (InputStream is = new FileInputStream(file)) {
      byte[] bytes = new byte[128];
      is.read(bytes);
    }
  }

}
