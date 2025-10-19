package TestMain;

import Node.Node;

import java.util.Scanner;

public class CircularMain {

    static  class CircularLinkedList{


        Node head=null;

        public void insertAtPosition(int data, int position) {
            Node newNode = new Node(data);

            // If list is empty, always make newNode the head
            if (head == null) {
                head = newNode;
                newNode.next = head;
                return;
            }

            // If inserting at position 1 (head)
            if (position <= 1) {
                Node temp = head;
                while (temp.next != head) {
                    temp = temp.next;
                }
                temp.next = newNode;
                newNode.next = head;
                head = newNode;
                return;
            }

            // Otherwise, insert at given position or at end if position is too large
            Node current = head;
            int count = 1;

            while (count < position - 1 && current.next != head) {
                current = current.next;
                count++;
            }

            newNode.next = current.next;
            current.next = newNode;
        }

        // b) Delete node by data
        public void deleteNode(int data) {
            if (head == null) {
                System.out.println("List is empty!");
                return;
            }

            Node current = head, prev = null;
            // If head is to be deleted
            if (head.data == data) {
                Node temp = head;
                while (temp.next != head)
                    temp = temp.next;
                if (temp == head) { // only one node
                    head = null;
                } else {
                    temp.next = head.next;
                    head = head.next;
                }
                System.out.println("Node deleted: " + data);
                return;
            }

            // Traverse to find node
            do {
                prev = current;
                current = current.next;
                if (current.data == data) {
                    prev.next = current.next;
                    System.out.println("Node deleted: " + data);
                    return;
                }
            } while (current != head);

            System.out.println("Node not found!");
        }

        // c) Modify node
        public void modifyNode(int oldData, int newData) {
            if (head == null) {
                System.out.println("List is empty!");
                return;
            }
            Node temp = head;
            do {
                if (temp.data == oldData) {
                    temp.data = newData;
                    System.out.println("Node modified successfully.");
                    return;
                }
                temp = temp.next;
            } while (temp != head);

            System.out.println("Node not found!");
        }

        // d) Display nodes
        public void display() {
            if (head == null) {
                System.out.println("List is empty!");
                return;
            }
            Node temp = head;
            System.out.print("Circular Linked List: ");
            do {
                System.out.print(temp.data + " -> ");
                temp = temp.next;
            } while (temp != head);
            System.out.println("(head)");
        }
    }

public static void main(String args[])
{
    CircularLinkedList list = new CircularLinkedList();
    Scanner sc = new Scanner(System.in);

    while (true) {
        System.out.println("\n--- Circular Linked List Menu ---");
        System.out.println("1. Insert node at position");
        System.out.println("2. Delete node by data");
        System.out.println("3. Modify node");
        System.out.println("4. Display list");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Enter data: ");
                int data = sc.nextInt();
                System.out.print("Enter position: ");
                int pos = sc.nextInt();
                list.insertAtPosition(data, pos);
                break;
           case 2:
                System.out.print("Enter data to delete: ");
                int del = sc.nextInt();
                list.deleteNode(del);
                break;
            case 3:
                System.out.print("Enter old data: ");
                int oldData = sc.nextInt();
                System.out.print("Enter new data: ");
                int newData = sc.nextInt();
                list.modifyNode(oldData, newData);
                break;
            case 4:
                list.display();
                break;
            case 5:
                System.out.println("Exiting...");
                sc.close();
                System.exit(0);
            default:
                System.out.println("Invalid choice!");
        }
    }
}
}



