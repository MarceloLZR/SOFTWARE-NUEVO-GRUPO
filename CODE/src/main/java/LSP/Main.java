package LSP;

public class Main {
    public static void main(String[] args) {
        Graphics g = new Graphics();
        Shapes shapes = new Shapes(g);
        shapes.add(new TextBox("Hello, World!"));
        shapes.add(new Rectangle(10, 5));

        shapes.draw();
    }
}
