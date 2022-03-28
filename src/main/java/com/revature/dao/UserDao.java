package com.revature.dao;

import com.revature.model.User;
import com.revature.utility.ConnectionUtility;
import org.postgresql.util.PSQLException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.*;

public class UserDao {
    private byte[] salt = "2ZlQT09FZHRkhVVWVVRakVVWVVR2ZlQT09FZHRFZHRkhVVWVVRZi9V".getBytes();
    public UserDao() {}

    public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
        try(Connection con = ConnectionUtility.getConnection()) {
//            String sql = "SELECT U.ers_users_id, U.ers_username, U.ers_password, U.user_role_id " +
//                    "FROM ers_users as U WHERE U.ers_username = ? AND U.ers_password = ?";

//            SecureRandom secureRandom = new SecureRandom();
//            byte[] salt = new byte[16];
//            secureRandom.nextBytes(salt);

            KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = secretKeyFactory.generateSecret(keySpec).getEncoded();
            String passwordHash = new String(hash, StandardCharsets.UTF_8);

            String sql = "SELECT * FROM ers_users as U WHERE U.ers_username = ? AND U.ers_password = ?";

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, passwordHash);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int id = resultSet.getInt("ers_users_id");
                String un = resultSet.getString("ers_username");
                //String pw = resultSet.getString("ers_password");
                String fn = resultSet.getString("user_first_name");
                String ln = resultSet.getString("user_last_name");
                String em = resultSet.getString("user_email");
                int roleId = resultSet.getInt("user_role_id");

                return new User(id, un, "", fn, ln, em, roleId);
            }

            return null;
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (InvalidKeySpecException e) {
            return null;
        }
    }

    public User createUser(User user) throws SQLException {
        try(Connection con = ConnectionUtility.getConnection()) {
//            SecureRandom secureRandom = new SecureRandom();
//            byte[] salt = new byte[16];
//            secureRandom.nextBytes(salt);

            KeySpec keySpec = new PBEKeySpec(user.getPassword().toCharArray(), salt, 65536, 128);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = secretKeyFactory.generateSecret(keySpec).getEncoded();
            String passwordHash = new String(hash, StandardCharsets.UTF_8);

            String sql = "INSERT INTO ers_users " +
                    "(ers_username, ers_password, user_first_name, user_last_name, user_email) " +
                    "VALUES (?, ?, ? , ?, ?)";


            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, passwordHash);
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getEmail());


            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int generatedId = resultSet.getInt(1);

            return new User(generatedId, user.getUsername(), "", user.getFirstName(), user.getLastName(), user.getEmail(), 2);
        } catch (PSQLException e) {
            return null;
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (InvalidKeySpecException e) {
            return null;
        }
    }

    public User getUserById(int userId) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            String sql = "SELECT * FROM ers_users WHERE ers_users_id = ?";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                int id = resultSet.getInt("ers_users_id");
                String un = resultSet.getString("ers_username");
                String pw = resultSet.getString("ers_password");
                String fn = resultSet.getString("user_first_name");
                String ln = resultSet.getString("user_last_name");
                String em = resultSet.getString("user_email");
                int roleId = resultSet.getInt("user_role_id");

                return new User(id, un, pw, fn, ln, em, roleId);
            }

            return  null;
        }
    }


}
