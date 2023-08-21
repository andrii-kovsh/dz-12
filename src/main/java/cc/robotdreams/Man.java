package cc.robotdreams;

public class Man extends Person {

    public Man(String firstName, String lastName, int age) {
        super(firstName, lastName, age);
    }

    @Override
    public boolean isRetired() {
        return getAge() >= 65;
    }
    public String toString() {
        String partnerName = (getPartner() != null) ? getPartner().getFirstName() + " " + getPartner().getLastName() : "немає партнера";
        return "Чоловік: " + getFirstName() + " " + getLastName() + ", вік: " + getAge() + ", партнер: " + partnerName;
    }
}