package SRP;

public class Main {
    public static void main(String[] args) {
        Shapes shapes = new Shapes();
        shapes.add(new TextBox("Hello, World!"));
        shapes.add(new Rectangle(10, 5));

        Graphics g = new Graphics();
        shapes.draw(g);
    }
}
