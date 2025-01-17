import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class Notes {
    private String title;
    private String content;

    @Override
    public String toString() {
        return "Название: '" + title + '\'' +
               "\n   Содержимое: '" + content + '\'';
    }
}
