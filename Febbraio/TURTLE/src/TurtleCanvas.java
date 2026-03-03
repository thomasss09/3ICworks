
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Canvas per il rendering grafico delle tartarughe.
 * Implementa il double buffering per animazioni fluide senza sfarfallio.
 *
 * <p>Utilizza due buffer (front e back) per evitare artefatti visivi:</p>
 * <ul>
 *   <li>Il back buffer è dove le tartarughe disegnano</li>
 *   <li>Il front buffer è quello visualizzato sullo schermo</li>
 *   <li>{@link #swapBuffers()} copia il back buffer nel front buffer</li>
 * </ul>
 *
 * <p>Il sistema di coordinate ha l'origine al centro del canvas,
 * con X positivo verso destra e Y positivo verso l'alto.</p>
 *
 * @author JavaTurtle
 * @version 1.0
 */
public class TurtleCanvas extends JPanel {

    /** Buffer visualizzato sullo schermo */
    private BufferedImage frontBuffer;

    /** Buffer su cui le tartarughe disegnano */
    private BufferedImage backBuffer;

    /** Lock per sincronizzare lo swap dei buffer */
    private final Object swapLock = new Object();

    /** Lista delle tartarughe da renderizzare */
    private List<Turtle> turtles;

    /** Colore di sfondo del canvas */
    private Color backgroundColor = Color.WHITE;

    /** Timer per il refresh automatico del display */
    private Timer renderTimer;

    /**
     * Crea un nuovo canvas con le dimensioni specificate.
     *
     * @param width la larghezza in pixel
     * @param height l'altezza in pixel
     */
    public TurtleCanvas(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setDoubleBuffered(true);
        frontBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        backBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        clearBothBuffers();
        startRenderLoop();
    }

    /**
     * Avvia il loop di rendering a 60 FPS.
     */
    private void startRenderLoop() {
        renderTimer = new Timer(1000 / 60, e -> repaint());
        renderTimer.start();
    }

    /**
     * Ferma il loop di rendering.
     */
    public void stopRenderLoop() {
        if (renderTimer != null) {
            renderTimer.stop();
        }
    }

    /**
     * Imposta la lista delle tartarughe da visualizzare.
     *
     * @param turtles la lista delle tartarughe
     */
    public void setTurtles(List<Turtle> turtles) {
        this.turtles = turtles;
    }

    /**
     * Restituisce un contesto grafico per disegnare sul back buffer.
     * Il contesto ha antialiasing e rendering di alta qualità abilitati.
     *
     * <p>IMPORTANTE: chiamare {@code dispose()} sul Graphics2D dopo l'uso.</p>
     *
     * @return un Graphics2D configurato per il disegno
     */
    public Graphics2D getBufferGraphics() {
        Graphics2D g2d = backBuffer.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        return g2d;
    }

    /**
     * Cancella il back buffer con il colore di sfondo.
     */
    public void clearBuffer() {
        Graphics2D g2d = backBuffer.createGraphics();
        g2d.setColor(backgroundColor);
        g2d.fillRect(0, 0, backBuffer.getWidth(), backBuffer.getHeight());
        g2d.dispose();
    }

    /**
     * Cancella entrambi i buffer con il colore di sfondo.
     */
    private void clearBothBuffers() {
        Graphics2D g2d = frontBuffer.createGraphics();
        g2d.setColor(backgroundColor);
        g2d.fillRect(0, 0, frontBuffer.getWidth(), frontBuffer.getHeight());
        g2d.dispose();

        g2d = backBuffer.createGraphics();
        g2d.setColor(backgroundColor);
        g2d.fillRect(0, 0, backBuffer.getWidth(), backBuffer.getHeight());
        g2d.dispose();
    }

    /**
     * Copia il contenuto del back buffer nel front buffer.
     * Questa operazione è thread-safe.
     */
    public void swapBuffers() {
        synchronized (swapLock) {
            Graphics2D g = frontBuffer.createGraphics();
            g.drawImage(backBuffer, 0, 0, null);
            g.dispose();
        }
    }

    /**
     * Imposta il colore di sfondo e cancella i buffer.
     *
     * @param color il nuovo colore di sfondo
     */
    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        clearBothBuffers();
    }

    /**
     * Restituisce il colore di sfondo corrente.
     *
     * @return il colore di sfondo
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Converte una coordinata X del mondo turtle in coordinata schermo.
     * L'origine del mondo turtle è al centro del canvas.
     *
     * @param x la coordinata X nel sistema turtle
     * @return la coordinata X sullo schermo
     */
    public double toScreenX(double x) {
        return backBuffer.getWidth() / 2.0 + x;
    }

    /**
     * Converte una coordinata Y del mondo turtle in coordinata schermo.
     * L'asse Y è invertito (positivo verso l'alto nel mondo turtle).
     *
     * @param y la coordinata Y nel sistema turtle
     * @return la coordinata Y sullo schermo
     */
    public double toScreenY(double y) {
        return backBuffer.getHeight() / 2.0 - y;
    }

    /**
     * Disegna il contenuto del canvas.
     * Visualizza il front buffer e i cursori delle tartarughe visibili.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        synchronized (swapLock) {
            g2d.drawImage(frontBuffer, 0, 0, null);
        }

        if (turtles != null) {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            for (Turtle turtle : turtles) {
                if (turtle.isVisible()) {
                    drawTurtleCursor(g2d, turtle);
                }
            }
        }
    }

    /**
     * Disegna il cursore di una tartaruga alla sua posizione corrente.
     *
     * @param g2d il contesto grafico
     * @param turtle la tartaruga da disegnare
     */
    private void drawTurtleCursor(Graphics2D g2d, Turtle turtle) {
        double x = turtle.xcor();
        double y = turtle.ycor();
        double heading = turtle.heading();
        double size = turtle.getTurtleSize();
        String shape = turtle.getShape();
        Color fillColor = turtle.getFillColor();

        double screenX = toScreenX(x);
        double screenY = toScreenY(y);

        AffineTransform oldTransform = g2d.getTransform();
        g2d.translate(screenX, screenY);
        g2d.rotate(-Math.toRadians(heading));

        g2d.setColor(fillColor);

        switch (shape) {
            case "arrow" -> drawArrowShape(g2d, size);
            case "classic" -> drawClassicShape(g2d, size);
            case "turtle" -> drawTurtleShape(g2d, size);
            case "circle" -> drawCircleShape(g2d, size);
            case "square" -> drawSquareShape(g2d, size);
            case "blank" -> {}
            default -> drawArrowShape(g2d, size);
        }

        g2d.setTransform(oldTransform);
    }

    /**
     * Disegna la forma "arrow" (freccia).
     */
    private void drawArrowShape(Graphics2D g2d, double size) {
        double s = 10 * size;
        Path2D.Double path = new Path2D.Double();
        path.moveTo(s, 0);
        path.lineTo(-s * 0.7, -s * 0.5);
        path.lineTo(-s * 0.4, 0);
        path.lineTo(-s * 0.7, s * 0.5);
        path.closePath();
        g2d.fill(path);
        g2d.setColor(Color.BLACK);
        g2d.draw(path);
    }

    /**
     * Disegna la forma "classic" (triangolo classico).
     */
    private void drawClassicShape(Graphics2D g2d, double size) {
        double s = 6 * size;
        Path2D.Double path = new Path2D.Double();
        path.moveTo(s, 0);
        path.lineTo(-s, -s * 0.5);
        path.lineTo(-s * 0.5, 0);
        path.lineTo(-s, s * 0.5);
        path.closePath();
        g2d.fill(path);
        g2d.setColor(Color.BLACK);
        g2d.draw(path);
    }

    /**
     * Disegna la forma "turtle" (tartaruga stilizzata).
     */
    private void drawTurtleShape(Graphics2D g2d, double size) {
        double s = 10 * size;
        Ellipse2D.Double body = new Ellipse2D.Double(-s * 0.6, -s * 0.4, s * 1.2, s * 0.8);
        g2d.fill(body);

        Ellipse2D.Double head = new Ellipse2D.Double(s * 0.4, -s * 0.15, s * 0.4, s * 0.3);
        g2d.fill(head);

        double legSize = s * 0.25;
        Ellipse2D.Double leg1 = new Ellipse2D.Double(-s * 0.4, -s * 0.55, legSize, legSize);
        Ellipse2D.Double leg2 = new Ellipse2D.Double(s * 0.15, -s * 0.55, legSize, legSize);
        Ellipse2D.Double leg3 = new Ellipse2D.Double(-s * 0.4, s * 0.3, legSize, legSize);
        Ellipse2D.Double leg4 = new Ellipse2D.Double(s * 0.15, s * 0.3, legSize, legSize);
        g2d.fill(leg1);
        g2d.fill(leg2);
        g2d.fill(leg3);
        g2d.fill(leg4);

        g2d.setColor(Color.BLACK);
        g2d.draw(body);
    }

    /**
     * Disegna la forma "circle" (cerchio).
     */
    private void drawCircleShape(Graphics2D g2d, double size) {
        double s = 8 * size;
        Ellipse2D.Double circle = new Ellipse2D.Double(-s, -s, s * 2, s * 2);
        g2d.fill(circle);
        g2d.setColor(Color.BLACK);
        g2d.draw(circle);
    }

    /**
     * Disegna la forma "square" (quadrato).
     */
    private void drawSquareShape(Graphics2D g2d, double size) {
        double s = 8 * size;
        Rectangle2D.Double square = new Rectangle2D.Double(-s, -s, s * 2, s * 2);
        g2d.fill(square);
        g2d.setColor(Color.BLACK);
        g2d.draw(square);
    }
}
