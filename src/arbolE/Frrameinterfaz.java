/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package arbolE;

//import arbolexpresiones.ArbolExpresiones;
import java.awt.Color;
import java.awt.Image;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Desktop;
import java.io.File;
import java.net.URI;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Jesus Meza
 */
public class Frrameinterfaz extends javax.swing.JFrame {
    String nPolaca;
    JFrame ventana;
     int Contador =0;
    String emuLocal = "";//15 de julio
    String izquierdo, derecho;//15 de julio
    FrameCuadruplos cuadruplos;//equipo
    int temp;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Frrameinterfaz.class.getName());

    /**
     * Creates new form Frrameinterfaz
     */
public Frrameinterfaz() {
    initComponents();
    nPolaca = "";
    temp=0;
   
    String emuLocal = "";//15 de julio
    izquierdo = "";//15 de julio
    derecho = "";//15 de julio
    FrameCuadruplos cuadruplos;//equipo
    // Imagen 1
    ImageIcon icono = new ImageIcon(getClass().getResource("/arbolE/Logo tec.png"));
    Image imagen = icono.getImage();
    Image imagenEscalada = imagen.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    jLabel10.setIcon(new ImageIcon(imagenEscalada));

    // Imagen 2
    ImageIcon icono2 = new ImageIcon(getClass().getResource("/arbolE/Foto Personal.jpeg"));
    Image imagen2 = icono2.getImage();
    Image imagenEscalada2 = imagen2.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    jLabel12.setIcon(new ImageIcon(imagenEscalada2));
}

public void generarEmutasm(String emu, int i) {
    try {
        // Crear el archivo
        File archivoAsm = new File("e" + i + ".asm");

        FileWriter escritor = new FileWriter(archivoAsm);
        escritor.write(emu);
        escritor.close();

        System.out.println("Archivo creado: " + archivoAsm.getAbsolutePath());

        // Ruta donde está instalado emu8086
        String rutaEmu8086 = "C:\\emu8086\\emu8086.exe";//modificar aqui

        // Abrir el archivo .asm directamente en emu8086
        ProcessBuilder proceso = new ProcessBuilder(
                rutaEmu8086,
                archivoAsm.getAbsolutePath()
        );

        proceso.start();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
                null,
                "Error al crear o abrir el archivo en emu8086:\n" 
                + e.getMessage()
        );
        e.printStackTrace();
    }
}

