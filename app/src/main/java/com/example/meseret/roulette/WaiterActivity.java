package com.example.meseret.roulette;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class WaiterActivity extends SimpleBaseGameActivity {
    private final int CAMERA_HEIGHT = 480;
    private final int CAMERA_WIDTH = 800;
    private SpriteBackground background_image;
    private Font big_font;
    private BitmapTextureAtlas bottomBg;
    private ITextureRegion bottomBgRegion;
    private Sprite bottomSprite;
    private int game_timer = 0;
    private Text info2;
    private BitmapTextureAtlas mBackgroundTexture;
    private Camera mCamera;
    private Font mFont;
    private Scene mScene;
    private String name;
    private Text playerName;
    private BitmapTextureAtlas profileAtlas;
    private ITextureRegion profileRegion;
    private ITextureRegion region;
    private BitmapTextureAtlas topAtlas;
    private ITextureRegion topRegion;
    private Sprite top_sprite;
    private Text waiting_text;
    private Intent timer_intent;

    public EngineOptions onCreateEngineOptions() {
        this.mCamera = new Camera(0.0f, 0.0f, 800.0f, 480.0f);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        EngineOptions options = new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR, new FillResolutionPolicy(), this.mCamera);
        options.getAudioOptions().setNeedsMusic(true);
        options.getAudioOptions().setNeedsSound(true);
        return options;
    }

    protected void onCreateResources() throws IOException {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        BuildableBitmapTextureAtlas atlas = new BuildableBitmapTextureAtlas(this.mEngine.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        this.region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlas, (Context) this, "cards_texture.jpg");
        try {
            atlas.build(new BlackPawnTextureAtlasBuilder(0, 0, 0));
            atlas.load();
        } catch (Exception e) {
        }
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        this.topAtlas = new BitmapTextureAtlas(getTextureManager(), 1024, 52);
        this.topRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.topAtlas, (Context) this, "top_back.png", 0, 0);
        this.topAtlas.load();
        this.profileAtlas = new BitmapTextureAtlas(getTextureManager(), 42, 42);
        this.profileRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.profileAtlas, (Context) this, "profile.png", 0, 0);
        this.profileAtlas.load();
        this.bottomBg = new BitmapTextureAtlas(getTextureManager(), 1024, 52);
        this.bottomBgRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bottomBg, (Context) this, "top_back.png", 0, 0);
        this.bottomBg.load();
        this.mFont = FontFactory.createFromAsset(this.mEngine.getFontManager(), this.mEngine.getTextureManager(), 256, 256, TextureOptions.BILINEAR, getAssets(), "Aramis Italic.ttf", 26.0f, true, Color.WHITE_ABGR_PACKED_INT);
        this.mFont.load();
        this.big_font = FontFactory.createFromAsset(this.mEngine.getFontManager(), this.mEngine.getTextureManager(), 258, 258, TextureOptions.BILINEAR, getAssets(), "Aramis Italic.ttf", 40.0f, true, Color.WHITE_ABGR_PACKED_INT);
        this.big_font.load();
    }

    protected Scene onCreateScene() {
        this.mScene = new Scene();
        /*Intent timer_intent = getIntent();
        final String content = timer_intent.getStringExtra("content");
        this.game_timer = timer_intent.getIntExtra("timer", 0);
        name=timer_intent.getStringExtra("user_name");

        this.top_sprite = new Sprite(this.topRegion.getWidth() / 2.0f, 480.0f - (this.topRegion.getHeight() / 2.0f), this.topRegion, this.mEngine.getVertexBufferObjectManager());
        this.mScene.attachChild(this.top_sprite);
        Sprite sprite = new Sprite(20.0f, 25.0f, this.profileRegion, this.mEngine.getVertexBufferObjectManager());
        this.playerName = new Text(sprite.getWidth() + 40.0f, 15.0f, this.mFont, this.name, this.mEngine.getVertexBufferObjectManager());
        this.playerName.setColor(android.graphics.Color.rgb(234, 197, 137));
        this.top_sprite.attachChild(this.playerName);
        this.top_sprite.attachChild(sprite);
        this.waiting_text = new Text(370.0f, 350.0f, this.big_font, "The Game is already started.", this.mEngine.getVertexBufferObjectManager());
        this.mScene.attachChild(this.waiting_text);
        this.info2 = new Text(this.waiting_text.getX(), this.waiting_text.getY() - 50.0f, this.mFont, "Please wait for " + this.game_timer + "seconds", this.mEngine.getVertexBufferObjectManager());
        this.mScene.attachChild(this.info2);
        this.bottomSprite = new Sprite(this.bottomBgRegion.getWidth() / 2.0f, this.bottomBgRegion.getHeight() / 2.0f, this.bottomBgRegion, this.mEngine.getVertexBufferObjectManager());
        this.mScene.attachChild(this.bottomSprite);
        this.mScene.registerUpdateHandler(new TimerHandler(1.0f, true, new ITimerCallback() {
            public void onTimePassed(TimerHandler pTimerHandler) {
                WaiterActivity.this.game_timer = WaiterActivity.this.game_timer - 1;
                WaiterActivity.this.info2.setText("Please wait for " + WaiterActivity.this.game_timer + " seconds");
                if (WaiterActivity.this.game_timer == 0) {
                    Intent intent = new Intent(WaiterActivity.this, CustomProfile.class);
                    WaiterActivity.this.startActivity(intent);
                    WaiterActivity.this.finish();
                }
                Log.d("timer", "" + WaiterActivity.this.game_timer);
            }
        }));*/

         timer_intent = getIntent();
        final String content = timer_intent.getStringExtra("content");
        this.game_timer = timer_intent.getIntExtra("timer", 0);
        name=timer_intent.getStringExtra("user_name");
        this.top_sprite = new Sprite(this.topRegion.getWidth() / 2.0f, 480.0f - (this.topRegion.getHeight() / 2.0f), this.topRegion, this.mEngine.getVertexBufferObjectManager());
        Sprite sprite = new Sprite(20.0f, 25.0f, this.profileRegion, this.mEngine.getVertexBufferObjectManager());
        this.playerName = new Text(sprite.getWidth() + 40.0f, 15.0f, this.mFont,name, this.mEngine.getVertexBufferObjectManager());
        this.playerName.setColor(android.graphics.Color.rgb(234, 197, 137));
        this.top_sprite.attachChild(this.playerName);

        this.waiting_text = new Text(370.0f, 350.0f, this.big_font, "The Game is already started.", this.mEngine.getVertexBufferObjectManager());
        this.mScene.attachChild(this.waiting_text);
        this.info2 = new Text(this.waiting_text.getX(), this.waiting_text.getY() - 50.0f, this.mFont, "Please wait for " + this.game_timer + "seconds", this.mEngine.getVertexBufferObjectManager());
        this.mScene.attachChild(this.info2);

        this.bottomSprite = new Sprite(this.bottomBgRegion.getWidth() / 2.0f, this.bottomBgRegion.getHeight() / 2.0f, this.bottomBgRegion, this.mEngine.getVertexBufferObjectManager());
        this.mScene.attachChild(this.bottomSprite);
        this.mScene.registerUpdateHandler(new TimerHandler(1.0f, true, new ITimerCallback() {
            public void onTimePassed(TimerHandler pTimerHandler) {
                WaiterActivity.this.game_timer = WaiterActivity.this.game_timer - 1;
                WaiterActivity.this.info2.setText("Please wait for " + WaiterActivity.this.game_timer + " seconds");
                if (WaiterActivity.this.game_timer == 0) {
                    Intent intent = new Intent(WaiterActivity.this, GameScene.class);
                    intent.putExtra("user_name",timer_intent.getStringExtra("user_name"));
                    intent.putExtra("email",timer_intent.getStringExtra("email"));
                    intent.putExtra("amount",timer_intent.getStringExtra("amount"));
                    intent.putExtra("token",timer_intent.getStringExtra("token"));
                    intent.putExtra("referral_code",timer_intent.getStringExtra("referral_code"));
                    WaiterActivity.this.startActivity(intent);
                    WaiterActivity.this.finish();
                }
                Log.d("timer", "" + WaiterActivity.this.game_timer);
            }
        }));

        this.mScene.attachChild(this.top_sprite);
        this.mScene.setBackground(new SpriteBackground(new Sprite(this.region.getWidth() / 2.0f, this.region.getHeight() / 2.0f, this.region, getVertexBufferObjectManager())));
        return this.mScene;
    }
}