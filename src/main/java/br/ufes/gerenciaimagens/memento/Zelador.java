package br.ufes.gerenciaimagens.memento;

import java.util.Stack;

/**
 *
 * @author rborges
 */
public class Zelador {
    
    private final Stack<MementoImagem> elementos;
    private static Zelador instancia;
    
    private Zelador() {
        this.elementos = new Stack<>();
    }
    
    public static Zelador getInstancia() {
        if (instancia == null) {
            instancia = new Zelador();
        }
        
        return instancia;
    }
    
    public void add(MementoImagem todo) throws Exception {
        this.elementos.push(todo);
    }
    
    public MementoImagem getUltimo() throws Exception {
        if (!elementos.isEmpty()) {
            return elementos.pop();
        }
        
        throw new Exception("Não há imagens para restaurar");
    }
    
}
