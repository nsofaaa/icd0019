package collections.simulator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;


public class Hand implements Iterable<Card>, Comparable<Hand> {

    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public String toString() {
        return cards.toString();
    }

    public HandType getHandType() {
        Collections.sort(cards);

        if (isFlush() && isStraight()) {
            return HandType.STRAIGHT_FLUSH;
        }
        if (hasCount(4)) {
            return HandType.FOUR_OF_A_KIND;
        }
        if (hasCount(3) && hasCount(2)) {
            return HandType.FULL_HOUSE;
        }
        if (isFlush()) {
            return HandType.FLUSH;
        }
        if (isStraight()) {
            return HandType.STRAIGHT;
        }
        if (hasCount(3)) {
            return HandType.TRIPS;
        }
        if (countPairs() == 2) {
            return HandType.TWO_PAIRS;
        }
        if (hasCount(2)) {
            return HandType.ONE_PAIR;
        }

        return HandType.HIGH_CARD;
    }

    private boolean hasCount(int need) {
        for (Card c1 : cards) {
            int count = 0;
            for (Card c2 : cards) {
                if (c1.getValue() == c2.getValue()) {
                    count++;
                }
            }
            if (count == need) {
                return true;
            }
        }
        return false;
    }

    private int countPairs() {
        int pairs = 0;
        List<Card.CardValue> checked = new ArrayList<>();
        for (Card c : cards) {
            if (checked.contains(c.getValue())) {
                continue;
            }
            int count = 0;
            for (Card c2 : cards) {
                if (c.getValue() == c2.getValue()) {
                    count++;
                }
            }
            if (count == 2) {
                pairs++;
                checked.add(c.getValue());
            }
        }
        return pairs;
    }

    private boolean isFlush() {
        if (cards.size() < 5) {
            return false;
        }
        Card.CardSuit first = cards.getFirst().getSuit();
        for (Card c : cards) {
            if (c.getSuit() != first) {
                return false;
            }
        }
        return true;
    }

    private boolean isStraight() {
        if (cards.size() < 5) {
            return false;
        }

        boolean normal = true;
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).getValue().ordinal() + 1 != cards.get(i + 1).getValue().ordinal()) {
                normal = false;
                break;
            }
        }
        if (normal) {
            return true;
        }

        return cards.get(4).getValue() == Card.CardValue.A &&
                cards.get(0).getValue() == Card.CardValue.S2 &&
                cards.get(1).getValue() == Card.CardValue.S3 &&
                cards.get(2).getValue() == Card.CardValue.S4 &&
                cards.get(3).getValue() == Card.CardValue.S5;
    }

    public boolean contains(Card card) {
        return cards.contains(card);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    @Override
    public int compareTo(Hand other) {
        // сначала тип руки
        int res = this.getHandType().compareTo(other.getHandType());
        if (res != 0) {
            return res;
        }

        // создаём список значений по старшинству (просто по количеству повторов + значение)
        List<Card.CardValue> thisValues = new ArrayList<>();
        List<Card.CardValue> otherValues = new ArrayList<>();

        for (Card c : this.cards) thisValues.add(c.getValue());
        for (Card c : other.cards) otherValues.add(c.getValue());

        // простая сортировка: сначала по количеству повторов, затем по старшей карте
        thisValues.sort((a, b) -> {
            int countA = 0, countB = 0;
            for (Card.CardValue x : thisValues) { if (x == a) countA++; }
            for (Card.CardValue x : otherValues) { if (x == b) countB++; }
            if (countA != countB) return countB - countA;
            return b.ordinal() - a.ordinal();
        });

        otherValues.sort((a, b) -> {
            int countA = 0, countB = 0;
            for (Card.CardValue x : otherValues) { if (x == a) countA++; }
            for (Card.CardValue x : thisValues) { if (x == b) countB++; }
            if (countA != countB) return countB - countA;
            return b.ordinal() - a.ordinal();
        });

        for (int i = 0; i < thisValues.size(); i++) {
            int cmp = thisValues.get(i).ordinal() - otherValues.get(i).ordinal();
            if (cmp != 0) return cmp;
        }

        return 0;
    }
}