package work.xiaoying.netty;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * 测试字节缓冲区
 *
 * @author 小樱
 * @date 2023/01/09
 */
@Slf4j
public class TestByteBuffer {
    public static void main(String[] args) {
        Charset charset = Charset.forName("UTF-8");
        CharsetDecoder decoder = charset.newDecoder();
        try(FileChannel channel=new FileInputStream("README.md").getChannel()) {
            ByteBuffer buffer=ByteBuffer.allocate(1024);
            while (true){
                int len=channel.read(buffer);
                log.info("读取到的字节数 {}",len);
                if (len==-1){
                    break;
                }
                //切换到读模式
                buffer.flip();
                while (buffer.hasRemaining()){
                    log.info("读取到的数据 {}",decoder.decode(buffer));
                }
                //切换为写模式(或者compact()方法)
                buffer.clear();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
