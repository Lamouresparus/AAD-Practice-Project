package com.example.aadpracticeproject.retrofit;

import com.example.aadpracticeproject.model.Leader;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface LeaderService {

    @GET("skilliq")
    Call<List<Leader>> getSkillIqLeaders();

    @GET("hours")
    Call<List<Leader>> getLearningHoursLeaders();

    @FormUrlEncoded
    @POST
    Call<Void> submitProject(@Url String url,
                             @Field("entry.1824927963")String email,
                             @Field("entry.1877115667")String firstName,
                             @Field("entry.2006916086")String lastName,
                             @Field("entry.284483984")String projectLink);

}
