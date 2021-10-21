package ui;

import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        Scanner userInput = new Scanner(System.in);
        System.out.println(prompt);
        String response = userInput.nextLine();
        return response;
    }

    @Override
    public int readInt(String prompt) {
        Scanner userInput = new Scanner(System.in);
        System.out.println(prompt);
        Integer response = Integer.parseInt(userInput.nextLine());
        return response;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        Scanner userInput = new Scanner(System.in);
        Integer response;
        while (true) {
            System.out.println(prompt);
            response = Integer.parseInt(userInput.nextLine());
            if (response <= max && response >= min) {
                break;
            }
        }
        return response;
    }

    @Override
    public double readDouble(String prompt) {
        Scanner userInput = new Scanner(System.in);
        System.out.println(prompt);
        Double response = Double.parseDouble(userInput.nextLine());
        return response;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        Scanner userInput = new Scanner(System.in);
        Double response;
        while (true) {
            System.out.println(prompt);
            response = Double.parseDouble(userInput.nextLine());
            if (response <= max && response >= min) {
                break;
            }
        }
        return response;
    }

    @Override
    public float readFloat(String prompt) {
        Scanner userInput = new Scanner(System.in);
        System.out.println(prompt);
        Float response = Float.parseFloat(userInput.nextLine());
        return response;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        Scanner userInput = new Scanner(System.in);
        Float response;
        while (true) {
            System.out.println(prompt);
            response = Float.parseFloat(userInput.nextLine());
            if (response <= max && response >= min) {
                break;
            }
        }
        return response;
    }

    @Override
    public long readLong(String prompt) {
        Scanner userInput = new Scanner(System.in);
        System.out.println(prompt);
        Long response = Long.parseLong(userInput.nextLine());
        return response;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        Scanner userInput = new Scanner(System.in);
        Long response;
        while (true) {
            System.out.println(prompt);
            response = Long.parseLong(userInput.nextLine());
            if (response <= max && response >= min) {
                break;
            }
        }
        return response;
    }

    @Override
    public int readMoney(String prompt) {
        Scanner userInput = new Scanner(System.in);

        String amountString;
        int output;
        while (true) {
            System.out.println(prompt);
            amountString = userInput.nextLine();
            int decimalPosition = amountString.indexOf(".");
            if (decimalPosition == -1 || amountString.length() - decimalPosition <= 3) {
                try {
                    output = (int) (Float.parseFloat(amountString) * 100);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Not a valid input");
                }
            }

        }
        return output;
        //return readInt(prompt);

    }
}