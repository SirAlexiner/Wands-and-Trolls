import nl.jqno.equalsverifier.EqualsVerifier;
import no.ntnu.idatg2001.gr13.Link;
import org.junit.jupiter.api.Test;

class HashCodeContractTest {
  @Test
  void equalsHashCodeContracts() {
    EqualsVerifier.forClass(Link.class)
        .withIgnoredFields("actions")
        .verify();
  }
}
