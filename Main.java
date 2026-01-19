import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Book> listBook = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Menu dạng Text Block
        String menu = """
            === Chuong trinh quan ly sach ===
            1. Them 1 cuon sach
            2. Xoa 1 cuon sach
            3. Thay doi thong tin sach
            4. Xuat thong tin tat ca cac cuon sach
            5. Tim sach co tu "Lap trinh" (khong phan biet hoa/thuong)
            6. Lay toi da K cuon sach co gia <= P
            7. Tim sach theo danh sach tac gia
            0. Thoat
            Chon chuc nang: 
            """;

        int choice;
        do {
            System.out.print(menu);
            // Dùng nextLine() để tránh lỗi input sau khi dùng nextInt()
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                choice = -1; // Lựa chọn không hợp lệ
            } else {
                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    choice = -1;
                }
            }

            switch (choice) {
                case 1 -> {
                    Book newBook = new Book();
                    newBook.input(scanner);
                    listBook.add(newBook);
                    System.out.println("✅ Da them sach!");
                }

                case 2 -> {
                    System.out.print("Nhap ma sach can xoa: ");
                    String idStr = scanner.nextLine().trim();
                    try {
                        int id = Integer.parseInt(idStr);
                        Book toRemove = listBook.stream()
                            .filter(b -> b.getId() == id)
                            .findFirst()
                            .orElse(null);
                        if (toRemove != null) {
                            listBook.remove(toRemove);
                            System.out.println("✅ Da xoa sach!");
                        } else {
                            System.out.println("❌ Khong tim thay sach co ma " + id);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Ma sach khong hop le!");
                    }
                }

                case 3 -> {
                    System.out.print("Nhap ma sach can cap nhat: ");
                    String idStr = scanner.nextLine().trim();
                    try {
                        int id = Integer.parseInt(idStr);
                        Book bookToUpdate = listBook.stream()
                            .filter(b -> b.getId() == id)
                            .findFirst()
                            .orElse(null);
                        if (bookToUpdate != null) {
                            System.out.println("Nhap thong tin moi:");
                            bookToUpdate.input(scanner); // Ghi đè thông tin cũ
                            System.out.println("✅ Da cap nhat sach!");
                        } else {
                            System.out.println("❌ Khong tim thay sach!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Ma sach khong hop le!");
                    }
                }

                case 4 -> {
                    if (listBook.isEmpty()) {
                        System.out.println("📋 Danh sach sach rong.");
                    } else {
                        System.out.println("\n=== DANH SACH TAT CA SACH ===");
                        listBook.forEach(Book::output); // Method Reference
                    }
                }

                case 5 -> {
                    List<Book> result = listBook.stream()
                        .filter(b -> b.getTitle().toLowerCase().contains("lap trinh"))
                        .collect(Collectors.toList());
                    if (result.isEmpty()) {
                        System.out.println("🔍 Khong co sach nao chua tu 'Lap trinh'.");
                    } else {
                        System.out.println("\n=== SACH CHUA TU 'LAP TRINH' ===");
                        result.forEach(Book::output);
                    }
                }

                case 6 -> {
                    System.out.print("Nhap so luong toi da K: ");
                    String kStr = scanner.nextLine().trim();
                    System.out.print("Nhap gia toi da P: ");
                    String pStr = scanner.nextLine().trim();

                    try {
                        int k = Integer.parseInt(kStr);
                        double p = Double.parseDouble(pStr);

                        if (k <= 0 || p < 0) {
                            System.out.println("❌ K phai > 0 va P phai >= 0!");
                            break;
                        }

                        List<Book> filtered = listBook.stream()
                            .filter(b -> b.getPrice() <= p)
                            .limit(k)
                            .collect(Collectors.toList());

                        if (filtered.isEmpty()) {
                            System.out.println("🔍 Khong co sach nao thoa man dieu kien.");
                        } else {
                            System.out.println("\n=== TOI DA " + k + " SACH CO GIA <= " + p + " ===");
                            filtered.forEach(Book::output);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Dau vao K hoac P khong hop le!");
                    }
                }

                case 7 -> {
                    System.out.print("Nhap danh sach tac gia (ngan cach bang dau ','): ");
                    String authorsInput = scanner.nextLine().trim();
                    if (authorsInput.isEmpty()) {
                        System.out.println("❌ Danh sach tac gia rong!");
                        break;
                    }

                    Set<String> authorSet = Arrays.stream(authorsInput.split(","))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toSet());

                    List<Book> matchedBooks = listBook.stream()
                        .filter(b -> authorSet.contains(b.getAuthor()))
                        .collect(Collectors.toList());

                    if (matchedBooks.isEmpty()) {
                        System.out.println("🔍 Khong co sach nao cua cac tac gia da nhap.");
                    } else {
                        System.out.println("\n=== SACH CUA CAC TAC GIA: " + authorSet + " ===");
                        matchedBooks.forEach(Book::output);
                    }
                }

                case 0 -> {
                    System.out.println("👋 Tam biet! Hen gap lai.");
                }

                default -> {
                    if (choice != -1) {
                        System.out.println("⚠️ Lua chon khong hop le. Vui long chon tu 0-7.");
                    }
                }
            }

            // Ngăn cách giữa các lần lặp
            if (choice != 0) {
                System.out.println("\n" + "=".repeat(50) + "\n");
            }

        } while (choice != 0);

        scanner.close();
    }
}