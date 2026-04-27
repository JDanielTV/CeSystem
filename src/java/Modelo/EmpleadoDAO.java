package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {   
    
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    /**
     *
     */
    public static int idEmpleadoAct;
    
    
        //validar
    Empleado em=new Empleado();

    
    public Empleado validar(String user, String password){
        
        String sql="select * from empleado where User=? and Password=?";
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1,user);
            ps.setString(2,password);
            rs=ps.executeQuery();
            while (rs.next()){
                String idEmpleadoActS = rs.getString("IdEmpleado");
                idEmpleadoAct = Integer.parseInt(idEmpleadoActS);
                em.setIdempleado(idEmpleadoAct);
                em.setUser(rs.getString("User"));
                em.setPassword(rs.getString("Password"));
                em.setNombre(rs.getString("Nombres"));
            }
        }
        catch (Exception e){
            
        }
        return em;
    }
    
    public int obtenerIdEmp(){        
        idEmpleadoAct = em.getIdempleado();
        return idEmpleadoAct;
    }
    
 //Operaciones CRUD
    public List listar(){
        String sql = "select * from empleado";
        List<Empleado>lista=new ArrayList<>();
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                Empleado em = new Empleado();
                em.setIdempleado(rs.getInt(1));
                em.setNombre(rs.getString(2));
                em.setApellido(rs.getString(3));
                em.setTelefono(rs.getString(4));
                em.setUser(rs.getString(5));
                lista.add(em);                                                             
            }
        }catch (Exception e){            
        }        
        return lista;
    }   
    public int agregar(Empleado em){
        String sql = "insert into empleado(IdEmpleado,Nombres, Apellido, Telefono,User,Password)values(?,?,?,?,?,?)";
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            String idempleado = String.valueOf(em.getIdempleado());
            ps.setString(1,idempleado);            
            ps.setString(2,em.getNombre());
            ps.setString(3,em.getApellido());
            ps.setString(4,em.getTelefono());
            ps.setString(5,em.getUser());
            ps.setString(6,em.getUser());            
            ps.executeUpdate();
        }catch (Exception e){            
        }
        return r;
    }
    public Empleado listarId(int id){
        Empleado emp= new Empleado();
        String sql="select * from empleado where IdEmpleado="+id;
        try{            
            con = cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){    
                emp.setIdempleado(id);
                emp.setNombre(rs.getString(2));
                emp.setApellido(rs.getString(3));
                emp.setTelefono(rs.getString(4));
                emp.setUser(rs.getString(5));                
            }
                    
        }catch (Exception e){            
        }
        return emp;
    }
            
    public int actualizar(Empleado em){
        String sql = "update empleado set Nombres=?, Apellido=?, Telefono=?, User=? where IdEmpleado=?";
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1,em.getNombre());
            ps.setString(2,em.getApellido());
            ps.setString(3,em.getTelefono());
            ps.setString(4,em.getUser());
            ps.setInt(5,em.getIdempleado());            
            ps.executeUpdate();
        }catch (Exception e){            
        }
        return r;        
    }
    public void eliminar(int id){
        String sql="delete from empleado where IdEmpleado = "+id;
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        }catch (Exception e){            
        }
        
    }
}
