import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class NoteApp {
    private ArrayList<Notes> notes;
    private Scanner sc;
    private static final String FILE_NAME = "myFile.txt";

    public NoteApp() {
        notes = new ArrayList<>();
        sc = new Scanner(System.in);
        loadNotes();
    }

    // Метод для добавления заметки
    public void addNote() {
        System.out.println("Введите название заметки: ");
        String title = sc.nextLine();
        System.out.println("Введите содержание заметки:");
        String content = sc.nextLine();

        notes.add(new Notes(title, content));
        System.out.println("Заметка добавлена успешно!");
        saveNotes();
    }

    // Метод для редактирования заметки
    public void editNote() {
        if (notes.isEmpty()) {
            System.out.println("Нет заметок, доступных для редактирования.");
            return;
        }


        int index = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Введите индекс заметки для редактирования (от 0 до " + (notes.size() - 1) + "): ");
            try {
                index = Integer.parseInt(sc.nextLine());

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
                    validInput = true;
                    saveNotes();
                } else {
                    System.out.println("Неверный индекс заметки.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод, введите целое число.");
            }
        }
    }

    // Метод для удаления заметки
    public void deleteNote() {
        if (notes.isEmpty()) {
            System.out.println("Нет заметок, доступных для удаления.");
            return;
        }

        int index = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Введите индекс заметки, которую нужно удалить (от 0 до " + (notes.size() - 1) + ") : ");
            try {
                index = Integer.parseInt(sc.nextLine());

                if (index >= 0 && index < notes.size()) {
                    notes.remove(index);
                    System.out.println("Заметка успешно удалена!");
                    saveNotes();
                    validInput = true;
                } else {
                    System.out.println("Неверный индекс заметки.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорреткный ввод, введите челое число.");
            }
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

    // Метод для сохранения заметок в файл
    public void saveNotes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Notes note : notes) {
                writer.write(note.getTitle() + "\n");
                writer.write(note.getContent() + "\n");
                writer.write("---\n");
            }
            System.out.println("Заметки сохранены в файл.");
        } catch (Exception e) {
            System.out.println("Заметки о сохранении ошибок: " + e.getMessage());
        }
    }

    // Метод для загрузки заметок из файла
    public void loadNotes() {
        try (Scanner fileScanner = new Scanner(new java.io.File(FILE_NAME))) {
            while (fileScanner.hasNextLine()) {
                String title = fileScanner.nextLine();
                String content = fileScanner.nextLine();
                fileScanner.nextLine(); // Пропускаем разделитель "---"

                notes.add(new Notes(title, content));
            }
        } catch (IOException e) {
            System.out.println("Примечания по загрузке ошибок: " + e.getMessage());
        }
    }

    // Главное меню
    public void showMenu() {
        while (true) {
            System.out.println("1. Добавить заметку");
            System.out.println("2. Редактировать заметку");
            System.out.println("3. Удалить заметку");
            System.out.println("4. Просмотр всех заметок");
            System.out.println("5. Сохранить и выйти");
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
                    saveNotes();
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
