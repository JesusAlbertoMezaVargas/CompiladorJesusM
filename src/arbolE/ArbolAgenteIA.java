

package arbolE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author Jesus Meza
 */


public class ArbolAgenteIA {
    Stack<Nodo> ArbolNodo;
    Stack<String> caracter;
    //identidicar enre operador y operando
    final String espacios ="\t";
    final String aritmeticos ="+-*()^=/";
    private ArrayList<String[]> tripletas;       
    final String variables ="abcdefghijklmnopqrstuvwxyz";
    final String opMultiplica="*";
    private Nodo raiz;
     String r;
    String[] temporales = {"T1","T2","T3","T4","T5"};
    HashMap<String,String> tablaSimbolos;
    HashMap<String,String> erroresSemanticos;
    HashMap<String,String> producciones;
        int paso = 0;
    String reglaSemantica;   
        
      public String emu86; //===15 de julio
        //1ro Julio 
        ArrayList <String> reglasEjecutadas;
    public ArbolAgenteIA(){
         emu86=";MEZA VARGAS JESUS ALBERTO\n"+
                    ".MODEL SMALL\n"+
                    ".STACK \n"+
                    ".DATA \n";
         
        reglasEjecutadas = new  ArrayList<String> ();//1ro Julio
        tripletas = new ArrayList<String[]>();
        tablaSimbolos = new HashMap();
       erroresSemanticos = new HashMap();
       producciones = new HashMap();
       reglaSemantica ="";
       r ="";
       ArbolNodo = new Stack<Nodo>();
       caracter  =  new Stack<String>(); 
         paso = 0;
    } //constructor
    
    //------- reglas ejecutadas ---- 1 Julio
 public HashMap<String, String> getTablaSimbolos() {
    return this.tablaSimbolos;
}
    public String getReglasEjecutadas(){
       String reglasE="";
        for (int i = 0; i<reglasEjecutadas.size(); i++) {
           System.out.println("Reglas ejecutadas" + reglasEjecutadas.get(i));
           reglasE +=  reglasEjecutadas.get(i)+"\n";
        }//for
                return reglasE;
    }  
    
    public void agregaValex (String lexema, String valor){
        tablaSimbolos.put(lexema, valor);   
    }//agrega valex
  
    public String regresaValex(String lexema){
        return this.tablaSimbolos.get(lexema);
    }//regreza valex
    
    public void guardar(){
       if (ArbolNodo.size() < 2||caracter.empty()  ) return;
       paso++;
       r = "r" + paso;
        Nodo izquierdo = ArbolNodo.pop();
        Nodo derecho = ArbolNodo.pop();
        String operador = caracter.pop();
        
        ArbolNodo.push(new Nodo(derecho,operador,izquierdo));
        
        String reglaE = "E.nodo = new Nodo(" + operador +",E1.nodo,T.nodo";
        reglasEjecutadas.add("p"+ paso + " " + reglaE);
        
    }//guardar
    
