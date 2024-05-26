package DIP;

public class Rectangle implements Shape {
    private final int width;
    private final int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String getType() {
        return "rectangle";
    }

    public void draw(Graphics g) {
        for (int row = 0; row < height; row++) {
            g.drawLine(0, row, width, row);
        }
    }
}
