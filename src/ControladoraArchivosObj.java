import java.io.*;
import java.util.ArrayList;

/**
 * Excepcion Comprobada
 * Son excepciones que el compilador requiere que sean manejadas de alguna manera, ya sea mediante un bloque try-catch
 * o declarando que el método las puede lanzar usando throws.  Tenemos control sobre cómo manejarlas, pero el compilador
 * nos obliga a hacerlo. Ej: IOException,
 *
 * Excepcion No comprobada
 *
 * : Son excepciones que no requieren ser manejadas explícitamente en el código. El compilador no obliga a
 * que sean capturadas o declaradas. No estamos obligados a manejarlas, aunque podemos hacerlo si queremos.
 * Estas excepciones suelen ser resultado de errores de programación Ej: NullPointerException,
 * ArrayIndexOutOfBoundsException.
 */


public class ControladoraArchivosObj <T extends Serializable> {

    public static ArrayList<Alumno> leerArchivosObjetos() {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("alumnos.dat");
            objectInputStream = new ObjectInputStream(fileInputStream);

            //Si conocemos la cantidad excata de registros podemos hacer un for pero es mejor un while-true
            while (true) {
                Alumno alumno = (Alumno) objectInputStream.readObject();
                alumnos.add(alumno);
            }


        } catch (EOFException ex) //Fin del archivo
        {
            System.out.println("FIN");
        }

        catch (ClassNotFoundException e) //Excepcion de que por ejemplo pasamos un archivo de perros y eran de personas
        {
            throw new RuntimeException(e);
        }
        catch (FileNotFoundException ex) //Archivo no encontrado, dañado o se intenta grabar un directorio
        {
            System.out.println(ex.getMessage());
        }
        catch (IOException exception)
        {
            System.out.println(exception.getMessage());
        }
        finally
        {
            try
            {
                if(fileInputStream!= null){
                    fileInputStream.close();
                }
                if(objectInputStream != null){
                    objectInputStream.close();
                }
            }
            catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }

        }
        return  alumnos;
    }


    //Recibe un arraylist de alumnos
    public static void grabarArchivoObjetos(ArrayList<Alumno> alumnos)
    {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try
        {
            fileOutputStream = new FileOutputStream("alumnos.dat");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for (Alumno a : alumnos) {
                objectOutputStream.writeObject(a);
            }

        }
        catch (FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch (IOException exception)
        {
            System.out.println(exception.getMessage());
        }
        finally
        {
            try
            {

                if(fileOutputStream != null){
                    fileOutputStream.close();
                }
                if(objectOutputStream != null){
                    objectOutputStream.close();
                }
            }
            catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }

        }
    }
}
