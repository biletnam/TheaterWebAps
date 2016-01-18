package theater.domein;
/**
 * Representeert een plaats 
 * @author Open Universiteit
 *
 */
public class Plaats {
  private int rijnummer = 0;
  private int stoelnummer = 0;

  public Plaats(int rijnummer, int stoelnummer) {
    super();
    this.rijnummer = rijnummer;
    this.stoelnummer = stoelnummer;
  }

  public int getRijnummer() {
    return rijnummer;
  }

  public int getStoelnummer() {
    return stoelnummer;
  }

}
