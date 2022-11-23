package work.xiaoying.validator;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence(
        {
                InsertSequence.Level1.class, InsertSequence.Level2.class,
                InsertSequence.Level3.class, Default.class
        })
public interface InsertSequence {
    interface Level1 {
    }

    interface Level2 {
    }

    interface Level3 {
    }
}
