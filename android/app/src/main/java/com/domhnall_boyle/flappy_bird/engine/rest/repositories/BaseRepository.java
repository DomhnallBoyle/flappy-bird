package com.domhnall_boyle.flappy_bird.engine.rest.repositories;

import com.domhnall_boyle.flappy_bird.engine.rest.APIClient;
import com.domhnall_boyle.flappy_bird.engine.rest.responses.ErrorResponse;
import com.domhnall_boyle.flappy_bird.utilities.MyApplication;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.HttpURLConnection;
import java.net.UnknownHostException;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;


public class BaseRepository {

    protected void onRequestUnsuccessful(Response response) {
        if (response.code() == HttpURLConnection.HTTP_NOT_FOUND) {
            MyApplication.showMessage("Could not establish connection to server");
        } else {
            if (response.errorBody() != null) {
                Converter<ResponseBody, ErrorResponse> errorConverter =
                        APIClient.getInstance().responseBodyConverter(ErrorResponse.class, new Annotation[0]);
                try {
                    ErrorResponse error = errorConverter.convert(response.errorBody());
                    MyApplication.showMessage("Login failed: " + error.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void onRequestFailure(Throwable t) {
        // only socket/networking issues will come here

        if (t instanceof UnknownHostException) {
            MyApplication.showMessage("Could not connect to server. Please try again later");
        } else {
            MyApplication.showMessage(t.getMessage());
        }
    }
}