public void Sonido(){
    //sonido
                try {
                    File sonido = new File("C:\\Users\\Jesus Meza\\OneDrive\\Documentos\\VERANO2026\\AUTOMATAS 2\\ArbolExpresiones\\src\\arbolE\\new-notification-022-370046.wav");
                    if (sonido.exists()) {
                        AudioInputStream audioStream = AudioSystem.getAudioInputStream(sonido);
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioStream);
                        clip.start(); 
                    } else {
                        showMessageDialog(null, "No se encontró el archivo de sonido.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showMessageDialog(null, "Error al reproducir el sonido.");
                }
}
public void inOrden(Nodo n){//izq raiz Der
 if(n!=null){
     inOrden(n.getIzquierdo());
        jTextinOrden.append(n.getDato()+"\n");
        nPolaca+=jNotacionPolaca.getText()+n.getDato()+ " ";
        jNotacionPolaca.setText(jNotacionPolaca.getText()+
                n.getDato());
        
        inOrden(n.getDerecho());
        
        //15 de julio
        
        switch(n.getDato()){
            
            case "+":
                izquierdo = n.getIzquierdo().getDato();
                derecho = n.getDerecho().getDato();
                System.out.println ("ADD Meza VARGAS");
                emuLocal += "MOV AX, "+ n.getIzquierdo().getDato()+"\n";
                emuLocal += "MOV BX, "+ n.getDerecho().getDato()+"\n";
                emuLocal += "ADD AX,BX"+"\n\n";
            break;
            case "-": 
                izquierdo = n.getIzquierdo().getDato();
                derecho = n.getDerecho().getDato();
                System.out.println("SUB");
                emuLocal += "MOV AX, "+ n.getIzquierdo().getDato()+"\n";
                emuLocal += "MOV BX, "+ n.getDerecho().getDato()+"\n";
                emuLocal += "ADD AX,BX"+"\n\n";
            break;
              case "/": 
                  izquierdo = n.getIzquierdo().getDato();
                  derecho = n.getDerecho().getDato();
                  System.out.println("DIV");
                  emuLocal += "MOV AX, "+ n.getIzquierdo().getDato()+"\n";
                  emuLocal += "MOV BX, "+ n.getDerecho().getDato()+"\n";
                  emuLocal += "ADD AX,BX"+"\n\n";
            break;
            case "*": 
                izquierdo = n.getIzquierdo().getDato();
                derecho = n.getDerecho().getDato();
                System.out.println("MUL");
                emuLocal += "MOV AX, "+ n.getIzquierdo().getDato()+"\n";
                emuLocal += "MOV BX, "+ n.getDerecho().getDato()+"\n";
                emuLocal += "ADD AX,BX"+"\n\n";
        }//switch
    }//if
}//inorden

public void preOrden(Nodo n){//raiz izq der
    if(n!=null){
        jTxtPreOrden.append(n.getDato()+"\n");
        nPolaca+=jNotacionPolaca.getText()+n.getDato()+ " ";
        jNotacionPolaca.setText(jNotacionPolaca.getText()+
                n.getDato());
        preOrden(n.getIzquierdo());
        preOrden(n.getDerecho());
    }//if
}
  public void postOrden(Nodo n){//izq Der raiz
    if(n!=null){
         postOrden(n.getIzquierdo());
        postOrden(n.getDerecho());
        jTextPostOrden.append(n.getDato()+"\n");
        nPolaca+=jNotacionPolaca.getText()+n.getDato()+ " ";
        jNotacionPolaca.setText(jNotacionPolaca.getText()+
                n.getDato());
       
    }//if
}
  
  public void intermedioIA(Nodo n) {
    if (n == null) return;

    // Recorrido Postorden (Izquierdo -> Derecho -> Raíz)
    intermedio(n.getIzquierdo());
    intermedio(n.getDerecho());

    Nodo izq = n.getIzquierdo();
    Nodo der = n.getDerecho();
    String dato = n.getDato();

    // CASO 1: Es una hoja (Variable o Constante)
    if (izq == null && der == null) {
        n.setLugar(dato); // Eliminamos el espacio innecesario al final
        n.setCodigoIntermedio("");
        return;
    }

    // CASO 2: Es un operador aritmético
    if (esOperador(dato)) {
        temp++;
        n.setLugar("T" + temp);

        StringBuilder sb = new StringBuilder();
        // Arrastra el código intermedio generado por los hijos (si existe)
        sb.append(izq.getCodigoIntermedio()).append(" ")
          .append(der.getCodigoIntermedio()).append(" ")
          // Agrega la nueva instrucción de tres direcciones
          .append(n.getLugar()).append(" = ").append(izq.getLugar()).append(" ").append(dato).append(" ").append(der.getLugar());

        // Normalizamos los espacios duplicados y añadimos salto de línea
        n.setCodigoIntermedio(sb.toString().trim().replaceAll(" +", " ") + "\n");
    } 
    // CASO 3: Es una asignación
    else if (dato.equals("=")) {
        StringBuilder sb = new StringBuilder();
        // Arrastra el código del subárbol derecho (la expresión matemática)
        sb.append(der.getCodigoIntermedio())
          // Genera la asignación final: id = lugar_de_la_expresión
          .append(izq.getLugar()).append(" = ").append(der.getLugar()).append("\n");

        n.setCodigoIntermedio(sb.toString().trim().replaceAll(" +", " ") + "\n");
    }
}

// Método auxiliar para mantener limpio el flujo principal
private boolean esOperador(String dato) {
    return dato.equals("+") || dato.equals("-") || dato.equals("*") || dato.equals("/");
}
  public void intermedio(Nodo n){
      if(n!=null){
          intermedio(n.getIzquierdo());
          intermedio(n.getDerecho());
          
      if(n.getIzquierdo()==null && n.getDerecho()==null){
          n.setLugar(n.getDato()+" ");
          n.setCodigoIntermedio("");
      }else{
          if(n.getDato().equals("+")|| n.getDato().equals("*")
                  ||n.getDato().equals("-")||n.getDato().equals("/")){
              temp++;
              n.setLugar("T" + temp);
              Nodo izquierdo = n.getIzquierdo();
              Nodo derecho = n.getDerecho();
              String codigoI = "";
              codigoI = izquierdo.getCodigoIntermedio()+ " " + derecho.getCodigoIntermedio() + 
                      " " + n.getLugar()+ "="+izquierdo.getLugar()+
                     "" + n.getDato()+ " " +derecho.getLugar();
              
              n.setCodigoIntermedio(codigoI+"\n");
              
              }else{
                      if(n.getDato().equals("=")){
                      String codigoI= "";
                      Nodo izquierdo = n.getIzquierdo();
                      Nodo derecho = n.getDerecho();
                      codigoI = derecho.getDato()+ " "+
                              izquierdo.getLugar()+ " = "+temp+ "\n";
                      n.setCodigoIntermedio(codigoI);
                      }//EQUALS =
      }//equals +-*/
  }//getDerecho getIzquierdo
  }//n!null
  }//intermedio

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jNotacionPolaca = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextTresDirecciones = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextinOrden = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTxtPreOrden = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextPostOrden = new javax.swing.JTextArea();
        AgenteIA = new javax.swing.JButton();
        OptimizaIntermedio = new javax.swing.JButton();

        jTextArea5.setColumns(20);
        jTextArea5.setRows(5);
        jScrollPane5.setViewportView(jTextArea5);

        jPanel2.setBackground(new java.awt.Color(0, 51, 51));
        jPanel2.setForeground(new java.awt.Color(0, 51, 102));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Arbol de expresiones -LyA2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setForeground(new java.awt.Color(0, 51, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(650, 180));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Arbol de expresiones -LyA2");
        jLabel1.setPreferredSize(new java.awt.Dimension(150, 16));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/arbolE/Logo tec.png"))); // NOI18N

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/arbolE/Foto Personal.jpeg"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(725, 725, 725)
                .addComponent(jLabel11)
                .addContainerGap(149, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54))))
        );

        jLayeredPane1.setPreferredSize(new java.awt.Dimension(720, 280));

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jScrollPane4.setViewportView(jTextArea4);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setForeground(new java.awt.Color(0, 51, 102));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("notacion polaca");

        jButton2.setText("Codigo de 3 direcciones");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setText("Codigo P");

        jButton4.setText("Tabla de simbolos");

        jPanel4.setBackground(new java.awt.Color(255, 204, 51));
        jPanel4.setForeground(new java.awt.Color(0, 51, 102));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Pre Orden");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("In Orden");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Post Orden");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Regla semantica");

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Codigo 3 direcciones");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(137, 137, 137)
                .addComponent(jLabel6)
                .addGap(70, 70, 70)
                .addComponent(jLabel7)
                .addGap(107, 107, 107)
                .addComponent(jLabel8)
                .addGap(123, 123, 123)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jButton6.setText("Clean");

        jButton7.setText("Cuadruplos");
        jButton7.addActionListener(this::jButton7ActionPerformed);

        jButton8.setText("Tripletas");

        jButton5.setText("Asignacion Individual");
        jButton5.addActionListener(this::jButton5ActionPerformed);

        jButton9.setText("Codigo de 3 direcciones");
        jButton9.addActionListener(this::jButton9ActionPerformed);

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Explicacion de lo siguiente en cada boton");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel13)
                        .addGap(33, 33, 33)
                        .addComponent(jButton5))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jNotacionPolaca, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addGap(82, 82, 82)
                        .addComponent(jButton6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jNotacionPolaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton7)
                        .addComponent(jButton9)
                        .addComponent(jButton8)
                        .addComponent(jButton4)
                        .addComponent(jButton3))
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(jButton5)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        jLabel2.setText("Expresiones");

        jButton1.setText("Compila");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jTextTresDirecciones.setColumns(20);
        jTextTresDirecciones.setRows(5);
        jScrollPane6.setViewportView(jTextTresDirecciones);

        jTextinOrden.setColumns(20);
        jTextinOrden.setRows(5);
        jScrollPane1.setViewportView(jTextinOrden);

        jTxtPreOrden.setColumns(20);
        jTxtPreOrden.setRows(5);
        jTxtPreOrden.setToolTipText("");
        jScrollPane2.setViewportView(jTxtPreOrden);

        jTextPostOrden.setColumns(20);
        jTextPostOrden.setRows(5);
        jScrollPane7.setViewportView(jTextPostOrden);

        AgenteIA.setText("Agente IA");
        AgenteIA.addActionListener(this::AgenteIAActionPerformed);

        OptimizaIntermedio.setText("Optimiza Intermedio");
        OptimizaIntermedio.addActionListener(this::OptimizaIntermedioActionPerformed);

        jLayeredPane1.setLayer(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jTextField1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(AgenteIA, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(OptimizaIntermedio, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AgenteIA, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(OptimizaIntermedio, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(AgenteIA)
                    .addComponent(OptimizaIntermedio))
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 874, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 852, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String datos ="";
        ArbolJesusM a = new ArbolJesusM();
        datos = jTextField1.getText();
        
        Nodo  arbolExpresion = a.crear(datos);
        jTextArea4.append (a.getReglaEjecutadas());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void AgenteIAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgenteIAActionPerformed
    
        String datos = "";
    ArbolAgenteIA arbol = new ArbolAgenteIA();

    datos = jTextField1.getText();

    Nodo arbolExpresion = arbol.crear(datos);
    jTextArea4.append(arbol.getReglasEjecutadas());
    try {
    FileWriter archivo = new FileWriter("reglas.txt");
    archivo.write(arbol.getReglasEjecutadas());
    archivo.close();
       System.out.println("Archivo creado.");
} catch (IOException e) {
    e.printStackTrace();
}


    // Solicitar ancho de línea
    int anchoLinea = Integer.parseInt(
            JOptionPane.showInputDialog("Ingrese el ancho de las líneas:"));

    // Solicitar tamaño del nodo
    int radio = Integer.parseInt(
            JOptionPane.showInputDialog("Ingrese el tamaño (radio) de los nodos:"));
    // Opciones de colores para las líneas
    String[] coloresLinea = {
        "Negro",
        "Azul",
        "Rojo",
        "Verde",
        "Gris"
    };

    String opcionLinea = (String) JOptionPane.showInputDialog(
            null,
            "Seleccione el color de las líneas:",
            "Color de líneas",
            JOptionPane.QUESTION_MESSAGE,
            null,
            coloresLinea,
            coloresLinea[0]);

    Color colorLinea;

    switch (opcionLinea) {
        case "Azul":
            colorLinea = Color.BLUE;
            break;
        case "Rojo":
            colorLinea = Color.RED;
            break;
        case "Verde":
            colorLinea = Color.GREEN;
            break;
        case "Gris":
            colorLinea = Color.GRAY;
            break;
        default:
            colorLinea = Color.BLACK;
    }

    // Opciones de colores para los nodos
    String[] coloresNodo = {
        "Azul claro",
        "Verde claro",
        "Amarillo",
        "Naranja",
        "Rosa",
        "Blanco"
    };

    String opcionNodo = (String) JOptionPane.showInputDialog(
            null,
            "Seleccione el color de los nodos:",
            "Color de nodos",
            JOptionPane.QUESTION_MESSAGE,
            null,
            coloresNodo,
            coloresNodo[0]);

   // Color colorNodo;
Color colorIzquierdo;
Color colorDerecho ;
colorDerecho  = Color.white;


switch (opcionNodo) {
    case "Verde claro":
        colorIzquierdo = new Color(144, 238, 144);
        break;
    case "Amarillo":
        colorIzquierdo = Color.YELLOW;
        break;
    case "Naranja":
        colorIzquierdo = new Color(255, 200, 120);
        break;
    case "Rosa":
        colorIzquierdo = Color.PINK;
        break;
    case "Blanco":
        colorIzquierdo = Color.WHITE;
        break;
    default:
        colorIzquierdo = new Color(173, 216, 230); // Azul claro
}

    JFrame ventana = new JFrame("Visualizador de Árboles - LyA2");

    /*PanelArbol panel = new PanelArbol(
            arbolExpresion,
            radio,
            anchoLinea,
            colorLinea,
            colorNodo); */
PanelArbol panel = new PanelArbol(
        arbolExpresion,
        radio,
        anchoLinea,
        colorLinea,
        colorIzquierdo,
        colorDerecho,
        arbol.getTablaSimbolos() // <-- Esto conecta los valores con el diseño
);
    ventana.add(panel);
    ventana.setSize(600, 400);
    ventana.setLocationRelativeTo(null);
    ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    ventana.setVisible(true);
    //
    
   
    preOrden(arbolExpresion);
    inOrden(arbolExpresion);
    postOrden(arbolExpresion);
    
    
    arbol.emu86+=".CODE \n"+
                "MOV AX, @DATA \n"+
            "MOV DS, AX \n";
    
    String  finalEmu = arbol.emu86 + this.emuLocal;
    finalEmu +="\n mov AX, ac0h \n"+
            "int 21h \n end";
    
    showMessageDialog(null,finalEmu);
        Contador++;
        generarEmutasm(finalEmu, Contador);
        Sonido();
    
    //intermedio(arbolExpresion);
    intermedioIA(arbolExpresion);
    
    jTextTresDirecciones.append(arbolExpresion.getCodigoIntermedio());
    
//otro equipo agrego esto
  PanelGrafo panelGrafo = new PanelGrafo(
    arbol.convertirAGAD(arbolExpresion),
    Color.GREEN,
    25,
    Color.BLACK,
    2.0f
);

JFrame ventanaGrafo = new JFrame("Visualizador del Grafo");

ventanaGrafo.add(panelGrafo);
ventanaGrafo.setSize(800, 600);
ventanaGrafo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
ventanaGrafo.setLocationRelativeTo(null);
ventanaGrafo.setVisible(true);

//OTRO EQUIPO
cuadruplos = new FrameCuadruplos();
       cuadruplos.setVisible(true);

//otro equipo agrego esto
// Generar las tripletas a partir del árbol
arbol.generarTripletas(arbolExpresion);

// Abrir la ventana de tripletas
FrameTripletas ventanaTripletas =
        new FrameTripletas(arbol.getTripletas());

ventanaTripletas.setVisible(true);
    }//GEN-LAST:event_AgenteIAActionPerformed

    private void OptimizaIntermedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OptimizaIntermedioActionPerformed
           try {
        Desktop.getDesktop().browse(new URI("https://drive.google.com/file/d/1RlqtI3OKb86NO79tUvudQy0ytSXSlzf6/view?usp=sharing"));
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "No se pudo abrir el enlace.");
        e.printStackTrace();
    }
    }//GEN-LAST:event_OptimizaIntermedioActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       FrameDirecciones ventanaIntermedio = new FrameDirecciones();
    ventanaIntermedio.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       SSADiagrama ventana = new SSADiagrama();
ventana.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
    //borrar aqui   // java.awt.EventQueue.invokeLater(() -> new Frrameinterfaz().setVisible(true));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AgenteIA;
    private javax.swing.JButton OptimizaIntermedio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JTextField jNotacionPolaca;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea jTextPostOrden;
    private javax.swing.JTextArea jTextTresDirecciones;
    private javax.swing.JTextArea jTextinOrden;
    private javax.swing.JTextArea jTxtPreOrden;
    // End of variables declaration//GEN-END:variables
}
