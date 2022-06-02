package basic.helper.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LevelCollector extends AppCompatActivity {

    Random random = new Random();
    public int your_answer = 1;
    public int val1;
    public int val2;
    public int val3;
    public int val4;
    int x, y, q, z;
    //String questions_task;
   // Questions questions;
   // List<String> array1;
    //int[] values = {1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);
        Bundle str = getIntent().getExtras();
        String s  = str.getString("task");
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getValues(s);
    }
    public void getValues(String s){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/LeyaKs/EGE.github.io/gh-pages/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ClientService result = retrofit.create(ClientService.class);
        retrofit2.Call<Questions> call = result.getValues(s);
        call.enqueue(new Callback<Questions>() {
            @Override
            public void onResponse(retrofit2.Call<Questions> call, Response<Questions> response) {
                if(response.isSuccessful()){
                    Questions questions1 = response.body();
                    create_task(questions1);
                }
            }

            @Override
            public void onFailure(Call<Questions> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
        public void create_task(Questions questions) {
            String questions_task = questions.getTask_text();
            List<String> array1 = questions.getStrokes();
            TextView text_level1 = findViewById(R.id.text_level);
            Button button_next = findViewById(R.id.button_next);
            CheckBox option1 = findViewById(R.id.option1);
            CheckBox option2 = findViewById(R.id.option2);
            CheckBox option3 = findViewById(R.id.option3);
            CheckBox option4 = findViewById(R.id.option4);
            TextView answer = findViewById(R.id.answer);
            option1.setTextColor(Color.BLACK);
            option2.setTextColor(Color.BLACK);
            option3.setTextColor(Color.BLACK);
            option4.setTextColor(Color.BLACK);
            text_level1.setText (questions_task);
            val1 = random.nextInt(28);
            option1.setText(array1.get(val1).substring(1));
            val2 = random.nextInt(28);
            while (val1 == val2){
                val2 = random.nextInt(28);
            }
            option2.setText(array1.get(val2).substring(1));
            val3 = random.nextInt(28);
            while ((val2 == val3) || (val1 == val3)){
                val3 = random.nextInt(28);
            }
            option3.setText(array1.get(val3).substring(1));
            val4 = random.nextInt(28);
            x = Integer.parseInt(String.valueOf(array1.get(val1).charAt(0)));
            y = Integer.parseInt(String.valueOf(array1.get(val2).charAt(0)));
            q = Integer.parseInt(String.valueOf(array1.get(val3).charAt(0)));
            z = Integer.parseInt(String.valueOf(array1.get(val4).charAt(0)));
            while (val3 == val4 || val2 == val4 || val1 == val4 || (x == y && y==z && z==q)){
                val4 = random.nextInt(28);
                z = Integer.parseInt(String.valueOf(array1.get(val4).charAt(0)));
            }
            option4.setText(array1.get(val4).substring(1));
            Button check_button = findViewById(R.id.check_button);
            check_button.setOnClickListener(v -> {
                your_answer = 1;
                if (option1.isChecked())
                    your_answer = your_answer * x;
                if (option2.isChecked())
                    your_answer = your_answer * y;
                if (option3.isChecked())
                    your_answer = your_answer * q;
                if (option4.isChecked())
                    your_answer = your_answer * z;
                if (!option1.isChecked() && (x == 1))
                    your_answer = 0;
                if (!option2.isChecked() && (y == 1))
                    your_answer = 0;
                if (!option3.isChecked() && (q == 1))
                    your_answer = 0;
                if (!option4.isChecked() && (z == 1))
                    your_answer = 0;
                if (x == 1)
                    option1.setTextColor(Color.GREEN);
                else option1.setTextColor(Color.RED);
                if (y == 1)
                    option2.setTextColor(Color.GREEN);
                else option2.setTextColor(Color.RED);
                if (q == 1)
                    option3.setTextColor(Color.GREEN);
                else option3.setTextColor(Color.RED);
                if (z == 1)
                    option4.setTextColor(Color.GREEN);
                else option4.setTextColor(Color.RED);
                if (your_answer == 1){
                    answer.setText("Правильно");
                    answer.setTextColor(Color.GREEN);

                }else {
                    answer.setText("Неправильно");
                    answer.setTextColor(Color.RED);
                }
                option1.setEnabled(false);
                option2.setEnabled(false);
                option3.setEnabled(false);
                option4.setEnabled(false);
            });
            button_next.setOnClickListener(v -> {
                option1.setEnabled(true);
                option2.setEnabled(true);
                option3.setEnabled(true);
                option4.setEnabled(true);
                option1.setChecked(false);
                option2.setChecked(false);
                option3.setChecked(false);
                option4.setChecked(false);
                answer.setText("");
                option1.setTextColor(Color.BLACK);
                option2.setTextColor(Color.BLACK);
                option3.setTextColor(Color.BLACK);
                option4.setTextColor(Color.BLACK);
                val1 = random.nextInt(28);
                option1.setText(array1.get(val1).substring(1));
                val2 = random.nextInt(28);
                while (val1 == val2){
                    val2 = random.nextInt(28);
                }
                option2.setText(array1.get(val2).substring(1));
                val3 = random.nextInt(28);
                while ((val2 == val3) || (val1 == val3)){
                    val3 = random.nextInt(28);
                }
                option3.setText(array1.get(val3).substring(1));
                val4 = random.nextInt(28);
                x = Integer.parseInt(String.valueOf(array1.get(val1).charAt(0)));
                y = Integer.parseInt(String.valueOf(array1.get(val2).charAt(0)));
                q = Integer.parseInt(String.valueOf(array1.get(val3).charAt(0)));
                z = Integer.parseInt(String.valueOf(array1.get(val4).charAt(0)));
                while ((val3 == val4) || (val2 == val4) || (val1 == val4) || (x == y && y == z && z == q)){
                    val4 = random.nextInt(28);
                    z = Integer.parseInt(String.valueOf(array1.get(val4).charAt(0)));
                }
                option4.setText(array1.get(val4).substring(1));
            });
        }
    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(LevelCollector.this, Tasks.class);
            startActivity(intent);
            finish();
        }catch (Exception e){

        }
    }
}