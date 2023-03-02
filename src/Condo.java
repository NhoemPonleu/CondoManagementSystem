import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class Condo {
	public static void pressAnyKey(){
        Scanner input = new Scanner(System.in);
        System.out.println("Press Enter to continue...!");
        input.nextLine();
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int option = 0;
        int floor, room = 0;
        String[][] condo = new String[0][];
        boolean isFloorValind = false, isRoomValid = false; //? why need to initialize false?
        //set up
       // boolean b = !isRoomValid;
       // boolean b = !isRoomValid;
        do{
            System.out.println("----------------- Set Up Condo -----------------");
            System.out.println("Enter number of floor : ");
            floor = input.nextInt();
            if(floor>0){
                isFloorValind = true;
                System.out.println("Enter number of room : ");
                room = input.nextInt();
                if(room>0){
                    isRoomValid = true;
                    condo = new String[floor][room];
                    System.out.println("Congratulation! Successfully set up condo...!");
                    System.out.println("Floor : "+floor+" floor"+((floor>1) ? "s" : ""));
                    System.out.println("Room : "+room+" room");
                    System.out.println("Total rooms are "+(floor * room)+" rooms");
                }
            }
            pressAnyKey();
        }while ( !isRoomValid); //?

        do{
            System.out.println("-------------------- Welcome to CMS --------------------");
            System.out.println("1. Buy condo ");
            System.out.println("2. Sell condo ");
            System.out.println("3. Search condo information ");
            System.out.println("4. Show condo information ");
            System.out.println("5. Exit ");
            System.out.println("Choose any option from 1 to 5 : ");
            option = input.nextInt();
            switch (option){
                case 1:
                    int desiredRoom;
                    int desiredFloor;
                    String ownerName;
                    int buyOption;
                    boolean isConditionValid = false;
                    do {
                        System.out.println("----------------- Buy Condo ----------------");
                        System.out.println("Enter desired floor : ");
                        desiredFloor = input.nextInt();
                        if (desiredFloor > 0 && desiredFloor <= floor) ;
                        isFloorValind = true;
                        System.out.println("Enter desired room : ");
                        desiredRoom = input.nextInt();
                        if (desiredRoom > 0 && desiredRoom <= room) {
                            if (condo[desiredFloor - 1][desiredRoom - 1] == null) {
                                isRoomValid = true;
                                isConditionValid = true;
                                System.out.println("Enter owner name : ");
                                input.nextLine();
                                ownerName = input.nextLine();
                                condo[desiredFloor - 1][desiredRoom - 1] = ownerName;
                                System.out.println("Congratulation...! You have successfully bought a condo.");
                            } else {
                                System.out.println("The room is already owned by someone!");
                                isRoomValid = false;
                            }
                        } else {
                            System.out.println("Error : Room number is invalid!");
                            isRoomValid = false;
                        }
                        if( !isFloorValind){
                            System.out.println("-------------------------------------------------");
                            System.out.println("Press 1 to exit and press other number to continue...!");
                            System.out.println("Choose your option : ");
                            buyOption = input.nextInt();
                            if(buyOption==1){
                                isConditionValid = true;
                            }else {
                                isConditionValid = false;
                            }
                        }
                    }while (!isConditionValid);
                    break;
                case 2:
                    String ownerCondoName;
                    boolean isConditionValid1 = false;
                    do{
                        System.out.println("------------------------- Sell Condo ------------------------");
                        System.out.println("Enter desired floor : ");
                        desiredFloor = input.nextInt();
                        System.out.println("Enter desired room : ");
                        desiredRoom = input.nextInt();
                        if(desiredFloor>0 && desiredFloor<=floor && desiredRoom>0 && desiredRoom<=room){
                            if(condo[desiredFloor-1][desiredRoom-1]!=null){
                                System.out.println("Enter the owner name : ");
                                input.nextLine();
                                ownerCondoName = input.nextLine();
                                if(ownerCondoName.equalsIgnoreCase(condo[desiredFloor-1][desiredRoom-1])){
                                    // assign
                                    condo[desiredFloor-1][desiredRoom-1]=null;//??
                                    System.out.println("Congratulation! You sold the condo...!");
                                    isRoomValid = false;
                                }else {
                                    System.out.println("Error! Valid name is required ");
                                    isRoomValid = false;
                                }
                            }else {
                                System.out.println("Sorry! This condo is owning by someone...!");
                                isRoomValid = false;
                            }
                        }else {
                            System.out.println("Error! Floor and room are invalid...!");
                            isRoomValid = false;
                        }
                        if(!isRoomValid || !isFloorValind){
                            System.out.println("-------------------------------------------------");
                            System.out.println("Press 1 to exit and press other number to continue...!");
                            System.out.println("Choose your option : ");
                            buyOption = input.nextInt();
                            if(buyOption==1){
                                isConditionValid1 = true;
                            }else {
                                isConditionValid1 = false;
                            }
                        }
                    }while (!isConditionValid1);
                    break;
                case 3:
                    int searchOption = 0;
                    int outOption;
                    boolean isConditionValid2 =  false;
                    System.out.println("------------------------ Search Condo Information ----------------------");
                    System.out.println("1. Search by floor");
                    System.out.println("2. Search by floor and room");
                    System.out.println("3. Exit");
                    System.out.print("Choose your option from 1 to 3 : ");
                    searchOption = input.nextInt();
                    switch (searchOption){
                        case 1 -> {
                            System.out.println("---------------------- Search by floor ------------------------");
                            int searchFloor = 0;
                            System.out.println("Enter the floor to search : ");
                            searchFloor = input.nextInt();
                            if(searchFloor>0 && searchFloor<=floor){
                                System.out.print("Floor " + searchFloor + " : ");
                                for(int i=0; i<condo[searchFloor-1].length;i++){
                                    System.out.print("\t"+condo[searchFloor-1][i]);
                                }
                                System.out.println(); //print new line
                            }else {
                                System.out.println("ERROR! Invalid Floor (Choose from 1 to "+floor+" ) ");
                            }
                        }
                        case 2 -> {
                            int searchFloor;
                            int searchRoom;
                            System.out.println("---------------------- Search by floor and room -----------------------");
                            System.out.println("Enter search floor : ");
                            searchFloor = input.nextInt();
                            System.out.println("Enter search room : ");
                            searchRoom = input.nextInt();
                            // searchFloor & searchRoom apply condition
                            if(searchFloor>0 && searchFloor<=floor && searchRoom>0 && searchRoom<= room){
                                System.out.println("Floor of : "+searchFloor+" Room of : " +searchRoom+
                                        ((condo[searchFloor-1][searchRoom-1]==null)? " is available"
                                                : " => Owner is "+ condo[searchFloor-1][searchRoom-1]));
                                // print condo[floor-1][room-1]
                            }else{
                                System.out.println("ERROR!! searchRoom and searchFloor is incorrect ");
                                System.out.println("Choose Floor from  1 to "+floor);
                                System.out.println("Choose Room from  1 to "+room);
                            }
                            // print condo[floor-1][room-1]
                        }
                        case 3 -> System.out.println("Exit the search menu......!");
                        default -> System.out.println("Wrong option! Please choose from 1 to 3...!");
                    }
                    break;
                case 4:
                    System.out.println("------------------------- Show Condo info ---------------------");
                    for(int i=0; i<condo.length; i++){
                        for(int j=0; j<condo[i].length; j++){
                            System.out.print("\t"+condo[i][j]);
                        }
                        System.out.println();
                    }
                    break;
                case 5: break;
            }
            pressAnyKey();
        }while (option!=5);
    }
}
