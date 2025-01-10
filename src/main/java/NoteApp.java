import java.util.ArrayList;
import java.util.Scanner;

public class NoteApp {
    private ArrayList<Notes> notes;
    private Scanner sc;

    public NoteApp() {
        notes = new ArrayList<>();
        sc = new Scanner(System.in);
    }

    // Метод для добавления заметки
    public void addNote() {
        System.out.println("Введите название заметки: ");
        String title = sc.nextLine();
        System.out.println("Введите содержание заметки:");
        String content = sc.nextLine();

        notes.add(new Notes(title, content));
        System.out.println("Заметка добавлена успешно!");
    }

    // Метод для редактирования заметки
    public void editNote() {
        System.out.println("Введите индекс заметки для редактирования (от 0 до " + (notes.size() - 1) + "): ");
        int index = Integer.parseInt(sc.nextLine());

        if (index >= 0 && index < notes.size()) {
            Notes note = notes.get(index);
            System.out.println("Редактирование заметки: ");
            System.out.println(note);

            System.out.println("Введите новое название (оставьте пустым, чтобы сохранить актуальность): ");
            String newTitle = sc.nextLine();
            if (!newTitle.isEmpty()) {
                note.setTitle(newTitle);
            }

            System.out.println("Введите новый контент (оставьте поле пустым, чтобы поддерживать актуальность): ");
            String newContent = sc.nextLine();
            if (!newContent.isEmpty()) {
                note.setContent(newContent);
            }

            System.out.println("Заметка успешно обновлена!");
        } else {
            System.out.println("Неверный индекс заметки.");
        }
    }

    // Метод для удаления заметки
    public void deleteNote() {
        System.out.println("Введите индекс заметки, которую нужно удалить (от 0 до " + (notes.size() - 1) + ") : ");
        int index = Integer.parseInt(sc.nextLine());

        if (index >= 0 && index < notes.size()) {
            notes.remove(index);
            System.out.println("Заметка успешно удалена!");
        } else {
            System.out.println("Неверный индекс заметки.");
        }
    }

    // Метод для отображения всех заметок
    public void displayNotes() {
        if (notes.isEmpty()) {
            System.out.println("Нет доступных заметок.");
        } else {
            System.out.println("Все заметки: ");
            for (int i = 0; i < notes.size(); i++) {
                System.out.println(i + ". " + notes.get(i));
                System.out.println();
            }
        }
    }

    // Главное меню
    public void showMenu() {
        while (true) {
            System.out.println("1. Добавить заметку");
            System.out.println("2. Редактировать заметку");
            System.out.println("3. Удалить заметку");
            System.out.println("4. Все заметки");
            System.out.println("5. Выход");
            System.out.print("Выберите вариант: ");
            int option = Integer.parseInt(sc.nextLine());

            switch (option) {
                case 1:
                    addNote();
                    break;
                case 2:
                    editNote();
                    break;
                case 3:
                    deleteNote();
                    break;
                case 4:
                    displayNotes();
                    break;
                case 5:
                    System.out.println("Выхожу...");
                    return;
                default:
                    System.out.println("Неверный вариант, пожалуйста, попробуйте еще раз.");
            }
        }
    }

    public static void main(String[] args) {
        NoteApp app = new NoteApp();
        app.showMenu();
    }
}
