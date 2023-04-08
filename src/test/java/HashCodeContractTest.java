import nl.jqno.equalsverifier.EqualsVerifier;
import no.ntnu.idatg2001.gr13.Link;
import no.ntnu.idatg2001.gr13.Passage;
import org.junit.jupiter.api.Test;

class HashCodeContractTest {
  @Test
  void equalsHashCodeContracts() {
    EqualsVerifier.forClass(Link.class)
        .withOnlyTheseFields("reference")
        .verify();
  }

  @Test
  void equalsAndHashCodeForPassageObjectTest() {
    EqualsVerifier.forClass(Passage.class)
            .withOnlyTheseFields("title")
            .usingGetClass()
            .verify();
  }
}
