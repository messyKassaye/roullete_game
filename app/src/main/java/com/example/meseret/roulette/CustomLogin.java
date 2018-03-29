package com.example.meseret.roulette;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.JsonReader;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.squareup.okhttp.ResponseBody;

import clients.UsersClient;
import clientsModel.Users;
import clientsModel.UsersData;
import clientsModel.UsersLogin;
import http.HTTPManager;
import http.UpdateRoulette6;
import model.ResponseMessage;
import projectstatic.ProjectStatic;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.Entity;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.PathModifier.Path;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.color.Color;
import org.andengine.util.level.constants.LevelConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomLogin extends SimpleBaseGameActivity {
    private final int CAMERA_HEIGHT = 480;
    private final int CAMERA_WIDTH = 800;
    private SpriteBackground background_image;
    private Gamers gamers;
    private BitmapTextureAtlas bottomBg;
    private ITextureRegion bottomBgRegion;
    private Sprite bottomSprite;
    private Button cancel_forgot;
    private Entity center_container;
    private Entity center_scrollbar;
    private Sprite center_transparency;
    private Text create_account;
    private boolean dialog_is_showen = false;
    private boolean dialog_not_showen = false;
    private Dialog dialogs;
    public EditText email;
    private TextView error_shower;
    private BitmapTextureAtlas first_scroll_atlas;
    private PathModifier first_scroll_modifier;
    private Path first_scroll_path;
    private ITextureRegion first_scroll_region;
    private Sprite first_scroll_sprite;
    private TextView forgot_error_value;
    private TextView forgot_pass;
    private LinearLayout forgot_pass_layout;
    private EditText forogt_email_address;
    private Handler handler = new Handler();
    private BitmapTextureAtlas help_atlas;
    private ITextureRegion help_region;
    private Text help_text;
    private ITextureRegion home;
    private Sprite home_sprite;
    private BitmapTextureAtlas homeatlas;
    private ITextureRegion howitworks;
    private Text howitworks_header_text;
    private Entity howitworks_panel;
    private Text howitworks_text;
    private BitmapTextureAtlas howitworksatlas;
    private BitmapTextureAtlas howitworkspanel_atlas;
    private ITextureRegion howitworkspanel_region;
    private BitmapTextureAtlas info_atlas;
    private ITextureRegion info_region;
    private Text info_text;
    private Text login;
    private ITextureRegion logo;
    private BitmapTextureAtlas logoatlas;
    private BitmapTextureAtlas mBackgroundTexture;
    private Camera mCamera;
    private Scene mScene;
    private LinearLayout main_login_layout;
    private boolean newversionavailable = false;
    private Font normal_font;
    public EditText password;
    private String password_value;
    private String passwords;
    private ProgressBar pr;
    private ProgressBar pr_bar;
    private ProgressBar prreg;
    private ITextureRegion region;
    private TextView registererror_shower;
    public EditText registerpassword;
    public EditText registerusername;
    private Sprite second_scrll_sprite;
    private BitmapTextureAtlas second_scroll_atlas;
    private PathModifier second_scroll_modifier;
    private Path second_scroll_path;
    private ITextureRegion second_scroll_region;
    private Button send_forgot_email;
    private String session;
    private SharedPreferences shared_data;
    private Font small_font;
    private BitmapTextureAtlas third_scroll_atlas;
    private PathModifier third_scroll_modifier;
    private Path third_scroll_path;
    private ITextureRegion third_scroll_region;
    private Sprite third_scroll_sprite;
    private TimerTask timerTask;
    private int timer_exact_value;
    private int timer_value = 1;
    private BitmapTextureAtlas topAtlas;
    private ITextureRegion topRegion;
    private Entity top_right_entity;
    private Sprite top_sprite;
    private UpdateRoulette6 update;
    private String user_name_value;
    private String user_names;
    public EditText username;
    private SharedPreferences version_holder;
    private double versioncode = 1.0d;

    class C02551 extends TimerTask {

        class C02511 implements Runnable {
            C02511() {
            }

            public void run() {
            }
        }

        class C02542 implements Runnable {

            class C02531 implements Runnable {

                class C02521 implements OnClickListener {
                    C02521() {
                    }

                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        CustomLogin.this.dialog_not_showen = false;
                    }
                }

                C02531() {
                }

                public void run() {
                    if (!CustomLogin.this.dialog_not_showen) {
                        Builder builder = new Builder(CustomLogin.this);
                        builder.setMessage("Connection is not available.please set your connection").setPositiveButton("OK", new C02521());
                        builder.show();
                        CustomLogin.this.dialog_not_showen = true;
                    }
                }
            }

            C02542() {
            }

            public void run() {
                CustomLogin.this.runOnUiThread(new C02531());
            }
        }

        C02551() {
        }

        public void run() {
            ConnectivityManager cm =
                    (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netinfo = cm.getActiveNetworkInfo();
            if (netinfo == null || !netinfo.isConnectedOrConnecting()) {
                CustomLogin.this.handler.postDelayed(new C02542(), 100);
            } else {
                CustomLogin.this.handler.postDelayed(new C02511(), 100);
            }
        }
    }

    class C02646 implements Runnable {

        class C02621 implements OnClickListener {
            C02621() {
            }

            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        }

        class C02632 implements OnClickListener {
            C02632() {
            }

            public void onClick(DialogInterface dialog, int id) {
                CustomLogin.this.update.execute(new String[0]);
            }
        }

        C02646() {
        }

        public void run() {
            Builder builder = new Builder(CustomLogin.this);
            builder.setMessage("There is newer version of this application available, click OK to upgrade now?").setPositiveButton("OK", new C02632()).setNegativeButton("Remind Later", new C02621());
            builder.create().show();
        }
    }

    class Create_account_task extends AsyncTask<String, String, String> {
        Create_account_task() {
        }

        protected void onPreExecute() {
            CustomLogin.this.prreg.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {
            return HTTPManager.REGISTER_USERS(params[0], params[1], params[2], params[3]);
        }

        protected void onPostExecute(String s) {
            if (s.equals("")) {
                CustomLogin.this.prreg.setVisibility(View.GONE);
                Toast.makeText(CustomLogin.this.getApplicationContext(), "Something is not good. please check your connection", Toast.LENGTH_LONG).show();
            } else if (s.contains(LevelConstants.TAG_LEVEL_ATTRIBUTE_NAME) && s.contains("email")) {
                CustomLogin.this.prreg.setVisibility(View.GONE);
                Intent intent = new Intent(CustomLogin.this, CustomProfile.class);
                intent.putExtra("json", s);
                CustomLogin.this.startActivity(intent);
                CustomLogin.this.finish();
                CustomLogin.this.finish();
            } else if (s.contains("email_exist")) {
                Toast.makeText(CustomLogin.this.getApplicationContext(), "your email address is already registered.please use different email address", Toast.LENGTH_LONG).show();
            }
            super.onPostExecute(s);
        }
    }

    public class Get_timer extends AsyncTask<String, String, String> {

        class C02661 implements Runnable {

            class C02651 implements OnClickListener {
                C02651() {
                }

                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                    System.exit(0);
                }
            }

            C02661() {
            }

            public void run() {
                Builder builder = new Builder(CustomLogin.this);
                builder.setMessage("Connection is not Available. we are going to close Roulette6").setPositiveButton("OK", new C02651());
                builder.create().show();
            }
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {
            return HTTPManager.GET_TIMER();
        }

        protected void onPostExecute(String jsonResponse) {
            String update = "no";
            if (jsonResponse != null) {
                int game_timer = Integer.parseInt(jsonResponse);
                Intent intent;
                if (game_timer <= 19) {
                    CustomLogin.this.pr.setVisibility(View.GONE);
                    intent = new Intent(CustomLogin.this, WaiterActivity.class);
                    intent.putExtra("content", CustomLogin.this.getSession());
                    intent.putExtra("timer", game_timer);
                    CustomLogin.this.startActivity(intent);
                    CustomLogin.this.finish();
                } else {
                    CustomLogin.this.pr.setVisibility(View.GONE);
                    intent = new Intent(CustomLogin.this, CustomProfile.class);
                    intent.putExtra("json", CustomLogin.this.getSession());
                    CustomLogin.this.startActivity(intent);
                    CustomLogin.this.finish();
                }
            } else if (!CustomLogin.this.dialog_is_showen) {
                CustomLogin.this.runOnUiThread(new C02661());
                CustomLogin.this.dialog_is_showen = true;
            }
            super.onPostExecute(jsonResponse);
        }
    }

    class Login_task extends AsyncTask<String, String, String> {
        Login_task() {
        }

        protected void onPreExecute() {
            CustomLogin.this.pr.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {
            return HTTPManager.LOGIN_USER(params[0], params[1]);
        }

        protected void onPostExecute(String s) {
            CustomLogin.this.pr.setVisibility(View.GONE);
            Intent intent = new Intent(CustomLogin.this, CustomProfile.class);
            intent.putExtra("json", s);
            CustomLogin.this.startActivity(intent);
            CustomLogin.this.finish();
            /*if (s.equals("no connection")) {
                CustomLogin.this.error_shower.setText("Something is not good. please check your connection");
                CustomLogin.this.pr.setVisibility(View.GONE);
            } else if (s.contains(LevelConstants.TAG_LEVEL_ATTRIBUTE_NAME)) {
                CustomLogin.this.pr.setVisibility(View.GONE);
                Intent intent = new Intent(CustomLogin.this, CustomProfile.class);
                intent.putExtra("json", s);
                CustomLogin.this.startActivity(intent);
                CustomLogin.this.finish();
            } else if (s.contains("fail")) {
                CustomLogin.this.error_shower.setText("Incorrect user name or password is used.try again");
            }*/
            super.onPostExecute(s);
        }
    }

    class Version_task extends AsyncTask<String, String, String> {
        Version_task() {
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {
            return HTTPManager.GET_GAME_VERSION();
        }

        protected void onPostExecute(String s) {
            if (s.equals("no")) {
                Toast.makeText(CustomLogin.this.getApplicationContext(), "" + 5, Toast.LENGTH_LONG).show();
                Toast.makeText(CustomLogin.this.getApplicationContext(), "No update", Toast.LENGTH_LONG).show();
            } else {
                CustomLogin.this.display_update(Integer.parseInt(s));
            }
            super.onPostExecute(s);
        }
    }

    class C05597 implements ITimerCallback {
        C05597() {
        }

        public void onTimePassed(TimerHandler pTimerHandler) {
            CustomLogin.this.timer_value = CustomLogin.this.timer_value + 1;
            if (CustomLogin.this.timer_value == 5) {
                CustomLogin.this.first_scroll_path = new Path(2).to(CustomLogin.this.first_scroll_sprite.getX(), CustomLogin.this.first_scroll_sprite.getY()).to(-652.0f, CustomLogin.this.first_scroll_sprite.getY());
                CustomLogin.this.first_scroll_modifier = new PathModifier(2.0f, CustomLogin.this.first_scroll_path);
                CustomLogin.this.first_scroll_sprite.registerEntityModifier(CustomLogin.this.first_scroll_modifier);
                CustomLogin.this.second_scroll_path = new Path(2).to(CustomLogin.this.second_scrll_sprite.getX(), CustomLogin.this.second_scrll_sprite.getY()).to(30.0f, CustomLogin.this.second_scrll_sprite.getY());
                CustomLogin.this.second_scroll_modifier = new PathModifier(2.0f, CustomLogin.this.second_scroll_path);
                CustomLogin.this.second_scrll_sprite.registerEntityModifier(CustomLogin.this.second_scroll_modifier);
                CustomLogin.this.third_scroll_sprite.setPosition(682.0f, 200.0f);
            } else if (CustomLogin.this.timer_value == 10) {
                CustomLogin.this.first_scroll_sprite.setPosition(682.0f, 200.0f);
                CustomLogin.this.second_scrll_sprite.registerEntityModifier(new PathModifier(2.0f, new Path(2).to(CustomLogin.this.second_scrll_sprite.getX(), CustomLogin.this.second_scrll_sprite.getY()).to(-652.0f, CustomLogin.this.second_scrll_sprite.getY())));
                CustomLogin.this.third_scroll_sprite.registerEntityModifier(new PathModifier(2.0f, new Path(2).to(CustomLogin.this.third_scroll_sprite.getX(), CustomLogin.this.third_scroll_sprite.getY()).to(30.0f, CustomLogin.this.third_scroll_sprite.getY())));
            } else if (CustomLogin.this.timer_value == 15) {
                CustomLogin.this.second_scrll_sprite.setPosition(682.0f, 200.0f);
                CustomLogin.this.third_scroll_sprite.registerEntityModifier(new PathModifier(2.0f, new Path(2).to(CustomLogin.this.third_scroll_sprite.getX(), CustomLogin.this.third_scroll_sprite.getY()).to(-652.0f, CustomLogin.this.second_scrll_sprite.getY())));
                CustomLogin.this.first_scroll_sprite.registerEntityModifier(new PathModifier(2.0f, new Path(2).to(CustomLogin.this.first_scroll_sprite.getX(), CustomLogin.this.first_scroll_sprite.getY()).to(30.0f, CustomLogin.this.third_scroll_sprite.getY())));
                CustomLogin.this.timer_value = 1;
            }
        }
    }

    public EngineOptions onCreateEngineOptions() {
        this.mCamera = new Camera(0.0f, 0.0f, 800.0f, 480.0f);
        //setRequestedOrientation(0);
        EngineOptions options = new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR, new FillResolutionPolicy(), this.mCamera);
        options.getAudioOptions().setNeedsMusic(true);
        options.getAudioOptions().setNeedsSound(true);
        return options;
    }

    protected void onCreateResources() throws IOException {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        BuildableBitmapTextureAtlas atlas = new BuildableBitmapTextureAtlas(this.mEngine.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        this.region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlas, (Context) this, "login_back.png");
        try {
            atlas.build(new BlackPawnTextureAtlasBuilder(0, 0, 0));
            atlas.load();
        } catch (Exception e) {
        }
        this.timerTask = new C02551();
        new Timer().schedule(this.timerTask, 0, 1000);
        this.topAtlas = new BitmapTextureAtlas(getTextureManager(), 1024, 52);
        this.topRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.topAtlas, (Context) this, "top_back.png", 0, 0);
        this.topAtlas.load();
        this.logoatlas = new BitmapTextureAtlas(getTextureManager(), 128, 42);
        this.logo = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.logoatlas, (Context) this, "roulette6.png", 0, 0);
        this.logoatlas.load();
        this.homeatlas = new BitmapTextureAtlas(getTextureManager(), 128, 42);
        this.home = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.homeatlas, (Context) this, "home.png", 0, 0);
        this.homeatlas.load();
        this.bottomBg = new BitmapTextureAtlas(getTextureManager(), 1024, 52);
        this.bottomBgRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bottomBg, (Context) this, "top_back.png", 0, 0);
        this.bottomBg.load();
        this.howitworksatlas = new BitmapTextureAtlas(getTextureManager(), 128, 42);
        this.howitworks = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.howitworksatlas, (Context) this, "howitworks.png", 0, 0);
        this.howitworksatlas.load();
        this.info_atlas = new BitmapTextureAtlas(getTextureManager(), 64, 32);
        this.info_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.info_atlas, (Context) this, "info.png", 0, 0);
        this.info_atlas.load();
        this.help_atlas = new BitmapTextureAtlas(getTextureManager(), 64, 32);
        this.help_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.help_atlas, (Context) this, "help.png", 0, 0);
        this.help_atlas.load();
        this.howitworkspanel_atlas = new BitmapTextureAtlas(getTextureManager(), 550, 350);
        this.howitworkspanel_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.howitworkspanel_atlas, (Context) this, "panel.png", 0, 0);
        this.howitworkspanel_atlas.load();
        this.first_scroll_atlas = new BitmapTextureAtlas(getTextureManager(), 512, 320);
        this.first_scroll_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.first_scroll_atlas, (Context) this, "firstimage.png", 0, 0);
        this.first_scroll_atlas.load();
        this.second_scroll_atlas = new BitmapTextureAtlas(getTextureManager(), 512, 320);
        this.second_scroll_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.second_scroll_atlas, (Context) this, "sharingimage.png", 0, 0);
        this.second_scroll_atlas.load();
        this.third_scroll_atlas = new BitmapTextureAtlas(getTextureManager(), 512, 320);
        this.third_scroll_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.third_scroll_atlas, (Context) this, "levelimage.png", 0, 0);
        this.third_scroll_atlas.load();
        this.normal_font = FontFactory.createFromAsset(this.mEngine.getFontManager(), this.mEngine.getTextureManager(), 258, 256, TextureOptions.BILINEAR, getAssets(), "ColabLig.otf", 25.0f, true, Color.WHITE_ABGR_PACKED_INT);
        this.normal_font.load();
        this.small_font = FontFactory.createFromAsset(this.mEngine.getFontManager(), this.mEngine.getTextureManager(), 256, 256, TextureOptions.BILINEAR, getAssets(), "ColabLig.otf", 20.0f, true, Color.WHITE_ABGR_PACKED_INT);
        this.small_font.load();
    }

    protected Scene onCreateScene() {
        this.mScene = new Scene();
        this.version_holder = getSharedPreferences("roulette6version", 0);
        Editor editor = this.version_holder.edit();
        editor.putString("version_code", "1.0");
        editor.apply();
        this.update = new UpdateRoulette6(this);
        this.top_sprite = new Sprite(this.topRegion.getWidth() / 2.0f, 480.0f - (this.topRegion.getHeight() / 2.0f), this.topRegion, this.mEngine.getVertexBufferObjectManager());
        this.mScene.attachChild(this.top_sprite);
        this.bottomSprite = new Sprite(this.bottomBgRegion.getWidth() / 2.0f, this.bottomBgRegion.getHeight() / 2.0f, this.bottomBgRegion, this.mEngine.getVertexBufferObjectManager());
        this.mScene.attachChild(this.bottomSprite);
        Sprite howitworks_sprite = new Sprite(70.0f, 25.0f, this.howitworks, this.mEngine.getVertexBufferObjectManager()) {
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) {
                CustomLogin.this.add_howitworkspanel();
                return true;
            }
        };
        this.mScene.registerTouchArea(howitworks_sprite);
        this.bottomSprite.attachChild(howitworks_sprite);
        Sprite sprite = new Sprite(350.0f, 20.0f, this.info_region, this.mEngine.getVertexBufferObjectManager());
        this.bottomSprite.attachChild(sprite);
        sprite = new Sprite((sprite.getX() + sprite.getWidth()) + 10.0f, 20.0f, this.help_region, this.mEngine.getVertexBufferObjectManager());
        this.bottomSprite.attachChild(sprite);
        this.home_sprite = new Sprite((sprite.getX() + sprite.getWidth()) + 10.0f, 20.0f, this.home, this.mEngine.getVertexBufferObjectManager()) {
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) {
                CustomLogin.this.add_scrolling_images();
                return true;
            }
        };
        this.mScene.registerTouchArea(this.home_sprite);
        this.home_sprite.setVisible(false);
        this.bottomSprite.attachChild(this.home_sprite);
        this.login = new Text(this.top_sprite.getWidth() - 500.0f, this.top_sprite.getHeight() - 25.0f, this.normal_font, "Login", this.mEngine.getVertexBufferObjectManager()) {

            class C02581 implements Runnable {

                class C02561 implements View.OnClickListener {
                    C02561() {
                    }

                    public void onClick(View v) {
                        CustomLogin.this.dialogs.dismiss();
                    }
                }

                class C02572 implements View.OnClickListener {
                    C02572() {
                    }

                    public void onClick(View v) {
                        String username_Value = CustomLogin.this.username.getText().toString().trim();
                        String password_value = CustomLogin.this.password.getText().toString().trim();
                        if (username_Value.equals("")) {
                            CustomLogin.this.error_shower.setText("Please enter user name");
                        } else if (password_value.equals("")) {
                            CustomLogin.this.error_shower.setText("Please enter password");
                        } else {
                            CustomLogin.this.setUser_names(username_Value);
                            CustomLogin.this.setPasswords(password_value);
                            new Login_task().execute(new String[]{username_Value, password_value});
                        }
                    }
                }

                C02581() {
                }

                public void run() {
                    CustomLogin.this.dialogs = new Dialog(CustomLogin.this);
                    CustomLogin.this.dialogs.requestWindowFeature(1);
                    CustomLogin.this.dialogs.setContentView(R.layout.custom_login_layout);
                    CustomLogin.this.username = (EditText) CustomLogin.this.dialogs.findViewById(R.id.dialog_username_input);
                    CustomLogin.this.password = (EditText) CustomLogin.this.dialogs.findViewById(R.id.dialog_password_input);
                    CustomLogin.this.error_shower = (TextView) CustomLogin.this.dialogs.findViewById(R.id.error_value);
                    CustomLogin.this.pr = (ProgressBar) CustomLogin.this.dialogs.findViewById(R.id.dialog_login_pr);
                    Button login = (Button) CustomLogin.this.dialogs.findViewById(R.id.dialog_login_button);
                    Button cancel = (Button) CustomLogin.this.dialogs.findViewById(R.id.dialog_cancel_button);
                    CustomLogin.this.dialogs.show();
                    cancel.setOnClickListener(new C02561());
                    login.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String username_Value = CustomLogin.this.username.getText().toString().trim();
                            String password_value = CustomLogin.this.password.getText().toString().trim();
                            if (username_Value.equals("")) {
                                CustomLogin.this.error_shower.setText("Please enter user name");
                            } else if (password_value.equals("")) {
                                CustomLogin.this.error_shower.setText("Please enter password");
                            } else {
                                pr.setVisibility(View.VISIBLE);
                                UsersLogin login1=new UsersLogin(username_Value,password_value);
                                CustomLogin.this.setUser_names(username_Value);
                                CustomLogin.this.setPasswords(password_value);
                                Retrofit retrofit=getAuthUser();
                                UsersClient client=retrofit.create(UsersClient.class);
                                Call<ResponseMessage> call=client.login(login1);
                                call.enqueue(new Callback<ResponseMessage>() {
                                    @Override
                                    public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                                      if (response.body().getStatus().equals("ok")){
                                          getUsersData(response.body().getToken(),"login");
                                      }else if (response.body().getStatus().equals("unknown")){
                                          pr.setVisibility(View.GONE);
                                          error_shower.setText("Incorrect user name or password is used.");
                                      }else {
                                          Toast.makeText(getApplicationContext(),"Something is not Good.Please check your connection",Toast.LENGTH_LONG).show();
                                      }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseMessage> call, Throwable t) {

                                    }
                                });
                                //new Login_task().execute(new String[]{username_Value, password_value});
                            }
                        }
                    });
                }
            }

            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) {
                if (pSceneTouchEvent.isActionUp()) {
                    CustomLogin.this.runOnUiThread(new C02581());
                }
                return true;
            }
        };
        this.login.setColor(android.graphics.Color.rgb(255, 255, 112));
        this.top_sprite.attachChild(this.login);
        this.create_account = new Text((this.login.getX() + this.login.getWidth()) + 100.0f, this.top_sprite.getHeight() - 25.0f, this.normal_font, "Create Account", this.mEngine.getVertexBufferObjectManager()){
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                if(pSceneTouchEvent.isActionUp()) {
                    CustomLogin.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final Dialog dialog = new Dialog(CustomLogin.this);
                            dialog.requestWindowFeature(1);
                            dialog.setContentView(R.layout.register_users_dialog);
                            CustomLogin.this.registerusername = (EditText) dialog.findViewById(R.id.dialog_registerusername_input);
                            CustomLogin.this.email = (EditText) dialog.findViewById(R.id.dialog_registeremail_input);
                            CustomLogin.this.registerpassword = (EditText) dialog.findViewById(R.id.dialog_registerpassword_input);
                            CustomLogin.this.error_shower = (TextView) dialog.findViewById(R.id.registererror_value);
                            CustomLogin.this.prreg = (ProgressBar) dialog.findViewById(R.id.proregister);
                            Button login = (Button) dialog.findViewById(R.id.registerdialog_login_button);
                            Button cancel=(Button)dialog.findViewById(R.id.registerdialog_cancel_button);
                            cancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
                                }
                            });
                            login.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    String username_Value = CustomLogin.this.registerusername.getText().toString().trim();
                                    String email_value = CustomLogin.this.email.getText().toString().trim();
                                    String password_value = CustomLogin.this.registerpassword.getText().toString().trim();
                                    if (username_Value.equals("")) {
                                        CustomLogin.this.error_shower.setText("Please enter user name");
                                    } else if (email_value.equals("")) {
                                        CustomLogin.this.error_shower.setText("Please enter your email");
                                    } else if (!CustomLogin.isValidEmail(email_value)) {
                                        CustomLogin.this.error_shower.setText("Incorrect email address");
                                    } else if (password_value.equals("")) {
                                        CustomLogin.this.error_shower.setText("Please enter password");
                                    } else if (CustomLogin.this.is_connected()) {
                                        prreg.setVisibility(View.VISIBLE);
                                        Retrofit retrofit = getAuthUser();
                                        UsersClient client = retrofit.create(UsersClient.class);
                                        Users users = new Users(username_Value, email_value, password_value);
                                        Call<ResponseMessage> call= client.signup(users);
                                        call.enqueue(new Callback<ResponseMessage>() {
                                            @Override
                                            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                                            if (response.body().getStatus().equals("ok")){
                                                   getUsersData(response.body().getToken(),"re");
                                               }else if (response.body().getStatus().equals("exist")){
                                                   prreg.setVisibility(View.GONE);
                                                   error_shower.setText("Someone registered by this email address.please change your email and register):");
                                               }
                                            }

                                            @Override
                                            public void onFailure(Call<ResponseMessage> call, Throwable t) {
                                               Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                                            }
                                        });
                                    }



                                }
                            });
                            dialog.show();
                        }
                    });
                }
                return true;
              };
        };
        this.mScene.registerTouchArea(this.create_account);
        this.create_account.setColor(android.graphics.Color.rgb(255, 255, 112));
        this.top_sprite.attachChild(this.create_account);
        this.top_sprite.attachChild(new Sprite(100.0f, 28.0f, this.logo, this.mEngine.getVertexBufferObjectManager()));
        this.center_container = new Entity();
        float scrollXPosition = this.region.getWidth() / 2.0f;
        float scrollYposition = this.region.getHeight() / 2.0f;
        this.center_container.setPosition(380.0f, 40.0f);
        this.mScene.attachChild(this.center_container);
        this.center_scrollbar = new Entity();
        this.first_scroll_sprite = new Sprite(30.0f, 200.0f, this.first_scroll_region, this.mEngine.getVertexBufferObjectManager());
        this.center_scrollbar.attachChild(this.first_scroll_sprite);
        this.second_scrll_sprite = new Sprite((this.first_scroll_sprite.getX() + this.first_scroll_sprite.getWidth()) + 140.0f, 200.0f, this.second_scroll_region, this.mEngine.getVertexBufferObjectManager());
        this.center_scrollbar.attachChild(this.second_scrll_sprite);
        this.third_scroll_sprite = new Sprite((this.second_scrll_sprite.getX() + this.second_scrll_sprite.getWidth()) + 140.0f, 200.0f, this.third_scroll_region, this.mEngine.getVertexBufferObjectManager());
        this.center_scrollbar.attachChild(this.third_scroll_sprite);
        this.center_container.attachChild(this.center_scrollbar);
        this.mScene.registerTouchArea(this.login);
        this.center_transparency = new Sprite(20.0f, 200.0f, this.howitworkspanel_region, this.mEngine.getVertexBufferObjectManager());
        this.center_transparency.setWidth(650.0f);
        this.center_transparency.setHeight(300.0f);
        this.howitworks_header_text = new Text(100.0f, this.center_transparency.getHeight() - 20.0f, this.normal_font, "How it works", this.mEngine.getVertexBufferObjectManager());
        this.center_transparency.attachChild(this.howitworks_header_text);
        this.howitworks_text = new Text(0.0f, 0.0f, this.small_font, "Roulette6 Live casino game use Bitcoin currency.To play Roulette6 you have \nto register your Wallet address to Roulette6 society. After Registering your \nwallet to us we will give you 0.00115225 bitcoin for start up which is equal to 5$.\nThe match of Roulette6 is done using chips which means your bitcoin amount \nwill be changed into chips using our chips market of bitcoin. When you want \nto transact your account to your wallet address we will convert your chips \namount to bitcoin and send to you wallet address.", this.mEngine.getVertexBufferObjectManager());
        this.howitworks_text.setPosition(325.0f, this.center_transparency.getHeight() - 110.0f);
        this.center_transparency.attachChild(this.howitworks_text);
        registerScenUpdateHandler(this.mScene);
        if (is_connected()) {
            new Version_task().execute(new String[0]);
        }
        this.mScene.setBackground(new SpriteBackground(new Sprite(this.region.getWidth() / 2.0f, this.region.getHeight() / 2.0f, this.region, getVertexBufferObjectManager())));
        return this.mScene;
    }
    public void getUsersData(final String token, final String type){
        Retrofit retrofit= getAPIUser();
        UsersClient client= retrofit.create(UsersClient.class);
        Call<UsersData> call=client.getUsersInfo(token);
        call.enqueue(new Callback<UsersData>() {
            @Override
            public void onResponse(Call<UsersData> call, Response<UsersData> response) {
                if (!response.body().getUser_name().equals("")){
                    if (type.equals("re")){
                        prreg.setVisibility(View.GONE);
                    }else {
                        pr.setVisibility(View.GONE);
                    }
                    Intent intent = new Intent(CustomLogin.this,MainProfile.class);
                    intent.putExtra("user_name",response.body().getUser_name());
                    intent.putExtra("email",response.body().getEmail());
                    intent.putExtra("amount",response.body().getAmount());
                    intent.putExtra("referral_code",response.body().getReferal_code());
                    intent.putExtra("bitcoin_address",response.body().getBitcoin_address());
                    intent.putExtra("token",token);
                    startActivity(intent);
                    finish();
                }else {
                   if (type.equals("re")){
                       error_shower.setText("Something is not Good.please check your connection):");
                   }else {
                       error_shower.setText("Something is not Good.please check your connection):");
                   }
                }
            }

            @Override
            public void onFailure(Call<UsersData> call, Throwable t) {

            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.dialogs != null && this.dialogs.isShowing()) {
            this.dialogs.cancel();
        }
    }

    public Retrofit getAPIUser(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(ProjectStatic.API_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson));

        Retrofit retrofit =builder.build();
        return  retrofit;
    }
    public Retrofit getAuthUser(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder =
                        new Retrofit.Builder()
                        .baseUrl(ProjectStatic.AUTH_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson));

        Retrofit retrofit =builder.build();
        return  retrofit;
    }

    public void display_update(int latest_versioncode) {
        if (latest_versioncode != 5) {
            runOnUiThread(new C02646());
        }
    }

    public String getSession() {
        return this.session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getUser_names() {
        return this.user_names;
    }

    public void setUser_names(String user_names) {
        this.user_names = user_names;
    }

    public String getPasswords() {
        return this.passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public void transfer_to_addcode(String username, String email, String pass) {
        Intent intent = new Intent(this, Add_Referral_code.class);
        intent.putExtra("username", username);
        intent.putExtra("email", email);
        intent.putExtra("password", pass);
        startActivity(intent);
    }

    public boolean isNewversionavailable() {
        return this.newversionavailable;
    }

    public void setNewversionavailable(boolean newversionavailable) {
        this.newversionavailable = newversionavailable;
    }

    public int getTimer_exact_value() {
        return this.timer_exact_value;
    }

    public void setTimer_exact_value(int timer_exact_value) {
        this.timer_exact_value = timer_exact_value;
    }

    public void add_scrolling_images() {
        this.home_sprite.setVisible(false);
        this.center_container.detachChildren();
        this.center_container.attachChild(this.center_scrollbar);
    }

    public void add_howitworkspanel() {
        this.center_container.detachChildren();
        this.center_container.attachChild(this.center_transparency);
        this.home_sprite.setVisible(true);
    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    public void registerScenUpdateHandler(Scene scene) {
        scene.registerUpdateHandler(new TimerHandler(1.0f, true, new C05597()));
    }

    public boolean is_connected() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net_info = cm.getActiveNetworkInfo();
        if (net_info == null || !net_info.isConnectedOrConnecting()) {
            return false;
        }
        return true;
    }

    public static final boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        }
        return Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public String getUser_name_value() {
        return this.user_name_value;
    }

    public void setUser_name_value(String user_name_value) {
        this.user_name_value = user_name_value;
    }

    public String getPassword_value() {
        return this.password_value;
    }

    public void setPassword_value(String password_value) {
        this.password_value = password_value;
    }
}