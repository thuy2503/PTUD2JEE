public class Book {
    private int id;
    private String title;
    private String author;
    private double price;

    public Book() {}

    public Book(int id, String title, String author, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    // Input
    public void input(java.util.Scanner scanner) {
        System.out.print("Nhap ma sach: ");
        this.id = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhap ten sach: ");
        this.title = scanner.nextLine();
        System.out.print("Nhap tac gia: ");
        this.author = scanner.nextLine();
        System.out.print("Nhap don gia: ");
        this.price = Double.parseDouble(scanner.nextLine());
    }

    // Output (Text Block + formatted)
    public void output() {
        String msg = """
            BOOK:
            id = %d, title = %s, author = %s, price = %.2f
            """.formatted(id, title, author, price);
        System.out.println(msg);
    }
}
