package basic.helper.finalproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Questions {
    public String task_text;
    public List<String> strokes;

    public String getTask_text() {
        return task_text;
    }

    public List<String> getStrokes() {
        return strokes;
    }
}
