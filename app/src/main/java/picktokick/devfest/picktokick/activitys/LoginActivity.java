package picktokick.devfest.picktokick.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

import java.net.URL;
import java.util.Arrays;

import picktokick.devfest.picktokick.R;
import picktokick.devfest.picktokick.objects.Constanttt;
import picktokick.devfest.picktokick.objects.User;
import picktokick.devfest.picktokick.service.GPSTracker;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public static String name, id, email, link;
    LocationManager locationManager;
    Location loc;
    SharedPreferences ref;
    SharedPreferences.Editor edit_login;
    private CallbackManager callbackManager;
    private FacebookCallback<LoginResult> loginResult;
    private LoginButton loginButton;
    private URL imageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ref = getSharedPreferences(Constanttt.SHARE_REF_LOGIN, MODE_PRIVATE);
        edit_login = ref.edit();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ref.getAll().size() > 0) {
            startActivity(new Intent(LoginActivity.this, Main_Activity.class));
            finish();
        }

        if (!locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)) {
            turnGPSOn();
        }
        GPSTracker gpsTracker = new GPSTracker(this);
        loc = gpsTracker.getLocation();

        edit_login.putString(Constanttt.LOGIN_LATITUDE, String.valueOf(loc.getLatitude()));
        edit_login.putString(Constanttt.LOGIN_LONGITUDE, String.valueOf(loc.getLongitude()));
        edit_login.commit();

        Log.e(Constanttt.TAG_APP, loc.getLatitude() + "  " + loc.getLongitude());
        getSupportActionBar().hide();
        //config facebook
        FacebookSdk.sdkInitialize(getApplicationContext());

        //anh xa

        callbackManager = CallbackManager.Factory.create();


        loginButton = (LoginButton) findViewById(R.id.btnlogin);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(LoginActivity.this, "Successful", Toast.LENGTH_LONG).show();
                GraphRequest request = GraphRequest.newMeRequest(
                        AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object,
                                                    GraphResponse response) {
                                // Application code

                                name = object.optString(getString(R.string.name));
                                id = object.optString(getString(R.string.id));
                                email = object.optString(getString(R.string.email));
                                link = object.optString(getString(R.string.link));
                                imageURL = extractFacebookIcon(id);
                                /*Log.e(Constanttt.TAG_TAG, name);
                                Log.e(Constanttt.TAG_TAG, id);
                                Log.e(Constanttt.TAG_TAG, email);
                                Log.e(Constanttt.TAG_TAG, link);
                                Log.e(Constanttt.TAG_TAG, imageURL.toString());
                                */
                                DatabaseReference database= FirebaseDatabase.getInstance().getReference();
                                User user = new User();
                                user.setIdUser(id);
                                user.setLinkAvataUser(imageURL.toString());
                                user.setTenUser(name);
                                user.setListFriends(null);
                                database.child(Constanttt.USERS).child(id).setValue(user);
                                //luu data facebook vao share
                                edit_login.putString(Constanttt.LOGIN_ID, id);
                                edit_login.putString(Constanttt.LOGIN_NAME, name);
                                edit_login.putString(Constanttt.LOGIN_LINK_IMG, imageURL.toString());
                                edit_login.putString(Constanttt.LOGIN_LATITUDE, String.valueOf(loc.getLatitude()));
                                edit_login.putString(Constanttt.LOGIN_LONGITUDE, String.valueOf(loc.getLongitude()));
                                edit_login.apply();
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString(getString(R.string.fields), getString(R.string.fields_name));
                request.setParameters(parameters);
                request.executeAsync();
                startActivity(new Intent(LoginActivity.this, Main_Activity.class));
                //dang nhap xong thi finish activity nay
                finish();
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Login  canceled.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException e) {
                //Log.e(Constanttt.TAG_TAG,e.toString());
                Toast.makeText(LoginActivity.this, "Login failed.", Toast.LENGTH_LONG).show();
            }
        });
    }

    //lay url image
    public URL extractFacebookIcon(String id) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL imageURL = new URL("http://graph.facebook.com/" + id
                    + "/picture?type=large");
            return imageURL;
        } catch (Throwable e) {
            return null;
        }
    }

    //callback
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        LoginManager.getInstance().logInWithReadPermissions(
                this
                , Arrays.asList("public_profile", "user_friends", "email"));
    }

    private void turnGPSOn() {
        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean enabled = service
                .isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!enabled) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    this);
            alertDialogBuilder
                    .setMessage("BẠN CẦN BẬT GPS ĐỂ XÁC ĐỊNH VỊ TRÍ")
                    .setCancelable(false)
                    .setPositiveButton(getResources().getString(R.string.open),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    Intent callGPSSettingIntent = new Intent(
                                            android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                    startActivity(callGPSSettingIntent);
                                }
                            });
            alertDialogBuilder.setNegativeButton(getResources().getString(R.string.cancel),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = alertDialogBuilder.create();
            alert.show();
        }
    }

}
