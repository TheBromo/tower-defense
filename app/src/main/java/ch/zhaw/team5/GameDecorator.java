package ch.zhaw.team5;

public class GameDecorator implements IsObservable {
    /*
     * 
     * private Game game;
     * 
     * private final List<IsObserver> listener = new ArrayList<>();
     * 
     * public GameDecorator(Game player) {
     * this.game = game;
     * }
     */

    @Override
    public void addListener(IsObserver observer) {
        // listener.add(observer);
    }

    @Override
    public void removeListener(IsObserver observer) {
        // listener.remove(observer);
    }

}
