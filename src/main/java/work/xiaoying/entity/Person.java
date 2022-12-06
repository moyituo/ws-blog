package work.xiaoying.entity;

import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.group.GroupSequenceProvider;
import work.xiaoying.validator.PersonGroupSequenceProvider;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@GroupSequenceProvider(PersonGroupSequenceProvider.class)
public class Person {

    @NotBlank
    private String name;


    @NonNull
    @Range(min = 10, max = 50)
    private Integer age;

    @NotNull
    @Size(min = 1, max = 3, groups = WhenAgeBetween10and20Group.class)
    @Size(min = 4, max = 6, groups = WhenAgeGt30Group.class)
    private List<String> website;

    public interface WhenAgeBetween10and20Group {
    }

    public interface WhenAgeGt30Group {
    }
}
