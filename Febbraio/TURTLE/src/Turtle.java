
import java.awt.*;
import java.awt.geom.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe che rappresenta una tartaruga grafica.
 * La tartaruga può muoversi su un canvas, disegnare linee e forme,
 * e può essere personalizzata con colori e dimensioni diverse.
 *
 * <p>Il sistema di coordinate ha l'origine al centro dello schermo,
 * con l'asse X positivo verso destra e l'asse Y positivo verso l'alto.</p>
 *
 * @author JavaTurtle
 * @version 1.0
 */
public class Turtle {

    /** Mappa dei colori predefiniti accessibili per nome */
    private static final Map<String, Color> COLOR_MAP = new HashMap<>();

    static {
        COLOR_MAP.put("red", Color.RED);
        COLOR_MAP.put("blue", Color.BLUE);
        COLOR_MAP.put("green", Color.GREEN);
        COLOR_MAP.put("black", Color.BLACK);
        COLOR_MAP.put("white", Color.WHITE);
        COLOR_MAP.put("yellow", Color.YELLOW);
        COLOR_MAP.put("orange", Color.ORANGE);
        COLOR_MAP.put("purple", new Color(128, 0, 128));
        COLOR_MAP.put("pink", Color.PINK);
        COLOR_MAP.put("cyan", Color.CYAN);
        COLOR_MAP.put("magenta", Color.MAGENTA);
        COLOR_MAP.put("brown", new Color(139, 69, 19));
        COLOR_MAP.put("gray", Color.GRAY);
        COLOR_MAP.put("grey", Color.GRAY);
    }

    private TurtleScreen screen;
    private TurtleCanvas canvas;
    private double x = 0;
    private double y = 0;
    private double heading = 0;
    private boolean penDown = true;
    private Color penColor = Color.BLACK;
    private Color fillColor = Color.BLACK;
    private double penWidth = 1.0;
    private boolean visible = true;
    private String shape = "arrow";
    private double turtleSize = 1.0;
    private int speed = 50;
    private boolean filling = false;
    private Path2D.Double fillPath;

    /**
     * Crea una nuova tartaruga associata allo schermo specificato.
     * La tartaruga viene automaticamente registrata nello schermo.
     *
     * @param screen lo schermo su cui la tartaruga disegnerà
     */
    public Turtle(TurtleScreen screen) {
        this.screen = screen;
        this.canvas = screen.getCanvas();
        screen.registerTurtle(this);
    }

    /**
     * Converte una stringa di colore in un oggetto Color.
     * Supporta nomi di colori predefiniti (es. "red", "blue") e
     * codici esadecimali (es. "#FF0000", "#F00").
     *
     * @param colorString il nome del colore o codice esadecimale
     * @return l'oggetto Color corrispondente, o Color.BLACK se non riconosciuto
     */
    public static Color parseColor(String colorString) {
        if (colorString == null || colorString.isEmpty()) {
            return Color.BLACK;
        }

        String lower = colorString.toLowerCase().trim();

        if (COLOR_MAP.containsKey(lower)) {
            return COLOR_MAP.get(lower);
        }

        if (colorString.startsWith("#")) {
            String hex = colorString.substring(1);
            if (hex.length() == 3) {
                char r = hex.charAt(0);
                char g = hex.charAt(1);
                char b = hex.charAt(2);
                hex = "" + r + r + g + g + b + b;
            }
            if (hex.length() == 6) {
                int rgb = Integer.parseInt(hex, 16);
                return new Color(rgb);
            }
        }

        return Color.BLACK;
    }

    /**
     * Muove la tartaruga in avanti della distanza specificata.
     * Se la penna è abbassata, disegna una linea lungo il percorso.
     *
     * @param distance la distanza da percorrere (può essere negativa per andare indietro)
     */
    public void forward(double distance) {
        double radians = Math.toRadians(heading);
        double newX = x + distance * Math.cos(radians);
        double newY = y + distance * Math.sin(radians);

        if (speed == 0) {
            moveTo(newX, newY);
        } else {
            animateMove(newX, newY, Math.abs(distance));
        }
    }

    /**
     * Abbreviazione di {@link #forward(double)}.
     *
     * @param distance la distanza da percorrere
     */
    public void fd(double distance) {
        forward(distance);
    }

