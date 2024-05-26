package SRP;

import java.util.ArrayList;
import java.util.List;

public class Shapes {
    private final List<Shape> allShapes = new ArrayList<>();

    public void add(Shape s) {
        allShapes.add(s);
    }

    public void draw(Graphics g) {
        for (Shape s : allShapes) {
            switch (s.getType()) {
                case "textbox":
                    var t = (TextBox) s;
                    t.draw(g);
                    break;

                case "rectangle":
                    var r = (Rectangle) s;
                    r.draw(g);
            }
        }
        }
}
