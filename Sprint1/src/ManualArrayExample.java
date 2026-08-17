/**
 * Vetor dinâmico implementado manualmente.
 *
 * Motivo: demonstrar a mecânica de uma estrutura de armazenamento sequencial,
 * detalhando como o acesso por índice ocorre em tempo constante O(1) e como o crescimento
 * dinâmico da estrutura é gerenciado sem depender do java.util.ArrayList.
 */
public final class ManualArrayExample<T> {
    
    // O array base que armazenará os dados de forma sequencial na memória.
    private Object[] data;
    
    // O número exato de elementos que o usuário enxerga dentro do array (pode ser menor que a capacidade real de data[]).
    private int size;

    /**
     * Construtor padrão. Inicializa o array com uma capacidade inicial de 8.
     * Custo: O(1)
     */
    public ManualArray() {
        this(8);
    }

    /**
     * Construtor que permite definir a capacidade inicial do array.
     * Custo: O(1) 
     * 
     * @param capacity A capacidade inicial desejada.
     */
    public ManualArray(int capacity) {
        // Garante pelo menos capacidade 1 para evitar arrays de tamanho 0.
        data = new Object[Math.max(1, capacity)];
        size = 0;
    }

    /**
     * Retorna a quantidade de elementos armazenados logicamente no array.
     * Custo: O(1)
     * 
     * @return O tamanho lógico do array.
     */
    public int size() {
        return size;
    }

    /**
     * Verifica se o array está vazio.
     * Custo: O(1)
     * 
     * @return true se o array não possuir elementos, false caso contrário.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adiciona um elemento no final do array.
     * Custo Médio (Amortizado): O(1). No pior caso (quando precisa redimensionar e copiar tudo), é O(n).
     * 
     * @param value O valor a ser adicionado.
     */
    public void add(T value) {
        // A inserção no final (append) é o caso mais comum e eficiente.
        insertAt(size, value);
    }

    /**
     * Insere um elemento em uma posição específica do array.
     * Custo Dominante (Pior Caso): O(n), pois precisamos "empurrar" todos os elementos
     * seguintes uma posição para a direita (Shift Right) para abrir espaço no meio do array.
     * 
     * @param index A posição desejada.
     * @param value O valor a ser inserido.
     */
    public void insertAt(int index, T value) {
        checkPosition(index); // Garante que o índice não cria buracos na estrutura
        ensureCapacity(size + 1); // Garante que há espaço na memória para o novo item
        
        // Desloca elementos para abrir espaço na posição solicitada (shift right).
        // Custo O(n)
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        
        // Insere o elemento na posição que agora está livre
        data[index] = value;
        size++;
    }

    /**
     * Acessa o elemento na posição indicada.
     * Custo: O(1) - Ponto forte do armazenamento sequencial, onde a CPU calcula
     * matematicamente a posição da memória e vai direto até ela.
     * 
     * @param index O índice do elemento.
     * @return O valor armazenado na posição.
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    /**
     * Substitui o elemento em uma posição específica.
     * Custo: O(1) - Acesso direto de memória.
     * 
     * @param index A posição do elemento a ser substituído.
     * @param value O novo valor.
     */
    public void set(int index, T value) {
        checkIndex(index);
        data[index] = value;
    }

    /**
     * Remove um elemento de uma posição específica do array.
     * Custo Dominante (Pior Caso): O(n), pois após a remoção, precisamos "puxar" 
     * todos os elementos seguintes uma posição para a esquerda para tapar o buraco 
     * e manter os dados sequenciais.
     * 
     * @param index A posição do elemento a ser removido.
     * @return O elemento que foi removido.
     */
    @SuppressWarnings("unchecked")
    public T removeAt(int index) {
        checkIndex(index);
        T removed = (T) data[index];
        
        // Compacta o vetor para manter elementos contíguos após remoção (shift left).
        // Custo O(n)
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        
        // Limpa a última referência para evitar vazamento de memória (Memory Leak).
        // Se não fizermos isso, o objeto antigo continuará referenciado e não será apagado pelo Garbage Collector.
        data[size - 1] = null;
        size--;
        return removed;
    }

    /**
     * Remove todos os elementos do array.
     * Custo: O(n) para limpar todas as referências de memória.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    /**
     * Método interno para garantir que o array tenha a capacidade solicitada.
     * Se a capacidade atual for insuficiente, cria-se um novo array com o dobro do tamanho.
     * Custo: O(n) no momento da realocação, pois precisa copiar todos os N elementos antigos.
     * 
     * @param needed A capacidade mínima necessária.
     */
    private void ensureCapacity(int needed) {
        // Se a capacidade já suporta a nova inserção, não fazemos nada (Custo O(1))
        if (needed <= data.length) {
            return;
        }
        
        // Dobrar a capacidade reduz o custo amortizado de realocação (Trade-off clássico: Desperdiça Memória para ganhar Tempo).
        // Se aumentássemos apenas de 1 em 1 toda vez, o custo seria O(n^2) em múltiplas inserções.
        Object[] bigger = new Object[data.length * 2];
        
        // Copia os elementos antigos para o array novo. Custo O(n)
        for (int i = 0; i < data.length; i++) {
            bigger[i] = data[i];
        }
        
        // Substitui a referência para o array novo, o Garbage Collector do Java cuidará de limpar o array antigo
        data = bigger;
    }

    /**
     * Verifica se o índice fornecido é válido para leitura ou alteração.
     * Lança erro caso o índice seja menor que zero ou maior que o último índice existente.
     * 
     * @param index O índice a ser checado.
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);
        }
    }

    /**
     * Verifica se o índice fornecido é válido para inserção. 
     * Diferente do checkIndex, ele permite que se tente inserir exatamente no 'size' (no final absoluto da lista).
     * 
     * @param index O índice a ser checado.
     */
    private void checkPosition(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);
        }
    }
}
