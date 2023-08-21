package cc.robotdreams;

import org.testng.*;
import org.testng.annotations.*;

public class PersonTest {

    @DataProvider(name = "personData")
    public static Object[][] personData() {

        Person woman1 = new Woman("Іра", "Дика", 59);
        Person woman2 = new Woman("Тетяна", "Мельник", 61);
        Person woman3 = new Woman("Ольга", "Лисенко", 60);

        Person man1 = new Man("Василь", "Чірт", 64);
        Person man2 = new Man("Володимир", "Сидоренко", 66);
        Person man3 = new Man("Петро", "Коваль", 65);

        return new Object[][] {
                { woman1, "Іра", "Дика", 59, false },
                { woman2, "Тетяна", "Мельник", 61, true },
                { woman3, "Ольга", "Лисенко", 60, true },
                { man1, "Василь", "Чірт", 64, false },
                { man2, "Володимир", "Сидоренко", 66, true },
                { man3, "Петро", "Коваль", 65, true },
        };
    }

    @Test(dataProvider = "personData")
    public void testPersonProperties(Person person, String firstName, String lastName, int age, boolean isRetired) {
        Assert.assertEquals(person.getFirstName(), firstName, "Неправильне ім'я");
        Assert.assertEquals(person.getLastName(), lastName, "Неправильне прізвище");
        Assert.assertEquals(person.getAge(), age, "Неправильний вік");

        if (person instanceof Man) {
            Assert.assertTrue(person.isRetired() == isRetired && person instanceof Man, "Неправильний статус пенсіонера або тип об'єкта");
        } else {
            Assert.assertTrue(person.isRetired() == isRetired && person instanceof Woman, "Неправильний статус пенсіонера або тип об'єкта");
        }

        // Тест сетерів та гетерів
        person.setFirstName("Нове Ім'я");
        person.setLastName("Нове Прізвище");
        person.setAge(50);

        Assert.assertEquals(person.getFirstName(), "Нове Ім'я", "Неправильно встановлено нове ім'я");
        Assert.assertEquals(person.getLastName(), "Нове Прізвище", "Неправильно встановлено нове прізвище");
        Assert.assertEquals(person.getAge(), 50, "Неправильно встановлений новий вік");
    }

    @Test(dataProvider = "personData")
    public void testPartnership(Person person, String firstName, String lastName, int age, boolean isRetired) {
        Person partner;
        if (person instanceof Man) {
            partner = new Woman("Марія", "Петрівна", 28);
        } else {
            partner = new Man("Іван", "Сірко", 40);
        }

        person.registerPartnership(partner);

        if (person instanceof Man) {
            Assert.assertEquals(((Man) person).getPartner(), partner, "Партнерство чоловіка не встановлено");
            Assert.assertEquals(((Woman) partner).getPartner(), person, "Партнерство жінки не встановлено");
        } else {
            Assert.assertEquals(((Woman) person).getPartner(), partner, "Партнерство жінки не встановлено");
            Assert.assertEquals(((Man) partner).getPartner(), person, "Партнерство чоловіка не встановлено");
        }
    }

    @Test(dataProvider = "personData")
    public void testBreakupPartnership(Person person, String firstName, String lastName, int age, boolean isRetired) {
        Person partner;
        if (person instanceof Man) {
            partner = new Woman("Марія", "Петрівна", 28);
        } else {
            partner = new Man("Іван", "Сірко", 40);
        }

        person.registerPartnership(partner);
        person.deregisterPartnership(true);

        Assert.assertNull(person.getPartner(), "Партнерство не розірвано для першої особи");
        Assert.assertNull(partner.getPartner(), "Партнерство не розірвано для другої особи");

        if (!(person instanceof Man)) {
            Assert.assertEquals(person.getLastName(), lastName, "Прізвище жінки не відновлено після розриву партнерства");
        }
    }
}