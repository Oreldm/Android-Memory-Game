package com.example.h1z1.memorygame.Activities;

import com.example.h1z1.memorygame.R;

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

    public static int[] animals = {R.drawable.animals_1,R.drawable.animals_2,R.drawable.animals_3,
            R.drawable.animals_4,R.drawable.animals_5,R.drawable.animals_6,
            R.drawable.animals_7,R.drawable.animals_8,R.drawable.animals_9,
            R.drawable.animals_10 };


}
