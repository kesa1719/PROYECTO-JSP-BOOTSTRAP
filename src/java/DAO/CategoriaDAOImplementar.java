
package DAO;

import Factory.ConexionBD;
import Factory.FactoryConexionBD;
import Model.Categoria;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CategoriaDAOImplementar implements CategoriaDAO{
    
    ConexionBD conn; //crear el objeto tipo conexion
    
    
    public CategoriaDAOImplementar() {
        //Definir a la base de datos que se conectara por defecto
        
    }

    @Override
    public List<Categoria> Listar() {
        this.conn = FactoryConexionBD.open(FactoryConexionBD.MySQL);
        StringBuilder miSQL = new StringBuilder(); //Construir la consulta
        miSQL.append("SELECT * FROM tb_categoria;"); //Agregarla consulta
        List<Categoria> lista = new ArrayList<Categoria>(); 
        try{
            //Se crea el objeto ResultSet implementando el método(consultaSQL) creado para la consulta
            ResultSet resultadoSQL = this.conn.consultaSQL(miSQL.toString()); 
            while(resultadoSQL.next()){
                Categoria categoria = new Categoria(); //DEclarar el objeto categoria
                //Asignar Cada campo consultado al atributo en la clase
                categoria.setId_categoria(resultadoSQL.getInt("id_categoria"));
                categoria.setNom_categoria(resultadoSQL.getString("nom_categoria"));
                categoria.setEstado_categoria(resultadoSQL.getInt("estado_categoria"));
                lista.add(categoria); //Agregar al Array cada registro ebcontrado
            }
        
        } catch (Exception ex) {
        
        }finally{
            this.conn.cerrarConexion(); //Para cerrar la conexion 
        }
        return lista;
        
    }

    @Override
    public List<Categoria> Listar2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Categoria editarCat(int id_cat_edit) {
        this.conn = FactoryConexionBD.open(FactoryConexionBD.MySQL); //Hacer la conexion 
        Categoria categoria = new Categoria(); //Objeto categoria para devolver los datos
        StringBuilder miSQL = new StringBuilder(); //Construir la consulta
        //Agregar la consuta SQL
        miSQL.append("SELECT * FROM tb_categoria WHERE id_categoria = ").append(id_cat_edit);
        try{ //Realizar consulta
            ResultSet resultadoSQL = this.conn.consultaSQL(miSQL.toString()); 
            while(resultadoSQL.next()){
                categoria.setId_categoria(resultadoSQL.getInt("id_categoria"));
                categoria.setNom_categoria(resultadoSQL.getString("nom_categoria"));
                categoria.setEstado_categoria(resultadoSQL.getInt("estado_categoria"));
            }
        }   catch (Exception ex) {
        }finally{
            this.conn.cerrarConexion(); //Cerrar conexion
        }
        return categoria;
        
    }
        
    @Override
    public boolean guardarCat(Categoria categoria) {
         this.conn = FactoryConexionBD.open(FactoryConexionBD.MySQL); //Hacer la conexion 
         boolean guarda = false; //Bandera de resultado
         try{
             if(categoria.getId_categoria() == 0) {
                StringBuilder miSQL = new StringBuilder();
                //Agregar consulta SQL; el id_categoria es autoincrementable
                miSQL.append("INSERT INTO tb_categoria(nom_categoria, estado_categoria) VALUES ('");
                miSQL.append(categoria.getNom_categoria() + "', ").append(categoria.getEstado_categoria());
                miSQL.append(");");
                //Invocar metodo para ejecutar la consulta
                this.conn.ejecutarSQL(miSQL.toString());   
             } else if (categoria.getId_categoria() > 0){ //Actualizar, id_categoria mayores a 0
                 StringBuilder miSQL = new StringBuilder();
                 miSQL.append("UPDATE tb_categoria SET id_categoria = ").append(categoria.getId_categoria());
                 miSQL.append(", nom_categoria = '").append(categoria.getNom_categoria());
                 miSQL.append(", estado_categoria = ").append(categoria.getEstado_categoria());
                 miSQL.append(" WHERE id_categoria = ").append(categoria.getId_categoria()).append(";");
                 //Invocar método para ejecutar la consulta 
                 this.conn.ejecutarSQL(miSQL.toString());
             }
             guarda = true;
         }catch (Exception e){    
             
         }finally{
             this.conn.cerrarConexion(); //Cerrar conexión
         }
        return guarda;
    }

    @Override
    public boolean borrarCat(int id_cat_borrar) {
        this.conn = FactoryConexionBD.open(FactoryConexionBD.MySQL); //Hacer la conexion 
        boolean borra = false; //Bandera de resultado
        try{
            StringBuilder miSQL = new StringBuilder();
            miSQL.append("DELETE FROM tb_categoria WHERE id_categoria = ").append(id_cat_borrar);
            this.conn.ejecutarSQL(miSQL.toString());
            borra = true;
        }catch (Exception e){    
        }finally{
            this.conn.cerrarConexion(); //Cerrar conexion
        }
        return borra;
    }
    
}
