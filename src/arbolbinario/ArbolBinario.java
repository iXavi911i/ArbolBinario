/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinario;

/**
 *
 * @author Xavi
 */
public class ArbolBinario {
    
    static Nodo raiz;
    
    private void agregar(Nodo nodo, int valor){
        if (valor<nodo.valor){
            if(nodo.izquierdo==null){
                nodo.izquierdo = new Nodo(valor);
                System.out.println("Se agrego el valor " + valor);
            }
            else
                agregar(nodo.izquierdo,valor);
        }
        else if(valor>nodo.valor){
            if(nodo.derecho==null){
                nodo.derecho = new Nodo(valor);
                System.out.println("Se agrego el valor " + valor);
            }
            else
                agregar(nodo.derecho,valor);
        }
    }
    
    public void agregar(int valor){
        agregar(raiz,valor);
    }
    
    public boolean buscar(int valor){
        Nodo actual = raiz;
        while(actual!=null){
            if(actual.valor==valor){
                return true;
            }
            else if(valor<actual.valor){
                actual = actual.izquierdo;
            }
            else{
                actual = actual.derecho;
            }
        }
        return false;
    }
    
    public boolean borrar(int valor){
        Nodo padre = raiz;
        Nodo actual = raiz;
        boolean hijoDer = false;
        
        //ubica y guarda en la variable actual el nodo a borrar
        while(actual.valor!=valor){
            padre = actual;
            if(valor<actual.valor){
                hijoDer = false;
                actual = actual.izquierdo;
            }
            else{
                hijoDer = true;
                actual = actual.derecho;
            }
            
            if(actual==null)
               return false;
        }
        
        //caso donde el nodo esta solillo
        if(actual.izquierdo == null && actual.derecho == null){
            if(actual == raiz)
                raiz = null;
            if(hijoDer)
                padre.derecho = null;
            else
                padre.izquierdo = null;
        }
        //caso donde el nodo tiene 1 hijo
        else if(actual.izquierdo==null){
            if(actual == raiz)
                raiz = actual.derecho;
            else if(hijoDer)
                padre.derecho = actual.derecho;
            else
                padre.izquierdo = actual.derecho;
        }
        else if(actual.derecho==null){
            if(actual==raiz)
                raiz = actual.izquierdo;
            else if(hijoDer)
                padre.derecho = actual.izquierdo;
            else
                padre.izquierdo = actual.izquierdo;
        }
        //caso donde el nodo tiene 2 hijos
        else if(actual.izquierdo!=null && actual.derecho!=null){
            Nodo minimo = getMinimo(actual);
            if(actual==raiz)
                raiz = minimo;
            else if(hijoDer)
                padre.derecho = minimo;
            else
                padre.izquierdo = minimo;
            minimo.izquierdo = actual.izquierdo;
        }
        return true;
    }
    
    private Nodo getMinimo(Nodo nodo){
    Nodo minimo = null;
    Nodo minimoPadre = null;
    Nodo actual = nodo.derecho;
    while(actual!=null){
        minimoPadre = minimo;
        minimo = actual;
        actual = actual.izquierdo;
    }
    if(minimo!=nodo.derecho){
        minimoPadre.izquierdo = minimo.derecho;
        minimo.derecho = nodo.derecho;
    }
    return minimo;
    }
    
    public void imprimir(Nodo nodo) {
    if(nodo!=null) {
      imprimir(nodo.izquierdo);
      System.out.println(nodo.valor);
      imprimir(nodo.derecho);
    }
  }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArbolBinario inst = new ArbolBinario();
        
        raiz = new Nodo(5);
        inst.agregar(2);
        inst.agregar(12);
        inst.agregar(9);
        inst.agregar(21);
        inst.agregar(19);
        inst.agregar(25);
        inst.agregar(-4);
        inst.agregar(3);  
        System.out.println("\n Borrar el nodo con valor -4..."+ "\n");
        inst.imprimir(raiz);
        if(inst.borrar(-4)){
            System.out.println("\n Arbol restante...\n");
            inst.imprimir(raiz);}
        else
            System.out.println("\n Valor No encontrado \n");
        
        System.out.println();
        raiz = new Nodo(5);
        inst.agregar(2);
        inst.agregar(18);
        inst.agregar(21);
        inst.agregar(19);
        inst.agregar(25);
        inst.agregar(-4);
        inst.agregar(3);
        System.out.println("\n Borrar el nodo con valor 18..."+ "\n");
        inst.imprimir(raiz);
        if(inst.borrar(18)){
            System.out.println("\n Arbol restante...\n");
            inst.imprimir(raiz);}
        else
            System.out.println("\n Valor No encontrado \n");
        
        System.out.println();
        raiz = new Nodo(5);
        inst.agregar(2);
        inst.agregar(12);
        inst.agregar(9);
        inst.agregar(21);
        inst.agregar(19);
        inst.agregar(25);
        inst.agregar(-4);
        inst.agregar(3);         
        System.out.println("\n Borrar el nodo con valor 12..."+ "\n");
        inst.imprimir(raiz);
        if(inst.borrar(12)){
            System.out.println("\n Arbol restante...\n");
            inst.imprimir(raiz);}
        else
            System.out.println("\n Valor No encontrado \n");
        
        System.out.println("\n Valor buscado 9 ha sido: " + inst.buscar(9) + "\n");
        System.out.println("\n Valor buscado 20 ha sido: " + inst.buscar(20)+ "\n");
    }
    
}
