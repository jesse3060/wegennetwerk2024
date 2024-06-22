package uitdrukkingen;

import java.util.function.Consumer;

/**
 * @immutable
 * @invar | getNaam() != null
 */
public class VariabeleUitdrukking extends Uitdrukking {

	/**
	 * @invar | naam != null
	 */
	private String naam;
	
	/**
	 * @throws IllegalArgumentException | naam == null
	 * @post | getNaam().equals(naam)
	 */
	public VariabeleUitdrukking(String naam) {
		if (naam == null)
			throw new IllegalArgumentException("nope");
		this.naam = naam;
	}
	
	public String getNaam() {
		return naam;
	}

	/**
	 * @throws IllegalArgumentException | naam == null
	 * @post | result == ((naam.equals(getNaam())) ? 1 : 0)
	 */
	@Override
	public int aantalVoorkomensVan(String naam) {
		if (naam == null)
			throw new IllegalArgumentException("'naam' cant be null");
		return naam.equals(this.naam) ? 1 : 0;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof VariabeleUitdrukking uitdr)
			return uitdr.getNaam().equals(this.naam);
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
		consumer.accept(this);
	}
}
