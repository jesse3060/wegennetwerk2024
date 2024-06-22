package testwegen;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import wegennetwerk_20240610.Kruispunt;
import wegennetwerk_20240610.Weg;

public class WegenTest {

	
	@Test
	public void test() {
		
		Weg bosstraat = new Weg();
		assertNull(bosstraat.getStartKruispunt());
		assertNull(bosstraat.getEindKruispunt());
		
		Kruispunt kp1 = new Kruispunt();
		assertEquals(Set.of(), kp1.getAankomendeWegen());
		assertEquals(Set.of(), kp1.getVertrekkendeWegen());
		
		Kruispunt kp2 = new Kruispunt();
		
		bosstraat.setStartKruispunt(kp1);
		assertEquals(kp1, bosstraat.getStartKruispunt());
		assertEquals(Set.of(bosstraat), kp1.getVertrekkendeWegen());
		
		bosstraat.setEindKruispunt(kp2);
		assertEquals(kp2, bosstraat.getEindKruispunt());
		assertEquals(Set.of(bosstraat), kp2.getAankomendeWegen());
		
		bosstraat.ontkoppelEind();
		assertNull(bosstraat.getEindKruispunt());
		assertEquals(Set.of(), kp2.getAankomendeWegen());
		
		Kruispunt kp3 = new Kruispunt();
		bosstraat.setEindKruispunt(kp3);
		
		bosstraat.ontkoppelStart();
		assertEquals(Set.of(), kp1.getVertrekkendeWegen());
		bosstraat.setStartKruispunt(kp2);
		
		// TEST 2e ORDE WEG
		Kruispunt kp4 = new Kruispunt();
		Weg oudebaan = new Weg();
		Weg wittewijk = new Weg();
		Weg dorpstraat = new Weg();
		Weg alsemberg = new Weg();
		quickKoppel(oudebaan, kp1, kp2);
		quickKoppel(wittewijk, kp1, kp3);
		quickKoppel(alsemberg, kp3, kp4);
		quickKoppel(dorpstraat, kp4, kp1);
		
		assertEquals(Set.of(alsemberg), oudebaan.getTweedeOrdeWegen());
		assertEquals(Set.of(oudebaan, wittewijk), alsemberg.getTweedeOrdeWegen());

	}
	
	
	private void quickKoppel(Weg weg, Kruispunt start, Kruispunt eind) {
		weg.setStartKruispunt(start);
		weg.setEindKruispunt(eind);
	}
}

