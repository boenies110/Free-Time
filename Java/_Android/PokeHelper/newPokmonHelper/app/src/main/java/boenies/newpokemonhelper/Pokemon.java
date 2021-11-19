package boenies.newpokemonhelper;

class Pokemon {
    String name;
    private String type1; private int type1_row;
    private String type2; private int type2_row;

    final static int COUNT_TYPE = 18;
    private final static float[][] typeMatrix = {
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
    final static String[] typeNames = {
            "Normal", "Kampf", "Flug", "Gift", "Boden", "Gestein", "Käfer", "Geist", "Stahl",
            "Feuer", "Wasser", "Pflanze", "Elektro", "Psycho", "Eis", "Drache", "Unlicht", "Fee"
    };

    public Pokemon(String name) {
        this.name = name;

        //this.type1 = type1;
        Pokedex Dex = new Pokedex();
        this.type1 = Dex.getDexEntry(this.name.toLowerCase())[1];
        this.type1_row = getTypeRow(this.type1);

        this.type2 = Dex.getDexEntry(this.name.toLowerCase())[2];
        this.type2_row = getTypeRow(this.type2);
    }

    public Pokemon(String type1, String type2) {
        this.name = "";

        this.type1 = type1;
        this.type2 = type2;
    }

    public int getTypeRow(String type){
        for (int i = 0; i < COUNT_TYPE; i++){
            if (type.equals(typeNames[i])){
                return i;
            }
        }

        return -1;
    }

    public float[] calcWeaknesses(String type1, String type2){
        float[] result = new float[COUNT_TYPE];

        for(int i = 0; i < COUNT_TYPE; i++){
            result[i] = typeMatrix[i][getTypeRow(type1)] * typeMatrix[i][getTypeRow(type2)];
        }

        return result;
    }

    public float[] calcWeaknesses(String type1){
        float[] result = new float[COUNT_TYPE];

        for(int i = 0; i < COUNT_TYPE; i++){
            result[i] = typeMatrix[i][getTypeRow(type1)];
        }

        return result;
    }

    public float[] calcWeaknesses(){
        if(this.type2.equals("")){
            return calcWeaknesses(this.type1);
        }
        //else if (this)
        return null;
    }

    public String[] getTypes() {
        String[] result = {this.type1,this.type2};

        return result;
    }
}
