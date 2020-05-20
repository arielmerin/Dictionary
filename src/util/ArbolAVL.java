package util;

/**
 * <p>Clase para árboles AVL.</p>
 *
 * <p>Un árbol AVL cumple que para cada uno de sus nodos, la diferencia entre
 * la altura de sus subárboles izquierdo y derecho está entre -1 y 1.</p>
 */
public class ArbolAVL<T extends Comparable<T>> extends ArbolBinarioBusqueda<T> {

    /**
     * Clase interna protegida para nodos de árboles AVL. La única diferencia
     * con los nodos de árbol binario, es que tienen una variable de clase
     * para la altura del nodo.
     */
    public class NodoAVL extends Nodo {

        /** La altura del nodo. */
        public int altura;

        /**
         * Constructor único que recibe un elemento.
         * @param elemento el elemento del nodo.
         */
        public NodoAVL(T elemento) {
            super(elemento);
            this.altura = 0;
        }

        /**
         * Regresa la altura del nodo.
         * @return la altura del nodo.
         */
        @Override public int altura() {
            return this.altura;
        }


        /**
         * Regresa una representación en cadena del nodo AVL.
         * @return una representación en cadena del nodo AVL.
         */
        @Override public String toString() {
            return super.toString();
        }

        /**
         * Compara el nodo con otro objeto. La comparación es
         * <em>recursiva</em>.
         * @param o el objeto con el cual se comparará el nodo.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link NodoAVL}, su elemento es igual al elemento de éste
         *         nodo, los descendientes de ambos son recursivamente
         *         iguales, y las alturas son iguales; <code>false</code> en
         *         otro caso.
         */
        @Override public boolean equals(Object o) {
            return super.equals(o);
        }
    }

    /**
     * Constructor sin parámetros. Para no perder el constructor sin parámetros
     * de {@link ArbolBinarioBusqueda}.
     */
    public ArbolAVL() {
        super();
    }

    /**
     * Construye un árbol rojinegro a partir de una colección. El árbol
     * rojinegro tiene los mismos elementos que la colección recibida.
     * @param coleccion la colección a partir de la cual creamos el árbol
     *        rojinegro.
     */
    public ArbolAVL(Coleccionable<T> coleccion) {
        super(coleccion);
    }

    public ArbolAVL(Lista<T> coercion) {
        for (T elemento : coercion) {
            this.agrega(elemento);
        }
    }

    /**
     * Construye un nuevo nodo, usando una instancia de {@link NodoAVL}.
     * @param elemento el elemento dentro del nodo.
     * @return un nuevo nodo con el elemento recibido dentro del mismo.
     */
    @Override protected NodoAVL nuevoNodo(T elemento) {
        return new NodoAVL (elemento);
    }
    /**
     * Agrega un nuevo elemento al árbol. El método invoca al método {@link
     * .ArbolBinarioBusqueda#agrega}, y después balancea el árbol girándolo comos
     * sea necesario.
     * @param elemento el elemento a agregar.
     */
    @Override public void agrega(T elemento) {
        super.agrega(elemento);
        Nodo<T> nodoAVL = this.getUltimo();
        this.rebalanceo((NodoAVL) nodoAVL);
    }
    /**
     * Método privado que rebalance el árbol.
     */
    private void rebalanceo(NodoAVL nodo) {
        if (nodo == null){
            return;
        }
        getAltura(nodo);
        if (getBalance(nodo) == -2){
            if (getBalance((NodoAVL)nodo.derecho) == 1){
                NodoAVL der = (NodoAVL) nodo.derecho;
                this.giraDerechaAVL(der);
                this.getAltura(der);
                this.getAltura((NodoAVL)der.padre);
            }
            this.giraIzquierdaAVL(nodo);
            this.getAltura(nodo);
        }
        else if (getBalance(nodo) == 2){
            if (getBalance((NodoAVL) nodo.izquierdo) == -1){
                NodoAVL nIzq = (NodoAVL) nodo.izquierdo;
                this.giraIzquierdaAVL(nIzq);
                this.getAltura(nIzq);
                this.getAltura((NodoAVL)nIzq.padre);
            }
            this.giraDerechaAVL(nodo);
            this.getAltura(nodo);
        }
        this.rebalanceo((NodoAVL) nodo.padre);
    }

    private int getBalance(NodoAVL n){
        return this.getAltura((NodoAVL)n.izquierdo) - this.getAltura((NodoAVL)n.derecho);
    }

    /**
     * Elimina un elemento del árbol. El método elimina el nodo que contiene
     * el elemento, y gira el árbol como sea necesario para rebalancearlo.
     * @param elemento el elemento a eliminar del árbol.
     */
    @Override public void elimina(T elemento) {
        Nodo<T> nodo = buscaNodo(raiz, elemento);
        super.elimina(elemento);
        rebalanceo((NodoAVL) nodo);
    }

    /**
     * Lanza la excepción {@link UnsupportedOperationException}: los árboles AVL
     * no pueden ser girados a la derecha por los usuarios de la clase, porque
     * se desbalancean.
     * @param q el nodo sobre el que se quiere girar.
     * @throws UnsupportedOperationException siempre.
     */
    @Override public void giraDerecha(Nodo<T> q) {
        throw new UnsupportedOperationException("Los árboles AVL no  pueden " +
                "girar a la izquierda por el usuario.");
    }

    /**
     * Lanza la excepción {@link UnsupportedOperationException}: los árboles AVL
     * no pueden ser girados a la izquierda por los usuarios de la clase, porque
     * se desbalancean.
     * @param p el nodo sobre el que se quiere girar.
     * @throws UnsupportedOperationException siempre.
     */
    @Override public void giraIzquierda(Nodo<T> p) {
        throw new UnsupportedOperationException("Los árboles AVL no  pueden " +
                "girar a la derecha por el usuario.");
    }

    private void giraDerechaAVL(NodoAVL p){
        super.giraDerecha(p);
    }
    private void giraIzquierdaAVL(NodoAVL p){ // Aquí suponemos que p tiene hijo derecho q
        super.giraIzquierda(p);
    }
    private int getAltura(NodoAVL v){
        if (v == null){
            return -1;
        }
        v.altura = Math.max(this.getAltura((NodoAVL)v.izquierdo), this.getAltura((NodoAVL)v.derecho)) + 1;
        return v.altura;
    }

}
