package sql;

import java.sql.*;

/**
 *
 * @author Solovenko
 *
 * Методы 2х типов: 1) с возвратом значения: SELECT 2) без вовзрата значения:
 * INSERT, UPDATE, DELETE
 *
 */
public class DBManager {

    /**
     * Метод с возвратом значений
     *
     * @param sql SELECT-запрос
     * @param columnNames название колонок в таблице, по которым возвращаем
     * значения
     * @return String[] массив с ответным значением.
     * <br>Положение значений в массиве эквивалентно порядку названий колонок во
     * входном параметре columnNames
     */
    public static String[] requestWithAnswerSQL(String sql, String[] columnNames) {
        try {
            Class.forName("java.sql.Driver");
            //Создаём соединение
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "ytrewq");
            //Создаём ссылку на утверждение из нашего соединения
            Statement statement = con.createStatement();
            
            //Запрос, выясняющий сколько у нас колонок в таблице
            String query = "select * from prepodavatel;";

//            helpStrBuilder.append("select * from ");
//            helpStrBuilder.append(tableName);
//            helpStrBuilder.append(";");
//            //Запрос на вставку значений в таблицу
//            StringBuilder sqlRequest = new StringBuilder();
//            sqlRequest.append("insert into ");
//            sqlRequest.append(tableName);
//            sqlRequest.append(" values ");
            String renameString = "update prepodavatel set prepodavatel.`FIO`='Крючкокова' where prepodavatel.`ID_PREPODAVATEL`=1;";

            statement.executeUpdate(renameString);
            //В результирующую переменную "rs" получаем ответ от базы после нашего запроса query
            ResultSet rs = statement.executeQuery(query);
            //Получаем из результирующей переменной значения из таблицы

            while (rs.next()) {
                String fio = rs.getString("FIO");

                String login = rs.getString("Login");
                String password = rs.getString("Password");
//                model.addRow(new Object[]{
//                    fio, login, password
//                });
//                data.add(new String[]{
//                    fio, login, password
//                });
            }
            rs.close();
            statement.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
//            JOptionPane.showMessageDialog(this, "Error in connectivity: " + e);
        }
        return null;
    }

    public static void requestWithoutAnswerSQL(String sql) {

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

    public static void main(String[] args) {
        requestWithAnswerSQL("asdasdasdasd", new String[]{
        "ssssss","vvvvvv","2222222"});
    }

}