    /**
     * Muove la tartaruga all'indietro della distanza specificata.
     *
     * @param distance la distanza da percorrere all'indietro
     */
    public void backward(double distance) {
        forward(-distance);
    }

    /**
     * Abbreviazione di {@link #backward(double)}.
     *
     * @param distance la distanza da percorrere
     */
    public void bk(double distance) {
        backward(distance);
    }

    /**
     * Alias di {@link #backward(double)}.
     *
     * @param distance la distanza da percorrere
     */
    public void back(double distance) {
        backward(distance);
    }

    /**
     * Ruota la tartaruga verso destra (in senso orario) dell'angolo specificato.
     *
     * @param angle l'angolo di rotazione in gradi
     */
    public void right(double angle) {
        heading -= angle;
        heading = normalizeAngle(heading);
    }

    /**
     * Abbreviazione di {@link #right(double)}.
     *
     * @param angle l'angolo di rotazione in gradi
     */
    public void rt(double angle) {
        right(angle);
    }

    /**
     * Ruota la tartaruga verso sinistra (in senso antiorario) dell'angolo specificato.
     *
     * @param angle l'angolo di rotazione in gradi
     */
    public void left(double angle) {
        heading += angle;
        heading = normalizeAngle(heading);
    }

    /**
     * Abbreviazione di {@link #left(double)}.
     *
     * @param angle l'angolo di rotazione in gradi
     */
    public void lt(double angle) {
        left(angle);
    }

    /**
     * Sposta la tartaruga alle coordinate specificate.
     * Se la penna è abbassata, disegna una linea dalla posizione corrente.
     *
     * @param newX la coordinata X di destinazione
     * @param newY la coordinata Y di destinazione
     */
    public void goTo(double newX, double newY) {
        double distance = Math.sqrt(Math.pow(newX - x, 2) + Math.pow(newY - y, 2));
        if (speed == 0) {
            moveTo(newX, newY);
        } else {
            animateMove(newX, newY, distance);
        }
    }

    /**
     * Alias di {@link #goTo(double, double)}.
     *
     * @param x la coordinata X di destinazione
     * @param y la coordinata Y di destinazione
     */
    public void setPos(double x, double y) {
        goTo(x, y);
    }

    /**
     * Alias di {@link #goTo(double, double)}.
     *
     * @param x la coordinata X di destinazione
     * @param y la coordinata Y di destinazione
     */
    public void setPosition(double x, double y) {
        goTo(x, y);
    }

    /**
     * Imposta solo la coordinata X della tartaruga.
     *
     * @param newX la nuova coordinata X
     */
    public void setX(double newX) {
        goTo(newX, y);
    }

    /**
     * Imposta solo la coordinata Y della tartaruga.
     *
     * @param newY la nuova coordinata Y
     */
    public void setY(double newY) {
        goTo(x, newY);
    }

    /**
     * Riporta la tartaruga all'origine (0, 0) e la orienta verso destra (0 gradi).
     */
    public void home() {
        goTo(0, 0);
        setHeading(0);
    }

    /**
     * Disegna un cerchio completo con il raggio specificato.
     * Il cerchio viene disegnato a sinistra della tartaruga.
     *
     * @param radius il raggio del cerchio (negativo per disegnare a destra)
     */
    public void circle(double radius) {
        circle(radius, 360, 36);
    }

    /**
     * Disegna un arco di cerchio con il raggio e l'estensione specificati.
     *
     * @param radius il raggio del cerchio
     * @param extent l'angolo dell'arco in gradi (360 per un cerchio completo)
     */
    public void circle(double radius, double extent) {
        int steps = Math.max(1, (int) Math.abs(extent / 10));
        circle(radius, extent, steps);
    }

    /**
     * Disegna un arco di cerchio con controllo sul numero di segmenti.
     *
     * @param radius il raggio del cerchio
     * @param extent l'angolo dell'arco in gradi
     * @param steps il numero di segmenti per approssimare l'arco
     */
    public void circle(double radius, double extent, int steps) {
        double angleStep = extent / steps;
        double stepLen = 2 * Math.abs(radius) * Math.sin(Math.toRadians(Math.abs(angleStep) / 2));

        if (radius < 0) {
            angleStep = -angleStep;
        }

        for (int i = 0; i < steps; i++) {
            forward(stepLen);
            left(angleStep);
        }
    }

