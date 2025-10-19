package TestMain;

import Node.Node;

import java.util.Scanner;

public class CircularQueueDemo {

    static class CircularQueue {
        private Node front, rear;

        // Constructor
        public CircularQueue() {
            front = rear = null;
        }

        // a) Enqueue (Insert element)
        public void enqueue(int data) {
            Node newNode = new Node(data);

            // If queue is empty
            if (front == null) {
                front = rear = newNode;
                rear.next = front; // circular link
            } else {
                rear.next = newNode;
                rear = newNode;
                rear.next = front; // maintain circular connection
            }
            System.out.println(data + " inserted into queue.");
        }

        // b) Dequeue (Delete element)
        public void dequeue() {
            if (front == null) {
                System.out.println("Queue is empty! Cannot delete.");
                return;
            }

            int value;
            // If only one element
            if (front == rear) {
                value = front.data;
                front = rear = null;
            } else {
                value = front.data;
                front = front.next;
                rear.next = front; // maintain circular connection
            }
            System.out.println("Deleted: " + value);
        }

        // c) Peek (Front element)
        public void peek() {
            if (front == null) {
                System.out.println("Queue is empty!");
            } else {
                System.out.println("Front element: " + front.data);
            }
        }

        // d) Display all elements
        public void display() {
            if (front == null) {
                System.out.println("Queue is empty!");
                return;
            }

            System.out.print("Circular Queue: ");
            Node temp = front;
            do {
                System.out.print(temp.data + " ");
                temp = temp.next;
            } while (temp != front);
            System.out.println();
        }
    }

    // main() to test
    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue();
        Scanner sc = new Scanner(System.in);
        int choice, data;

        while (true) {
            System.out.println("\n--- Circular Queue Menu ---");
            System.out.println("1. Enqueue (Insert)");
            System.out.println("2. Dequeue (Delete)");
            System.out.println("3. Peek (Front Element)");
            System.out.println("4. Display");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter data: ");
                    data = sc.nextInt();
                    queue.enqueue(data);
                    break;
                case 2:
                    queue.dequeue();
                    break;
                case 3:
                    queue.peek();
                    break;
                case 4:
                    queue.display();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }}
