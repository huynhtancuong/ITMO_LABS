package itmo.Data;

import itmo.Exceptions.DuplicatePassportID;
import itmo.Utility.Console;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class Person implements CSV {
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    private Date birthday; //Поле не может быть null

    public void setHeight(long height) {
        this.height = height;
    }

    private long height; //Значение поля должно быть больше 0
    private String passportID; //Строка не может быть пустой. Значение этого поля должно быть уникальным. Длина строки должна быть не меньше 10, Поле не может быть null
    private static HashSet<String> passportIDHashSet = new HashSet<String>();

    public String getCSVString(String CSV_SEPARATOR) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return  formatter.format(birthday)+ CSV_SEPARATOR + Long.toString(height) + CSV_SEPARATOR + passportID;
    }

    public Person() {}
    public Person(Date birthday, long height, String passportID) throws DuplicatePassportID{
        this.birthday = birthday;
        this.height = height;
        setPassportID(passportID, true);
    }

    public Date getBirthday() {
        return birthday;
    }
    public long getHeight() {
        return height;
    }
    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID, boolean bypassDuplicatedPassportID) throws DuplicatePassportID {
        if (passportIDHashSet.contains(passportID) && bypassDuplicatedPassportID==false)
            throw new DuplicatePassportID("PassportID must be unique!");
        else {
            this.passportID = passportID;
            passportIDHashSet.add(passportID);
         }
    }

    @Override
    public String toString() {
        String info = "";
        info+= "\n-PassportID = " + passportID;
        info+= "\n-Height = " + height;
        info+= "\n-Birthday = " + birthday;
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

