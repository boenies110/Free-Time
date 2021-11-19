package boenies.pokemonhelper;

import androidx.appcompat.app.AppCompatActivity;

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

        if (firstAppStart()){
        createDatabase();
        }
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
        int row, good = 0, normal = 0, bad = 0;
        if      (type.equals("normal")){
            row = 0;
        }
        else if (type.equals("kampf")){
            row = 1;
        }
        else if (type.equals("flug")){
            row = 2;
        }
        else if (type.equals("gift")) {
            row = 3;
        }
        else if (type.equals("boden")) {
            row = 4;
        }
        else if (type.equals("gestein")) {
            row = 5;
        }
        else if (type.equals("kaefer") ||
                type.equals("käfer")) {
            row = 6;
        }
        else if (type.equals("geist")) {
            row = 7;
        }
        else if (type.equals("stahl")) {
            row = 8;
        }
        else if (type.equals("feuer")) {
            row = 9;
        }
        else if (type.equals("wasser")) {
            row = 10;
        }
        else if (type.equals("pflanze")) {
            row = 11;
        }
        else if (type.equals("elektro")) {
            row = 12;
        }
        else if (type.equals("psycho")) {
            row = 13;
        }
        else if (type.equals("eis")) {
            row = 14;
        }
        else if (type.equals("drache")) {
            row = 15;
        }
        else if (type.equals("unlicht")) {
            row = 16;
        }
        else if (type.equals("fee")) {
            row = 17;
        }

        else{
            row = -1;
            TBadResult[9].setText("nix");
        }

        //TBadResult[9].setText(((Integer)row).toString());
        if(row >= 0 && row <= 17){
            TVeryGoodResult[0].setText(eTType1.getText().toString().replaceAll(" ",""));
            for (int i = 0; i < 18; i++){
                if (typeMatrix[i][row] == 2.0f){
                    TGoodResult[good].setText(typeNames[i]);
                    good++;
                    TVeryGoodResult[1].setText(((Integer)good).toString());
                }
                else if (typeMatrix[i][row] == 1.0f){
                    TNormalResult[normal].setText(typeNames[i]);
                    normal++;
                    TVeryGoodResult[2].setText(((Integer)normal).toString());
                }
                else if (typeMatrix[i][row] == 0.5f || typeMatrix[row][i] == 0.0f){
                    TBadResult[bad].setText(typeNames[i]);
                    bad++;
                    TVeryGoodResult[3].setText(((Integer)bad).toString());
                }
            }
        }
    }

    public void TestFunc(String type, String type2){
        int row, row2, verygood = 0, good = 0, normal = 0, bad = 0;

        {
            if      (type.equals("normal")){
                row = 0;
            }
            else if (type.equals("kampf")){
                row = 1;
            }
            else if (type.equals("flug")){
                row = 2;
            }
            else if (type.equals("gift")) {
                row = 3;
            }
            else if (type.equals("boden")) {
                row = 4;
            }
            else if (type.equals("gestein")) {
                row = 5;
            }
            else if (type.equals("kaefer") ||
                    type.equals("käfer")) {
                row = 6;
            }
            else if (type.equals("geist")) {
                row = 7;
            }
            else if (type.equals("stahl")) {
                row = 8;
            }
            else if (type.equals("feuer")) {
                row = 9;
            }
            else if (type.equals("wasser")) {
                row = 10;
            }
            else if (type.equals("pflanze")) {
                row = 11;
            }
            else if (type.equals("elektro")) {
                row = 12;
            }
            else if (type.equals("psycho")) {
                row = 13;
            }
            else if (type.equals("eis")) {
                row = 14;
            }
            else if (type.equals("drache")) {
                row = 15;
            }
            else if (type.equals("unlicht")) {
                row = 16;
            }
            else if (type.equals("fee")) {
                row = 17;
            }

            else{
                row = -1;
                TBadResult[9].setText("nix");
            }
        }           // Typ1

        {
            if      (type2.equals("normal")){
                row2 = 0;
            }
            else if (type2.equals("kampf")){
                row2 = 1;
            }
            else if (type2.equals("flug")){
                row2 = 2;
            }
            else if (type2.equals("gift")) {
                row2 = 3;
            }
            else if (type2.equals("boden")) {
                row2 = 4;
            }
            else if (type2.equals("gestein")) {
                row2 = 5;
            }
            else if (type2.equals("kaefer") ||
                    type2.equals("käfer")) {
                row2 = 6;
            }
            else if (type2.equals("geist")) {
                row2 = 7;
            }
            else if (type2.equals("stahl")) {
                row2 = 8;
            }
            else if (type2.equals("feuer")) {
                row2 = 9;
            }
            else if (type2.equals("wasser")) {
                row2 = 10;
            }
            else if (type2.equals("pflanze")) {
                row2 = 11;
            }
            else if (type2.equals("elektro")) {
                row2 = 12;
            }
            else if (type2.equals("psycho")) {
                row2 = 13;
            }
            else if (type2.equals("eis")) {
                row2 = 14;
            }
            else if (type2.equals("drache")) {
                row2 = 15;
            }
            else if (type2.equals("unlicht")) {
                row2 = 16;
            }
            else if (type2.equals("fee")) {
                row2 = 17;
            }

            else{
                row2 = -1;
                TBadResult[10].setText("nix");
            }
        }           // Typ2

        float[] resultArray = new float[18];
        if(row >= 0 && row <= 17 && row2 >= 0 && row2 <= 17){
            for(int i = 0; i < 18; i++){
                resultArray[i] = typeMatrix[i][row] * typeMatrix[i][row2];

                if (resultArray[i] == 2.0f){
                    TGoodResult[good].setText(typeNames[i]);
                    good++;
                }
                else if (resultArray[i] == 4.0f){
                    TVeryGoodResult[verygood].setText(typeNames[i]);
                    verygood++;
                }
                else if (resultArray[i] == 1.0f){
                    TNormalResult[normal].setText(typeNames[i]);
                    normal++;
                }
                else if (resultArray[i] == 0.5f || resultArray[i] == 0.0f || resultArray[i] == 0.25f){
                    TBadResult[bad].setText(typeNames[i]);
                    bad++;
                }
            }
        }
    }

    public void Vibration(int milliseconds){
        vibrator.vibrate(milliseconds);
    }

    public void calcWeaknesses(String type){
        int row, good = 0, normal = 0, bad = 0;
        if      (type.equals("normal")){
            row = 0;
        }
        else if (type.equals("kampf")){
            row = 1;
        }
        else if (type.equals("flug")){
            row = 2;
        }
        else if (type.equals("gift")) {
            row = 3;
        }
        else if (type.equals("boden")) {
            row = 4;
        }
        else if (type.equals("gestein")) {
            row = 5;
        }
        else if (type.equals("kaefer") ||
                type.equals("käfer")) {
            row = 6;
        }
        else if (type.equals("geist")) {
            row = 7;
        }
        else if (type.equals("stahl")) {
            row = 8;
        }
        else if (type.equals("feuer")) {
            row = 9;
        }
        else if (type.equals("wasser")) {
            row = 10;
        }
        else if (type.equals("pflanze")) {
            row = 11;
        }
        else if (type.equals("elektro")) {
            row = 12;
        }
        else if (type.equals("psycho")) {
            row = 13;
        }
        else if (type.equals("eis")) {
            row = 14;
        }
        else if (type.equals("drache")) {
            row = 15;
        }
        else if (type.equals("unlicht")) {
            row = 16;
        }
        else if (type.equals("fee")) {
            row = 17;
        }

        else{
            row = -1;
            TBadResult[9].setText("nix");
        }

        //TBadResult[9].setText(((Integer)row).toString());
        if(row >= 0 && row <= 17){
            //TVeryGoodResult[0].setText(eTType1.getText().toString().replaceAll(" ",""));
            for (int i = 0; i < 18; i++){
                if (typeMatrix[i][row] == 2.0f){
                    TGoodResult[good].setText(typeNames[i]);
                    good++;
                    //TVeryGoodResult[1].setText(((Integer)good).toString());
                }
                else if (typeMatrix[i][row] == 1.0f){
                    TNormalResult[normal].setText(typeNames[i]);
                    normal++;
                    //TVeryGoodResult[2].setText(((Integer)normal).toString());
                }
                else if (typeMatrix[i][row] == 0.5f || typeMatrix[row][i] == 0.0f){
                    TBadResult[bad].setText(typeNames[i]);
                    bad++;
                    //TVeryGoodResult[3].setText(((Integer)bad).toString());
                }
            }
        }
    }

    public void calcWeaknesses(String type, String type2){
        int row, row2, verygood = 0, good = 0, normal = 0, bad = 0;

        {
            if      (type.equals("normal")){
                row = 0;
            }
            else if (type.equals("kampf")){
                row = 1;
            }
            else if (type.equals("flug")){
                row = 2;
            }
            else if (type.equals("gift")) {
                row = 3;
            }
            else if (type.equals("boden")) {
                row = 4;
            }
            else if (type.equals("gestein")) {
                row = 5;
            }
            else if (type.equals("kaefer") ||
                    type.equals("käfer")) {
                row = 6;
            }
            else if (type.equals("geist")) {
                row = 7;
            }
            else if (type.equals("stahl")) {
                row = 8;
            }
            else if (type.equals("feuer")) {
                row = 9;
            }
            else if (type.equals("wasser")) {
                row = 10;
            }
            else if (type.equals("pflanze")) {
                row = 11;
            }
            else if (type.equals("elektro")) {
                row = 12;
            }
            else if (type.equals("psycho")) {
                row = 13;
            }
            else if (type.equals("eis")) {
                row = 14;
            }
            else if (type.equals("drache")) {
                row = 15;
            }
            else if (type.equals("unlicht")) {
                row = 16;
            }
            else if (type.equals("fee")) {
                row = 17;
            }

            else{
                row = -1;
                TBadResult[9].setText("nix");
            }
        }           // Typ1

        {
            if      (type2.equals("normal")){
                row2 = 0;
            }
            else if (type2.equals("kampf")){
                row2 = 1;
            }
            else if (type2.equals("flug")){
                row2 = 2;
            }
            else if (type2.equals("gift")) {
                row2 = 3;
            }
            else if (type2.equals("boden")) {
                row2 = 4;
            }
            else if (type2.equals("gestein")) {
                row2 = 5;
            }
            else if (type2.equals("kaefer") ||
                    type2.equals("käfer")) {
                row2 = 6;
            }
            else if (type2.equals("geist")) {
                row2 = 7;
            }
            else if (type2.equals("stahl")) {
                row2 = 8;
            }
            else if (type2.equals("feuer")) {
                row2 = 9;
            }
            else if (type2.equals("wasser")) {
                row2 = 10;
            }
            else if (type2.equals("pflanze")) {
                row2 = 11;
            }
            else if (type2.equals("elektro")) {
                row2 = 12;
            }
            else if (type2.equals("psycho")) {
                row2 = 13;
            }
            else if (type2.equals("eis")) {
                row2 = 14;
            }
            else if (type2.equals("drache")) {
                row2 = 15;
            }
            else if (type2.equals("unlicht")) {
                row2 = 16;
            }
            else if (type2.equals("fee")) {
                row2 = 17;
            }

            else{
                row2 = -1;
                TBadResult[10].setText("nix");
            }
        }           // Typ2

        float[] resultArray = new float[18];
        if(row >= 0 && row <= 17 && row2 >= 0 && row2 <= 17){
            for(int i = 0; i < 18; i++){
                resultArray[i] = typeMatrix[i][row] * typeMatrix[i][row2];

                if (resultArray[i] == 2.0f){
                    TGoodResult[good].setText(typeNames[i]);
                    good++;
                }
                else if (resultArray[i] == 4.0f){
                    TVeryGoodResult[verygood].setText(typeNames[i]);
                    verygood++;
                }
                else if (resultArray[i] == 1.0f){
                    TNormalResult[normal].setText(typeNames[i]);
                    normal++;
                }
                else if (resultArray[i] == 0.5f || resultArray[i] == 0.0f || resultArray[i] == 0.25f){
                    TBadResult[bad].setText(typeNames[i]);
                    bad++;
                }
            }
        }
    }


    // ---------------------------------------------------------------------------------------------
    // Methods for first start
    public boolean firstAppStart(){
        boolean is_first = false;
        SharedPreferences sharedPreferences =  getSharedPreferences("firstStart", MODE_PRIVATE);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();

        if(sharedPreferences.getBoolean("firstStart", false) == false){
            is_first = true;
            sharedPreferencesEditor.putBoolean("firstStart", true);
            sharedPreferencesEditor.commit();
        }
        return is_first;
    }

    public void createDatabase(){
        SQLiteDatabase database = getBaseContext().openOrCreateDatabase(databaseName, MODE_PRIVATE, null);
        if(firstAppStart()){
            database.execSQL("CREATE TABLE Dex1(Nr INT, Name TEXT, Type1 TEXT, Type2 TEXT)");

        database.execSQL("INSERT INTO Dex1 VALUES ('1',     'Bisasam',      'Pflanze',  'Gift')");
        database.execSQL("INSERT INTO Dex1 VALUES ('2',     'Bisaknosp',    'Pflanze',  'Gift')");
        database.execSQL("INSERT INTO Dex1 VALUES ('6',     'Glurak',       'Feuer',    'Flug')");
        database.execSQL("INSERT INTO Dex1 VALUES ('10',    'Raupy',        'Käfer',    '')");
        database.execSQL("INSERT INTO Dex1 VALUES ('31',    'Nidoqueen',    'Gift',     'Boden')");
        database.execSQL("INSERT INTO Dex1 VALUES ('32',    'Nidoran_m',    'Gift',     '')");
        database.execSQL("INSERT INTO Dex1 VALUES ('483',   'Dialga',       'Stahl',    'Drache')");
        database.execSQL("INSERT INTO Dex1 VALUES ('645',   'Demeteros',    'Boden',    'Flug')");
        database.close();
        }
    }
    // ---------------------------------------------------------------------------------------------

    @Override
    public void onClick(View v) {
        clearTable();
        switch (v.getId()){
            case R.id.btnTest:
                TestFunc(eTType1.getText().toString().toLowerCase().replaceAll(" ",""));
                //TestFunc2();
                break;

            case R.id.btnClearTable:
                clearTable();
                break;

            case R.id.btnCalcWeakness:

                //sowohl in eTType1 als auch ETType2 ein Eintrag
                if(!eTType1.getText().toString().equals("") && !eTType2.getText().toString().equals("")) {

                    // eTType1 == eTType2
                    if (eTType1.getText().toString().toLowerCase().replaceAll(" ","").equals(
                            eTType2.getText().toString().toLowerCase().replaceAll(" ",""))){
                        calcWeaknesses(eTType1.getText().toString().toLowerCase().replaceAll(" ",""));
                    }

                    // eTType1 != eTType2
                    else {
                        calcWeaknesses(eTType1.getText().toString().toLowerCase().replaceAll(" ",""), eTType2.getText().toString().toLowerCase().replaceAll(" ",""));
                    }

                }

                //nur in eTType1 ein Eintrag
                else if (!eTType1.getText().toString().equals("") && eTType2.getText().toString().equals("")){
                    calcWeaknesses(eTType1.getText().toString().toLowerCase().replaceAll(" ",""));
                }

                //nur in eTType2 ein Eintrag
                else if (!eTType2.getText().toString().equals("") && eTType1.getText().toString().equals("")){
                    calcWeaknesses(eTType2.getText().toString().toLowerCase().replaceAll(" ",""));
                }

                //gar kein Eintrag
                else {
                    TNormalResult[0].setText("weder Typ1 noch Typ2");
                }
                break;
        }
    }
}
