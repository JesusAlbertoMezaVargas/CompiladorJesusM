package arbolE;
/*
Meza Vargas
CLASE para armar el arbol
Nombre
Parte 1. Analisis sintactico
Parte 2. Analisis semantico
Parte 3. Codigo intermedio
Parte 4. Codigo Objecto

*/

public class Nodo {
   //ATRIBUTOS
    private String dato;
    private Nodo padre;
    private Nodo izquierdo;
    private Nodo derecho;
    private String codigoIntermedio;
    private String lugar;//para los temporales
    String valor ;
    //CONSTRUCTORES
    

    public Nodo(Nodo derecho, String dato, Nodo izquierdo ) {
        this.dato = dato;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }
    
    
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    public Nodo(String dato){
        this.dato=dato;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public Nodo getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    public Nodo getDerecho() {
        return derecho;
    }

    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }

    public String getCodigoIntermedio() {
        return codigoIntermedio;
    }

    public void setCodigoIntermedio(String codigoIntermedio) {
        this.codigoIntermedio = codigoIntermedio;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

   
    
    
}
