import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

class FunctionsTest
{
    private static void ReadData(ArrayList<Integer> data)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter N: ");
        int n = scanner.nextInt();

        System.out.print("Enter the array: ");
        for(int i = 0; i < n; i++)
        {
            int num = scanner.nextInt();
            data.add(num);
        }
        scanner.close(); 
    }

    private static void SortData(ArrayList<Integer> data)
    {
        Collections.sort(data);
    }

    private static void PrintData(ArrayList<Integer> data)
    {
        System.out.print("Array is: ");
        for(Integer i: data)
        {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    public static void main(String[] args)
    {
        ArrayList<Integer> array = new ArrayList<>();
        ReadData(array);
        SortData(array);
        PrintData(array);
    }
}