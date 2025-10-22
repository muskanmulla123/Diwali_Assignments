package TestMain;

import Node.BinarySearchTree;

import java.util.Scanner;

public class BinaryMain {

    // Main menu
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BinarySearchTree bst = new BinarySearchTree();
        int choice, value;

        while (true) {
            System.out.println("\n--- Binary Search Tree Menu ---");
            System.out.println("1. Add Node");
            System.out.println("2. Remove Node");
            System.out.println("3. Display Tree (Inorder, Preorder, Postorder)");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to insert: ");
                    value = sc.nextInt();
                    bst.add(value);
                    System.out.println("Node inserted successfully!");
                    break;
                case 2:
                    System.out.print("Enter value to delete: ");
                    value = sc.nextInt();
                    bst.remove(value);
                    System.out.println("Node deleted successfully (if present)!");
                    break;
                case 3:
                    bst.inorder();
                    bst.preorder();
                    bst.postorder();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice!");

        }
    }
}
}
