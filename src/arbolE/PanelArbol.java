
package arbolE;

import arbolE.Nodo;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.HashMap;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Meza Vargas 
 * MÉTODO PARA DIBUJAR ÁRBOL GRÁFICO
 * INSTRUCCIONES:
 * a. Solicitar el ancho y color de las líneas
 * b. Solicitar el ancho y color de los nodos
 * c. Decorarlo con contenido del nodo
 * d. Agregar el método ON CLOSE con la opción de this.dispose() para EVITAR
 *    que cierre el proyecto.
 */


public class PanelArbol extends JPanel {

    private final Nodo raiz;
    private int radio;
    private int anchoLinea;
    private Color colorLinea;
    private Color colorIzquierdo;
    private Color colorDerecho;
    private final int ESPACIO_VERTICAL = 60;
    private HashMap<String, String> tablaSimbolos; 

    public PanelArbol(Nodo raiz, int radio, int anchoLinea, Color colorLinea, 
                      Color colorIzquierdo, Color colorDerecho, HashMap<String, String> tablaSimbolos) {
        this.raiz = raiz;
        this.radio = radio;
        this.anchoLinea = anchoLinea;
        this.colorLinea = colorLinea;
        this.colorIzquierdo = colorIzquierdo;
        this.colorDerecho = colorDerecho;
        this.tablaSimbolos = tablaSimbolos;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (raiz != null) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setStroke(new BasicStroke(anchoLinea));
            dibujarNodo(g2, raiz, getWidth() / 2, 40, getWidth() / 4);
        }
    }

    private void dibujarNodo(Graphics2D g, Nodo nodo, int x, int y, int espacioHorizontal) {
        if (nodo == null) return;

        // Dibujar líneas
        g.setColor(colorLinea);
        if (nodo.getIzquierdo() != null) {
            g.drawLine(x, y, x - espacioHorizontal, y + ESPACIO_VERTICAL);
            dibujarNodo(g, nodo.getIzquierdo(), x - espacioHorizontal, y + ESPACIO_VERTICAL, espacioHorizontal / 2);
        }
        if (nodo.getDerecho() != null) {
            g.drawLine(x, y, x + espacioHorizontal, y + ESPACIO_VERTICAL);
            dibujarNodo(g, nodo.getDerecho(), x + espacioHorizontal, y + ESPACIO_VERTICAL, espacioHorizontal / 2);
        }

        int ancho = 80;
        int alto = 30;
        g.setColor(colorIzquierdo);
        g.fillRect(x - ancho / 2, y - alto / 2, ancho / 2, alto);

        g.setColor(colorDerecho);
        g.fillRect(x, y - alto / 2, ancho / 2, alto);

        // Bordes
        g.setColor(Color.BLACK);
        g.drawRect(x - ancho / 2, y - alto / 2, ancho, alto);
        g.drawLine(x, y - alto / 2, x, y + alto / 2);

        FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.BLACK);

        // LADO IZQUIERDO:

        String izquierda = nodo.getDato(); 
        int anchoIzq = fm.stringWidth(izquierda);
        g.drawString(izquierda, x - ancho / 4 - anchoIzq / 2, y + fm.getAscent() / 4);

        // LADO DERECHO: 
        String derecha = "";
        
        if ("+-*/^=".contains(nodo.getDato()) && nodo.getDato().length() == 1) {
            derecha = String.valueOf(evaluarNodo(nodo));
        } else {
            // Si es una variable simple (a, b, x), buscamos su valor en la tabla
            if (tablaSimbolos != null && tablaSimbolos.containsKey(nodo.getDato())) {
                derecha = tablaSimbolos.get(nodo.getDato());
            } else {
                derecha = nodo.getDato(); // Por si ya es un número directo
            }
        }

        int anchoDer = fm.stringWidth(derecha);
        g.drawString(derecha, x + ancho / 4 - anchoDer / 2, y + fm.getAscent() / 4);
    }

    // Método auxiliar para resolver las operaciones de los nodos y dar el resultado real
    private double evaluarNodo(Nodo nodo) {
        if (nodo == null) return 0;
        
        // Si es una hoja (variable), buscar su valor numérico
        if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
            try {
                if (tablaSimbolos != null && tablaSimbolos.containsKey(nodo.getDato())) {
                    return Double.parseDouble(tablaSimbolos.get(nodo.getDato()));
                }
                return Double.parseDouble(nodo.getDato());
            } catch (Exception e) {
                return 0; // Si no se puede parsear
            }
        }

        // Evaluar recursivamente los hijos
        double valIzq = evaluarNodo(nodo.getIzquierdo());
        double valDer = evaluarNodo(nodo.getDerecho());

        // Aplicar la operación del símbolo
        switch (nodo.getDato()) {
            case "+": return valIzq + valDer;
            case "-": return valIzq - valDer;
            case "*": return valIzq * valDer;
            case "/": return valDer != 0 ? valIzq / valDer : 0;
            case "^": return Math.pow(valIzq, valDer);
            default: return valDer; // Para el "=" u otros casos
        }
    }
}


