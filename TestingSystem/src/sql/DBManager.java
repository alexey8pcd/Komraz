package sql;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Solovenko
 *
 * Методы 2х типов: 
 * 1) с возвратом значения: SELECT 
 * 2) без вовзрата значения: INSERT, UPDATE, DELETE
 *
 */
public class DBManager {

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
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "ytrewq");
            //Создаём ссылку на утверждение из нашего соединения
            Statement statement = con.createStatement();
            //В результирующую переменную "rs" получаем ответ от базы после нашего запроса sqlRequest
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
     * Метод без возвратного значения. Используется для INSERT,UPDATE,DELETE-запросов
     * @param sqlRequest SQL-запрос
     */
    public static void requestWithoutAnswerSQL(String sqlRequest) {
        try {
            Class.forName("java.sql.Driver");
            //Создаём соединение
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "ytrewq");
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