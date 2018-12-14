package com.netcracker.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserOperations {
    String[] loginAndPassword;
    private String formLogin;
    private String formPassword;
    private File userFile;
    private HashMap<String, String> users = new HashMap<>();
    public static HashMap<String, Integer> userAttempts = new HashMap<>();

    public UserOperations(String formLogin, String formPassword, String appPath) throws IOException {
        this.formLogin = formLogin;
        this.formPassword = formPassword;
        this.userFile = new File(appPath + "\\users.txt");
        if (!userFile.exists()) {
            userFile.createNewFile();
        }
        initUserList(userFile);
    }


    public boolean hasUser(){
        boolean isExist = false;
        try (Scanner fileScanner = new Scanner(userFile)) {
            while (fileScanner.hasNextLine()) {
                loginAndPassword = fileScanner.nextLine().trim().split(":");
                String existLogin = loginAndPassword[0];
                if (existLogin.equals(formLogin)) {
                    isExist = true;
                    break;
                }
                loginAndPassword = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isExist;
    }

    public boolean validPassword() {
        String password = loginAndPassword[1];
        return password.equals(formPassword);
    }

    public String createUser(){
        Pattern login = Pattern.compile("^[a-zA-Z0-9]{3,25}$");
        Pattern password = Pattern.compile("^[^*=]{6,25}$");
        Matcher loginMatcher = login.matcher(formLogin);
        Matcher passwordMatcher = password.matcher(formPassword);
        if (!loginMatcher.matches()) {
            return "Name must be from 3 to 25 symbols";
        }
        if (!passwordMatcher.matches()) {
            return "password must contains at least 6 symbols, except * and =";
        }
        return writeUser(formLogin, formPassword, userFile);
    }

    private String writeUser(String user, String password, File file) {
        if (users.containsKey(user)) {
            return "User already exist";
        }
        users.put(user, password);
        try {
            saveUsers();
            return "User successfully created";
        } catch (IOException e) {

            e.printStackTrace();
        }
        return "User create error";
    }


    private void initUserList(File file) {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] loginAndPassword = scanner.nextLine().split(":");
                users.put(loginAndPassword[0], loginAndPassword[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeUser(String user) throws IOException {
        users.remove(user);
        saveUsers();
    }

    private void saveUsers() throws IOException {
        userFile.delete();
        userFile.createNewFile();
        try (PrintWriter pw = new PrintWriter(userFile)) {
            users.keySet().forEach(x -> {
                pw.append(x);
                pw.append(":");
                pw.append(users.get(x));
                pw.append("\n");
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
