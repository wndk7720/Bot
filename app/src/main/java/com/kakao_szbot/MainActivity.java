package com.kakao_szbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kakao_szbot.cmd.CommandList;
import com.kakao_szbot.cmd.CommandLovePoint;
import com.kakao_szbot.cmd.CommandQuiz;
import com.kakao_szbot.cmd.CommandStudy;
import com.kakao_szbot.cmd.CommandSurvival;
import com.kakao_szbot.cmd.CommandTower;
import com.kakao_szbot.cmd.CommandTwentyQuestions;

import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private static Context context;
    private static Resources res;
    private static String PackageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getApplicationContext();
        res = getResources();
        PackageName = getPackageName();

        new CommandList().loadBotBasicCommand();
        new CommandStudy().loadStudyMessage();
        new CommandLovePoint().loadLovePointList();
        new CommandQuiz().loadQuizPointList();
        new CommandQuiz().loadQuiz2PointList();
        new CommandSurvival().loadSurvivalData();
        new CommandTower().loadTowerData();
        new CommandTwentyQuestions().loadTQData();

        permissionGrantred();
        setContentView(R.layout.activity_main);
    }

    private boolean permissionGrantred() {
        Set<String> sets = NotificationManagerCompat.getEnabledListenerPackages(this);
        if (sets != null && sets.contains(getPackageName())) {
            return true;
        } else {
            startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
            return false;
        }
    }

    /** Called when the user touches the button */
    public void permissionButton(View view) {
        startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
    }

    public static Context getAppContext() {
        return context;
    }
    public static Resources getAppResources() {
        return res;
    }
    public static String getAppPackageName() {
        return PackageName;
    }
}