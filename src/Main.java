import java.util.ArrayList;

/**
 * transient : se utiliza para indicar que un campo de una clase no debe ser serializado. Ejemplo
 * public class Usuario implements Serializable {
 *     private String nombre;
 *     private String email;
 *     private transient String password;
 *}
 */
public class Main {
    public static void main(String[] args) {

        Alumno alumno = new Alumno("Fede",28);
        Alumno otro = new Alumno("Juan",25);
        ArrayList<Alumno> alumnos = new ArrayList<>();

        alumnos.add(alumno);
        alumnos.add(otro);

        //ControladoraArchivosObj.grabarArchivoObjetos(alumnos);
        alumnos = ControladoraArchivosObj.leerArchivosObjetos();
        System.out.println(alumnos.size());
        int index = 0;
        for(Alumno a : alumnos){
            System.out.println("Registro "+index+++" "+a+"\n");
        }
    }


}
