package com.lucasengcomp.eventos.dao;

import com.lucasengcomp.eventos.model.Evento;
import org.junit.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

public class EventoDAOTest {

    private static Connection connection;
    private Evento evento;
    private EventoDAO dao;

    @BeforeClass
    public static void  iniciarClasse() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cadastroevento" +
                "?useTimezone=true&serverTimezone=UTC", "root", "root");
    }

    @AfterClass
    public static void encerrarClasse() throws SQLException {
        connection.close();
    }

    @Test
    public void testInsercao() {
        // Arrange
        inicializarEventoEDAO();

        // Act
        Integer id = dao.salvar(evento);

        // Assert
        Assert.assertNotNull("Identificador foi retornado como NULL.", id);
    }

    @Test
    public void testLeitura() {
        // Arrange
        inicializarEventoEDAO();

        Integer id = dao.salvar(evento);

        // Act
        evento = dao.buscar(id);

        // Assert
        Assert.assertNotNull("Evento nulo.", evento);
    }

    @Test
    public void testAtualizacao() {
        // Arrange
        evento = new Evento(1, "Notebook", new Date());
        dao = new EventoDAO(connection);

        // Act
        EventoDAO dao = new EventoDAO(connection);
        Evento evento = dao.buscar(this.evento.getId());

        String nomeAlterado = evento.getNome() + " alterado";
        evento.setNome(nomeAlterado);
        dao.atualizar(evento);
        evento = dao.buscar(this.evento.getId());

        // Assert
        Assert.assertEquals("O nome não foi atualizado corretamente.", nomeAlterado, evento.getNome());
    }

    @Test
    public void testRemocao() {
        // Arrange
        inicializarEventoEDAO();
        Integer id = dao.salvar(evento);

        // Act
        dao.deletar(id);
        evento = dao.buscar(id);

        // Assert
        Assert.assertNull("Evento ainda existe e não deveria.", evento);
    }

    private void inicializarEventoEDAO() {
        evento = new Evento(null, "Notebook", new Date());
        dao = new EventoDAO(connection);
    }
}