    /**
     * Disegna un punto (cerchio pieno) alla posizione corrente.
     *
     * @param size il diametro del punto
     */
    public void dot(double size) {
        dot(size, penColor);
    }

    /**
     * Disegna un punto colorato alla posizione corrente.
     *
     * @param size il diametro del punto
     * @param color il colore del punto
     */
    public void dot(double size, Color color) {
        Graphics2D g2d = canvas.getBufferGraphics();
        double screenX = canvas.toScreenX(x);
        double screenY = canvas.toScreenY(y);
        g2d.setColor(color);
        double halfSize = size / 2;
        g2d.fill(new Ellipse2D.Double(screenX - halfSize, screenY - halfSize, size, size));
        g2d.dispose();
    }

    /**
     * Imposta la velocità di movimento della tartaruga.
     *
     * @param speed la velocità (0 = istantaneo, 1 = molto veloce, 255 = molto lento)
     */
    public void speed(int speed) {
        this.speed = Math.max(0, Math.min(255, speed));
    }

    /**
     * Restituisce la velocità corrente della tartaruga.
     *
     * @return la velocità (0-255)
     */
    public int speed() {
        return speed;
    }

    /**
     * Abbassa la penna. I movimenti successivi lasceranno una traccia.
     */
    public void penDown() {
        penDown = true;
    }

    /**
     * Abbreviazione di {@link #penDown()}.
     */
    public void pd() {
        penDown();
    }

    /**
     * Alias di {@link #penDown()}.
     */
    public void down() {
        penDown();
    }

    /**
     * Alza la penna. I movimenti successivi non lasceranno traccia.
     */
    public void penUp() {
        penDown = false;
    }

    /**
     * Abbreviazione di {@link #penUp()}.
     */
    public void pu() {
        penUp();
    }

    /**
     * Alias di {@link #penUp()}.
     */
    public void up() {
        penUp();
    }

    /**
     * Verifica se la penna è abbassata.
     *
     * @return true se la penna è abbassata, false altrimenti
     */
    public boolean isDown() {
        return penDown;
    }

    /**
     * Imposta lo spessore della penna.
     *
     * @param width lo spessore in pixel
     */
    public void setPenSize(double width) {
        this.penWidth = width;
    }

    /**
     * Alias di {@link #setPenSize(double)}.
     *
     * @param width lo spessore in pixel
     */
    public void width(double width) {
        setPenSize(width);
    }

    /**
     * Imposta il colore della penna.
     *
     * @param color il colore
     */
    public void setPenColor(Color color) {
        this.penColor = color;
    }

    /**
     * Imposta il colore della penna usando un nome o codice esadecimale.
     *
     * @param colorName il nome del colore (es. "red") o codice hex (es. "#FF0000")
     */
    public void setPenColor(String colorName) {
        setPenColor(parseColor(colorName));
    }

    /**
     * Restituisce il colore corrente della penna.
     *
     * @return il colore della penna
     */
    public Color getPenColor() {
        return penColor;
    }

    /**
     * Imposta il colore di riempimento per le forme.
     *
     * @param color il colore di riempimento
     */
    public void setFillColor(Color color) {
        this.fillColor = color;
    }

    /**
     * Imposta il colore di riempimento usando un nome o codice esadecimale.
     *
     * @param colorName il nome del colore o codice hex
     */
    public void setFillColor(String colorName) {
        setFillColor(parseColor(colorName));
    }

    /**
     * Restituisce il colore corrente di riempimento.
     *
     * @return il colore di riempimento
     */
    public Color getFillColor() {
        return fillColor;
    }

    /**
     * Imposta sia il colore della penna che quello di riempimento.
     *
     * @param pen il colore della penna
     * @param fill il colore di riempimento
     */
    public void setColor(Color pen, Color fill) {
        setPenColor(pen);
        setFillColor(fill);
    }

    /**
     * Imposta sia il colore della penna che quello di riempimento usando nomi.
     *
     * @param pen il nome del colore della penna
     * @param fill il nome del colore di riempimento
     */
    public void setColor(String pen, String fill) {
        setPenColor(pen);
        setFillColor(fill);
    }

