package util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Clase para modelar árboles binarios de búsqueda genéricos.</p>
 *
 * <p>Un árbol instancia de esta clase siempre cumple que:</p>
 * <ul>
 *   <li>Cualquier elemento en el árbol es mayor o igual que todos sus
 *       descendientes por la izquierda.</li>
 *   <li>Cualquier elemento en el árbol es menor o igual que todos sus
 *       descendientes por la derecha.</li>
 * </ul>
 * @author Ariel Merino 317031326
 */
public class ArbolBinarioBusqueda<T extends Comparable<T>> extends ArbolBinario<T> {

    /* Clase privada para iteradores de árboles binarios ordenados. */
    private class Iterador implements Iterator<T> {

        /* util.Pila para recorrer los nodos en DFS in-order. */
        private Pila<Nodo> pila;

        /* Construye un iterador con el nodo recibido. */
        public Iterador() {
            pila = new Pila<>();
            Nodo n = raiz;
            while (n != null){
                pila.agrega(n);
                n = n.izquierdo;
            }
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            return !pila.esVacio();
        }

        /* Regresa el siguiente elemento en orden DFS in-order. */
        @Override public T next() {
            if(pila.esVacio()){
                throw  new NoSuchElementException("No hay mas elementos");
            }
            Nodo n = pila.pop();
            T elem = (T) n.elemento;
            n = n.derecho;
            while (n != null){
                pila.push(n);
                n = n.izquierdo;
            }
            return elem;
        }
    }

    protected Nodo ultimo;

    public Nodo getUltimo() {
        return ultimo;
    }

    /**
     * Constructor que no recibe parámeteros. {@link ArbolBinario}.
     */
    public ArbolBinarioBusqueda() {
    }

    /**
     * Construye un árbol binario ordenado a partir de una colección. El árbol
     * binario ordenado tiene los mismos elementos que la colección recibida.
     * @param coleccion la colección a partir de la cual creamos el árbol
     *        binario ordenado.
     */

    public ArbolBinarioBusqueda(Coleccionable<T> coleccion) {
        super(coleccion);
    }

    protected void agregaNodo(Nodo<T> n, Nodo<T> nuevo) {
        if(n.elemento.compareTo(nuevo.elemento) >= 0){
            if (n.hayIzquierdo()){
                agregaNodo(n.izquierdo, nuevo);
            }else {
                n.izquierdo = nuevo;
                nuevo.padre = n;
            }
        }else {
            if (n.hayDerecho()){
                agregaNodo(n.derecho, nuevo);
            }else {
                n.derecho = nuevo;
                nuevo.padre = n;
            }
        }
    }