/*public class PanelArbol extends JPanel {
    private final Nodo raiz;
    private final int RADIO = 20;
    private final int ESPACIO_VERTICAL = 60;
    //----------------CREE UNAS VARIABLES ---------------------------------
    private float anchoLinea;
    private int radio;
    private Color colorLinea;
    private Color colorNodo;

//cambie el constructor PARA PREGUNTAR LO DE INGRESO las medidas y color
public PanelArbol(Nodo raiz) {
    this.raiz = raiz;

    setBackground(Color.WHITE);

    anchoLinea = Float.parseFloat(
            JOptionPane.showInputDialog("Ingrese el ancho de las líneas:"));

    colorLinea = JColorChooser.showDialog(
            null,
            "Seleccione el color de las líneas",
            Color.BLACK);

    radio = Integer.parseInt(
            JOptionPane.showInputDialog("Ingrese el radio de los nodos:"));

    colorNodo = JColorChooser.showDialog(
            null,
            "Seleccione el color de los nodos",
            new Color(173,216,230));

    if(colorLinea == null)
        colorLinea = Color.BLACK;

    if(colorNodo == null)
        colorNodo = new Color(173,216,230);
}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (raiz != null) {
            
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                    RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2.setStroke(new BasicStroke(anchoLinea));
            
            // INICIA DESDE EL CENTRO SUPERIOR
            dibujarNodo(g2, raiz, getWidth() / 2, 40, getWidth() / 4);
        }
    }

    
   
 private void dibujarNodo(Graphics2D g, Nodo nodo, int x, int y, int espacioHorizontal) {

    if (nodo == null)
        return;

    // ================= HIJO IZQUIERDO =================
    if (nodo.getIzquierdo() != null) {

        g.setColor(colorLinea);
        g.drawLine(x, y, x - espacioHorizontal, y + ESPACIO_VERTICAL);

        dibujarNodo(
                g,
                nodo.getIzquierdo(),
                x - espacioHorizontal,
                y + ESPACIO_VERTICAL,
                Math.max(40, espacioHorizontal / 2));
    }

    // ================= HIJO DERECHO =================
    if (nodo.getDerecho() != null) {

        g.setColor(colorLinea);
        g.drawLine(x, y, x + espacioHorizontal, y + ESPACIO_VERTICAL);

        dibujarNodo(
                g,
                nodo.getDerecho(),
                x + espacioHorizontal,
                y + ESPACIO_VERTICAL,
                Math.max(40, espacioHorizontal / 2));
    }

    //==================== DIBUJAR NODO ====================

    int ancho = 60;
    int alto = 50;

    // Fondo
    g.setColor(colorNodo);
    g.fillRect(x - ancho / 2, y - alto / 2, ancho, alto);

    // Borde
    g.setColor(Color.BLACK);
    g.drawRect(x - ancho / 2, y - alto / 2, ancho, alto);

    // Línea divisoria
    g.drawLine(
            x - ancho / 2,
            y,
            x + ancho / 2,
            y
    );

    FontMetrics fm = g.getFontMetrics();

    //==================== DATO ====================

    String dato = nodo.getDato();

    int anchoDato = fm.stringWidth(dato);

    g.setColor(Color.RED);

    g.drawString(
            dato,
            x - anchoDato / 2,
            y - 8
    );

    //==================== VALOR ====================

    String valor = nodo.getValor();

    if (valor == null)
        valor = "0";

    int anchoValor = fm.stringWidth(valor);

    g.setColor(Color.BLUE);

    g.drawString(
            valor,
            x - anchoValor / 2,
            y + 18
    );
}
}//fin de la clase */