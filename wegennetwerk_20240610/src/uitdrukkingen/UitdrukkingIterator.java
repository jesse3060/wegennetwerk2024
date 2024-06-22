package uitdrukkingen;

public abstract class UitdrukkingIterator<T extends Uitdrukking> {
	
	public abstract boolean hasNext();
	
	public abstract T next();

}
