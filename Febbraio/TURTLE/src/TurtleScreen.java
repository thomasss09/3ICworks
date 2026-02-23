
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.*;

/**
 * Classe principale per la gestione dello schermo della tartaruga.
 * Fornisce una finestra grafica dove le tartarughe possono disegnare.
 *
 * <p>Per creare un'applicazione, estendere questa classe e sovrascrivere
 * i metodi {@link #setup()} e {@link #loop()}.</p>
 *
 * <p>Esempio di utilizzo:</p>
 * <pre>{@code
 * public class MiaDemo extends TurtleScreen {
 *     private Turtle t;
 *
 *     public void setup() {
 *         t = createTurtle();
 *         t.forward(100);
 *     }
 *
 *     public static void main(String[] args) {
 *         new MiaDemo().run();
 *     }
 * }
 * }</pre>
 *
 * @author JavaTurtle
 * @version 1.0
 */
public class TurtleScreen {

    // Istanza singleton per l'accesso globale
    private static TurtleScreen instance;

    //  La finestra principale dell'applicazione
    protected JFrame frame;

    //  Il canvas su cui vengono disegnate le tartarughe
    protected TurtleCanvas canvas;

    //  Lista thread-safe di tutte le tartarughe registrate
    protected final CopyOnWriteArrayList<Turtle> turtles = new CopyOnWriteArrayList<>();

    //  Larghezza dello schermo in pixel
    protected int width;

    //  Altezza dello schermo in pixel
    protected int height;

    private volatile String lastKey = null;
    private volatile boolean mouseClicked = false;
    private volatile int mouseX = 0;
    private volatile int mouseY = 0;
    private volatile boolean running = false;
    private volatile boolean looping = true;
    private int frameRate = 60;

    /**
     * Crea uno schermo con dimensioni predefinite (800x600).
     */
    public TurtleScreen() {
        this(800, 600);
    }

    /**
     * Crea uno schermo con le dimensioni specificate.
     *
     * @param width la larghezza dello schermo in pixel
     * @param height l'altezza dello schermo in pixel
     */
    public TurtleScreen(int width, int height) {
        this.width = width;
        this.height = height;
        initWindow();
    }

