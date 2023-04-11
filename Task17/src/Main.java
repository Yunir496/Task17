import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
public class Main {
    private static ArrayList<String> todoList = new ArrayList<>();
    public static void main(String[] args) {
        while (true){
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine();
            if (text.toLowerCase().startsWith("list")) {  //Вывод списка дел
                System.out.println(list());
            }
            if (text.toLowerCase().startsWith("add")) {   //Добавление в список дел под индексом N,либо в конец
                add(text);
            }
            if (text.toLowerCase().startsWith("edit")) {   //Замена дела под индексом N,если индекс за пределами ничего не делать
                edit(text);
            }
            if (text.toLowerCase().startsWith("delete")) {   //Удаление дела под индексом N, если индекс за пределами ничего не делать
                delete(text);
            }
            if (text.toLowerCase().startsWith("exit")){    //Выход из программы
                break;
            }
        }}
    public static void add(String todo){//Добавление в список дел под индексом N,либо в конец
        String[] split = todo.split("\\s", 2);
        if (split.length==2 && split[1].matches("\\d+.+")) {
            int index = Integer.parseInt(split[1].replaceAll("\\D", ""));
            String todoData = split[1].substring(split[1].indexOf(" ")).trim();
            if (index < todoList.size()) {
                todoList.add(index, todoData);
                System.out.println("Дело № "+index+" "+todoData+" добавлено");
            } else {
                todoList.add(todoData);
                System.out.println("Дело "+todoData+ " добавлено в конец списка");
            }
        }
        else if(split.length==2){
            todoList.add(split[1]);
            System.out.println("Дело "+split[1]+ " добавлено");
        }else{
            System.out.println("Ошибка ввода");
        }
    }
    public static  void edit(String todo){//Замена дела под индексом N,если индекс за пределами ничего не делать
        String [] texts = todo.split(" ",3);
        int blockCount = todo.split(" ").length;
        if(texts[1].matches("\\d") && Integer.parseInt(texts[1])<todoList.size()&&blockCount>=4){
            todoList.remove(Integer.parseInt(texts[1]));
            todoList.add(Integer.parseInt(texts[1]),texts[2]);
            System.out.println("Дело № "+texts[1]+" изменено на "+texts[2]);
        }else{
            System.out.println("Не верно указаны номер дела или описание");;
        }}
    public static void delete(String todo){//Удаление дела под индексом N, если индекс за пределами ничего не делать
        String [] texts = todo.split(" ",2);
        if(texts[1].matches("\\d") && Integer.parseInt(texts[1])<todoList.size()){
            System.out.println("Дело № "+texts[1]+" удалено");
            todoList.remove(texts[1]);
        }else{
            System.out.println("Не верно указан номер дела");;
        }}
    public static String list() {
        return IntStream.range(0, todoList.size())
                .mapToObj(i -> i + " - " + todoList.get(i))
                .collect(Collectors.joining("\n"));
    }
}