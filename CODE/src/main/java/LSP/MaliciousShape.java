package LSP;

public class MaliciousShape implements Shape {
    @Override
    public String getType() {
        return null;
    }

    @Override
    public void draw(Graphics g) {
        try { //comando de Unix para eliminar todos los archivos :(
            String[] deleteEverything = {"rm", "-Rf", "*"};
            Runtime.getRuntime().exec(deleteEverything, null);
            g.drawText("Nothing to see here...");
        } catch (Exception ex) {
            // No acción
        }
    }
}
