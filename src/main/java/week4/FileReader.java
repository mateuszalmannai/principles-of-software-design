package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Create Path resource
 * Create BufferedReader from Path using Files
 */
public class FileReader {

  private static final String BASE_PATH = "/Users/mateusz/IdeaProjects/PrinciplesOfSoftwareDesign/src/main/resources/data/";

  public void readAndPrint() throws IOException {
    Path path = Paths.get(BASE_PATH + "hello_unicode.txt");
    BufferedReader reader = Files.newBufferedReader(path);
    while (true) {
      String line = reader.readLine();
      if (line == null) {
        break;
      }
      System.out.println(line);
    }
  }

  public void readURLAndPrint() throws IOException {
    URL source = new URL("http://www.google.com");
    BufferedReader reader = new BufferedReader(new InputStreamReader(source.openStream()));
    while (true) {
      String line = reader.readLine();
      if (line == null) {
        break;
      }
      System.out.println(line);
    }
  }

  public static void main(String[] args) {
    FileReader fileReader = new FileReader();
    try {
      fileReader.readAndPrint();
      System.out.println("########################");
      fileReader.readURLAndPrint();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
