package com.mika.loginconfile.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.mika.loginconfile.model.Usuario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class ApiClient {

    public static void guardar(Context context, Usuario usuario){
        File directorio= context.getFilesDir();
        File archivo= new File(directorio, "cliente.dat");
        if(!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream fos= new FileOutputStream(archivo);
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            oos.writeObject(usuario);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Usuario leer(Context context){
        Usuario usuario= new Usuario();
        File directorio= context.getFilesDir();
        File archivo= new File(directorio, "cliente.dat");
        try {
            FileInputStream fis= new FileInputStream(archivo);
            ObjectInputStream ois= new ObjectInputStream(fis);
            usuario= (Usuario) ois.readObject();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public static Usuario login(Context context,String mail, String pass){
        Usuario usuario= new Usuario();
        File directorio= context.getFilesDir();
        File archivo= new File(directorio, "cliente.dat");
        try {
            FileInputStream fis= new FileInputStream(archivo);
            ObjectInputStream ois= new ObjectInputStream(fis);
            Usuario usuario1= (Usuario) ois.readObject();
            String email= usuario1.getMail();
            String password= usuario1.getPassword();
            if(email.equals(mail) && password.equals(pass)) {
                usuario = usuario1;
            }else {
                usuario=null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return usuario;
    }

}
