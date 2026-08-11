public final class Sprint01ManualArrayDemo {

    private Sprint01ManualArrayDemo() {

    }
    
    public static void main(String [] args) {

        ManualArray<String> frutas = new ManualArray<>(5);

        frutas.add("Maçã");
        frutas.add("Banana");
        frutas.add("Laranja");

        frutas.insertAt(1, "Morango");

        frutas.add("Uva");

        System.out.println("=== EXEMPLO GENERICO - MANUAL ARRAY ===");
        System.out.println("Tamanho atual: " + frutas.size());
        System.out.println("Tabela de frutas (indice -> nome)");
        System.out.println("----------------------------------------");

        for (int i = 0; i < frutas.size(); i++) {
            System.out.printf("[%d] -> %s%n", i, frutas.get(i));
        }

        System.out.println("----------------------------------------");
        System.out.println("Leitura pod índice (O(1) imediato):");
        System.out.println("indice 0: " + frutas.get(0));
        System.out.println("indice 2: " + frutas.get(2));
        System.out.println("indice 4: " + frutas.get(4));
        System.out.println("----------------------------------------");

        String removido = frutas.removeAt(3);

        System.out.println("Removido em indice 3: " + removido);
        System.out.println("Tamanho após remoção: " + frutas.size());

        System.out.println("Tabela final após compactação:");

        for(int i = 0; i < frutas.size(); i++) {
            System.out.printf("[%d] -> %s%n", i, frutas.get(i));
        }

        System.out.println("----------------------------------------");
        System.out.println("DICA DA SPRINT: Como você adaptaria esse código para armazenar 'Processos' no lugar de frutas?");

    }
}