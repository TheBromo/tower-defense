package ch.zhaw.team5;

import java.util.ArrayList;
import java.util.List;

import ch.zhaw.team5.model.Player;

public class PlayerDecorator implements IsObservable {

    private Player player;

    private final List<IsObserver> listener = new ArrayList<>();

    public PlayerDecorator(Player player) {
        this.player = player;
    }

    public void initialize() {
        informListener();
    }

    @Override
    public void addListener(IsObserver observer) {
        listener.add(observer);
    }

    @Override
    public void removeListener(IsObserver observer) {
        listener.remove(observer);
    }

    private void informListener() {
        for (IsObserver observer : listener) {
            observer.update();
        }
    }

}
