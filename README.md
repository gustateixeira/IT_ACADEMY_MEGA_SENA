Programa DELL IT Academy – Processo Seletivo – Edição #20


Enunciado
Esta atividade consiste no desenvolvimento de um sistema de controle de apostas,
similar à Mega-Sena® e outros. O sistema deverá funcionar em 4 fases: fase de
apostas, fase de sorteio, fase de apuração e fase de premiação.
As regras são:
1. Os sorteios ocorrem sobre os números de 1 a 50.
2. Cada aposta deverá conter exatamente 5 números (não são permitidas apostas
com menos de 5 ou com mais de 5 números).
3. O apostador poderá escolher livremente os números em que quer apostar, ou o
sistema poderá sortear automaticamente os números (aposta “surpresinha”).
4. Na fase de sorteio, deve-se inicialmente sortear exatamente 5 números entre 1
e 50.
5. Na fase de apuração, deve-se verificar quais apostas acertaram os 5 números
sorteados.
a. Apostas vencedoras são somente aquelas que acertaram os 5 números
(não existe premiação para 4 ou menos números).
b. Caso nenhuma aposta tenha acertado os 5 números, o sistema deverá
realizar mais uma rodada de sorteio sorteando mais um número e
acrescentando-o à lista de números sorteados, e repetindo a apuração,
agora com 1 número sorteado a mais.
c. O sistema deverá repetir esse mecanismo no máximo 25 vezes, até
encontrar apostas vencedoras ou finalizar sem nenhum vencedor.
Na fase de apostas, o sistema deverá permitir o registro das apostas coletando, para
cada uma, o nome e CPF do apostador e os números por ele escolhidos. Um apostador
poderá fazer quantas apostas quiser. Cada registro de aposta tem um número único
sequencial iniciando sempre em mil: 1000, 1001, 1002, etc.
As fases de sorteio e apuração funcionam de forma intercambiada. Na fase de sorteio,
o sistema não deverá permitir que novas apostas sejam registradas. O sistema deverá
realizar o sorteio conforme as regras descritas anteriormente, até encontrar vencedores
ou então até finalizar sem encontrar vencedores, ou seja, deverá executar o sorteio e
em seguida a apuração até chegar no fim do processo. Quando houver pelo menos um
acertador em uma rodada a apuração termina.
Por fim, o sistema mostra a lista de apostas vencedoras (ordenada alfabeticamente pelo
nome dos apostadores) ou uma mensagem de que não houve vencedores. O sistema
também mostra a lista de números sorteados e iniciar o processo de premiação.
Você deve implementar as seguintes funcionalidades:
1. [Iniciar] O sistema deverá iniciar uma nova edição, preparando-se para a fase de
registro de apostas.
2. [Registrar nova aposta] O sistema deverá coletar os dados do apostador e os 5
números do seu palpite, inseridos manualmente ou sorteados automaticamente pelo
sistema (função “surpresinha”), se ele assim desejar.
3. [Listas apostas] O sistema deverá exibir ao operador os dados de todas as apostas
registradas até o momento.
4. [Finalizar apostas e executar o sorteio] O sistema deverá solicitar uma
confirmação e então migrar para a fase de sorteio, executando as regras
anteriormente explicadas.
5. [Fim da apuração] Ao final da apuração, o sistema deverá exibir:
a. a lista de números sorteados
b. quantas rodadas de sorteio foram realizadas
c. a quantidade de apostas vencedoras
d. a lista de apostas vencedoras (ordenada alfabeticamente pelo nome dos
apostadores) ou uma mensagem de que não houve vencedores
e. uma lista de todos os números apostados, considerando todas as apostas,
ordenada do número mais escolhido ao menos escolhido. Ao lado de cada
número deverá haver a quantidade de apostas que contêm aquele número,
como no exemplo a seguir (se você quiser, poderá apresentar isso
graficamente):
Nro apostado Qtd de apostas
42 28
19 25
22 18
12 6

6. [Premiação] Essa é uma funcionalidade que ficará integralmente a seu critério.
Havendo apostas vencedoras, você deverá premiá-las da maneira que desejar. Isso
significa que você deverá elaborar as regras e o mecanismo de premiação para as
apostas vencedoras. Você deverá documentar e justificar as regras que você
elaborou, as quais deverão ser coerentes com o restante do enunciado deste
desafio. Você também deverá implementar o mecanismo de premiação no sistema
e demonstrar que ele funciona como o esperado. Seja criativo(a)! 
