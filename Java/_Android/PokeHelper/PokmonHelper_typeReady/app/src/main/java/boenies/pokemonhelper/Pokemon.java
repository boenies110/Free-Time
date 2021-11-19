package boenies.pokemonhelper;

import java.util.ArrayList;
import java.util.List;

class Pokemon {
    int dexNr;
    String name;
    String type1;
    String type2;

    private final float[][] typeMatrix = {
            {1.0f,1.0f,1.0f,1.0f,1.0f,0.5f,1.0f,0.0f,0.5f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f},
            {2.0f,1.0f,0.5f,0.5f,1.0f,2.0f,0.5f,0.0f,2.0f,1.0f,1.0f,1.0f,1.0f,0.5f,2.0f,1.0f,2.0f,0.5f},
            {1.0f,2.0f,1.0f,1.0f,1.0f,0.5f,2.0f,1.0f,0.5f,1.0f,1.0f,2.0f,0.5f,1.0f,1.0f,1.0f,1.0f,1.0f},
            {1.0f,1.0f,1.0f,0.5f,0.5f,0.5f,1.0f,0.5f,0.0f,1.0f,1.0f,2.0f,1.0f,1.0f,1.0f,1.0f,1.0f,2.0f},
            {1.0f,1.0f,0.0f,2.0f,1.0f,2.0f,0.5f,1.0f,2.0f,2.0f,1.0f,0.5f,2.0f,1.0f,1.0f,1.0f,1.0f,1.0f},
            {1.0f,0.5f,2.0f,1.0f,0.5f,1.0f,2.0f,1.0f,0.5f,2.0f,1.0f,1.0f,1.0f,1.0f,2.0f,1.0f,1.0f,1.0f},
            {1.0f,0.5f,0.5f,0.5f,1.0f,1.0f,1.0f,0.5f,0.5f,0.5f,1.0f,2.0f,1.0f,2.0f,1.0f,1.0f,2.0f,0.5f},
            {0.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,2.0f,1.0f,1.0f,1.0f,1.0f,1.0f,2.0f,1.0f,1.0f,0.5f,1.0f},
            {1.0f,1.0f,1.0f,1.0f,1.0f,2.0f,1.0f,1.0f,0.5f,0.5f,0.5f,1.0f,0.5f,1.0f,2.0f,1.0f,1.0f,2.0f},
            {1.0f,1.0f,1.0f,1.0f,1.0f,0.5f,2.0f,1.0f,2.0f,0.5f,0.5f,2.0f,1.0f,1.0f,2.0f,0.5f,1.0f,1.0f},
            {1.0f,1.0f,1.0f,1.0f,2.0f,2.0f,1.0f,1.0f,1.0f,2.0f,0.5f,0.5f,1.0f,1.0f,1.0f,0.5f,1.0f,1.0f},
            {1.0f,1.0f,0.5f,0.5f,2.0f,2.0f,0.5f,1.0f,0.5f,0.5f,2.0f,0.5f,1.0f,1.0f,1.0f,0.5f,1.0f,1.0f},
            {1.0f,1.0f,2.0f,1.0f,0.0f,1.0f,1.0f,1.0f,1.0f,1.0f,2.0f,0.5f,0.5f,1.0f,1.0f,0.5f,1.0f,1.0f},
            {1.0f,2.0f,1.0f,2.0f,1.0f,1.0f,1.0f,1.0f,0.5f,1.0f,1.0f,1.0f,1.0f,0.5f,1.0f,1.0f,0.0f,1.0f},
            {1.0f,1.0f,2.0f,1.0f,2.0f,1.0f,1.0f,1.0f,0.5f,0.5f,0.5f,2.0f,1.0f,1.0f,0.5f,2.0f,1.0f,1.0f},
            {1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,0.5f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,2.0f,1.0f,0.0f},
            {1.0f,0.5f,1.0f,1.0f,1.0f,1.0f,1.0f,2.0f,1.0f,1.0f,1.0f,1.0f,1.0f,2.0f,1.0f,1.0f,0.5f,0.5f},
            {1.0f,2.0f,1.0f,0.5f,1.0f,1.0f,1.0f,1.0f,0.5f,0.5f,1.0f,1.0f,1.0f,1.0f,1.0f,2.0f,2.0f,1.0f}
    };

    public Pokemon(int dexNr, String name, String type1, String type2) {
        this.dexNr = dexNr;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
    }

    public int getColumnOfMatrix(String type){
        return 0;
    }

    public List<String> getWeaknesses_4x(){
        if (type2.isEmpty()){
            return null;
        }
        else {
            float weaknessType1 = getColumnOfMatrix(this.type1),
                    weaknessType2 = getColumnOfMatrix(this.type2);
        }

        return null;
    }
}
