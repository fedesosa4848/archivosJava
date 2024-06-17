import java.io.*;

public class ControladoraArchivosPrimitivos {

    public static void leerArchivoDatosPrimitivo()
    {
        DataInputStream dataInputStream = null;
        try
        {
            FileInputStream fileInputStream = new FileInputStream("Temperatura.bin");

            dataInputStream = new DataInputStream(fileInputStream);

            while (true) {
                System.out.println(dataInputStream.readUTF());
                System.out.println(dataInputStream.readInt());
            }
        }
        catch (FileNotFoundException ex) //Si el no archivoExiste, o no se puede grabar un directorio o el archivo esta dañado o el disco dañado
        {
            System.out.println("Archivo no encontrado");
        }
        catch (EOFException e) //Es una excepcion "buena". Nos indica el fin del archivo
        {
            System.out.println("FIN");
        }
        catch (IOException ex) //Errores de entrada o salida
        {
            System.out.println("error io");
        }
        catch (Exception e) //Excepcion general
        {
            System.out.println("otra cosa");
        }
        finally
        {
            try
            {
                assert dataInputStream != null;
                dataInputStream.close();
            }
            catch (IOException ex)
            {
                System.out.println("No se pudo cerrar el archivo");
            }

        }
    }

    public static void grabarArchivoDatosPrimitivos()
    {
        DataOutputStream dataOutputStream = null;
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream("Temperatura.bin") ;
            dataOutputStream = new DataOutputStream(fileOutputStream);

            for (int i=0;i<10;i++)
            {
                dataOutputStream.writeUTF("Gonzalo "+i);
                dataOutputStream.writeInt(i);
            }

        }
        catch (FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally
        {
            try
            {
                assert dataOutputStream != null;
                dataOutputStream.close();
            }
            catch (IOException ex) //Error
            {
                System.out.println("No se pudo cerrar el archivo");
            }

        }
    }
}
