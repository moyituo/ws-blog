package work.xiaoying.validator;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence(
        {
                UpdateSequence.Level1.class, UpdateSequence.Level2.class,
                UpdateSequence.Level3.class, Default.class
        })
public interface UpdateSequence {
    interface Level1 {
    }

    interface Level2 {
    }

    interface Level3 {
    }
}
