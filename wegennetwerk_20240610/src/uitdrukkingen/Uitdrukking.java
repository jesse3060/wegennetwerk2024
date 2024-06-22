package uitdrukkingen;

import java.util.function.Consumer;

public abstract class Uitdrukking {

	/*
	 * Behavioral Subtyping: iets met super klasse en dat die methode andes wordt geimplementeerd afhv het 
	 * child type 
	 */
	/**
	 * @throws IllegalArgumentException | naam == null
	 */
	public abstract int aantalVoorkomensVan(String naam);
	
	public abstract UitdrukkingIterator<Uitdrukking> deelUitdrukkingIterator();
	
	public abstract void forEachVariabeleUitdrukking(Consumer<? super Uitdrukking> consumer);
	
}
