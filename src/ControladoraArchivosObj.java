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


public class ControladoraArchivosObj {

    public static ArrayList<Alumno> leerArchivosObjetos() {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        ObjectInputStream objectInputStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("alumnos.dat");
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
        catch (FileNotFoundException ex) //Archivo no encontrado, dañado o se intenta grabar un directorio
        {
            ex.printStackTrace();
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        } finally
        {
            try
            {
                objectInputStream.close();
            }
            catch (IOException ex)
            {

            }

        }
        return  alumnos;
    }

    public static void grabarArchivoObjetos(ArrayList<Alumno> alumnos)
    {
        ObjectOutputStream objectOutputStream = null;
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream("alumnos.dat");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for (Alumno a : alumnos) {
                objectOutputStream.writeObject(a);
            }

        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            try
            {
                objectOutputStream.close();
            }
            catch (IOException ex)
            {

            }

        }
    }
}
