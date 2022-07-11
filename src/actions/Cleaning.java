package actions;

import agents.VacuumCleaner;
import environment.Square;

public class Cleaning {

	// These methods are used when the vacuum cleaner knows its location
	/**
	 * Cleans the square where the vacuum cleaner is located.
	 * @param vc The vacuum cleaner which is cleaning
	 * @param effectiveness The amount of dirt that will be removed
	 */
	public static void cleanSquare(VacuumCleaner vc) {
		Square s = vc.getLocation();
		s.setDirtLevel(Math.max(0, s.getDirtLevel()-vc.getEffectiveness()));
	}

	/**
	 * Reveals the dirt state of the vacuum cleaner's location
	 * @param vc The vacuum cleaner which is cleaning
	 * @param effectiveness The amount of dirt that will be removed
	 * @return
	 */
	public static boolean isDirty(VacuumCleaner vc) {
		return vc.getLocation().getDirtLevel() > vc.getDirtLevelAutorized();
	}

}
