package work.xiaoying.test;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import lombok.SneakyThrows;

import java.io.File;

public class TestMetaDataExtractor {
    @SneakyThrows
    public static void main(String[] args) {
        Metadata metadata = ImageMetadataReader.readMetadata(new File("E:\\Desktop\\meta.jpg"));
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                //格式化输出[directory.getName()] - tag.getTagName() = tag.getDescription()
                System.out.format("[%s] - %s = %s\n",
                        directory.getName(), tag.getTagName(), tag.getDescription());
            }
            if (directory.hasErrors()) {
                for (String error : directory.getErrors()) {
                    System.err.format("ERROR: %s", error);
                }
            }
        }
    }
}
