package Controlador;
import Modelo.Estudiante;
import Vista.VistaConsola;
import Modelo.EstudianteDTO;
import java.io.IOException;
/**
 *
 * @author Sergio Castro
 */
public class Controlador {
    private VistaConsola vista;
    private EstudianteDTO student;
    public Controlador() throws IOException{
        vista = new VistaConsola();
        student = new EstudianteDTO();
        funcionar();
    }
    
    public void funcionar() throws IOException{
        int opcion = 0;
        
        String menu = "\n"+"INSTITUTO LA FLORESTA"+
                "Seleccione tarea a realizar:"+
                "\n"+"1.Ingresar estudiante"+
                "\n"+"2.Buscar estudiante"+
                "\n"+"3.Modificar estudiante"+
                "\n"+"4.Eliminar estudiante"+
                "\n"+"5.Ver directorio de estudiantes"+
                "\n"+"6.Salir"+
                "Opción:";
        do{
            opcion = vista.leerDatoEntero(menu);
            
            switch(opcion){
                case 1:
                    ingresarEstudiante();                
                    break;
                case 2:
                    buscarEstudiante();
                    break;
                case 3:
                    modificarEstudiante();
                    break;
                case 4:
                    eliminarEstudiante();
                    break;
                case 5:
                    mostrarDirectorio();
                    break;
                case 6:
                    vista.imprimir("Hasta pronto");
                    break;
                default:
                    vista.imprimir("La opcion no es valida");
            }
        }while(opcion != 6);
    }
    
    public void ingresarEstudiante()throws IOException{
        String nom = "", ape = "", fecha = "", coIns= "", coPer = "", programa = "";
        long telCel = 0, telfijo = 0;
        vista.imprimir("\nIngresar estudiante");
        nom = vista.leerDatoString("Ingresar nombres:");
        ape = vista.leerDatoString("\nIngresar apellidos:");
        fecha = vista.leerDatoString("\nIngresar fecha de nacimiento (YYYY-MM-DD):");
        coIns = vista.leerDatoString("\nIngresar correo institucional:");
        coPer = vista.leerDatoString("\nIngresar correo personal:");
        telCel = vista.leerDatoLong("\nIngresar número de celular:");
        telfijo = vista.leerDatoLong("\nIngresar número fijo:");
        programa = vista.leerDatoString("\nIngresar programa:");
        
        if(student.getEstudianteDAO().agregarEstudiante(nom,ape,fecha,coIns,coPer,telCel,telfijo,programa,student.getEstudiantes(),student.getFile())){
            vista.imprimir("\nSe agregó el estudiante");
        }else{
            vista.imprimir("\nNo fue posible agregar el estudiante");  
        }
    }
    
    public void buscarEstudiante(){
        String coIns = "";
        vista.imprimir("\nBuscar estudiante");
        coIns = vista.leerDatoString("Ingresar correo institucional:");
        Estudiante rta = student.getEstudianteDAO().buscarEstudiante(coIns,student.getEstudiantes());
        if( rta != null){
            vista.imprimir("\nInformación del estudiante");
            vista.imprimir(rta.toString());
            
        }else{
            vista.imprimir("El estudiante no se encuentra registrado");
        }
    }
    
    public void modificarEstudiante() throws IOException{
        String nom = "", ape = "", fecha = "", coIns= "", coPer = "", programa = "";
        long telCel = 0, telfijo = 0;
        vista.imprimir("\nModificar estudiante");
        coIns = vista.leerDatoString("Ingresar correo institucional");
        Estudiante rta = student.getEstudianteDAO().buscarEstudiante(coIns,student.getEstudiantes());
        if(rta != null){
            nom = vista.leerDatoString("Ingresar nombres:");
            ape = vista.leerDatoString("\nIngresar apellidos:");
            fecha = vista.leerDatoString("\nIngresar fecha de nacimiento (YYYY-MM-DD):");
            coIns = vista.leerDatoString("\nIngresar correo institucional:");
            coPer = vista.leerDatoString("\nIngresar correo personal:");
            telCel = vista.leerDatoLong("\nIngresar número de celular:");
            telfijo = vista.leerDatoLong("\nIngresar número fijo:");
            programa = vista.leerDatoString("Ingresar programa:");
            
            student.getEstudianteDAO().eliminarEstudiante(coIns,student.getEstudiantes(),student.getFile());
            student.getEstudianteDAO().agregarEstudiante(nom,ape,fecha,coIns,coPer,telCel,telfijo,programa,student.getEstudiantes(),student.getFile());
            vista.imprimir("Se modificó el estudiante");
        }else{
            vista.imprimir("\nEl estudiante no se encuentra registrado");
        }
    }
    
    public void eliminarEstudiante(){
        String coIns = "";
        vista.imprimir("\nEliminar estudiante");
        coIns = vista.leerDatoString("Ingresar correo institucional:");
        if(student.getEstudianteDAO().eliminarEstudiante(coIns,student.getEstudiantes(),student.getFile())){
            vista.imprimir("\nSe eliminó el estudiante");
        }else{
            vista.imprimir("El estudiante no se encuentra registrado");
        }
    }
    
    public void mostrarDirectorio() throws IOException{
        vista.imprimir("\nEl directorio de los estudiantes");
        vista.imprimir(student.getEstudianteDAO().verEstudiante(student.getFile()));
    }
}
