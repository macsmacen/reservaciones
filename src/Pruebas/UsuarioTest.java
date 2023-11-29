package Pruebas;

import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import Clases.Usuario;

public class UsuarioTest {

    private Usuario usuario;
    List<Usuario> usuarios;

    @Before
    public void setUp(){
        usuario = new Usuario("iker", "admin", "123");
        usuarios = new ArrayList<>();
    }

    @Test
    public void crearUsuarioTest(){
        Assert.assertEquals("iker", usuario.getNombre());
        Assert.assertEquals("123", usuario.getContrasena());
        Assert.assertEquals("admin", usuario.getTipo());
    }

    @Test
    public void registrosEnReservaTest(){
        usuarios = Usuario.obtenerUsuarios();
        //Verificamos que el archivo "usuarios.csv" tenga contenido
        Assert.assertFalse(usuarios.isEmpty());
        //Verificamos la cantidad de reservas: 2
        Assert.assertEquals(3, usuarios.size());
    }
}
