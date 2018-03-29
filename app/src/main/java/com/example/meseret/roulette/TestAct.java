package com.example.meseret.roulette;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.IGameInterface;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import java.io.IOException;

public class TestAct extends SimpleBaseGameActivity  {
  private Camera mCamera;
    private ITextureRegion region;
    private Scene mScene;


    @Override
    public EngineOptions onCreateEngineOptions() {
        this.mCamera = new Camera(0.0f, 0.0f, 800.0f, 480.0f);
        //setRequestedOrientation(0);
        EngineOptions options = new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR, new FillResolutionPolicy(), this.mCamera);
        options.getAudioOptions().setNeedsMusic(true);
        options.getAudioOptions().setNeedsSound(true);
        return options;
    }

    @Override
    protected void onCreateResources() throws IOException {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        BuildableBitmapTextureAtlas atlas = new BuildableBitmapTextureAtlas(this.mEngine.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        this.region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlas, (Context) this, "login_back.png");
        try {
            atlas.build(new BlackPawnTextureAtlasBuilder(0, 0, 0));
            atlas.load();
        } catch (Exception e) {
        }
    }

    @Override
    protected Scene onCreateScene() {
        mScene=new Scene();

        this.mScene.setBackground(new SpriteBackground(new Sprite(this.region.getWidth() / 2.0f, this.region.getHeight() / 2.0f, this.region, getVertexBufferObjectManager())));
        return this.mScene;
    }
}
