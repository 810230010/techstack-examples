package basic;

import java.util.HashSet;
import java.util.Set;

public class User {
    private int id;
    private int age;
    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(int id, int age, String username) {
        this.id = id;
        this.age = age;
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        return age == user.age;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + age;
        return result;
    }

    public static void main(String[] args) {
        Set<User> set = new HashSet<>();
        User user1 = new User(1, 12, "jjp");
        set.add(user1);
        User user2 = new User(1, 12, "aaa");
        System.out.println(set.contains(user2));
    }
}
