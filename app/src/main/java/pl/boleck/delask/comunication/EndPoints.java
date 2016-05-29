package pl.boleck.delask.comunication;

import java.util.List;
import java.util.UUID;

import pl.boleck.delask.model.Question;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Boleck on 2016-04-22.
 */
public interface Endpoints {

    //Dodaje nowe zapytanie
    @POST("/questions")
    Call<Question> createQuestion(@Body Question question);

    //Zwraca wszystkie zapytania
    @GET("/questions")
    Call<List<Question>> getAllQuestions();

    //zwaraca tylko zapytania usera
    @GET("/question/user/{user_id}")
    Call<List<Question>> getQuestionOfUser(@Path("user_id") String userId);

    //usuwa zapytanie
    @DELETE("/question/{question_id}")
    Call<Void> removeQuestion(@Path("question_id") String questionId);

    @PUT("/question/{question_id}/yes")
    Call<Void> incrementPositive(@Path("question_id")String questionId);

    @PUT("/question/{question_id}/no")
    Call<Void> incrementNegative(@Path("question_id")String questionId);
}