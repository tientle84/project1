package com.revature.utility;

// Imports the Google Cloud client library
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import io.javalin.http.UploadedFile;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.stream.IntStream;

public class UploadFile {
    public static String uploadFile(UploadedFile receiptFile) throws IOException {
        // The ID of your GCP project
        String projectId = "silent-bolt-343919";

        // The ID of your GCS bucket
        String bucketName = "project1-imgs";


        String fileName = fileNameGenerate(15);
        String objectName = "reimb_imgs/" + fileName + receiptFile.getExtension();
        String contentType = receiptFile.getContentType();
        InputStream inputStream = receiptFile.getContent();
        byte[] bytes = IOUtils.toByteArray(inputStream);

        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(contentType).build();
        storage.create(blobInfo, bytes);

        return "https://storage.googleapis.com/project1-imgs/" + objectName;
    }

    // Generate Random Alphanumeric String
    public static String fileNameGenerate(int fileNameSize) {
        SecureRandom secureRandom = new SecureRandom();
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'

        IntStream intStream = secureRandom.ints(leftLimit, rightLimit+1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(fileNameSize);
        return intStream.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }
}
