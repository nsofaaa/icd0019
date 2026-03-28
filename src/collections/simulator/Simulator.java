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
        double wins = 0;
        List<Card> deck = createFullDeck();

        for (Card c : p1) {
            deck.remove(c);
        }
        for (Card c : p2) {
            deck.remove(c);
        }

        for (int i = 0; i < iterations; i++) {

            List<Card> currentDeck = new ArrayList<>(deck);
            Collections.shuffle(currentDeck);

            Hand h1 = new Hand();
            Hand h2 = new Hand();

            for (Card c : p1) {
                h1.addCard(c);
            }
            for (Card c : p2) {
                h2.addCard(c);
            }

            for (int j = 0; j < 5; j++) {
                Card tableCard = currentDeck.get(j);
                h1.addCard(tableCard);
                h2.addCard(tableCard);
            }

            Hand best1 = getBestHand(h1);
            Hand best2 = getBestHand(h2);

            int cmp = best1.compareTo(best2);

            if (cmp > 0) {
                wins += 1;
            } else if (cmp == 0) {
                wins += 0.5;
            }
        }

        return wins / iterations * 100;
    }


    private Hand getBestHand(Hand hand) {
        List<Card> cards = new ArrayList<>();
        for (Card c : hand) {
            cards.add(c);
        }

        Hand best = null;

        for (int i = 0; i < cards.size(); i++) {
            for (int j = i + 1; j < cards.size(); j++) {
                for (int k = j + 1; k < cards.size(); k++) {
                    for (int l = k + 1; l < cards.size(); l++) {
                        for (int m = l + 1; m < cards.size(); m++) {

                            Hand temp = new Hand();
                            temp.addCard(cards.get(i));
                            temp.addCard(cards.get(j));
                            temp.addCard(cards.get(k));
                            temp.addCard(cards.get(l));
                            temp.addCard(cards.get(m));

                            if (best == null || temp.compareTo(best) > 0) {
                                best = temp;
                            }
                        }
                    }
                }
            }
        }

        return best;
    }
}