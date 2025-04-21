import java.util.Random;
import java.util.Scanner;

public class BatalhaNavalMatematico {
    public static void main(String[] args) {
        int tamanhoTabuleiro = 5;
        char[][] tabuleiro = new char[tamanhoTabuleiro][tamanhoTabuleiro];
        boolean[][] navios = new boolean[tamanhoTabuleiro][tamanhoTabuleiro];
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);

        // Inicializa tabuleiro e posiciona navios
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                tabuleiro[i][j] = '~'; // Água
                navios[i][j] = rand.nextBoolean() && rand.nextBoolean(); // Navios esparsos
            }
        }

        int naviosRestantes = 0;
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                if (navios[i][j]) naviosRestantes++;
            }
        }

        System.out.println("=== BATALHA NAVAL MATEMÁTICO ===");
        System.out.println("Resolva as operações para atacar!");

        while (naviosRestantes > 0) {
            // Exibe tabuleiro
            System.out.println("\nTabuleiro:");
            for (int i = 0; i < tamanhoTabuleiro; i++) {
                for (int j = 0; j < tamanhoTabuleiro; j++) {
                    System.out.print(tabuleiro[i][j] + " ");
                }
                System.out.println();
            }

            // Gera operação matemática
            int a = rand.nextInt(10) + 1;
            int b = rand.nextInt(10) + 1;
            String[] operadores = {"+", "-", "*"};
            String operador = operadores[rand.nextInt(3)];
            System.out.printf("\nResolva: %d %s %d = ? ", a, operador, b);

            int respostaJogador = scanner.nextInt();
            int respostaCorreta;
            switch (operador) {
                case "+": respostaCorreta = a + b; break;
                case "-": respostaCorreta = a - b; break;
                case "*": respostaCorreta = a * b; break;
                default: respostaCorreta = 0;
            }

            if (respostaJogador == respostaCorreta) {
                System.out.println("Acertou! Agora escolha as coordenadas (linha e coluna de 1 a 5):");
                int linha = scanner.nextInt() - 1;
                int coluna = scanner.nextInt() - 1;

                if (linha >= 0 && linha < tamanhoTabuleiro && coluna >= 0 && coluna < tamanhoTabuleiro) {
                    if (navios[linha][coluna]) {
                        System.out.println("BOOM! Navio atingido!");
                        tabuleiro[linha][coluna] = 'X';
                        naviosRestantes--;
                    } else {
                        System.out.println("Splash! Água.");
                        tabuleiro[linha][coluna] = 'O';
                    }
                } else {
                    System.out.println("Coordenadas inválidas!");
                }
            } else {
                System.out.println("Errou a operação! Perdeu a vez.");
            }
        }

        System.out.println("\nParabéns! Você afundou todos os navios!");
        scanner.close();
    }
}