package kz.argynsagash.zipreaderexample.json.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Person {
    private String name;
    private Float price;
    private Boolean available;
    private Float version;
    private List<String> functions;

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", available=" + available +
                ", version=" + version +
                ", functions=" + functions +
                '}';
    }
}
