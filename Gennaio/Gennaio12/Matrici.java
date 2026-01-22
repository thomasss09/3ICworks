package Gennaio12;


/*Lo scopo è avere un programma che ci permette di lavorare e modificare le matrici
Nel main, leggi le dimensioni N e M dall'utente
Fai un metodo che crea una matrice NxM riempita di numeri casuali da 0 a 99 (inclusi), e
chiamalo nel main 
A questo punto, presenta all'utente una lista di comandi (il "menu")
Trasposta: scrive a schermo la matrice trasposta
ScambiaRighe: chiede due indici di riga, mostra a schermo la matrice con le righe
scambiate. Salva la matrice (dal prossimo comando usiamo questa matrice modificata)
ScambiaColonne: vedi ScambiaRighe
SommaRighe: stampa la somma dei valori di ogni riga
SommaColonne: vedi SommaRighe
Esci: termina il programma
Il programma deve terminare solo quando l'utente sceglie l'opzione "Esci". Altrimenti, dopo ogni
comando, mostra nuovamente il "menu"
Ogni comando deve usare un metodo statico per generare la matrice/array richiesto, l'output
avviene solo nel main */
public class Matrici {

    public static int[][] creaMatrice(int N, int M) { // M per le colonne N per le righe
        int i = 0;
        int[][] matrice = new int[N][M];
        for (int J = 0; J < N; J++) {
            for (int K = 0; K < M; K++) {
                i = (int) (Math.random() * 100);
                matrice[J][K] = i;
            }
        }
        return matrice;
    }

    // CREO IL MENUUUUU
    
    //trasposta
    public static int[][] trasporta(int N , int M) {
        System.out.println(Arrays.toString(creaMatrice(N, M)));
        return creaMatrice(N, M);
    }
    //Scambia righe

    public static int[][] scambiaRighe(int[][] matrice, int riga1, int riga2) {
        int s = 0;
        int colonne = matrice[0].length; // mi da la lunghezza della colonna 
        for (int i = 0; i < colonne; i++) {           //continuo per la lunghezza della colonna 
            s = matrice[riga1][i];             // mi tengo il numero della 1 riga 
            matrice[riga1][i] = matrice[riga2][i];    // mi prendo il numero della 2 riga
            matrice[riga2][i] = s;                // do alla 2 riga il numero che mi ero tenuto della 1
        }
        return matrice;
    }

    // stampo la matrice 
    public static void stampaMatrice(int[][] matrice) {
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                System.out.print(matrice[i][j] + "\t");
            }
            System.out.println();
        }
    }
       //stampo le colonne
    public static int[][] ScambiaColonne(int[][] matrice, int colonna1, int colonna2) {
        int t = 0;
        int colonne = matrice[0].length;                    // mi da la lunghezza della colonna 
        for (int i = 0; i < colonne; i++) {                 //continuo per la lunghezza della colonna 
            t = matrice[colonna1][i];                       // mi tengo il numero della 1 colonna 
            matrice[colonna1][i] = matrice[colonna2][i];     // mi prendo il numero della 2 colonna
            matrice[colonna2][i] = t;                         // do alla 2 colonna il numero che mi ero tenuto della 1
        }
        return matrice;
    }
    // inizio a formare il menù
    public static void creaMenu(int opzione , int[][] matrice){
        switch(opzione){
            case 1:
                
                
            break;
            case 2:  //scambiarighe

            break;
            case 3:   //scambiacolonne

            break;
            case 4:  //sommarighe
 
            break;
            case 5:   //sommacolonne

            break;
            case 6:  //esce dal menù

            break;
            default:
                if(opzione < 1 || opzione > 6){
            System.out.println("numero errato.. indexOutOfBuonds!!!"); 
            }
            break;
        }
    }
}
