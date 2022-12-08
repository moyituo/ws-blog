package work.xiaoying.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Dog extends Animal{
    private int weight;
    private String color;
    public Dog(int id,String name,int weight,String color){
        super(id,name);
        this.weight=weight;
        this.color=color;
    }
}