    public Nodo crear(String expresion){
        //1. Considerar la expresion como un conjunto de tokens
        StringTokenizer tokenizer;
        String token;
        //0. Inicializar valores para varias ejecuciones
        paso=0;//Paso de las reglas semanticas  
        reglaSemantica = ""; r ="";
        //2. Separacion de tokens de la expresion
        tokenizer = new StringTokenizer(expresion, espacios+aritmeticos+"/",true);
        //3. Mientras existan tokens
        while(tokenizer.hasMoreTokens()){
            //4. Omintir espacios en blanco
            token = tokenizer.nextToken();
            if(espacios.contains(token)) continue;
                //5. Se trata de un identificador
            if(aritmeticos.indexOf(token)<0){
           //no es un operador aritmetico
                //6. Extraer de la pila los terminos que estaban
                ArbolNodo.push(new Nodo(token));

if (!tablaSimbolos.containsKey(token)) {

    String valor = JOptionPane.showInputDialog(
            "Ingrese el valor para el identificador: " + token
    );

    agregaValex(token, valor);

    // Agregar variable y valor al código ensamblador
    emu86 += token + " dw " + valor + "\n";
}

paso++;

String regla = "T.nodo = new Hoja(id<" + token
        + ">,id.entrada_" + token + ")";

reglasEjecutadas.add("p" + paso + " " + regla);
               /* ArbolNodo.push(new Nodo(token));
            emu86+= token+" dw "+ valor +"\n";//15 de julio
                paso++;
                String regla ="T.nodo = new Hoja(id<"+token+">,id.entrada_"+token+")";
                reglasEjecutadas.add("p"+paso+""+regla);
                
                //solicitar el valor del token */
              
if (!tablaSimbolos.containsKey(token)) {

    String valor = javax.swing.JOptionPane.showInputDialog(
            "Ingrese el valor para el identificador: " + token +"");

    agregaValex(token, valor);
} //aqui acaba lo que agregue ahorita 
            }else  if(token.equals(")")){
                //7. Tratar tokens que no son parentesis
                    
                while(!caracter.empty()&& !caracter.peek().equals("(")){
                    guardar();
                            
                }//while
                caracter.pop();
                //if
            }else{
              if(!token.equals("(")&&!caracter.empty()){
                  String exa=(String)caracter.peek();
                  while(!caracter.empty() && !exa.equals("(") 
                          && aritmeticos.indexOf(exa)>=aritmeticos.indexOf(token)){
                            guardar();
                            if(!caracter.empty()){
                                exa=(String)caracter.peek();
                            }//IF !caracter.empty
                  }//while !exa
              }//if-token
              caracter.push(token);
            }//if else
            //8. Guardar el token
        }//while
        while(!caracter.empty()){
            if(caracter.peek().equals(")")){
                caracter.pop();
            }else{
                guardar();
                raiz=(Nodo) ArbolNodo.peek();
            }//else
        }//while !caracter.empty
        
       
        System.out.println("------ TABLA DE SIMBOLOS ------");

for (String clave : tablaSimbolos.keySet()) {
    System.out.println(clave + " = " + tablaSimbolos.get(clave));
}//nuevo 
        return raiz;
    }//crear
    
    //agregado nuevo 
    
    public Nodo convertirAGAD(Nodo raizAST) {
        HashMap<String, Nodo> tabla = new HashMap<>();
        return convertir(raizAST, tabla);
    }

    private Nodo convertir(Nodo n, HashMap<String, Nodo> tabla) {
        if (n == null) return null;

        if (n.getIzquierdo() == null && n.getDerecho() == null) {
            String clave = "HOJA#" + n.getDato();
            Nodo existente = tabla.get(clave);
            if (existente != null) return existente; // reutiliza
            tabla.put(clave, n);
            return n;
        }

        // Procesar hijos primero (post-orden): así al llegar al padre
        // ya sabemos si los hijos son nodos compartidos o no.
        Nodo izqNuevo = convertir(n.getIzquierdo(), tabla);
        Nodo derNuevo = convertir(n.getDerecho(), tabla);

        // Reasignar hijos (puede que ahora apunten a nodos ya existentes)
        n.setIzquierdo(izqNuevo);
        n.setDerecho(derNuevo);

        String clave = n.getDato() + "#" 
                     + System.identityHashCode(izqNuevo) + "#" 
                     + System.identityHashCode(derNuevo);

        Nodo existente = tabla.get(clave);
        if (existente != null) return existente; 

        tabla.put(clave, n);
        return n;
    }
    
    
    //
    private int obtenerPrioridad(String operador){
     switch(operador){
         case " ":
             return 3;
         case "*": case "/":
             return 2;
          case "+": case "-":
             return 2;    
         case "=":   
             return 0;
             default:
             return -1;
                     
     }   
    }
    
    
    //agregado equipo
    public ArrayList<String[]> getTripletas() {
    return tripletas;
}

public void generarTripletas(Nodo nodo) {
    tripletas.clear();
    generarTripletasRecursivo(nodo);
}

private String generarTripletasRecursivo(Nodo nodo) {
    if (nodo == null) {
        return "";
    }

    // Si es una hoja: variable o número
    if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
        return nodo.getDato();
    }

    String arg1 = generarTripletasRecursivo(nodo.getIzquierdo());
    String arg2 = generarTripletasRecursivo(nodo.getDerecho());

    tripletas.add(new String[]{
        nodo.getDato(), // Operador
        arg1,           // Argumento 1
        arg2            // Argumento 2
    });

    // Referencia a la posición de la tripleta creada
    return "[" + (tripletas.size() - 1) + "]";
}
    //
}//fin clase



