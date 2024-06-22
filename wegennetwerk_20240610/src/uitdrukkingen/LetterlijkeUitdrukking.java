package uitdrukkingen;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * @immutable
 */
public class LetterlijkeUitdrukking extends Uitdrukking {

	private int waarde;
	
	/**
	 * @post | getWaarde() == waarde
	 */
	public LetterlijkeUitdrukking(int waarde) {
		this.waarde = waarde;
	}
	
	
	public int getWaarde() {
		return this.waarde;
	}


	/**
	 * @throws IllegalArgumentException | naam == null
	 * @post | result == 0
	 */
	@Override
	public int aantalVoorkomensVan(String naam) {
		if (naam == null)
			throw new IllegalArgumentException("Naam can't be null");
		return 0;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof LetterlijkeUitdrukking uitdr)
			return uitdr.getWaarde() == this.getWaarde();
		return false;
	}


	@Override
	public UitdrukkingIterator<Uitdrukking> deelUitdrukkingIterator() {
		return new UitdrukkingIterator<Uitdrukking>() {

			@Override
			public boolean hasNext() {
				return false;
			}

			@Override
			public Uitdrukking next() {
				return null;
			}
			
		};
	}


	@Override
	public void forEachVariabeleUitdrukking(Consumer<? super Uitdrukking> consumer) {
		return;
	}
	
}
