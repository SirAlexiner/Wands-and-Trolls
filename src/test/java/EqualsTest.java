import static org.junit.jupiter.api.Assertions.assertEquals;

import no.ntnu.idatg2001.gr13.Link;
import org.junit.jupiter.api.Test;

class EqualsTest {
  Link linkTest = new Link("Arthur", "Arthur");
  Link linkCorrect = new Link("Arthur", "Arthur");
  Link linkPartialCorrect = new Link("Torgrim", "Arthur");
  Link linkNotCorrect = new Link("Torgrim", "Torgrim");

  @Test
  void correctEqualsTest(){
    boolean expectedVal = true;
    boolean returnedVal = linkTest.equals(linkCorrect);
    assertEquals(expectedVal,returnedVal);
  }

  @Test
  void partialCorrectEqualsTest(){
    boolean expectedVal = true;
    boolean returnedVal = linkTest.equals(linkPartialCorrect);
    assertEquals(expectedVal,returnedVal);
  }

  @Test
  void notCorrectEqualsTest(){
    boolean expectedVal = false;
    boolean returnedVal = linkTest.equals(linkNotCorrect);
    assertEquals(expectedVal,returnedVal);
  }
}
