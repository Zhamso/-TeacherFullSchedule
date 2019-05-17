import org.jsoup.nodes.Element;

public class Helper {
    public String GetTeacherID(Element element) {
        String string = element.toString();
        string = string.replace("<span class=\"rasp_teacher rasp_link\"><a href=\"?t=", "");
        String result = "";
        for(int i = 0; i < string.length(); i++) {
            if(Character.isDigit(string.charAt(i))) {
                result += string.charAt(i);
            } else {
                break;
            }
        }
        return result;
    }
}
