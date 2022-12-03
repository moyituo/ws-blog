package work.xiaoying.utils.file;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

@Service
@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface UploadMode {
    UploadModeEnum mode();
}
