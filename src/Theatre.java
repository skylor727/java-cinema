package cinema;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Theatre {
    int[][] theatreSeats;
    int ticketsSold, totalPossible, currentTotal, rows, seats;
    double percentageSold;
    ArrayList<Integer> chosenSeat;

    public Theatre(int rows, int seats) {
        this.rows = rows;
        this.seats = seats;
        this.theatreSeats = new int[rows][seats];
        this.chosenSeat = new ArrayList<>();
        this.ticketsSold = 0;
        this.totalPossible = 0;
        this.currentTotal = 0;
        this.percentageSold = 0.00;
    }

    public void printTheatre() {
        System.out.println("Cinema:");
        System.out.print("  ");
        int seats = this.theatreSeats[0].length;
        int rows = this.theatreSeats.length;
        for (int i = 0; i < seats; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1);
            for (int x = 0; x < seats; x++) {
                if (this.theatreSeats[i][x] != 1) {
                    System.out.print(" S");
                } else System.out.print(" B");
            }
            System.out.println();
        }
        System.out.println();

    }

    public void chooseSeat() {
        Scanner sc = new Scanner(System.in);
        boolean availableSeat = false;
        do {
            System.out.println("Enter a row number:");
            int row = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seat = sc.nextInt();
            if (row > this.rows || row < 1 || seat > seats || seat < 1) {
                System.out.println("Wrong input!");
            } else if (this.theatreSeats[row - 1][seat - 1] != 0) {
                System.out.println("That ticket has already been purchased");
            } else {
                if (this.chosenSeat.size() == 0) {
                    this.chosenSeat.add(row);
                    this.chosenSeat.add(seat);
                } else {
                    this.chosenSeat.set(0, row);
                    this.chosenSeat.set(1, seat);
                }
                this.theatreSeats[row - 1][seat - 1] = 1;
                this.ticketsSold++;
                availableSeat = true;
            }
        } while (!availableSeat);
    }

    public int calculateSeatCost() {
        int row = this.chosenSeat.get(0);
        int rows = this.theatreSeats.length;
        int seats = this.theatreSeats[0].length;
        int numSeats = rows * seats;
        int frontRows = rows / 2;
        if (numSeats > 60 && frontRows < row) {
            this.currentTotal += 8;
            return 8;

        } else {
            this.currentTotal += 10;
            return 10;
        }
    }

    public void calculateTotalPossible() {
        int rows = this.theatreSeats.length;
        int seats = this.theatreSeats[0].length;
        int numSeats = rows * seats;
        int frontRows = rows / 2;
        if (numSeats > 60) {
            int eightDollarSeats = numSeats - (frontRows * seats);
            this.totalPossible += eightDollarSeats * 8;
            this.totalPossible += (numSeats - eightDollarSeats) * 10;
        } else {
            this.totalPossible = numSeats * 10;
        }
    }

    public void printStatistics() {
        if (this.totalPossible == 0) {
            this.calculateTotalPossible();
        }
        this.calculatePercentage();
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("Number of purchased tickets: " + this.ticketsSold);
        System.out.println("Percentage: " + df.format(this.percentageSold) + "%");
        System.out.println("Current income: $" + this.currentTotal);
        System.out.println("Total income: $" + this.totalPossible);
        System.out.println();
    }

    public void calculatePercentage() {
        int rows = this.theatreSeats.length;
        int seats = this.theatreSeats[0].length;
        int numSeats = rows * seats;
        this.percentageSold = (ticketsSold / (double) numSeats) * 100;

    }
}