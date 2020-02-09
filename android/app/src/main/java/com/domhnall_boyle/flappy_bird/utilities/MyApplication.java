package com.domhnall_boyle.flappy_bird.utilities;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.domhnall_boyle.flappy_bird.R;
import com.domhnall_boyle.flappy_bird.activities.MainActivity;
import com.domhnall_boyle.flappy_bird.fragments.GameFragment;

public class MyApplication extends Application implements android.app.Application.ActivityLifecycleCallbacks {

    private static Activity applicationActivity;

    public MyApplication() {
        this.registerActivityLifecycleCallbacks(this);
    }

    public static Activity getCurrentActivity() {
        return applicationActivity;
    }

    public static void changeFragment(GameFragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = ((MainActivity)applicationActivity).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    public static void showMessage(String message) {
        Toast.makeText(applicationActivity, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        applicationActivity = activity;
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }
}