    /**
     * Imposta lo stesso colore per penna e riempimento.
     *
     * @param c il colore
     */
    public void setColor(Color c) {
        setPenColor(c);
        setFillColor(c);
    }

    /**
     * Imposta lo stesso colore per penna e riempimento usando un nome.
     *
     * @param c il nome del colore
     */
    public void setColor(String c) {
        setPenColor(c);
        setFillColor(c);
    }

    /**
     * Inizia a registrare un percorso per il riempimento.
     * Tutti i movimenti successivi verranno registrati fino a {@link #endFill()}.
     */
    public void beginFill() {
        filling = true;
        fillPath = new Path2D.Double();
        fillPath.moveTo(canvas.toScreenX(x), canvas.toScreenY(y));
    }

    /**
     * Termina la registrazione del percorso e riempie la forma.
     * Il percorso viene chiuso automaticamente e riempito con il colore di riempimento.
     */
    public void endFill() {
        if (filling && fillPath != null) {
            fillPath.closePath();
            Graphics2D g2d = canvas.getBufferGraphics();
            g2d.setColor(fillColor);
            g2d.fill(fillPath);
            if (penDown) {
                g2d.setColor(penColor);
                g2d.setStroke(new BasicStroke((float) penWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2d.draw(fillPath);
            }
            g2d.dispose();
        }
        filling = false;
        fillPath = null;
    }

    /**
     * Ripristina la tartaruga alle impostazioni predefinite.
     * Posizione all'origine, orientamento a destra, penna nera abbassata.
     */
    public void reset() {
        x = 0;
        y = 0;
        heading = 0;
        penDown = true;
        penColor = Color.BLACK;
        fillColor = Color.BLACK;
        penWidth = 1.0;
        visible = true;
        shape = "arrow";
        turtleSize = 1.0;
        speed = 50;
        filling = false;
        fillPath = null;
    }

    /**
     * Cancella tutti i disegni dal canvas.
     * La posizione e l'orientamento della tartaruga non vengono modificati.
     */
    public void clear() {
        canvas.clearBuffer();
    }

    /**
     * Imposta l'orientamento della tartaruga.
     * 0 gradi = destra, 90 gradi = su, 180 gradi = sinistra, 270 gradi = giù.
     *
     * @param angle l'angolo in gradi
     */
    public void setHeading(double angle) {
        heading = normalizeAngle(angle);
    }

    /**
     * Abbreviazione di {@link #setHeading(double)}.
     *
     * @param angle l'angolo in gradi
     */
    public void seth(double angle) {
        setHeading(angle);
    }

    /**
     * Restituisce l'orientamento corrente della tartaruga.
     *
     * @return l'angolo in gradi (0-360)
     */
    public double heading() {
        return heading;
    }

    /**
     * Orienta la tartaruga verso il punto specificato.
     *
     * @param targetX la coordinata X del punto di destinazione
     * @param targetY la coordinata Y del punto di destinazione
     */
    public void towards(double targetX, double targetY) {
        double dx = targetX - x;
        double dy = targetY - y;
        setHeading(Math.toDegrees(Math.atan2(dy, dx)));
    }

    /**
     * Calcola la distanza dal punto specificato.
     *
     * @param targetX la coordinata X del punto
     * @param targetY la coordinata Y del punto
     * @return la distanza in pixel
     */
    public double distance(double targetX, double targetY) {
        return Math.sqrt(Math.pow(targetX - x, 2) + Math.pow(targetY - y, 2));
    }

    /**
     * Restituisce la coordinata X corrente della tartaruga.
     *
     * @return la coordinata X
     */
    public double xcor() {
        return x;
    }

    /**
     * Restituisce la coordinata Y corrente della tartaruga.
     *
     * @return la coordinata Y
     */
    public double ycor() {
        return y;
    }

    /**
     * Restituisce la posizione corrente come array [x, y].
     *
     * @return array con le coordinate [x, y]
     */
    public double[] position() {
        return new double[]{x, y};
    }

    /**
     * Rende visibile la tartaruga sul canvas.
     */
    public void showTurtle() {
        visible = true;
    }

    /**
     * Abbreviazione di {@link #showTurtle()}.
     */
    public void st() {
        showTurtle();
    }

    /**
     * Nasconde la tartaruga dal canvas.
     * La tartaruga continua a disegnare anche se nascosta.
     */
    public void hideTurtle() {
        visible = false;
    }

    /**
     * Abbreviazione di {@link #hideTurtle()}.
     */
    public void ht() {
        hideTurtle();
    }

    /**
     * Verifica se la tartaruga è visibile.
     *
     * @return true se visibile, false altrimenti
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Imposta la forma del cursore della tartaruga.
     * Forme disponibili: "arrow", "classic", "turtle", "circle", "square", "blank".
     *
     * @param name il nome della forma
     */
    public void setShape(String name) {
        this.shape = name;
    }

    /**
     * Restituisce il nome della forma corrente del cursore.
     *
     * @return il nome della forma
     */
    public String getShape() {
        return shape;
    }

    /**
     * Imposta la dimensione del cursore della tartaruga.
     *
     * @param size il fattore di scala (1.0 = dimensione normale)
     */
    public void setTurtleSize(double size) {
        this.turtleSize = size;
    }

    /**
     * Restituisce la dimensione corrente del cursore.
     *
     * @return il fattore di scala
     */
    public double getTurtleSize() {
        return turtleSize;
    }

    /**
     * Scrive testo alla posizione corrente della tartaruga.
     * Usa il font predefinito e allineamento a sinistra.
     *
     * @param text il testo da scrivere
     */
    public void write(String text) {
        write(text, "left", new Font("SansSerif", Font.PLAIN, 12));
    }

    /**
     * Scrive testo alla posizione corrente con font e allineamento personalizzati.
     *
     * @param text il testo da scrivere
     * @param align l'allineamento ("left", "center", "right")
     * @param font il font da utilizzare
     */
    public void write(String text, String align, Font font) {
        Graphics2D g2d = canvas.getBufferGraphics();
        g2d.setColor(penColor);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        double screenX = canvas.toScreenX(x);
        double screenY = canvas.toScreenY(y);

        switch (align.toLowerCase()) {
            case "center" -> screenX -= textWidth / 2.0;
            case "right" -> screenX -= textWidth;
        }

        g2d.drawString(text, (float) screenX, (float) screenY);
        g2d.dispose();
    }

    /**
     * Sposta la tartaruga alle nuove coordinate, disegnando se la penna è abbassata.
     */
    private void moveTo(double newX, double newY) {
        if (penDown) {
            drawLine(x, y, newX, newY);
        }
        if (filling && fillPath != null) {
            fillPath.lineTo(canvas.toScreenX(newX), canvas.toScreenY(newY));
        }
        x = newX;
        y = newY;
    }

    /**
     * Anima il movimento della tartaruga verso le coordinate specificate.
     */
    private void animateMove(double targetX, double targetY, double distance) {
        int steps = Math.max(1, (int) (distance / getStepSize()));
        double dx = (targetX - x) / steps;
        double dy = (targetY - y) / steps;

        for (int i = 0; i < steps; i++) {
            double nextX = x + dx;
            double nextY = y + dy;
            moveTo(nextX, nextY);
            delayIfNeeded();
        }

        moveTo(targetX, targetY);
    }

    /**
     * Calcola la dimensione del passo in base alla velocità.
     */
    private double getStepSize() {
        if (speed == 0) return Double.MAX_VALUE;
        return 1 + (256 - speed) / 10.0;
    }

    /**
     * Aggiunge un ritardo tra i passi dell'animazione se necessario.
     */
    private void delayIfNeeded() {
        if (speed > 0) {
            screen.refresh();
            int delay = Math.max(1, speed / 3);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Disegna una linea tra due punti.
     */
    private void drawLine(double x1, double y1, double x2, double y2) {
        Graphics2D g2d = canvas.getBufferGraphics();
        g2d.setColor(penColor);
        g2d.setStroke(new BasicStroke((float) penWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.draw(new Line2D.Double(
                canvas.toScreenX(x1), canvas.toScreenY(y1),
                canvas.toScreenX(x2), canvas.toScreenY(y2)
        ));
        g2d.dispose();
    }

    /**
     * Normalizza un angolo nell'intervallo [0, 360).
     */
    private double normalizeAngle(double angle) {
        angle = angle % 360;
        if (angle < 0) angle += 360;
        return angle;
    }
}
