import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

public class Grades {

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private String gradeToString(String grade) {
        switch (grade) {
            case "5": {
                return "Безупречно";
            }
            case "4": {
                return "Потрясающе";
            }
            case "3": {
                return "Восхитительно";
            }
            case "2": {
                return "Прекрасно";
            }
            default:
                return "Очаровательно";
        }
    }

    // grades - строка вида "имя,фамилия,предмет,оценка;имя,фамилия,предмет,оценка;"
    public String serializeGrades(String[] grades) {
        List<String> resultList = new ArrayList<>();
        for (String grade : grades) {
            String[] elems = grade.split(" ");
            String serializedGrade = String.join(",", elems[0].toLowerCase(), elems[1].toLowerCase(), elems[2], gradeStringToInt(elems[4]));
            resultList.add(serializedGrade);
        }

        return String.join(";", resultList);
    }