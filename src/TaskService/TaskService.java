package TaskService;

import Task.*;
import TypeOfTask.Type;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static javax.swing.UIManager.get;

public class TaskService {
    private static Map<Integer, Task> taskMap = new LinkedHashMap<Integer, Task>();
    private static List<Task> removedTasks;

    public static Map<Integer, Task> getTaskMap() {
        return taskMap;
    }

    public static void printRemovedTasks() {
        System.out.println(removedTasks);
    }
    public static Task createTask(String title, Type type, LocalDate date, LocalTime time, String description, int repeatType) {
        switch (repeatType) {
            case 1:
                return new DailyTask(title,type,date, time, description);
            case 2:
                return new WeeklyTask(title,type,date, time, description);
            case 3:
                return new MonthlyTask(title,type,date, time, description);
            case 4:
                return new YearlyTask(title,type,date, time, description);
            default:
                return new OneTimeTask(title,type,date, time, description);
        }
    }

    public static void add(Task task){
        taskMap.put(task.getId(),task);
    }
    public static Task getTask(int id) throws IncorrectArgumentException {
        if (taskMap.containsKey(id)) {
            return taskMap.get(id);
        } else {
            throw new IncorrectArgumentException("Событие в эту дату отсутствует");
        }
    }
    public static void removeTask(int id) throws TaskNotFoundException{
        if (taskMap.containsKey(id)) {
               removedTasks.add(taskMap.remove(id));
            } else {
                throw new TaskNotFoundException();
            }
        }
    public static void getAllByDate(LocalDate ld) {
        taskMap.forEach((a,b)-> System.out.print((Objects.equals(b.getDate(), ld)?b:"")));
    }

}
