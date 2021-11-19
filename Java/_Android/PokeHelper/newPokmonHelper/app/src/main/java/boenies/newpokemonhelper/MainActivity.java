package boenies.newpokemonhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    static final int COUNT_LINES = 16;

    Button btnCalcWeakness, btnClearTable, btnTest;

    TextView[] TVeryGoodResult = new TextView[COUNT_LINES];
    TextView[] TGoodResult = new TextView[COUNT_LINES];
    TextView[] TNormalResult = new TextView[COUNT_LINES];
    TextView[] TBadResult = new TextView[COUNT_LINES];

    EditText eTPokemonNameInput, eTType1, eTType2;

    Pokedex Dex = new Pokedex();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTest = (Button) findViewById(R.id.btnTest);
        btnTest.setOnClickListener(this);

        btnCalcWeakness = (Button) findViewById(R.id.btnCalcWeakness);
        btnCalcWeakness.setOnClickListener(this);

        btnClearTable = (Button) findViewById(R.id.btnClearTable);
        btnClearTable.setOnClickListener(this);

        eTPokemonNameInput = (EditText) findViewById(R.id.eTPokemonNameInput);
        eTType1 = (EditText) findViewById(R.id.eTType1);
        eTType2 = (EditText) findViewById(R.id.eTType2);

        {
            TVeryGoodResult[0] = (TextView) findViewById(R.id.TVeryGoodResult01);
            TVeryGoodResult[1] = (TextView) findViewById(R.id.TVeryGoodResult02);
            TVeryGoodResult[2] = (TextView) findViewById(R.id.TVeryGoodResult03);
            TVeryGoodResult[3] = (TextView) findViewById(R.id.TVeryGoodResult04);
            TVeryGoodResult[4] = (TextView) findViewById(R.id.TVeryGoodResult05);
            TVeryGoodResult[5] = (TextView) findViewById(R.id.TVeryGoodResult06);
            TVeryGoodResult[6] = (TextView) findViewById(R.id.TVeryGoodResult07);
            TVeryGoodResult[7] = (TextView) findViewById(R.id.TVeryGoodResult08);
            TVeryGoodResult[8] = (TextView) findViewById(R.id.TVeryGoodResult09);
            TVeryGoodResult[9] = (TextView) findViewById(R.id.TVeryGoodResult10);
            TVeryGoodResult[10] = (TextView) findViewById(R.id.TVeryGoodResult11);
            TVeryGoodResult[11] = (TextView) findViewById(R.id.TVeryGoodResult12);
            TVeryGoodResult[12] = (TextView) findViewById(R.id.TVeryGoodResult13);
            TVeryGoodResult[13] = (TextView) findViewById(R.id.TVeryGoodResult14);
            TVeryGoodResult[14] = (TextView) findViewById(R.id.TVeryGoodResult15);
            TVeryGoodResult[15] = (TextView) findViewById(R.id.TVeryGoodResult16);

            TGoodResult[0] = (TextView) findViewById(R.id.TGoodResult01);
            TGoodResult[1] = (TextView) findViewById(R.id.TGoodResult02);
            TGoodResult[2] = (TextView) findViewById(R.id.TGoodResult03);
            TGoodResult[3] = (TextView) findViewById(R.id.TGoodResult04);
            TGoodResult[4] = (TextView) findViewById(R.id.TGoodResult05);
            TGoodResult[5] = (TextView) findViewById(R.id.TGoodResult06);
            TGoodResult[6] = (TextView) findViewById(R.id.TGoodResult07);
            TGoodResult[7] = (TextView) findViewById(R.id.TGoodResult08);
            TGoodResult[8] = (TextView) findViewById(R.id.TGoodResult09);
            TGoodResult[9] = (TextView) findViewById(R.id.TGoodResult10);
            TGoodResult[10] = (TextView) findViewById(R.id.TGoodResult11);
            TGoodResult[11] = (TextView) findViewById(R.id.TGoodResult12);
            TGoodResult[12] = (TextView) findViewById(R.id.TGoodResult13);
            TGoodResult[13] = (TextView) findViewById(R.id.TGoodResult14);
            TGoodResult[14] = (TextView) findViewById(R.id.TGoodResult15);
            TGoodResult[15] = (TextView) findViewById(R.id.TGoodResult16);

            TNormalResult[0] = (TextView) findViewById(R.id.TNormalResult01);
            TNormalResult[1] = (TextView) findViewById(R.id.TNormalResult02);
            TNormalResult[2] = (TextView) findViewById(R.id.TNormalResult03);
            TNormalResult[3] = (TextView) findViewById(R.id.TNormalResult04);
            TNormalResult[4] = (TextView) findViewById(R.id.TNormalResult05);
            TNormalResult[5] = (TextView) findViewById(R.id.TNormalResult06);
            TNormalResult[6] = (TextView) findViewById(R.id.TNormalResult07);
            TNormalResult[7] = (TextView) findViewById(R.id.TNormalResult08);
            TNormalResult[8] = (TextView) findViewById(R.id.TNormalResult09);
            TNormalResult[9] = (TextView) findViewById(R.id.TNormalResult10);
            TNormalResult[10] = (TextView) findViewById(R.id.TNormalResult11);
            TNormalResult[11] = (TextView) findViewById(R.id.TNormalResult12);
            TNormalResult[12] = (TextView) findViewById(R.id.TNormalResult13);
            TNormalResult[13] = (TextView) findViewById(R.id.TNormalResult14);
            TNormalResult[14] = (TextView) findViewById(R.id.TNormalResult15);
            TNormalResult[15] = (TextView) findViewById(R.id.TNormalResult16);

            TBadResult[0] = (TextView) findViewById(R.id.TBadResult01);
            TBadResult[1] = (TextView) findViewById(R.id.TBadResult02);
            TBadResult[2] = (TextView) findViewById(R.id.TBadResult03);
            TBadResult[3] = (TextView) findViewById(R.id.TBadResult04);
            TBadResult[4] = (TextView) findViewById(R.id.TBadResult05);
            TBadResult[5] = (TextView) findViewById(R.id.TBadResult06);
            TBadResult[6] = (TextView) findViewById(R.id.TBadResult07);
            TBadResult[7] = (TextView) findViewById(R.id.TBadResult08);
            TBadResult[8] = (TextView) findViewById(R.id.TBadResult09);
            TBadResult[9] = (TextView) findViewById(R.id.TBadResult10);
            TBadResult[10] = (TextView) findViewById(R.id.TBadResult11);
            TBadResult[11] = (TextView) findViewById(R.id.TBadResult12);
            TBadResult[12] = (TextView) findViewById(R.id.TBadResult13);
            TBadResult[13] = (TextView) findViewById(R.id.TBadResult14);
            TBadResult[14] = (TextView) findViewById(R.id.TBadResult15);
            TBadResult[15] = (TextView) findViewById(R.id.TBadResult16);
        } // define table entries ---------------------------
    }

    public void clearTable(){
        for(int i = 0; i < COUNT_LINES; ++i){
            TVeryGoodResult[i].setText("");
            TGoodResult[i].setText("");
            TNormalResult[i].setText("");
            TBadResult[i].setText("");
        }
    }

    @Override
    public void onClick(View v) {
        clearTable();

        switch (v.getId()){
            case R.id.btnTest:
                float[] weakness_of_pokemon = new float[Pokemon.COUNT_TYPE];

                if (!eTPokemonNameInput.getText().toString().replaceAll(" ", "").equals("")){
                    Pokemon pokemon = new Pokemon(eTPokemonNameInput.getText().toString().replaceAll(" ",""));
                    eTType1.setText(pokemon.getTypes()[0]);
                    eTType2.setText(pokemon.getTypes()[1]);
                    calcTable(pokemon.calcWeaknesses());

                }
                else {
                    String type1 = eTType1.getText().toString().toLowerCase().replaceAll(" ", "");
                    String type2 = eTType2.getText().toString().toLowerCase().replaceAll(" ", "");


                    //Pokemon pokemon = new Pokemon(type1, type2);

                    //if (type1.equals(type2)){
                    calcTable(pokemon.calcWeaknesses(type1, type2));
                    //}
                    //else if (type1.equals("") && !type2.equals(""))
                }

                break;

            case R.id.btnCalcWeakness:
                break;

            case R.id.btnClearTable:
                clearTable();
                break;
        }
    }

    public String makeFirstLetterCapitalLetter(String s){
        String result = s.substring(0,1);
        result = result.toUpperCase();
        result += s.substring(1, s.length());
        //return result;
        return s.substring(0,1).toUpperCase() + s.substring(1);
    }

    private void calcTable(float[] weaknesses) {
        int verygood = 0, good = 0, normal = 0, bad = 0;
        for (int i = 0; i < Pokemon.COUNT_TYPE; i++){
            if (weaknesses[i] == 4.0f){
                TVeryGoodResult[verygood].setText(makeFirstLetterCapitalLetter(Pokemon.typeNames[i]));
                verygood++;
            }
            else if (weaknesses[i] == 2.0f){
                TGoodResult[good].setText(makeFirstLetterCapitalLetter(Pokemon.typeNames[i]));
                good++;
            }
            else if (weaknesses[i] == 1.0f){
                TNormalResult[normal].setText(makeFirstLetterCapitalLetter(Pokemon.typeNames[i]));
                normal++;
            }
            else if (weaknesses[i] < 1.0f){
                TBadResult[bad].setText(makeFirstLetterCapitalLetter(Pokemon.typeNames[i]));
                bad++;
            }
            else {
                Toast.makeText(getApplicationContext(), "Fehler bei Berechnungen", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
