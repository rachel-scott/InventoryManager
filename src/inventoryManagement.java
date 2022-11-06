import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.*;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

public class inventoryManagement {

    public static void main(String[] args) throws IOException, EOFException {

        File file = new File("Inventory.txt");

        ArrayList<String> productNameArray = new ArrayList<String>();
        ArrayList<Integer> quantityArray = new ArrayList<Integer>();
        ArrayList<Double> unitPriceArray = new ArrayList<Double>();

        //
        int count = 0;
        String theLine;

        try {
            Scanner input3 = new Scanner(file);

            while (true) {
                theLine = input3.nextLine();
                count++;
            }
        }
             catch (NoSuchElementException e) {
                 System.out.print("");
            }
        //
        String inventoryRead;
        try {
            Scanner input = new Scanner(file);
            for (int i = 0; i < count; i++) {

                inventoryRead = input.nextLine();

                StringTokenizer phrase = new StringTokenizer(inventoryRead, ",");

                productNameArray.add(phrase.nextToken());
                quantityArray.add(Integer.parseInt(phrase.nextToken()));
                unitPriceArray.add(Double.parseDouble(phrase.nextToken()));

            }
        } catch (FileNotFoundException ex) {
            System.out.printf("ERROR %s\n", ex);
        }


        boolean exited = false;

        while (!exited) {

            Scanner input2 = new Scanner(System.in);
            System.out.println("Menu – Type the number of the desired option" + "\n" + "1 Search Product" + "\n" + "2 Reduce Inventory" + "\n" + "3 Add New Product" + "\n" + "4 Bulk Price Change" + "\n" + "5 Exit");

            String option = input2.next();

            if (option.equals("1")) {
                System.out.println("Enter product name: ");
                String productName = input2.next();

                for (int i = 0; i < productNameArray.size(); i++) {
                    if (productName.equals(productNameArray.get(i))) {
                        System.out.println(productNameArray.get(i) + ", " + quantityArray.get(i) + ", " + unitPriceArray.get(i));
                        i = productNameArray.size() + 1;
                    } else if (!productNameArray.get(i).equals(productName) && i == productNameArray.size() - 1) {
                        System.out.println("Product not found.");
                    }
                }

            } else if (option.equals("2")) {
                System.out.println("Enter product name: ");
                String productName2 = input2.next();

                for (int i = 0; i < productNameArray.size(); i++) {
                    if (productName2.equals(productNameArray.get(i))) {
                        System.out.println("How many would you like to remove from inventory?");
                        int removing = input2.nextInt();
                        if (removing <= quantityArray.get(i)) {
                            quantityArray.set(i, quantityArray.get(i) - removing);
                            System.out.println("You requested " + removing + " " + productNameArray.get(i) + ". You now have " + quantityArray.get(i) + " " + productNameArray.get(i) + ".");
                        } else if (removing > quantityArray.get(i)) {
                            System.out.println("You requested " + removing + " " + productNameArray.get(i) + ", but there were only " + quantityArray.get(i) + " in stock. You now have 0. Please reorder.");
                            quantityArray.set(i, 0);
                        }
                        i = productNameArray.size() + 1;
                    } else if (!productNameArray.get(i).equals(productName2) && i == productNameArray.size() - 1) {
                        System.out.println("Product not found.");
                    }
                }
            } else if (option.equals("3")) {
                System.out.println("Enter new product name: ");
                productNameArray.add(input2.next());
                System.out.println("Enter starting inventory: ");
                quantityArray.add(input2.nextInt());
                System.out.println("Enter unit price: ");
                unitPriceArray.add(input2.nextDouble());


            } else if (option.equals("4")) {
                System.out.println("Enter price change (in percent)");
                double priceChange = input2.nextDouble();
                priceChange = priceChange / 100; // turn into percentage

                for (int i = 0; i < unitPriceArray.size(); i++) {
                    unitPriceArray.set(i, unitPriceArray.get(i) + unitPriceArray.get(i) * priceChange);
                }
                for (int i = 0; i < unitPriceArray.size(); i++) {
                    System.out.println(productNameArray.get(i) + "," + quantityArray.get(i) + "," + unitPriceArray.get(i));
                }


            } else if (option.equals("5")) {
                System.out.println("Goodbye.");

                try {
                    PrintWriter output = new PrintWriter(file);
                    for (int i = 0; i < productNameArray.size(); i++) {
                        output.print(productNameArray.get(i) + "," + quantityArray.get(i) + "," + unitPriceArray.get(i) + "\n");
                    }
                    output.close();
                } catch (IOException ex) {
                    System.out.printf("ERROR: %s\n", ex);
                }

                exited = true;
            } else {
                System.out.println("Please choose an option from the list.");
            }

        }

        try {
            PrintWriter output = new PrintWriter(file); //writing object

            for (int i = 0; i < productNameArray.size(); i++) {

                output.println(productNameArray.get(i) + "," + quantityArray.get(i) + "," + unitPriceArray.get(i));
            }

        output.close();
        }
        catch (IOException ex) {
            System.out.printf("ERROR: %s\n", ex);
        }

    }
}
