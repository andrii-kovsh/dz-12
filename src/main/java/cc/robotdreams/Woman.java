package cc.robotdreams;

public class Woman extends Person {

    public Woman(String firstName, String lastName, int age) {
        super(firstName, lastName, age);
    }


    @Override
    public void deregisterPartnership(boolean returnToPreviousLastName) {
        super.deregisterPartnership(returnToPreviousLastName);
        if (returnToPreviousLastName) {
            setLastName(getPreviousLastName());
        }
    }

    @Override
    public boolean isRetired() {
        return getAge() >= 60;
    }

    @Override
    public String toString() {
        String partnerName = (getPartner() != null) ? getPartner().getFirstName() + " " + getPartner().getLastName() : "немає партнера";
        return "Жінка: " + getFirstName() + " " + getLastName() + ", вік: " + getAge() + ", партнер: " + partnerName;
    }
}