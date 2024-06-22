package uitdrukkingen.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import uitdrukkingen.*;

public class UitdrukkingenTest {

	@Test
	public void test() {
		
		LetterlijkeUitdrukking n69 = new LetterlijkeUitdrukking(69);
		assertEquals(69, n69.getWaarde());
		assertEquals(0, n69.aantalVoorkomensVan("y"));
		
		VariabeleUitdrukking x = new VariabeleUitdrukking("x");
		assertEquals("x", x.getNaam());
		assertEquals(0, x.aantalVoorkomensVan("y"));
		assertEquals(1, x.aantalVoorkomensVan("x"));
		
		Optelling som = new Optelling(x, n69); // som = x + 69
		assertEquals(x, som.getLinkerUitdrukking()); 
		assertEquals(n69, som.getRechterUitdrukking()); 
		assertEquals(1, som.aantalVoorkomensVan("x"));
		assertEquals(0, som.aantalVoorkomensVan("y"));

		
		// equals teste,
		VariabeleUitdrukking x2 = new VariabeleUitdrukking("x");
		assertEquals(x2, som.getLinkerUitdrukking()); // test overriden equals method
		LetterlijkeUitdrukking n692 = new LetterlijkeUitdrukking(69);
		assertEquals(n692, som.getRechterUitdrukking());
		
		// TESTING ITERATOR
		HashSet<Uitdrukking> testletterlijk = new HashSet<>();
		UitdrukkingIterator<Uitdrukking> iterator = n69.deelUitdrukkingIterator();
		while(iterator.hasNext()) {
			testletterlijk.add(iterator.next());
		}
		assertEquals(Set.of(), testletterlijk);
		
		HashSet<Uitdrukking> testvariabel = new HashSet<>();
		UitdrukkingIterator<Uitdrukking> variterator = x.deelUitdrukkingIterator();
		while(variterator.hasNext()) {
			testvariabel.add(variterator.next());
		}
		assertEquals(Set.of(), testvariabel);
		
		HashSet<Uitdrukking> testsom = new HashSet<>();
		UitdrukkingIterator<Uitdrukking> somiterator = som.deelUitdrukkingIterator();
		while(somiterator.hasNext()) {
			testsom.add(somiterator.next());
		}
		assertEquals(Set.of(x, n69), testsom);
		
		//TESTING CONSUMER FOR VAROPERATIES
		HashSet<Uitdrukking> testvarconsumer = new HashSet<>();
		som.forEachVariabeleUitdrukking(v -> testvarconsumer.add(v));
		assertEquals(Set.of(x), testvarconsumer);
		
		testvarconsumer.clear();
		x2.forEachVariabeleUitdrukking(v -> testvarconsumer.add(v));
		assertEquals(Set.of(x2), testvarconsumer);
		
		testvarconsumer.clear();
		n69.forEachVariabeleUitdrukking(v -> testvarconsumer.add(v));
		assertEquals(Set.of(), testvarconsumer);

	}
}
