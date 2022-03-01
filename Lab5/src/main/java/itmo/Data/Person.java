package itmo.Data;

import itmo.Exceptions.DuplicatePassportID;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;

public class Person {
    private Date birthday; //Поле не может быть null
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
        setPassportID(passportID);
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) throws DuplicatePassportID {
        if (passportIDHashSet.contains(passportID))
            throw new DuplicatePassportID("PassportID must be unique!");
        else {
            this.passportID = passportID;
            passportIDHashSet.add(passportID);
         }
    }
}