    /**
     * Inizializza la finestra Swing e configura gli event listener.
     */
    private void initWindow() {
        try {
            SwingUtilities.invokeAndWait(() -> {
                frame = new JFrame("Java Turtle Graphics");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                canvas = new TurtleCanvas(width, height);
                canvas.setTurtles(turtles);
                frame.add(canvas);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        running = false;
                        canvas.stopRenderLoop();
                    }
                });

                canvas.setFocusable(true);
                canvas.requestFocusInWindow();

                canvas.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        int code = e.getKeyCode();

                        // Tasti speciali con nomi specifici
                        String key = switch (code) {
                            case KeyEvent.VK_UP -> "up";
                            case KeyEvent.VK_DOWN -> "down";
                            case KeyEvent.VK_LEFT -> "left";
                            case KeyEvent.VK_RIGHT -> "right";
                            case KeyEvent.VK_SPACE -> "space";
                            case KeyEvent.VK_ENTER -> "enter";
                            case KeyEvent.VK_ESCAPE -> "escape";
                            default -> null;
                        };

                        // Se non è un tasto speciale, usa il carattere
                        if (key == null) {
                            char c = e.getKeyChar();
                            if (c != KeyEvent.CHAR_UNDEFINED) {
                                key = String.valueOf(c).toLowerCase();
                            } else {
                                key = KeyEvent.getKeyText(code).toLowerCase();
                            }
                        }

                        lastKey = key;
                    }
                });

                canvas.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        mouseX = e.getX() - canvas.getWidth() / 2;
                        mouseY = canvas.getHeight() / 2 - e.getY();
                        mouseClicked = true;
                    }
                });
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Restituisce l'istanza singleton dello schermo (800x600).
     *
     * @return l'istanza singleton di TurtleScreen
     */
    public static TurtleScreen getInstance() {
        if (instance == null) {
            instance = new TurtleScreen(800, 600);
        }
        return instance;
    }

    /**
     * Restituisce l'istanza singleton con dimensioni specifiche.
     *
     * @param width la larghezza desiderata
     * @param height l'altezza desiderata
     * @return l'istanza singleton di TurtleScreen
     */
    public static TurtleScreen getInstance(int width, int height) {
        if (instance == null) {
            instance = new TurtleScreen(width, height);
        }
        return instance;
    }

    /**
     * Crea una nuova tartaruga associata a questo schermo.
     *
     * @return una nuova istanza di Turtle
     */
    public Turtle createTurtle() {
        return new Turtle(this);
    }

    /**
     * Registra una tartaruga nello schermo
     * Chiamato automaticamente dal costruttore di Turtle
     *
     * @param turtle la tartaruga da registrare
     */
    void registerTurtle(Turtle turtle) {
        turtles.add(turtle);
    }

    /**
     * Imposta il colore di sfondo dello schermo.
     *
     * @param color il colore di sfondo
     */
    public void bgcolor(Color color) {
        canvas.setBackgroundColor(color);
    }

    /**
     * Imposta il colore di sfondo usando un nome o codice esadecimale.
     *
     * @param colorName il nome del colore (es. "blue") o codice hex (es. "#0000FF")
     */
    public void bgcolor(String colorName) {
        bgcolor(Turtle.parseColor(colorName));
    }

    /**
     * Imposta il titolo della finestra.
     *
     * @param title il nuovo titolo
     */
    public void title(String title) {
        SwingUtilities.invokeLater(() -> frame.setTitle(title));
    }

    /**
     * Aggiorna lo schermo immediatamente.
     * Utile per vedere i progressi del disegno in modalità noLoop.
     */
    public void refresh() {
        canvas.swapBuffers();
        canvas.repaint();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Imposta il frame rate desiderato per il loop di animazione.
     *
     * @param fps i frame al secondo (default: 60)
     */
    public void frameRate(int fps) {
        this.frameRate = fps;
    }

    /**
     * Metodo chiamato una volta all'avvio dell'applicazione.
     * Sovrascrivere per inizializzare le tartarughe e il disegno iniziale.
     */
    public void setup() {
    }

    /**
     * Metodo chiamato ripetutamente durante l'esecuzione.
     * Sovrascrivere per creare animazioni o giochi interattivi.
     * Il canvas viene cancellato automaticamente prima di ogni chiamata.
     */
    public void loop() {
    }

    /**
     * Avvia l'esecuzione dell'applicazione.
     * Chiama {@link #setup()} una volta, poi {@link #loop()} ripetutamente
     * (a meno che non sia stata chiamata {@link #noLoop()}).
     */
    public void run() {
        setup();
        running = true;

        if (!looping) {
            canvas.swapBuffers();
            canvas.repaint();
            while (running) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }
            return;
        }

        long frameTime = 1000 / frameRate;

        while (running) {
            long startTime = System.currentTimeMillis();

            canvas.clearBuffer();
            loop();
            canvas.swapBuffers();
            canvas.repaint();

            long elapsed = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsed;

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    /**
     * Ferma l'esecuzione del loop principale.
     */
    public void stop() {
        running = false;
    }

    /**
     * Disabilita il loop di animazione.
     * Chiamare prima di {@link #run()} per disegni statici.
     * Il metodo {@link #loop()} non verrà chiamato.
     */
    public void noLoop() {
        looping = false;
    }

    /**
     * Cancella il contenuto del canvas.
     */
    public void clear() {
        canvas.clearBuffer();
    }

    /**
     * Forza un aggiornamento del display.
     */
    public void update() {
        canvas.repaint();
    }

    /**
     * Restituisce la larghezza dello schermo.
     *
     * @return la larghezza in pixel
     */
    public int screenWidth() {
        return canvas.getWidth();
    }

    /**
     * Restituisce l'altezza dello schermo.
     *
     * @return l'altezza in pixel
     */
    public int screenHeight() {
        return canvas.getHeight();
    }

    /**
     * Restituisce l'ultimo tasto premuto e lo resetta.
     * Restituisce null se nessun tasto è stato premuto dall'ultima chiamata.
     *
     * @return il nome del tasto premuto, o null
     */
    public String getLastKey() {
        String key = lastKey;
        lastKey = null;
        return key;
    }

    /**
     * Verifica se un tasto specifico è stato premuto.
     * Resetta lo stato del tasto se corrisponde.
     *
     * @param key il nome del tasto da verificare (es. "space", "a", "up")
     * @return true se il tasto è stato premuto
     */
    public boolean checkKey(String key) {
        if (lastKey != null && lastKey.equals(key.toLowerCase())) {
            lastKey = null;
            return true;
        }
        return false;
    }

    /**
     * Verifica se il mouse è stato cliccato dall'ultima chiamata.
     * Resetta lo stato dopo la lettura.
     *
     * @return true se il mouse è stato cliccato
     */
    public boolean isMouseClicked() {
        boolean clicked = mouseClicked;
        mouseClicked = false;
        return clicked;
    }

    /**
     * Restituisce la coordinata X dell'ultimo click del mouse.
     * Le coordinate sono relative al centro dello schermo.
     *
     * @return la coordinata X del click
     */
    public int getMouseX() {
        return mouseX;
    }

    /**
     * Restituisce la coordinata Y dell'ultimo click del mouse.
     * Le coordinate sono relative al centro dello schermo.
     *
     * @return la coordinata Y del click
     */
    public int getMouseY() {
        return mouseY;
    }

    /**
     * Restituisce il canvas di disegno.
     *
     * @return il TurtleCanvas
     */
    public TurtleCanvas getCanvas() {
        return canvas;
    }

    /**
     * Alias di {@link #run()} per compatibilità con Python turtle.
     */
    public void mainloop() {
        run();
    }
}
