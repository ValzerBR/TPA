/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import app.Aluno;

import java.util.Comparator;

/**
 *
 * @author victoriocarvalho
 *
 * É um requisito do trabalho que sua classe ArvoreBinária implemente esta interface!
 * Com isso garantiremos que você implementou todos os métodos obrigatórios e que
 * conseguirá rodar o programa de teste para redigir o relatório.
 *
 *
 * @param <T>
 */
public class ArvoreBinaria<T> implements IArvoreBinaria<T> {

    protected No<T> raiz = null;
    protected Comparator<T> comparador;

    public ArvoreBinaria(Comparator<T> comp) {
        comparador = comp;
    }

    @Override
    public void adicionar(T novoValor) {
        var novo = new No<>(novoValor); // Crie um novo nó com o novo valor
        if (raiz == null) {
            raiz = novo; // Se a árvore estiver vazia, o novo nó se torna a raiz
        } else {
            adicionarRecursivo(raiz, novo); // Caso contrário, chame o método auxiliar para adicionar recursivamente
        }
    }

    // Método auxiliar para adicionar recursivamente
    private void adicionarRecursivo(No<T> pai, No<T> novo) {
        // Comparar o novo valor com o valor do nó pai
        int compResult = comparador.compare(novo.getValor(), pai.getValor());

        // Se o novo valor for menor que o valor do pai, adiciona à esquerda
        if (compResult < 0) {
            if (pai.getFilhoEsquerda() == null) {
                pai.setFilhoEsquerda(novo); // Se não houver filho à esquerda, adicione o novo nó aqui
            } else {
                adicionarRecursivo(pai.getFilhoEsquerda(), novo); // Caso contrário, continue a busca recursivamente à esquerda
            }
        }
        // Se o novo valor for maior que o valor do pai, adiciona à direita
        else if (compResult > 0) {
            if (pai.getFilhoDireita() == null) {
                pai.setFilhoDireita(novo); // Se não houver filho à direita, adicione o novo nó aqui
            } else {
                adicionarRecursivo(pai.getFilhoDireita(), novo); // Caso contrário, continue a busca recursivamente à direita
            }
        }
        // Se o novo valor for igual ao valor do pai, pode ser tratado de forma diferente, dependendo dos requisitos da árvore
        // Por exemplo, você pode optar por permitir duplicatas ou não, ou substituir o valor existente pelo novo valor
    }


    private No<T> removerRecursivo(No<T> atual, T valor) {
        if (atual == null) {
            return null; // Nó não encontrado, não há nada a ser removido
        }

        int compResult = comparador.compare(valor, atual.getValor());

        if (compResult < 0) {
            atual.setFilhoEsquerda(removerRecursivo(atual.getFilhoEsquerda(), valor)); // Procura na subárvore esquerda
        } else if (compResult > 0) {
            atual.setFilhoDireita(removerRecursivo(atual.getFilhoDireita(), valor)); // Procura na subárvore direita
        } else {
            // Caso de remoção encontrado
            if (atual.getFilhoEsquerda() == null) {
                return atual.getFilhoDireita(); // Se não houver filho à esquerda, retorna o filho à direita (ou null)
            } else if (atual.getFilhoDireita() == null) {
                return atual.getFilhoEsquerda(); // Se não houver filho à direita, retorna o filho à esquerda
            } else {
                // Caso de remoção com dois filhos
                // Encontra o nó sucessor, que é o menor nó à direita
                No<T> sucessor = encontrarMenor(atual.getFilhoDireita());
                atual.setValor(sucessor.getValor()); // Copia o valor do sucessor para o nó atual
                atual.setFilhoDireita(removerRecursivo(atual.getFilhoDireita(), sucessor.getValor())); // Remove o sucessor da subárvore direita
            }
        }

        return atual;
    }

    // Método auxiliar para encontrar o menor nó em uma subárvore
    private No<T> encontrarMenor(No<T> no) {
        if (no.getFilhoEsquerda() == null) {
            return no; // O nó atual é o menor
        }
        return encontrarMenor(no.getFilhoEsquerda()); // Continue a procurar na subárvore esquerda
    }



    public No<T> adicionarBreno(No<T> no,T novoValor) {
        if (no == null) {
            return new No<>(novoValor);
        }

        if (comparador.compare(novoValor, no.getValor()) < 0) {
            //no.setFilhoEsquerda(adicionar(novoValor));
        } else if (comparador.compare(novoValor, no.getValor()) > 0) {
            //no.setFilhoDireita(adicionar(novoValor));
        }

        return no;
    }

    @Override
    public T pesquisar(T valor) {
        return pesquisar(raiz, valor);
    }

    private T pesquisar(No<T> no, T valor) {
        if (no == null) {
            return null;
        }

        if (comparador.compare(valor, no.getValor()) < 0) {
            return pesquisar(no.getFilhoEsquerda(), valor);
        } else if (comparador.compare(valor, no.getValor()) > 0) {
            return pesquisar(no.getFilhoDireita(), valor);
        } else {
            return no.getValor();
        }
    }

    @Override
    public T pesquisar(T valor, Comparator comparador) {
        return null;
    }

    private void _remover(T valor) {
        raiz = removerRecursivo(raiz, valor);
    }

    @Override
    public T remover(T valor) {
        raiz = removerRecursivo(raiz, valor);
        return null;
    }

    @Override
    public int altura() {
        var no = altura(raiz);
        return altura(raiz);
    }

    private int altura(No<T> no) {
        if (no == null) {
            return -1;  // Altura de uma árvore vazia é -1
        }

        int alturaEsquerda = altura(no.getFilhoEsquerda());
        int alturaDireita = altura(no.getFilhoDireita());

        // A altura do nó atual é o máximo entre a altura das subárvores esquerda e direita + 1
        return 1 + Math.max(alturaEsquerda, alturaDireita);
    }

    private int contarNos(No<T> no) {
        if (no == null) {
            return 0;
        }
        // Visita primeiro o nó da esquerda
        int nosNaEsquerda = contarNos(no.getFilhoEsquerda());
        // Conta o próprio nó
        int nosNoAtual = 1;
        // Visita depois o nó da direita
        int nosNaDireita = contarNos(no.getFilhoDireita());
        // Retorna a soma dos nós na esquerda, no nó atual e na direita
        return nosNaEsquerda + nosNoAtual + nosNaDireita;
    }


    @Override
    public int quantidadeNos() {
        int nos = contarNos(raiz);
        return nos;
    }

    @Override
    public String caminharEmNivel() {
        return "";
    }

    @Override
    public String caminharEmOrdem() {
        return "";
    }
}
    
