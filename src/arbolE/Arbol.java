
package arbolE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *
 * @author Jesus Meza
 */
public class Arbol {
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
    public Arbol(){
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
    
    public void guardar(){//permite construir el arbol
        paso++;
        
        Nodo izquierdo = (Nodo) ArbolNodo.pop();
        Nodo derecho = (Nodo) ArbolNodo.pop();
        
        String operador = caracter.peek();
        //Invertigar que es lo que hace peek    (1ro de julio)
        //nos ayudara siempre a clarificar  el manejo de los streams algo que necesitaremos 
        //en muchas ocasiones
        ArbolNodo.push(new Nodo(derecho,caracter.pop(),izquierdo));
        
        if(operador.equals("+")){
            String reglaE = "E.nodo = new Nodo(+, E1.nodo, T.nodo)";
            reglasEjecutadas.add("p"+paso+ " " + reglaE);
            
        }//if el operador +
        //id para el -
    if(operador.equals("-")){
            String reglaE = "E.nodo = new Nodo(-, E1.nodo, T.nodo)";
            reglasEjecutadas.add("p"+paso+ " " + reglaE);
            
        }//if el operador -
    if(operador.equals("*")){
            String reglaE = "E.nodo = new Nodo(*, E1.nodo, T.nodo)";
            reglasEjecutadas.add("p"+paso+ " " + reglaE);
            
        }//if el operador *
    if(operador.equals("/")){
            String reglaE = "E.nodo = new Nodo(/, E1.nodo, T.nodo)";
            reglasEjecutadas.add("p"+paso+ " " + reglaE);
            
        }//if el operador /
        
    }//guardar
    
    public Nodo crear(String expresion){
        //1. considerar la expresion como un conjunto de tokens
        StringTokenizer tokenizer;
        String token;
        //invetigacion.2 1ro de julio
       // Este metodo nos sirve para cuando se pasa una 
       //cadena poder dividirla en partes mas pequeñas.
        //0. inicializar valores para varias ejecuciones
       paso = 0;
        //2. separacion de tokens de la expresion
        tokenizer = new StringTokenizer(expresion, espacios+aritmeticos,true);
        //3. mientras existan tokens
        while(tokenizer.hasMoreTokens()){
            
            //4.omitir espacios en blanco
            token = tokenizer.nextToken();
            System.out.println("Token"+ token);
            if(espacios.indexOf(token)>=0){
                //5.se trata de un identificador
                System.out.println("se trata de un identificador");
                //
            }else if(aritmeticos.indexOf(token)<0){
                //6.extraer de la pila los terminos que estaban
                ArbolNodo.push(new Nodo(token));
                paso ++;
                String regla = "T.nodo=new Hoja(id<"+token+">,id.entrada_"+token+")";
                reglasEjecutadas.add("p" +paso+ regla);
                
            }else if(token.equals(")")){
                //7.tratar tokens que no son parentesis
              while (!caracter.empty() && !caracter.peek().equals("(")){
                        guardar();
                        
                        
                    }//while
                    caracter.pop();
               
            }else{
                if(!token.equals("(") && !caracter.empty()){
                    String exa = (String) caracter.peek();
                    while(!exa.equals("(") && caracter.empty()&&
                            aritmeticos.indexOf(exa)>= aritmeticos.indexOf(token)){
                        guardar();
                        if(!caracter.empty()){
                            exa = (String) caracter.peek();
                        }//if !caracter empty
                    
                }//while !exa
                }//if - token (
                caracter.push(token);//guardar el token
            }//if-else
            
            //8.guardar el token
        }//while-tokenizer - hasMoreTockens
        while (!caracter.empty()){
            if(caracter.peek().equals("(")){//el caracter tiene simbolo de apertura
              caracter.pop();
            }else{
                guardar();
                raiz = (Nodo) ArbolNodo.peek();
            }//if
        }//while !caracter.empty
        return raiz;
    }//crear
}//fin de la clase
