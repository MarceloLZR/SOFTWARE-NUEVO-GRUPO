package LSP;

import java.util.ArrayList;
import java.util.List;

public class Shapes {
    private final List<Shape> allShapes = new ArrayList<>();
    private final Graphics graphics;
    public Shapes(Graphics graphics) {
        this.graphics = graphics;
    }
    public void add(Shape s) {
        allShapes.add(s);
    }

    public void draw() {
        allShapes.forEach(shape->shape.draw(graphics));
    }
}
