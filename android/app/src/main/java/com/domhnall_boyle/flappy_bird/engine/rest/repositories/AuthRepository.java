package com.domhnall_boyle.flappy_bird.engine.rest.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.domhnall_boyle.flappy_bird.engine.rest.APIClient;
import com.domhnall_boyle.flappy_bird.engine.rest.APIServices;
import com.domhnall_boyle.flappy_bird.engine.rest.bodies.LoginBody;
import com.domhnall_boyle.flappy_bird.engine.rest.responses.LoginResponse;
import com.domhnall_boyle.flappy_bird.models.User;
import com.domhnall_boyle.flappy_bird.utilities.MyApplication;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository extends BaseRepository {

    public LiveData<Boolean> login(LoginBody loginBody) {
        MutableLiveData<Boolean> success = new MutableLiveData<>();

        Call<LoginResponse> call = APIClient.getInstance()
                .create(APIServices.class)
                .login(loginBody);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse body = response.body();
                    User user = User.getInstance();
                    user.setId(body.getUserId());
                    user.setUsername(loginBody.getUsername());
                    user.setHighScore(body.getHighScore());
                    user.setAccessToken(body.getAccessToken());
                    user.setRefreshToken(body.getRefreshToken());
                    user.setLoggedIn(true);
                    MyApplication.showMessage("Login successful");
                    success.setValue(true);
                } else {
                    onRequestUnsuccessful(response);
                    success.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                onRequestFailure(t);
                success.setValue(false);
            }
        });

        return success;
    }
}
