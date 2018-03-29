package components;


import com.example.meseret.roulette.GameDisplay;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.ITextureRegion;

public class TimerButton extends Sprite {
    private Text buttonText;
    private String text;

    public TimerButton(float x, float y, ITextureRegion region, GameDisplay vbo, Font pFont, String pText) {
        super(x, y, region, vbo.getVertexBufferObjectManager());
        this.text = pText;
        this.buttonText = new Text(25.0f, 25.0f, pFont, pText, vbo.getVertexBufferObjectManager());
        this.buttonText.setPosition((((getX() + getWidth()) - this.buttonText.getWidth()) / 2.0f) - 10.0f, ((getY() + getHeight()) - this.buttonText.getHeight()) / 2.0f);
        attachChild(this.buttonText);
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
