package Bejeweled;

import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.*;

public class GameFonts {
    private Font UIFont1;
    private UnicodeFont uniFont;

    public GameFonts(String resFont, float size) {
        try {
            UIFont1 = Font.createFont(Font.TRUETYPE_FONT,
                    ResourceLoader.getResourceAsStream(resFont));
            UIFont1 = UIFont1.deriveFont(Font.PLAIN, size);

            uniFont = new UnicodeFont(UIFont1);
            uniFont.addAsciiGlyphs();
            uniFont.getEffects().add(new ColorEffect(java.awt.Color.white));
            uniFont.addAsciiGlyphs();
            uniFont.loadGlyphs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DrawWithGameFont(float x, float y, String text, org.newdawn.slick.Color col) {
        uniFont.drawString(x, y, text, col);
    }

    public Font getUIFont1() {
        return UIFont1;
    }

    public UnicodeFont getUniFont() {
        return uniFont;
    }
}
