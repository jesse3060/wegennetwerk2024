package uitdrukkingen;

import java.util.function.Consumer;

/**
 * @immutable
 * @invar | getLinkerUitdrukking() != null
 * @invar | getRechterUitdrukking() != null
 */
public class Optelling extends Uitdrukking {
	
	/**
	 * @invar | linkerUitdrukking != null
	 * @invar | rechterUitdrukking != null
	 */
	private Uitdrukking linkerUitdrukking;
	private Uitdrukking rechterUitdrukking;
	
	/**
	 * @throws IllegalArgumentException | links == null
	 * @throws IllegalArgumentException | rechts == null
	 * @post | getLinkerUitdrukking() == links
	 * @post | getRechterUitdrukking() == rechts
	 */
	public Optelling(Uitdrukking links, Uitdrukking rechts) {
		if (links == null || rechts == null) {
			throw new IllegalArgumentException("'links' en 'rechts' mogen niet null zijn");
		}
		this.linkerUitdrukking = links;
		this.rechterUitdrukking = rechts;
	}
	
	public Uitdrukking getLinkerUitdrukking() {
		return linkerUitdrukking;
	}
	
	public Uitdrukking getRechterUitdrukking() {
		return rechterUitdrukking;
	}

	/**
	 * @throws IllegalArgumentException | naam == null
	 * @post | result == getLinkerUitdrukking().aantalVoorkomensVan(naam) + getRechterUitdrukking().aantalVoorkomensVan(naam)
	 */
	@Override
	public int aantalVoorkomensVan(String naam) {
		if (naam == null)
			throw new IllegalArgumentException("'naam' can't be null");
		return linkerUitdrukking.aantalVoorkomensVan(naam) + rechterUitdrukking.aantalVoorkomensVan(naam);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Optelling uitdr)
			return uitdr.getLinkerUitdrukking().equals(this.getLinkerUitdrukking())
					&& uitdr.getRechterUitdrukking().equals(this.getRechterUitdrukking());
		return false;
	}
	@Override
	public UitdrukkingIterator<Uitdrukking> deelUitdrukkingIterator() {
		Uitdrukking links = getLinkerUitdrukking();
		Uitdrukking rechts = getRechterUitdrukking();
		
		return new UitdrukkingIterator<Uitdrukking>() {
			Uitdrukking[] deeluitdrukkingen = new Uitdrukking[] {links, rechts};
			int teller = 0;
			@Override
			public boolean hasNext() {
				return teller < 2;
			}

			@Override
			public Uitdrukking next() {
				
				return deeluitdrukkingen[teller++];
			}
			
		};
	}

	@Override
	public void forEachVariabeleUitdrukking(Consumer<? super Uitdrukking> consumer) {
		linkerUitdrukking.forEachVariabeleUitdrukking(consumer);
		rechterUitdrukking.forEachVariabeleUitdrukking(consumer);
	}

}
