package com.mycompany.millionaire.dsa;

import com.mycompany.millionaire.model.GameRound;
import java.util.Stack;

public class GameHistoryStack {

    private final Stack<GameRound> answeredRounds = new Stack<>();

    public void push(GameRound round) {
        answeredRounds.push(round);
    }

    public int size() {
        return answeredRounds.size();
    }

    public boolean isEmpty() {
        return answeredRounds.isEmpty();
    }

    public GameRound peek() {
        return answeredRounds.peek();
    }
}
