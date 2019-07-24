package com.example.nyan.axisappcopy2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private AutoCompleteTextView mCameraIP;
    private EditText mLogin;
    private EditText mPassword;
    private VideoView mVideoView;
    private Button mConnectBtn;
    private Switch mIsPTZ;
    private View mView;
    private String userLogin;
    private String userPassword;

    public static final String APP_PREFERENCES = "AUTOSET";
    private List<String> mList;
    private SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mVideoView = findViewById(R.id.videoView);
        mConnectBtn = findViewById(R.id.connectBtn);
        mCameraIP = findViewById(R.id.cameraIP);
        mIsPTZ = findViewById(R.id.isPTZ);

        sPref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        mCameraIP = findViewById(R.id.cameraIP);
        mList = loadText();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                mList);

        mCameraIP.setAdapter(adapter);

        mConnectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mCameraIP.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "Поле IP не может быть пустым", Toast.LENGTH_SHORT).show();
                } else {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                mView = MainActivity.this.getLayoutInflater().inflate(R.layout.dialog_login, null);
                builder.setTitle("Авторизация")
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mLogin = mView.findViewById(R.id.login);
                                mPassword = mView.findViewById(R.id.password);

                                userLogin = mLogin.getText().toString();
                                userPassword = mPassword.getText().toString();

                                String videoSource = "rtsp://46.0.199.87/axis-media/media.amp";
                                mVideoView.setVideoURI(Uri.parse(videoSource));
                                mVideoView.start();
                                startStream();
                            }
                        });

                builder.setView(mView);
                AlertDialog dialog = builder.create();
                dialog.show();
                        }

                String newAdd = mCameraIP.getText().toString();
                if(!mList.contains(newAdd)) {
                    mList.add(newAdd);
                    int num = mList.size()+1;
                    saveText("ip"+num, newAdd);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        getApplicationContext(),
                        android.R.layout.simple_dropdown_item_1line,
                        mList);

                mCameraIP.setAdapter(adapter);
            }
        });


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        String IP = mCameraIP.getText().toString();
        String userLogin = mLogin.getText().toString();
        String userPassword = mPassword.getText().toString();

        if (id == R.id.nav_prep_home) {
            preparedAction(IP, userLogin, userPassword, "home");

        } else if (id == R.id.nav_prep1) {
            preparedAction(IP, userLogin, userPassword, "Position1");

        }else if (id == R.id.nav_prep2) {
            preparedAction(IP, userLogin, userPassword, "Position2");

        } else if (id == R.id.nav_prep3) {
            preparedAction(IP, userLogin, userPassword, "Position3");
        }
        else if (id == R.id.nav_prep4) {
            preparedAction(IP, userLogin, userPassword, "Position4");

        } else if (id == R.id.nav_prep5) {
            preparedAction(IP, userLogin, userPassword, "Position5");

        } else if (id == R.id.nav_prep6) {
            preparedAction(IP, userLogin, userPassword, "Position6");

        } else if (id == R.id.nav_prep7) {
            preparedAction(IP, userLogin, userPassword, "Position7");
        }
        else if (id == R.id.nav_prep8) {
            preparedAction(IP, userLogin, userPassword, "Position8");

        } else if (id == R.id.nav_prep9) {
            preparedAction(IP, userLogin, userPassword, "Position9");

        } else if (id == R.id.nav_prep10) {
            preparedAction(IP, userLogin, userPassword, "Position10");

        } else if (id == R.id.nav_prep11) {
            preparedAction(IP, userLogin, userPassword, "Position11");

        } else if (id == R.id.nav_prep12) {
            preparedAction(IP, userLogin, userPassword, "Position12");

        } else if (id == R.id.nav_prep13) {
            preparedAction(IP, userLogin, userPassword, "Position13");

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void preparedAction(String IP, String username, String password, String action) {
        OkHttpClient client = new OkHttpClient();
        String credentials = username+":"+password;

        final String basic = "Basic "+ Base64.encodeToString(credentials.getBytes(),
                Base64.NO_WRAP);
        String url = "http://"+IP+"/axis-cgi/com/ptz.cgi?gotoserverpresetname="+action;
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", basic)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) {
                // System.out.println("Response!");
                try {
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            public void onFailure(Call call, IOException e) {
                //System.out.println("Failure...");
                //System.out.println(e);
            }
        });
    }

    public void startStream() {
        String IP = mCameraIP.getText().toString();
        if(IP.equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Это поле не может быть пустым", Toast.LENGTH_SHORT).show();
        }
        String videoSource;
        // если камера имеет логин/пароль
        // конструируем адрес видеопотока
        if ((!userLogin.equals("")) && (!userPassword.equals(""))) {
            userLogin += ":";
            userPassword += "@";
        }

        if(mIsPTZ.isChecked()) {
            videoSource = "rtsp://" + userLogin + userPassword + IP + "/mpeg4/media.amp";
        } else {
            videoSource = "rtsp://" + userLogin + userPassword + IP + "/axis-media/media.amp";
        }

        mVideoView.setVideoURI(Uri.parse(videoSource));
        mVideoView.start();
    }

    public void saveText(String key, String value) {
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(key, value);
        ed.apply();
    }

    public List<String> loadText() {
        Map<String, ?> map = sPref.getAll();
        return new ArrayList(map.values());
    }
}
