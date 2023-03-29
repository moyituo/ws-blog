//package work.xiaoying.controller;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.*;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.locks.ReentrantLock;
//
//@RestController
//@RequestMapping("/api/upload")
//public class UploadController {
//
//  private static final String UPLOAD_FOLDER = "path/to/save/upload/";
//  private static final Integer SPACE_ID=1;
//
//  private static final int CHUNK_SIZE = 16 * 1024 * 1024; // 切片大小，这里为16MB
//
//  private final Map<String, ReentrantLock> chunkLocks = new ConcurrentHashMap<>();
//
//  @PostMapping("/checkFile")
//  public ResponseEntity<?> checkFile(@RequestParam("name") String name) {
//    String uploadPath = UPLOAD_FOLDER + name;
//    File uploadFile = new File(uploadPath);
//
//    if (uploadFile.exists()) {
//      return ResponseEntity.ok().build();
//    } else {
//      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//    }
//  }
//
//  @PostMapping("/checkChunk")
//  public ResponseEntity<?> checkChunk(@RequestParam("name") String name,
//                                      @RequestParam("chunkIndex") int chunkIndex,
//                                      @RequestParam("chunkSize") int chunkSize,
//                                      @RequestParam("currentChunkSize") int currentChunkSize,
//                                      @RequestParam("type") String type) {
//    String chunkKey = name + "-" + chunkIndex;
//    ReentrantLock lock = chunkLocks.computeIfAbsent(chunkKey, k -> new ReentrantLock());
//
//    lock.lock();
//    try {
//      String uploadPath = UPLOAD_FOLDER + name;
//      File uploadFile = new File(uploadPath);
//
//      // 如果文件已存在并且切片已上传，则返回已存在的响应
//      if (uploadFile.exists() && uploadFile.length() >= currentChunkSize) {
//        return ResponseEntity.status(HttpStatus.OK).build();
//      } else {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//      }
//    } finally {
//      lock.unlock();
//    }
//  }
//
//  @PostMapping
//  public ResponseEntity<?> upload(@RequestParam("name") String name,
//                                  @RequestParam("chunkIndex") int chunkIndex,
//                                  @RequestParam("chunkSize") int chunkSize,
//                                  @RequestParam("currentChunkSize") int currentChunkSize,
//                                  @RequestParam("type") String type,
//                                  @RequestParam("totalSize") long totalSize,
//                                  @RequestParam("totalChunks") int totalChunks,
//                                  @RequestBody byte[] data) throws IOException {
//    String uploadPath = UPLOAD_FOLDER + name;
//    File uploadFile = new File(uploadPath);
//
//// 获取锁
//    String chunkKey = name + "-" + chunkIndex;
//    ReentrantLock lock = chunkLocks.computeIfAbsent(chunkKey, k -> new ReentrantLock());
//    lock.lock();
//    try {
//      // 再次检查是否已存在
//      if (uploadFile.exists() && uploadFile.length() >= currentChunkSize) {
//        return ResponseEntity.status(HttpStatus.OK).build();
//      }
//
//      // 写入切片
//      RandomAccessFile raf = new RandomAccessFile(uploadFile, "rw");
//      raf.seek((long) chunkIndex * (long) CHUNK_SIZE);
//      raf.write(data);
//      raf.close();
//
//      // 检查文件是否上传完整
//      if (isUploadComplete(name,totalChunks)) {
//        mergeChunks(name, totalChunks,totalSize);
//        clearChunks(name,totalChunks);
//      }
//
//      return ResponseEntity.status(HttpStatus.OK).build();
//    } finally {
//      // 释放锁
//      lock.unlock();
//    }
//
//  }
//
//  private boolean isUploadComplete(String fileName, int totalChunks) {
//    boolean isComplete = true;
//    for (int i = 0; i < totalChunks; i++) {
//      File chunkFile = new File(UPLOAD_FOLDER, fileName + "-" + i);
//      if (!chunkFile.exists()) {
//        isComplete = false;
//        break;
//      }
//    }
//    return isComplete;
//  }
//
//
//
//  private void mergeChunks(String fileName, int totalChunks, long totalSize) throws IOException {
//    File outputFile = new File("path/to/save/", fileName);
//    int fileSize=0;
//    int chunkSize = 1024 * 1024 * 1024; // 1G chunk size
//    try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
//      for (int i = 0; i < totalChunks; i++) {
//        File chunkFile = new File(UPLOAD_FOLDER, fileName + "-" + i);
//        try (FileInputStream inputStream = new FileInputStream(chunkFile)) {
//          byte[] buffer = new byte[1024];
//          int bytesRead;
//          while ((bytesRead = inputStream.read(buffer)) != -1) {
//            outputStream.write(buffer, 0, bytesRead);
//            fileSize += bytesRead;
//            // 如果目标文件大小超过1G，则关闭目标文件并创建一个新文件
//            if (fileSize >= chunkSize) {
//              outputStream.close();
//              fileSize = 0;
//              targetFileName = String.format("file_%d.chunk", ++fileCount);
//              outputStream = new FileOutputStream(new File(targetDirectory, targetFileName));
//            }
//          }
//        }
//        chunkFile.delete();
//      }
//    }
//    if (outputFile.length() != totalSize) {
//      throw new IOException("合并的文件大小不正确");
//    }
//  }
//
//  private void clearChunks(String fileName, int totalChunks) {
//    for (int i = 0; i < totalChunks; i++) {
//      File chunkFile = new File(UPLOAD_FOLDER, fileName + "-" + i);
//      chunkFile.delete();
//    }
//  }
//
//
//}
//
