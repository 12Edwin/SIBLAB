package mx.edu.utez.SIBLAB.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Bcrypt {
    public static void main(String[] args) {
        String pass = "123456";
        System.out.println(bcrypt(pass));
    }
    public static String bcrypt (String pass){
        BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
        return crypt.encode(pass);
    }
}
