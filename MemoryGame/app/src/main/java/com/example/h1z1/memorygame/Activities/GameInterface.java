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

    public final static int DELAY_1000MS=1000;

    public final static int HARD_TIMER=60;
    public final static int MEDIUM_TIMER=45;
    public final static int EASY_TIMER=5;
    public final static int BOARD_WIDTH=4;
    public final static int ZERO=0;



    public static int[] animals = {R.drawable.animals_1,R.drawable.animals_2,R.drawable.animals_3,
            R.drawable.animals_4,R.drawable.animals_5,R.drawable.animals_6,
            R.drawable.animals_7,R.drawable.animals_8,R.drawable.animals_9,
            R.drawable.animals_10 };


}
