package com.revature.utility;

// Imports the Google Cloud client library
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;

import com.google.common.collect.Lists;
import io.javalin.http.UploadedFile;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
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

        // create random file name
        String fileName = fileNameGenerate(15);
        String objectName = "reimb_imgs/" + fileName + receiptFile.getExtension();

        // create bucket and upload the image file
        Bucket bucket = getBucket(bucketName);
        bucket.create(objectName, receiptFile.getContent(), receiptFile.getContentType());

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

    private static Bucket getBucket(String bucketName) {
        try{
            GoogleCredentials credentials = GoogleCredentials
                    .fromStream(new FileInputStream(System.getenv("GOOGLE_APPLICATION_CREDENTIALS")))
                    .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));

            Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
            Bucket bucket = storage.get(bucketName);

            if (bucket == null) {
                throw new IOException("Bucket not found: " + bucketName);
            }

            return bucket;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
