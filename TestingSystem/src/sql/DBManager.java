package sql;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import main.DialogManager;

/**
 *
 * @author Solovenko
 *
 * Методы 2х типов: 1) с возвратом значения: SELECT 2) без вовзрата значения:
 * INSERT, UPDATE, DELETE
 *
 */
public class DBManager {

    public static final EntityManagerFactory managerFactory
            = Persistence.createEntityManagerFactory("TestingSystemPU");
    public static EntityManager entityManager
            = managerFactory.createEntityManager();

    /**
     * Метод сохранения объекта или обновления в БД посредством ORM 
     * (Eclipse JPA 2.0)
     * #Аналог UPDATE в SQL
     * @param object сохраняемы объект
     * @throws Exception возможное исключение на запись в БД
     */
    public static void writeObjectMerge(Object object) throws Exception {
        entityManager.getTransaction().begin();
        entityManager.merge(object);
        entityManager.getTransaction().commit();
    }
    
    /**
     * Метод сохранения объекта или обновления в БД посредством ORM 
     * (Eclipse JPA 2.0)
     * #Аналог INSERT в SQL
     * @param object
     * @throws Exception 
     */
    public static void writeObjectPersist(Object object) throws Exception {
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
    }

    public static String loadUserNameAndPassword() {
        try {
            DataInputStream dataInputStream = new DataInputStream(
                    new FileInputStream(new File("./config.dat")));
            String result = dataInputStream.readUTF();
            return result;
        } catch (Exception ex) {
            DialogManager.errorMessage(ex);
        }
        return null;
    }

    /**
     * Метод с возвратом значений. Используется для SELECT-запросов
     *
     * @param sqlRequest SELECT-запрос
     * @param columnNames название колонок в таблице, по которым возвращаем
     * значения
     * @return ArrayList<String[]> лист массивов с ответным значением.
     * <br>Положение значений в массиве эквивалентно порядку названий колонок во
     * входном параметре columnNames
     */
    public static ArrayList<String[]> requestWithAnswerSQL(String sqlRequest, String[] columnNames) {

        //Количество колонок в запросе
        int amountOfColumns = columnNames.length;
        //Инициализируем лист, куда будем помещать ответные значения
        ArrayList answer = new ArrayList();

        try {
            Class.forName("java.sql.Driver");
            //Создаём соединение
            String nameAndPassword = loadUserNameAndPassword();
            String[] parts = nameAndPassword.split(",");
            String name = parts[0];
            String password = parts[1];
            //root,ytrewq
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydb", name, password);
            //Создаём ссылку на утверждение из нашего соединения
            Statement statement = con.createStatement();
            //В результирующую переменную "rs" получаем ответ 
            //от базы после нашего запроса sqlRequest
            ResultSet rs = statement.executeQuery(sqlRequest);
            //Получаем из результирующей переменной значения из таблицы
            while (rs.next()) {
                String[] linesOfAnswer = new String[amountOfColumns];
                for (int i = 0; i < amountOfColumns; i++) {
                    linesOfAnswer[i] = rs.getString(columnNames[i]);
                }
                answer.add(linesOfAnswer);
            }
            rs.close();
            statement.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error in connectivity: " + e);
        }
        return answer;
    }

    /**
     * Метод без возвратного значения. Используется для
     * INSERT,UPDATE,DELETE-запросов
     *
     * @param sqlRequest SQL-запрос
     */
    public static void requestWithoutAnswerSQL(String sqlRequest) {
        try {
            Class.forName("java.sql.Driver");
            String nameAndPassword = loadUserNameAndPassword();
            String[] parts = nameAndPassword.split(",");
            String name = parts[0];
            String password = parts[1];
            //Создаём соединение
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydb", name, password);
            //Создаём ссылку на утверждение из нашего соединения
            Statement statement = con.createStatement();
            //Выполняем sql-запрос
            statement.executeUpdate(sqlRequest);
            statement.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error in connectivity: " + e);
        }
    }

    /**
     * Типы возможных запросов в БД
     */
    public enum TypeOfRequest {

        SELECT,
        INSERT,
        UPDATE,
        DELETE
    }
}
