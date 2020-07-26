package javafxsolo.Utils;


import java.io.*;

public abstract class Serializer {
    public static void serialize(String filename, Object object){
        try{
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream oos;

            try {
                oos = new ObjectOutputStream(file);
                oos.writeObject(object);
                oos.flush();
                oos.close();
            }catch (IOException e){
                // erreur de serialisation
                e.printStackTrace();
            }

        }catch (FileNotFoundException e){
            //ficher non trouve
            e.printStackTrace();
        }

    }

    public static Object deSerialize(String filename){
        try{
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream ois;
            try{
                ois = new ObjectInputStream(file);
                try {
                    Object object = ois.readObject();
                    ois.close();
                    return object;
                }catch (ClassNotFoundException e){
                    e.printStackTrace();

                }
            }catch (StreamCorruptedException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();

            }
        }catch (FileNotFoundException e){
            //ficher non trouvé
            e.printStackTrace();
        }
        return null;
    }

}
