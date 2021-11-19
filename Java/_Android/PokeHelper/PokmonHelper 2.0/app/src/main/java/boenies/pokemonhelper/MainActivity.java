package boenies.pokemonhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int lines = 16;
    Button btnTest, btnCalcWeakness, btnClearTable;
    Vibrator vibrator;

    String databaseName = "Pokemon1.db";

    final static String ERROR_NO_TYPES = "NO TYPES";

    TextView[] TVeryGoodResult = new TextView[lines];
    TextView[] TGoodResult = new TextView[lines];
    TextView[] TNormalResult = new TextView[lines];
    TextView[] TBadResult = new TextView[lines];

    EditText eTPokemonNameInput, eTType1, eTType2;

    float[][] typeMatrix = {
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

    float[][] typeMatrix2 = {
            {1.0f,1.0f,1.0f,1.0f,1.0f,0.5f,1.0f,0.0f,0.5f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f},
            {2.0f,1.0f,0.5f,0.5f,1.0f,2.0f,0.5f,0.0f,2.0f,1.0f,1.0f,1.0f,1.0f,0.5f,2.0f,1.0f,2.0f,0.5f},
            {1.0f,2.0f,1.0f,0.5f,0.5f,0.5f,1.0f,0.5f,0.0f,1.0f,1.0f,2.0f,1.0f,1.0f,1.0f,1.0f,1.0f,2.0f},
            {1.0f,1.0f,0.0f,2.0f,1.0f,2.0f,0.5f,1.0f,2.0f,2.0f,1.0f,0.5f,2.0f,1.0f,1.0f,1.0f,1.0f,1.0f},
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
    String[] typeNames = {
            "normal", "kampf", "flug", "gift", "boden", "gestein", "käfer", "geist", "stahl",
            "feuer", "wasser", "pflanze", "elektro", "psycho", "eis", "drache", "unlicht", "fee"
    };

    Pokedex dex = new Pokedex();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

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

    public String makeFirstLetterCapitalLetter(String s){
        /*
        String result = s.substring(0,1);
        result = result.toUpperCase();
        result += s.substring(1, s.length());
        return result;
        */
        return s.substring(0,1).toUpperCase() + s.substring(1,s.length());
    }

    public Boolean isCorrectType(String s){

        return false;
    }

    public void clearTable(){
        for(int i = 0; i < lines; ++i){
            TVeryGoodResult[i].setText("");
            TGoodResult[i].setText("");
            TNormalResult[i].setText("");
            TBadResult[i].setText("");
        }
    }

    public void TestFunc(String type){

    }

    public void TestFunc(String type1, String type2){

    }

    public void Vibration(int milliseconds){
        vibrator.vibrate(milliseconds);
    }

    public void calcWeaknesses(String type){
        int row, good = 0, normal = 0, bad = 0;
        switch (type) {
            case "normal":
                row = 0;
                break;
            case "kampf":
                row = 1;
                break;
            case "flug":
                row = 2;
                break;
            case "gift":
                row = 3;
                break;
            case "boden":
                row = 4;
                break;
            case "gestein":
                row = 5;
                break;
            case "kaefer":
            case "käfer":
                row = 6;
                break;
            case "geist":
                row = 7;
                break;
            case "stahl":
                row = 8;
                break;
            case "feuer":
                row = 9;
                break;
            case "wasser":
                row = 10;
                break;
            case "pflanze":
                row = 11;
                break;
            case "elektro":
                row = 12;
                break;
            case "psycho":
                row = 13;
                break;
            case "eis":
                row = 14;
                break;
            case "drache":
                row = 15;
                break;
            case "unlicht":
                row = 16;
                break;
            case "fee":
                row = 17;
                break;
            default:
                row = -1;
                Toast.makeText(getApplicationContext(), ERROR_NO_TYPES, Toast.LENGTH_SHORT).show();
                break;
        }

        if(row >= 0 && row <= 17){
            for (int i = 0; i < 18; i++){
                if (typeMatrix[i][row] == 2.0f){
                    //TGoodResult[good].setText(typeNames[i]);
                    TGoodResult[good].setText(makeFirstLetterCapitalLetter(typeNames[i]));
                    good++;
                }
                else if (typeMatrix[i][row] == 1.0f){
                    TNormalResult[normal].setText(makeFirstLetterCapitalLetter(typeNames[i]));
                    normal++;
                }
                //else if (typeMatrix[i][row] == 0.5f || typeMatrix[row][i] == 0.0f){
                else if (typeMatrix[i][row] < 0.9f){
                    TBadResult[bad].setText(makeFirstLetterCapitalLetter(typeNames[i]));
                    bad++;
                }
            }
        }
    }

    public void calcWeaknesses(String type, String type2){
        int row, row2, verygood = 0, good = 0, normal = 0, bad = 0;

        {
            switch (type) {
                case "normal":
                    row = 0;
                    break;
                case "kampf":
                    row = 1;
                    break;
                case "flug":
                    row = 2;
                    break;
                case "gift":
                    row = 3;
                    break;
                case "boden":
                    row = 4;
                    break;
                case "gestein":
                    row = 5;
                    break;
                case "kaefer":
                case "käfer":
                    row = 6;
                    break;
                case "geist":
                    row = 7;
                    break;
                case "stahl":
                    row = 8;
                    break;
                case "feuer":
                    row = 9;
                    break;
                case "wasser":
                    row = 10;
                    break;
                case "pflanze":
                    row = 11;
                    break;
                case "elektro":
                    row = 12;
                    break;
                case "psycho":
                    row = 13;
                    break;
                case "eis":
                    row = 14;
                    break;
                case "drache":
                    row = 15;
                    break;
                case "unlicht":
                    row = 16;
                    break;
                case "fee":
                    row = 17;
                    break;
                default:
                    row = -1;
                    TBadResult[9].setText("nix");
                    break;
            }
        }           // Typ1

        {
            switch (type2) {
                case "normal":
                    row2 = 0;
                    break;
                case "kampf":
                    row2 = 1;
                    break;
                case "flug":
                    row2 = 2;
                    break;
                case "gift":
                    row2 = 3;
                    break;
                case "boden":
                    row2 = 4;
                    break;
                case "gestein":
                    row2 = 5;
                    break;
                case "kaefer":
                case "käfer":
                    row2 = 6;
                    break;
                case "geist":
                    row2 = 7;
                    break;
                case "stahl":
                    row2 = 8;
                    break;
                case "feuer":
                    row2 = 9;
                    break;
                case "wasser":
                    row2 = 10;
                    break;
                case "pflanze":
                    row2 = 11;
                    break;
                case "elektro":
                    row2 = 12;
                    break;
                case "psycho":
                    row2 = 13;
                    break;
                case "eis":
                    row2 = 14;
                    break;
                case "drache":
                    row2 = 15;
                    break;
                case "unlicht":
                    row2 = 16;
                    break;
                case "fee":
                    row2 = 17;
                    break;
                default:
                    row2 = -1;
                    TBadResult[10].setText("nix");
                    break;
            }
        }           // Typ2

        float[] resultArray = new float[18];
        if(row >= 0 && row <= 17 && row2 >= 0 && row2 <= 17){
            for(int i = 0; i < 18; i++){
                resultArray[i] = typeMatrix[i][row] * typeMatrix[i][row2];

                if (resultArray[i] == 2.0f){
                    TGoodResult[good].setText(makeFirstLetterCapitalLetter(typeNames[i]));
                    good++;
                }
                else if (resultArray[i] == 4.0f){
                    TVeryGoodResult[verygood].setText(makeFirstLetterCapitalLetter(typeNames[i]));
                    verygood++;
                }
                else if (resultArray[i] == 1.0f){
                    TNormalResult[normal].setText(makeFirstLetterCapitalLetter(typeNames[i]));
                    normal++;
                }
                //else if (resultArray[i] == 0.5f || resultArray[i] == 0.0f || resultArray[i] == 0.25f){
                else if (resultArray[i] < 0.9f){
                    TBadResult[bad].setText(makeFirstLetterCapitalLetter(typeNames[i]));
                    bad++;
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        clearTable();
        switch (v.getId()){
            case R.id.btnTest:
                Toast.makeText(getApplicationContext(),"test", Toast.LENGTH_SHORT).show();

                break;

            case R.id.btnClearTable:
                clearTable();
                break;

            case R.id.btnCalcWeakness:
                // spezifisches Pokemon
                if(!eTPokemonNameInput.getText().toString().equals("")){
                    String testname = eTPokemonNameInput.getText().toString().toLowerCase().replaceAll(" ","");

                    TNormalResult[5].setText(dex.getPokemon(testname)[1]);
                    TNormalResult[6].setText(dex.getPokemon(testname)[2]);

                    eTPokemonNameInput.setText(makeFirstLetterCapitalLetter(testname));
                    eTType1.setText(dex.getPokemon(testname)[1]);
                    eTType2.setText(dex.getPokemon(testname)[2]);
                    //TVeryGoodResult[10].setText(makeFirstLetterCapitalLetter(testname));
                }

                String type1 = eTType1.getText().toString().toLowerCase().replaceAll(" ","");
                String type2 = eTType2.getText().toString().toLowerCase().replaceAll(" ","");

                if(!type1.equals("")) {
                    eTType1.setText(makeFirstLetterCapitalLetter(type1));
                }
                if(!type2.equals("")) {
                    eTType2.setText(makeFirstLetterCapitalLetter(type2));
                }

                // eTType1 und eTType2 angegeben
                if(!type1.equals("") && !type2.equals("")) {

                    // eTType1 == eTType2
                    if (type1.equals(type2)){
                        calcWeaknesses(type1);
                    }

                    // eTType1 != eTType2
                    else {
                        calcWeaknesses(type1, type2);
                    }

                }

                // nur in eTType1 ein Eintrag
                else if (!type1.equals("") && type2.equals("")){
                    calcWeaknesses(type1);
                }

                // nur in eTType2 ein Eintrag
                else if (!type2.equals("") && type1.equals("")){
                    calcWeaknesses(type2);
                }

                // gar kein Eintrag
                else {
                    Toast.makeText(getApplicationContext(),ERROR_NO_TYPES, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
