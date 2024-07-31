package lib;

public class Grafo<T> {
    private ArrayList<Vertice<T>> vertices;

    public Vertice<T> adicionarVertice(T valor) {
        Vertice<T> novo = new Vertice<T>(valor);

        this.vertices.add(novo);
        return novo;
    }

    private Vertice<T> obterVertice(T valor) {
        Vertice<T> v;
        for (int i = 0; i < this.vertices.size(); i++) {
            v = this.vertices.get(i);
            if (v.getValor().equals(valor)) {
                return v;
            }
        }
        // Se chegou aqui é porque não existe um vértice com esse valor
        return null;
    }

    public void adicionarAresta(T origem, T destino, float peso) {
        Vertice verticeOrigem, verticeDestino;

        // Busco o vértice com o valor de origem
        verticeOrigem = obterVertice(origem);

        // Se ainda não existe vértice com o valor da origem, vou criar o vértice
        if (verticeOrigem == null) {
            verticeOrigem = adicionarVertice(origem);
        }

        // Busco o vértice com o valor de destino
        verticeDestino = obterVertice(destino);

        // Se ainda não existe vértice com o valor do destino, vou criar o vértice
        if (verticeDestino == null) {
            verticeDestino = adicionarVertice(destino);
        }

        // Vou adicionar a aresta à lista de adjacência do vértice de origem
        verticeOrigem.adicionarDestino(new Aresta(verticeDestino, peso));
    }
}
