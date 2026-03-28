package collections.simulator;

import java.util.EnumMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Simulator {

    private final double iterations;

    public Simulator(double iterations) {
        this.iterations = iterations;
    }

    public Map<HandType, Double> calculateProbabilities() {

        // use special map when using enum keys
        Map<HandType, Double> results = new EnumMap<>(HandType.class);
        for (HandType type : HandType.values()) {
            results.put(type, 0.0);
        }

        List<Card> deck = createFullDeck();

        for (int i = 0; i < iterations; i++) {
            Collections.shuffle(deck);
            Hand hand = new Hand();
            for (int j = 0; j < 5; j++) {
                hand.addCard(deck.get(j));
            }
            HandType type = hand.getHandType();
            results.put(type, results.get(type) + 1);
        }

        for (HandType type : HandType.values()) {
            results.put(type, results.get(type) / iterations * 100);
        }

        return results;
    }

    private List<Card> createFullDeck() {
        List<Card> deck = new ArrayList<>();
        for (Card.CardSuit s : Card.CardSuit.values()) {
            for (Card.CardValue v : Card.CardValue.values()) {
                deck.add(new Card(v, s));
            }
        }
        return deck;
    }

    public double getWinningOdds(Hand p1, Hand p2) {
        int wins = 0;
        List<Card> deck = createFullDeck();
        for (Card c : p1) {
            deck.remove(c);
        }
        for (Card c : p2) {
            deck.remove(c);
        }

        for (int i = 0; i < iterations; i++) {
            Collections.shuffle(deck);
            Hand h1 = new Hand();
            Hand h2 = new Hand();
            for (Card c : p1) {
                h1.addCard(c);
            }
            for (Card c : p2) {
                h2.addCard(c);
            }

            for (int j = 0; j < 5; j++) {
                h1.addCard(deck.get(j));
                h2.addCard(deck.get(j));
            }

            if (h1.compareTo(h2) > 0) {
                wins++;
            }
        }
        return wins / iterations * 100;
    }
}