    /**
     * Agrega un nuevo elemento al árbol. El árbol conserva su orden in-order.
     * @param elemento el elemento a agregar.
     */
    @Override public void agrega(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento enviado es nulo");
        }
        Nodo agregar = this.nuevoNodo(elemento);
        this.ultimo = agregar;
        tamanio++;
        if (raiz == null){
            this.raiz = agregar;
        }else {
            this.agregaNodo(raiz, agregar);
        }

    }

    protected Nodo<T> eliminaNodo(Nodo<T> n){
        /**
         * Donde es la raiz sin hijos
         */
        if(!n.hayPadre() && !n.hayIzquierdo() &&!n.hayIzquierdo()){
            limpia();
            /**
             * Donde es la raiz y tiene hijo derecho
             */
        }else if(!n.hayPadre()&& n.hayDerecho()){
            raiz = n.derecho;
            n.derecho.padre = null;
            /**
             * donde es la raiz y tiene hijo izquierdo
             */
        }else if (!n.hayPadre() && n.hayIzquierdo()){
            raiz = n.izquierdo;
            n.izquierdo.padre = null;
            /**
             * Donde es una hoja
             */
        }else if(!n.hayDerecho() && !n.hayIzquierdo() ){
            if (n.padre.hayIzquierdo() && n.padre.izquierdo == n){
                n.padre.izquierdo = null;
            }else {
                n.padre.derecho = null;
            }
        }else if( n.hayDerecho()){
            if (n.padre.hayIzquierdo() && n.padre.izquierdo == n){
                n.padre.izquierdo = n.derecho;
                n.derecho.padre = n.padre;
            }else {
                n.padre.derecho = n.derecho;
                n.derecho.padre = n.padre;
            }
        }else if (n.hayIzquierdo()){
            if (n.padre.hayIzquierdo() && n.padre.izquierdo == n){
                n.padre.izquierdo = n.izquierdo;
                n.izquierdo.padre = n.padre;
            }else {
                n.padre.derecho = n.izquierdo;
                n.izquierdo.padre = n.padre;
            }
        }
        return null;
    }

    /**
     * Elimina un elemento. Si el elemento no está en el árbol, no hace nada; si
     * está varias veces, elimina el primero que encuentre (in-order). El árbol
     * conserva su orden in-order.tamanio == 0 &&
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
        if (elemento == null){
            throw  new IllegalArgumentException("El elemento a eliminar es nulo");
        }
        if(!contiene(elemento)){
            return;
        }
        Nodo elimina = buscaNodo(raiz,elemento);
        tamanio--;
        if (elimina.hayIzquierdo() && elimina.hayDerecho()){
            Nodo nodoMax = maximoEnSubarbolIzquierdo(elimina.izquierdo);
            T elemMax = (T) nodoMax.elemento;
            elimina.elemento = elemMax;
            eliminaNodo(nodoMax);
        }else{
            eliminaNodo(elimina);
        }
    }


    private Nodo maximoEnSubarbolIzquierdo(Nodo n){
        if (!n.hayDerecho()){
            return n;
        }
        return maximoEnSubarbolIzquierdo(n.derecho);
    }

    /**
     * Nos dice si un elemento está contenido en el arbol.
     * @param elemento el elemento que queremos verificar si está contenido en
     *                 la arbol.
     * @return <code>true</code> si el elemento está contenido en el arbol,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean contiene(T elemento){
        if (elemento == null){
            throw  new IllegalArgumentException("Elemento invalido");
        }
        return buscaNodo(raiz, elemento) != null;
    }


    protected Nodo<T> buscaNodo(Nodo<T> n, T elemento){
        if (n == null){
            return null;
        }
        if (n.elemento.equals(elemento)){
            return n;
        }
        Nodo subAIzq = buscaNodo(n.izquierdo, elemento);
        Nodo subADer = buscaNodo(n.derecho, elemento);
        return subAIzq != null ? subAIzq : subADer;
    }


    /**
     * Gira el árbol a la derecha sobre el nodo recibido. Si el nodo no
     * tiene hijo izquierdo, el método no hace nada.
     * @param q el nodo sobre el que vamos a girar.
     */
    public void giraDerecha(Nodo<T> q) {
        if (!q.hayIzquierdo()){
            return;
        }

        Nodo p = q.izquierdo;

        if (p.hayDerecho()){
            q.izquierdo = p.derecho;
            p.derecho.padre = q;
        }else {
            q.izquierdo = null;
        }
        p.derecho = q;

        if (q.hayPadre()){
            if(esHijoIzquierdo(q)){
                q.padre.izquierdo = p;
            }else {
                q.padre.derecho = p;
            }
            p.padre = q.padre;
        }else {
            p.padre = null;
            this.raiz = p;
        }
        q.padre = p;
    }
    protected boolean esHijoIzquierdo(Nodo nodo) {
        if(nodo.padre == null) { return false; }
        if(nodo.padre.hayIzquierdo()) {
            return nodo.padre.izquierdo.equals(nodo);
        }
        return false;
    }
    /**
     * Gira el árbol a la izquierda sobre el nodo recibido. Si el nodo no
     * tiene hijo derecho, el método no hace nada.
     * @param p el nodo sobre el que vamos a girar.
     */
    public void giraIzquierda(Nodo<T> p) {
        if (!p.hayDerecho()){
            return;
        }
        Nodo q = p.derecho;
        if (q.hayIzquierdo()){
            p.derecho = q.izquierdo;
            q.izquierdo.padre = p;
        }else{
            p.derecho = null;
        }
        q.izquierdo = p;

        if (p.hayPadre()){
            if (esHijoIzquierdo(p)){
                p.padre.izquierdo = q;
            }else {
                p.padre.derecho = q;
            }
            q.padre = p.padre;
        }else {
            q.padre = null;
            this.raiz = q;
        }
        p.padre = q;
    }


    /**
     * Regresa un iterador para iterar el árbol. El árbol se itera en orden.
     * @return un iterador para iterar el árbol.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }

}