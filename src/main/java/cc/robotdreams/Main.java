package cc.robotdreams;

public class Main {

public static void main(String[] args) {
        Person man1 = new Man("Василь", "Чірт", 30);
        Person woman1 = new Woman("Іра", "Дика", 25);
        Person man2 = new Man("Петро", "Попадайло", 40);
        Person woman2 = new Woman("Алла", "Бараболя", 35);
        Person man3 = new Man("Макс", "Сила", 68);

        // Реєстрація шлюбу
        woman1.registerPartnership(man1);
        woman2.registerPartnership(man2);

        // Вивід деталей про осіб: у шлюбі, без шлюбу, після розриву
        System.out.println("Особи зареєстровані у шлюбах:");
        printPersonDetails(man1);
        printPersonDetails(woman1);
        printPersonDetails(man2);
        printPersonDetails(woman2);

        System.out.println("Особи без шлюбу:");
        printPersonDetails(man3);

        // Розірвати попередньо зареєстрований шлюб
        woman1.deregisterPartnership(true);

        System.out.println("Особи після розриву шлюбу:");
        printPersonDetails(woman1);
        printPersonDetails(man1);
    }

    public static void printPersonDetails(Person person) {
        String gender = (person instanceof Man) ? "Чоловік" : "Жінка";
        System.out.println(gender + ":");
        System.out.println("Ім'я: " + person.getFirstName());
        System.out.println("Прізвище: " + person.getLastName());
        System.out.println("Вік: " + person.getAge());
        System.out.println("Партнер: " + getPartnerName(person));
        System.out.println("Чи на пенсії: " + (person.isRetired() ? "так" : "ні"));
        System.out.println();
    }

    public static String getPartnerName(Person person) {
        if (person.getPartner() != null) {
            return person.getPartner().getFirstName() + " " + person.getPartner().getLastName();
        } else {
            return "немає партнера";
        }
    }
}
