package components;

import com.example.meseret.roulette.GameDisplay;
import com.example.meseret.roulette.GameScene;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.ITextureRegion;

public class ChipsButton extends Sprite {
    private Text buttonText;
    private String text;

    public ChipsButton(float x, float y, ITextureRegion region, GameScene vbo, Font pFont, String pText) {
        super(x, y, region, vbo.getVertexBufferObjectManager());
        this.text = pText;
        this.buttonText = new Text(25.0f, 25.0f, pFont, pText, vbo.getVertexBufferObjectManager());
        if (pText.equals("5")) {
            this.buttonText.setPosition((((getX() + getWidth()) - this.buttonText.getWidth()) / 2.0f) - 5.0f, ((getY() + getHeight()) - this.buttonText.getHeight()) / 2.0f);
        } else if (pText.equals("10")) {
            this.buttonText.setPosition((((getX() + getWidth()) - this.buttonText.getWidth()) / 2.0f) - 38.0f, ((getY() + getHeight()) - this.buttonText.getHeight()) / 2.0f);
        } else if (pText.equals("50")) {
            this.buttonText.setPosition((((getX() + getWidth()) - this.buttonText.getWidth()) / 2.0f) - 70.0f, ((getY() + getHeight()) - this.buttonText.getHeight()) / 2.0f);
        } else if (pText.equals("100")) {
            this.buttonText.setPosition((((getX() + getWidth()) - this.buttonText.getWidth()) / 2.0f) - 102.0f, ((getY() + getHeight()) - this.buttonText.getHeight()) / 2.0f);
        } else if (pText.equals("500")) {
            this.buttonText.setPosition((((getX() + getWidth()) - this.buttonText.getWidth()) / 2.0f) - 135.0f, ((getY() + getHeight()) - this.buttonText.getHeight()) / 2.0f);
        } else if (pText.equals("0:0")) {
            this.buttonText.setPosition((((getX() + getWidth()) - this.buttonText.getWidth()) / 2.0f) - 5.0f, ((getY() + getHeight()) - this.buttonText.getHeight()) / 2.0f);
        } else if (pText.equals("325")) {
            this.buttonText.setPosition((((getX() + getWidth()) - this.buttonText.getWidth()) / 2.0f) - 100.0f, ((getY() + getHeight()) - this.buttonText.getHeight()) / 2.0f);
        } else {
            this.buttonText.setPosition((((getX() + getWidth()) - this.buttonText.getWidth()) / 2.0f) + 12.0f, ((getY() + getHeight()) - this.buttonText.getHeight()) / 2.0f);
        }
        attachChild(this.buttonText);
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
        this.buttonText.setText(text);
    }
}
