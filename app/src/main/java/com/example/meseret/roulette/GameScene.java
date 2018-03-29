package com.example.meseret.roulette;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.media.TransportMediator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.util.FPSLogger;
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
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.color.Color;
import org.andengine.util.modifier.IModifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import clients.UsersClient;
import clientsModel.Amounts;
import clientsModel.Commission;
import clientsModel.Player;
import clientsModel.UpdateAccount;
import clientsModel.UsersData;
import components.BitButton;
import components.ChipsButton;
import components.ChipsButton2;
import model.ResponseMessage;
import projectstatic.ProjectStatic;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GameScene extends SimpleBaseGameActivity {
    private final int CAMERA_HEIGHT = 480;
    private final int CAMERA_WIDTH = 800;
    private boolean add8 = false;
    private String amount;
    private BitmapTextureAtlas avator1;
    private BitmapTextureAtlas avator10;
    private BitmapTextureAtlas avator2;
    private BitmapTextureAtlas avator3;
    private BitmapTextureAtlas avator4;
    private BitmapTextureAtlas avator5;
    private BitmapTextureAtlas avator6;
    private BitmapTextureAtlas avator7;
    private BitmapTextureAtlas avator8;
    private BitmapTextureAtlas avator9;
    private ITextureRegion avator_region1;
    private ITextureRegion avator_region10;
    private ITextureRegion avator_region2;
    private ITextureRegion avator_region3;
    private ITextureRegion avator_region4;
    private ITextureRegion avator_region5;
    private ITextureRegion avator_region6;
    private ITextureRegion avator_region7;
    private ITextureRegion avator_region8;
    private ITextureRegion avator_region9;
    private BitmapTextureAtlas ballAtlas;
    private ITextureRegion ballRegion;
    private Sprite ballSprite;
    private PathModifier.Path ball_path;
    private PathModifier ball_path_modifer;
    //private Music bg_music;
    private BitmapTextureAtlas bitAtlas;
    private BitmapTextureAtlas bitAtlas10;
    private BitmapTextureAtlas bitAtlas100;
    private BitmapTextureAtlas bitAtlas50;
    private BitmapTextureAtlas bitAtlas500;
    private BitButton bitButton;
    private Entity bitEntity;
    private ArrayList<Sprite> bitHolder;
    private ITextureRegion bitRegion;
    private ITextureRegion bitRegion10;
    private ITextureRegion bitRegion100;
    private ITextureRegion bitRegion50;
    private ITextureRegion bitRegion500;
    private ChipsButton bitSprite;
    private String bit_value = "";
    private ArrayList<ITextureRegion> bitsRegion;
    private BitmapTextureAtlas bottomBg;
    private ITextureRegion bottomBgRegion;
    private Sprite bottomSprite;
    private boolean bt18and1 = false;
    private Sound btn_click;
    private boolean btzeroandthree = false;
    private boolean btzeroandtwo = false;
    private boolean btzeronandone = false;
    private boolean btzerooneand18 = false;
    private boolean btzerothreetwo = false;
    private boolean btzerotwoandone = false;
    private Entity centerContainer;
    private float centerContainerXpos;
    private float centerContainerypos;
    private Entity chipsEntity;
    private BitmapTextureAtlas circleAtlas;
    private BitmapTextureAtlas circleRegion;
    private ITextureRegion circleRegions;
    private ChipsButton clear;
    private Text clear_string;
    private boolean connection_is_available = true;
    String content;
    private Text current_bit;
    private SharedPreferences dataholder;
    private Dialog dialog;
    private boolean dialog_is_showen = false;
    private boolean dialog_not_showen = false;
    private Text dollar;
    private String email;
    private Entity entity;
    private boolean eveisadded = false;
    private ChipsButton fifty;
    private BitmapTextureAtlas fiftyDollar;
    private ITextureRegion fiftyDollarRegion;
    private String finalWinner = "";
    private ChipsButton five;
    private BitmapTextureAtlas fiveDollar;
    private ITextureRegion fiveDollarRegion;
    private ChipsButton fiveHundred;
    private BitmapTextureAtlas fiveHundredDollar;
    private ITextureRegion fiveHundredDollarRegion;
    private String gameNumbers = "01234567";
    private int gameStarter = 0;
    private String game_player;
    private Gamers gamers;
    private Handler handler = new Handler();
    private ChipsButton hundred;
    private BitmapTextureAtlas hundredDollar;
    private ITextureRegion hundredDollarRegion;
    private int index = 0;
    private boolean is10Added = false;
    private boolean is11141013Added = false;
    private boolean is11Added = false;
    private boolean is12151114Added = false;
    private boolean is121814174Added = false;
    private boolean is12811Added = false;
    private boolean is13Added = false;
    private boolean is14171316Added = false;
    private boolean is14Added = false;
    private boolean is151814174Added = false;
    private boolean is15Added = false;
    private boolean is16Added = false;
    private boolean is17201619Added = false;
    private boolean is17Added = false;
    private boolean is18211720Added = false;
    private boolean is18Added = false;
    private boolean is19Added = false;
    private boolean is19bt36Added = false;
    private boolean is1bt18added = false;
    private boolean is20231922Added = false;
    private boolean is20Added = false;
    private boolean is21242023Added = false;
    private boolean is21Added = false;
    private boolean is22Added = false;
    private boolean is23262225Added = false;
    private boolean is23Added = false;
    private boolean is24271326Added = false;
    private boolean is24Added = false;
    private boolean is2514Added = false;
    private boolean is25Added = false;
    private boolean is26292528Added = false;
    private boolean is26Added = false;
    private boolean is27302629Added = false;
    private boolean is27Added = false;
    private boolean is28Added = false;
    private boolean is29322831Added = false;
    private boolean is29Added = false;
    private boolean is2to1AddedMiddle = false;
    private boolean is2to1AddedTop = false;
    private boolean is2to1Addedbelow = false;
    private boolean is30332932Added = false;
    private boolean is30Added = false;
    private boolean is31Added = false;
    private boolean is32353133Added = false;
    private boolean is32Added = false;
    private boolean is33363235Added = false;
    private boolean is33Added = false;
    private boolean is34Added = false;
    private boolean is35Added = false;
    private boolean is3625Added = false;
    private boolean is36Added = false;
    private boolean is5847Added = false;
    private boolean is6958Added = false;
    private boolean is6to5Added = false;
    private boolean is811710Added = false;
    private boolean isBlackAdded = false;
    private boolean isEightAdded = false;
    private boolean isFiveAdded = false;
    private boolean isMoveInside = true;
    private boolean isNineAdded = false;
    private boolean isOddAdded = false;
    private boolean isRedAdded = false;
    private boolean isSevenAdded = false;
    private boolean isSixAdded = false;
    private boolean is_bit_value_correct = true;
    private boolean is_less_than_17 = false;
    private boolean is_loading = false;
    private boolean is_playing_chips_set = false;
    private SharedPreferences is_updated;
    private boolean iseighthsixlineAdded = false;
    private boolean iseleventhsixlineAdded = false;
    private boolean isfifthsixlineAdded = false;
    private boolean isfirstsixlineAdded = false;
    private boolean isfourAdded = false;
    private boolean isfourthsixlineAdded = false;
    private boolean isninthsixlineAdded = false;
    private boolean issecondsixlineAdded = false;
    private boolean isseventhsixlineAdded = false;
    private boolean issixthsixlineAdded = false;
    private boolean issplit1013Added = false;
    private boolean issplit1114Added = false;
    private boolean issplit1215Added = false;
    private boolean issplit1316Added = false;
    private boolean issplit1417Added = false;
    private boolean issplit14Added = false;
    private boolean issplit1518Added = false;
    private boolean issplit1619Added = false;
    private boolean issplit1720Added = false;
    private boolean issplit1821Added = false;
    private boolean issplit1922Added = false;
    private boolean issplit2023Added = false;
    private boolean issplit2124Added = false;
    private boolean issplit2225Added = false;
    private boolean issplit2326Added = false;
    private boolean issplit2427Added = false;
    private boolean issplit2528Added = false;
    private boolean issplit25Added = false;
    private boolean issplit2629Added = false;
    private boolean issplit2730Added = false;
    private boolean issplit2831Added = false;
    private boolean issplit2932Added = false;
    private boolean issplit3033Added = false;
    private boolean issplit3134Added = false;
    private boolean issplit3235Added = false;
    private boolean issplit32Added = false;
    private boolean issplit3336Added = false;
    private boolean issplit47Added = false;
    private boolean issplit58Added = false;
    private boolean issplit69Added = false;
    private boolean issplit710Added = false;
    private boolean issplit811Added = false;
    private boolean issplit912Added = false;
    private boolean istableToucehEnable = true;
    private boolean istenthsixlineAdded = false;
    private boolean isthirdsixlineAdded = false;
    private boolean istwelveAdded = false;
    private Sound last_call;
    private TimerHandler less_than_17_updater;
    private int less_than_17_value = 0;
    private ITextureRegion loseRegion;
    private Sprite loseSprite;
    private BitmapTextureAtlas loseatlas;
    private Sound lost;
    private Result luckyNumber;
    private Camera mCamera;
    private Font mFont;
    private Scene mScene;
    private PathModifier modifier;
    private AnimatedSprite music_control;
    private BitmapTextureAtlas music_control_atlas;
    private ITiledTextureRegion music_control_region;
    private int music_current_index = 0;
    private Intent my_intent;
    private int my_timer_value = 0;
    private String name;
    private String no_more_bet = "No More Bet";
    private PathModifier.Path no_more_bit_path;
    private PathModifier no_more_bit_path_modifier;
    private Sound no_more_bit_sound;
    private Text no_more_text;
    private Entity nomorebitEntity;
    private boolean not_less_than_17 = true;
    private BitmapTextureAtlas numberAtlas;
    private int number_yp_position = 20;
    private boolean oneValueIsAdded = false;
    private Entity online_gamers_entity;
    private Font online_gamers_font;
    private BitmapTextureAtlas online_gamers_table_no;
    private Font online_gamers_table_no_font;
    private Sprite online_gamers_table_no_sprites;
    private Text online_gamers_table_no_text;
    private ITextureRegion online_gamers_table_region;
    private SharedPreferences online_table_shared;
    private float pAnchorCenterY = 20.0f;
    private PathModifier.Path path;
    private long pausedTime = 0;
    private PathModifier.Path place_bit_Path;
    private PathModifier place_bit_modifier;
    private Text place_bit_text;
    private String place_your_bet = "Place your Bet";
    private Sound place_your_bit;
    private Entity placebitentity;
    private boolean play_on = false;
    private int player;
    private int player2;
    private Text playerName;
    private String private_key;
    private BitmapTextureAtlas profileAtlas;
    private ITextureRegion profileRegion;
    private String public_key;
    private ITextureRegion region;
    private TiledSprite result;
    private BitmapTextureAtlas resultAtlas;
    private TiledTextureRegion resultRegion;
    private long resumedTIme = 0;
    private ITextureRegion roulettWheelBgRegion;
    private BitmapTextureAtlas rouletteWheelBg;
    private BitmapTextureAtlas rouletteWheelMain;
    private ITextureRegion rouletteWheelMainRegion;
    private TimerHandler scene_updater;
    private int selectedBitValue;
    private Sprite selectedNumberSprite;
    private ITextureRegion selectedNumberTexture;
    private Sprite setting;
    private BitmapTextureAtlas settingAtlas;
    private ITextureRegion settingRegion;
    private SharedPreferences shared_data;
    private TextToSpeech speech;
    private Sprite sprite5;
    private ArrayList<Sprite> spriteList;
    private Sprite sprite_user1;
    private Sprite sprite_user10;
    private Sprite sprite_user2;
    private Sprite sprite_user3;
    private Sprite sprite_user4;
    private Sprite sprite_user5;
    private Sprite sprite_user6;
    private Sprite sprite_user7;
    private Sprite sprite_user8;
    private Sprite sprite_user9;
    private float start_rotation = 10.0f;
    private long startingTime = 0;
    private boolean t_no_add8 = false;
    private boolean t_no_bt18and1 = false;
    private boolean t_no_btzeroandthree = false;
    private boolean t_no_btzeroandtwo = false;
    private boolean t_no_btzeronandone = false;
    private boolean t_no_btzerooneand18 = false;
    private boolean t_no_btzerothreetwo = false;
    private boolean t_no_btzerotwoandone = false;
    private boolean t_no_eveisadded = false;
    private boolean t_no_is10Added = false;
    private boolean t_no_is11141013Added = false;
    private boolean t_no_is11Added = false;
    private boolean t_no_is12151114Added = false;
    private boolean t_no_is121814174Added = false;
    private boolean t_no_is12811Added = false;
    private boolean t_no_is13Added = false;
    private boolean t_no_is14171316Added = false;
    private boolean t_no_is14Added = false;
    private boolean t_no_is151814174Added = false;
    private boolean t_no_is15Added = false;
    private boolean t_no_is16Added = false;
    private boolean t_no_is17201619Added = false;
    private boolean t_no_is17Added = false;
    private boolean t_no_is18211720Added = false;
    private boolean t_no_is18Added = false;
    private boolean t_no_is19Added = false;
    private boolean t_no_is19bt36Added = false;
    private boolean t_no_is20231922Added = false;
    private boolean t_no_is20Added = false;
    private boolean t_no_is21242023Added = false;
    private boolean t_no_is21Added = false;
    private boolean t_no_is22Added = false;
    private boolean t_no_is23262225Added = false;
    private boolean t_no_is23Added = false;
    private boolean t_no_is24271326Added = false;
    private boolean t_no_is24Added = false;
    private boolean t_no_is2514Added = false;
    private boolean t_no_is25Added = false;
    private boolean t_no_is26292528Added = false;
    private boolean t_no_is26Added = false;
    private boolean t_no_is27302629Added = false;
    private boolean t_no_is27Added = false;
    private boolean t_no_is28Added = false;
    private boolean t_no_is29322831Added = false;
    private boolean t_no_is29Added = false;
    private boolean t_no_is2to1AddedMiddle = false;
    private boolean t_no_is2to1AddedTop = false;
    private boolean t_no_is2to1Addedbelow = false;
    private boolean t_no_is30332932Added = false;
    private boolean t_no_is30Added = false;
    private boolean t_no_is31Added = false;
    private boolean t_no_is32353133Added = false;
    private boolean t_no_is32Added = false;
    private boolean t_no_is33363235Added = false;
    private boolean t_no_is33Added = false;
    private boolean t_no_is34Added = false;
    private boolean t_no_is35Added = false;
    private boolean t_no_is3625Added = false;
    private boolean t_no_is36Added = false;
    private boolean t_no_is5847Added = false;
    private boolean t_no_is6958Added = false;
    private boolean t_no_is6to5Added = false;
    private boolean t_no_is811710Added = false;
    private boolean t_no_isBlackAdded = false;
    private boolean t_no_isEightAdded = false;
    private boolean t_no_isEvenAdded = false;
    private boolean t_no_isFiveAdded = false;
    private boolean t_no_isNineAdded = false;
    private boolean t_no_isOddAdded = false;
    private boolean t_no_isRedAdded = false;
    private boolean t_no_isSevenAdded = false;
    private boolean t_no_isSixAdded = false;
    private boolean t_no_iseighthsixlineAdded = false;
    private boolean t_no_iseleventhsixlineAdded = false;
    private boolean t_no_isfifthsixlineAdded = false;
    private boolean t_no_isfirstsixlineAdded = false;
    private boolean t_no_isfourAdded = false;
    private boolean t_no_isfourthsixlineAdded = false;
    private boolean t_no_isninthsixlineAdded = false;
    private boolean t_no_issecondsixlineAdded = false;
    private boolean t_no_isseventhsixlineAdded = false;
    private boolean t_no_issixthsixlineAdded = false;
    private boolean t_no_issplit1013Added = false;
    private boolean t_no_issplit1114Added = false;
    private boolean t_no_issplit1215Added = false;
    private boolean t_no_issplit1316Added = false;
    private boolean t_no_issplit1417Added = false;
    private boolean t_no_issplit14Added = false;
    private boolean t_no_issplit1518Added = false;
    private boolean t_no_issplit1619Added = false;
    private boolean t_no_issplit1720Added = false;
    private boolean t_no_issplit1821Added = false;
    private boolean t_no_issplit1922Added = false;
    private boolean t_no_issplit2023Added = false;
    private boolean t_no_issplit2124Added = false;
    private boolean t_no_issplit2225Added = false;
    private boolean t_no_issplit2326Added = false;
    private boolean t_no_issplit2427Added = false;
    private boolean t_no_issplit2528Added = false;
    private boolean t_no_issplit25Added = false;
    private boolean t_no_issplit2629Added = false;
    private boolean t_no_issplit2730Added = false;
    private boolean t_no_issplit2831Added = false;
    private boolean t_no_issplit2932Added = false;
    private boolean t_no_issplit3033Added = false;
    private boolean t_no_issplit3134Added = false;
    private boolean t_no_issplit3235Added = false;
    private boolean t_no_issplit32Added = false;
    private boolean t_no_issplit3336Added = false;
    private boolean t_no_issplit36Added = false;
    private boolean t_no_issplit47Added = false;
    private boolean t_no_issplit58Added = false;
    private boolean t_no_issplit69Added = false;
    private boolean t_no_issplit710Added = false;
    private boolean t_no_issplit811Added = false;
    private boolean t_no_issplit912Added = false;
    private boolean t_no_istenthsixlineAdded = false;
    private boolean t_no_isthirdsixlineAdded = false;
    private boolean t_no_istwelveAdded = false;
    private boolean t_no_oneValueIsAdded = false;
    private boolean t_no_threeValueAdded = false;
    private boolean t_no_twoIsAdded = false;
    private boolean t_no_zeroValueAdded = false;
    private int tableNumber = 0;
    private Entity tableScoreContainer;
    private Sprite tableSprite;
    private BitmapTextureAtlas tableTextureAtlas;
    private ITextureRegion tableTextureRegion;
    private boolean table_is_added = false;
    private ChipsButton ten;
    private BitmapTextureAtlas tenDollar;
    private ITextureRegion tenDollarRegion;
    private float textXpos;
    private float textYpos;
    private Text text_user1;
    private Text text_user10;
    private Text text_user2;
    private Text text_user3;
    private Text text_user4;
    private Text text_user5;
    private Text text_user6;
    private Text text_user7;
    private Text text_user8;
    private Text text_user9;
    private Handler threadHandler = new Handler();
    private boolean threeValueAdded = false;
    private Text time_value;
    private long timeinmillis = 0;
    private int timer = 1;
    private Sprite timerButton;
    private ITextureRegion timerRegion;
    private TimerTask timerTask;
    private BitmapTextureAtlas timerTextureAtals;
    private AnimatedSprite timer_animate;
    private int timer_exact_value = -60;
    private int timer_index = 0;
    private ITiledTextureRegion timer_tiled;
    private BitmapTextureAtlas timer_tiled_atlas;
    private BitmapTextureAtlas topAtlas;
    private ITextureRegion topRegion;
    private Sprite topSprite;
    private BitmapTextureAtlas totalBit;
    private ITextureRegion totalBitRegion;
    private BitmapTextureAtlas totalScore;
    private ITextureRegion totalScoreRegion;
    private SharedPreferences total_amount_shared;
    private Text total_bit_string;
    private ChipsButton total_credit;
    private Text total_credit_string;
    private int total_lose = 0;
    private int total_number_of_bit = 0;
    private int total_win = 0;
    private boolean twoIsAdded = false;
    private long updateTime = 0;
    private int update_index = 0;
    private int lose_index=0;
    private Entity user1;
    private Entity user10;
    private Entity user2;
    private Entity user3;
    private Entity user4;
    private Entity user5;
    private Entity user6;
    private Entity user7;
    private Entity user8;
    private Entity user9;
    private int wait_timer_exact_value = 0;
    private Sprite wheelSprite;
    private Sound wheel_spin_sound;
    private Sound win;
    private ITextureRegion winRegion,exit_region;
    private Sprite winSprite;
    private boolean win_updated = false;
    private BitmapTextureAtlas winatlas,exit_atla;
    private BitmapTextureAtlas winner_atlas;
    private Entity winner_entity;
    private ITextureRegion winner_region;
    private Text winners_gain_value,please_wait;
    private String winners_id = "";
    private Text winners_name;
    private Sprite winners_sprite;
    private Font winningFont;
    private Text winningNumber;
    private int winningNumberValue;
    private float yp = 0.0f;
    private boolean zeroValueAdded = false;
    private Intent users_intent;
    private String token;
    private boolean game_started=false;
    private Sprite exit_sprite;


    private TimerTask network_timertask;
    private Timer network_timer;
    private Music game_music;


    @Override
    public EngineOptions onCreateEngineOptions() {
        this.mCamera = new Camera(0.0f, 0.0f, 800.0f, 480.0f);
        EngineOptions options = new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR, new FillResolutionPolicy(), this.mCamera);
        options.getAudioOptions().setNeedsMusic(true);
        options.getAudioOptions().setNeedsSound(true);
        return options;
    }

    protected void onCreateResources() throws IOException {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        BuildableBitmapTextureAtlas atlas = new BuildableBitmapTextureAtlas(this.mEngine.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        this.region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlas, (Context) this, "roulette_bg.jpg");
        try {
            atlas.build(new BlackPawnTextureAtlasBuilder(0, 0, 0));
            atlas.load();
        } catch (Exception e) {
        }
        loadTopResoureces();
        loadCenterResources();
        loadFonts();
        loadBottomResources();
        loadBitValue();
        loadWheelResources();
        load_music_soud();

    }

    public void loadResult() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        this.resultAtlas = new BitmapTextureAtlas(getTextureManager(), 256, 512, TextureOptions.DEFAULT);
        this.resultRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.resultAtlas, (Context) this, "items.png", 0, 0, 2, 2);
        this.resultAtlas.load();
    }

    public void loadCenterResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        this.tableTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 629, 340);
        this.tableTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.tableTextureAtlas, (Context) this, "tablelayout.png", 0, 0);
        this.tableTextureAtlas.load();
        this.numberAtlas = new BitmapTextureAtlas(getTextureManager(), 73, 360);
        this.selectedNumberTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.numberAtlas, (Context) this, "numberscreen.png", 0, 0);
        this.numberAtlas.load();
        this.winatlas = new BitmapTextureAtlas(getTextureManager(), 340, 339);
        this.winRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.winatlas, (Context) this, "win.PNG", 0, 0);
        this.winatlas.load();
        this.loseatlas = new BitmapTextureAtlas(getTextureManager(), 340, 339);
        this.loseRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.loseatlas, (Context) this, "lose.png", 0, 0);
        this.loseatlas.load();
        this.profileAtlas = new BitmapTextureAtlas(getTextureManager(), 42, 42);
        this.profileRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.profileAtlas, (Context) this, "profile.png", 0, 0);
        this.profileAtlas.load();
    }

    public void loadTopResoureces() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        this.topAtlas = new BitmapTextureAtlas(getTextureManager(), 800, 50);
        this.topRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.topAtlas, (Context) this, "toptoast.png", 0, 0);
        this.topAtlas.load();
        this.settingAtlas = new BitmapTextureAtlas(getTextureManager(), 35, 35);
        this.settingRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.settingAtlas, (Context) this, "setting.png", 0, 0);
        this.settingAtlas.load();
    }

    public void loadBottomResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        this.fiveDollar = new BitmapTextureAtlas(getTextureManager(), 75, 75);
        this.fiveDollarRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.fiveDollar, (Context) this, "greenchip.png", 0, 0);
        this.fiveDollar.load();
        this.bottomBg = new BitmapTextureAtlas(getTextureManager(), 800, 100);
        this.bottomBgRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bottomBg, (Context) this, "lowerbg.png", 0, 0);
        this.bottomBg.load();
        this.tenDollar = new BitmapTextureAtlas(getTextureManager(), 75, 75);
        this.tenDollarRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.tenDollar, (Context) this, "bluechip2.png", 0, 0);
        this.tenDollar.load();
        this.fiftyDollar = new BitmapTextureAtlas(getTextureManager(), 75, 75);
        this.fiftyDollarRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.fiftyDollar, (Context) this, "orangechip.png", 0, 0);
        this.fiftyDollar.load();
        this.hundredDollar = new BitmapTextureAtlas(getTextureManager(), 75, 75);
        this.hundredDollarRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.hundredDollar, (Context) this, "pinkchip.png", 0, 0);
        this.hundredDollar.load();
        this.fiveHundredDollar = new BitmapTextureAtlas(getTextureManager(), 75, 75);
        this.fiveHundredDollarRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.fiveHundredDollar, (Context) this, "greenchip.png", 0, 0);
        this.fiveHundredDollar.load();
        this.timerTextureAtals = new BitmapTextureAtlas(getTextureManager(), 60, 60);
        this.timerRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.timerTextureAtals, (Context) this, "yellowcircle.png", 0, 0);
        this.timerTextureAtals.load();
        this.totalBit = new BitmapTextureAtlas(getTextureManager(), 125, 41);
        this.totalBitRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.totalBit, (Context) this, "scorescreen.png", 0, 0);
        this.totalBit.load();
        this.timer_tiled_atlas = new BitmapTextureAtlas(getTextureManager(), 384, 64);
        this.timer_tiled = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.timer_tiled_atlas, (Context) this, "timer.png", 0, 0, 5, 1);
        this.timer_tiled_atlas.load();
        this.music_control_atlas = new BitmapTextureAtlas(getTextureManager(), 64, 32);
        this.music_control_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.timer_tiled_atlas, (Context) this, "music.png", 0, 0, 2, 1);
        this.music_control_atlas.load();

        this.exit_atla = new BitmapTextureAtlas(getTextureManager(), 64, 32);
        this.exit_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.exit_atla, (Context) this, "exit.png", 0, 0);
        this.exit_atla.load();
    }

    public void load_music_soud() {
        MusicFactory.setAssetBasePath("mfx/");
        SoundFactory.setAssetBasePath("mfx/");
        try {
            game_music = MusicFactory.createMusicFromAsset(getMusicManager(), this, "lobby.mp3");
            this.btn_click = SoundFactory.createSoundFromAsset(getSoundManager(), this, "button_click.mp3");
            this.place_your_bit = SoundFactory.createSoundFromAsset(getSoundManager(), this, "voice_placebets.mp3");
            this.last_call = SoundFactory.createSoundFromAsset(getSoundManager(), this, "voice_lastcall.mp3");
            this.no_more_bit_sound = SoundFactory.createSoundFromAsset(getSoundManager(), this, "voice_nobets.mp3");
            this.wheel_spin_sound = SoundFactory.createSoundFromAsset(getSoundManager(), this, "wheel_spin.mp3");
            this.win = SoundFactory.createSoundFromAsset(getSoundManager(), this, "voice_youwin.mp3");
            this.lost = SoundFactory.createSoundFromAsset(getSoundManager(), this, "lost.mp3");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadFonts() {
        this.mFont = FontFactory.createFromAsset(this.mEngine.getFontManager(), this.mEngine.getTextureManager(), 256, 256, TextureOptions.BILINEAR, getAssets(), "Aramis Italic.ttf", 26.0f, true, Color.WHITE_ABGR_PACKED_INT);
        this.mFont.load();
        this.online_gamers_font = FontFactory.createFromAsset(this.mEngine.getFontManager(), this.mEngine.getTextureManager(), 256, 256, TextureOptions.DEFAULT, getAssets(), "Lato-SemiboldItalic.ttf", 15.0f, true, Color.WHITE_ABGR_PACKED_INT);
        this.online_gamers_font.load();
        this.online_gamers_table_no_font = FontFactory.createFromAsset(this.mEngine.getFontManager(), this.mEngine.getTextureManager(), 256, 256, TextureOptions.DEFAULT, getAssets(), "Lato-SemiboldItalic.ttf", 15.0f, true, Color.WHITE_ABGR_PACKED_INT);
        this.online_gamers_table_no_font.load();
        this.winningFont = FontFactory.createFromAsset(this.mEngine.getFontManager(), this.mEngine.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR, getAssets(), "Aramis Italic.ttf", 150.0f, true, Color.WHITE_ABGR_PACKED_INT);
        this.winningFont.load();
    }

    public void unloadSelectedBit() {
        this.tenDollar.unload();
        this.fiftyDollar.unload();
        this.hundredDollar.unload();
        this.fiveHundredDollar.unload();
    }

    public void loadBitValue() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        this.bitAtlas = new BitmapTextureAtlas(getTextureManager(), 24, 24);
        this.bitRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bitAtlas, (Context) this, "bluechip5.png", 0, 0);
        this.bitAtlas.load();
        this.bitAtlas10 = new BitmapTextureAtlas(getTextureManager(), 24, 24);
        this.bitRegion10 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bitAtlas10, (Context) this, "bluechip10.png", 0, 0);
        this.bitAtlas10.load();
        this.bitAtlas50 = new BitmapTextureAtlas(getTextureManager(), 24, 24);
        this.bitRegion50 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bitAtlas50, (Context) this, "bluechip50.png", 0, 0);
        this.bitAtlas50.load();
        this.bitAtlas100 = new BitmapTextureAtlas(getTextureManager(), 24, 24);
        this.bitRegion100 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bitAtlas100, (Context) this, "bluechip100.png", 0, 0);
        this.bitAtlas100.load();
        this.bitAtlas500 = new BitmapTextureAtlas(getTextureManager(), 24, 24);
        this.bitRegion500 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bitAtlas500, (Context) this, "bluechip500.png", 0, 0);
        this.bitAtlas500.load();
    }
    public void loadWheelResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        this.rouletteWheelBg = new BitmapTextureAtlas(getTextureManager(), 340, 340);
        this.roulettWheelBgRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.rouletteWheelBg, (Context) this, "roulette_wheel_bg.png", 0, 0);
        this.rouletteWheelBg.load();
        this.ballAtlas = new BitmapTextureAtlas(getTextureManager(), 24, 24);
        this.ballRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.ballAtlas, (Context) this, "ball.png", 0, 0);
        this.ballAtlas.load();
        this.rouletteWheelMain = new BitmapTextureAtlas(getTextureManager(), 245, 245);
        this.rouletteWheelMainRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.rouletteWheelMain, (Context) this, "roulette_wheel_main.png", 0, 0);
        this.rouletteWheelMain.load();
    }


    @Override
    protected Scene onCreateScene() {
        this.mEngine.registerUpdateHandler(new FPSLogger());
        this.mScene = new Scene();
         game_music.play();
         game_music.setLooping(true);

        users_intent=getIntent();
        token=users_intent.getStringExtra("token");

        this.dataholder = getSharedPreferences("table_number", 0);
        this.total_amount_shared = getSharedPreferences("amount", 0);
        this.is_updated = getSharedPreferences("updation", 0);
        this.bitHolder = new ArrayList();
        this.dollar = new Text(170.0f, 125.0f, this.mFont, "0000000000000000000000000000", this.mEngine.getVertexBufferObjectManager());
        this.please_wait = new Text(170.0f, 125.0f, this.mFont, "Please wait for a moment.Loading...", this.mEngine.getVertexBufferObjectManager());

        //===============================
        //top items
        //==============
        this.topSprite = new Sprite(this.topRegion.getWidth() / 2.0f, 480.0f - (this.topRegion.getHeight() / 2.0f), this.topRegion, this.mEngine.getVertexBufferObjectManager());
        this.textXpos = this.topSprite.getWidth() - 150.0f;
        this.textYpos = this.topSprite.getHeight() - 20.0f;
        Sprite sprite = new Sprite(20.0f, 20.0f, this.profileRegion, this.mEngine.getVertexBufferObjectManager());
        this.topSprite.attachChild(sprite);
        this.playerName = new Text(sprite.getWidth() + 40.0f, 20.0f, this.mFont,users_intent.getStringExtra("user_name"), this.mEngine.getVertexBufferObjectManager());
        this.topSprite.attachChild(this.playerName);
        this.music_control = new AnimatedSprite(this.topSprite.getWidth() - 120.0f, 20.0f, this.music_control_region, this.mEngine.getVertexBufferObjectManager()) {
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) {
                if (pSceneTouchEvent.isActionUp()) {
                    if (GameScene.this.getMusic_current_index() == 0) {
                        GameScene.this.setMusic_current_index(1);
                        mute_bg_music();
                    } else {
                        GameScene.this.setMusic_current_index(0);
                        game_music.play();
                        game_music.setLooping(true);
                    }
                    GameScene.this.music_control.setCurrentTileIndex(GameScene.this.getMusic_current_index());
                }
                return true;
            }
        };
        setMusic_current_index(0);
        this.mScene.registerTouchArea(this.music_control);
        this.topSprite.attachChild(this.music_control);

        exit_sprite=new Sprite(music_control.getX()+music_control.getWidth()+50,music_control.getY(),exit_region,this.mEngine.getVertexBufferObjectManager()) {
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) {
                if (pSceneTouchEvent.isActionUp()) {
                    if (timer_exact_value<=15){
                        GameScene.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),"The game is allready started.please wait for a moment",Toast.LENGTH_LONG).show();
                            }
                        });
                    }else {
                        SharedPreferences.Editor edit = GameScene.this.dataholder.edit();
                        edit.clear();
                        edit.commit();
                        finish();
                    }
                }
                return true;
            }
        };
        mScene.registerTouchArea(exit_sprite);
        topSprite.attachChild(exit_sprite);

        mScene.attachChild(topSprite);

        this.place_bit_text = new Text(0.0f, 0.0f, this.mFont, this.place_your_bet, this.mEngine.getVertexBufferObjectManager());
        this.placebitentity = new Entity();
        this.placebitentity.attachChild(this.place_bit_text);
        this.placebitentity.setPosition(this.textXpos, this.textYpos);
        this.no_more_text = new Text(0.0f, 0.0f, this.mFont, this.no_more_bet, this.mEngine.getVertexBufferObjectManager());
        this.nomorebitEntity = new Entity();
        this.nomorebitEntity.attachChild(this.no_more_text);
        this.nomorebitEntity.setPosition(this.textXpos, this.textYpos);

        this.timerButton = new Sprite((this.timerRegion.getWidth() / 2.0f) + 10.0f, (this.timerRegion.getHeight() / 2.0f) + 10.0f, this.timerRegion, this.mEngine.getVertexBufferObjectManager());
        String time = Integer.toString(this.timer_exact_value);
        this.time_value = new Text(1024.0f, 1024.0f, this.mFont, "00", this.mEngine.getVertexBufferObjectManager());
        this.time_value.setPosition((((this.timerButton.getX() + this.timerButton.getWidth()) - this.timerButton.getWidth()) / 2.0f) + 5.0f, (((this.timerButton.getY() + this.timerButton.getHeight()) - this.timerButton.getHeight()) / 2.0f) + 10.0f);
        this.timerButton.attachChild(this.time_value);
        float bottombgy = this.bottomBgRegion.getHeight() / 2.0f;
        this.bottomSprite = new Sprite(this.bottomBgRegion.getWidth() / 2.0f, bottombgy, this.bottomBgRegion, this.mEngine.getVertexBufferObjectManager());
        this.bottomSprite.attachChild(this.timerButton);
        this.chipsEntity = new Entity();
        float chipsY = this.fiveDollarRegion.getHeight() / 2.0f;
        this.five = new ChipsButton(this.fiveDollarRegion.getWidth() / 2.0f, chipsY, this.fiveDollarRegion, this, this.mFont, "5") {
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) {
                if (pSceneTouchEvent.isActionUp()) {
                    GameScene.this.setSelectedBitValue(5);
                }
                return true;
            }
        };
        float f = chipsY;
        this.ten = new ChipsButton(getchipsNextPositon(this.five), f, this.tenDollarRegion, this, this.mFont, "10") {
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) {
                if (pSceneTouchEvent.isActionUp()) {
                    GameScene.this.setSelectedBitValue(10);
                    GameScene.this.btn_click.play();
                }
                return true;
            }
        };
        f = chipsY;
        this.fifty = new ChipsButton(getchipsNextPositon(this.ten), f, this.fiftyDollarRegion, this, this.mFont, "50") {
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) {
                if (pSceneTouchEvent.isActionUp()) {
                    GameScene.this.setSelectedBitValue(50);
                }
                return true;
            }
        };
        f = chipsY;
        this.hundred = new ChipsButton(getchipsNextPositon(this.fifty), f, this.hundredDollarRegion, this, this.mFont, "100") {
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) {
                if (pSceneTouchEvent.isActionUp()) {
                    GameScene.this.setSelectedBitValue(100);
                }
                return true;
            }
        };
        f = chipsY;
        this.fiveHundred = new ChipsButton(getchipsNextPositon(this.hundred), f, this.fiveHundredDollarRegion, this, this.mFont, "500") {
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) {
                if (pSceneTouchEvent.isActionUp()) {
                    GameScene.this.setSelectedBitValue(500);
                }
                return true;
            }
        };
        this.entity = new Entity();
        this.entity.setPosition(80.0f, 10.0f);
        this.entity.attachChild(this.fiveHundred);
        this.entity.attachChild(this.hundred);
        this.entity.attachChild(this.fifty);
        this.entity.attachChild(this.ten);
        this.entity.attachChild(this.five);
        this.mScene.registerTouchArea(this.five);
        this.mScene.registerTouchArea(this.ten);
        this.mScene.registerTouchArea(this.fifty);
        this.mScene.registerTouchArea(this.hundred);
        this.mScene.registerTouchArea(this.fiveHundred);
        this.bottomSprite.attachChild(this.entity);

        this.spriteList = new ArrayList();
        float bitXpos = this.totalBitRegion.getWidth() / 2.0f;
        float bitYpos = bottombgy - 20.0f;
        this.bitSprite = new ChipsButton(bitXpos, bitYpos, this.totalBitRegion, this, this.mFont, "000");
        this.total_credit = new ChipsButton(80.0f + this.bitSprite.getWidth(), bitYpos, this.totalBitRegion, this, this.mFont, "000000000000000");
        this.clear = new ChipsButton(bitXpos - 110.0f, bitYpos, this.totalBitRegion, this, this.mFont, " clear") {
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) {
                if (pSceneTouchEvent.isActionUp()) {
                    GameScene.this.update_dataholder();
                }
                return true;
            }
        };
        this.mScene.registerTouchArea(this.clear);
        this.clear.setWidth(80.0f);
        this.total_credit.setText("325");
        this.bitEntity = new Entity();
        this.total_bit_string = new Text(580.0f, 15.0f, this.mFont, "Total bit", this.mEngine.getVertexBufferObjectManager());
        this.total_credit_string = new Text(720.0f, 15.0f, this.mFont, "Total Credit", this.mEngine.getVertexBufferObjectManager());
        this.clear_string = new Text(this.total_bit_string.getX() - 120.0f, 15.0f, this.mFont, "clear", this.mEngine.getVertexBufferObjectManager());
        this.bitEntity.setPosition(520.0f, 20.0f);
        this.bitEntity.attachChild(this.bitSprite);
        this.bitEntity.attachChild(this.total_credit);
        this.bitEntity.attachChild(this.clear);
        this.bottomSprite.attachChild(this.bitEntity);
        this.bottomSprite.attachChild(this.total_bit_string);
        this.bottomSprite.attachChild(this.total_credit_string);
        this.bottomSprite.attachChild(this.clear_string);
        this.spriteList = new ArrayList();
        float bitXpos2 = this.totalBitRegion.getWidth() / 2.0f;
        float bitYpos2 = bottombgy - 20.0f;
        this.bitSprite = new ChipsButton(bitXpos2, bitYpos2, this.totalBitRegion, this, this.mFont, "000");
        this.total_credit = new ChipsButton(80.0f + this.bitSprite.getWidth(), bitYpos, this.totalBitRegion, this, this.mFont, "000000000000000");
        this.clear = new ChipsButton(bitXpos - 110.0f, bitYpos, this.totalBitRegion, this, this.mFont, " clear") {
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) {
                if (pSceneTouchEvent.isActionUp()) {
                    GameScene.this.update_dataholder();
                }
                return true;
            }
        };
        this.mScene.registerTouchArea(this.clear);
        this.clear.setWidth(80.0f);
        this.total_credit.setText(users_intent.getStringExtra("amount"));
        this.bitEntity = new Entity();
        this.total_bit_string = new Text(580.0f, 15.0f, this.mFont, "Total bit", this.mEngine.getVertexBufferObjectManager());
        this.total_credit_string = new Text(720.0f, 15.0f, this.mFont, "Total Credit", this.mEngine.getVertexBufferObjectManager());
        this.clear_string = new Text(this.total_bit_string.getX() - 120.0f, 15.0f, this.mFont, "clear", this.mEngine.getVertexBufferObjectManager());
        this.bitEntity.setPosition(520.0f, 20.0f);
        this.bitEntity.attachChild(this.bitSprite);
        this.bitEntity.attachChild(this.total_credit);
        this.bitEntity.attachChild(this.clear);
        this.bottomSprite.attachChild(this.bitEntity);
        this.bottomSprite.attachChild(this.total_bit_string);
        this.bottomSprite.attachChild(this.total_credit_string);
        this.bottomSprite.attachChild(this.clear_string);
         mScene.attachChild(bottomSprite);


        //=================
        //end of top items
        //=========


        //==============================
        //center components
        //================================
        this.centerContainer = new Entity();
        this.centerContainer.setWidth(1000.0f);
        this.centerContainer.setHeight(400.0f);
        sprite = new Sprite((this.roulettWheelBgRegion.getWidth() / 2.0f) - 250.0f, this.roulettWheelBgRegion.getHeight() / 2.0f, this.roulettWheelBgRegion, this.mEngine.getVertexBufferObjectManager());
        this.centerContainer.attachChild(sprite);
        this.wheelSprite = new Sprite((this.rouletteWheelMainRegion.getWidth() / 2.0f) + 45.0f, (this.rouletteWheelMainRegion.getHeight() / 2.0f) + 53.0f, this.rouletteWheelMainRegion, this.mEngine.getVertexBufferObjectManager()) {
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) {
                int x = Math.round(X);
                int y = Math.round(Y);
                Log.d("xp", "" + x);
                Log.d("yp", "" + y);
                return true;
            }
        };

        this.winningNumber = new Text(125.0f, 125.0f, this.winningFont, "135151", getVertexBufferObjectManager());
        this.mScene.registerTouchArea(this.wheelSprite);
        float circlex = this.wheelSprite.getWidth() / 2.0f;
        float circley = (this.wheelSprite.getY() / 2.0f) + 38.0f;
        sprite.attachChild(this.wheelSprite);
        float numberypos = this.selectedNumberTexture.getHeight() / 2.0f;
        this.tableSprite = new Sprite((this.tableTextureRegion.getWidth() / 2.0f) + 79.0f, this.tableTextureRegion.getHeight() / 2.0f, this.tableTextureRegion, this.mEngine.getVertexBufferObjectManager()){

            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) {
                if (GameScene.this.istableToucehEnable && pSceneTouchEvent.isActionUp()) {
                    if (!GameScene.this.is_bit_value_correct || GameScene.this.get_total_credit() == 0 || GameScene.this.getSelectedBitValue() > GameScene.this.get_total_credit()) {
                        GameScene.this.show_error("your balance is not enough to reach on " + (Integer.parseInt(GameScene.this.bitSprite.getText()) + GameScene.this.getSelectedBitValue()) + "$");
                    } else if (GameScene.this.getSelectedBitValue() > 0) {
                        String bitString = Integer.toString(GameScene.this.getSelectedBitValue());
                        GameScene.this.btn_click.play();
                        int x = Math.round(X);
                        int y = Math.round(Y);
                        Log.d("x value", "" + x + "y value " + y);
                        int xp=0;
                        int yp=0;
                        if (x >= 8 && x <= 45 && y >= 75 && y <= 329) {
                            xp = GameScene.this.find_center_x(8, 45);
                            yp = GameScene.this.find_center_y(75, 329);
                            addZero(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(0), bitString);
                            GameScene.this.send_table_bit(Integer.toString(0), xp + "," + yp);
                        } else if (x >= 100 && x <= 135 && y >= 260 && y <= 329) {
                            xp = GameScene.this.find_center_x(135, 100);
                            yp = GameScene.this.find_center_y(260, 329);
                            GameScene.this.addsix(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(6), bitString);
                            GameScene.this.send_table_bit(Integer.toString(6), xp + "," + yp);
                        } else if (x >= 100 && x <= 135 && y >= 170 && y <= 240) {
                            xp = GameScene.this.find_center_x(135, 100);
                            yp = GameScene.this.find_center_y(170, 260);
                            GameScene.this.addfive(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(5), bitString);
                            GameScene.this.send_table_bit(Integer.toString(5), xp + "," + yp);
                        } else if (x >= 100 && x <= 135 && y >= 82 && y <= 155) {
                            xp = GameScene.this.find_center_x(100, 135);
                            yp = GameScene.this.find_center_y(82, 155);
                            GameScene.this.addfour(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(4), bitString);
                            GameScene.this.send_table_bit(Integer.toString(4), xp + "," + yp);
                        } else if (x >= 145 && x <= 180 && y >= 260 && y <= 337) {
                            xp = GameScene.this.find_center_x(145, 180);
                            yp = GameScene.this.find_center_y(337, 260);
                            GameScene.this.addnine(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(9), bitString);
                            GameScene.this.send_table_bit(Integer.toString(9), xp + "," + yp);
                        } else if (x >= 145 && x <= 180 && y >= 170 && y <= 240) {
                            xp = GameScene.this.find_center_x(145, 180);
                            yp = GameScene.this.find_center_y(170, 260);
                            GameScene.this.addeight(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(8), bitString);
                            GameScene.this.send_table_bit(Integer.toString(8), xp + "," + yp);
                        } else if (x >= 145 && x <= 180 && y >= 82 && y <= 155) {
                            xp = GameScene.this.find_center_x(145, 180);
                            yp = GameScene.this.find_center_y(82, 155);
                            GameScene.this.addseven(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(7), bitString);
                            GameScene.this.send_table_bit(Integer.toString(7), xp + "," + yp);
                        } else if (x >= 190 && x <= 225 && y >= 260 && y <= 337) {
                            xp = GameScene.this.find_center_x(190, 225);
                            yp = GameScene.this.find_center_y(337, 260);
                            GameScene.this.add12(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(12), bitString);
                            GameScene.this.send_table_bit(Integer.toString(12), xp + "," + yp);
                        } else if (x >= 190 && x <= 225 && y >= 170 && y <= 240) {
                            xp = GameScene.this.find_center_x(190, 225);
                            yp = GameScene.this.find_center_y(240, 170);
                            GameScene.this.add11(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(11), bitString);
                            GameScene.this.send_table_bit(Integer.toString(11), xp + "," + yp);
                        } else if (x >= 190 && x <= 225 && y >= 82 && y <= 155) {
                            xp = GameScene.this.find_center_x(190, 225);
                            yp = GameScene.this.find_center_y(82, 155);
                            GameScene.this.add10(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(10), bitString);
                            GameScene.this.send_table_bit(Integer.toString(10), xp + "," + yp);
                        } else if (x >= 235 && x <= 270 && y >= 260 && y <= 337) {
                            xp = GameScene.this.find_center_x(235, 270);
                            yp = GameScene.this.find_center_y(240, 337);
                            GameScene.this.add15(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(15), bitString);
                            GameScene.this.send_table_bit(Integer.toString(15), xp + "," + yp);
                        } else if (x >= 235 && x <= 270 && y >= 170 && y <= 240) {
                            GameScene.this.add14(x, y, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(14), bitString);
                            GameScene.this.send_table_bit(Integer.toString(14), x + "," + y);
                        } else if (x >= 235 && x <= 270 && y >= 82 && y <= 155) {
                            xp = GameScene.this.find_center_x(235, 270);
                            yp = GameScene.this.find_center_y(82, 155);
                            GameScene.this.add13(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(13), bitString);
                            GameScene.this.send_table_bit(Integer.toString(13), xp + "," + yp);
                        } else if (x >= 280 && x <= 315 && y >= 260 && y <= 337) {
                            xp = GameScene.this.find_center_x(280, 315);
                            yp = GameScene.this.find_center_y(337, 260);
                            GameScene.this.add18(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(18), bitString);
                            GameScene.this.send_table_bit(Integer.toString(18), xp + "," + yp);
                        } else if (x >= 280 && x <= 315 && y >= 170 && y <= 240) {
                            xp = GameScene.this.find_center_x(280, 315);
                            yp = GameScene.this.find_center_y(170, 240);
                            GameScene.this.add17(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(17), bitString);
                            GameScene.this.send_table_bit(Integer.toString(17), xp + "," + yp);
                        } else if (x >= 280 && x <= 315 && y >= 82 && y <= 155) {
                            xp = GameScene.this.find_center_x(280, 315);
                            yp = GameScene.this.find_center_y(82, 155);
                            GameScene.this.add16(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(16), bitString);
                            GameScene.this.send_table_bit(Integer.toString(16), xp + "," + yp);
                        } else if (x >= 325 && x <= 360 && y >= 260 && y <= 337) {
                            xp = GameScene.this.find_center_x(325, 360);
                            yp = GameScene.this.find_center_y(337, 260);
                            GameScene.this.add21(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(21), bitString);
                            GameScene.this.send_table_bit(Integer.toString(21), xp + "," + yp);
                        } else if (x >= 325 && x <= 360 && y >= 170 && y <= 240) {
                            xp = GameScene.this.find_center_x(325, 360);
                            yp = GameScene.this.find_center_y(240, 170);
                            GameScene.this.add20(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(20), bitString);
                            GameScene.this.send_table_bit(Integer.toString(20), xp + "," + yp);
                        } else if (x >= 325 && x <= 360 && y >= 82 && y <= 155) {
                            xp = GameScene.this.find_center_x(325, 360);
                            yp = GameScene.this.find_center_y(82, 155);
                            GameScene.this.add19(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(19), bitString);
                            GameScene.this.send_table_bit(Integer.toString(19), xp + "," + yp);
                        } else if (x >= 370 && x <= 405 && y >= 260 && y <= 337) {
                            xp = GameScene.this.find_center_x(370, 405);
                            yp = GameScene.this.find_center_y(337, 260);
                            GameScene.this.add24(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(24), bitString);
                            GameScene.this.send_table_bit(Integer.toString(24), xp + "," + yp);
                        } else if (x >= 370 && x <= 405 && y >= 170 && y <= 240) {
                            xp = GameScene.this.find_center_x(370, 405);
                            yp = GameScene.this.find_center_y(240, 170);
                            GameScene.this.add23(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(23), bitString);
                            GameScene.this.send_table_bit(Integer.toString(23), xp + "," + yp);
                        } else if (x >= 370 && x <= 405 && y >= 82 && y <= 155) {
                            xp = GameScene.this.find_center_x(370, 405);
                            yp = GameScene.this.find_center_y(82, 155);
                            GameScene.this.add22(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(22), bitString);
                            GameScene.this.send_table_bit(Integer.toString(22), xp + "," + yp);
                        } else if (x >= 415 && x <= 450 && y >= 260 && y <= 337) {
                            xp = GameScene.this.find_center_x(415, 450);
                            yp = GameScene.this.find_center_y(337, 260);
                            GameScene.this.add27(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(27), bitString);
                            GameScene.this.send_table_bit(Integer.toString(27), xp + "," + yp);
                        } else if (x >= 415 && x <= 450 && y >= 170 && y <= 240) {
                            xp = GameScene.this.find_center_x(415, 450);
                            yp = GameScene.this.find_center_y(240, 170);
                            GameScene.this.add26(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(26), bitString);
                            GameScene.this.send_table_bit(Integer.toString(26), xp + "," + yp);
                        } else if (x >= 415 && x <= 450 && y >= 82 && y <= 155) {
                            xp = GameScene.this.find_center_x(415, 450);
                            yp = GameScene.this.find_center_y(82, 155);
                            GameScene.this.add25(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(25), bitString);
                            GameScene.this.send_table_bit(Integer.toString(25), xp + "," + yp);
                        } else if (x >= 460 && x <= 495 && y >= 260 && y <= 337) {
                            xp = GameScene.this.find_center_x(460, 495);
                            yp = GameScene.this.find_center_y(337, 260);
                            GameScene.this.add30(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(30), bitString);
                            GameScene.this.send_table_bit(Integer.toString(30), xp + "," + yp);
                        } else if (x >= 460 && x <= 495 && y >= 170 && y <= 240) {
                            xp = GameScene.this.find_center_x(460, 495);
                            yp = GameScene.this.find_center_y(240, 170);
                            GameScene.this.add29(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(29), bitString);
                            GameScene.this.send_table_bit(Integer.toString(29), xp + "," + yp);
                        } else if (x >= 460 && x <= 495 && y >= 82 && y <= 155) {
                            xp = GameScene.this.find_center_x(460, 495);
                            yp = GameScene.this.find_center_y(82, 155);
                            GameScene.this.add28(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(28), bitString);
                            GameScene.this.send_table_bit(Integer.toString(28), xp + "," + yp);
                        } else if (x >= 505 && x <= 540 && y >= 260 && y <= 337) {
                            xp = GameScene.this.find_center_x(505, 540);
                            yp = GameScene.this.find_center_y(337, 260);
                            GameScene.this.add33(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(33), bitString);
                            GameScene.this.send_table_bit(Integer.toString(33), xp + "," + yp);
                        } else if (x >= 505 && x <= 540 && y >= 170 && y <= 240) {
                            xp = GameScene.this.find_center_x(505, 540);
                            yp = GameScene.this.find_center_y(240, 170);
                            GameScene.this.add32(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(32), bitString);
                            GameScene.this.send_table_bit(Integer.toString(32), xp + "," + yp);
                        } else if (x >= 505 && x <= 540 && y >= 82 && y <= 155) {
                            xp = GameScene.this.find_center_x(505, 540);
                            yp = GameScene.this.find_center_y(82, 155);
                            GameScene.this.add31(xp, yp, bitString);
                            GameScene.this.storeData(Integer.toString(31), bitString);
                            GameScene.this.send_table_bit(Integer.toString(31), xp + "," + yp);
                        } else if (x >= 550 && x <= 585 && y >= 260 && y <= 337) {
                            xp = GameScene.this.find_center_x(550, 585);
                            yp = GameScene.this.find_center_y(337, 260);
                            GameScene.this.add36(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(36), bitString);
                            GameScene.this.send_table_bit(Integer.toString(36), xp + "," + yp);
                        } else if (x >= 550 && x <= 585 && y >= 170 && y <= 240) {
                            xp = GameScene.this.find_center_x(550, 585);
                            yp = GameScene.this.find_center_y(240, 170);
                            GameScene.this.add35(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(35), bitString);
                            GameScene.this.send_table_bit(Integer.toString(35), xp + "," + yp);
                        } else if (x >= 550 && x <= 585 && y >= 82 && y <= 155) {
                            xp = GameScene.this.find_center_x(550, 585);
                            yp = GameScene.this.find_center_y(82, 155);
                            GameScene.this.add34(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(34), bitString);
                            GameScene.this.send_table_bit(Integer.toString(34), xp + "," + yp);
                        } else if (x > 90 && x <= 100 && y >= 240 && y <= 260) {
                            xp = GameScene.this.find_center_x(90, 100);
                            yp = GameScene.this.find_center_y(240, 260);
                            GameScene.this.add3625(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("cornerone", bitString);
                            GameScene.this.send_table_bit("cornerone", xp + "," + yp);
                        } else if (x > 135 && x <= 145 && y >= 240 && y <= 260) {
                            xp = GameScene.this.find_center_x(135, 145);
                            yp = GameScene.this.find_center_y(240, 260);
                            GameScene.this.add6958(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("cornertwo", bitString);
                            GameScene.this.send_table_bit("cornertwo", xp + "," + yp);
                        } else if (x > 180 && x <= 190 && y >= 240 && y <= 260) {
                            xp = GameScene.this.find_center_x(180, 190);
                            yp = GameScene.this.find_center_y(240, 260);
                            GameScene.this.add912811(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("cornerthree", bitString);
                            GameScene.this.send_table_bit("cornerthree", xp + "," + yp);
                        } else if (x > 225 && x <= 235 && y >= 240 && y <= 260) {
                            xp = GameScene.this.find_center_x(225, 235);
                            yp = GameScene.this.find_center_y(240, 260);
                            GameScene.this.add912151114(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("cornerfour", bitString);
                            GameScene.this.send_table_bit("cornerfour", xp + "," + yp);
                        } else if (x > 270 && x <= 280 && y >= 240 && y <= 260) {
                            xp = GameScene.this.find_center_x(270, 280);
                            yp = GameScene.this.find_center_y(240, 260);
                            GameScene.this.add15181417(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("cornerfifth", bitString);
                            GameScene.this.send_table_bit("cornerfifth", xp + "," + yp);
                        } else if (x > 315 && x <= 325 && y >= 240 && y <= 260) {
                            xp = GameScene.this.find_center_x(315, 325);
                            yp = GameScene.this.find_center_y(240, 260);
                            GameScene.this.add18211720(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("cornersixth", bitString);
                            GameScene.this.send_table_bit("cornersixth", xp + "," + yp);
                        } else if (x > 360 && x <= 370 && y >= 240 && y <= 260) {
                            xp = GameScene.this.find_center_x(369, 370);
                            yp = GameScene.this.find_center_y(240, 260);
                            GameScene.this.add21242023(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("cornerseventh", bitString);
                            GameScene.this.send_table_bit("cornerseventh", xp + "," + yp);
                        } else if (x > 405 && x <= 415 && y >= 240 && y <= 260) {
                            xp = GameScene.this.find_center_x(405, 415);
                            yp = GameScene.this.find_center_y(240, 260);
                            GameScene.this.add24272326(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("cornereighth", bitString);
                            GameScene.this.send_table_bit("cornereighth", xp + "," + yp);
                        } else if (x > 450 && x <= 460 && y >= 240 && y <= 260) {
                            xp = GameScene.this.find_center_x(450, 460);
                            yp = GameScene.this.find_center_y(240, 260);
                            GameScene.this.add27302629(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("cornerninth", bitString);
                            GameScene.this.send_table_bit("cornerninth", xp + "," + yp);
                        } else if (x > 495 && x <= 505 && y >= 240 && y <= 260) {
                            xp = GameScene.this.find_center_x(495, 505);
                            yp = GameScene.this.find_center_y(240, 260);
                            GameScene.this.add30332932(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("cornertenth", bitString);
                            GameScene.this.send_table_bit("cornertenth", xp + "," + yp);
                        } else if (x > 540 && x <= 550 && y >= 240 && y <= 260) {
                            xp = GameScene.this.find_center_x(540, 550);
                            yp = GameScene.this.find_center_y(240, 260);
                            GameScene.this.add33363235(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("cornereleventh", bitString);
                            GameScene.this.send_table_bit("cornereleventh", xp + "," + yp);
                        } else if (x > 90 && x < 100 && y < 170 && y > 150) {
                            xp = GameScene.this.find_center_x(90, 100);
                            yp = GameScene.this.find_center_y(170, 150);
                            GameScene.this.add2514(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("cornertwelvth", bitString);
                            GameScene.this.send_table_bit("cornertwelvth", xp + "," + yp);
                        } else if (x > TransportMediator.KEYCODE_MEDIA_RECORD && x <= 143 && y < 170 && y > 150) {
                            xp = GameScene.this.find_center_x(TransportMediator.KEYCODE_MEDIA_RECORD, 143);
                            yp = GameScene.this.find_center_y(170, 150);
                            GameScene.this.add5847(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("cornerthirteen", bitString);
                            GameScene.this.send_table_bit("cornerthirteen", xp + "," + yp);
                        } else if (x > 180 && x <= 190 && y < 170 && y > 150) {
                            xp = GameScene.this.find_center_x(180, 190);
                            yp = GameScene.this.find_center_y(170, 150);
                            GameScene.this.add811710(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("cornerfourteen", bitString);
                            GameScene.this.send_table_bit("cornerfourteen", xp + "," + yp);
                        } else if (x > 220 && x <= 235 && y < 170 && y > 150) {
                            xp = GameScene.this.find_center_x(220, 235);
                            yp = GameScene.this.find_center_y(170, 150);
                            GameScene.this.add11141013(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("cornerfifteen", bitString);
                            GameScene.this.send_table_bit("cornerfifteen", xp + "," + yp);
                        } else if (x > 270 && x <= 280 && y < 170 && y > 150) {
                            xp = GameScene.this.find_center_x(270, 280);
                            yp = GameScene.this.find_center_y(170, 150);
                            GameScene.this.add14171316(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("cornersixteen", bitString);
                            GameScene.this.send_table_bit("cornersixteen", xp + "," + yp);
                        } else if (x > 310 && x <= 325 && y < 170 && y > 150) {
                            xp = GameScene.this.find_center_x(310, 325);
                            yp = GameScene.this.find_center_y(170, 150);
                            GameScene.this.add17201619(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("cornerseventeen", bitString);
                            GameScene.this.send_table_bit("cornerseventeen", xp + "," + yp);
                        } else if (x > 355 && x <= 370 && y < 170 && y > 150) {
                            xp = GameScene.this.find_center_x(355, 370);
                            yp = GameScene.this.find_center_y(170, 150);
                            GameScene.this.add20231922(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("cornereighteen", bitString);
                            GameScene.this.send_table_bit("cornereighteen", xp + "," + yp);
                        } else if (x > 405 && x <= 416 && y < 170 && y > 150) {
                            xp = GameScene.this.find_center_x(405, 416);
                            yp = GameScene.this.find_center_y(170, 150);
                            GameScene.this.add23262225(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("cornerninteen", bitString);
                            GameScene.this.send_table_bit("cornerninteen", xp + "," + yp);
                        } else if (x > 445 && x <= 460 && y < 170 && y > 150) {
                            xp = GameScene.this.find_center_x(445, 460);
                            yp = GameScene.this.find_center_y(170, 150);
                            GameScene.this.add26292528(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("cornertwenty", bitString);
                            GameScene.this.send_table_bit("cornertwenty", xp + "," + yp);
                        } else if (x > 495 && x <= 505 && y < 170 && y > 150) {
                            xp = GameScene.this.find_center_x(495, 505);
                            yp = GameScene.this.find_center_y(170, 150);
                            GameScene.this.add29322831(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("cornertwentyone", bitString);
                            GameScene.this.send_table_bit("cornertwentyone", xp + "," + yp);
                        } else if (x > 540 && x <= 550 && y < 170 && y > 150) {
                            xp = GameScene.this.find_center_x(540, 550);
                            yp = GameScene.this.find_center_y(170, 150);
                            GameScene.this.add32353134(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("cornertwentytwo", bitString);
                            GameScene.this.send_table_bit("cornertwentytwo", xp + "," + yp);
                        } else if (x >= 53 && x <= 91 && y >= 262 && y <= 329) {
                            GameScene.this.addthree(x, y, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(3), bitString);
                            GameScene.this.send_table_bit(Integer.toString(3), x + "," + y);
                        } else if (x >= 53 && x <= 91 && y >= 170 && y <= 245) {
                            xp = GameScene.this.find_center_x(53, 91);
                            yp = GameScene.this.find_center_y(170, 245);
                            GameScene.this.addTwo(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData(Integer.toString(2), bitString);
                            GameScene.this.send_table_bit(Integer.toString(2), xp + "," + yp);
                        } else if (x >= 53 && x <= 87 && y <= 155 && y >= 84) {
                            xp = GameScene.this.find_center_x(53, 87);
                            yp = GameScene.this.find_center_y(155, 84);
                            GameScene.this.addOne(xp, yp, bitString);
                            GameScene.this.storeData(Integer.toString(1), bitString);
                            GameScene.this.send_table_bit(Integer.toString(1), xp + "," + yp);
                        } else if (x >= 45 && x <= 136 && y <= 65 && y >= 9) {
                            xp = GameScene.this.find_center_x(45, 136);
                            yp = GameScene.this.find_center_y(65, 9);
                            GameScene.this.add1bt18(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("low", bitString);
                            GameScene.this.send_table_bit("low", xp + "," + yp);
                        } else if (x <= 225 && x >= 143 && y <= 65 && y >= 9) {
                            xp = GameScene.this.find_center_x(225, 143);
                            yp = GameScene.this.find_center_y(65, 9);
                            GameScene.this.addeven(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("even", bitString);
                            GameScene.this.send_table_bit("even", xp + "," + yp);
                        } else if (x <= 315 && x >= 232 && y <= 65 && y >= 9) {
                            xp = GameScene.this.find_center_x(315, 232);
                            yp = GameScene.this.find_center_y(65, 9);
                            GameScene.this.addRed(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("red", bitString);
                            GameScene.this.send_table_bit("red", xp + "," + yp);
                        } else if (x <= 405 && x >= 325 && y <= 65 && y >= 9) {
                            xp = GameScene.this.find_center_x(405, 325);
                            yp = GameScene.this.find_center_y(65, 9);
                            GameScene.this.addBlack(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("black", bitString);
                            GameScene.this.send_table_bit("black", xp + "," + yp);
                        } else if (x <= 495 && x >= 415 && y <= 65 && y >= 9) {
                            xp = GameScene.this.find_center_x(495, 415);
                            yp = GameScene.this.find_center_y(60, 9);
                            GameScene.this.addOdd(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("odd", bitString);
                            GameScene.this.send_table_bit("odd", xp + "," + yp);
                        } else if (x <= 585 && x >= 505 && y <= 65 && y >= 9) {
                            xp = GameScene.this.find_center_x(585, 505);
                            yp = GameScene.this.find_center_y(65, 9);
                            GameScene.this.add19bt36(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("high", bitString);
                            GameScene.this.send_table_bit("high", xp + "," + yp);
                        } else if (x <= 625 && x >= 595 && y <= 155 && y >= 78) {
                            xp = GameScene.this.find_center_x(625, 595);
                            yp = GameScene.this.find_center_y(155, 78);
                            GameScene.this.add2to1below(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("twotoonebelow", bitString);
                            GameScene.this.send_table_bit("twotoonebelow", xp + "," + yp);
                        } else if (x <= 625 && x >= 595 && y <= 245 && y >= 168) {
                            xp = GameScene.this.find_center_x(625, 595);
                            yp = GameScene.this.find_center_y(245, 168);
                            GameScene.this.add2to1middle(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("twotoonemiddle", bitString);
                            GameScene.this.send_table_bit("twotoonemiddle", xp + "," + yp);
                        } else if (x <= 625 && x >= 595 && y <= 337 && y >= 255) {
                            xp = GameScene.this.find_center_x(625, 595);
                            yp = GameScene.this.find_center_y(337, 255);
                            GameScene.this.add2to1Top(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("twotoonetop", bitString);
                            GameScene.this.send_table_bit("twotoonetop", xp + "," + yp);
                        } else if (x > 87 && x < 100 && y > 331 && y < 339) {
                            xp = GameScene.this.find_center_x(87, 100);
                            yp = GameScene.this.find_center_y(331, 339);
                            GameScene.this.firstsixline(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("sixlineone", bitString);
                            GameScene.this.send_table_bit("sixlineone", xp + "," + yp);
                        } else if (x > 133 && x < 146 && y > 330 && y < 337) {
                            xp = GameScene.this.find_center_x(133, 146);
                            yp = GameScene.this.find_center_y(330, 337);
                            GameScene.this.secondsixline(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("sixlinetwo", bitString);
                            GameScene.this.send_table_bit("sixlinetwo", xp + "," + yp);
                        } else if (x > 175 && x < 191 && y > 330 && y < 337) {
                            xp = GameScene.this.find_center_x(175, 191);
                            yp = GameScene.this.find_center_y(330, 337);
                            GameScene.this.thirdsixline(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("thirdlinetwo", bitString);
                            GameScene.this.send_table_bit("thirdlinetwo", xp + "," + yp);
                        } else if (x > 220 && x < 235 && y > 330 && y < 337) {
                            xp = GameScene.this.find_center_x(220, 235);
                            yp = GameScene.this.find_center_y(330, 337);
                            GameScene.this.fourthsixline(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("fourthlinetwo", bitString);
                            GameScene.this.send_table_bit("fourthlinetwo", xp + "," + yp);
                        } else if (x > 264 && x < 283 && y > 330 && y < 337) {
                            xp = GameScene.this.find_center_x(264, 283);
                            yp = GameScene.this.find_center_y(330, 337);
                            GameScene.this.fifthsixline(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("fifthlinetwo", bitString);
                            GameScene.this.send_table_bit("fifthlinetwo", xp + "," + yp);
                        } else if (x > 315 && x < 325 && y > 330 && y < 337) {
                            xp = GameScene.this.find_center_x(315, 325);
                            yp = GameScene.this.find_center_y(330, 337);
                            GameScene.this.sixthsixline(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("sixthlinetwo", bitString);
                            GameScene.this.send_table_bit("sixthlinetwo", xp + "," + yp);
                        } else if (x > 355 && x < 370 && y > 330 && y < 337) {
                            xp = GameScene.this.find_center_x(355, 370);
                            yp = GameScene.this.find_center_y(330, 337);
                            GameScene.this.seventhsixline(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("seventhlinetwo", bitString);
                            GameScene.this.send_table_bit("seventhlinetwo", xp + "," + yp);
                        } else if (x > 400 && x < 420 && y > 330 && y < 337) {
                            xp = GameScene.this.find_center_x(400, 420);
                            yp = GameScene.this.find_center_y(330, 337);
                            GameScene.this.eighthsixline(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("eighthlinetwo", bitString);
                            GameScene.this.send_table_bit("eighthlinetwo", xp + "," + yp);
                        } else if (x > 445 && x < 465 && y > 330 && y < 337) {
                            xp = GameScene.this.find_center_x(445, 465);
                            yp = GameScene.this.find_center_y(330, 337);
                            GameScene.this.ninthsixline(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("ninthlinetwo", bitString);
                            GameScene.this.send_table_bit("ninthlinetwo", xp + "," + yp);
                        } else if (x > 490 && x < 510 && y > 330 && y < 337) {
                            xp = GameScene.this.find_center_x(490, 510);
                            yp = GameScene.this.find_center_y(330, 337);
                            GameScene.this.tenthsixline(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("tenthlinetwo", bitString);
                            GameScene.this.send_table_bit("tenthlinetwo", xp + "," + yp);
                        } else if (x > 540 && x < 555 && y > 330 && y < 337) {
                            xp = GameScene.this.find_center_x(540, 555);
                            yp = GameScene.this.find_center_y(330, 337);
                            GameScene.this.eleventhsixline(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("eleventhlinetwo", bitString);
                            GameScene.this.send_table_bit("eleventhlinetwo", xp + "," + yp);
                        } else if (x > 90 && x <= 100 && y >= 260 && y <= 330) {
                            xp = GameScene.this.find_center_x(90, 100);
                            yp = GameScene.this.find_center_y(330, 260);
                            GameScene.this.addsplit_32(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splitthreesix", bitString);
                            GameScene.this.send_table_bit("splitthreesix", xp + "," + yp);
                        } else if (x > 135 && x <= 145 && y >= 260 && y <= 330) {
                            xp = GameScene.this.find_center_x(135, 145);
                            yp = GameScene.this.find_center_y(330, 260);
                            GameScene.this.addsplit_69(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splitsixnine", bitString);
                            GameScene.this.send_table_bit("splitsixnine", xp + "," + yp);
                        } else if (x > 180 && x <= 190 && y >= 260 && y <= 330) {
                            xp = GameScene.this.find_center_x(180, 190);
                            yp = GameScene.this.find_center_y(330, 260);
                            GameScene.this.addsplit_912(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splitninetwelve", bitString);
                            GameScene.this.send_table_bit("splitninetwelve", xp + "," + yp);
                        } else if (x > 225 && x <= 235 && y >= 260 && y <= 330) {
                            xp = GameScene.this.find_center_x(225, 235);
                            yp = GameScene.this.find_center_y(330, 260);
                            GameScene.this.addsplit_1215(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splittwelvefifteen", bitString);
                            GameScene.this.send_table_bit("splittwelvefifteen", xp + "," + yp);
                        } else if (x > 270 && x <= 280 && y >= 260 && y <= 330) {
                            xp = GameScene.this.find_center_x(270, 280);
                            yp = GameScene.this.find_center_y(330, 260);
                            GameScene.this.addsplit_1518(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splitfifteeneighteen", bitString);
                            GameScene.this.send_table_bit("splitfifteeneighteen", xp + "," + yp);
                        } else if (x > 315 && x <= 325 && y >= 260 && y <= 330) {
                            xp = GameScene.this.find_center_x(315, 325);
                            yp = GameScene.this.find_center_y(330, 260);
                            GameScene.this.addsplit_1821(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("spliteighteentwentyone", bitString);
                            GameScene.this.send_table_bit("spliteighteentwentyone", xp + "," + yp);
                        } else if (x > 360 && x <= 370 && y >= 260 && y <= 330) {
                            xp = GameScene.this.find_center_x(369, 370);
                            yp = GameScene.this.find_center_y(330, 260);
                            GameScene.this.addsplit_2124(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splittwentyonetwentyfour", bitString);
                            GameScene.this.send_table_bit("splittwentyonetwentyfour", xp + "," + yp);
                        } else if (x > 405 && x <= 415 && y >= 260 && y <= 330) {
                            xp = GameScene.this.find_center_x(405, 415);
                            yp = GameScene.this.find_center_y(330, 260);
                            GameScene.this.addsplit_2427(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splittwentyfourtwentyseven", bitString);
                            GameScene.this.send_table_bit("splittwentyfourtwentyseven", xp + "," + yp);
                        } else if (x > 450 && x <= 460 && y >= 260 && y <= 330) {
                            xp = GameScene.this.find_center_x(450, 460);
                            yp = GameScene.this.find_center_y(330, 260);
                            GameScene.this.addsplit_2730(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splittwentyseventhirty", bitString);
                            GameScene.this.send_table_bit("splittwentyseventhirty", xp + "," + yp);
                        } else if (x > 495 && x <= 505 && y >= 260 && y <= 330) {
                            xp = GameScene.this.find_center_x(495, 505);
                            yp = GameScene.this.find_center_y(330, 260);
                            GameScene.this.addsplit_3033(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splitthirtythirtythree", bitString);
                            GameScene.this.send_table_bit("splitthirtythirtythree", xp + "," + yp);
                        } else if (x > 540 && x <= 550 && y >= 260 && y <= 330) {
                            xp = GameScene.this.find_center_x(540, 550);
                            yp = GameScene.this.find_center_y(330, 260);
                            GameScene.this.addsplit_3336(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splitthirtythreethirtysix", bitString);
                            GameScene.this.send_table_bit("splitthirtythreethirtysix", xp + "," + yp);
                        } else if (x > 90 && x <= 100 && y >= 175 && y <= 240) {
                            xp = GameScene.this.find_center_x(90, 100);
                            yp = GameScene.this.find_center_y(175, 240);
                            GameScene.this.addsplit_25(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splittwofive", bitString);
                            GameScene.this.send_table_bit("splittwofive", xp + "," + yp);
                        } else if (x > 135 && x <= 145 && y >= 175 && y <= 240) {
                            xp = GameScene.this.find_center_x(135, 145);
                            yp = GameScene.this.find_center_y(175, 240);
                            GameScene.this.addsplit_58(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splitfiveeight", bitString);
                            GameScene.this.send_table_bit("splitfiveeight", xp + "," + yp);
                        } else if (x > 180 && x <= 190 && y >= 175 && y <= 240) {
                            xp = GameScene.this.find_center_x(180, 190);
                            yp = GameScene.this.find_center_y(175, 240);
                            GameScene.this.addsplit_811(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("spliteighteleven", bitString);
                            GameScene.this.send_table_bit("spliteighteleven", xp + "," + yp);
                        } else if (x > 225 && x <= 235 && y >= 175 && y <= 240) {
                            xp = GameScene.this.find_center_x(225, 235);
                            yp = GameScene.this.find_center_y(175, 240);
                            GameScene.this.addsplit_1114(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splitelevenfourteen", bitString);
                            GameScene.this.send_table_bit("splitelevenfourteen", xp + "," + yp);
                        } else if (x > 270 && x <= 280 && y >= 175 && y <= 240) {
                            xp = GameScene.this.find_center_x(270, 280);
                            yp = GameScene.this.find_center_y(175, 240);
                            GameScene.this.addsplit_1417(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splitfourteenseventeen", bitString);
                            GameScene.this.send_table_bit("splitfourteenseventeen", xp + "," + yp);
                        } else if (x > 315 && x <= 325 && y >= 175 && y <= 240) {
                            xp = GameScene.this.find_center_x(315, 325);
                            yp = GameScene.this.find_center_y(175, 240);
                            GameScene.this.addsplit_1720(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splitseventeentwenty", bitString);
                            GameScene.this.send_table_bit("splitseventeentwenty", xp + "," + yp);
                        } else if (x > 360 && x <= 370 && y >= 175 && y <= 240) {
                            xp = GameScene.this.find_center_x(369, 370);
                            yp = GameScene.this.find_center_y(175, 240);
                            GameScene.this.addsplit_2023(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splittwentytwentythree", bitString);
                            GameScene.this.send_table_bit("splittwentytwentythree", xp + "," + yp);
                        } else if (x > 405 && x <= 415 && y >= 175 && y <= 240) {
                            xp = GameScene.this.find_center_x(405, 415);
                            yp = GameScene.this.find_center_y(175, 240);
                            GameScene.this.addsplit_2326(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splittwentythreetwentysix", bitString);
                            GameScene.this.send_table_bit("splittwentythreetwentysix", xp + "," + yp);
                        } else if (x > 450 && x <= 460 && y >= 175 && y <= 240) {
                            xp = GameScene.this.find_center_x(450, 460);
                            yp = GameScene.this.find_center_y(175, 240);
                            GameScene.this.addsplit_2629(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splittwentysixtwentynine", bitString);
                            GameScene.this.send_table_bit("splittwentysixtwentynine", xp + "," + yp);
                        } else if (x > 495 && x <= 505 && y >= 175 && y <= 240) {
                            xp = GameScene.this.find_center_x(495, 505);
                            yp = GameScene.this.find_center_y(175, 240);
                            GameScene.this.addsplit_2932(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splittwentyninethirtytwo", bitString);
                            GameScene.this.send_table_bit("splittwentyninethirtytwo", xp + "," + yp);
                        } else if (x > 540 && x <= 550 && y >= 175 && y <= 240) {
                            xp = GameScene.this.find_center_x(540, 550);
                            yp = GameScene.this.find_center_y(175, 240);
                            GameScene.this.addsplit_3235(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splitthirtytwothirtysix", bitString);
                            GameScene.this.send_table_bit("splitthirtytwothirtysix", xp + "," + yp);
                        } else if (x > 90 && x <= 100 && y >= 85 && y <= 150) {
                            xp = GameScene.this.find_center_x(90, 100);
                            yp = GameScene.this.find_center_y(85, 150);
                            GameScene.this.addsplit_14(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splitonefour", bitString);
                            GameScene.this.send_table_bit("splitonefour", xp + "," + yp);
                        } else if (x > 135 && x <= 145 && y >= 85 && y <= 150) {
                            xp = GameScene.this.find_center_x(135, 145);
                            yp = GameScene.this.find_center_y(85, 150);
                            GameScene.this.addsplit_47(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splitfourseven", bitString);
                            GameScene.this.send_table_bit("splitfourseven", xp + "," + yp);
                        } else if (x > 180 && x <= 190 && y >= 85 && y <= 150) {
                            xp = GameScene.this.find_center_x(180, 190);
                            yp = GameScene.this.find_center_y(85, 150);
                            GameScene.this.addsplit_710(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splitseventen", bitString);
                            GameScene.this.send_table_bit("splitseventen", xp + "," + yp);
                        } else if (x > 225 && x <= 235 && y >= 85 && y <= 150) {
                            xp = GameScene.this.find_center_x(225, 235);
                            yp = GameScene.this.find_center_y(85, 150);
                            GameScene.this.addsplit_1013(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splittenthirteen", bitString);
                            GameScene.this.send_table_bit("splittenthirteen", xp + "," + yp);
                        } else if (x > 270 && x <= 280 && y >= 85 && y <= 150) {
                            xp = GameScene.this.find_center_x(270, 280);
                            yp = GameScene.this.find_center_y(85, 150);
                            GameScene.this.addsplit_1316(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splitthirteensixteen", bitString);
                            GameScene.this.send_table_bit("splitthirteensixteen", xp + "," + yp);
                        } else if (x > 315 && x <= 325 && y >= 85 && y <= 150) {
                            xp = GameScene.this.find_center_x(315, 325);
                            yp = GameScene.this.find_center_y(85, 150);
                            GameScene.this.addsplit_1619(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splitsixteennineteen", bitString);
                            GameScene.this.send_table_bit("splitsixteennineteen", xp + "," + yp);
                        } else if (x > 360 && x <= 370 && y >= 85 && y <= 150) {
                            xp = GameScene.this.find_center_x(369, 370);
                            yp = GameScene.this.find_center_y(85, 150);
                            GameScene.this.addsplit_1922(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splitnineteentwentytwo", bitString);
                            GameScene.this.send_table_bit("splitnineteentwentytwo", xp + "," + yp);
                        } else if (x > 405 && x <= 415 && y >= 85 && y <= 150) {
                            xp = GameScene.this.find_center_x(405, 415);
                            yp = GameScene.this.find_center_y(85, 150);
                            GameScene.this.addsplit_2225(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splittwentytwotwentyfive", bitString);
                            GameScene.this.send_table_bit("splittwentytwotwentyfive", xp + "," + yp);
                        } else if (x > 450 && x <= 460 && y >= 85 && y <= 150) {
                            xp = GameScene.this.find_center_x(450, 460);
                            yp = GameScene.this.find_center_y(85, 150);
                            GameScene.this.addsplit_2528(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splittwentyfivetwentyeight", bitString);
                            GameScene.this.send_table_bit("splittwentyfivetwentyeight", xp + "," + yp);
                        } else if (x > 495 && x <= 505 && y >= 85 && y <= 150) {
                            xp = GameScene.this.find_center_x(495, 505);
                            yp = GameScene.this.find_center_y(85, 150);
                            GameScene.this.addsplit_2831(xp, yp, bitString);
                            GameScene.this.add_total_bit(bitString);
                            GameScene.this.storeData("splittwentyeightthirtyone", bitString);
                            send_table_bit("splittwentyeightthirtyone", xp + "," + yp);
                        } else if (x > 540 && x <= 550 && y >= 85 && y <= 150) {
                            find_center_x(540, 550);
                            find_center_y(85, 150);
                            addsplit_3134(xp, yp, bitString);
                            add_total_bit(bitString);
                            storeData("splitthirtyonethirtyfour", bitString);
                            send_table_bit("splitthirtyonethirtyfour", xp + "," + yp);
                        }
                    } else {
                      GameScene.this.runOnUiThread(new Runnable() {
                          @Override
                          public void run() {
                              AlertDialog.Builder builder = new AlertDialog.Builder(GameScene.this);
                              builder.setMessage("select chips.......");
                              builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                  @Override
                                  public void onClick(DialogInterface dialogInterface, int i) {
                                      dialogInterface.dismiss();
                                  }
                              });
                              builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                  @Override
                                  public void onClick(DialogInterface dialogInterface, int i) {
                                      dialogInterface.dismiss();
                                  }
                              });
                              builder.create().show();
                          }
                      });
                    }
                }
                return true;
            }
        };
        float winxp = this.tableTextureRegion.getWidth() / 2.0f;
        float winyp = this.tableTextureRegion.getHeight() / 2.0f;
        this.winSprite = new Sprite(winxp, winyp, this.winRegion, this.mEngine.getVertexBufferObjectManager());
        this.loseSprite = new Sprite(winxp, winyp, this.loseRegion, this.mEngine.getVertexBufferObjectManager());
        this.mScene.registerTouchArea(this.tableSprite);
        this.selectedNumberSprite = new Sprite(this.selectedNumberTexture.getWidth() / 2.0f, numberypos, this.selectedNumberTexture, this.mEngine.getVertexBufferObjectManager());
        this.tableScoreContainer = new Entity();
        float widthTS = 800.0f - (this.tableTextureRegion.getWidth() + 73.0f);
        float heightTS = this.centerContainer.getHeight();
        this.tableScoreContainer.setWidth(711.0f);
        this.tableScoreContainer.setHeight(heightTS);
        this.tableScoreContainer.setColor(Color.RED);
        this.tableScoreContainer.setPosition((this.tableScoreContainer.getWidth() / 2.0f) + (800.0f - this.tableScoreContainer.getWidth()), this.tableScoreContainer.getHeight() / 2.0f);
        this.tableScoreContainer.setAlpha(0.5f);
        this.tableScoreContainer.attachChild(this.selectedNumberSprite);
        this.tableScoreContainer.attachChild(this.tableSprite);
        this.centerContainerXpos = this.centerContainer.getWidth() / 2.0f;
        this.centerContainerypos = (this.centerContainer.getHeight() / 2.0f) + 80.0f;
        this.centerContainer.setPosition(this.centerContainerXpos, this.centerContainerypos);
        this.centerContainer.attachChild(this.tableScoreContainer);
        this.luckyNumber = new Result(this.ballSprite);
        mScene.attachChild(centerContainer);
            //============================
        //end of adding center components
        //============================


        //==============================
        //game thread starts here
        //==============================

        this.scene_updater = new TimerHandler(1.0f, true, new ITimerCallback() {

            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {
                if (!is_connected()){
                    GameScene.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AlertDialog.Builder builder = new AlertDialog.Builder(GameScene.this);
                            builder.setMessage("Your Connection becomes slow.we are going to close roulette6");
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            builder.create().show();
                        }
                    });
                }else {
                    timeHandler();
                }

            }
        });
        //==================================
        //end of game updater
        //==================================


        mScene.registerUpdateHandler(scene_updater);
        this.mScene.setBackground(new SpriteBackground(new Sprite(this.region.getWidth() / 2.0f, this.region.getHeight() / 2.0f, this.region, getVertexBufferObjectManager())));
        return this.mScene;
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

    public void show_error(final String error_message) {
        runOnUiThread(new Runnable() {

            class C02931 implements View.OnClickListener {
                C02931() {
                }

                public void onClick(View view) {
                    GameScene.this.dialog.dismiss();
                }
            }

            public void run() {
                dialog = new Dialog(GameScene.this);
                dialog.requestWindowFeature(1);
                dialog.setContentView(R.layout.custom_dialog);
                ((TextView) GameScene.this.dialog.findViewById(R.id.dialogInfo)).setText(error_message);
                ((Button) GameScene.this.dialog.findViewById(R.id.exitButton)).setOnClickListener(new C02931());
                GameScene.this.dialog.show();
            }
        });
    }

    public void mute_bg_music() {
       if (this.game_music.isPlaying()) {
            this.game_music.pause();
        }
    }

    public void timeHandler(){

        class C07481 implements IEntityModifier.IEntityModifierListener {
            C07481() {
            }

            public void onModifierStarted(IModifier<IEntity> iModifier, IEntity pItem) {
                GameScene.this.isMoveInside = true;
            }

            public void onModifierFinished(IModifier<IEntity> iModifier, IEntity pItem) {
                GameScene.this.isMoveInside = false;
                GameScene.this.timer = 1;
                GameScene.this.findLuckyNumber(GameScene.this.gameStarter, GameScene.this.player2);
            }
        }
        Retrofit retrofitt=getAPIUser();
        UsersClient clientt=retrofitt.create(UsersClient.class);
        Call<ResponseMessage> callt=clientt.get_timer();
        callt.enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                timer_exact_value =Integer.parseInt(response.body().getStatus());
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {

            }
        });
        if (timer_exact_value<0){
        }else{
            timer_exact_value = timer_exact_value - 1;
            time_value.setText("" + timer_exact_value);
            if (!isMoveInside) {
                float ballnewxp = ballSprite.getX() - 20.0f;
                float ballnewyp = GameScene.this.ballSprite.getY() - 20.0f;
                GameScene.this.isMoveInside = true;
            }
            if (GameScene.this.timer_exact_value == 15) {
                GameScene.this.startMovingTable_wheel();
                //note gate player
                // new GameScene.Get_Player().execute(new String[0]);
                Retrofit retrofit = getAPIUser();
                UsersClient client = retrofit.create(UsersClient.class);
                Call<List<Player>> call = client.getPlayer(token);
                call.enqueue(new Callback<List<Player>>() {
                    @Override
                    public void onResponse(Call<List<Player>> call, final Response<List<Player>> response) {
                        player = Integer.valueOf(response.body().get(0).getGame_player());
                        player2 = Integer.valueOf(response.body().get(0).getGame_player2());
                    }

                    @Override
                    public void onFailure(Call<List<Player>> call, Throwable t) {

                    }
                });
                GameScene.this.gameStarter = GameScene.this.player;
                GameScene.this.rotate_ball(GameScene.this.gameStarter);
                GameScene.this.wheel_spin_sound.play();
                GameScene.this.topSprite.detachChild(GameScene.this.nomorebitEntity);
                if (GameScene.this.isMoveInside) {
                    GameScene.this.timer = GameScene.this.timer + 1;
                    GameScene.this.wheelSprite.registerEntityModifier(new LoopEntityModifier(new RotationModifier(5.0f, 0.0f, 360.0f), 1, new C07481()));
                }
            }
            if (GameScene.this.timer_exact_value == 5) {
                GameScene.this.path = new PathModifier.Path(2).to(GameScene.this.centerContainer.getX(), GameScene.this.centerContainerypos).to(GameScene.this.centerContainerXpos, GameScene.this.centerContainerypos);
                GameScene.this.modifier = new PathModifier(2.0f, GameScene.this.path);
                GameScene.this.modifier.setPathModifierListener(new PathModifier.IPathModifierListener() {
                    @Override
                    public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {

                    }

                    @Override
                    public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {

                    }

                    @Override
                    public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {


                    }

                    @Override
                    public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                        Map<String, ?> alldata = GameScene.this.dataholder.getAll();
                        Log.d("final winner ", finalWinner);
                        Log.d("shared ", alldata.toString());
                        for (Map.Entry<String, ?> entery : alldata.entrySet()) {
                            String numbers = (String) entery.getKey();
                            Log.d("my value values ", numbers);
                            if (!Pattern.compile("([0-9])").matcher(numbers).find()) {
                                Log.d("my value values ", numbers);
                                switch (numbers) {
                                    case "splitninetwelve":
                                        if (!checknumberexistency("9,12", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "eleventhlinetwo":
                                        if (!checknumberexistency("3,6,2,5", finalWinner)) {
                                            display_win(numbers, "8to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "8to1", true);
                                            break;
                                        }
                                    case "splittwelvefifteen":
                                        if (!checknumberexistency("7,10", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "splittwentysixtwentynine":
                                        if (!checknumberexistency("26,29", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "cornereleventh":
                                        if (!checknumberexistency("33,36,32,3", finalWinner)) {
                                            display_win(numbers, "8to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "8to1", true);
                                            break;
                                        }
                                    case "splitfiveeight":
                                        if (!checknumberexistency("5,8", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "tenthlinetwo":
                                        if (!checknumberexistency("30,33,29,32,28,31", finalWinner)) {
                                            display_win(numbers, "5to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "5to1", true);
                                            break;
                                        }
                                    case "fourthlinetwo":
                                        if (!checknumberexistency("12,15,11,14,10,13", finalWinner)) {
                                            display_win(numbers, "5to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "5to1", true);
                                            break;
                                        }
                                    case "splittwentyfourtwentyseven":
                                        if (!checknumberexistency("24,27", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "twotoonebelow":
                                        if (!checknumberexistency("1,4,7,10,13,16,19,22,25,28,31,34", finalWinner)) {
                                            display_win(numbers, "2to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "2to1", true);
                                            break;
                                        }
                                    case "splittwentytwentythree":
                                        if (!checknumberexistency("20,23", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "splittwentytwotwentyfive":
                                        if (!checknumberexistency("22,25", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "cornerone":
                                        if (!checknumberexistency("3,6,2,5", finalWinner)) {
                                            display_win(numbers, "8to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "8to1", true);
                                            break;
                                        }
                                    case "cornertwo":
                                        if (!checknumberexistency("6,9,5,8", finalWinner)) {
                                            display_win(numbers, "8to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "8to1", true);
                                            break;
                                        }
                                    case "splitthirtytwothirtyfive":
                                        if (!checknumberexistency("32,35", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "cornerfourteen":
                                        if (!checknumberexistency("8,11,7,10", finalWinner)) {
                                            display_win(numbers, "8to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "8to1", true);
                                            break;
                                        }
                                    case "spliteighteleven":
                                        if (!checknumberexistency("8,11", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "ninthlinetwo":
                                        if (!checknumberexistency("27,30,26,29,25,28", finalWinner)) {
                                            display_win(numbers, "5to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "5to1", true);
                                            break;
                                        }
                                    case "thirdlinetwo":
                                        if (!checknumberexistency("9,12,8,11,7,10", finalWinner)) {
                                            display_win(numbers, "5to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "5to1", true);
                                            break;
                                        }
                                    case "cornerfifth":
                                        if (!checknumberexistency("15,18,14,17", finalWinner)) {
                                            display_win(numbers, "8to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "8to1", true);
                                            break;
                                        }
                                    case "cornerninth":
                                        if (!checknumberexistency("27,30,26,29", finalWinner)) {
                                            display_win(numbers, "8to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "8to1", true);
                                            break;
                                        }
                                    case "cornersixth":
                                        if (!checknumberexistency("18,21,17,20", finalWinner)) {
                                            display_win(numbers, "8to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "8to1", true);
                                            break;
                                        }
                                    case "cornertenth":
                                        if (!checknumberexistency("30,33,29,32", finalWinner)) {
                                            display_win(numbers, "8to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "8to1", true);
                                            break;
                                        }
                                    case "cornerthree":
                                        if (!checknumberexistency("9,12,8,11", finalWinner)) {
                                            display_win(numbers, "8to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "8to1", true);
                                            break;
                                        }
                                    case "eigthlinetwo":
                                        if (!checknumberexistency("24,27,23,26,22,25", finalWinner)) {
                                            display_win(numbers, "5to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "5to1", true);
                                            break;
                                        }
                                    case "splittwentyseventhirty":
                                        if (!checknumberexistency("27,30", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "cornertwentyone":
                                        if (!checknumberexistency("29,32,28,31", finalWinner)) {
                                            display_win(numbers, "8to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "8to1", true);
                                            break;
                                        }
                                    case "cornertwentytwo":
                                        if (!checknumberexistency("32,35,31,34", finalWinner)) {
                                            display_win(numbers, "8to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "8to1", true);
                                            break;
                                        }
                                    case "cornerthirteen":
                                        if (!checknumberexistency("5,8,4,7", finalWinner)) {
                                            display_win(numbers, "8to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "8to1", true);
                                            break;
                                        }
                                    case "splittwentyfivetwentyeight":
                                        if (!checknumberexistency("25,28", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "fifthlinetwo":
                                        if (!checknumberexistency("15,18,14,17,13,16", finalWinner)) {
                                            display_win(numbers, "5to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "5to1", true);
                                            break;
                                        }
                                    case "splitfourseven":
                                        if (!checknumberexistency("4,7", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "splitnineteentwentytwo":
                                        if (!checknumberexistency("19,22", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "splittwentyonetwentyfour":
                                        if (!checknumberexistency("21,24", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "splitthirtyonethirtyfour":
                                        if (!checknumberexistency("31,34", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "cornereighteen":
                                        if (!checknumberexistency("20,23,19,22", finalWinner)) {
                                            display_win(numbers, "8to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "8to1", true);
                                            break;
                                        }
                                    case "low":
                                        if (!checknumberexistency("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18", finalWinner)) {
                                            display_win(numbers, "1to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "1to1", true);
                                            break;
                                        }
                                    case "odd":
                                        if (!oddeven(Integer.parseInt(finalWinner))) {
                                            display_win(numbers, "1to1", true);
                                            break;
                                        } else {
                                            display_win(numbers, "1to1", false);
                                            break;
                                        }
                                    case "red":
                                        if (!checknumberexistency("3,9,12,18,21,27,30,36,5,14,23,32,1,7,16,19,25,34", finalWinner)) {
                                            display_win(numbers, "1to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "1to1", true);
                                            break;
                                        }
                                    case "even":
                                        if (!oddeven(Integer.parseInt(finalWinner))) {
                                            display_win(numbers, "1to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "1to1", true);
                                            break;
                                        }
                                    case "high":
                                        if (!checknumberexistency("19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36", finalWinner)) {
                                            display_win(numbers, "1to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "1to1", true);
                                            break;
                                        }
                                    case "splitthirtythirtythree":
                                        if (!checknumberexistency("30,33", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "black":
                                        if (!checknumberexistency("6,15,24,33,2,8,11,17,20,26,29,35,4,10,13,22,28,31", finalWinner)) {
                                            display_win(numbers, "1to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "1to1", true);
                                            break;
                                        }
                                    case "splitsixnine":
                                        if (!checknumberexistency("6,9", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "splitthreesix":
                                        if (!checknumberexistency("3,6", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "splitseventen":
                                        if (!checknumberexistency("7,10", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "cornerninteen":
                                        if (!checknumberexistency("23,26,22,25", finalWinner)) {
                                            display_win(numbers, "8to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "8to1", true);
                                            break;
                                        }
                                    case "cornerseventh":
                                        if (!checknumberexistency("21,24,20,23", finalWinner)) {
                                            display_win(numbers, "8to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "8to1", true);
                                            break;
                                        }
                                    case "twotoonetop":
                                        if (!checknumberexistency("3,6,9,12,15,18,21,24,27,30,33,36", finalWinner)) {
                                            display_win(numbers, "2to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "2to1", true);
                                            break;
                                        }
                                    case "splittenthirteen":
                                        if (!checknumberexistency("10,13", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "cornersixteen":
                                        if (!checknumberexistency("14,17,13,16", finalWinner)) {
                                            display_win(numbers, "8to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "8to1", true);
                                            break;
                                        }
                                    case "splittwentythreetwentysix":
                                        if (!checknumberexistency("23,26", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "splitfourteenseventeen":
                                        if (!checknumberexistency("14,17", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "splittwentyninethirtytwo":
                                        if (!checknumberexistency("29,32", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "splitthirtythreethirtysix":
                                        if (!checknumberexistency("33,36", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "splitonefour":
                                        if (!checknumberexistency("1,4", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "seventhlinetwo":
                                        if (!checknumberexistency("21,24,20,23,19,22", finalWinner)) {
                                            display_win(numbers, "5to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "5to1", true);
                                            break;
                                        }
                                    case "spliteighteentwentyone":
                                        if (!checknumberexistency("18,21", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "cornereighth":
                                        if (!checknumberexistency("24,27,23,26", finalWinner)) {
                                            display_win(numbers, "8to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "8to1", true);
                                            break;
                                        }
                                    case "splitelevenfourteen":
                                        if (!checknumberexistency("11,14", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "sixlineone":
                                        if (!checknumberexistency("3,6,2,5,1,4", finalWinner)) {
                                            display_win(numbers, "5to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "5to1", true);
                                            break;
                                        }
                                    case "sixlinetwo":
                                        if (!checknumberexistency("6,9,5,8,4,7", finalWinner)) {
                                            display_win(numbers, "5to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "5to1", true);
                                            break;
                                        }
                                    case "splittwofive":
                                        if (!checknumberexistency("2,5", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "splittwentyeightthirtyone":
                                        if (!checknumberexistency("28,31", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "cornertwenty":
                                        if (!checknumberexistency("26,29,25,28", finalWinner)) {
                                            display_win(numbers, "8to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "8to1", true);
                                            break;
                                        }
                                    case "cornertwelvth":
                                        if (!checknumberexistency("2,5,1,4", finalWinner)) {
                                            display_win(numbers, "8to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "8to1", true);
                                            break;
                                        }
                                    case "splitseventeentwenty":
                                        if (!checknumberexistency("17,20", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "cornerseventeen":
                                        if (!checknumberexistency("17,20,16,19", finalWinner)) {
                                            display_win(numbers, "8to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "8to1", true);
                                            break;
                                        }
                                    case "cornerfifteen":
                                        if (!checknumberexistency("11,14,10,13", finalWinner)) {
                                            display_win(numbers, "8to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "8to1", true);
                                            break;
                                        }
                                    case "splitfifteeneighteen":
                                        if (!checknumberexistency("15,18", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "cornerfour":
                                        if (!checknumberexistency("12,15,11,14", finalWinner)) {
                                            display_win(numbers, "8to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "8to1", true);
                                            break;
                                        }
                                    case "splitthirteensixteen":
                                        if (!checknumberexistency("13,16", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "sixthlinetwo":
                                        if (!checknumberexistency("18,21,17,20,16,19", finalWinner)) {
                                            display_win(numbers, "5to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "5to1", true);
                                            break;
                                        }
                                    case "splitsixteennineteen":
                                        if (!checknumberexistency("16,19", finalWinner)) {
                                            display_win(numbers, "17to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "17to1", true);
                                            break;
                                        }
                                    case "twotoonemiddle":
                                        if (!checknumberexistency("2,5,8,11,14,17,20,23,26,29,32,35", finalWinner)) {
                                            display_win(numbers, "2to1", false);
                                            break;
                                        } else {
                                            display_win(numbers, "2to1", true);
                                            break;
                                        }

                                    default:
                                        break;

                                }
                            } else {
                                if (Integer.parseInt(finalWinner) == Integer.parseInt(numbers)) {
                                    display_win(numbers, "single", true);
                                } else {
                                    display_win(numbers, "single", false);
                                }
                            }

                        }
                    }
                });
                GameScene.this.is_updated.edit().putBoolean("isInserted", true).commit();
                GameScene.this.centerContainer.registerEntityModifier(GameScene.this.modifier);
                /*GameScene.this.topSprite.detachChild(GameScene.this.music_control);
                topSprite.attachChild(music_control);
                if (getMusic_current_index() == 0) {
                    bg_music.play();
                } else {
                    bg_music.pause();
                }*/
            }
            if (GameScene.this.timer_exact_value == 0) {
                GameScene.this.bitEntity.detachChild(GameScene.this.clear);
                GameScene.this.bitEntity.attachChild(GameScene.this.clear);
                GameScene.this.bottomSprite.detachChild(GameScene.this.clear_string);
                GameScene.this.bottomSprite.attachChild(GameScene.this.clear_string);
                GameScene.this.total_win = 0;
                GameScene.this.total_lose = 0;
                GameScene.this.is_playing_chips_set = false;
                GameScene.this.bitSprite.setText("00");
                GameScene.this.play_again();
                GameScene.this.win.stop();
                GameScene.this.lost.stop();
                game_started=false;
                SharedPreferences.Editor edit = GameScene.this.dataholder.edit();
                edit.clear();
                edit.commit();
                GameScene.this.winSprite.detachChildren();
                GameScene.this.wheelSprite.detachChild(GameScene.this.ballSprite);
                GameScene.this.wheelSprite.detachChild(GameScene.this.winningNumber);
                GameScene.this.index = 0;
                lose_index = 0;
                GameScene.this.is_updated.edit().putBoolean("isInserted", false).commit();
                GameScene.this.update_index = 0;
                GameScene.this.timer_exact_value = 60;
                GameScene.this.table_is_added = false;
                GameScene.this.is_less_than_17 = false;
                if (((double) GameScene.this.yp) == 20.0d) {
                    GameScene.this.selectedNumberSprite.detachChildren();
                    GameScene.this.number_yp_position = 20;
                }
            }
            if (GameScene.this.timer_exact_value == 17) {
                GameScene.this.no_more_bit_sound.play();
                GameScene.this.mScene.unregisterTouchArea(GameScene.this.five);
                GameScene.this.mScene.unregisterTouchArea(GameScene.this.ten);
                GameScene.this.mScene.unregisterTouchArea(GameScene.this.fifty);
                GameScene.this.mScene.unregisterTouchArea(GameScene.this.hundred);
                GameScene.this.mScene.unregisterTouchArea(GameScene.this.fiveHundred);
                GameScene.this.bottomSprite.detachChild(GameScene.this.entity);
                GameScene.this.bitEntity.detachChild(GameScene.this.clear);
                GameScene.this.bottomSprite.detachChild(GameScene.this.clear_string);
                GameScene.this.start_no_more_bit();
                if (GameScene.this.bitSprite.getText().toString().charAt(0) != '0') {

                    Retrofit retrofit = getAPIUser();
                    UsersClient client = retrofit.create(UsersClient.class);
                    Commission commission = new Commission(users_intent.getStringExtra("referral_code"), users_intent.getStringExtra("user_name"), GameScene.this.bitSprite.getText().toString());
                    Call<ResponseMessage> call = client.add_commission(token, commission);
                    call.enqueue(new Callback<ResponseMessage>() {
                        @Override
                        public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {

                        }

                        @Override
                        public void onFailure(Call<ResponseMessage> call, Throwable t) {

                        }
                    });
                    //note add commission here
                    //new GameScene.Add_commision().execute(new String[]{GameScene.this.gamers.getReferal_code(), GameScene.this.gamers.getUser_name(), GameScene.this.bitSprite.getText().toString()});
                }
            }
            if (GameScene.this.timer_exact_value >= 17 || GameScene.this.timer_exact_value <= 0) {
                GameScene.this.istableToucehEnable = true;
            } else {
                GameScene.this.istableToucehEnable = false;
            }
            if (GameScene.this.get_total_credit() == 0) {
                GameScene.this.is_bit_value_correct = false;
            }
            if (GameScene.this.timer_exact_value == 58) {
                GameScene.this.place_your_bit.play();
                GameScene.this.start_place_bit();
                GameScene.this.win_updated = false;
                GameScene.this.dataholder = GameScene.this.getSharedPreferences("table_number", 0);
            }
            if (GameScene.this.timer_exact_value == 25) {
                GameScene.this.last_call.play();
                GameScene.this.dataholder.getAll();
            }
            if (GameScene.this.timer_exact_value == 54) {
                GameScene.this.topSprite.detachChild(GameScene.this.placebitentity);
                GameScene.this.topSprite.detachChild(GameScene.this.placebitentity);


            }
            if (timer_exact_value==2){
                Retrofit retrofit= getAPIUser();
                UsersClient client= retrofit.create(UsersClient.class);
                int final_total_Credits = Integer.parseInt(this.total_credit.getText()) + total_win;
                Call<List<Amounts>> call=client.update_account(token,String.valueOf(final_total_Credits));
                call.enqueue(new Callback<List<Amounts>>() {
                    @Override
                    public void onResponse(Call<List<Amounts>> call, Response<List<Amounts>> response) {
                        //Toast.makeText(getApplicationContext(),response.body().get(0).getAmount(),Toast.LENGTH_LONG).show();
                        total_credit.setText(response.body().get(0).getAmount());
                        win_updated = true;
                        total_win=0;
                    }

                    @Override
                    public void onFailure(Call<List<Amounts>> call, Throwable t) {

                    }
                });
            }
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
    public boolean oddeven(int number) {
        if (number % 2 == 0) {
            return true;
        }
        return false;
    }
    public boolean checknumberexistency(String data, String number) {
        List<String> compare = new ArrayList();
        for (String values : data.split(",")) {
            compare.add(values);
        }
        if (compare.contains(number)) {
            return true;
        }
        return false;
    }
    public void display_win(String tableNo, String bitType, boolean iswin) {
        int money_amount;
        if (!iswin) {
            money_amount = Integer.parseInt(this.dataholder.getString(tableNo, ""));
            switch (bitType){
                case "single":
                    this.total_lose += (money_amount * 35) + money_amount;
                    break;
                case "1to1":
                    this.total_lose += (money_amount * 1) + money_amount;
                    break;
                case "2to1":
                    this.total_lose += (money_amount * 2) + money_amount;
                    break;
                case "5to1":
                    this.total_lose += (money_amount * 5) + money_amount;
                    break;
                case "8to1":
                    this.total_lose += (money_amount * 8) + money_amount;
                    break;
                case "17to1":
                    this.total_lose += (money_amount * 17) + money_amount;
                    break;
                default:
                    break;
            }
        }else {
            money_amount = Integer.parseInt(this.dataholder.getString(tableNo, ""));
            switch (bitType){
                case "single":
                    this.total_win += (money_amount * 35) + money_amount;
                    break;
                case "1to1":
                    this.total_win += (money_amount * 1) + money_amount;
                    break;
                case "2to1":
                    this.total_win += (money_amount * 2) + money_amount;
                    break;
                case "5to1":
                    this.total_win += (money_amount * 5) + money_amount;
                    break;
                case "8to1":
                    this.total_win += (money_amount * 8) + money_amount;
                    break;
                case "17to1":
                    this.total_win += (money_amount * 17) + money_amount;
                    break;
                default:
                    break;
            }

        }

        if (this.total_win <= 0) {
            display_lose();
        } else if (this.total_win > 0) {
            this.winSprite.detachChild(this.dollar);
            display_final_winning_balance(this.total_win);
        }
          Log.d("total win ",""+total_win);
    }

    public void display_final_winning_balance(int total_wins) {
        this.tableSprite.detachChildren();
        this.win.play();
        this.dollar.setText(Integer.toString(total_wins) + " chips");
        this.winSprite.attachChild(this.dollar);
        this.tableSprite.attachChild(this.winSprite);
        this.bitSprite.setText("00");
        int final_total_Credits = Integer.parseInt(this.total_credit.getText()) + total_wins;
        this.index++;

        /*Log.d("index ",""+index);
        ArrayList list = new ArrayList();
        ArrayList update_data = new ArrayList();
        list.add(final_total_Credits + "." + this.index);
        StringBuilder sb = new StringBuilder();
        StringBuilder up_sb = new StringBuilder();
        SharedPreferences.Editor editor = this.total_amount_shared.edit();
        editor.putString("total_credit", Integer.toString(final_total_Credits));
        editor.apply();
        String first_result = "";
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i) + ",");
        }
        if (sb.substring(0, sb.indexOf(",")).contains(Integer.toString(this.index))) {
            first_result = sb.substring(0, sb.indexOf(","));
            String player_gain = first_result.substring(0, first_result.indexOf("."));
            if (!win_updated){
                Retrofit retrofit= getAPIUser();
                UsersClient client= retrofit.create(UsersClient.class);
                Amounts amounts=new Amounts(player_gain);
                Call<List<Amounts>> call=client.update_account(token,amounts);
                call.enqueue(new Callback<List<Amounts>>() {
                    @Override
                    public void onResponse(Call<List<Amounts>> call, Response<List<Amounts>> response) {
                        //Toast.makeText(getApplicationContext(),response.body().get(0).getAmount(),Toast.LENGTH_LONG).show();
                        total_credit.setText(response.body().get(0).getAmount());
                        win_updated = true;
                    }

                    @Override
                    public void onFailure(Call<List<Amounts>> call, Throwable t) {

                    }
                });
            }
            //new GameScene().Update_account().execute(new String[]{this.email, player_gain});
        }*/
    }
    public void display_lose() {
        this.lost.play();
        this.tableSprite.detachChildren();
       this.tableSprite.attachChild(this.loseSprite);
        int total_lose = Integer.parseInt(this.bitSprite.getText());
        lose_index++;
        Retrofit retrofit=getAPIUser();
        UsersClient client= retrofit.create(UsersClient.class);
        UpdateAccount account=new UpdateAccount(lose_index,total_lose);
        Call<List<Amounts>> call= client.update_lose(token,account);
        call.enqueue(new Callback<List<Amounts>>() {
            @Override
            public void onResponse(Call<List<Amounts>> call, Response<List<Amounts>> response) {
                total_credit.setText(response.body().get(0).getAmount());
                GameScene.this.bitSprite.setText("00");
            }

            @Override
            public void onFailure(Call<List<Amounts>> call, Throwable t) {

            }
        });

    }

    public void startMovingTable_wheel() {
        this.path = new PathModifier.Path(2).to(this.centerContainerXpos, this.centerContainerypos).to(this.centerContainerXpos + 300.0f, this.centerContainerypos);
        this.modifier = new PathModifier(2.0f, this.path);
        this.centerContainer.registerEntityModifier(this.modifier);
    }

    public void rotate_ball(int result) {
        switch (result) {
            case 0:
                attach_ball(find_center_x(217, 238), find_center_y(133, 116));
                start_from_zero();
                return;
            case 1:
                attach_ball(find_center_x(57, 43), find_center_y(209, 196));
                start_from_1();
                return;
            case 2:
                attach_ball(find_center_x(186, 176), find_center_y(45, 37));
                start_from_2();
                return;
            case 3:
                attach_ball(find_center_x(230, 211), find_center_y(162, 150));
                start_from_3();
                return;
            case 4:
                attach_ball(find_center_x(202, 214), find_center_y(68, 58));
                start_from_4();
                return;
            case 5:
                attach_ball(find_center_x(27, 8), find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY));
                start_from_5();
                return;
            case 6:
                attach_ball(find_center_x(115, 110), find_center_y(28, 27));
                start_from_6();
                return;
            case 7:
                attach_ball(find_center_x(166, 178), find_center_y(211, 205));
                start_from_7();
                return;
            case 8:
                attach_ball(find_center_x(37, 18), find_center_y(91, 79));
                start_from_8();
                return;
            case 9:
                attach_ball(find_center_x(100, 116), find_center_y(240, 219));
                start_from_9();
                return;
            case 10:
                attach_ball(find_center_x(26, 7), find_center_y(113, 121));
                start_from_10();
                return;
            case 11:
                attach_ball(find_center_x(54, 43), find_center_y(53, 63));
                start_from_11();
                return;
            case 12:
                int centerx_12 = find_center_x(192, 210);
                int centery_12 = find_center_y(201, 178);
                attach_ball(centerx_12, centerx_12);
                start_from_12();
                return;
            case 13:
                attach_ball(find_center_x(81, 74), find_center_y(35, 38));
                start_from_13();
                return;
            case 14:
                attach_ball(find_center_x(49, 61), find_center_y(218, 205));
                start_from_14();
                return;
            case 15:
                attach_ball(find_center_x(213, 233), find_center_y(98, 85));
                start_from_15();
                return;
            case 16:
                attach_ball(find_center_x(21, 35), find_center_y(178, 163));
                start_from_16();
                return;
            case 17:
                attach_ball(find_center_x(136, 149), find_center_y(31, 13));
                start_from_17();
                return;
            case 18:
                attach_ball(find_center_x(139, 154), find_center_y(236, 219));
                start_from_18();
                return;
            case 19:
                attach_ball(find_center_x(208, 222), find_center_y(83, 68));
                start_from_19();
                return;
            case 20:
                attach_ball(find_center_x(65, 79), find_center_y(224, 212));
                start_from_20();
                return;
            case 21:
                attach_ball(find_center_x(190, 201), find_center_y(55, 45));
                start_from_21();
                return;
            case 22:
                attach_ball(find_center_x(120, 133), find_center_y(240, 223));
                start_from_22();
                return;
            case 23:
                attach_ball(find_center_x(8, 29), find_center_y(104, 93));
                start_from_23();
                return;
            case 24:
                attach_ball(find_center_x(29, 10), find_center_y(157, 146));
                start_from_24();
                return;
            case 25:
                attach_ball(find_center_x(155, 166), find_center_y(29, 23));
                start_from_25();
                return;
            case 26:
                attach_ball(find_center_x(216, 233), find_center_y(147, 135));
                start_from_26();
                return;
            case 27:
                attach_ball(find_center_x(97, 84), find_center_y(32, 17));
                start_from_27();
                return;
            case 28:
                int centerx_28 = find_center_x(180, 196);
                int centery_28 = find_center_y(213, 195);
                attach_ball(centerx_28, centerx_28);
                start_from_28();
                return;
            case 29:
                attach_ball(find_center_x(152, 170), find_center_y(232, 215));
                start_from_29();
                return;
            case 30:
                attach_ball(find_center_x(45, 23), find_center_y(74, 57));
                start_from_30();
                return;
            case 31:
                attach_ball(find_center_x(81, 94), find_center_y(235, 219));
                start_from_31();
                return;
            case 32:
                attach_ball(find_center_x(217, 233), find_center_y(115, 100));
                start_from_32();
                return;
            case 33:
                attach_ball(find_center_x(43, 23), find_center_y(193, 177));
                start_from_33();
                return;
            case 34:
                attach_ball(find_center_x(119, 131), find_center_y(27, 11));
                start_from_34();
                return;
            case 35:
                attach_ball(find_center_x(206, 221), find_center_y(186, 167));
                start_from_35();
                return;
            case 36:
                attach_ball(find_center_x(49, 63), find_center_y(51, 31));
                start_from_36();
                return;
            default:
                return;
        }
    }
    public void play_again() {
        this.mScene.registerTouchArea(this.tableSprite);
        this.timer_exact_value = 0;
        this.mScene.registerTouchArea(this.five);
        this.mScene.registerTouchArea(this.ten);
        this.mScene.registerTouchArea(this.fifty);
        this.mScene.registerTouchArea(this.hundred);
        this.mScene.registerTouchArea(this.fiveHundred);
        this.tableSprite.detachChildren();
        this.bottomSprite.detachChild(this.entity);
        this.bottomSprite.attachChild(this.entity);
        this.is3625Added = false;
        this.is6958Added = false;
        this.is12811Added = false;
        this.is12151114Added = false;
        this.is121814174Added = false;
        this.is33363235Added = false;
        this.is30332932Added = false;
        this.is27302629Added = false;
        this.is24271326Added = false;
        this.is21242023Added = false;
        this.is151814174Added = false;
        this.is18211720Added = false;
        this.is11141013Added = false;
        this.is2514Added = false;
        this.is5847Added = false;
        this.is811710Added = false;
        this.is32353133Added = false;
        this.is29322831Added = false;
        this.is26292528Added = false;
        this.is23262225Added = false;
        this.is20231922Added = false;
        this.is17201619Added = false;
        this.is14171316Added = false;
        this.zeroValueAdded = false;
        this.btzeroandthree = false;
        this.btzerothreetwo = false;
        this.threeValueAdded = false;
        this.twoIsAdded = false;
        this.btzeroandtwo = false;
        this.btzerotwoandone = false;
        this.oneValueIsAdded = false;
        this.btzeronandone = false;
        this.btzerooneand18 = false;
        this.bt18and1 = false;
        this.add8 = false;
        this.eveisadded = false;
        this.isRedAdded = false;
        this.isBlackAdded = false;
        this.isOddAdded = false;
        this.is19bt36Added = false;
        this.is2to1Addedbelow = false;
        this.is2to1AddedMiddle = false;
        this.is2to1AddedTop = false;
        this.isSixAdded = false;
        this.isFiveAdded = false;
        this.isfourAdded = false;
        this.isNineAdded = false;
        this.isEightAdded = false;
        this.isSevenAdded = false;
        this.istwelveAdded = false;
        this.is10Added = false;
        this.is11Added = false;
        this.is13Added = false;
        this.is14Added = false;
        this.is15Added = false;
        this.is16Added = false;
        this.is17Added = false;
        this.is18Added = false;
        this.is19Added = false;
        this.is20Added = false;
        this.is21Added = false;
        this.is22Added = false;
        this.is23Added = false;
        this.is24Added = false;
        this.is25Added = false;
        this.is26Added = false;
        this.is27Added = false;
        this.is28Added = false;
        this.is29Added = false;
        this.is30Added = false;
        this.is31Added = false;
        this.is32Added = false;
        this.is33Added = false;
        this.is34Added = false;
        this.is35Added = false;
        this.is36Added = false;
        this.is6to5Added = false;
        this.is1bt18added = false;
        this.isfirstsixlineAdded = false;
        this.issecondsixlineAdded = false;
        this.isthirdsixlineAdded = false;
        this.isfourthsixlineAdded = false;
        this.isfifthsixlineAdded = false;
        this.issixthsixlineAdded = false;
        this.isseventhsixlineAdded = false;
        this.iseighthsixlineAdded = false;
        this.isninthsixlineAdded = false;
        this.istenthsixlineAdded = false;
        this.iseleventhsixlineAdded = false;
        this.issplit32Added = false;
        this.issplit69Added = false;
        this.issplit912Added = false;
        this.issplit3336Added = false;
        this.issplit3033Added = false;
        this.issplit1215Added = false;
        this.issplit2730Added = false;
        this.issplit2427Added = false;
        this.issplit1518Added = false;
        this.issplit1821Added = false;
        this.issplit2124Added = false;
        this.issplit2932Added = false;
        this.issplit1417Added = false;
        this.issplit25Added = false;
        this.issplit1720Added = false;
        this.issplit2023Added = false;
        this.issplit58Added = false;
        this.issplit2326Added = false;
        this.issplit2629Added = false;
        this.issplit811Added = false;
        this.issplit1114Added = false;
        this.issplit3235Added = false;
        this.issplit47Added = false;
        this.issplit710Added = false;
        this.issplit1013Added = false;
        this.issplit1316Added = false;
        this.issplit1619Added = false;
        this.issplit1922Added = false;
        this.issplit2225Added = false;
        this.issplit2528Added = false;
        this.issplit2831Added = false;
        this.issplit3134Added = false;
    }

    public void start_from_18() {
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(120, 133), (float) find_center_y(240, 223)).to((float) find_center_x(100, 116), (float) find_center_y(240, 219)).to((float) find_center_x(81, 94), (float) find_center_y(235, 219)).to((float) find_center_x(65, 79), (float) find_center_y(224, 212)).to((float) find_center_x(49, 61), (float) find_center_y(218, 205)).to((float) find_center_x(57, 43), (float) find_center_y(209, 196)).to((float) find_center_x(43, 23), (float) find_center_y(193, 177)).to((float) find_center_x(21, 35), (float) find_center_y(178, 163)).to((float) find_center_x(29, 10), (float) find_center_y(157, 146)).to((float) find_center_x(27, 8), (float) find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY)).to((float) find_center_x(26, 7), (float) find_center_y(113, 121)).to((float) find_center_x(8, 29), (float) find_center_y(104, 93)).to((float) find_center_x(37, 18), (float) find_center_y(91, 79)).to((float) find_center_x(45, 23), (float) find_center_y(74, 57)).to((float) find_center_x(54, 43), (float) find_center_y(53, 63)).to((float) find_center_x(49, 63), (float) find_center_y(51, 31)).to((float) find_center_x(81, 74), (float) find_center_y(35, 38)).to((float) find_center_x(97, 84), (float) find_center_y(32, 17)).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_18();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_zero() {
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(216, 233), (float) find_center_y(147, 135)).to((float) find_center_x(230, 211), (float) find_center_y(162, 150)).to((float) find_center_x(204, 222), (float) find_center_y(183, 169)).to((float) find_center_x(192, 210), (float) find_center_y(201, 178)).to((float) find_center_x(180, 196), (float) find_center_y(213, 195)).to((float) find_center_x(166, 178), (float) find_center_y(211, 205)).to((float) find_center_x(152, 170), (float) find_center_y(232, 215)).to((float) find_center_x(139, 154), (float) find_center_y(236, 219)).to((float) find_center_x(120, 133), (float) find_center_y(240, 223)).to((float) find_center_x(100, 116), (float) find_center_y(240, 219)).to((float) find_center_x(81, 94), (float) find_center_y(235, 219)).to((float) find_center_x(65, 79), (float) find_center_y(224, 212)).to((float) find_center_x(49, 61), (float) find_center_y(218, 205)).to((float) find_center_x(57, 43), (float) find_center_y(209, 196)).to((float) find_center_x(43, 23), (float) find_center_y(193, 177)).to((float) find_center_x(21, 35), (float) find_center_y(178, 163)).to((float) find_center_x(29, 10), (float) find_center_y(157, 146)).to((float) find_center_x(27, 8), (float) find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY)).to((float) find_center_x(26, 7), (float) find_center_y(113, 121)).to((float) find_center_x(8, 29), (float) find_center_y(104, 93)).to((float) find_center_x(37, 18), (float) find_center_y(91, 79)).to((float) find_center_x(45, 23), (float) find_center_y(74, 57)).to((float) find_center_x(54, 43), (float) find_center_y(53, 63)).to((float) find_center_x(49, 63), (float) find_center_y(51, 31)).to((float) find_center_x(81, 74), (float) find_center_y(35, 38)).to((float) find_center_x(97, 84), (float) find_center_y(32, 17)).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_zero();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_26() {
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(230, 211), (float) find_center_y(162, 150)).to((float) find_center_x(204, 222), (float) find_center_y(183, 169)).to((float) find_center_x(192, 210), (float) find_center_y(201, 178)).to((float) find_center_x(180, 196), (float) find_center_y(213, 195)).to((float) find_center_x(166, 178), (float) find_center_y(211, 205)).to((float) find_center_x(152, 170), (float) find_center_y(232, 215)).to((float) find_center_x(139, 154), (float) find_center_y(236, 219)).to((float) find_center_x(120, 133), (float) find_center_y(240, 223)).to((float) find_center_x(100, 116), (float) find_center_y(240, 219)).to((float) find_center_x(81, 94), (float) find_center_y(235, 219)).to((float) find_center_x(65, 79), (float) find_center_y(224, 212)).to((float) find_center_x(49, 61), (float) find_center_y(218, 205)).to((float) find_center_x(57, 43), (float) find_center_y(209, 196)).to((float) find_center_x(43, 23), (float) find_center_y(193, 177)).to((float) find_center_x(21, 35), (float) find_center_y(178, 163)).to((float) find_center_x(29, 10), (float) find_center_y(157, 146)).to((float) find_center_x(27, 8), (float) find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY)).to((float) find_center_x(26, 7), (float) find_center_y(113, 121)).to((float) find_center_x(8, 29), (float) find_center_y(104, 93)).to((float) find_center_x(37, 18), (float) find_center_y(91, 79)).to((float) find_center_x(45, 23), (float) find_center_y(74, 57)).to((float) find_center_x(54, 43), (float) find_center_y(53, 63)).to((float) find_center_x(49, 63), (float) find_center_y(51, 31)).to((float) find_center_x(81, 74), (float) find_center_y(35, 38)).to((float) find_center_x(97, 84), (float) find_center_y(32, 17)).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_26();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_3() {
        setWinningNumberValue(3);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(204, 222), (float) find_center_y(183, 169)).to((float) find_center_x(192, 210), (float) find_center_y(201, 178)).to((float) find_center_x(180, 196), (float) find_center_y(213, 195)).to((float) find_center_x(166, 178), (float) find_center_y(211, 205)).to((float) find_center_x(152, 170), (float) find_center_y(232, 215)).to((float) find_center_x(139, 154), (float) find_center_y(236, 219)).to((float) find_center_x(120, 133), (float) find_center_y(240, 223)).to((float) find_center_x(100, 116), (float) find_center_y(240, 219)).to((float) find_center_x(81, 94), (float) find_center_y(235, 219)).to((float) find_center_x(65, 79), (float) find_center_y(224, 212)).to((float) find_center_x(49, 61), (float) find_center_y(218, 205)).to((float) find_center_x(57, 43), (float) find_center_y(209, 196)).to((float) find_center_x(43, 23), (float) find_center_y(193, 177)).to((float) find_center_x(21, 35), (float) find_center_y(178, 163)).to((float) find_center_x(29, 10), (float) find_center_y(157, 146)).to((float) find_center_x(27, 8), (float) find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY)).to((float) find_center_x(26, 7), (float) find_center_y(113, 121)).to((float) find_center_x(8, 29), (float) find_center_y(104, 93)).to((float) find_center_x(37, 18), (float) find_center_y(91, 79)).to((float) find_center_x(45, 23), (float) find_center_y(74, 57)).to((float) find_center_x(54, 43), (float) find_center_y(53, 63)).to((float) find_center_x(49, 63), (float) find_center_y(51, 31)).to((float) find_center_x(81, 74), (float) find_center_y(35, 38)).to((float) find_center_x(97, 84), (float) find_center_y(32, 17)).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) find_center_x(230, 211), (float) find_center_y(162, 150));
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_3();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_35() {
        int centerx_35 = find_center_x(206, 221);
        int centery_35 = find_center_y(186, 167);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(192, 210), (float) find_center_y(201, 178)).to((float) find_center_x(180, 196), (float) find_center_y(213, 195)).to((float) find_center_x(166, 178), (float) find_center_y(211, 205)).to((float) find_center_x(152, 170), (float) find_center_y(232, 215)).to((float) find_center_x(139, 154), (float) find_center_y(236, 219)).to((float) find_center_x(120, 133), (float) find_center_y(240, 223)).to((float) find_center_x(100, 116), (float) find_center_y(240, 219)).to((float) find_center_x(81, 94), (float) find_center_y(235, 219)).to((float) find_center_x(65, 79), (float) find_center_y(224, 212)).to((float) find_center_x(49, 61), (float) find_center_y(218, 205)).to((float) find_center_x(57, 43), (float) find_center_y(209, 196)).to((float) find_center_x(43, 23), (float) find_center_y(193, 177)).to((float) find_center_x(21, 35), (float) find_center_y(178, 163)).to((float) find_center_x(29, 10), (float) find_center_y(157, 146)).to((float) find_center_x(27, 8), (float) find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY)).to((float) find_center_x(26, 7), (float) find_center_y(113, 121)).to((float) find_center_x(8, 29), (float) find_center_y(104, 93)).to((float) find_center_x(37, 18), (float) find_center_y(91, 79)).to((float) find_center_x(45, 23), (float) find_center_y(74, 57)).to((float) find_center_x(54, 43), (float) find_center_y(53, 63)).to((float) find_center_x(49, 63), (float) find_center_y(51, 31)).to((float) find_center_x(81, 74), (float) find_center_y(35, 38)).to((float) find_center_x(97, 84), (float) find_center_y(32, 17)).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) find_center_x(230, 211), (float) find_center_y(162, 150)).to((float) centerx_35, (float) centery_35);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_35();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_12() {
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(180, 196), (float) find_center_y(213, 195)).to((float) find_center_x(166, 178), (float) find_center_y(211, 205)).to((float) find_center_x(152, 170), (float) find_center_y(232, 215)).to((float) find_center_x(139, 154), (float) find_center_y(236, 219)).to((float) find_center_x(120, 133), (float) find_center_y(240, 223)).to((float) find_center_x(100, 116), (float) find_center_y(240, 219)).to((float) find_center_x(81, 94), (float) find_center_y(235, 219)).to((float) find_center_x(65, 79), (float) find_center_y(224, 212)).to((float) find_center_x(49, 61), (float) find_center_y(218, 205)).to((float) find_center_x(57, 43), (float) find_center_y(209, 196)).to((float) find_center_x(43, 23), (float) find_center_y(193, 177)).to((float) find_center_x(21, 35), (float) find_center_y(178, 163)).to((float) find_center_x(29, 10), (float) find_center_y(157, 146)).to((float) find_center_x(27, 8), (float) find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY)).to((float) find_center_x(26, 7), (float) find_center_y(113, 121)).to((float) find_center_x(8, 29), (float) find_center_y(104, 93)).to((float) find_center_x(37, 18), (float) find_center_y(91, 79)).to((float) find_center_x(45, 23), (float) find_center_y(74, 57)).to((float) find_center_x(54, 43), (float) find_center_y(53, 63)).to((float) find_center_x(49, 63), (float) find_center_y(51, 31)).to((float) find_center_x(81, 74), (float) find_center_y(35, 38)).to((float) find_center_x(97, 84), (float) find_center_y(32, 17)).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_12();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_28() {
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(166, 178), (float) find_center_y(211, 205)).to((float) find_center_x(152, 170), (float) find_center_y(232, 215)).to((float) find_center_x(139, 154), (float) find_center_y(236, 219)).to((float) find_center_x(120, 133), (float) find_center_y(240, 223)).to((float) find_center_x(100, 116), (float) find_center_y(240, 219)).to((float) find_center_x(81, 94), (float) find_center_y(235, 219)).to((float) find_center_x(65, 79), (float) find_center_y(224, 212)).to((float) find_center_x(49, 61), (float) find_center_y(218, 205)).to((float) find_center_x(57, 43), (float) find_center_y(209, 196)).to((float) find_center_x(43, 23), (float) find_center_y(193, 177)).to((float) find_center_x(21, 35), (float) find_center_y(178, 163)).to((float) find_center_x(29, 10), (float) find_center_y(157, 146)).to((float) find_center_x(27, 8), (float) find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY)).to((float) find_center_x(26, 7), (float) find_center_y(113, 121)).to((float) find_center_x(8, 29), (float) find_center_y(104, 93)).to((float) find_center_x(37, 18), (float) find_center_y(91, 79)).to((float) find_center_x(45, 23), (float) find_center_y(74, 57)).to((float) find_center_x(54, 43), (float) find_center_y(53, 63)).to((float) find_center_x(49, 63), (float) find_center_y(51, 31)).to((float) find_center_x(81, 74), (float) find_center_y(35, 38)).to((float) find_center_x(97, 84), (float) find_center_y(32, 17)).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_28();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_7() {
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(152, 170), (float) find_center_y(232, 215)).to((float) find_center_x(139, 154), (float) find_center_y(236, 219)).to((float) find_center_x(120, 133), (float) find_center_y(240, 223)).to((float) find_center_x(100, 116), (float) find_center_y(240, 219)).to((float) find_center_x(81, 94), (float) find_center_y(235, 219)).to((float) find_center_x(65, 79), (float) find_center_y(224, 212)).to((float) find_center_x(49, 61), (float) find_center_y(218, 205)).to((float) find_center_x(57, 43), (float) find_center_y(209, 196)).to((float) find_center_x(43, 23), (float) find_center_y(193, 177)).to((float) find_center_x(21, 35), (float) find_center_y(178, 163)).to((float) find_center_x(29, 10), (float) find_center_y(157, 146)).to((float) find_center_x(27, 8), (float) find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY)).to((float) find_center_x(26, 7), (float) find_center_y(113, 121)).to((float) find_center_x(8, 29), (float) find_center_y(104, 93)).to((float) find_center_x(37, 18), (float) find_center_y(91, 79)).to((float) find_center_x(45, 23), (float) find_center_y(74, 57)).to((float) find_center_x(54, 43), (float) find_center_y(53, 63)).to((float) find_center_x(49, 63), (float) find_center_y(51, 31)).to((float) find_center_x(81, 74), (float) find_center_y(35, 38)).to((float) find_center_x(97, 84), (float) find_center_y(32, 17)).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_7();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void findLuckyNumber(int starter, int lucky) {
        switch (lucky) {
            case 0:
                displayResult(find_center_x(198, 206), find_center_y(TransportMediator.KEYCODE_MEDIA_PAUSE, 119), 0);
                return;
            case 1:
                displayResult(find_center_x(61, 67), find_center_y(189, 175), 1);
                return;
            case 2:
                displayResult(find_center_x(160, 168), find_center_y(48, 63), 2);
                return;
            case 3:
                displayResult(find_center_x(191, 211), find_center_y(157, 143), 3);
                return;
            case 4:
                displayResult(find_center_x(181, 188), find_center_y(79, 67), 4);
                return;
            case 5:
                displayResult(find_center_x(35, 45), find_center_y(137, TransportMediator.KEYCODE_MEDIA_PAUSE), 5);
                return;
            case 6:
                displayResult(find_center_x(107, 113), find_center_y(52, 37), 6);
                return;
            case 7:
                displayResult(find_center_x(159, 168), find_center_y(202, 187), 7);
                return;
            case 8:
                displayResult(find_center_x(38, 55), find_center_y(97, 85), 8);
                return;
            case 9:
                displayResult(find_center_x(106, 116), find_center_y(213, 198), 9);
                return;
            case 10:
                displayResult(find_center_x(37, 43), find_center_y(124, 114), 10);
                return;
            case 11:
                displayResult(find_center_x(38, 55), find_center_y(71, 64), 11);
                return;
            case 12:
                displayResult(find_center_x(178, 194), find_center_y(185, 167), 12);
                return;
            case 13:
                displayResult(find_center_x(80, 88), find_center_y(63, 43), 13);
                return;
            case 14:
                displayResult(find_center_x(80, 89), find_center_y(205, 192), 14);
                return;
            case 15:
                displayResult(find_center_x(196, 201), find_center_y(90, 101), 15);
                return;
            case 16:
                displayResult(find_center_x(41, 48), find_center_y(166, 153), 16);
                return;
            case 17:
                displayResult(find_center_x(133, 143), find_center_y(52, 38), 17);
                return;
            case 18:
                displayResult(find_center_x(136, 140), find_center_y(212, 198), 18);
                return;
            case 19:
                displayResult(find_center_x(190, 195), find_center_y(90, 78), 19);
                return;
            case 20:
                displayResult(find_center_x(68, 77), find_center_y(198, 182), 20);
                return;
            case 21:
                displayResult(find_center_x(171, 178), find_center_y(55, 69), 21);
                return;
            case 22:
                displayResult(find_center_x(125, TransportMediator.KEYCODE_MEDIA_PAUSE), find_center_y(201, 215), 22);
                return;
            case 23:
                displayResult(find_center_x(38, 48), find_center_y(107, 100), 23);
                return;
            case 24:
                displayResult(find_center_x(38, 47), find_center_y(152, 141), 24);
                return;
            case 25:
                displayResult(find_center_x(147, 156), find_center_y(43, 55), 25);
                return;
            case 26:
                displayResult(find_center_x(195, 211), find_center_y(143, 133), 26);
                return;
            case 27:
                displayResult(find_center_x(95, 100), find_center_y(54, 38), 27);
                return;
            case 28:
                displayResult(find_center_x(169, 180), find_center_y(185, 169), 28);
                return;
            case 29:
                displayResult(find_center_x(146, 155), find_center_y(211, 193), 29);
                return;
            case 30:
                displayResult(find_center_x(47, 61), find_center_y(83, 74), 30);
                return;
            case 31:
                displayResult(find_center_x(92, 102), find_center_y(195, 211), 31);
                return;
            case 32:
                displayResult(find_center_x(191, 196), find_center_y(90, 78), 32);
                return;
            case 33:
                displayResult(find_center_x(52, 57), find_center_y(178, 162), 33);
                return;
            case 34:
                displayResult(find_center_x(120, TransportMediator.KEYCODE_MEDIA_RECORD), find_center_y(51, 36), 34);
                return;
            case 35:
                displayResult(find_center_x(186, 201), find_center_y(169, 157), 35);
                return;
            case 36:
                displayResult(find_center_x(68, 79), find_center_y(64, 53), 36);
                return;
            default:
                return;
        }
    }

    public void displayResult(int centerx, int centery, int result) {
        this.finalWinner = Integer.toString(result);
        PathModifier modifier = new PathModifier(1.0f, new PathModifier.Path(2).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) centerx, (float) centery));
        this.ballSprite.setPosition((float) centerx, (float) centery);
        this.ballSprite.unregisterEntityModifier(this.ball_path_modifer);
        modifier.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.winningNumber.setText(GameScene.this.finalWinner);
                GameScene.this.wheelSprite.attachChild(GameScene.this.winningNumber);
                GameScene.this.wheel_spin_sound.stop();
                GameScene.this.add_selected_numbers(GameScene.this.finalWinner);
                GameScene.this.number_yp_position = GameScene.this.number_yp_position + 30;
               // new GameScene.Add_winnig_number().execute(new String[]{GameScene.this.finalWinner});
            }
        });
        this.ballSprite.registerEntityModifier(modifier);
    }
    public void add_selected_numbers(String winner_number) {
        this.yp = this.selectedNumberSprite.getHeight() - ((float) this.number_yp_position);
        Text winner_text = new Text(30.0f, this.yp, this.mFont, winner_number, this.mEngine.getVertexBufferObjectManager());
        winner_text.setRed(3.2f);
        this.selectedNumberSprite.attachChild(winner_text);
    }


    public void start_from_29() {
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(139, 154), (float) find_center_y(236, 219)).to((float) find_center_x(120, 133), (float) find_center_y(240, 223)).to((float) find_center_x(100, 116), (float) find_center_y(240, 219)).to((float) find_center_x(81, 94), (float) find_center_y(235, 219)).to((float) find_center_x(65, 79), (float) find_center_y(224, 212)).to((float) find_center_x(49, 61), (float) find_center_y(218, 205)).to((float) find_center_x(57, 43), (float) find_center_y(209, 196)).to((float) find_center_x(43, 23), (float) find_center_y(193, 177)).to((float) find_center_x(21, 35), (float) find_center_y(178, 163)).to((float) find_center_x(29, 10), (float) find_center_y(157, 146)).to((float) find_center_x(27, 8), (float) find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY)).to((float) find_center_x(26, 7), (float) find_center_y(113, 121)).to((float) find_center_x(8, 29), (float) find_center_y(104, 93)).to((float) find_center_x(37, 18), (float) find_center_y(91, 79)).to((float) find_center_x(45, 23), (float) find_center_y(74, 57)).to((float) find_center_x(54, 43), (float) find_center_y(53, 63)).to((float) find_center_x(49, 63), (float) find_center_y(51, 31)).to((float) find_center_x(81, 74), (float) find_center_y(35, 38)).to((float) find_center_x(97, 84), (float) find_center_y(32, 17)).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_29();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_22() {
        int centerx_22 = find_center_x(120, 133);
        int centery_22 = find_center_y(240, 223);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(100, 116), (float) find_center_y(240, 219)).to((float) find_center_x(81, 94), (float) find_center_y(235, 219)).to((float) find_center_x(65, 79), (float) find_center_y(224, 212)).to((float) find_center_x(49, 61), (float) find_center_y(218, 205)).to((float) find_center_x(57, 43), (float) find_center_y(209, 196)).to((float) find_center_x(43, 23), (float) find_center_y(193, 177)).to((float) find_center_x(21, 35), (float) find_center_y(178, 163)).to((float) find_center_x(29, 10), (float) find_center_y(157, 146)).to((float) find_center_x(27, 8), (float) find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY)).to((float) find_center_x(26, 7), (float) find_center_y(113, 121)).to((float) find_center_x(8, 29), (float) find_center_y(104, 93)).to((float) find_center_x(37, 18), (float) find_center_y(91, 79)).to((float) find_center_x(45, 23), (float) find_center_y(74, 57)).to((float) find_center_x(54, 43), (float) find_center_y(53, 63)).to((float) find_center_x(49, 63), (float) find_center_y(51, 31)).to((float) find_center_x(81, 74), (float) find_center_y(35, 38)).to((float) find_center_x(97, 84), (float) find_center_y(32, 17)).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18).to((float) centerx_22, (float) centery_22);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_22();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_9() {
        int centerx_9 = find_center_x(100, 116);
        int centery_9 = find_center_y(240, 219);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        int centerx_22 = find_center_x(120, 133);
        int centery_22 = find_center_y(240, 223);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(81, 94), (float) find_center_y(235, 219)).to((float) find_center_x(65, 79), (float) find_center_y(224, 212)).to((float) find_center_x(49, 61), (float) find_center_y(218, 205)).to((float) find_center_x(57, 43), (float) find_center_y(209, 196)).to((float) find_center_x(43, 23), (float) find_center_y(193, 177)).to((float) find_center_x(21, 35), (float) find_center_y(178, 163)).to((float) find_center_x(29, 10), (float) find_center_y(157, 146)).to((float) find_center_x(27, 8), (float) find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY)).to((float) find_center_x(26, 7), (float) find_center_y(113, 121)).to((float) find_center_x(8, 29), (float) find_center_y(104, 93)).to((float) find_center_x(37, 18), (float) find_center_y(91, 79)).to((float) find_center_x(45, 23), (float) find_center_y(74, 57)).to((float) find_center_x(54, 43), (float) find_center_y(53, 63)).to((float) find_center_x(49, 63), (float) find_center_y(51, 31)).to((float) find_center_x(81, 74), (float) find_center_y(35, 38)).to((float) find_center_x(97, 84), (float) find_center_y(32, 17)).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18).to((float) centerx_22, (float) centery_22).to((float) centerx_9, (float) centery_9);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_9();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_31() {
        int centerx_31 = find_center_x(81, 94);
        int centery_31 = find_center_y(235, 219);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        int centerx_22 = find_center_x(120, 133);
        int centery_22 = find_center_y(240, 223);
        int centerx_9 = find_center_x(100, 116);
        int centery_9 = find_center_y(240, 219);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(65, 79), (float) find_center_y(224, 212)).to((float) find_center_x(49, 61), (float) find_center_y(218, 205)).to((float) find_center_x(57, 43), (float) find_center_y(209, 196)).to((float) find_center_x(43, 23), (float) find_center_y(193, 177)).to((float) find_center_x(21, 35), (float) find_center_y(178, 163)).to((float) find_center_x(29, 10), (float) find_center_y(157, 146)).to((float) find_center_x(27, 8), (float) find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY)).to((float) find_center_x(26, 7), (float) find_center_y(113, 121)).to((float) find_center_x(8, 29), (float) find_center_y(104, 93)).to((float) find_center_x(37, 18), (float) find_center_y(91, 79)).to((float) find_center_x(45, 23), (float) find_center_y(74, 57)).to((float) find_center_x(54, 43), (float) find_center_y(53, 63)).to((float) find_center_x(49, 63), (float) find_center_y(51, 31)).to((float) find_center_x(81, 74), (float) find_center_y(35, 38)).to((float) find_center_x(97, 84), (float) find_center_y(32, 17)).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18).to((float) centerx_22, (float) centery_22).to((float) centerx_9, (float) centery_9).to((float) centerx_31, (float) centery_31);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_31();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_14() {
        int centerx_14 = find_center_x(65, 79);
        int centery_14 = find_center_y(224, 212);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        int centerx_22 = find_center_x(120, 133);
        int centery_22 = find_center_y(240, 223);
        int centerx_9 = find_center_x(100, 116);
        int centery_9 = find_center_y(240, 219);
        int centerx_31 = find_center_x(81, 94);
        int centery_31 = find_center_y(235, 219);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(49, 61), (float) find_center_y(218, 205)).to((float) find_center_x(57, 43), (float) find_center_y(209, 196)).to((float) find_center_x(43, 23), (float) find_center_y(193, 177)).to((float) find_center_x(21, 35), (float) find_center_y(178, 163)).to((float) find_center_x(29, 10), (float) find_center_y(157, 146)).to((float) find_center_x(27, 8), (float) find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY)).to((float) find_center_x(26, 7), (float) find_center_y(113, 121)).to((float) find_center_x(8, 29), (float) find_center_y(104, 93)).to((float) find_center_x(37, 18), (float) find_center_y(91, 79)).to((float) find_center_x(45, 23), (float) find_center_y(74, 57)).to((float) find_center_x(54, 43), (float) find_center_y(53, 63)).to((float) find_center_x(49, 63), (float) find_center_y(51, 31)).to((float) find_center_x(81, 74), (float) find_center_y(35, 38)).to((float) find_center_x(97, 84), (float) find_center_y(32, 17)).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18).to((float) centerx_22, (float) centery_22).to((float) centerx_9, (float) centery_9).to((float) centerx_31, (float) centery_31).to((float) centerx_14, (float) centery_14);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_14();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_20() {
        int centerx_20 = find_center_x(49, 61);
        int centery_20 = find_center_y(218, 205);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        int centerx_22 = find_center_x(120, 133);
        int centery_22 = find_center_y(240, 223);
        int centerx_9 = find_center_x(100, 116);
        int centery_9 = find_center_y(240, 219);
        int centerx_31 = find_center_x(81, 94);
        int centery_31 = find_center_y(235, 219);
        int centerx_14 = find_center_x(65, 79);
        int centery_14 = find_center_y(224, 212);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(57, 43), (float) find_center_y(209, 196)).to((float) find_center_x(43, 23), (float) find_center_y(193, 177)).to((float) find_center_x(21, 35), (float) find_center_y(178, 163)).to((float) find_center_x(29, 10), (float) find_center_y(157, 146)).to((float) find_center_x(27, 8), (float) find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY)).to((float) find_center_x(26, 7), (float) find_center_y(113, 121)).to((float) find_center_x(8, 29), (float) find_center_y(104, 93)).to((float) find_center_x(37, 18), (float) find_center_y(91, 79)).to((float) find_center_x(45, 23), (float) find_center_y(74, 57)).to((float) find_center_x(54, 43), (float) find_center_y(53, 63)).to((float) find_center_x(49, 63), (float) find_center_y(51, 31)).to((float) find_center_x(81, 74), (float) find_center_y(35, 38)).to((float) find_center_x(97, 84), (float) find_center_y(32, 17)).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18).to((float) centerx_22, (float) centery_22).to((float) centerx_9, (float) centery_9).to((float) centerx_31, (float) centery_31).to((float) centerx_14, (float) centery_14).to((float) centerx_20, (float) centery_20);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_20();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_1() {
        int centerx_1 = find_center_x(57, 43);
        int centery_1 = find_center_y(209, 196);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        int centerx_22 = find_center_x(120, 133);
        int centery_22 = find_center_y(240, 223);
        int centerx_9 = find_center_x(100, 116);
        int centery_9 = find_center_y(240, 219);
        int centerx_31 = find_center_x(81, 94);
        int centery_31 = find_center_y(235, 219);
        int centerx_14 = find_center_x(65, 79);
        int centery_14 = find_center_y(224, 212);
        int centerx_20 = find_center_x(49, 61);
        int centery_20 = find_center_y(218, 205);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(43, 23), (float) find_center_y(193, 177)).to((float) find_center_x(21, 35), (float) find_center_y(178, 163)).to((float) find_center_x(29, 10), (float) find_center_y(157, 146)).to((float) find_center_x(27, 8), (float) find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY)).to((float) find_center_x(26, 7), (float) find_center_y(113, 121)).to((float) find_center_x(8, 29), (float) find_center_y(104, 93)).to((float) find_center_x(37, 18), (float) find_center_y(91, 79)).to((float) find_center_x(45, 23), (float) find_center_y(74, 57)).to((float) find_center_x(54, 43), (float) find_center_y(53, 63)).to((float) find_center_x(49, 63), (float) find_center_y(51, 31)).to((float) find_center_x(81, 74), (float) find_center_y(35, 38)).to((float) find_center_x(97, 84), (float) find_center_y(32, 17)).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18).to((float) centerx_22, (float) centery_22).to((float) centerx_9, (float) centery_9).to((float) centerx_31, (float) centery_31).to((float) centerx_14, (float) centery_14).to((float) centerx_20, (float) centery_20).to((float) centerx_1, (float) centery_1);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_1();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_33() {
        int centerx_33 = find_center_x(43, 23);
        int centery_33 = find_center_y(193, 177);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        int centerx_22 = find_center_x(120, 133);
        int centery_22 = find_center_y(240, 223);
        int centerx_9 = find_center_x(100, 116);
        int centery_9 = find_center_y(240, 219);
        int centerx_31 = find_center_x(81, 94);
        int centery_31 = find_center_y(235, 219);
        int centerx_14 = find_center_x(65, 79);
        int centery_14 = find_center_y(224, 212);
        int centerx_20 = find_center_x(49, 61);
        int centery_20 = find_center_y(218, 205);
        int centerx_1 = find_center_x(57, 43);
        int centery_1 = find_center_y(209, 196);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(21, 35), (float) find_center_y(178, 163)).to((float) find_center_x(29, 10), (float) find_center_y(157, 146)).to((float) find_center_x(27, 8), (float) find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY)).to((float) find_center_x(26, 7), (float) find_center_y(113, 121)).to((float) find_center_x(8, 29), (float) find_center_y(104, 93)).to((float) find_center_x(37, 18), (float) find_center_y(91, 79)).to((float) find_center_x(45, 23), (float) find_center_y(74, 57)).to((float) find_center_x(54, 43), (float) find_center_y(53, 63)).to((float) find_center_x(49, 63), (float) find_center_y(51, 31)).to((float) find_center_x(81, 74), (float) find_center_y(35, 38)).to((float) find_center_x(97, 84), (float) find_center_y(32, 17)).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18).to((float) centerx_22, (float) centery_22).to((float) centerx_9, (float) centery_9).to((float) centerx_31, (float) centery_31).to((float) centerx_14, (float) centery_14).to((float) centerx_20, (float) centery_20).to((float) centerx_1, (float) centery_1).to((float) centerx_33, (float) centery_33);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_33();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_16() {
        int centerx_16 = find_center_x(21, 35);
        int centery_16 = find_center_y(178, 163);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        int centerx_22 = find_center_x(120, 133);
        int centery_22 = find_center_y(240, 223);
        int centerx_9 = find_center_x(100, 116);
        int centery_9 = find_center_y(240, 219);
        int centerx_31 = find_center_x(81, 94);
        int centery_31 = find_center_y(235, 219);
        int centerx_14 = find_center_x(65, 79);
        int centery_14 = find_center_y(224, 212);
        int centerx_20 = find_center_x(49, 61);
        int centery_20 = find_center_y(218, 205);
        int centerx_1 = find_center_x(57, 43);
        int centery_1 = find_center_y(209, 196);
        int centerx_33 = find_center_x(43, 23);
        int centery_33 = find_center_y(193, 177);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(29, 10), (float) find_center_y(157, 146)).to((float) find_center_x(27, 8), (float) find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY)).to((float) find_center_x(26, 7), (float) find_center_y(113, 121)).to((float) find_center_x(8, 29), (float) find_center_y(104, 93)).to((float) find_center_x(37, 18), (float) find_center_y(91, 79)).to((float) find_center_x(45, 23), (float) find_center_y(74, 57)).to((float) find_center_x(54, 43), (float) find_center_y(53, 63)).to((float) find_center_x(49, 63), (float) find_center_y(51, 31)).to((float) find_center_x(81, 74), (float) find_center_y(35, 38)).to((float) find_center_x(97, 84), (float) find_center_y(32, 17)).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18).to((float) centerx_22, (float) centery_22).to((float) centerx_9, (float) centery_9).to((float) centerx_31, (float) centery_31).to((float) centerx_14, (float) centery_14).to((float) centerx_20, (float) centery_20).to((float) centerx_1, (float) centery_1).to((float) centerx_33, (float) centery_33).to((float) centerx_16, (float) centery_16);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_16();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_24() {
        int centerx_24 = find_center_x(29, 10);
        int centery_24 = find_center_y(157, 146);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        int centerx_22 = find_center_x(120, 133);
        int centery_22 = find_center_y(240, 223);
        int centerx_9 = find_center_x(100, 116);
        int centery_9 = find_center_y(240, 219);
        int centerx_31 = find_center_x(81, 94);
        int centery_31 = find_center_y(235, 219);
        int centerx_14 = find_center_x(65, 79);
        int centery_14 = find_center_y(224, 212);
        int centerx_20 = find_center_x(49, 61);
        int centery_20 = find_center_y(218, 205);
        int centerx_1 = find_center_x(57, 43);
        int centery_1 = find_center_y(209, 196);
        int centerx_33 = find_center_x(43, 23);
        int centery_33 = find_center_y(193, 177);
        int centerx_16 = find_center_x(21, 35);
        int centery_16 = find_center_y(178, 163);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(27, 8), (float) find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY)).to((float) find_center_x(26, 7), (float) find_center_y(113, 121)).to((float) find_center_x(8, 29), (float) find_center_y(104, 93)).to((float) find_center_x(37, 18), (float) find_center_y(91, 79)).to((float) find_center_x(45, 23), (float) find_center_y(74, 57)).to((float) find_center_x(54, 43), (float) find_center_y(53, 63)).to((float) find_center_x(49, 63), (float) find_center_y(51, 31)).to((float) find_center_x(81, 74), (float) find_center_y(35, 38)).to((float) find_center_x(97, 84), (float) find_center_y(32, 17)).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18).to((float) centerx_22, (float) centery_22).to((float) centerx_9, (float) centery_9).to((float) centerx_31, (float) centery_31).to((float) centerx_14, (float) centery_14).to((float) centerx_20, (float) centery_20).to((float) centerx_1, (float) centery_1).to((float) centerx_33, (float) centery_33).to((float) centerx_16, (float) centery_16).to((float) centerx_24, (float) centery_24);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_24();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_5() {
        int centerx_5 = find_center_x(27, 8);
        int centery_5 = find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        int centerx_22 = find_center_x(120, 133);
        int centery_22 = find_center_y(240, 223);
        int centerx_9 = find_center_x(100, 116);
        int centery_9 = find_center_y(240, 219);
        int centerx_31 = find_center_x(81, 94);
        int centery_31 = find_center_y(235, 219);
        int centerx_14 = find_center_x(65, 79);
        int centery_14 = find_center_y(224, 212);
        int centerx_20 = find_center_x(49, 61);
        int centery_20 = find_center_y(218, 205);
        int centerx_1 = find_center_x(57, 43);
        int centery_1 = find_center_y(209, 196);
        int centerx_33 = find_center_x(43, 23);
        int centery_33 = find_center_y(193, 177);
        int centerx_16 = find_center_x(21, 35);
        int centery_16 = find_center_y(178, 163);
        int centerx_24 = find_center_x(29, 10);
        int centery_24 = find_center_y(157, 146);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(26, 7), (float) find_center_y(113, 121)).to((float) find_center_x(8, 29), (float) find_center_y(104, 93)).to((float) find_center_x(37, 18), (float) find_center_y(91, 79)).to((float) find_center_x(45, 23), (float) find_center_y(74, 57)).to((float) find_center_x(54, 43), (float) find_center_y(53, 63)).to((float) find_center_x(49, 63), (float) find_center_y(51, 31)).to((float) find_center_x(81, 74), (float) find_center_y(35, 38)).to((float) find_center_x(97, 84), (float) find_center_y(32, 17)).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18).to((float) centerx_22, (float) centery_22).to((float) centerx_9, (float) centery_9).to((float) centerx_31, (float) centery_31).to((float) centerx_14, (float) centery_14).to((float) centerx_20, (float) centery_20).to((float) centerx_1, (float) centery_1).to((float) centerx_33, (float) centery_33).to((float) centerx_16, (float) centery_16).to((float) centerx_24, (float) centery_24).to((float) centerx_5, (float) centery_5);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_5();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_10() {
        int centerx_10 = find_center_x(26, 7);
        int centery_10 = find_center_y(113, 121);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        int centerx_22 = find_center_x(120, 133);
        int centery_22 = find_center_y(240, 223);
        int centerx_9 = find_center_x(100, 116);
        int centery_9 = find_center_y(240, 219);
        int centerx_31 = find_center_x(81, 94);
        int centery_31 = find_center_y(235, 219);
        int centerx_14 = find_center_x(65, 79);
        int centery_14 = find_center_y(224, 212);
        int centerx_20 = find_center_x(49, 61);
        int centery_20 = find_center_y(218, 205);
        int centerx_1 = find_center_x(57, 43);
        int centery_1 = find_center_y(209, 196);
        int centerx_33 = find_center_x(43, 23);
        int centery_33 = find_center_y(193, 177);
        int centerx_16 = find_center_x(21, 35);
        int centery_16 = find_center_y(178, 163);
        int centerx_24 = find_center_x(29, 10);
        int centery_24 = find_center_y(157, 146);
        int centerx_5 = find_center_x(27, 8);
        int centery_5 = find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(8, 29), (float) find_center_y(104, 93)).to((float) find_center_x(37, 18), (float) find_center_y(91, 79)).to((float) find_center_x(45, 23), (float) find_center_y(74, 57)).to((float) find_center_x(54, 43), (float) find_center_y(53, 63)).to((float) find_center_x(49, 63), (float) find_center_y(51, 31)).to((float) find_center_x(81, 74), (float) find_center_y(35, 38)).to((float) find_center_x(97, 84), (float) find_center_y(32, 17)).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18).to((float) centerx_22, (float) centery_22).to((float) centerx_9, (float) centery_9).to((float) centerx_31, (float) centery_31).to((float) centerx_14, (float) centery_14).to((float) centerx_20, (float) centery_20).to((float) centerx_1, (float) centery_1).to((float) centerx_33, (float) centery_33).to((float) centerx_16, (float) centery_16).to((float) centerx_24, (float) centery_24).to((float) centerx_5, (float) centery_5).to((float) centerx_10, (float) centery_10);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_10();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_23() {
        int centerx_23 = find_center_x(8, 29);
        int centery_23 = find_center_y(104, 93);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        int centerx_22 = find_center_x(120, 133);
        int centery_22 = find_center_y(240, 223);
        int centerx_9 = find_center_x(100, 116);
        int centery_9 = find_center_y(240, 219);
        int centerx_31 = find_center_x(81, 94);
        int centery_31 = find_center_y(235, 219);
        int centerx_14 = find_center_x(65, 79);
        int centery_14 = find_center_y(224, 212);
        int centerx_20 = find_center_x(49, 61);
        int centery_20 = find_center_y(218, 205);
        int centerx_1 = find_center_x(57, 43);
        int centery_1 = find_center_y(209, 196);
        int centerx_33 = find_center_x(43, 23);
        int centery_33 = find_center_y(193, 177);
        int centerx_16 = find_center_x(21, 35);
        int centery_16 = find_center_y(178, 163);
        int centerx_24 = find_center_x(29, 10);
        int centery_24 = find_center_y(157, 146);
        int centerx_5 = find_center_x(27, 8);
        int centery_5 = find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY);
        int centerx_10 = find_center_x(26, 7);
        int centery_10 = find_center_y(113, 121);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(37, 18), (float) find_center_y(91, 79)).to((float) find_center_x(45, 23), (float) find_center_y(74, 57)).to((float) find_center_x(54, 43), (float) find_center_y(53, 63)).to((float) find_center_x(49, 63), (float) find_center_y(51, 31)).to((float) find_center_x(81, 74), (float) find_center_y(35, 38)).to((float) find_center_x(97, 84), (float) find_center_y(32, 17)).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18).to((float) centerx_22, (float) centery_22).to((float) centerx_9, (float) centery_9).to((float) centerx_31, (float) centery_31).to((float) centerx_14, (float) centery_14).to((float) centerx_20, (float) centery_20).to((float) centerx_1, (float) centery_1).to((float) centerx_33, (float) centery_33).to((float) centerx_16, (float) centery_16).to((float) centerx_24, (float) centery_24).to((float) centerx_5, (float) centery_5).to((float) centerx_10, (float) centery_10).to((float) centerx_23, (float) centery_23);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_23();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_8() {
        int centerx_8 = find_center_x(37, 18);
        int centery_8 = find_center_y(91, 79);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        int centerx_22 = find_center_x(120, 133);
        int centery_22 = find_center_y(240, 223);
        int centerx_9 = find_center_x(100, 116);
        int centery_9 = find_center_y(240, 219);
        int centerx_31 = find_center_x(81, 94);
        int centery_31 = find_center_y(235, 219);
        int centerx_14 = find_center_x(65, 79);
        int centery_14 = find_center_y(224, 212);
        int centerx_20 = find_center_x(49, 61);
        int centery_20 = find_center_y(218, 205);
        int centerx_1 = find_center_x(57, 43);
        int centery_1 = find_center_y(209, 196);
        int centerx_33 = find_center_x(43, 23);
        int centery_33 = find_center_y(193, 177);
        int centerx_16 = find_center_x(21, 35);
        int centery_16 = find_center_y(178, 163);
        int centerx_24 = find_center_x(29, 10);
        int centery_24 = find_center_y(157, 146);
        int centerx_5 = find_center_x(27, 8);
        int centery_5 = find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY);
        int centerx_10 = find_center_x(26, 7);
        int centery_10 = find_center_y(113, 121);
        int centerx_23 = find_center_x(8, 29);
        int centery_23 = find_center_y(104, 93);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(45, 23), (float) find_center_y(74, 57)).to((float) find_center_x(54, 43), (float) find_center_y(53, 63)).to((float) find_center_x(49, 63), (float) find_center_y(51, 31)).to((float) find_center_x(81, 74), (float) find_center_y(35, 38)).to((float) find_center_x(97, 84), (float) find_center_y(32, 17)).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18).to((float) centerx_22, (float) centery_22).to((float) centerx_9, (float) centery_9).to((float) centerx_31, (float) centery_31).to((float) centerx_14, (float) centery_14).to((float) centerx_20, (float) centery_20).to((float) centerx_1, (float) centery_1).to((float) centerx_33, (float) centery_33).to((float) centerx_16, (float) centery_16).to((float) centerx_24, (float) centery_24).to((float) centerx_5, (float) centery_5).to((float) centerx_10, (float) centery_10).to((float) centerx_23, (float) centery_23).to((float) centerx_8, (float) centery_8);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_8();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_30() {
        int centerx_30 = find_center_x(45, 23);
        int centery_30 = find_center_y(74, 57);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        int centerx_22 = find_center_x(120, 133);
        int centery_22 = find_center_y(240, 223);
        int centerx_9 = find_center_x(100, 116);
        int centery_9 = find_center_y(240, 219);
        int centerx_31 = find_center_x(81, 94);
        int centery_31 = find_center_y(235, 219);
        int centerx_14 = find_center_x(65, 79);
        int centery_14 = find_center_y(224, 212);
        int centerx_20 = find_center_x(49, 61);
        int centery_20 = find_center_y(218, 205);
        int centerx_1 = find_center_x(57, 43);
        int centery_1 = find_center_y(209, 196);
        int centerx_33 = find_center_x(43, 23);
        int centery_33 = find_center_y(193, 177);
        int centerx_16 = find_center_x(21, 35);
        int centery_16 = find_center_y(178, 163);
        int centerx_24 = find_center_x(29, 10);
        int centery_24 = find_center_y(157, 146);
        int centerx_5 = find_center_x(27, 8);
        int centery_5 = find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY);
        int centerx_10 = find_center_x(26, 7);
        int centery_10 = find_center_y(113, 121);
        int centerx_23 = find_center_x(8, 29);
        int centery_23 = find_center_y(104, 93);
        int centerx_8 = find_center_x(37, 18);
        int centery_8 = find_center_y(91, 79);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(54, 43), (float) find_center_y(53, 63)).to((float) find_center_x(49, 63), (float) find_center_y(51, 31)).to((float) find_center_x(81, 74), (float) find_center_y(35, 38)).to((float) find_center_x(97, 84), (float) find_center_y(32, 17)).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18).to((float) centerx_22, (float) centery_22).to((float) centerx_9, (float) centery_9).to((float) centerx_31, (float) centery_31).to((float) centerx_14, (float) centery_14).to((float) centerx_20, (float) centery_20).to((float) centerx_1, (float) centery_1).to((float) centerx_33, (float) centery_33).to((float) centerx_16, (float) centery_16).to((float) centerx_24, (float) centery_24).to((float) centerx_5, (float) centery_5).to((float) centerx_10, (float) centery_10).to((float) centerx_23, (float) centery_23).to((float) centerx_8, (float) centery_8).to((float) centerx_30, (float) centery_30);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_30();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_11() {
        int centerx_11 = find_center_x(54, 43);
        int centery_11 = find_center_y(53, 63);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        int centerx_22 = find_center_x(120, 133);
        int centery_22 = find_center_y(240, 223);
        int centerx_9 = find_center_x(100, 116);
        int centery_9 = find_center_y(240, 219);
        int centerx_31 = find_center_x(81, 94);
        int centery_31 = find_center_y(235, 219);
        int centerx_14 = find_center_x(65, 79);
        int centery_14 = find_center_y(224, 212);
        int centerx_20 = find_center_x(49, 61);
        int centery_20 = find_center_y(218, 205);
        int centerx_1 = find_center_x(57, 43);
        int centery_1 = find_center_y(209, 196);
        int centerx_33 = find_center_x(43, 23);
        int centery_33 = find_center_y(193, 177);
        int centerx_16 = find_center_x(21, 35);
        int centery_16 = find_center_y(178, 163);
        int centerx_24 = find_center_x(29, 10);
        int centery_24 = find_center_y(157, 146);
        int centerx_5 = find_center_x(27, 8);
        int centery_5 = find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY);
        int centerx_10 = find_center_x(26, 7);
        int centery_10 = find_center_y(113, 121);
        int centerx_23 = find_center_x(8, 29);
        int centery_23 = find_center_y(104, 93);
        int centerx_8 = find_center_x(37, 18);
        int centery_8 = find_center_y(91, 79);
        int centerx_30 = find_center_x(45, 23);
        int centery_30 = find_center_y(74, 57);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(49, 63), (float) find_center_y(51, 31)).to((float) find_center_x(81, 74), (float) find_center_y(35, 38)).to((float) find_center_x(97, 84), (float) find_center_y(32, 17)).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18).to((float) centerx_22, (float) centery_22).to((float) centerx_9, (float) centery_9).to((float) centerx_31, (float) centery_31).to((float) centerx_14, (float) centery_14).to((float) centerx_20, (float) centery_20).to((float) centerx_1, (float) centery_1).to((float) centerx_33, (float) centery_33).to((float) centerx_16, (float) centery_16).to((float) centerx_24, (float) centery_24).to((float) centerx_5, (float) centery_5).to((float) centerx_10, (float) centery_10).to((float) centerx_23, (float) centery_23).to((float) centerx_8, (float) centery_8).to((float) centerx_30, (float) centery_30).to((float) centerx_11, (float) centery_11);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_11();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_36() {
        int centerx_36 = find_center_x(49, 63);
        int centery_36 = find_center_y(51, 31);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        int centerx_22 = find_center_x(120, 133);
        int centery_22 = find_center_y(240, 223);
        int centerx_9 = find_center_x(100, 116);
        int centery_9 = find_center_y(240, 219);
        int centerx_31 = find_center_x(81, 94);
        int centery_31 = find_center_y(235, 219);
        int centerx_14 = find_center_x(65, 79);
        int centery_14 = find_center_y(224, 212);
        int centerx_20 = find_center_x(49, 61);
        int centery_20 = find_center_y(218, 205);
        int centerx_1 = find_center_x(57, 43);
        int centery_1 = find_center_y(209, 196);
        int centerx_33 = find_center_x(43, 23);
        int centery_33 = find_center_y(193, 177);
        int centerx_16 = find_center_x(21, 35);
        int centery_16 = find_center_y(178, 163);
        int centerx_24 = find_center_x(29, 10);
        int centery_24 = find_center_y(157, 146);
        int centerx_5 = find_center_x(27, 8);
        int centery_5 = find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY);
        int centerx_10 = find_center_x(26, 7);
        int centery_10 = find_center_y(113, 121);
        int centerx_23 = find_center_x(8, 29);
        int centery_23 = find_center_y(104, 93);
        int centerx_8 = find_center_x(37, 18);
        int centery_8 = find_center_y(91, 79);
        int centerx_30 = find_center_x(45, 23);
        int centery_30 = find_center_y(74, 57);
        int centerx_11 = find_center_x(54, 43);
        int centery_11 = find_center_y(53, 63);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(81, 74), (float) find_center_y(35, 38)).to((float) find_center_x(97, 84), (float) find_center_y(32, 17)).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18).to((float) centerx_22, (float) centery_22).to((float) centerx_9, (float) centery_9).to((float) centerx_31, (float) centery_31).to((float) centerx_14, (float) centery_14).to((float) centerx_20, (float) centery_20).to((float) centerx_1, (float) centery_1).to((float) centerx_33, (float) centery_33).to((float) centerx_16, (float) centery_16).to((float) centerx_24, (float) centery_24).to((float) centerx_5, (float) centery_5).to((float) centerx_10, (float) centery_10).to((float) centerx_23, (float) centery_23).to((float) centerx_8, (float) centery_8).to((float) centerx_30, (float) centery_30).to((float) centerx_11, (float) centery_11).to((float) centerx_36, (float) centery_36);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_36();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_13() {
        int centerx_13 = find_center_x(81, 74);
        int centery_13 = find_center_y(35, 38);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        int centerx_22 = find_center_x(120, 133);
        int centery_22 = find_center_y(240, 223);
        int centerx_9 = find_center_x(100, 116);
        int centery_9 = find_center_y(240, 219);
        int centerx_31 = find_center_x(81, 94);
        int centery_31 = find_center_y(235, 219);
        int centerx_14 = find_center_x(65, 79);
        int centery_14 = find_center_y(224, 212);
        int centerx_20 = find_center_x(49, 61);
        int centery_20 = find_center_y(218, 205);
        int centerx_1 = find_center_x(57, 43);
        int centery_1 = find_center_y(209, 196);
        int centerx_33 = find_center_x(43, 23);
        int centery_33 = find_center_y(193, 177);
        int centerx_16 = find_center_x(21, 35);
        int centery_16 = find_center_y(178, 163);
        int centerx_24 = find_center_x(29, 10);
        int centery_24 = find_center_y(157, 146);
        int centerx_5 = find_center_x(27, 8);
        int centery_5 = find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY);
        int centerx_10 = find_center_x(26, 7);
        int centery_10 = find_center_y(113, 121);
        int centerx_23 = find_center_x(8, 29);
        int centery_23 = find_center_y(104, 93);
        int centerx_8 = find_center_x(37, 18);
        int centery_8 = find_center_y(91, 79);
        int centerx_30 = find_center_x(45, 23);
        int centery_30 = find_center_y(74, 57);
        int centerx_11 = find_center_x(54, 43);
        int centery_11 = find_center_y(53, 63);
        int centerx_36 = find_center_x(49, 63);
        int centery_36 = find_center_y(51, 31);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(97, 84), (float) find_center_y(32, 17)).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18).to((float) centerx_22, (float) centery_22).to((float) centerx_9, (float) centery_9).to((float) centerx_31, (float) centery_31).to((float) centerx_14, (float) centery_14).to((float) centerx_20, (float) centery_20).to((float) centerx_1, (float) centery_1).to((float) centerx_33, (float) centery_33).to((float) centerx_16, (float) centery_16).to((float) centerx_24, (float) centery_24).to((float) centerx_5, (float) centery_5).to((float) centerx_10, (float) centery_10).to((float) centerx_23, (float) centery_23).to((float) centerx_8, (float) centery_8).to((float) centerx_30, (float) centery_30).to((float) centerx_11, (float) centery_11).to((float) centerx_36, (float) centery_36).to((float) centerx_13, (float) centery_13);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_13();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_27() {
        int centerx_27 = find_center_x(97, 84);
        int centery_27 = find_center_y(32, 17);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        int centerx_22 = find_center_x(120, 133);
        int centery_22 = find_center_y(240, 223);
        int centerx_9 = find_center_x(100, 116);
        int centery_9 = find_center_y(240, 219);
        int centerx_31 = find_center_x(81, 94);
        int centery_31 = find_center_y(235, 219);
        int centerx_14 = find_center_x(65, 79);
        int centery_14 = find_center_y(224, 212);
        int centerx_20 = find_center_x(49, 61);
        int centery_20 = find_center_y(218, 205);
        int centerx_1 = find_center_x(57, 43);
        int centery_1 = find_center_y(209, 196);
        int centerx_33 = find_center_x(43, 23);
        int centery_33 = find_center_y(193, 177);
        int centerx_16 = find_center_x(21, 35);
        int centery_16 = find_center_y(178, 163);
        int centerx_24 = find_center_x(29, 10);
        int centery_24 = find_center_y(157, 146);
        int centerx_5 = find_center_x(27, 8);
        int centery_5 = find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY);
        int centerx_10 = find_center_x(26, 7);
        int centery_10 = find_center_y(113, 121);
        int centerx_23 = find_center_x(8, 29);
        int centery_23 = find_center_y(104, 93);
        int centerx_8 = find_center_x(37, 18);
        int centery_8 = find_center_y(91, 79);
        int centerx_30 = find_center_x(45, 23);
        int centery_30 = find_center_y(74, 57);
        int centerx_11 = find_center_x(54, 43);
        int centery_11 = find_center_y(53, 63);
        int centerx_36 = find_center_x(49, 63);
        int centery_36 = find_center_y(51, 31);
        int centerx_13 = find_center_x(81, 74);
        int centery_13 = find_center_y(35, 38);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18).to((float) centerx_22, (float) centery_22).to((float) centerx_9, (float) centery_9).to((float) centerx_31, (float) centery_31).to((float) centerx_14, (float) centery_14).to((float) centerx_20, (float) centery_20).to((float) centerx_1, (float) centery_1).to((float) centerx_33, (float) centery_33).to((float) centerx_16, (float) centery_16).to((float) centerx_24, (float) centery_24).to((float) centerx_5, (float) centery_5).to((float) centerx_10, (float) centery_10).to((float) centerx_23, (float) centery_23).to((float) centerx_8, (float) centery_8).to((float) centerx_30, (float) centery_30).to((float) centerx_11, (float) centery_11).to((float) centerx_36, (float) centery_36).to((float) centerx_13, (float) centery_13).to((float) centerx_27, (float) centery_27);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_27();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_6() {
        int centerx_6 = find_center_x(115, 110);
        int centery_6 = find_center_y(28, 27);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        int centerx_22 = find_center_x(120, 133);
        int centery_22 = find_center_y(240, 223);
        int centerx_9 = find_center_x(100, 116);
        int centery_9 = find_center_y(240, 219);
        int centerx_31 = find_center_x(81, 94);
        int centery_31 = find_center_y(235, 219);
        int centerx_14 = find_center_x(65, 79);
        int centery_14 = find_center_y(224, 212);
        int centerx_20 = find_center_x(49, 61);
        int centery_20 = find_center_y(218, 205);
        int centerx_1 = find_center_x(57, 43);
        int centery_1 = find_center_y(209, 196);
        int centerx_33 = find_center_x(43, 23);
        int centery_33 = find_center_y(193, 177);
        int centerx_16 = find_center_x(21, 35);
        int centery_16 = find_center_y(178, 163);
        int centerx_24 = find_center_x(29, 10);
        int centery_24 = find_center_y(157, 146);
        int centerx_5 = find_center_x(27, 8);
        int centery_5 = find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY);
        int centerx_10 = find_center_x(26, 7);
        int centery_10 = find_center_y(113, 121);
        int centerx_23 = find_center_x(8, 29);
        int centery_23 = find_center_y(104, 93);
        int centerx_8 = find_center_x(37, 18);
        int centery_8 = find_center_y(91, 79);
        int centerx_30 = find_center_x(45, 23);
        int centery_30 = find_center_y(74, 57);
        int centerx_11 = find_center_x(54, 43);
        int centery_11 = find_center_y(53, 63);
        int centerx_36 = find_center_x(49, 63);
        int centery_36 = find_center_y(51, 31);
        int centerx_13 = find_center_x(81, 74);
        int centery_13 = find_center_y(35, 38);
        int centerx_27 = find_center_x(97, 84);
        int centery_27 = find_center_y(32, 17);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18).to((float) centerx_22, (float) centery_22).to((float) centerx_9, (float) centery_9).to((float) centerx_31, (float) centery_31).to((float) centerx_14, (float) centery_14).to((float) centerx_20, (float) centery_20).to((float) centerx_1, (float) centery_1).to((float) centerx_33, (float) centery_33).to((float) centerx_16, (float) centery_16).to((float) centerx_24, (float) centery_24).to((float) centerx_5, (float) centery_5).to((float) centerx_10, (float) centery_10).to((float) centerx_23, (float) centery_23).to((float) centerx_8, (float) centery_8).to((float) centerx_30, (float) centery_30).to((float) centerx_11, (float) centery_11).to((float) centerx_36, (float) centery_36).to((float) centerx_13, (float) centery_13).to((float) centerx_27, (float) centery_27).to((float) centerx_6, (float) centery_6);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_6();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_34() {
        int centerx_34 = find_center_x(119, 131);
        int centery_34 = find_center_y(27, 11);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        int centerx_22 = find_center_x(120, 133);
        int centery_22 = find_center_y(240, 223);
        int centerx_9 = find_center_x(100, 116);
        int centery_9 = find_center_y(240, 219);
        int centerx_31 = find_center_x(81, 94);
        int centery_31 = find_center_y(235, 219);
        int centerx_14 = find_center_x(65, 79);
        int centery_14 = find_center_y(224, 212);
        int centerx_20 = find_center_x(49, 61);
        int centery_20 = find_center_y(218, 205);
        int centerx_1 = find_center_x(57, 43);
        int centery_1 = find_center_y(209, 196);
        int centerx_33 = find_center_x(43, 23);
        int centery_33 = find_center_y(193, 177);
        int centerx_16 = find_center_x(21, 35);
        int centery_16 = find_center_y(178, 163);
        int centerx_24 = find_center_x(29, 10);
        int centery_24 = find_center_y(157, 146);
        int centerx_5 = find_center_x(27, 8);
        int centery_5 = find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY);
        int centerx_10 = find_center_x(26, 7);
        int centery_10 = find_center_y(113, 121);
        int centerx_23 = find_center_x(8, 29);
        int centery_23 = find_center_y(104, 93);
        int centerx_8 = find_center_x(37, 18);
        int centery_8 = find_center_y(91, 79);
        int centerx_30 = find_center_x(45, 23);
        int centery_30 = find_center_y(74, 57);
        int centerx_11 = find_center_x(54, 43);
        int centery_11 = find_center_y(53, 63);
        int centerx_36 = find_center_x(49, 63);
        int centery_36 = find_center_y(51, 31);
        int centerx_13 = find_center_x(81, 74);
        int centery_13 = find_center_y(35, 38);
        int centerx_27 = find_center_x(97, 84);
        int centery_27 = find_center_y(32, 17);
        int centerx_6 = find_center_x(115, 110);
        int centery_6 = find_center_y(28, 27);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18).to((float) centerx_22, (float) centery_22).to((float) centerx_9, (float) centery_9).to((float) centerx_31, (float) centery_31).to((float) centerx_14, (float) centery_14).to((float) centerx_20, (float) centery_20).to((float) centerx_1, (float) centery_1).to((float) centerx_33, (float) centery_33).to((float) centerx_16, (float) centery_16).to((float) centerx_24, (float) centery_24).to((float) centerx_5, (float) centery_5).to((float) centerx_10, (float) centery_10).to((float) centerx_23, (float) centery_23).to((float) centerx_8, (float) centery_8).to((float) centerx_30, (float) centery_30).to((float) centerx_11, (float) centery_11).to((float) centerx_36, (float) centery_36).to((float) centerx_13, (float) centery_13).to((float) centerx_27, (float) centery_27).to((float) centerx_6, (float) centery_6).to((float) centerx_34, (float) centery_34);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_34();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_17() {
        int centerx_17 = find_center_x(136, 149);
        int centery_17 = find_center_y(31, 13);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        int centerx_22 = find_center_x(120, 133);
        int centery_22 = find_center_y(240, 223);
        int centerx_9 = find_center_x(100, 116);
        int centery_9 = find_center_y(240, 219);
        int centerx_31 = find_center_x(81, 94);
        int centery_31 = find_center_y(235, 219);
        int centerx_14 = find_center_x(65, 79);
        int centery_14 = find_center_y(224, 212);
        int centerx_20 = find_center_x(49, 61);
        int centery_20 = find_center_y(218, 205);
        int centerx_1 = find_center_x(57, 43);
        int centery_1 = find_center_y(209, 196);
        int centerx_33 = find_center_x(43, 23);
        int centery_33 = find_center_y(193, 177);
        int centerx_16 = find_center_x(21, 35);
        int centery_16 = find_center_y(178, 163);
        int centerx_24 = find_center_x(29, 10);
        int centery_24 = find_center_y(157, 146);
        int centerx_5 = find_center_x(27, 8);
        int centery_5 = find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY);
        int centerx_10 = find_center_x(26, 7);
        int centery_10 = find_center_y(113, 121);
        int centerx_23 = find_center_x(8, 29);
        int centery_23 = find_center_y(104, 93);
        int centerx_8 = find_center_x(37, 18);
        int centery_8 = find_center_y(91, 79);
        int centerx_30 = find_center_x(45, 23);
        int centery_30 = find_center_y(74, 57);
        int centerx_11 = find_center_x(54, 43);
        int centery_11 = find_center_y(53, 63);
        int centerx_36 = find_center_x(49, 63);
        int centery_36 = find_center_y(51, 31);
        int centerx_13 = find_center_x(81, 74);
        int centery_13 = find_center_y(35, 38);
        int centerx_27 = find_center_x(97, 84);
        int centery_27 = find_center_y(32, 17);
        int centerx_6 = find_center_x(115, 110);
        int centery_6 = find_center_y(28, 27);
        int centerx_34 = find_center_x(119, 131);
        int centery_34 = find_center_y(27, 11);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18).to((float) centerx_22, (float) centery_22).to((float) centerx_9, (float) centery_9).to((float) centerx_31, (float) centery_31).to((float) centerx_14, (float) centery_14).to((float) centerx_20, (float) centery_20).to((float) centerx_1, (float) centery_1).to((float) centerx_33, (float) centery_33).to((float) centerx_16, (float) centery_16).to((float) centerx_24, (float) centery_24).to((float) centerx_5, (float) centery_5).to((float) centerx_10, (float) centery_10).to((float) centerx_23, (float) centery_23).to((float) centerx_8, (float) centery_8).to((float) centerx_30, (float) centery_30).to((float) centerx_11, (float) centery_11).to((float) centerx_36, (float) centery_36).to((float) centerx_13, (float) centery_13).to((float) centerx_27, (float) centery_27).to((float) centerx_6, (float) centery_6).to((float) centerx_34, (float) centery_34).to((float) centerx_17, (float) centery_17);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_17();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_25() {
        int centerx_25 = find_center_x(155, 166);
        int centery_25 = find_center_y(29, 23);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        int centerx_22 = find_center_x(120, 133);
        int centery_22 = find_center_y(240, 223);
        int centerx_9 = find_center_x(100, 116);
        int centery_9 = find_center_y(240, 219);
        int centerx_31 = find_center_x(81, 94);
        int centery_31 = find_center_y(235, 219);
        int centerx_14 = find_center_x(65, 79);
        int centery_14 = find_center_y(224, 212);
        int centerx_20 = find_center_x(49, 61);
        int centery_20 = find_center_y(218, 205);
        int centerx_1 = find_center_x(57, 43);
        int centery_1 = find_center_y(209, 196);
        int centerx_33 = find_center_x(43, 23);
        int centery_33 = find_center_y(193, 177);
        int centerx_16 = find_center_x(21, 35);
        int centery_16 = find_center_y(178, 163);
        int centerx_24 = find_center_x(29, 10);
        int centery_24 = find_center_y(157, 146);
        int centerx_5 = find_center_x(27, 8);
        int centery_5 = find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY);
        int centerx_10 = find_center_x(26, 7);
        int centery_10 = find_center_y(113, 121);
        int centerx_23 = find_center_x(8, 29);
        int centery_23 = find_center_y(104, 93);
        int centerx_8 = find_center_x(37, 18);
        int centery_8 = find_center_y(91, 79);
        int centerx_30 = find_center_x(45, 23);
        int centery_30 = find_center_y(74, 57);
        int centerx_11 = find_center_x(54, 43);
        int centery_11 = find_center_y(53, 63);
        int centerx_36 = find_center_x(49, 63);
        int centery_36 = find_center_y(51, 31);
        int centerx_13 = find_center_x(81, 74);
        int centery_13 = find_center_y(35, 38);
        int centerx_27 = find_center_x(97, 84);
        int centery_27 = find_center_y(32, 17);
        int centerx_6 = find_center_x(115, 110);
        int centery_6 = find_center_y(28, 27);
        int centerx_34 = find_center_x(119, 131);
        int centery_34 = find_center_y(27, 11);
        int centerx_17 = find_center_x(136, 149);
        int centery_17 = find_center_y(31, 13);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18).to((float) centerx_22, (float) centery_22).to((float) centerx_9, (float) centery_9).to((float) centerx_31, (float) centery_31).to((float) centerx_14, (float) centery_14).to((float) centerx_20, (float) centery_20).to((float) centerx_1, (float) centery_1).to((float) centerx_33, (float) centery_33).to((float) centerx_16, (float) centery_16).to((float) centerx_24, (float) centery_24).to((float) centerx_5, (float) centery_5).to((float) centerx_10, (float) centery_10).to((float) centerx_23, (float) centery_23).to((float) centerx_8, (float) centery_8).to((float) centerx_30, (float) centery_30).to((float) centerx_11, (float) centery_11).to((float) centerx_36, (float) centery_36).to((float) centerx_13, (float) centery_13).to((float) centerx_27, (float) centery_27).to((float) centerx_6, (float) centery_6).to((float) centerx_34, (float) centery_34).to((float) centerx_17, (float) centery_17).to((float) centerx_25, (float) centery_25);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_25();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_2() {
        int centerx_2 = find_center_x(186, 176);
        int centery_2 = find_center_y(45, 37);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        int centerx_22 = find_center_x(120, 133);
        int centery_22 = find_center_y(240, 223);
        int centerx_9 = find_center_x(100, 116);
        int centery_9 = find_center_y(240, 219);
        int centerx_31 = find_center_x(81, 94);
        int centery_31 = find_center_y(235, 219);
        int centerx_14 = find_center_x(65, 79);
        int centery_14 = find_center_y(224, 212);
        int centerx_20 = find_center_x(49, 61);
        int centery_20 = find_center_y(218, 205);
        int centerx_1 = find_center_x(57, 43);
        int centery_1 = find_center_y(209, 196);
        int centerx_33 = find_center_x(43, 23);
        int centery_33 = find_center_y(193, 177);
        int centerx_16 = find_center_x(21, 35);
        int centery_16 = find_center_y(178, 163);
        int centerx_24 = find_center_x(29, 10);
        int centery_24 = find_center_y(157, 146);
        int centerx_5 = find_center_x(27, 8);
        int centery_5 = find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY);
        int centerx_10 = find_center_x(26, 7);
        int centery_10 = find_center_y(113, 121);
        int centerx_23 = find_center_x(8, 29);
        int centery_23 = find_center_y(104, 93);
        int centerx_8 = find_center_x(37, 18);
        int centery_8 = find_center_y(91, 79);
        int centerx_30 = find_center_x(45, 23);
        int centery_30 = find_center_y(74, 57);
        int centerx_11 = find_center_x(54, 43);
        int centery_11 = find_center_y(53, 63);
        int centerx_36 = find_center_x(49, 63);
        int centery_36 = find_center_y(51, 31);
        int centerx_13 = find_center_x(81, 74);
        int centery_13 = find_center_y(35, 38);
        int centerx_27 = find_center_x(97, 84);
        int centery_27 = find_center_y(32, 17);
        int centerx_6 = find_center_x(115, 110);
        int centery_6 = find_center_y(28, 27);
        int centerx_34 = find_center_x(119, 131);
        int centery_34 = find_center_y(27, 11);
        int centerx_17 = find_center_x(136, 149);
        int centery_17 = find_center_y(31, 13);
        int centerx_25 = find_center_x(155, 166);
        int centery_25 = find_center_y(29, 23);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18).to((float) centerx_22, (float) centery_22).to((float) centerx_9, (float) centery_9).to((float) centerx_31, (float) centery_31).to((float) centerx_14, (float) centery_14).to((float) centerx_20, (float) centery_20).to((float) centerx_1, (float) centery_1).to((float) centerx_33, (float) centery_33).to((float) centerx_16, (float) centery_16).to((float) centerx_24, (float) centery_24).to((float) centerx_5, (float) centery_5).to((float) centerx_10, (float) centery_10).to((float) centerx_23, (float) centery_23).to((float) centerx_8, (float) centery_8).to((float) centerx_30, (float) centery_30).to((float) centerx_11, (float) centery_11).to((float) centerx_36, (float) centery_36).to((float) centerx_13, (float) centery_13).to((float) centerx_27, (float) centery_27).to((float) centerx_6, (float) centery_6).to((float) centerx_34, (float) centery_34).to((float) centerx_17, (float) centery_17).to((float) centerx_25, (float) centery_25).to((float) centerx_2, (float) centery_2);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_2();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    @Override
    public void onBackPressed() {
         if (timer_exact_value<=15){
             this.runOnUiThread(new Runnable() {
                 @Override
                 public void run() {
                     Toast.makeText(getApplicationContext(),"The game is allready started.please wait for a moment",Toast.LENGTH_LONG).show();
                 }
             });
             return;
         }else {
             SharedPreferences.Editor edit = GameScene.this.dataholder.edit();
             edit.clear();
             edit.commit();
             finish();
             super.onBackPressed();
         }

    }

    public void start_from_21() {
        int centerx_21 = find_center_x(190, 201);
        int centery_21 = find_center_y(55, 45);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        int centerx_22 = find_center_x(120, 133);
        int centery_22 = find_center_y(240, 223);
        int centerx_9 = find_center_x(100, 116);
        int centery_9 = find_center_y(240, 219);
        int centerx_31 = find_center_x(81, 94);
        int centery_31 = find_center_y(235, 219);
        int centerx_14 = find_center_x(65, 79);
        int centery_14 = find_center_y(224, 212);
        int centerx_20 = find_center_x(49, 61);
        int centery_20 = find_center_y(218, 205);
        int centerx_1 = find_center_x(57, 43);
        int centery_1 = find_center_y(209, 196);
        int centerx_33 = find_center_x(43, 23);
        int centery_33 = find_center_y(193, 177);
        int centerx_16 = find_center_x(21, 35);
        int centery_16 = find_center_y(178, 163);
        int centerx_24 = find_center_x(29, 10);
        int centery_24 = find_center_y(157, 146);
        int centerx_5 = find_center_x(27, 8);
        int centery_5 = find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY);
        int centerx_10 = find_center_x(26, 7);
        int centery_10 = find_center_y(113, 121);
        int centerx_23 = find_center_x(8, 29);
        int centery_23 = find_center_y(104, 93);
        int centerx_8 = find_center_x(37, 18);
        int centery_8 = find_center_y(91, 79);
        int centerx_30 = find_center_x(45, 23);
        int centery_30 = find_center_y(74, 57);
        int centerx_11 = find_center_x(54, 43);
        int centery_11 = find_center_y(53, 63);
        int centerx_36 = find_center_x(49, 63);
        int centery_36 = find_center_y(51, 31);
        int centerx_13 = find_center_x(81, 74);
        int centery_13 = find_center_y(35, 38);
        int centerx_27 = find_center_x(97, 84);
        int centery_27 = find_center_y(32, 17);
        int centerx_6 = find_center_x(115, 110);
        int centery_6 = find_center_y(28, 27);
        int centerx_34 = find_center_x(119, 131);
        int centery_34 = find_center_y(27, 11);
        int centerx_17 = find_center_x(136, 149);
        int centery_17 = find_center_y(31, 13);
        int centerx_25 = find_center_x(155, 166);
        int centery_25 = find_center_y(29, 23);
        int centerx_2 = find_center_x(186, 176);
        int centery_2 = find_center_y(45, 37);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18).to((float) centerx_22, (float) centery_22).to((float) centerx_9, (float) centery_9).to((float) centerx_31, (float) centery_31).to((float) centerx_14, (float) centery_14).to((float) centerx_20, (float) centery_20).to((float) centerx_1, (float) centery_1).to((float) centerx_33, (float) centery_33).to((float) centerx_16, (float) centery_16).to((float) centerx_24, (float) centery_24).to((float) centerx_5, (float) centery_5).to((float) centerx_10, (float) centery_10).to((float) centerx_23, (float) centery_23).to((float) centerx_8, (float) centery_8).to((float) centerx_30, (float) centery_30).to((float) centerx_11, (float) centery_11).to((float) centerx_36, (float) centery_36).to((float) centerx_13, (float) centery_13).to((float) centerx_27, (float) centery_27).to((float) centerx_6, (float) centery_6).to((float) centerx_34, (float) centery_34).to((float) centerx_17, (float) centery_17).to((float) centerx_25, (float) centery_25).to((float) centerx_2, (float) centery_2).to((float) centerx_21, (float) centery_21);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_21();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_4() {
        int centerx_4 = find_center_x(202, 214);
        int centery_4 = find_center_y(68, 58);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        int centerx_22 = find_center_x(120, 133);
        int centery_22 = find_center_y(240, 223);
        int centerx_9 = find_center_x(100, 116);
        int centery_9 = find_center_y(240, 219);
        int centerx_31 = find_center_x(81, 94);
        int centery_31 = find_center_y(235, 219);
        int centerx_14 = find_center_x(65, 79);
        int centery_14 = find_center_y(224, 212);
        int centerx_20 = find_center_x(49, 61);
        int centery_20 = find_center_y(218, 205);
        int centerx_1 = find_center_x(57, 43);
        int centery_1 = find_center_y(209, 196);
        int centerx_33 = find_center_x(43, 23);
        int centery_33 = find_center_y(193, 177);
        int centerx_16 = find_center_x(21, 35);
        int centery_16 = find_center_y(178, 163);
        int centerx_24 = find_center_x(29, 10);
        int centery_24 = find_center_y(157, 146);
        int centerx_5 = find_center_x(27, 8);
        int centery_5 = find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY);
        int centerx_10 = find_center_x(26, 7);
        int centery_10 = find_center_y(113, 121);
        int centerx_23 = find_center_x(8, 29);
        int centery_23 = find_center_y(104, 93);
        int centerx_8 = find_center_x(37, 18);
        int centery_8 = find_center_y(91, 79);
        int centerx_30 = find_center_x(45, 23);
        int centery_30 = find_center_y(74, 57);
        int centerx_11 = find_center_x(54, 43);
        int centery_11 = find_center_y(53, 63);
        int centerx_36 = find_center_x(49, 63);
        int centery_36 = find_center_y(51, 31);
        int centerx_13 = find_center_x(81, 74);
        int centery_13 = find_center_y(35, 38);
        int centerx_27 = find_center_x(97, 84);
        int centery_27 = find_center_y(32, 17);
        int centerx_6 = find_center_x(115, 110);
        int centery_6 = find_center_y(28, 27);
        int centerx_34 = find_center_x(119, 131);
        int centery_34 = find_center_y(27, 11);
        int centerx_17 = find_center_x(136, 149);
        int centery_17 = find_center_y(31, 13);
        int centerx_25 = find_center_x(155, 166);
        int centery_25 = find_center_y(29, 23);
        int centerx_2 = find_center_x(186, 176);
        int centery_2 = find_center_y(45, 37);
        int centerx_21 = find_center_x(190, 201);
        int centery_21 = find_center_y(55, 45);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18).to((float) centerx_22, (float) centery_22).to((float) centerx_9, (float) centery_9).to((float) centerx_31, (float) centery_31).to((float) centerx_14, (float) centery_14).to((float) centerx_20, (float) centery_20).to((float) centerx_1, (float) centery_1).to((float) centerx_33, (float) centery_33).to((float) centerx_16, (float) centery_16).to((float) centerx_24, (float) centery_24).to((float) centerx_5, (float) centery_5).to((float) centerx_10, (float) centery_10).to((float) centerx_23, (float) centery_23).to((float) centerx_8, (float) centery_8).to((float) centerx_30, (float) centery_30).to((float) centerx_11, (float) centery_11).to((float) centerx_36, (float) centery_36).to((float) centerx_13, (float) centery_13).to((float) centerx_27, (float) centery_27).to((float) centerx_6, (float) centery_6).to((float) centerx_34, (float) centery_34).to((float) centerx_17, (float) centery_17).to((float) centerx_25, (float) centery_25).to((float) centerx_2, (float) centery_2).to((float) centerx_21, (float) centery_21).to((float) centerx_4, (float) centery_4);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_4();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_19() {
        int centerx_19 = find_center_x(208, 222);
        int centery_19 = find_center_y(83, 68);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        int centerx_22 = find_center_x(120, 133);
        int centery_22 = find_center_y(240, 223);
        int centerx_9 = find_center_x(100, 116);
        int centery_9 = find_center_y(240, 219);
        int centerx_31 = find_center_x(81, 94);
        int centery_31 = find_center_y(235, 219);
        int centerx_14 = find_center_x(65, 79);
        int centery_14 = find_center_y(224, 212);
        int centerx_20 = find_center_x(49, 61);
        int centery_20 = find_center_y(218, 205);
        int centerx_1 = find_center_x(57, 43);
        int centery_1 = find_center_y(209, 196);
        int centerx_33 = find_center_x(43, 23);
        int centery_33 = find_center_y(193, 177);
        int centerx_16 = find_center_x(21, 35);
        int centery_16 = find_center_y(178, 163);
        int centerx_24 = find_center_x(29, 10);
        int centery_24 = find_center_y(157, 146);
        int centerx_5 = find_center_x(27, 8);
        int centery_5 = find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY);
        int centerx_10 = find_center_x(26, 7);
        int centery_10 = find_center_y(113, 121);
        int centerx_23 = find_center_x(8, 29);
        int centery_23 = find_center_y(104, 93);
        int centerx_8 = find_center_x(37, 18);
        int centery_8 = find_center_y(91, 79);
        int centerx_30 = find_center_x(45, 23);
        int centery_30 = find_center_y(74, 57);
        int centerx_11 = find_center_x(54, 43);
        int centery_11 = find_center_y(53, 63);
        int centerx_36 = find_center_x(49, 63);
        int centery_36 = find_center_y(51, 31);
        int centerx_13 = find_center_x(81, 74);
        int centery_13 = find_center_y(35, 38);
        int centerx_27 = find_center_x(97, 84);
        int centery_27 = find_center_y(32, 17);
        int centerx_6 = find_center_x(115, 110);
        int centery_6 = find_center_y(28, 27);
        int centerx_34 = find_center_x(119, 131);
        int centery_34 = find_center_y(27, 11);
        int centerx_17 = find_center_x(136, 149);
        int centery_17 = find_center_y(31, 13);
        int centerx_25 = find_center_x(155, 166);
        int centery_25 = find_center_y(29, 23);
        int centerx_2 = find_center_x(186, 176);
        int centery_2 = find_center_y(45, 37);
        int centerx_21 = find_center_x(190, 201);
        int centery_21 = find_center_y(55, 45);
        int centerx_4 = find_center_x(202, 214);
        int centery_4 = find_center_y(68, 58);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18).to((float) centerx_22, (float) centery_22).to((float) centerx_9, (float) centery_9).to((float) centerx_31, (float) centery_31).to((float) centerx_14, (float) centery_14).to((float) centerx_20, (float) centery_20).to((float) centerx_1, (float) centery_1).to((float) centerx_33, (float) centery_33).to((float) centerx_16, (float) centery_16).to((float) centerx_24, (float) centery_24).to((float) centerx_5, (float) centery_5).to((float) centerx_10, (float) centery_10).to((float) centerx_23, (float) centery_23).to((float) centerx_8, (float) centery_8).to((float) centerx_30, (float) centery_30).to((float) centerx_11, (float) centery_11).to((float) centerx_36, (float) centery_36).to((float) centerx_13, (float) centery_13).to((float) centerx_27, (float) centery_27).to((float) centerx_6, (float) centery_6).to((float) centerx_34, (float) centery_34).to((float) centerx_17, (float) centery_17).to((float) centerx_25, (float) centery_25).to((float) centerx_2, (float) centery_2).to((float) centerx_21, (float) centery_21).to((float) centerx_4, (float) centery_4).to((float) centerx_19, (float) centery_19);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_19();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_15() {
        int centerx_15 = find_center_x(213, 233);
        int centery_15 = find_center_y(98, 85);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        int zero_centerx = find_center_x(217, 238);
        int zer_centery = find_center_y(133, 116);
        int centerx_3 = find_center_x(230, 211);
        int centery_3 = find_center_y(162, 150);
        int centerx_35 = find_center_x(230, 211);
        int centery_35 = find_center_y(162, 150);
        int centerx_12 = find_center_x(192, 210);
        int centery_12 = find_center_y(201, 178);
        int centerx_28 = find_center_x(180, 196);
        int centery_28 = find_center_y(213, 195);
        int centerx_7 = find_center_x(166, 178);
        int centery_7 = find_center_y(211, 205);
        int centerx_29 = find_center_x(152, 170);
        int centery_29 = find_center_y(232, 215);
        int centerx_18 = find_center_x(139, 154);
        int centery_18 = find_center_y(236, 219);
        int centerx_22 = find_center_x(120, 133);
        int centery_22 = find_center_y(240, 223);
        int centerx_9 = find_center_x(100, 116);
        int centery_9 = find_center_y(240, 219);
        int centerx_31 = find_center_x(81, 94);
        int centery_31 = find_center_y(235, 219);
        int centerx_14 = find_center_x(65, 79);
        int centery_14 = find_center_y(224, 212);
        int centerx_20 = find_center_x(49, 61);
        int centery_20 = find_center_y(218, 205);
        int centerx_1 = find_center_x(57, 43);
        int centery_1 = find_center_y(209, 196);
        int centerx_33 = find_center_x(43, 23);
        int centery_33 = find_center_y(193, 177);
        int centerx_16 = find_center_x(21, 35);
        int centery_16 = find_center_y(178, 163);
        int centerx_24 = find_center_x(29, 10);
        int centery_24 = find_center_y(157, 146);
        int centerx_5 = find_center_x(27, 8);
        int centery_5 = find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY);
        int centerx_10 = find_center_x(26, 7);
        int centery_10 = find_center_y(113, 121);
        int centerx_23 = find_center_x(8, 29);
        int centery_23 = find_center_y(104, 93);
        int centerx_8 = find_center_x(37, 18);
        int centery_8 = find_center_y(91, 79);
        int centerx_30 = find_center_x(45, 23);
        int centery_30 = find_center_y(74, 57);
        int centerx_11 = find_center_x(54, 43);
        int centery_11 = find_center_y(53, 63);
        int centerx_36 = find_center_x(49, 63);
        int centery_36 = find_center_y(51, 31);
        int centerx_13 = find_center_x(81, 74);
        int centery_13 = find_center_y(35, 38);
        int centerx_27 = find_center_x(97, 84);
        int centery_27 = find_center_y(32, 17);
        int centerx_6 = find_center_x(115, 110);
        int centery_6 = find_center_y(28, 27);
        int centerx_34 = find_center_x(119, 131);
        int centery_34 = find_center_y(27, 11);
        int centerx_17 = find_center_x(136, 149);
        int centery_17 = find_center_y(31, 13);
        int centerx_25 = find_center_x(155, 166);
        int centery_25 = find_center_y(29, 23);
        int centerx_2 = find_center_x(186, 176);
        int centery_2 = find_center_y(45, 37);
        int centerx_21 = find_center_x(190, 201);
        int centery_21 = find_center_y(55, 45);
        int centerx_4 = find_center_x(202, 214);
        int centery_4 = find_center_y(68, 58);
        int centerx_19 = find_center_x(208, 222);
        int centery_19 = find_center_y(83, 68);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(217, 233), (float) find_center_y(115, 100)).to((float) zero_centerx, (float) zer_centery).to((float) centerx_26, (float) centery_26).to((float) centerx_3, (float) centery_3).to((float) centerx_35, (float) centery_35).to((float) centerx_12, (float) centery_12).to((float) centerx_28, (float) centery_28).to((float) centerx_7, (float) centery_7).to((float) centerx_29, (float) centery_29).to((float) centerx_18, (float) centery_18).to((float) centerx_22, (float) centery_22).to((float) centerx_9, (float) centery_9).to((float) centerx_31, (float) centery_31).to((float) centerx_14, (float) centery_14).to((float) centerx_20, (float) centery_20).to((float) centerx_1, (float) centery_1).to((float) centerx_33, (float) centery_33).to((float) centerx_16, (float) centery_16).to((float) centerx_24, (float) centery_24).to((float) centerx_5, (float) centery_5).to((float) centerx_10, (float) centery_10).to((float) centerx_23, (float) centery_23).to((float) centerx_8, (float) centery_8).to((float) centerx_30, (float) centery_30).to((float) centerx_11, (float) centery_11).to((float) centerx_36, (float) centery_36).to((float) centerx_13, (float) centery_13).to((float) centerx_27, (float) centery_27).to((float) centerx_6, (float) centery_6).to((float) centerx_34, (float) centery_34).to((float) centerx_17, (float) centery_17).to((float) centerx_25, (float) centery_25).to((float) centerx_2, (float) centery_2).to((float) centerx_21, (float) centery_21).to((float) centerx_4, (float) centery_4).to((float) centerx_19, (float) centery_19).to((float) centerx_15, (float) centery_15);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_15();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }

    public void start_from_32() {
        int centerx_32 = find_center_x(217, 233);
        int centery_32 = find_center_y(115, 100);
        int centerx_26 = find_center_x(216, 233);
        int centery_26 = find_center_y(147, 135);
        this.ball_path = new PathModifier.Path(38).to(this.ballSprite.getX(), this.ballSprite.getY()).to((float) find_center_x(217, 238), (float) find_center_y(133, 116)).to((float) centerx_26, (float) centery_26).to((float) find_center_x(230, 211), (float) find_center_y(162, 150)).to((float) find_center_x(230, 211), (float) find_center_y(162, 150)).to((float) find_center_x(192, 210), (float) find_center_y(201, 178)).to((float) find_center_x(180, 196), (float) find_center_y(213, 195)).to((float) find_center_x(166, 178), (float) find_center_y(211, 205)).to((float) find_center_x(152, 170), (float) find_center_y(232, 215)).to((float) find_center_x(139, 154), (float) find_center_y(236, 219)).to((float) find_center_x(120, 133), (float) find_center_y(240, 223)).to((float) find_center_x(100, 116), (float) find_center_y(240, 219)).to((float) find_center_x(81, 94), (float) find_center_y(235, 219)).to((float) find_center_x(65, 79), (float) find_center_y(224, 212)).to((float) find_center_x(49, 61), (float) find_center_y(218, 205)).to((float) find_center_x(57, 43), (float) find_center_y(209, 196)).to((float) find_center_x(43, 23), (float) find_center_y(193, 177)).to((float) find_center_x(21, 35), (float) find_center_y(178, 163)).to((float) find_center_x(29, 10), (float) find_center_y(157, 146)).to((float) find_center_x(27, 8), (float) find_center_y(173, TransportMediator.KEYCODE_MEDIA_PLAY)).to((float) find_center_x(26, 7), (float) find_center_y(113, 121)).to((float) find_center_x(8, 29), (float) find_center_y(104, 93)).to((float) find_center_x(37, 18), (float) find_center_y(91, 79)).to((float) find_center_x(45, 23), (float) find_center_y(74, 57)).to((float) find_center_x(54, 43), (float) find_center_y(53, 63)).to((float) find_center_x(49, 63), (float) find_center_y(51, 31)).to((float) find_center_x(81, 74), (float) find_center_y(35, 38)).to((float) find_center_x(97, 84), (float) find_center_y(32, 17)).to((float) find_center_x(115, 110), (float) find_center_y(28, 27)).to((float) find_center_x(119, 131), (float) find_center_y(27, 11)).to((float) find_center_x(136, 149), (float) find_center_y(31, 13)).to((float) find_center_x(155, 166), (float) find_center_y(29, 23)).to((float) find_center_x(186, 176), (float) find_center_y(45, 37)).to((float) find_center_x(190, 201), (float) find_center_y(55, 45)).to((float) find_center_x(202, 214), (float) find_center_y(68, 58)).to((float) find_center_x(208, 222), (float) find_center_y(83, 68)).to((float) find_center_x(213, 233), (float) find_center_y(98, 85)).to((float) centerx_32, (float) centery_32);
        this.ball_path_modifer = new PathModifier((float) this.timer, this.ball_path);
        this.ball_path_modifer.setPathModifierListener(new PathModifier.IPathModifierListener() {
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                GameScene.this.start_from_32();
            }
        });
        this.ballSprite.registerEntityModifier(this.ball_path_modifer);
    }
    public void start_place_bit() {
        this.topSprite.detachChild(this.placebitentity);
        this.topSprite.attachChild(this.placebitentity);
        this.place_bit_Path = new PathModifier.Path(2).to(this.textXpos, this.textYpos).to(500.0f, this.textYpos);
        this.place_bit_modifier = new PathModifier(3.0f, this.place_bit_Path);
        this.placebitentity.registerEntityModifier(this.place_bit_modifier);
    }

    public void start_no_more_bit() {
        this.topSprite.detachChild(this.nomorebitEntity);
        this.topSprite.attachChild(this.nomorebitEntity);
        this.no_more_bit_path = new PathModifier.Path(2).to(this.textXpos, this.textYpos).to(400.0f, this.textYpos);
        this.no_more_bit_path_modifier = new PathModifier(3.0f, this.no_more_bit_path);
        this.nomorebitEntity.registerEntityModifier(this.no_more_bit_path_modifier);
    }

    public int getMusic_current_index() {
        return this.music_current_index;
    }

    public void setMusic_current_index(int music_current_index) {
        this.music_current_index = music_current_index;
    }
    public float getchipsNextPositon(Sprite r) {
        return (r.getX() + r.getWidth()) + 10.0f;
    }
    public int getSelectedBitValue() {
        return this.selectedBitValue;
    }

    public void setSelectedBitValue(int selectedBitValue) {
        this.selectedBitValue = selectedBitValue;
    }
    public void update_dataholder() {
        this.total_credit.setText(Integer.toString(Integer.parseInt(this.total_credit.getText().toString().trim()) + Integer.parseInt(this.bitSprite.getText().toString().trim())));
        this.bitSprite.setText("00");
        getSharedPreferences("table_number", 0).edit().clear().commit();
        this.tableSprite.detachChildren();
    }

    public int find_center_x(int x1, int x2) {
        return (x1 + x2) / 2;
    }

    public int find_center_y(int y1, int y2) {
        return (y1 + y2) / 2;
    }

    public void attach_ball(int xp, int yp) {
        this.ballSprite = new Sprite((float) xp, (float) yp, this.ballRegion, getVertexBufferObjectManager());
        this.wheelSprite.attachChild(this.ballSprite);
    }

    public void addBitOnTable(float x, float y, String value) {
        if (value.equals("5")) {
            this.sprite5 = new Sprite(x, y, this.bitRegion, this.mEngine.getVertexBufferObjectManager());
            this.tableSprite.attachChild(this.sprite5);
            this.bitHolder.add(this.sprite5);
        } else if (value.equals("10")) {
            Sprite sprite10 = new Sprite(x, y, this.bitRegion10, this.mEngine.getVertexBufferObjectManager());
            this.tableSprite.attachChild(sprite10);
            this.bitHolder.add(sprite10);
        } else if (value.equals("50")) {
            Sprite sprite50 = new Sprite(x, y, this.bitRegion50, this.mEngine.getVertexBufferObjectManager());
            this.tableSprite.attachChild(sprite50);
            this.bitHolder.add(sprite50);
        } else if (value.equals("100")) {
            Sprite sprite100 = new Sprite(x, y, this.bitRegion100, this.mEngine.getVertexBufferObjectManager());
            this.tableSprite.attachChild(sprite100);
            this.bitHolder.add(sprite100);
        } else if (value.equals("500")) {
            Sprite sprite500 = new Sprite(x, y, this.bitRegion500, this.mEngine.getVertexBufferObjectManager());
            this.tableSprite.attachChild(sprite500);
            this.bitHolder.add(sprite500);
        }
    }
    public void add_total_bit(String bitString) {
        int total_bit = Integer.parseInt(this.bitSprite.getText());
        int bit_value = getSelectedBitValue();
        int total_credits = get_total_credit();
        int total_hack = total_bit + bit_value;
        int min_credit = total_credits - bit_value;
        this.is_playing_chips_set = true;
        if (total_credits >= bit_value) {
            this.is_bit_value_correct = true;
            total_credits -= bit_value;
            this.bitSprite.setText(Integer.toString(total_bit + bit_value));
            this.total_credit.setText(Integer.toString(total_credits));
            //this.gamers.setAmount(Integer.toString(total_credits));
            return;
        }
        this.is_bit_value_correct = false;
        //show_error("your balance is not enough to reach on " + (total_bit + bit_value) + " chips");
    }
    public int getWinningNumberValue() {
        return this.winningNumberValue;
    }

    public void setWinningNumberValue(int value) {
        this.winningNumberValue = value;
    }

    public int get_total_credit() {
        return Integer.parseInt(this.total_credit.getText());
    }
    public void storeData(String number, String value) {
        SharedPreferences.Editor editor = this.dataholder.edit();
        editor.putString(number, value);
        editor.apply();
    }
    public void send_table_bit(String table_no, String position) {
        //new GameDisplay.Add_table_bit().execute(new String[]{this.email, table_no, position});
    }

    public void eleventhsixline(int x, int y, String bitString) {
        if (!this.iseleventhsixlineAdded) {
            addBitOnTable((float) x, (float) y, bitString);
            this.iseleventhsixlineAdded = true;
        }
    }

    public void tenthsixline(int x, int y, String bitString) {
        if (!this.istenthsixlineAdded) {
            addBitOnTable((float) x, (float) y, bitString);
            this.istenthsixlineAdded = true;
        }
    }

    public void eighthsixline(int x, int y, String bitString) {
        if (!this.iseighthsixlineAdded) {
            addBitOnTable((float) x, (float) y, bitString);
            this.iseighthsixlineAdded = true;
        }
    }

    public void ninthsixline(int x, int y, String bitString) {
        if (!this.isninthsixlineAdded) {
            addBitOnTable((float) x, (float) y, bitString);
            this.isninthsixlineAdded = true;
        }
    }

    public void seventhsixline(int x, int y, String bitString) {
        if (!this.isseventhsixlineAdded) {
            addBitOnTable((float) x, (float) y, bitString);
            this.isseventhsixlineAdded = true;
        }
    }

    public void firstsixline(int x, int y, String bitString) {
        if (!this.isfirstsixlineAdded) {
            addBitOnTable((float) x, (float) y, bitString);
            this.isfirstsixlineAdded = true;
        }
    }

    public void secondsixline(int x, int y, String bitString) {
        if (!this.issecondsixlineAdded) {
            addBitOnTable((float) x, (float) y, bitString);
            this.issecondsixlineAdded = true;
        }
    }

    public void thirdsixline(int x, int y, String bitString) {
        if (!this.isthirdsixlineAdded) {
            addBitOnTable((float) x, (float) y, bitString);
            this.isthirdsixlineAdded = true;
        }
    }

    public void fifthsixline(int x, int y, String bitString) {
        if (!this.isfifthsixlineAdded) {
            addBitOnTable((float) x, (float) y, bitString);
            this.isfifthsixlineAdded = true;
        }
    }

    public void fourthsixline(int x, int y, String bitString) {
        if (!this.isfourthsixlineAdded) {
            addBitOnTable((float) x, (float) y, bitString);
            this.isfourthsixlineAdded = true;
        }
    }

    public void sixthsixline(int x, int y, String bitString) {
        if (!this.issixthsixlineAdded) {
            addBitOnTable((float) x, (float) y, bitString);
            this.issixthsixlineAdded = true;
        }
    }


    public void addZero(int x, int y, String bitString) {
        if (!this.zeroValueAdded) {
            addBitOnTable((float) 23, (float) 168, bitString);
            this.zeroValueAdded = true;
        }
    }

    public void addbtZeroAndThree(int x, int y, String bitString) {
        if (!this.btzeroandthree) {
            addBitOnTable((float) x, (float) y, bitString);
            this.btzeroandthree = true;
        }
    }

    public void addbtzerothreetwo(int x, int y, String bitString) {
        if (!this.btzerothreetwo) {
            addBitOnTable((float) x, (float) y, bitString);
            this.btzerothreetwo = true;
        }
    }

    public void addthree(int x, int y, String bitstring) {
        if (!this.threeValueAdded) {
            addBitOnTable((float) x, (float) y, bitstring);
            this.threeValueAdded = true;
        }
    }

    public void addTwo(int x, int y, String bitString) {
        if (!this.twoIsAdded) {
            addBitOnTable((float) x, (float) y, bitString);
            this.twoIsAdded = true;
        }
    }

    public void addbtZeroandtwo(int x, int y, String bitString) {
        if (!this.btzeroandtwo) {
            addBitOnTable((float) x, (float) y, bitString);
            this.btzeroandtwo = true;
        }
    }

    public void addbttwoandone(int x, int y, String bitString) {
        if (!this.btzerotwoandone) {
            addBitOnTable((float) x, (float) y, bitString);
            this.btzerotwoandone = true;
        }
    }

    public void addOne(int x, int y, String bitString) {
        if (!this.oneValueIsAdded) {
            addBitOnTable((float) x, (float) y, bitString);
            this.oneValueIsAdded = true;
        }
    }

    public void addbtzerontwoand18(int x, int y, String bitString) {
        if (!this.btzerooneand18) {
            addBitOnTable((float) x, (float) y, bitString);
            this.btzerooneand18 = true;
        }
    }

    public void addbtoneandzero(int x, int y, String bitString) {
        if (!this.btzeronandone) {
            addBitOnTable((float) x, (float) y, bitString);
            this.btzeronandone = true;
        }
    }

    public void addbt18and1(int x, int y, String bitString) {
        if (!this.bt18and1) {
            addBitOnTable((float) x, (float) y, bitString);
            this.bt18and1 = true;
        }
    }

    public void add8(int x, int y, String bitString) {
        if (!this.add8) {
            addBitOnTable((float) x, (float) y, bitString);
            this.add8 = true;
        }
    }

    public void addeven(int x, int y, String bitString) {
        if (!this.eveisadded) {
            addBitOnTable((float) x, (float) y, bitString);
            this.eveisadded = true;
        }
    }

    public void addRed(int x, int y, String bitString) {
        if (!this.isRedAdded) {
            addBitOnTable((float) x, (float) y, bitString);
            this.isRedAdded = true;
        }
    }

    public void addBlack(int x, int y, String bitString) {
        if (!this.isBlackAdded) {
            addBitOnTable((float) x, (float) y, bitString);
            this.isBlackAdded = true;
        }
    }

    public void addOdd(int x, int y, String bitString) {
        if (!this.isOddAdded) {
            addBitOnTable((float) x, (float) y, bitString);
            this.isOddAdded = true;
        }
    }

    public void add19bt36(int x, int y, String bitString) {
        if (!this.is19bt36Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is19bt36Added = true;
        }
    }

    public void add1bt18(int x, int y, String bitString) {
        if (!this.is1bt18added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is1bt18added = true;
        }
    }

    public void add2to1below(int x, int y, String bitString) {
        if (!this.is2to1Addedbelow) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is2to1Addedbelow = true;
        }
    }

    public void add2to1middle(int x, int y, String bitString) {
        if (!this.is2to1AddedMiddle) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is2to1AddedMiddle = true;
        }
    }

    public void add2to1Top(int x, int y, String bitString) {
        if (!this.is2to1AddedTop) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is2to1AddedTop = true;
        }
    }

    public void addsix(int x, int y, String bitString) {
        if (!this.isSixAdded) {
            addBitOnTable((float) x, (float) y, bitString);
            this.isSixAdded = true;
        }
    }

    public void addfive(int x, int y, String bitString) {
        if (!this.isFiveAdded) {
            addBitOnTable((float) x, (float) y, bitString);
            this.isFiveAdded = true;
        }
    }

    public void add6to5(int x, int y, String bitString) {
        if (!this.is6to5Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is6to5Added = true;
        }
    }

    public void addfour(int x, int y, String bitString) {
        if (!this.isfourAdded) {
            addBitOnTable((float) x, (float) y, bitString);
            this.isfourAdded = true;
        }
    }

    public void addnine(int x, int y, String bitString) {
        if (!this.isNineAdded) {
            addBitOnTable((float) x, (float) y, bitString);
            this.isNineAdded = true;
        }
    }

    public void addeight(int x, int y, String bitString) {
        if (!this.isEightAdded) {
            addBitOnTable((float) x, (float) y, bitString);
            this.isEightAdded = true;
        }
    }

    public void addseven(int x, int y, String bitString) {
        if (!this.isSevenAdded) {
            addBitOnTable((float) x, (float) y, bitString);
            this.isSevenAdded = true;
        }
    }

    public void add12(int x, int y, String bitString) {
        if (!this.istwelveAdded) {
            addBitOnTable((float) x, (float) y, bitString);
            this.istwelveAdded = true;
        }
    }

    public void add11(int x, int y, String bitString) {
        if (!this.is11Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is11Added = true;
        }
    }

    public void add10(int x, int y, String bitString) {
        if (!this.is10Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is10Added = true;
        }
    }

    public void add15(int x, int y, String bitString) {
        if (!this.is15Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is15Added = true;
        }
    }

    public void addsplit_32(int x, int y, String bitString) {
        if (!this.issplit32Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.issplit32Added = true;
        }
    }

    public void addsplit_69(int x, int y, String bitString) {
        if (!this.issplit69Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.issplit69Added = true;
        }
    }

    public void addsplit_912(int x, int y, String bitString) {
        if (!this.issplit912Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.issplit912Added = true;
        }
    }

    private void addsplit_1215(int xp, int yp, String bitString) {
        if (!this.issplit1215Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit1215Added = true;
        }
    }

    private void addsplit_1518(int xp, int yp, String bitString) {
        if (!this.issplit1518Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit1518Added = true;
        }
    }

    private void addsplit_1821(int xp, int yp, String bitString) {
        if (!this.issplit1821Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit1821Added = true;
        }
    }

    private void addsplit_2124(int xp, int yp, String bitString) {
        if (!this.issplit2124Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit2124Added = true;
        }
    }

    private void addsplit_2427(int xp, int yp, String bitString) {
        if (!this.issplit2427Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit2427Added = true;
        }
    }

    private void addsplit_2730(int xp, int yp, String bitString) {
        if (!this.issplit2730Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit2730Added = true;
        }
    }

    private void addsplit_3033(int xp, int yp, String bitString) {
        if (!this.issplit3033Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit3033Added = true;
        }
    }

    private void addsplit_3336(int xp, int yp, String bitString) {
        if (!this.issplit3336Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit3336Added = true;
        }
    }

    private void addsplit_25(int xp, int yp, String bitString) {
        if (!this.issplit25Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit25Added = true;
        }
    }

    private void addsplit_58(int xp, int yp, String bitString) {
        if (!this.issplit58Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit58Added = true;
        }
    }

    private void addsplit_811(int xp, int yp, String bitString) {
        if (!this.issplit811Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit811Added = true;
        }
    }

    private void addsplit_1114(int xp, int yp, String bitString) {
        if (!this.issplit1114Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit1114Added = true;
        }
    }

    private void addsplit_1417(int xp, int yp, String bitString) {
        if (!this.issplit1417Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit1417Added = true;
        }
    }

    private void addsplit_1720(int xp, int yp, String bitString) {
        if (!this.issplit1720Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit1720Added = true;
        }
    }

    private void addsplit_2023(int xp, int yp, String bitString) {
        if (!this.issplit2023Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit2023Added = true;
        }
    }

    private void addsplit_2326(int xp, int yp, String bitString) {
        if (!this.issplit2326Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit2326Added = true;
        }
    }

    private void addsplit_2629(int xp, int yp, String bitString) {
        if (!this.issplit2629Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit2629Added = true;
        }
    }

    private void addsplit_2932(int xp, int yp, String bitString) {
        if (!this.issplit2932Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit2932Added = true;
        }
    }

    private void addsplit_3235(int xp, int yp, String bitString) {
        if (!this.issplit3235Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit3235Added = true;
        }
    }

    private void addsplit_14(int xp, int yp, String bitString) {
        if (!this.issplit14Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit14Added = true;
        }
    }

    private void addsplit_47(int xp, int yp, String bitString) {
        if (!this.issplit47Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit47Added = true;
        }
    }

    private void addsplit_710(int xp, int yp, String bitString) {
        if (!this.issplit710Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit710Added = true;
        }
    }

    private void addsplit_1013(int xp, int yp, String bitString) {
        if (!this.issplit1013Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit1013Added = true;
        }
    }

    private void addsplit_1316(int xp, int yp, String bitString) {
        if (!this.issplit1316Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit1316Added = true;
        }
    }

    private void addsplit_1619(int xp, int yp, String bitString) {
        if (!this.issplit1619Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit1619Added = true;
        }
    }

    private void addsplit_1922(int xp, int yp, String bitString) {
        if (!this.issplit1922Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit1922Added = true;
        }
    }

    private void addsplit_2225(int xp, int yp, String bitString) {
        if (!this.issplit2225Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit2225Added = true;
        }
    }

    private void addsplit_2528(int xp, int yp, String bitString) {
        if (!this.issplit2528Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit2528Added = true;
        }
    }

    private void addsplit_2831(int xp, int yp, String bitString) {
        if (!this.issplit2831Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit2831Added = true;
        }
    }

    private void addsplit_3134(int xp, int yp, String bitString) {
        if (!this.issplit3134Added) {
            addBitOnTable((float) xp, (float) yp, bitString);
            this.issplit3134Added = true;
        }
    }

    public void add14(int x, int y, String bitString) {
        if (!this.is14Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is14Added = true;
        }
    }

    public void add13(int x, int y, String bitString) {
        if (!this.is13Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is13Added = true;
        }
    }

    public void add18(int x, int y, String bitString) {
        if (!this.is18Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is18Added = true;
        }
    }

    public void add17(int x, int y, String bitString) {
        if (!this.is17Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is17Added = true;
        }
    }

    public void add16(int x, int y, String bitString) {
        if (!this.is16Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is16Added = true;
        }
    }

    public void add19(int x, int y, String bitString) {
        if (!this.is19Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is19Added = true;
        }
    }

    public void add20(int x, int y, String bitString) {
        if (!this.is20Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is20Added = true;
        }
    }

    public void add21(int x, int y, String bitString) {
        if (!this.is21Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is21Added = true;
        }
    }

    public void add22(int x, int y, String bitString) {
        if (!this.is22Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is22Added = true;
        }
    }

    public void add23(int x, int y, String bitString) {
        if (!this.is23Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is23Added = true;
        }
    }

    public void add24(int x, int y, String bitString) {
        if (!this.is24Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is24Added = true;
        }
    }

    public void add25(int x, int y, String bitString) {
        if (!this.is25Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is25Added = true;
        }
    }

    public void add26(int x, int y, String bitString) {
        if (!this.is26Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is26Added = true;
        }
    }

    public void add27(int x, int y, String bitString) {
        if (!this.is27Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is27Added = true;
        }
    }

    public void add28(int x, int y, String bitString) {
        if (!this.is28Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is28Added = true;
        }
    }

    public void add29(int x, int y, String bitString) {
        if (!this.is29Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is29Added = true;
        }
    }

    public void add30(int x, int y, String bitString) {
        if (!this.is30Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is30Added = true;
        }
    }

    public void add31(int x, int y, String bitString) {
        if (!this.is31Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is31Added = true;
        }
    }

    public void add32(int x, int y, String bitString) {
        if (!this.is32Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is32Added = true;
        }
    }

    public void add33(int x, int y, String bitString) {
        if (!this.is33Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is33Added = true;
        }
    }

    public void add34(int x, int y, String bitString) {
        if (!this.is34Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is34Added = true;
        }
    }

    public void add35(int x, int y, String bitString) {
        if (!this.is35Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is35Added = true;
        }
    }

    public void add36(int x, int y, String bitString) {
        if (!this.is36Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is36Added = true;
        }
    }

    public void add3625(int x, int y, String bitString) {
        if (!this.is3625Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is3625Added = true;
        }
    }

    public void add6958(int x, int y, String bitString) {
        if (!this.is6958Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is6958Added = true;
        }
    }

    public void add912811(int x, int y, String bitString) {
        if (!this.is12811Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is12811Added = true;
        }
    }

    public void add912151114(int x, int y, String bitString) {
        if (!this.is12151114Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is12151114Added = true;
        }
    }

    public void add912181417(int x, int y, String bitString) {
        if (!this.is121814174Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is121814174Added = true;
        }
    }

    public void add15181417(int x, int y, String bitString) {
        if (!this.is151814174Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is151814174Added = true;
        }
    }

    public void add18211720(int x, int y, String bitString) {
        if (!this.is18211720Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is18211720Added = true;
        }
    }

    public void add21242023(int x, int y, String bitString) {
        if (!this.is21242023Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is21242023Added = true;
        }
    }

    public void add24272326(int x, int y, String bitString) {
        if (!this.is24271326Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is24271326Added = true;
        }
    }

    public void add27302629(int x, int y, String bitString) {
        if (!this.is27302629Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is27302629Added = true;
        }
    }

    public void add30332932(int x, int y, String bitString) {
        if (!this.is30332932Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is30332932Added = true;
        }
    }

    public void add33363235(int x, int y, String bitString) {
        if (!this.is33363235Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is33363235Added = true;
        }
    }

    public void add2514(int x, int y, String bitString) {
        if (!this.is2514Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is2514Added = true;
        }
    }

    public void add5847(int x, int y, String bitString) {
        if (!this.is5847Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is5847Added = true;
        }
    }

    public void add811710(int x, int y, String bitString) {
        if (!this.is811710Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is811710Added = true;
        }
    }

    public void add11141013(int x, int y, String bitString) {
        if (!this.is11141013Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is11141013Added = true;
        }
    }

    public void add14171316(int x, int y, String bitString) {
        if (!this.is14171316Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is14171316Added = true;
        }
    }

    public void add17201619(int x, int y, String bitString) {
        if (!this.is17201619Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is17201619Added = true;
        }
    }

    public void add20231922(int x, int y, String bitString) {
        if (!this.is20231922Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is20231922Added = true;
        }
    }

    public void add23262225(int x, int y, String bitString) {
        if (!this.is23262225Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is23262225Added = true;
        }
    }

    public void add26292528(int x, int y, String bitString) {
        if (!this.is26292528Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is26292528Added = true;
        }
    }

    public void add29322831(int x, int y, String bitString) {
        if (!this.is29322831Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is29322831Added = true;
        }
    }

    public void add32353134(int x, int y, String bitString) {
        if (!this.is32353133Added) {
            addBitOnTable((float) x, (float) y, bitString);
            this.is32353133Added = true;
        }
    }


}
