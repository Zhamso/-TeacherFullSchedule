import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Scanner;

public class Main {

    //Парсер расписания для преподавателей
    public static void main(String[] args) throws Exception {
        Helper helper = new Helper();
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите имя преподавателя");
        String teacherName = sc.nextLine();
        System.out.println("Имя " + teacherName);
        Document page = Jsoup.connect("http://www.bsu.ru/rasp/").get();
        Elements allTeachers = page.select("span.rasp_teacher.rasp_link");
        String teacherId = "";
        for(Element element : allTeachers) {
            if(element.text().equals(teacherName)) {
                teacherId = helper.GetTeacherID(element);
            }
        }
        if(teacherId.length() == 0) {
            System.out.println("Преподаватель не найден");
        } else {
            page = Jsoup.connect("http://www.bsu.ru/rasp/?t=" + teacherId).get();
            Elements allSubjects = page.select("table.rasp_drasp").first().select("tbody").select("tr");
            for(Element element : allSubjects) {
                if(element.select("td.rasp_subj").text().equals("")){
                    System.out.println();
                    System.out.println(element.text());
                } else {
                    String time = element.select("td.rasp_time").text();
                    String subject = element.select("td.rasp_subj").text();
                    String type = element.select("td.rasp_subj_type").text();
                    String auditory = element.select("td.rasp_aud").text();
                    String group = element.select("td").last().text();
                    String out = String.format("%5s %-20s%-3s%-5s%s", time, subject, type, auditory, group);
                    System.out.println(out);
                }
            }
        }
    }
}
