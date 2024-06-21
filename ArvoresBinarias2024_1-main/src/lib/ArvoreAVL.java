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
        if (noAtual == null) {
            return new No<>(novoValor);
        }

        int comparacao = comparador.compare(novoValor, noAtual.getValor());
        if (comparacao < 0) {
            noAtual.setFilhoEsquerda(adicionarRecursivo(noAtual.getFilhoEsquerda(), novoValor));
        } else if (comparacao > 0) {
            noAtual.setFilhoDireita(adicionarRecursivo(noAtual.getFilhoDireita(), novoValor));
        } else {
            return noAtual; // Valor duplicado
        }

        atualizarAltura(noAtual); // Atualiza a altura do nó após a inserção
        return balancear(noAtual); // Balanceia o nó se necessário
    }

    @Override
    public T remover(T valor) {
        raiz = removerRecursivo(raiz, valor);
        return null; // Ou retorne o valor removido, se necessário
    }

    private No<T> removerRecursivo(No<T> noAtual, T valor) {
        if (noAtual == null) {
            return null; // Valor não encontrado
        }

        int comparacao = comparador.compare(valor, noAtual.getValor());
        if (comparacao < 0) {
            noAtual.setFilhoEsquerda(removerRecursivo(noAtual.getFilhoEsquerda(), valor));
        } else if (comparacao > 0) {
            noAtual.setFilhoDireita(removerRecursivo(noAtual.getFilhoDireita(), valor));
        } else {
            // Nó a ser removido encontrado
            if (noAtual.getFilhoEsquerda() == null || noAtual.getFilhoDireita() == null) {
                return (noAtual.getFilhoEsquerda() != null) ? noAtual.getFilhoEsquerda() : noAtual.getFilhoDireita();
            }

            // Dois filhos: encontra o sucessor (menor da subárvore direita)
            No<T> sucessor = encontrarMenor(noAtual.getFilhoDireita());
            noAtual.setValor(sucessor.getValor());
            noAtual.setFilhoDireita(removerRecursivo(noAtual.getFilhoDireita(), sucessor.getValor()));
        }

        atualizarAltura(noAtual); // Atualiza a altura do nó após a remoção
        return balancear(noAtual); // Balanceia o nó se necessário
    }
    private int fatorBalanceamento(No<T> no) {
        return altura(no.getFilhoDireita()) - altura(no.getFilhoEsquerda());
    }

    // Método para atualizar a altura de um nó
    private void atualizarAltura(No<T> no) {
        no.setAltura(1 + Math.max(altura(no.getFilhoEsquerda()), altura(no.getFilhoDireita())));
    }

    // Método para realizar uma rotação à esquerda em um nó
    private No<T> rotacaoEsquerda(No<T> no) {
        No<T> novoPai = no.getFilhoDireita();
        No<T> filhoEsquerdaDoNovoPai = novoPai.getFilhoEsquerda();

        novoPai.setFilhoEsquerda(no);
        no.setFilhoDireita(filhoEsquerdaDoNovoPai);

        atualizarAltura(no);
        atualizarAltura(novoPai);
        return novoPai;
    }

    // Método para realizar uma rotação à direita em um nó
    private No<T> rotacaoDireita(No<T> no) {
        No<T> novoPai = no.getFilhoEsquerda();
        No<T> filhoDireitaDoNovoPai = novoPai.getFilhoDireita();

        novoPai.setFilhoDireita(no);
        no.setFilhoEsquerda(filhoDireitaDoNovoPai);

        atualizarAltura(no);
        atualizarAltura(novoPai);
        return novoPai;
    }

    // Método para balancear um nó -----------> AQUI É UTILIZADO O fatorBalanceamento PARA VERIFICAR SE SERÁ PRECISO
    // BALANCEAR E QUAL BALANCEAMENTO SERÁ FEITO
    private No<T> balancear(No<T> no) {
        int fatorBalanceamento = fatorBalanceamento(no);

        // Rotação à direita (caso EE)
        if (fatorBalanceamento > 1 && fatorBalanceamento(no.getFilhoDireita()) >= 0) {
            return rotacaoEsquerda(no);
        }

        // Rotação à esquerda (caso DD)
        if (fatorBalanceamento < -1 && fatorBalanceamento(no.getFilhoEsquerda()) <= 0) {
            return rotacaoDireita(no);
        }

        // Rotação esquerda direita (caso ED)
        if (fatorBalanceamento > 1 && fatorBalanceamento(no.getFilhoDireita()) < 0) {
            no.setFilhoDireita(rotacaoDireita(no.getFilhoDireita()));
            return rotacaoEsquerda(no);
        }

        // Rotação direita esquerda (caso DE)
        if (fatorBalanceamento < -1 && fatorBalanceamento(no.getFilhoEsquerda()) > 0) {
            no.setFilhoEsquerda(rotacaoEsquerda(no.getFilhoEsquerda()));
            return rotacaoDireita(no);
        }

        return no; // Nó já está balanceado
    }


}

