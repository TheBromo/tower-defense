package ch.zhaw.team5;

public interface IsObservable {
	/**
	 * Add an observer that listens for updates
	 * 
	 * @param observer
	 */
	void addListener(IsObserver observer);

	/**
	 * Remove an observer from the list
	 * 
	 * @param observer
	 */
	void removeListener(IsObserver observer);
}
