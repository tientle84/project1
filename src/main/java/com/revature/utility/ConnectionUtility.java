package com.revature.utility;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.collect.Lists;
import org.postgresql.Driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {
    private ConnectionUtility() {}

    public static Connection getConnection() throws SQLException {
        // Register the Postgres driver with JDBC
        DriverManager.registerDriver(new Driver());

        // Specify credentials for the database
        String url = System.getenv("db1_url");
        String username = System.getenv("db_username");
        String password = System.getenv("db_password");

        Connection connection = DriverManager.getConnection(url, username, password);

        return connection;
    }

//    public static void authExplicit(String jsonPath) throws IOException {
//        // You can specify a credential file by providing a path to GoogleCredentials.
//        // Otherwise credentials are read from the GOOGLE_APPLICATION_CREDENTIALS environment variable.
//        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(jsonPath))
//                .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
//        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
//
////        System.out.println("Buckets:");
////        Page<Bucket> buckets = storage.list();
////        for (Bucket bucket : buckets.iterateAll()) {
////            System.out.println(bucket.toString());
////        }
//    }
}
