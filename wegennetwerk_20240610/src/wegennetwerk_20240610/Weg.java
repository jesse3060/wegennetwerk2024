package wegennetwerk_20240610;

import java.util.Set;
import java.util.stream.Collectors;

import logicalcollections.LogicalSet;

/**
 * @invar | getStartKruispunt() == null || getStartKruispunt().getVertrekkendeWegen().contains(this)
 * @invar | getEindKruispunt() == null || getEindKruispunt().getAankomendeWegen().contains(this)
 */
public class Weg {
	/**
	 * @peerObject 
	 * @invar | start == null || start.vertrekkendeWegen.contains(this)
	 * 
	 */
	Kruispunt start;
	/**
	 * @peerObject
	 * @invar | eind == null || eind.aankomendeWegen.contains(this)
	 */
	Kruispunt eind;
	
	public Weg() {}
	
	/**
	 * @peerObject
	 */
	public Kruispunt getStartKruispunt() {
		return start;
	}
	
	/**
	 * @peerObject
	 */
	public Kruispunt getEindKruispunt() {
		return eind;
	}
	
	/**
	 * @throws IllegalArgumentException | kp == null
	 * @throws IllegalArgumentException | getStartKruispunt() != null
	 * 
	 * @mutates_properties | getStartKruispunt(), kp.getVertrekkendeWegen()
	 * @post | getStartKruispunt() == kp
	 * @post | kp.getVertrekkendeWegen().equals(LogicalSet.plus(old(kp.getVertrekkendeWegen()), this))
	 */
	public void setStartKruispunt(Kruispunt kp) {
		if (kp == null) {
			throw new IllegalArgumentException("'kruispunt' mag niet null zijn");
		}
		if (this.start != null) {
			throw new IllegalArgumentException("Startpunt al gekoppeld");
		}
		this.start = kp;
		kp.vertrekkendeWegen.add(this);
	}
	
	/**
	 * @pre | kp != null
	 * @pre | getEindKruispunt() == null
	 *
	 * @mutates_properties | getEindKruispunt(), kp.getAankomendeWegen()
	 * @post | getEindKruispunt() == kp
	 * @post | kp.getAankomendeWegen().equals(LogicalSet.plus(old(kp.getAankomendeWegen()), this))
	 */
	public void setEindKruispunt(Kruispunt kp) {
		this.eind = kp;
		kp.aankomendeWegen.add(this);
	}
	
	/**
	 * @pre | getStartKruispunt() != null
	 * 
	 * @mutates_properties | getStartKruispunt(), getStartKruispunt().getVertrekkendeWegen()
	 * @post | getStartKruispunt() == null
	 * @post | old(getStartKruispunt()).getVertrekkendeWegen().equals(LogicalSet.minus(old(getStartKruispunt().getVertrekkendeWegen()), this))
	 */
	public void ontkoppelStart() {
		this.start.vertrekkendeWegen.remove(this);
		this.start = null;
	}
	
	/**
	 * @pre | getEindKruispunt() != null
	 * 
	 * @mutates_properties | getEindKruispunt(), getEindKruispunt().getAankomendeWegen()
	 * @post | getEindKruispunt() == null
	 * @post | old(getEindKruispunt()).getAankomendeWegen().equals(LogicalSet.minus(old(getEindKruispunt().getAankomendeWegen()), this))
	 */
	public void ontkoppelEind() {
		this.eind.aankomendeWegen.remove(this);
		this.eind = null;
	}
	
	public Set<Weg> getTweedeOrdeWegen() {
		return eind.getVertrekkendeWegen().stream().flatMap(weg -> weg.getEindKruispunt().getVertrekkendeWegen().stream()).collect(Collectors.toSet());
	}

}
