package de.boenies;
import java.util.Random;


public class Main {

    public static void main(String[] args) {
        long seed = -7365433992806290166L;

        Random rnd;

        for(int xPosition = -5; xPosition<5; ++xPosition) {
            for(int zPosition=-5; zPosition<5; ++zPosition){
                 rnd = new Random(
                        seed +
                                (int) (xPosition * xPosition * 0x4c1906) +
                                (int) (xPosition * 0x5ac0db) +
                                (int) (zPosition * zPosition) * 0x4307a7L +
                                (int) (zPosition * 0x5f24f) ^ 0x3ad8025fL
                );

                if((rnd.nextInt(10) == 0)) {
                    System.out.println("x = " + xPosition + "; "
                            + "y = " + zPosition + "; "
                    );
                }
            }
        }


    }
}
