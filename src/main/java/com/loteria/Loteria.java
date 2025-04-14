/*Exercício loteria - Fazer um programa para ganhar premio  de R$ 1.000.000,00:

Solicitar lista de 10 apostadores,
cada um poderá apostar 4 números entre 0 e 9
Programa vai sortear 3 números entre 0 e 9 e exibir
Quem acertar pelo menos 2 números vai poder dividir o premio
Exibir lista de ganhadores,
 números apostados,
 valor que cada um ganhou.
*/

package com.loteria;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Loteria {
    // Classe para representar cada apostador
    public static class Apostador {
        public String nome;
        public int[] numerosApostados;
        public int acertos;

        public Apostador(String nome, int[] numerosApostados) {
            this.nome = nome;
            this.numerosApostados = numerosApostados;
            this.acertos = 0;
        }
    }

    // Método principal
    public static void main(String[] args) {
        // Criar lista de apostadores
        ArrayList<Apostador> apostadores = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        // Solicitar dados dos 10 apostadores
    System.out.println("Cadastre 10 apostadores, cada um escolhendo 4 números entre 0 e 9.");

        for (int i = 0; i < 10; i++) {
            System.out.println("\nApostador " + (i + 1) + ":");

            // Solicitar nome do apostador
            System.out.print("Digite o nome do apostador: ");
            String nome = sc.nextLine();

            // Solicitar os 4 números da aposta
            int[] numeros = new int[4];
            for (int j = 0; j < 4; j++) {
                boolean numeroValido = false;
                while (!numeroValido) {
                    System.out.print("Digite o número " + (j + 1) + " (0-9): ");
                    try {
                        int num = Integer.parseInt(sc.nextLine());
                        if (num >= 0 && num <= 9) {
                            numeros[j] = num;
                            numeroValido = true;
                        } else {
                            System.out.println("Número inválido! Digite um número entre 0 e 9.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida! Digite um número entre 0 e 9.");
                    }
                }
            }

            // Adicionar apostador à lista
            apostadores.add(new Apostador(nome, numeros));
        }

        // Realizar o sorteio de 3 números
        Random rnd = new Random();
        int[] numerosSorteados = new int[3];
        System.out.println("\nRealizando o sorteio...");

        for (int i = 0; i < 3; i++) {
            numerosSorteados[i] = rnd.nextInt(10); // Gera números entre 0 e 9
        }

        // Exibir números sorteados
        System.out.println("\nNúmeros sorteados:");
        for (int num : numerosSorteados) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Verificar acertos de cada apostador
        ArrayList<Apostador> ganhadores = new ArrayList<>();

        for (Apostador apostador : apostadores) {
            apostador.acertos = 0;
            for (int numApostado : apostador.numerosApostados) {
                for (int numSorteado : numerosSorteados) {
                    if (numApostado == numSorteado) {
                        apostador.acertos++;
                        break;
                    }
                }
            }

            // Adicionar à lista de ganhadores se acertou pelo menos 2 números
            if (apostador.acertos >= 2) {
                ganhadores.add(apostador);
            }
        }

        // Exibir resultados
        System.out.println("\nResultado do Sorteio:");

        if (ganhadores.isEmpty()) {
            System.out.println("Não houveram ganhadores. O prêmio acumula para o próximo sorteio!");
        } else {
            // Calcular valor do prêmio para cada ganhador
            double premioPorGanhador = 1000000.0 / ganhadores.size();

            System.out.println("Parabéns aos ganhadores que acertaram pelo menos 2 números!");
            System.out.println("Número de ganhadores: " + ganhadores.size());
            System.out.println("Prêmio por ganhador: R$" + premioPorGanhador);

            System.out.println("\nLista de Ganhadores:");
            for (Apostador ganhador : ganhadores) {
                System.out.println("\nNome: " + ganhador.nome);
                System.out.print("Números apostados: ");
                for (int num : ganhador.numerosApostados) {
                    System.out.print(num + " ");
                }
                System.out.println("\nAcertos: " + ganhador.acertos);
                System.out.println("Valor ganho: R$" + premioPorGanhador);
            }
        }

        sc.close();
    }
}
