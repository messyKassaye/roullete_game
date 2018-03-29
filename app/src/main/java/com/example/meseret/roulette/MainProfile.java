package com.example.meseret.roulette;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.ResponseBody;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.AnimatedSprite;
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
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.color.Color;
import org.andengine.util.adt.color.constants.ColorConstants;
import org.andengine.util.level.constants.LevelConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.TimerTask;

import clients.UsersClient;
import clientsModel.AddressModel;
import clientsModel.Timer;
import http.HTTPManager;
import model.ResponseMessage;
import projectstatic.ProjectStatic;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainProfile extends SimpleBaseGameActivity {
    private final int CAMERA_HEIGHT = 480;
    private final int CAMERA_WIDTH = 800;


    Button add_address;
    private ProgressBar add_bar;
    private SpriteBackground background_image;
    private Font big_font;
    private EditText bitcoin_address;
    private BitmapTextureAtlas bottomBg;
    private ITextureRegion bottomBgRegion;
    private Sprite bottomSprite;
    private BitmapTextureAtlas center_transparency;
    private ITextureRegion center_transparency_region;
    private Sprite center_transparencys;
    private Text chips_value;
    private BitmapTextureAtlas close;
    private ITextureRegion close_region;
    private Sprite close_sprite;
    private Text commision_value;
    private ITextureRegion commission;
    private BitmapTextureAtlas commission_atlas;
    private String content;
    private Entity dash_board;
    private Dialog dialog;
    private TextView dialog_error;
    private boolean dialog_is_showen = false;
    private ImageView email;
    private LinearLayout emailiconlayout;
    private LinearLayout emaillayout;
    private TextView error;
    private Gamers gamers;
    private ITextureRegion get_chips;
    private BitmapTextureAtlas get_chips_atlas;
    private Handler handler = new Handler();
    private BitmapTextureAtlas help_atlas;
    private ITextureRegion help_region;
    private Text inbitcoin;
    private Text indollar;
    private BitmapTextureAtlas info_atlas;
    private ITextureRegion info_region;
    private Entity left_entity;
    private ITextureRegion level;
    private BitmapTextureAtlas level_atlas;
    private BitmapTextureAtlas levels_atlas;
    private ITextureRegion levels_region;
    private Text levels_value;
    private ITextureRegion logout;
    private BitmapTextureAtlas logout_atlas;
    private Text logoutmenu;
    private BitmapTextureAtlas mBackgroundTexture;
    private Camera mCamera;
    private Font mFont;
    private Scene mScene;
    private Entity main_center_entity;
    private ITextureRegion menu;
    private PathModifier.Path menu_path;
    private PathModifier menu_path_modifier;
    private Text menu_profile_name;
    private BitmapTextureAtlas menuatlas;
    private ITextureRegion menubackcontainer;
    private BitmapTextureAtlas menucontaineratlas;
    private Sprite menucontainersprite;
    private BitmapTextureAtlas menuprofile;
    private ITextureRegion menuprofile_region;
    private Sprite menuprofilesprite;
    private Sprite menusprite;
    private Line myaccount_line;
    private Entity myaccount_menu_item_entity;
    private BitmapTextureAtlas myaccount_menu_left_icon_atlas;
    private ITextureRegion myaccount_menu_left_icon_region;
    private Sprite myaccount_menu_left_icon_sprite;
    private Text myaccountmenu;
    private Line mycommission;
    private Entity mycommission_menu_item_entity;
    private BitmapTextureAtlas mycommission_menu_left_atlas;
    private ITextureRegion mycommission_menu_left_icon_region;
    private Sprite mycommission_menu_left_sprite;
    private Text mycommission_name;
    private Text mycommissionmenu;
    private Line mylevel;
    private Entity mylevel_menu_item_entity;
    private BitmapTextureAtlas mylevel_menu_left_icon_atlas;
    private ITextureRegion mylevel_menu_left_icon_region;
    private Sprite mylevel_menu_left_sprite;
    private Text mylevel_name;
    private Text mylevelmenu;
    private Line mylogout_line;
    private Entity mylogout_menu_item_entity;
    private BitmapTextureAtlas mylogout_menu_left_icon_atlas;
    private ITextureRegion mylogout_menu_left_icon_region;
    private Sprite mylogout_menu_left_sprite;
    private Text mylogout_name;
    private Line mysetting_line;
    private Entity mysetting_menu_item_entity;
    private BitmapTextureAtlas mysetting_menu_left_icon_left_atlas;
    private ITextureRegion mysetting_menu_left_icon_region;
    private Sprite mysetting_menu_left_icon_sprite;
    private Text mysetting_name;
    private Line mywithdrawal_line;
    private Entity mywithdrawal_menu_item_enity;
    private BitmapTextureAtlas mywithdrawal_menu_left_icon_atlas;
    private ITextureRegion mywithdrawal_menu_left_icon_region;
    private Sprite mywithdrawal_menu_left_icon_sprite;
    private Text mywithdrawal_name;
    private String name;
    private BitmapTextureAtlas nav_profile_atlas;
    private ITextureRegion nav_profile_region;
    private Sprite nav_profile_sprite;
    private Text networth;
    private Font networth_value_font;
    private Font normal_font;
    private ITextureRegion notification;
    private BitmapTextureAtlas notification_atlas;
    private Font or_font;
    private AnimatedSprite play_animate;
    private BitmapTextureAtlas play_atlas;
    private ITextureRegion play_region;
    private ITiledTextureRegion play_tiled;
    private BitmapTextureAtlas play_tiled_atlas;
    private Text playerName;
    private BitmapTextureAtlas profileAtlas;
    private ITextureRegion profileRegion;
    private ITextureRegion region;
    private Sprite register;
    private ITextureRegion register_region;
    private BitmapTextureAtlas registeratla;
    private Dialog send_dialog;
    private Button send_email;
    private ITextureRegion setting;
    private BitmapTextureAtlas setting_atlas;
    private Text settingmenu;
    private BitmapTextureAtlas share_atlas;
    private EditText share_email_address;
    private ITextureRegion share_region;
    private ImageView shareemail;
    private ImageView sharewatsapp;
    private Font small_font;
    private TimerTask timerTask;
    private BitmapTextureAtlas topAtlas;
    private ITextureRegion topRegion;
    private Entity top_right_entity;
    private Sprite top_sprite;
    private ImageView watsapp;
    private LinearLayout watsapplayout;
    private Text welcome;
    private Text your_commision;
    private Text your_levels;
    private String token;
    private Intent intent;

    @Override
    public EngineOptions onCreateEngineOptions() {
        this.mCamera = new Camera(0.0f, 0.0f, 800.0f, 480.0f);
        //setRequestedOrientation(0);
        EngineOptions options = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new FillResolutionPolicy(), this.mCamera);
        options.getAudioOptions().setNeedsMusic(true);
        options.getAudioOptions().setNeedsSound(true);
        return options;
    }

    @Override
    protected void onCreateResources() throws IOException {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        BuildableBitmapTextureAtlas atlas = new BuildableBitmapTextureAtlas(this.mEngine.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        this.region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlas,this, "cards_texture.jpg");
        try {
            atlas.build(new BlackPawnTextureAtlasBuilder(0, 0, 0));
            atlas.load();
        } catch (Exception e) {
        }

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        this.topAtlas = new BitmapTextureAtlas(getTextureManager(), 1024, 52);
        this.topRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.topAtlas,this, "top_back.png", 0, 0);
        this.topAtlas.load();
        this.profileAtlas = new BitmapTextureAtlas(getTextureManager(), 42, 42);
        this.profileRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.profileAtlas, this, "profile.png", 0, 0);
        this.profileAtlas.load();
        this.nav_profile_atlas = new BitmapTextureAtlas(getTextureManager(), 42, 42);
        this.nav_profile_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.nav_profile_atlas, this, "profile.png", 0, 0);
        this.nav_profile_atlas.load();
        this.menuatlas = new BitmapTextureAtlas(getTextureManager(), 32, 24);
        this.menu = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.menuatlas,  this, "menu.png", 0, 0);
        this.menuatlas.load();
        this.menucontaineratlas = new BitmapTextureAtlas(getTextureManager(), 512, 256);
        this.menubackcontainer = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.menucontaineratlas, this, "menuback.png", 0, 0);
        this.menucontaineratlas.load();
        this.registeratla = new BitmapTextureAtlas(getTextureManager(), 128, 42);
        this.register_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.registeratla, this, "register.png", 0, 0);
        this.registeratla.load();
        this.setting_atlas = new BitmapTextureAtlas(getTextureManager(), 32, 32);
        this.setting = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.setting_atlas, this, "setting.png", 0, 0);
        this.setting_atlas.load();
        this.menuprofile = new BitmapTextureAtlas(getTextureManager(), 128, 128);
        this.menuprofile_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.menuprofile,this, "menuprofile.png", 0, 0);
        this.menuprofile.load();
        this.notification_atlas = new BitmapTextureAtlas(getTextureManager(), 25, 25);
        this.notification = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.notification_atlas,this, "notification.png", 0, 0);
        this.notification_atlas.load();
        this.logout_atlas = new BitmapTextureAtlas(getTextureManager(), 25, 25);
        this.logout = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.logout_atlas,this, "logout.png", 0, 0);
        this.logout_atlas.load();
        this.bottomBg = new BitmapTextureAtlas(getTextureManager(), 1024, 52);
        this.bottomBgRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bottomBg,this, "top_back.png", 0, 0);
        this.bottomBg.load();
        this.center_transparency = new BitmapTextureAtlas(getTextureManager(), 480, 256);
        this.center_transparency_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.center_transparency, this, "transparency.png", 0, 0);
        this.center_transparency.load();
        this.mFont = FontFactory.createFromAsset(this.mEngine.getFontManager(), this.mEngine.getTextureManager(), 256, 256, TextureOptions.BILINEAR, getAssets(), "Aramis Italic.ttf", 26.0f, true, Color.WHITE_ABGR_PACKED_INT);
        this.mFont.load();
        this.big_font = FontFactory.createFromAsset(this.mEngine.getFontManager(), this.mEngine.getTextureManager(), 256, 256, TextureOptions.BILINEAR, getAssets(), "Aramis Italic.ttf", 52.0f, true, Color.WHITE_ABGR_PACKED_INT);
        this.big_font.load();
        this.normal_font = FontFactory.createFromAsset(this.mEngine.getFontManager(), this.mEngine.getTextureManager(), 258, 256, TextureOptions.BILINEAR, getAssets(), "ColabLig.otf", 25.0f, true, Color.WHITE_ABGR_PACKED_INT);
        this.normal_font.load();
        this.networth_value_font = FontFactory.createFromAsset(this.mEngine.getFontManager(), this.mEngine.getTextureManager(), 258, 256, TextureOptions.BILINEAR, getAssets(), "ColabLig.otf", 20.0f, true, Color.WHITE_ABGR_PACKED_INT);
        this.networth_value_font.load();
        this.or_font = FontFactory.createFromAsset(this.mEngine.getFontManager(), this.mEngine.getTextureManager(), 258, 256, TextureOptions.BILINEAR, getAssets(), "ColabLig.otf", 20.0f, true, Color.WHITE_ABGR_PACKED_INT);
        this.or_font.load();
        this.small_font = FontFactory.createFromAsset(this.mEngine.getFontManager(), this.mEngine.getTextureManager(), 256, 256, TextureOptions.BILINEAR, getAssets(), "ColabLig.otf", 20.0f, true, Color.WHITE_ABGR_PACKED_INT);
        this.small_font.load();
        this.myaccount_menu_left_icon_atlas = new BitmapTextureAtlas(getTextureManager(), 18, 24);
        this.myaccount_menu_left_icon_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.myaccount_menu_left_icon_atlas, this, "menudollaicon.png", 0, 0);
        this.myaccount_menu_left_icon_atlas.load();
        this.mycommission_menu_left_atlas = new BitmapTextureAtlas(getTextureManager(), 18, 24);
        this.mycommission_menu_left_icon_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mycommission_menu_left_atlas, this, "mycommission.png", 0, 0);
        this.mycommission_menu_left_atlas.load();
        this.mylevel_menu_left_icon_atlas = new BitmapTextureAtlas(getTextureManager(), 18, 24);
        this.mylevel_menu_left_icon_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mylevel_menu_left_icon_atlas, this, "mylevel.png", 0, 0);
        this.mylevel_menu_left_icon_atlas.load();
        this.mysetting_menu_left_icon_left_atlas = new BitmapTextureAtlas(getTextureManager(), 24, 24);
        this.mysetting_menu_left_icon_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mysetting_menu_left_icon_left_atlas, this, "mysetting.png", 0, 0);
        this.mysetting_menu_left_icon_left_atlas.load();
        this.mywithdrawal_menu_left_icon_atlas = new BitmapTextureAtlas(getTextureManager(), 18, 24);
        this.mywithdrawal_menu_left_icon_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mywithdrawal_menu_left_icon_atlas,this, "mywithdrawal.png", 0, 0);
        this.mywithdrawal_menu_left_icon_atlas.load();
        this.mylogout_menu_left_icon_atlas = new BitmapTextureAtlas(getTextureManager(), 18, 24);
        this.mylogout_menu_left_icon_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mylogout_menu_left_icon_atlas, this, "mylogout.png", 0, 0);
        this.mylogout_menu_left_icon_atlas.load();
        this.play_atlas = new BitmapTextureAtlas(getTextureManager(), 78, 27);
        this.play_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.play_atlas, this, "play1.png", 0, 0);
        this.play_atlas.load();
        this.play_tiled_atlas = new BitmapTextureAtlas(getTextureManager(), 256, 32);
        this.play_tiled = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.play_tiled_atlas,this, "play.png", 0, 0, 2, 1);
        this.play_tiled_atlas.load();
        this.share_atlas = new BitmapTextureAtlas(getTextureManager(), 64, 32);
        this.share_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.share_atlas,  this, "share.png", 0, 0);
        this.share_atlas.load();
        this.info_atlas = new BitmapTextureAtlas(getTextureManager(), 64, 32);
        this.info_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.info_atlas, this, "info.png", 0, 0);
        this.info_atlas.load();
        this.close = new BitmapTextureAtlas(getTextureManager(), 24, 24);
        this.close_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.close, this, "close.png", 0, 0);
        this.close.load();
        this.help_atlas = new BitmapTextureAtlas(getTextureManager(), 64, 32);
        this.help_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.help_atlas, this, "help.png", 0, 0);
        this.help_atlas.load();
        this.get_chips_atlas = new BitmapTextureAtlas(getTextureManager(), 128, 32);
        this.get_chips = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.get_chips_atlas,this, "getchips.png", 0, 0);
        this.get_chips_atlas.load();
        this.commission_atlas = new BitmapTextureAtlas(getTextureManager(), 80, 29);
        this.commission = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.commission_atlas, this, "commision.png", 0, 0);
        this.commission_atlas.load();
        this.levels_atlas = new BitmapTextureAtlas(getTextureManager(), 80, 29);
        this.level = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.levels_atlas, this, "level.png", 0, 0);
        this.levels_atlas.load();

    }

    @Override
    protected Scene onCreateScene() {
        mScene=new Scene();
        intent = getIntent();
        token=intent.getStringExtra("token");


        if (intent.getStringExtra("bitcoin_address").equals("0")) {
            this.welcome = new Text(220.0f, (float) (this.center_transparency.getHeight() - 90), this.small_font, "Hello, " +intent.getStringExtra("user_name")+ " Wel come to Roulette6\nThank you for choosing us.\n" + intent.getStringExtra("user_name")+ " our game uses maxocoin cryptocurrency for any \ntransactions when you win and lose during playing \nthe game.Register your wallet address and start \nplaying and increase your income.\nClick Register wallet address to register your address\nand to earn more maxocoin", this.mEngine.getVertexBufferObjectManager());
        } else {
            this.welcome = new Text(200.0f, (float) (this.center_transparency.getHeight() - 50), this.small_font, "Hi, " + intent.getStringExtra("user_name")+ " How are you today\nYou Have " + intent.getStringExtra("amount") + " chips in your amount\n start playing and increase your wallet amount", this.mEngine.getVertexBufferObjectManager());
        }
        this.main_center_entity = new Entity();
        this.top_sprite = new Sprite(this.topRegion.getWidth() / 2.0f, 480.0f - (this.topRegion.getHeight() / 2.0f), this.topRegion, this.mEngine.getVertexBufferObjectManager());
        this.mScene.attachChild(this.top_sprite);
        Sprite sprite = new Sprite(20.0f, 25.0f, this.profileRegion, this.mEngine.getVertexBufferObjectManager());
        this.playerName = new Text(sprite.getWidth() + 40.0f, 15.0f, this.mFont,intent.getStringExtra("user_name"), this.mEngine.getVertexBufferObjectManager());
        this.playerName.setColor(android.graphics.Color.rgb(234, 197, 137));
        this.top_sprite.attachChild(this.playerName);
        this.top_sprite.attachChild(sprite);

        //===============================
        //adding the menu humberger
        //=============================
        this.menucontainersprite = new Sprite(950.0f, (this.region.getHeight() / 2.0f) - 80.0f, this.menubackcontainer, this.mEngine.getVertexBufferObjectManager());
        this.menucontainersprite.setWidth(300.0f);
        this.menucontainersprite.setHeight(480.0f);
        this.menuprofilesprite = new Sprite(this.menucontainersprite.getWidth() / 2.0f, (this.menucontainersprite.getHeight() / 2.0f) + 150.0f, this.menuprofile_region, this.mEngine.getVertexBufferObjectManager());
        this.menucontainersprite.attachChild(this.menuprofilesprite);
        this.close_sprite = new Sprite((this.menucontainersprite.getWidth() - (this.close_region.getWidth() / 2.0f)) - 10.0f, this.menucontainersprite.getHeight() - 20.0f, this.close_region, this.mEngine.getVertexBufferObjectManager());

        float menuXpos = this.topRegion.getWidth() / 2.0f;
        this.menucontainersprite = new Sprite(950.0f, (this.region.getHeight() / 2.0f) - 80.0f, this.menubackcontainer, this.mEngine.getVertexBufferObjectManager());
        this.menucontainersprite.setWidth(300.0f);
        this.menucontainersprite.setHeight(480.0f);
        this.menuprofilesprite = new Sprite(this.menucontainersprite.getWidth() / 2.0f, (this.menucontainersprite.getHeight() / 2.0f) + 150.0f, this.menuprofile_region, this.mEngine.getVertexBufferObjectManager());
        this.menucontainersprite.attachChild(this.menuprofilesprite);

        this.close_sprite = new Sprite((this.menucontainersprite.getWidth() - (this.close_region.getWidth() / 2.0f)) - 10.0f, this.menucontainersprite.getHeight() - 20.0f, this.close_region, this.mEngine.getVertexBufferObjectManager()) {
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) {
                if (pSceneTouchEvent.isActionUp()) {
                    MainProfile.this.menu_path = new PathModifier.Path(2).to(MainProfile.this.menucontainersprite.getX(), MainProfile.this.menucontainersprite.getY()).to(950.0f, MainProfile.this.menucontainersprite.getY());
                    MainProfile.this.menu_path_modifier = new PathModifier(0.5f, MainProfile.this.menu_path);
                    MainProfile.this.menucontainersprite.registerEntityModifier(MainProfile.this.menu_path_modifier);
                }
                return true;
            }
        };

        this.mScene.registerTouchArea(this.close_sprite);
        this.menucontainersprite.attachChild(this.close_sprite);

        this.menusprite = new Sprite(this.top_sprite.getWidth() - ColorConstants.COLOR_FACTOR_INT_TO_FLOAT, this.top_sprite.getHeight() - 25.0f, this.menu, this.mEngine.getVertexBufferObjectManager()) {
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) {
                if (pSceneTouchEvent.isActionUp()) {
                    MainProfile.this.menu_path = new PathModifier.Path(2).to(MainProfile.this.menucontainersprite.getX(), MainProfile.this.menucontainersprite.getY()).to(650.0f, MainProfile.this.menucontainersprite.getY());
                    MainProfile.this.menu_path_modifier = new PathModifier(0.5f, MainProfile.this.menu_path);
                    MainProfile.this.menucontainersprite.registerEntityModifier(MainProfile.this.menu_path_modifier);
                }
                return true;
            }
        };
        this.mScene.registerTouchArea(this.menusprite);
        this.top_sprite.attachChild(this.menusprite);


        this.menu_profile_name = new Text(this.menucontainersprite.getWidth() / 2.0f, (this.menucontainersprite.getHeight() / 2.0f) + 70.0f, this.big_font,intent.getStringExtra("user_name"), this.mEngine.getVertexBufferObjectManager());
        this.menucontainersprite.attachChild(this.menu_profile_name);
        this.nav_profile_sprite = new Sprite(this.menuprofilesprite.getWidth() / 2.0f, 70.0f, this.nav_profile_region, this.mEngine.getVertexBufferObjectManager()) {
            protected void preDraw(GLState pGLState, Camera pCamera) {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        };
        this.nav_profile_sprite.setWidth(100.0f);
        this.nav_profile_sprite.setHeight(100.0f);
        this.menuprofilesprite.attachChild(this.nav_profile_sprite);

        this.myaccount_menu_item_entity = new Entity();
        this.myaccount_menu_item_entity.setPosition(0.0f, 250.0f);
        this.myaccount_menu_left_icon_sprite = new Sprite(20.0f, 15.0f, this.myaccount_menu_left_icon_region, this.mEngine.getVertexBufferObjectManager());
        this.myaccount_menu_item_entity.attachChild(this.myaccount_menu_left_icon_sprite);
        this.myaccountmenu = new Text((this.myaccount_menu_left_icon_sprite.getX() + this.myaccount_menu_left_icon_sprite.getWidth()) + 50.0f, 15.0f, this.or_font, "My account", this.mEngine.getVertexBufferObjectManager());
        this.myaccountmenu.setColor(android.graphics.Color.rgb(178, 172, 171));
        this.myaccount_menu_item_entity.attachChild(this.myaccountmenu);
        this.myaccount_line = new Line(0.0f, 0.0f, this.menucontainersprite.getWidth(), 0.0f, this.mEngine.getVertexBufferObjectManager());
        this.myaccount_line.setColor(android.graphics.Color.rgb(178, 172, 171));
        this.myaccount_menu_item_entity.attachChild(this.myaccount_line);
        this.menucontainersprite.attachChild(this.myaccount_menu_item_entity);
        this.mycommission_menu_item_entity = new Entity();
        this.mycommission_menu_item_entity.setPosition(0.0f, this.myaccount_menu_item_entity.getY() - 50.0f);
        this.mycommission_menu_left_sprite = new Sprite(20.0f, 15.0f, this.mycommission_menu_left_icon_region, this.mEngine.getVertexBufferObjectManager());
        this.mycommission_menu_item_entity.attachChild(this.mycommission_menu_left_sprite);
        this.mycommissionmenu = new Text((this.mycommission_menu_left_sprite.getX() + this.mycommission_menu_left_sprite.getWidth()) + 60.0f, 15.0f, this.or_font, "My commission", this.mEngine.getVertexBufferObjectManager()){
          /*  @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionUp()){
                    Intent intent=new Intent(MainProfile.this,MyCommission.class);
                    intent.putExtra("token",token);
                    startActivity(intent);
                }
                return true;
            }*/
          class C05611 implements PathModifier.IPathModifierListener {
              C05611() {
              }

              public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
              }

              public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
              }

              public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
              }

              public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                  Intent intent=new Intent(MainProfile.this,MyCommission.class);
                  intent.putExtra("token",token);
                  startActivity(intent);
              }
          }

            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) {
                if (pSceneTouchEvent.isActionUp()){
                    MainProfile.this.menu_path = new PathModifier.Path(2).to(MainProfile.this.menucontainersprite.getX(), MainProfile.this.menucontainersprite.getY()).to(950.0f, MainProfile.this.menucontainersprite.getY());
                    MainProfile.this.menu_path_modifier = new PathModifier(0.1f, MainProfile.this.menu_path);
                    MainProfile.this.menucontainersprite.registerEntityModifier(MainProfile.this.menu_path_modifier);
                    MainProfile.this.menu_path_modifier.setPathModifierListener(new C05611());
                }
                return true;
            }
        };
        mScene.registerTouchArea(mycommissionmenu);
        this.mycommissionmenu.setColor(android.graphics.Color.rgb(178, 172, 171));
        this.mycommission_menu_item_entity.attachChild(this.mycommissionmenu);
        this.mycommission = new Line(0.0f, 0.0f, this.menucontainersprite.getWidth(), 0.0f, this.mEngine.getVertexBufferObjectManager());
        this.mycommission.setColor(android.graphics.Color.rgb(178, 172, 171));
        this.mycommission_menu_item_entity.attachChild(this.mycommission);
        this.menucontainersprite.attachChild(this.mycommission_menu_item_entity);
        this.mylevel_menu_item_entity = new Entity();
        this.mylevel_menu_item_entity.setPosition(0.0f, this.mycommission_menu_item_entity.getY() - 50.0f);
        this.myaccount_menu_left_icon_sprite = new Sprite(20.0f, 15.0f, this.mylevel_menu_left_icon_region, this.mEngine.getVertexBufferObjectManager());
        this.mylevel_menu_item_entity.attachChild(this.myaccount_menu_left_icon_sprite);
        this.mylevel_name = new Text((this.myaccount_menu_left_icon_sprite.getX() + this.myaccount_menu_left_icon_sprite.getWidth()) + 33.0f, 15.0f, this.or_font, "My level", this.mEngine.getVertexBufferObjectManager()){
            /*@Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionUp()){
                    Intent intent=new Intent(MainProfile.this,MyLevels.class);
                    intent.putExtra("token",token);
                    startActivity(intent);
                }
                return true;
            }*/

            class C05611 implements PathModifier.IPathModifierListener {
                C05611() {
                }

                public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
                }

                public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
                }

                public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
                }

                public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                    Intent intent=new Intent(MainProfile.this,MyLevels.class);
                    intent.putExtra("token",token);
                    startActivity(intent);
                }
            }

            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) {
                if (pSceneTouchEvent.isActionUp()){
                    MainProfile.this.menu_path = new PathModifier.Path(2).to(MainProfile.this.menucontainersprite.getX(), MainProfile.this.menucontainersprite.getY()).to(950.0f, MainProfile.this.menucontainersprite.getY());
                    MainProfile.this.menu_path_modifier = new PathModifier(0.1f, MainProfile.this.menu_path);
                    MainProfile.this.menucontainersprite.registerEntityModifier(MainProfile.this.menu_path_modifier);
                    MainProfile.this.menu_path_modifier.setPathModifierListener(new C05611());
                }
                return true;
            }

        };

        this.mScene.registerTouchArea(this.mylevel_name);
        this.mylevel_name.setColor(android.graphics.Color.rgb(178, 172, 171));
        this.mylevel_menu_item_entity.attachChild(this.mylevel_name);
        this.mylevel = new Line(0.0f, 0.0f, this.menucontainersprite.getWidth(), 0.0f, this.mEngine.getVertexBufferObjectManager());
        this.mylevel.setColor(android.graphics.Color.rgb(178, 172, 171));
        this.mylevel_menu_item_entity.attachChild(this.mylevel);
        this.menucontainersprite.attachChild(this.mylevel_menu_item_entity);
        this.mysetting_menu_item_entity = new Entity();
        this.mysetting_menu_item_entity.setPosition(0.0f, this.mylevel_menu_item_entity.getY() - 50.0f);
        this.mysetting_menu_left_icon_sprite = new Sprite(20.0f, 15.0f, this.mysetting_menu_left_icon_region, this.mEngine.getVertexBufferObjectManager());
        this.mysetting_menu_item_entity.attachChild(this.mysetting_menu_left_icon_sprite);
        this.mysetting_name = new Text((this.mysetting_menu_left_icon_sprite.getX() + this.mysetting_menu_left_icon_sprite.getWidth()) + 20.0f, 15.0f, this.or_font, "Setting", this.mEngine.getVertexBufferObjectManager()){
            /*@Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionUp()){
                    Intent intent=new Intent(MainProfile.this,MyLevels.class);
                    intent.putExtra("token",token);
                    startActivity(intent);
                }
                return true;
            }*/

            class C05611 implements PathModifier.IPathModifierListener {
                C05611() {
                }

                public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
                }

                public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
                }

                public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
                }

                public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                    Intent intent=new Intent(MainProfile.this,Settings.class);
                    intent.putExtra("token",token);
                    startActivity(intent);
                }
            }

            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) {
                if (pSceneTouchEvent.isActionUp()){
                    MainProfile.this.menu_path = new PathModifier.Path(2).to(MainProfile.this.menucontainersprite.getX(), MainProfile.this.menucontainersprite.getY()).to(950.0f, MainProfile.this.menucontainersprite.getY());
                    MainProfile.this.menu_path_modifier = new PathModifier(0.1f, MainProfile.this.menu_path);
                    MainProfile.this.menucontainersprite.registerEntityModifier(MainProfile.this.menu_path_modifier);
                    MainProfile.this.menu_path_modifier.setPathModifierListener(new C05611());
                }
                return true;
            }

        };
        mScene.registerTouchArea(mysetting_name);
        this.mysetting_name.setColor(android.graphics.Color.rgb(178, 172, 171));
        this.mysetting_menu_item_entity.attachChild(this.mysetting_name);
        this.mysetting_line = new Line(0.0f, 0.0f, this.menucontainersprite.getWidth(), 0.0f, this.mEngine.getVertexBufferObjectManager());
        this.mysetting_line.setColor(android.graphics.Color.rgb(178, 172, 171));
        this.mysetting_menu_item_entity.attachChild(this.mysetting_line);
        this.menucontainersprite.attachChild(this.mysetting_menu_item_entity);
        this.mywithdrawal_menu_item_enity = new Entity();
        this.mywithdrawal_menu_item_enity.setPosition(0.0f, this.mysetting_menu_item_entity.getY() - 50.0f);
        this.mywithdrawal_menu_left_icon_sprite = new Sprite(20.0f, 15.0f, this.mywithdrawal_menu_left_icon_region, this.mEngine.getVertexBufferObjectManager());
        this.mywithdrawal_menu_item_enity.attachChild(this.mywithdrawal_menu_left_icon_sprite);
        this.mywithdrawal_name = new Text((this.mywithdrawal_menu_left_icon_sprite.getX() + this.mywithdrawal_menu_left_icon_sprite.getWidth()) + 50.0f, 15.0f, this.or_font, "Withdrawal", this.mEngine.getVertexBufferObjectManager()){
            /*@Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionUp()){
                    Intent intents=new Intent(MainProfile.this,Withdrawal.class);
                    intents.putExtra("token",token);
                    intents.putExtra("amount",intent.getStringExtra("amount"));
                    startActivity(intents);
                }
                return true;
            }*/
            class C05611 implements PathModifier.IPathModifierListener {
                C05611() {
                }

                public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
                }

                public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
                }

                public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
                }

                public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                    Intent intents=new Intent(MainProfile.this,Withdrawal.class);
                    intents.putExtra("token",token);
                    intents.putExtra("amount",intent.getStringExtra("amount"));
                    startActivity(intents);
                }
            }

            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) {
                if (pSceneTouchEvent.isActionUp()){
                    MainProfile.this.menu_path = new PathModifier.Path(2).to(MainProfile.this.menucontainersprite.getX(), MainProfile.this.menucontainersprite.getY()).to(950.0f, MainProfile.this.menucontainersprite.getY());
                    MainProfile.this.menu_path_modifier = new PathModifier(0.1f, MainProfile.this.menu_path);
                    MainProfile.this.menucontainersprite.registerEntityModifier(MainProfile.this.menu_path_modifier);
                    MainProfile.this.menu_path_modifier.setPathModifierListener(new C05611());
                }
                return true;
            }
        };
        this.mScene.registerTouchArea(this.mywithdrawal_name);
        this.mywithdrawal_name.setColor(android.graphics.Color.rgb(178, 172, 171));
        this.mywithdrawal_menu_item_enity.attachChild(this.mywithdrawal_name);
        this.mywithdrawal_line = new Line(0.0f, 0.0f, this.menucontainersprite.getWidth(), 0.0f, this.mEngine.getVertexBufferObjectManager());
        this.mywithdrawal_line.setColor(android.graphics.Color.rgb(178, 172, 171));
        this.mywithdrawal_menu_item_enity.attachChild(this.mywithdrawal_line);
        this.menucontainersprite.attachChild(this.mywithdrawal_menu_item_enity);
        this.mylogout_menu_item_entity = new Entity();
        this.mylogout_menu_item_entity.setPosition(0.0f, this.mywithdrawal_menu_item_enity.getY() - 50.0f);
        this.mylogout_menu_left_sprite = new Sprite(20.0f, 15.0f, this.mylogout_menu_left_icon_region, this.mEngine.getVertexBufferObjectManager());
        this.mylogout_menu_item_entity.attachChild(this.mylogout_menu_left_sprite);
        this.mylogout_name = new Text((this.mylogout_menu_left_sprite.getX() + this.mylogout_menu_left_sprite.getWidth()) + 30.0f, 15.0f, this.or_font, "Logout", this.mEngine.getVertexBufferObjectManager()){
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionUp()){
                    Intent intent=new Intent(MainProfile.this,CustomLogin.class);
                    startActivity(intent);
                    MainProfile.this.finish();
                }
                return true;
            }
        };

        this.mScene.registerTouchArea(this.mylogout_name);
        this.mylogout_name.setColor(android.graphics.Color.rgb(178, 172, 171));
        this.mylogout_menu_item_entity.attachChild(this.mylogout_name);
        this.mylogout_line = new Line(0.0f, 0.0f, this.menucontainersprite.getWidth(), 0.0f, this.mEngine.getVertexBufferObjectManager());
        this.mylogout_line.setColor(android.graphics.Color.rgb(178, 172, 171));
        this.mylogout_menu_item_entity.attachChild(this.mylogout_line);
        this.menucontainersprite.attachChild(this.mylogout_menu_item_entity);


        this.dash_board = new Entity();
        this.main_center_entity.attachChild(this.dash_board);
        this.bottomSprite = new Sprite(this.bottomBgRegion.getWidth() / 2.0f, this.bottomBgRegion.getHeight() / 2.0f, this.bottomBgRegion, this.mEngine.getVertexBufferObjectManager());
        this.mScene.attachChild(this.bottomSprite);
        this.play_animate = new AnimatedSprite(this.top_sprite.getWidth() - 295.0f, 25.0f, this.play_tiled, this.mEngine.getVertexBufferObjectManager()) {
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) {
                if (pSceneTouchEvent.isActionUp()) {
                    start_play();

                }
                return true;
            }
        };
        this.play_animate.animate(300);
        this.mScene.registerTouchArea(this.play_animate);
        this.bottomSprite.attachChild(this.play_animate);

        Sprite get_chips_sprite = new Sprite(70.0f, 25.0f, this.get_chips, this.mEngine.getVertexBufferObjectManager()) {
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) {
                if (pSceneTouchEvent.isActionUp()) {
                    Intent intent = new Intent(MainProfile.this,ChipsMarket.class);
                    intent.putExtra("token",token);
                    startActivity(intent);
                }
                return true;
            }
        };
        Sprite share_sprite = new Sprite(350.0f, 20.0f, this.share_region, this.mEngine.getVertexBufferObjectManager()){
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                if (pSceneTouchEvent.isActionUp()) {
                MainProfile.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MainProfile.this.send_dialog = new Dialog(MainProfile.this);
                        MainProfile.this.send_dialog.requestWindowFeature(1);
                        MainProfile.this.send_dialog.setContentView(R.layout.share_layout_dialog);
                        MainProfile.this.watsapplayout = (LinearLayout) send_dialog.findViewById(R.id.watsapplayout);
                        MainProfile.this.emailiconlayout = (LinearLayout)send_dialog.findViewById(R.id.emailconlayout);
                        MainProfile.this.emaillayout = (LinearLayout)send_dialog.findViewById(R.id.emaillayout);
                        MainProfile.this.share_email_address = (EditText)send_dialog.findViewById(R.id.email_to_shares);
                        MainProfile.this.share_email_address.requestFocus();
                        MainProfile.this.dialog_error = (TextView)send_dialog.findViewById(R.id.dialog_email_error);
                        MainProfile.this.send_email = (Button)send_dialog.findViewById(R.id.send_to_emails);
                        MainProfile.this.sharewatsapp = (ImageView)send_dialog.findViewById(R.id.watsappicon);
                        MainProfile.this.shareemail = (ImageView)send_dialog.findViewById(R.id.emailicon);
                       // MainProfile.this.shareemail.setOnClickListener(new C02711());
                       // MainProfile.this.send_email.setOnClickListener(new C02722());
                       // MainProfile.this.sharewatsapp.setOnClickListener(new C02733());
                        sharewatsapp.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String message = "";
                                String user_name = intent.getStringExtra("user_name");
                                if (Build.VERSION.SDK_INT >= 24) {
                                    message = "Hi,friends. Play roulette6 casino game and get maxocoin. Download Roulette6.apk in here http://roulette6.club/share/?referral_code=" + intent.getStringExtra("referral_code")+ " and register (from " + intent.getStringExtra("user_name")+ ") use my referral code when you register " + intent.getStringExtra("referral_code");
                                } else {
                                    message = "Hi,friends. Play roulette6 casino game and get maxocoin . Download Roulette6.apk in here http://roulette6.club/share/?referral_code=" + intent.getStringExtra("referral_code")+ " and register (from " + intent.getStringExtra("user_name")+ " ) use my referral code when you register " + intent.getStringExtra("referral_code");
                                }
                                Intent sendIntent = new Intent("android.intent.action.MAIN");
                                sendIntent.putExtra("android.intent.extra.TEXT", message);
                                sendIntent.setAction("android.intent.action.SEND");
                                sendIntent.setPackage("com.whatsapp");
                                sendIntent.setType("text/plain");
                                MainProfile.this.startActivity(sendIntent);
                            }
                        });

                        shareemail.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                MainProfile.this.watsapplayout.setVisibility(View.GONE);
                                MainProfile.this.emailiconlayout.setVisibility(View.GONE);
                                MainProfile.this.emaillayout.setVisibility(View.VISIBLE);
                            }
                        });


                        send_email.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                             //laravel mailer
                            }
                        });

                        MainProfile.this.send_dialog.show();
                    }
                });
                }
                return true;
            }
        };

        this.mScene.registerTouchArea(get_chips_sprite);
        this.bottomSprite.attachChild(get_chips_sprite);
        this.mScene.registerTouchArea(share_sprite);
        this.bottomSprite.attachChild(share_sprite);


        sprite = new Sprite((share_sprite.getX() + share_sprite.getWidth()) + 10.0f, 20.0f, this.info_region, this.mEngine.getVertexBufferObjectManager());
        this.bottomSprite.attachChild(sprite);
        Sprite help=new Sprite(sprite.getX() + sprite.getWidth() + 10.0f, 20.0f, this.help_region, this.mEngine.getVertexBufferObjectManager());

        this.bottomSprite.attachChild(help);

        this.left_entity = new Entity();
        this.networth = new Text(150.0f, 380.0f, this.normal_font, "Net Worth", this.mEngine.getVertexBufferObjectManager());
        this.networth.setColor(android.graphics.Color.rgb(234, 197, 137));
        this.left_entity.attachChild(this.networth);

        this.chips_value = new Text(160.0f, 350.0f, this.networth_value_font, "in chips =325", this.mEngine.getVertexBufferObjectManager());
        this.chips_value.setColor(android.graphics.Color.rgb(237, 235, 221));
        this.left_entity.attachChild(this.chips_value);

        this.indollar = new Text(160.0f, 330.0f, this.networth_value_font, "in dollar = 5$", this.mEngine.getVertexBufferObjectManager());
        this.left_entity.attachChild(this.indollar);
        this.inbitcoin = new Text(195.0f, 310.0f, this.networth_value_font, "in maxocoin = 0.0003456", this.mEngine.getVertexBufferObjectManager());
        this.left_entity.attachChild(this.inbitcoin);
        this.your_commision = new Text(180.0f, 270.0f, this.normal_font, "Your commission", this.mEngine.getVertexBufferObjectManager());
        this.your_commision.setColor(android.graphics.Color.rgb(234, 197, 137));
        this.commision_value = new Text(180.0f, 245.0f, this.networth_value_font, "commission = 0", this.mEngine.getVertexBufferObjectManager());
        this.left_entity.attachChild(this.your_commision);
        this.left_entity.attachChild(this.commision_value);
        this.your_levels = new Text(150.0f, 210.0f, this.normal_font, "Your Level", this.mEngine.getVertexBufferObjectManager());
        this.your_levels.setColor(android.graphics.Color.rgb(234, 197, 137));
        this.levels_value = new Text(160.0f, 180.0f, this.networth_value_font, "Levels = 0", this.mEngine.getVertexBufferObjectManager());
        this.left_entity.attachChild(this.your_levels);
        this.left_entity.attachChild(this.levels_value);
        this.left_entity.setWidth(400.0f);
        this.left_entity.setHeight(240.0f);
        this.left_entity.setColor(Color.RED);
        this.left_entity.setPosition(120.0f, 120.0f);

        this.dash_board.attachChild(this.left_entity);
        this.center_transparencys = new Sprite(450.0f, 280.0f, this.center_transparency_region, this.mEngine.getVertexBufferObjectManager());
        this.center_transparencys.attachChild(this.welcome);
        this.dash_board.attachChild(this.center_transparencys);
        this.center_transparencys.setColor(Color.RED);

        this.register = new Sprite(250.0f, this.welcome.getHeight() - 120.0f, this.register_region, this.mEngine.getVertexBufferObjectManager()) {

            class C02761 implements Runnable {

                class C02751 implements View.OnClickListener {
                    C02751() {
                    }

                    public void onClick(View v) {
                        String address = MainProfile.this.bitcoin_address.getText().toString();
                        if (address.equals("")) {
                            MainProfile.this.error.setVisibility(View.VISIBLE);
                            MainProfile.this.error.setText("Please add your bitcoin address");
                        } else if (address.length() < 28) {
                            MainProfile.this.error.setVisibility(View.VISIBLE);
                            MainProfile.this.error.setText("Address size must be greater than 28 characters");
                        } else if (Character.isDigit(address.charAt(0))) {
                            MainProfile.this.add_bar.setVisibility(View.VISIBLE);
                            Retrofit retrofit = getAPIUser();
                            UsersClient client=retrofit.create(UsersClient.class);
                            AddressModel model=new AddressModel(address);
                            Call<ResponseMessage> call=client.updateAddress(token,model);
                            call.enqueue(new Callback<ResponseMessage>() {
                                @Override
                                public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                                   if (response.body().getStatus().equals("ok")){
                                       MainProfile.this.address_add();
                                       MainProfile.this.dialog.dismiss();
                                       MainProfile.this.add_bar.setVisibility(View.GONE);

                                   }else {
                                       Toast.makeText(getApplicationContext(),"Your address is not added.try it later",Toast.LENGTH_LONG).show();
                                   }
                                }

                                @Override
                                public void onFailure(Call<ResponseMessage> call, Throwable t) {

                                }
                            });
                            //new Add_bitcoin_address_task().execute(new String[]{email, address});
                        } else {
                            MainProfile.this.error.setVisibility(View.VISIBLE);
                            MainProfile.this.error.setText("incorrect maxocoin address. maxocoin address starts with number");
                        }
                    }
                }

                C02761() {
                }

                public void run() {
                    MainProfile.this.dialog = new Dialog(MainProfile.this);
                    MainProfile.this.dialog.requestWindowFeature(1);
                    MainProfile.this.dialog.setContentView(R.layout.registor_bitcoin_address);
                    MainProfile.this.bitcoin_address = (EditText) MainProfile.this.dialog.findViewById(R.id.user_bitcoin_address);
                    MainProfile.this.error = (TextView) MainProfile.this.dialog.findViewById(R.id.error_handler);
                    MainProfile.this.add_address = (Button) MainProfile.this.dialog.findViewById(R.id.register_address);
                    MainProfile.this.add_bar = (ProgressBar) MainProfile.this.dialog.findViewById(R.id.add_bar);
                    MainProfile.this.add_address.setOnClickListener(new C02751());
                    MainProfile.this.dialog.show();
                }
            }

            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) {
                if (pSceneTouchEvent.isActionUp()) {
                    MainProfile.this.runOnUiThread(new C02761());
                }
                return true;
            }
        };
        this.mScene.registerTouchArea(this.register);
        if (intent.getStringExtra("bitcoin_address").equals("0")) {
            this.center_transparencys.attachChild(this.register);
        }


        mScene.attachChild(main_center_entity);


        mScene.attachChild(menucontainersprite);




       // this.mScene.attachChild(this.menucontainersprite);
        this.mScene.setBackground(new SpriteBackground(new Sprite(this.region.getWidth() / 2.0f, this.region.getHeight() / 2.0f, this.region, getVertexBufferObjectManager())));
        return this.mScene;
    }
    public void start_play() {
        Retrofit retrofit=getAPIUser();
        UsersClient client=retrofit.create(UsersClient.class);
        Call<ResponseMessage> call=client.get_timer();
        call.enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
               int timers= Integer.parseInt(response.body().getStatus());
                if (timers<=19){
                    Intent intents = new Intent(MainProfile.this, WaiterActivity.class);
                    intents.putExtra("user_name",intent.getStringExtra("user_name"));
                    intents.putExtra("email",intent.getStringExtra("email"));
                    intents.putExtra("amount",intent.getStringExtra("amount"));
                    intents.putExtra("token",token);
                    intents.putExtra("referral_code",intent.getStringExtra("referral_code"));
                    intents.putExtra("timer", timers);
                    startActivity(intents);
                }else {
                    Intent intents=new Intent(MainProfile.this,GameScene.class);
                    intents.putExtra("user_name",intent.getStringExtra("user_name"));
                    intents.putExtra("email",intent.getStringExtra("email"));
                    intents.putExtra("amount",intent.getStringExtra("amount"));
                    intents.putExtra("token",token);
                    intents.putExtra("referral_code",intent.getStringExtra("referral_code"));
                    startActivity(intents);
                }
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

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
    public void address_add() {
        this.welcome.setText("Hi, " + intent.getStringExtra("user_name")+ " How are you today\nYou Have " +intent.getStringExtra("amount") + " chips in your amount\n start playing and increase your wallet amount");
        this.welcome.setPosition(200.0f, (float) (this.center_transparency.getHeight() - 50));
        this.center_transparencys.detachChild(this.register);
    }
    class GetTimer extends AsyncTask<String,String,String> {
        HttpURLConnection urlConnection;
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder result = new StringBuilder();

            try {
                URL url = new URL("http://10.0.2.2:8000/game_timers.php");
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }


            return result.toString();
        }


        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            super.onPostExecute(s);
        }
    }
}
