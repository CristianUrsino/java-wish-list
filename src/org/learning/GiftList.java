package org.learning;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GiftList {
    public static void main(String[] args) {
        //definisco lo scanner
        Scanner scanner = new Scanner(System.in);
        //inizializzo la posizione del file
        File giftListText = new File("./resources/giftList.txt");
        //dichiaro la lista dei regali attuali
        List<String> giftList = new ArrayList<>();
        //dichiaro la lista dei regali salvati nel file
        List<String> completeGiftList = new ArrayList<>();
        //inizializzo la lista con i valori precedentemente inseriti
        try(Scanner fileScanner = new Scanner(giftListText)){
            while(fileScanner.hasNext()){
                completeGiftList.add(fileScanner.nextLine());
            }
        }catch(IOException e){
            System.out.println("Unable to read file");
        }
        //inizializzo la flag per concludere l'iterazione dell'inserimento del regalo
        boolean exit = false;
        //dichiaro l'indice che indica quanti valori sono stati aggiunti
        do{
            //prendo in input la conferma di continuare
            System.out.print((!giftList.isEmpty() ? "Size list: " + giftList.size() + ". " : "")
                    + "Insert a" + (!giftList.isEmpty() ? " next " : " first ") + "gift? y-n: ");
            String choise = scanner.nextLine();
            //formatto l'input
            choise = choise.replaceAll(" ", "");
            switch (choise.toLowerCase()){
                case "y":
                    do{
                        //input di gift attuale
                        System.out.print("Enter the gift name: ");
                        giftList.add(scanner.nextLine());
                        //formattazione per la validazione
                        String currentGiftValidated = giftList.get(giftList.size() - 1).replaceAll(" ","");
                    }while(giftList.get(giftList.size() - 1).isEmpty());
                    break;
                case "n":
                    exit = true;
                    break;
                default:
                    System.out.println("Error, choise invalid, try again");
                    break;
            }
        }while(!exit);
        //outputs di fine iterazione
        if(!giftList.isEmpty()){
            System.out.println("\n" + giftList.size() + " gifts inserted: ");
            Collections.sort(giftList);
            for(String gift : giftList){
                System.out.print(gift + " - " );
                completeGiftList.add(gift);
            }
            //ordino i regali completi
            Collections.sort(completeGiftList);
            //instanzio il fileWriter facendo si che cancelli gli elementi precedenti per poterli inserire ORDINATI
            // invece di fare FileWriter(file, TRUE) per mantenere quelli precedenti in quanto non sarebbe possibile ordinarli
            try(FileWriter fileWriter = new FileWriter(giftListText)){
                //istanzio l'iterator
                Iterator<String> iter = completeGiftList.iterator();
                System.out.println(completeGiftList);
                while(iter.hasNext()){
                    //scrivo sul file ogni regalo
                    fileWriter.write("\n" + iter.next());
                }
            }catch(IOException e){
                System.out.println("Unable to open file");
            }
        }
        else System.out.println("List is empty");
        //leggo i valori di tutto il file
        try(Scanner fileScanner = new Scanner(giftListText)){
            System.out.println("\n\nGift in the file: ");
            while(fileScanner.hasNext()){
                System.out.println(fileScanner.nextLine());
            }
        }catch(IOException e){
            System.out.println("Unable to read file");
        }
        //chiudo la risorsa scanner
        scanner.close();
    }
}
