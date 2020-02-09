package com.domhnall_boyle.flappy_bird.fragments;

import android.app.Dialog;
import android.widget.Button;
import android.widget.EditText;

import com.domhnall_boyle.flappy_bird.R;
import com.domhnall_boyle.flappy_bird.engine.rest.bodies.LoginBody;
import com.domhnall_boyle.flappy_bird.engine.rest.repositories.AuthRepository;
import com.domhnall_boyle.flappy_bird.game.Game;
import com.domhnall_boyle.flappy_bird.models.User;
import com.domhnall_boyle.flappy_bird.screens.IntroductionScreen;
import com.domhnall_boyle.flappy_bird.utilities.MyApplication;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroductionFragment extends GameFragment {

    @BindView(R.id.login)
    Button loginButton;

    @BindView(R.id.bypass)
    Button bypass;

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.password)
    EditText password;

    public IntroductionFragment(Game game) {
        super(game);
    }

    @Override
    public void onStart() {
        super.onStart();
        new IntroductionScreen(this, this.game);

        if (!User.getInstance().isLoggedIn()) {
            showLoginDialog();
        }
    }

    private void showLoginDialog() {
        Dialog loginDialog = new Dialog(this.getContext());
        loginDialog.setContentView(R.layout.login_dialog);
        loginDialog.setCancelable(false);

        ButterKnife.bind(this, loginDialog);

        loginButton.setOnClickListener(view -> {
            String username = this.username.getText().toString();
            String password = this.password.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                MyApplication.showMessage("Username and password are required");
            } else {
                new AuthRepository().login(new LoginBody(username, password)).observe(this, success -> {
                    if (success) {
                        loginDialog.dismiss();
                    }
                });
            }
        });

        bypass.setOnClickListener(view -> {
            loginDialog.dismiss();
        });

        loginDialog.show();
    }
}
