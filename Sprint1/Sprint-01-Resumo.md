# Resumo da resolução sprint 01

## ManualArray

O Manual Array é uma estrutura de vetor feita manualmente para armazenar objetos em coleções possibilitando o armazenamento sequencial desses objetos em uma estrutura do mesmo tipo, neste caso sendo um tipo genérico.

Internamente ele controla o armazenamento, inserção, remoção e redimencionamento desses objetos através de métodos implementados na estrutura. "Data" guarda os elementos, "size" controla quantos elementos realmente estão sendo usados, "add" insere um elemento no final e para inserir em uma posição específica utiliza "InsertAt" deslocando os elementos para a direita antes de colocar o novo valor. Faz busca por índice com "get" e pode substituir um valor também por índice com "set". "RemoveAt" remove um elemento e depois puxa os outros para preencher o espaço e "clear" limpa todos os itens.

O custo para ler ou escrever por índice (get/set) é O(1), porque a posição na memória é acessada diretamente.
Já inserir ou remover no meio da lista (insertAt/removeAt) é O(n), porque exige deslocar todos os outros elementos à partir da posição inserida uma posição para a direita, ou no caso de remover, uma para a esqueda.