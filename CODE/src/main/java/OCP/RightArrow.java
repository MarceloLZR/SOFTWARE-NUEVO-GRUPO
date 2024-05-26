package OCP;

public class RightArrow implements Shape {
    @Override
    public String getType() {
        return null;
    }

    @Override
    public void draw(Graphics g) {
        g.drawText("   \\");
        g.drawText("-----");
        g.drawText("   /");
    }
}
