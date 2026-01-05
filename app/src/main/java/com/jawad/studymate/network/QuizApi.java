package com.jawad.studymate.network;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuizApi {
    @GET("/posts")
    Call<List<QuizQuestion>> getQuestions();
}
