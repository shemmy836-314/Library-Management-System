import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Library {
    private static final int BORROW_DAYS = 14;

    private ArrayList<Book> bookList;
    private HashMap<String, Book> bookMap;
    private ArrayList<Member> memberList;
    private HashMap<String, Member> memberMap;

    public Library() {
        bookList = new ArrayList<>();
        bookMap = new HashMap<>();
        memberList = new ArrayList<>();
        memberMap = new HashMap<>();
    }

    public void addBook(Book book) {
        bookList.add(book);
        bookMap.put(book.getBookId(), book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void addMember(Member member) {
        memberList.add(member);
        memberMap.put(member.getMemberId(), member);
        System.out.println("Member added: " + member.getName());
    }

    public void issueBook(String bookId, String memberId)
            throws BookNotFoundException, BookAlreadyIssuedException, InvalidMemberException {

        Book book = bookMap.get(bookId);
        if (book == null) {
            throw new BookNotFoundException("No book found with ID: " + bookId);
        }
        if (!memberMap.containsKey(memberId)) {
            throw new InvalidMemberException("No member found with ID: " + memberId);
        }
        if (!book.isAvailable()) {
            throw new BookAlreadyIssuedException("Book \"" + book.getTitle() + "\" is already issued.");
        }

        LocalDate issueDate = LocalDate.now().minusDays(20);
        LocalDate dueDate = DateUtil.calculateDueDate(issueDate, BORROW_DAYS);

        book.setAvailable(false);
        book.setIssuedToMemberId(memberId);
        book.setIssueDate(issueDate);
        book.setDueDate(dueDate);

        System.out.println("Book \"" + book.getTitle() + "\" issued to member " + memberId
                + ". Due date: " + DateUtil.format(dueDate));
    }

    public double returnBook(String bookId) throws BookNotFoundException, BookNotIssuedException {
        Book book = bookMap.get(bookId);
        if (book == null) {
            throw new BookNotFoundException("No book found with ID: " + bookId);
        }
        if (book.isAvailable()) {
            throw new BookNotIssuedException("Book \"" + book.getTitle() + "\" was not issued.");
        }

        LocalDate returnDate = LocalDate.now();
        double fine = FineCalculator.calculateFine(book.getDueDate(), returnDate);

        System.out.println("Book \"" + book.getTitle() + "\" returned.");
        if (fine > 0) {
            System.out.println("Overdue! Fine: Rs. " + fine);
        } else {
            System.out.println("Returned on time. No fine.");
        }

        book.setAvailable(true);
        book.setIssuedToMemberId(null);
        book.setIssueDate(null);
        book.setDueDate(null);

        return fine;
    }

    public List<Book> searchByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book b : bookList) {
            if (b.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }

    public List<Book> searchByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book b : bookList) {
            if (b.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }

    public void displayAllBooks() {
        if (bookList.isEmpty()) {
            System.out.println("No books in library.");
            return;
        }
        for (Book b : bookList) {
            System.out.println(b);
        }
    }

    public void displayAvailableBooks() {
        boolean found = false;
        for (Book b : bookList) {
            if (b.isAvailable()) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) System.out.println("No books currently available.");
    }

    public void displayIssuedBooks() {
        boolean found = false;
        for (Book b : bookList) {
            if (!b.isAvailable()) {
                System.out.println("BookID: " + b.getBookId() + " | Title: " + b.getTitle()
                        + " | Issued To: " + b.getIssuedToMemberId()
                        + " | Issue Date: " + DateUtil.format(b.getIssueDate())
                        + " | Due Date: " + DateUtil.format(b.getDueDate()));
                found = true;
            }
        }
        if (!found) System.out.println("No books currently issued.");
    }

    public void displayAllMembers() {
        if (memberList.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }
        for (Member m : memberList) {
            System.out.println(m);
        }
    }
}