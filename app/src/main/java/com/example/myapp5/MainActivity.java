package com.example.myapp5;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {
    TextView[] textView= new TextView[13];
    Button[] button= new Button[2];
    Spinner[] spinner= new Spinner[1];
    List<Integer> targetList = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    List<Integer> estimateList = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    String ansList;
    Integer eat;
    Integer bite;
    Integer trialNumber=0;
    Integer digitsNumber=3;
    String[] spinnerItems={"3","4","5"};
    ArrayList<String> historyList;
    NumerOn no = new NumerOn();

    View.OnClickListener buttonOperatorListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button0:
                    ansList = "";
                    Collections.shuffle(targetList);
    //                    textView[0].setText(String.valueOf(estimateList));
                    textView[0].setText("Answer");
                    for (int i = 0; i < digitsNumber; i++) {
                        ansList = ansList + targetList.get(i);
                    }
                    textView[1].setText(ansList);
                    break;
                case R.id.button1:
                    trialNumber++;
                    ansList = "";
                    Collections.shuffle(estimateList);
    //                    textView[2].setText(String.valueOf(estimateList));
                    textView[2].setText("Trial");
                    for (int i = 0; i < digitsNumber; i++) {
                        ansList = ansList + estimateList.get(i);
                    }
                    textView[3].setText(ansList);
                    textView[9].setText(String.valueOf(trialNumber));
                    eat = 0;
                    bite = 0;
                    // Judgement if eat or bite
                    for (int i = 0; i < digitsNumber; i++) {
                        for (int j = 0; j < digitsNumber; j++) {
                            if (estimateList.get(i) == targetList.get(j)) {
                                if (i == j) {
                                    eat++;
                                } else {
                                    bite++;
                                }
                            }
                        }
                    }
                    // Represent the numbers of eat and bite
                    textView[5].setText(String.valueOf(eat));
                    textView[7].setText(String.valueOf(bite));
                    // Message depending only on the number of eat
                    switch (eat) {
                        case 4:
                            textView[10].setText("Congratulations!");
                            break;
                        case 3:
                            textView[10].setText("Near pin!");
                            break;
                        case 2:
                            textView[10].setText("Average");
                            break;
                        case 1:
                            textView[10].setText("Again!");
                            break;
                        case 0:
                            textView[10].setText("That's too bad...");
                            break;
                    }
                    viewHistory();
                    break;
            }
        }
    };

    public void viewHistory(){
        // https://akira-watson.com/android/arraylist.html
        int number = historyList.size();
        historyList.add(String.format(Locale.US, "Test \n", (number+1)));
        StringBuilder stb = new StringBuilder();
        for(int i=0; i< historyList.size(); i++){
            stb.append(historyList.get(i));
        }
        textView[12].setText(stb);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize id resources for textView, button, and spinner
        // http://doshirouto.wp.xdomain.jp/java/java_study/2389
        for(int i=0; i <textView.length; i++){
            String str = "textView" + i;
            int buttonId= getResources().getIdentifier(str, "id", getPackageName());
            textView[i]=findViewById(buttonId);
        }
        for(int i=0; i <button.length; i++){
            String str = "button" + i;
            int buttonId= getResources().getIdentifier(str, "id", getPackageName()); //getIdentifier("リソース名", "欲しい値", "パッケージ名");
            button[i]=findViewById(buttonId); // buttonId=R.id.button0
        }
        for(int i=0; i <spinner.length; i++){
            String str = "spinner" + i;
            int buttonId= getResources().getIdentifier(str, "id", getPackageName());
            spinner[i]=findViewById(buttonId);
        }
        historyList = new ArrayList<>();

        for (int i = 0; i < button.length; i++) {
            button[i].setOnClickListener(buttonOperatorListener);
        }
        // *** Select digits numbers ***
        // https://akira-watson.com/android/spinner.html
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner[0].setAdapter(adapter);
        spinner[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                String item = (String)spinner.getSelectedItem();
                textView[11].setText(item);
                digitsNumber=parseInt(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });
    }
}