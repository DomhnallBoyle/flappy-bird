package com.domhnall_boyle.flappy_bird.engine.rest.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.domhnall_boyle.flappy_bird.engine.rest.APIClient;
import com.domhnall_boyle.flappy_bird.engine.rest.APIServices;
import com.domhnall_boyle.flappy_bird.engine.rest.bodies.HighScoreBody;
import com.domhnall_boyle.flappy_bird.engine.rest.responses.UserResponse;
import com.domhnall_boyle.flappy_bird.models.User;
import com.domhnall_boyle.flappy_bird.utilities.MyApplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRespository extends BaseRepository {

    public void updateHighScore(HighScoreBody body) {
        String accessToken = "Bearer " + User.getInstance().getAccessToken();

        Call<UserResponse> call = APIClient.getInstance()
                .create(APIServices.class)
                .updateHighScore(accessToken, User.getInstance().getId(), body);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    MyApplication.showMessage("Highscore Updated");
                } else {
                    onRequestUnsuccessful(response);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                onRequestFailure(t);
            }
        });
    }

    public LiveData<List<UserResponse>> getHighScores() {
        MutableLiveData<List<UserResponse>> topPlayers = new MutableLiveData<>();

        String accessToken = "Bearer " + User.getInstance().getAccessToken();

        Call<List<UserResponse>> call = APIClient.getInstance()
                .create(APIServices.class)
                .getHighScores(accessToken);

        call.enqueue(new Callback<List<UserResponse>>() {
            @Override
            public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                if (response.isSuccessful()) {
                    List<UserResponse> body = response.body();
                    topPlayers.setValue(body);
                } else {
                    onRequestUnsuccessful(response);
                    topPlayers.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<UserResponse>> call, Throwable t) {
                onRequestFailure(t);
                topPlayers.setValue(null);
            }
        });

        return topPlayers;
    }
}
