package com.domhnall_boyle.flappy_bird.engine.rest;

import com.domhnall_boyle.flappy_bird.engine.rest.bodies.HighScoreBody;
import com.domhnall_boyle.flappy_bird.engine.rest.bodies.LoginBody;
import com.domhnall_boyle.flappy_bird.engine.rest.responses.LoginResponse;
import com.domhnall_boyle.flappy_bird.engine.rest.responses.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIServices {

    @POST("login")
    Call<LoginResponse> login(@Body LoginBody body);

    @GET("users")
    Call<List<UserResponse>> getHighScores(@Header("Authorization") String authorization);

    @POST("users/{id}")
    Call<UserResponse> updateHighScore(@Header("Authorization") String authorization,
                                       @Path("id") String userId,
                                       @Body HighScoreBody body);
}
