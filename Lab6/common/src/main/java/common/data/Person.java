package common.data;

import common.exceptions.DuplicatePassportID;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

/**
 * Person is a class that represents a person
 */
public class Person implements CSV , Serializable {
    /**
     * Setter
     * @param birthday birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    private Date birthday; //Поле не может быть null

    /**
     * Setter
     * @param height height
     */
    public void setHeight(long height) {
        this.height = height;
    }

    private long height; //Значение поля должно быть больше 0
    private String passportID; //Строка не может быть пустой. Значение этого поля должно быть уникальным. Длина строки должна быть не меньше 10, Поле не может быть null
    private static HashSet<String> passportIDHashSet = new HashSet<String>();
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    // A method that returns a string that represents a person in a CSV format.
    public String getCSVString(String CSV_SEPARATOR) {

        return  formatter.format(birthday)+ CSV_SEPARATOR + Long.toString(height) + CSV_SEPARATOR + passportID;
    }

    /**
     * Constructor
     */
    public Person() {}

    /**
     * Constructor
     * @param birthday birthday
     * @param height height
     * @param passportID passportID
     * @throws DuplicatePassportID an exception
     */
    public Person(Date birthday, long height, String passportID) throws DuplicatePassportID {
        this.birthday = birthday;
        this.height = height;
        setPassportID(passportID, true);
    }

    /**
     * Getter
     * @return birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Getter
     * @return height
     */
    public long getHeight() {
        return height;
    }

    /**
     * Getter
     * @return passportID
     */
    public String getPassportID() {
        return passportID;
    }

    /**
     * @param passportID passportID
     * @param bypassDuplicatedPassportID condition to bypass exeption
     * @throws DuplicatePassportID an exception
     */
    // A method that sets the passportID of a person. If the passportID is already in the HashSet, it throws an exception.
    public void setPassportID(String passportID, boolean bypassDuplicatedPassportID) throws DuplicatePassportID {
        if (passportIDHashSet.contains(passportID) && bypassDuplicatedPassportID==false)
            throw new DuplicatePassportID("PassportID must be unique!");
        else {
            this.passportID = passportID;
            passportIDHashSet.add(passportID);
         }
    }

    @Override
    // Overriding the `toString()` method of the Object class.
    public String toString() {
        String info = "";
        info+= "\n-PassportID = " + passportID;
        info+= "\n-Height = " + height;
        info+= "\n-Birthday = " + formatter.format(birthday);
        return info;
    }

    @Override
    public int hashCode() {
        return passportID.hashCode() + (int) height + birthday.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Person) {
            Person personObj = (Person) obj;
            boolean birthdayCondition = false;
            if (birthday.compareTo(personObj.getBirthday()) == 0) {birthdayCondition = true;}
            boolean condition =  passportID.equals(personObj.getPassportID()) && (height == personObj.getHeight()) && birthdayCondition;
            return condition;
        }
        return false;
    }
}

