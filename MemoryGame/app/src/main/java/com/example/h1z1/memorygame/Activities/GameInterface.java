package com.example.h1z1.memorygame.Activities;

public interface GameInterface {
    public static enum LEVELS {
        EASY(3), MEDIUM(4), HARD(5);

        private final int value;
        private LEVELS(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


}
