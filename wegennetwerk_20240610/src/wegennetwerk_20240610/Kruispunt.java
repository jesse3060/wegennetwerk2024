package wegennetwerk_20240610;

import java.util.HashSet;
import java.util.Set;

public class Kruispunt {
	/**
	 * @representationObject
	 * @peerObjects
	 * @invar | aankomendeWegen != null
	 * @invar | aankomendeWegen.stream().allMatch(w -> w != null && w.eind == this)
	 */
	Set<Weg> aankomendeWegen;
	/**
	 * @representationObject
	 * @peerObjects
	 * @invar | vertrekkendeWegen != null
	 * @invar | vertrekkendeWegen.stream().allMatch(w -> w != null && w.start == this)
	 */
	Set<Weg> vertrekkendeWegen;
	
	/**
	 * @post | getAankomendeWegen().isEmpty()
	 * @post | getVertrekkendeWegen().isEmpty()
	 */
	public Kruispunt() {
		aankomendeWegen = new HashSet<>();
		vertrekkendeWegen = new HashSet<>();
	}
	
	/**
	 * @creates | result
	 * @peerObjects
	 */
	public Set<Weg> getAankomendeWegen() {
		return Set.copyOf(aankomendeWegen);
	}
	
	/**
	 * @creates | result
	 * @peerObjects
	 */
	public Set<Weg> getVertrekkendeWegen() {
		return Set.copyOf(vertrekkendeWegen);
	}
	
	

}
