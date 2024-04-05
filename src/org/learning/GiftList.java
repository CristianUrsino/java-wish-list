package org.learning;

import java.util.*;

public class GiftList {
    public static void main(String[] args) {
        //definisco lo scanner
        Scanner scanner = new Scanner(System.in);
        //dichiaro la lista dei regali
        List<String> giftList = new ArrayList<>();
        //inizializzo la flag per concludere l'iterazione dell'inserimento del regalo
        boolean exit = false;
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
        //outputs finale
        if(!giftList.isEmpty()){
            System.out.println("\n" + giftList.size() + " gifts inserted: ");
            Collections.sort(giftList);
            for(String gift : giftList){
                System.out.print(gift + " - " );
            }
        }
        else System.out.println("List is empty");
        //chiudo la risorsa scanner
        scanner.close();
    }
}
