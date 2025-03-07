package com.hcring.dataStructureAndAlgorithms.deque;

import java.util.*;


public class Practice2 {

    public String solution(String command, Integer[] array) {
      Deque<Integer> deque = new ArrayDeque<>(Arrays.asList(array));
        boolean isRight = true;

        for(char c : command.toCharArray()) {
            if(c == 'R') {
                isRight = !isRight;
            } else if(c == 'D') {
                if (isRight)
                    deque.removeFirst();
                else
                    deque.removeLast();
            } else if(deque.isEmpty())
                return "error";
        }

        List<Integer> result = new ArrayList<>(deque);
        if (!isRight) {
            Collections.reverse(result);
        }

        return result.toString();
    }
}