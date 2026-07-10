import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        // Sample starter data
        library.addBook(new Book("B101", "The Alchemist", "Paulo Coelho"));
        library.addBook(new Book("B102", "Atomic Habits", "James Clear"));
        library.addBook(new Book("B103", "Java Fundamentals", "Herbert Schildt"));

        library.addMember(new Member("M201", "Ravi"));
        library.addMember(new StudentMember("M202", "Priya", "Ashoka Women's Engineering College", "21CSE045"));

        int choice;
        do {
            printMenu();
            choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1:
                    System.out.print("Book ID: ");
                    String bId = sc.nextLine();
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    library.addBook(new Book(bId, title, author));
                    break;

                case 2:
                    System.out.print("Member ID: ");
                    String mId = sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    library.addMember(new Member(mId, name));
                    break;

                case 3:
                    System.out.print("Book ID to issue: ");
                    String issueBookId = sc.nextLine();
                    System.out.print("Member ID: ");
                    String issueMemberId = sc.nextLine();
                    try {
                        library.issueBook(issueBookId, issueMemberId);
                    } catch (BookNotFoundException | BookAlreadyIssuedException | InvalidMemberException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Book ID to return: ");
                    String returnBookId = sc.nextLine();
                    try {
                        library.returnBook(returnBookId);
                    } catch (BookNotFoundException | BookNotIssuedException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.print("Enter title keyword: ");
                    String searchTitle = sc.nextLine();
                    List<Book> byTitle = library.searchByTitle(searchTitle);
                    if (byTitle.isEmpty()) System.out.println("No matches found.");
                    else byTitle.forEach(System.out::println);
                    break;

                case 6:
                    System.out.print("Enter author keyword: ");
                    String searchAuthor = sc.nextLine();
                    List<Book> byAuthor = library.searchByAuthor(searchAuthor);
                    if (byAuthor.isEmpty()) System.out.println("No matches found.");
                    else byAuthor.forEach(System.out::println);
                    break;

                case 7:
                    library.displayAllBooks();
                    break;

                case 8:
                    library.displayAvailableBooks();
                    break;

                case 9:
                    library.displayIssuedBooks();
                    break;

                case 10:
                    library.displayAllMembers();
                    break;

                case 0:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
            System.out.println();

        } while (choice != 0);

        sc.close();
    }

    private static void printMenu() {
        System.out.println("===== LIBRARY MANAGEMENT SYSTEM =====");
        System.out.println("1. Add Book");
        System.out.println("2. Add Member");
        System.out.println("3. Issue Book");
        System.out.println("4. Return Book");
        System.out.println("5. Search Book by Title");
        System.out.println("6. Search Book by Author");
        System.out.println("7. Display All Books");
        System.out.println("8. Display Available Books");
        System.out.println("9. Display Issued Books");
        System.out.println("10. Display All Members");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }
}