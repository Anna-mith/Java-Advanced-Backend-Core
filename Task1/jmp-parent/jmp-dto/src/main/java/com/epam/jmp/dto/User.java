package com.epam.jmp.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private String name;
    private String surname;
    private LocalDate birthday;

    public User(String name, String surname, LocalDate birthday) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public static List<User> getMockUsers() {
        var users = new ArrayList<User>();
        var user1 = new User("Jane", "Doe", LocalDate.of(1988, 1, 12));
        var user2 = new User("Lucas", "George", LocalDate.of(1944, 5, 14));
        var user3 = new User("John", "Doe", LocalDate.of(2014, 5, 14));
        var user4 = new User("Mellody", "Hobson", LocalDate.of(1969, 4, 3));
        var user5 = new User("Lily", "Woodstone", LocalDate.of(2000, 10, 28));
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        return users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, birthday);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
