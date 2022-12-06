package work.xiaoying.validator;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;
import work.xiaoying.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonGroupSequenceProvider implements DefaultGroupSequenceProvider<Person> {
    @Override
    public List<Class<?>> getValidationGroups(Person person) {
        List<Class<?>> defaultGroupSequence=new ArrayList<>();
        defaultGroupSequence.add(Person.class);
        if (person!=null){
            int age = person.getAge();
            if (age>10&&age<20){
                defaultGroupSequence.add(Person.WhenAgeBetween10and20Group.class);
            }else if(age>=30){
                defaultGroupSequence.add(Person.WhenAgeGt30Group.class);
            }
        }
        return defaultGroupSequence;
    }
}
