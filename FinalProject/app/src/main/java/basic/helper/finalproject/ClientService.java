package basic.helper.finalproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ClientService {
    @GET("Resources/{task_text}.json")
    Call<Questions> getValues(@Path("task_text") String task_text);
    @GET ("types_of_tasks.json")
    Call<List<NamesQuestions>> getNameQuestion();
}
