package Main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class Preference {

    String userName;
    String password;
    public static String file="CONFIG_FILE.txt";
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = DigestUtils.shaHex(password);
    }
    public Preference() {
        userName = "admin";
        setPassword("admin");
    }

    public static void initConfig() {
        Preference preference =new Preference();
        Gson gson=new Gson();
        Writer writer = null;
        try {
            writer=new FileWriter(file);
            gson.toJson(preference,writer);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static Preference getPreference() {
        Gson gson=new Gson();
        Preference preference =new Preference();
        try {
            preference=gson.fromJson(new FileReader(file), Preference.class);
        } catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
            // TODO Auto-generated catch block
            initConfig();
            e.printStackTrace();
        }
        return preference;
    }
    public static boolean initPrefernce(Preference preference) {
        Gson gson=new Gson();

        Writer writer = null;
        try {
            writer=new FileWriter(file);
            gson.toJson(preference,writer);
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;
  }







}
