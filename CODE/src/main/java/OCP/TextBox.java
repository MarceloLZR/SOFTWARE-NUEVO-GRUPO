package OCP;

public class TextBox implements Shape {
    private final String text;

    public TextBox(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String getType() {
        return "textbox";
    }

    public void draw(Graphics g) {
        g.drawText(text);
    }
}
