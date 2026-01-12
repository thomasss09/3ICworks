package Gennaio12;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N, M, riga1, riga2, colonna1, colonna2, opzione;
        Scanner SC = new Scanner(System.in);
        System.out.println("dimmi il numero di colonne della tua matrice  ");
        M = SC.nextInt();
        System.out.println("dimmi il numero di righe della tua matrice");
        N = SC.nextInt();
        System.out.println("questa è la tua matrice attuale");
        int[][] matrone = Matrici.creaMatrice(N, M);
        Matrici.stampaMatrice(matrone);

        System.out.println("ECCO IL MENU': -Trasposta(1) || -ScambiaRighe(2) || -ScambiaColonne(3) || -SommaRighe(4) || -SommaColonne(5) || -Esci(6) ");
        System.out.println("scegli un opzione per continuare(1-6)");
        opzione = SC.nextInt();

        System.out.println("dimmi la 1 righa che vuoi cambiare  ");
        riga1 = SC.nextInt();
        System.out.println("dimmi la  2 righa che vuoi cambiare ");
        riga2 = SC.nextInt();

        System.out.println("dimmi la  1 colonna che vuoi cambiare  ");
        colonna1 = SC.nextInt();
        System.out.println("dimmi la 2 colonna che vuoi cambiare ");
        colonna2 = SC.nextInt();

        SC.close();
    }
    
}
