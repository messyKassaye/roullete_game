package components;


import com.example.meseret.roulette.GameDisplay;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.ITextureRegion;

public class BitButton extends Sprite {
    private float xpos;
    private float ypos;

    public BitButton(float x, float y, ITextureRegion region, GameDisplay vbo, Font pFont, String pText) {
        super(x, y, region, vbo.getVertexBufferObjectManager());
        setXpos(x);
        setYpos(y);
        Text buttonText = new Text(25.0f, 25.0f, pFont, pText, vbo.getVertexBufferObjectManager());
        buttonText.setPosition(getXpos(), getYpos());
        attachChild(buttonText);
    }

    public float getXpos() {
        return this.xpos;
    }

    public void setXpos(float xpos) {
        this.xpos = xpos;
    }

    public float getYpos() {
        return this.ypos;
    }

    public void setYpos(float ypos) {
        this.ypos = ypos;
    }
}
