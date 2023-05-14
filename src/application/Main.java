package application;

import entities.CarRental;
import entities.Vehicle;
import services.BrazilTaxService;
import services.RentalService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Interface e um tipo que define um conjunto de operacoes que uma classe deve implementar;
        //Estabelece um contrato que a classe deve cumprir;
        //Serve para criar sistemas com baixo acoplamento e flexiveis

        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");

        System.out.println("Enter with rental data: ");
        System.out.print("Car model: ");
        String carModel = sc.nextLine();
        System.out.println("Rental start date (dd/MM/yyyy hh:mm): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
        System.out.println("Rental finish date (dd/MM/yyyy hh:mm): ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);

        CarRental cr = new CarRental(start, finish, new Vehicle(carModel));

        System.out.println("Enter with hour price: ");
        double pricePerHour = sc.nextDouble();
        System.out.println("Enter with day price: ");
        double pricePerDay = sc.nextDouble();

        RentalService rs = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());

        rs.processInvoice(cr);

        System.out.println("Invoice: ");
        System.out.println("Basic payment: " + cr.getInvoice().getBasicPayment());
        System.out.println("Tax: " + cr.getInvoice().getTax());
        System.out.println("Total payment: " + cr.getInvoice().getTotalPayment());


        sc.close();
    }
}