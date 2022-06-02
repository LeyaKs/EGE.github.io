package basic.helper.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Tasks extends AppCompatActivity {    private NamesQuestionsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks);
        adapter = new NamesQuestionsAdapter(this);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayBasic);
        ListView listView = findViewById(R.id.list_item);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Tasks.this, LevelCollector.class);
                intent.putExtra ("task", adapter.getNameQuestions(position).getTask_text());
                startActivity(intent);
                finish();
            }
        });
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getNames();
    }

    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(Tasks.this, MainActivity.class);
            startActivity(intent);
            finish();
        }catch (Exception e){

        }
    }
    public void getNames(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/LeyaKs/EGE.github.io/gh-pages/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ClientService question = retrofit.create(ClientService.class);
        retrofit2.Call<List<NamesQuestions>> call = question.getNameQuestion();
        call.enqueue(new Callback<List<NamesQuestions>>() {
            @Override
            public void onResponse(retrofit2.Call<List<NamesQuestions>> call, Response<List<NamesQuestions>> response) {
                if(response.isSuccessful()){
                    List<NamesQuestions> namesQuestions = response.body();
                    adapter.refresh(namesQuestions);
                }
            }

            @Override
            public void onFailure(Call<List<NamesQuestions>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}