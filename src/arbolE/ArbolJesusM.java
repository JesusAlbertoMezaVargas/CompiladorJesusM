
package arbolE;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *
 * @author Jesus Meza
 */
public class ArbolJesusM {
    Stack<Nodo> ArbolNodo;
    Stack<String> caracter;
    
    final String espacios="\t";
    final String aritmeticos = "+-*()^=";
    final String variables = "abcefghijklmnopqrstuvwxyz";
    final String opMultiplica = "*";
    private Nodo raiz;
    
//30 de junio 2026
    String[] temporales = {"T1","T2","T3","T4","T5"};
    HashMap<String, String> tablaSimbolos;
    HashMap<String, String> erroresSemanticos;
    HashMap<String,String> producciones;
    
    int paso;
    
    //constructor
    //==========1RO DE JULIO
    ArrayList<String> reglasEjecutadas;
    public ArbolJesusM (){
            reglasEjecutadas = new ArrayList<String>();//1RO DE JULIO
            tablaSimbolos = new HashMap();
            erroresSemanticos = new HashMap();
            producciones = new HashMap();
            
            ArbolNodo = new Stack<Nodo>();
            caracter = new Stack<String>();
            
            paso=0;
    }//constructor
    
    //*********REGLAS EJECUTADAS =====1RO DE JULIO
    public String getReglaEjecutadas(){
        String reglasE="";
  
        for(int i=0;i<reglasEjecutadas.size();i++){
            System.out.println("Reglas ejecutadas"+
                    reglasEjecutadas.get(i));
            reglasE+=reglasEjecutadas.get(i)+"\n";
        
    }//for
        return reglasE;
    }
    public void agregaValex (String lexema, String valor){
        
    }//agregarValex-Analisis semantico
    
    public String regresaValex (String lexema){
        return this.tablaSimbolos.get(lexema);
       
    
    }//regresaValex
   
 //Invertigar que es lo que hace peek    (1ro de julio)
 //nos ayudara siempre a clarificar  el manejo de los streams algo que necesitaremos 
 //en muchas ocasiones
    
    //**********************CODIGO OPTIMIZADO*************************************************
    
//MEZA VARGAS JESUS ALBERTO
public void guardar() {
    paso++;

    Nodo izquierdo = ArbolNodo.pop();
    Nodo derecho = ArbolNodo.pop();
    String operador = caracter.pop();

    ArbolNodo.push(new Nodo(derecho, operador, izquierdo));

    reglasEjecutadas.add(
        "p" + paso + " E.nodo = new Nodo(" + operador + ", E1.nodo, T.nodo)");
}//guardar
    
    
public Nodo crear(String expresion) {

    paso = 0;
    raiz = null;
    ArbolNodo.clear();
    caracter.clear();
    reglasEjecutadas.clear();

    StringTokenizer tokenizer =
            new StringTokenizer(expresion, espacios + aritmeticos, true);

    while (tokenizer.hasMoreTokens()) {

        String token = tokenizer.nextToken();
        System.out.println("Token " + token);

        if (espacios.contains(token))
            continue;

        if (!aritmeticos.contains(token)) {
            ArbolNodo.push(new Nodo(token));
            paso++;
            reglasEjecutadas.add("p" + paso
                    + " T.nodo = new Hoja(id<"
                    + token + ">, id.entrada_" + token + ")");
            continue;
        }//if aritmeticos

        if (token.equals(")")) {
            while (!caracter.empty() && !caracter.peek().equals("("))
                guardar();

            if (!caracter.empty())
                caracter.pop();

            continue;
        }//if token"("

        if (!token.equals("("))
            while (!caracter.empty()
                    && !caracter.peek().equals("(")
                    && aritmeticos.contains(caracter.peek())
                    && aritmeticos.indexOf(caracter.peek()) >= aritmeticos.indexOf(token))
                guardar();

        caracter.push(token);
    }//while tokenizer

    while (!caracter.empty())
        if (caracter.peek().equals("("))
            caracter.pop();
        else
            guardar();

    if (!ArbolNodo.empty())
        raiz = ArbolNodo.peek();

    return raiz;
}//crear
    
}//ArbolJesusM    
   




//******************************Anterior codico*********************************************
/*public void guardar() {
    paso++;

    Nodo izquierdo = ArbolNodo.pop();
    Nodo derecho = ArbolNodo.pop();
    String operador = caracter.pop();

    ArbolNodo.push(new Nodo(derecho, operador, izquierdo));

 if(operador.equals("+")){
        reglasEjecutadas.add("p" + paso + " E.nodo = new Nodo(+, E1.nodo, T.nodo)");
 }else if (operador.equals("-")){
        reglasEjecutadas.add("p" + paso + " E.nodo = new Nodo(-, E1.nodo, T.nodo)");
 }else if (operador.equals("*")){
        reglasEjecutadas.add("p" + paso + " E.nodo = new Nodo(*, E1.nodo, T.nodo)");
}else if (operador.equals("/")){
        reglasEjecutadas.add("p" + paso + " E.nodo = new Nodo(/, E1.nodo, T.nodo)");
}//ELSE IF
}//GUARDAR

    
    
    
   public Nodo crear(String expresion) {
  //invetigacion.2 1ro de julio
  // Este metodo nos sirve para cuando se pasa una 
  //cadena poder dividirla en partes mas pequeñas.
  
  //0. inicializar valores para varias ejecuciones
    paso = 0;
    raiz = null;
    ArbolNodo.clear();
    caracter.clear();
    reglasEjecutadas.clear();
//2. separacion de tokens de la expresion
    StringTokenizer tokenizer = new StringTokenizer(expresion, espacios + aritmeticos, true);
//3. mientras existan tokens
    while (tokenizer.hasMoreTokens()) {
        
        String token = tokenizer.nextToken();
        System.out.println("Token " + token);
//4.omitir espacios en blanco
        if (espacios.contains(token))
            continue;

        if (!aritmeticos.contains(token)) {
            ArbolNodo.push(new Nodo(token));
            paso++;
            reglasEjecutadas.add("p" + paso + " T.nodo = new Hoja(id<" + token + ">, id.entrada_" + token + ")");
            continue;
        }//ELSE TOKEN ARITMETICOS

        if (token.equals(")")) {
            while (!caracter.empty() && !caracter.peek().equals("("))
                guardar();

            if (!caracter.empty())
                caracter.pop();

            continue;
        }//IF TOKEN ")"

        if (!token.equals("(")) {
            while (!caracter.empty()
                    && !caracter.peek().equals("(")
                    && aritmeticos.contains(caracter.peek())
                    && aritmeticos.indexOf(caracter.peek()) >= aritmeticos.indexOf(token))
                guardar();
        }//IF TOKEN "("

        caracter.push(token);
    }//WHILE

    while (!caracter.empty()) {
        if (caracter.peek().equals("("))
            caracter.pop();
        else
            guardar();
    }//WHILE

    if (!ArbolNodo.empty())
        raiz = ArbolNodo.peek();

    return raiz;
}//NODO CREAR
 
 */   
    
    
    

