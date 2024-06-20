package lib;

import java.util.Comparator;

public class ArvoreAVL<T> extends ArvoreBinaria<T> {

    public ArvoreAVL(Comparator<T> comp) {
        super(comp);
    }
    @Override
    public void adicionar(T novoValor) {
        raiz = adicionarRecursivo(raiz, novoValor);
    }

    private No<T> adicionarRecursivo(No<T> noAtual, T novoValor) {
        /* Metodo adicionarRecursivo sobreescrevendo método da lib de Arvore */
        return null;
    }

    public T remover(T valor) {
        raiz = removerRecursivo(raiz, valor);
        return null; // Ou retorne o valor removido, se necessário
    }

    private No<T> removerRecursivo(No<T> noAtual, T valor) {
        /* Metodo removerRecursivo sobreescrevendo método da lib de Arvore */
        return null;
    }


}

