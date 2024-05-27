package ISP;

import ISP.Graphics;

public class Shapes implements Shape {
    private final TextGraphics textGraphics;
    private final LineGraphics lineGraphics;
    private final String text;
    private final int lineWidth;

    public Shapes(TextGraphics textGraphics, LineGraphics lineGraphics, String text, int lineWidth) {
        this.textGraphics = textGraphics;
        this.lineGraphics = lineGraphics;
        this.text = text;
        this.lineWidth = lineWidth;
    }

    @Override
    public void drawText(TextGraphics g) {
        g.drawText(text);
    }

    @Override
    public void drawLine(LineGraphics g) {
        g.drawHorizontalLine(lineWidth);
    }
}
