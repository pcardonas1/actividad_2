/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

/**
 *
 * @author augus
 */
public class cliente extends persona{
    private String nit;
    private int id;
    Conexion cn;
public cliente(){}
    public cliente(int id,String nit, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.nit = nit;
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
  // @Override
    
    //public void agregar(){
    //System.out.println("Nit: " + getNit());
    //System.out.println("Nombres: " + getNombres());
    //System.out.println("Apellidos: " + getApellidos());   
    //System.out.println("Direccion: " + getDireccion());
    //System.out.println("Telefono: " + getTelefono());
    //System.out.println("Fecha Nacimiento: " + getFecha_nacimiento());
    //System.out.println("____________________");
    public DefaultTableModel leer(){
       DefaultTableModel tabla = new DefaultTableModel ();
       try{
           cn = new Conexion();
           cn.abrir_conexion();
           String query;
           query = "Select id_cliente as id,nit,nombres,apellidos,direccion,telefono,fecha_nacimiento from clientes;";
           ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);    
           
           String encabezado[] = {"id","Nit","Nombre","Apellidos","Direccion","Telefono","Fecha_Nacimiento"};
           tabla.setColumnIdentifiers(encabezado);
           
           String datos[] = new String[7];
           
           while(consulta.next()){
               datos[0] = consulta.getString("id");
               datos[1] = consulta.getString("nit");
               datos[2] = consulta.getString("nombres");
               datos[3] = consulta.getString("apellidos");
               datos[4] = consulta.getString("direccion");
               datos[5] = consulta.getString("telefono");
               datos[6] = consulta.getString("fecha_nacimiento");
               tabla.addRow(datos);
           }
           cn.cerrar_conexion();
          
       }catch(SQLException ex){
           cn.cerrar_conexion();
           System.out.println("Error:" + ex.getMessage());
       }
       return tabla;    
    }
    
    
    
    @Override
   public void agregar(){
          try{
              PreparedStatement parametro;
              String query ="INSERT INTO clientes(nit,nombres,apellidos,direccion,telefono,fecha_nacimiento)VALUES(?,?,?,?,?,?);";
              cn = new Conexion();
              cn.abrir_conexion();
              parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
              parametro.setString(1, getNit());
              parametro.setString(2, getNombres());
              parametro.setString(3, getApellidos());
              parametro.setString(4, getDireccion());
              parametro.setString(5, getTelefono());
              parametro.setString(6, getFecha_nacimiento());
              
             int executar = parametro.executeUpdate();
             cn.cerrar_conexion();
             JOptionPane.showMessageDialog(null,Integer.toString(executar)+"Registro Ingresado","agregar",JOptionPane.INFORMATION_MESSAGE);
            }catch(HeadlessException | SQLException ex){
            System.out.println("Error..."+ ex.getMessage());
            
            }
        
           
   }
   public void actualizar(){
       try{
              PreparedStatement parametro;
              String query ="UPDATE clientes set nit= ?,nombres= ?,apellidos= ?,direccion= ?,telefono= ?,fecha_nacimiento= ? "+
                             "where id_cliente = ?";
              cn = new Conexion();
              cn.abrir_conexion();
              parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
              parametro.setString(1, getNit());
              parametro.setString(2, getNombres());
              parametro.setString(3, getApellidos());
              parametro.setString(4, getDireccion());
              parametro.setString(5, getTelefono());
              parametro.setString(6, getFecha_nacimiento());
              parametro.setInt(7, getId());
             int executar = parametro.executeUpdate();
             cn.cerrar_conexion();
             JOptionPane.showMessageDialog(null,Integer.toString(executar)+"Registro Actualizado","agregar",JOptionPane.INFORMATION_MESSAGE);
            }catch(HeadlessException | SQLException ex){
            System.out.println("Error..."+ ex.getMessage());
            
            }
        
    }
    @Override
    public void eliminar(){ 
     try{
              PreparedStatement parametro;
              cn = new Conexion();
              cn.abrir_conexion();
              String query;
              query ="Delete from clientes where id_cliente = ?";
              
              parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
              parametro.setInt(1, getId());
              
              int executar = parametro.executeUpdate();
             cn.cerrar_conexion();
             JOptionPane.showMessageDialog(null,Integer.toString(executar)+"Registro Elimindo","agregar",JOptionPane.INFORMATION_MESSAGE);
            }catch(HeadlessException | SQLException ex){
            System.out.println("Error..."+ ex.getMessage());
            
            }
    
    }   

   
}

    

   

