

import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        cinema.Theatre theatre = createTheatre();
        menu(theatre);
    }

    public static cinema.Theatre createTheatre() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = sc.nextInt();
        cinema.Theatre theatre = new cinema.Theatre(rows, seats);
        return theatre;
    }

    public static void menu(cinema.Theatre theatre) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int choice = sc.nextInt();
            if (choice == 0) break;
            switch (choice) {
                case 1:
                    theatre.printTheatre();
                    break;
                case 2:
                    theatre.chooseSeat();
                    System.out.println("Ticket price: $" + theatre.calculateSeatCost());
                    break;
                case 3:
                    theatre.printStatistics();
                    break;
            }
        }
    }
}