package ISP;

import ISP.Graphics;
import ISP.Shapes;
import ISP.*;

public class Main {
    public static void main(String[] args) {

        TextGraphics textGraphics = new ConsoleGraphics();
        LineGraphics lineGraphics = new ConsoleGraphics();

        Shape shape = new Shapes(textGraphics, lineGraphics, "ISP", 9);

        shape.drawText(textGraphics);
        shape.drawLine(lineGraphics);
    }
}
