import java.util.ArrayList;
import java.util.Scanner;

public class ForLoop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter N: ");
        int n = scanner.nextInt();

        ArrayList<Integer> array = new ArrayList<Integer>(5);
        
        System.out.print("Enter the array: ");
        for(int i = 0; i < n; i++)
        {
            int num = scanner.nextInt();
            array.add(num);
        }
        
        System.out.print("Array is: ");
        for(Integer num: array)
        {
            System.out.print(num + " ");
        }
        System.out.println();
        
        scanner.close(); 
    }
}