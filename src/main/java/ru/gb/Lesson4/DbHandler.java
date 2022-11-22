package ru.gb.Lesson4;

import java.sql.*;

public class DbHandler {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
    public static void conn() throws ClassNotFoundException, SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "JGB";
        String userName = "root";
        String password = "123456";
        conn = DriverManager.getConnection(url + dbName, userName, password);
    }

    // --------Создание таблицы--------
    public static void createDB() throws ClassNotFoundException, SQLException {
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists movies (id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, title VARCHAR(128) NOT NULL, duration INT NOT NULL CHECK (duration IN(60,90,120)));");
        statmt.execute("CREATE TABLE if not exists halls (id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, hall_name VARCHAR(64) NOT NULL, capacity INT NOT NULL);");
        statmt.execute("CREATE TABLE if not exists seats (id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, hall_id INT NOT NULL, line INT NOT NULL, seat INT NOT NULL, CONSTRAINT fk_seats_halls FOREIGN KEY (hall_id) REFERENCES halls (id) ON DELETE NO ACTION ON UPDATE NO ACTION);");
        statmt.execute("CREATE TABLE if not exists sessions (id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, movie_id INT NOT NULL, hall_id INT NOT NULL, date DATE NOT NULL, time TIME NOT NULL,CONSTRAINT fk_sessions_halls FOREIGN KEY (hall_id) REFERENCES halls (id) ON DELETE NO ACTION ON UPDATE NO ACTION, CONSTRAINT fk_sessions_movies FOREIGN KEY (movie_id) REFERENCES movies (id) ON DELETE NO ACTION ON UPDATE NO ACTION);");
        statmt.execute("CREATE TABLE if not exists prices (id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, session_id INT NOT NULL, price DECIMAL(4,2) NOT NULL,  CONSTRAINT fk_prices_sessions FOREIGN KEY (session_id) REFERENCES sessions (id) ON DELETE NO ACTION ON UPDATE NO ACTION);");
        statmt.execute("CREATE TABLE if not exists tickets (id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, session_id INT NOT NULL, seat_id INT NOT NULL, sold CHAR(1) NOT NULL CHECK (sold IN('Y','N')), CONSTRAINT fk_tickets_sessions FOREIGN KEY (session_id) REFERENCES sessions (id) ON DELETE NO ACTION ON UPDATE NO ACTION, CONSTRAINT fk_tickets_seats FOREIGN KEY (seat_id) REFERENCES seats (id) ON DELETE NO ACTION ON UPDATE NO ACTION);");
        System.out.println("Таблицы созданы или уже существуют.");
    }

    // --------Заполнение таблицы--------
    public static void writeDB() throws SQLException {
        statmt.execute("INSERT INTO movies (title, duration) VALUES (\"Die Hard-1\", 120);");
        statmt.execute("INSERT INTO movies (title, duration) VALUES (\"Die Hard-2\", 120);");
        statmt.execute("INSERT INTO movies (title, duration) VALUES (\"Die Hard-3\", 60);");
        statmt.execute("INSERT INTO movies (title, duration) VALUES (\"Die Hard-4\", 120);");
        statmt.execute("INSERT INTO movies (title, duration) VALUES (\"Die Hard-5\", 90);");

        statmt.execute("INSERT INTO halls (hall_name, capacity) VALUES (\"Hall-1\", 6);");
        statmt.execute("INSERT INTO halls (hall_name, capacity) VALUES (\"Hall-2\", 12)");

        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j <= 3; j++) {
                statmt.execute("INSERT INTO seats (hall_id, line, seat) VALUES (1, " + i + " , " + j + ");");
            }
        }
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 4; j++) {
                statmt.execute("INSERT INTO seats (hall_id, line, seat) VALUES (2, " + i + " , " + j + ");");
            }
        }

        statmt.execute("INSERT INTO sessions (movie_id, hall_id, date, time) VALUES (1, 1, '2022-11-11', '09:00');");
        statmt.execute("INSERT INTO sessions (movie_id, hall_id, date, time) VALUES (1, 2, '2022-11-11', '09:30');");
        statmt.execute("INSERT INTO sessions (movie_id, hall_id, date, time) VALUES (2, 1, '2022-11-11', '09:45');");
        statmt.execute("INSERT INTO sessions (movie_id, hall_id, date, time) VALUES (2, 1, '2022-11-11', '13:00');");
        statmt.execute("INSERT INTO sessions (movie_id, hall_id, date, time) VALUES (3, 2, '2022-11-11', '19:00');");
        statmt.execute("INSERT INTO sessions (movie_id, hall_id, date, time) VALUES (3, 1, '2022-11-11', '20:00');");
        statmt.execute("INSERT INTO sessions (movie_id, hall_id, date, time) VALUES (4, 1, '2022-11-11', '16:00');");
        statmt.execute("INSERT INTO sessions (movie_id, hall_id, date, time) VALUES (4, 1, '2022-11-11', '23:00');");
        statmt.execute("INSERT INTO sessions (movie_id, hall_id, date, time) VALUES (5, 2, '2022-11-11', '09:00');");

        statmt.execute("INSERT INTO prices (session_id, price) VALUES (1, 10.00);");
        statmt.execute("INSERT INTO prices (session_id, price) VALUES (2, 30.00);");
        statmt.execute("INSERT INTO prices (session_id, price) VALUES (3, 30.00);");
        statmt.execute("INSERT INTO prices (session_id, price) VALUES (4, 35.00);");
        statmt.execute("INSERT INTO prices (session_id, price) VALUES (5, 45.00);");
        statmt.execute("INSERT INTO prices (session_id, price) VALUES (6, 55.00);");
        statmt.execute("INSERT INTO prices (session_id, price) VALUES (7, 40.00);");
        statmt.execute("INSERT INTO prices (session_id, price) VALUES (8, 60.00);");
        statmt.execute("INSERT INTO prices (session_id, price) VALUES (9, 25.00);");

        statmt.execute("INSERT INTO tickets (session_id, seat_id, sold) VALUES (1, 1,'Y');");
        statmt.execute("INSERT INTO tickets (session_id, seat_id, sold) VALUES (1, 2,'Y');");
        statmt.execute("INSERT INTO tickets (session_id, seat_id, sold) VALUES (2, 1,'Y');");
        statmt.execute("INSERT INTO tickets (session_id, seat_id, sold) VALUES (3, 3,'Y');");
        statmt.execute("INSERT INTO tickets (session_id, seat_id, sold) VALUES (4, 4,'Y');");
        statmt.execute("INSERT INTO tickets (session_id, seat_id, sold) VALUES (5, 5,'Y');");
        statmt.execute("INSERT INTO tickets (session_id, seat_id, sold) VALUES (6, 6,'Y');");
        statmt.execute("INSERT INTO tickets (session_id, seat_id, sold) VALUES (7, 7,'Y');");
        statmt.execute("INSERT INTO tickets (session_id, seat_id, sold) VALUES (8, 8,'Y');");
        statmt.execute("INSERT INTO tickets (session_id, seat_id, sold) VALUES (9, 9,'Y');");
        statmt.execute("INSERT INTO tickets (session_id, seat_id, sold) VALUES (2, 4,'Y');");
        statmt.execute("INSERT INTO tickets (session_id, seat_id, sold) VALUES (4, 6,'Y');");

        System.out.println("Таблицы заполнены");
    }

    // -------- Вывод таблицы--------
    public static void OneRequestDB() throws ClassNotFoundException, SQLException {

//      ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени. Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;
        resSet = statmt.executeQuery(
                "SELECT t1.title, t1.date, t1.time, t1.duration, t2.title, t2.date, t2.time, t2.duration " +
                        "FROM (SELECT s.id, s.hall_id, m.title, s.date, s.time, m.duration, TIMESTAMP(s.date, s.time) AS starts_at, " +
                        "TIMESTAMPADD(MINUTE, m.duration, TIMESTAMP(s.date, s.time)) AS ends_at " +
                        "FROM sessions s JOIN movies m ON s.movie_id = m.id) AS t1 " +
                        "JOIN (SELECT s.id, s.hall_id, m.title, s.date, s.time, m.duration, TIMESTAMP(s.date, s.time) AS starts_at, " +
                        "TIMESTAMPADD(MINUTE, m.duration, TIMESTAMP(s.date, s.time)) AS ends_at " +
                        "FROM sessions s JOIN movies m ON s.movie_id = m.id) AS t2 " +
                        "WHERE t1.starts_at BETWEEN t2.starts_at AND t2.ends_at AND t1.id <> t2.id AND t1.hall_id = t2.hall_id ORDER BY t1.date ASC, t1.time ASC;");

        while (resSet.next()) {
            String title1 = resSet.getString("t1.title");
            String date1 = resSet.getString("t1.date");
            String time1 = resSet.getString("t1.time");
            String duration1 = resSet.getString("t1.duration");
            String title2 = resSet.getString("t2.title");
            String date2 = resSet.getString("t2.date");
            String time2 = resSet.getString("t2.time");
            String duration2 = resSet.getString("t2.duration");
            System.out.println("дата = " + date1);
            System.out.println("время начала = " + time1);
            System.out.println("название фильма 1= " + title1);
            System.out.println("длительность = " + duration1);
            System.out.println("дата = " + date2);
            System.out.println("время начала = " + time2);
            System.out.println("название фильма 2 = " + title2);
            System.out.println("длительность = " + duration2);
            System.out.println();
        }

        System.out.println("Таблица выведена");
    }

    public static void TwoRequestDB() throws ClassNotFoundException, SQLException {

//      перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва. Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;
        resSet = statmt.executeQuery(
                "SELECT * FROM ( WITH timestamps_table AS ( SELECT s.id, s.hall_id, m.title, s.date, s.time, m.duration, TIMESTAMP(s.date, s.time) AS starts_at, TIMESTAMPADD(MINUTE, m.duration, TIMESTAMP(s.date, s.time)) AS ends_at FROM sessions s JOIN movies m ON s.movie_id = m.id WHERE s.hall_id = 2 ORDER BY s.date ASC, s.time ASC) SELECT tt.title, tt.starts_at, tt.duration, LEAD(starts_at, 1) OVER () next_session_starts_at, TIMESTAMPDIFF(MINUTE, ends_at, LEAD(starts_at, 1) OVER ()) minutes_between_sessions, CASE WHEN TIMESTAMPDIFF(MINUTE, ends_at, LEAD(starts_at, 1) OVER ()) < 0  THEN 'Наложение сеансов' WHEN TIMESTAMPDIFF(MINUTE, ends_at, LEAD(starts_at, 1) OVER ()) = 0 THEN 'Нет перерыва' WHEN TIMESTAMPDIFF(MINUTE, ends_at, LEAD(starts_at, 1) OVER ()) >= 30 THEN 'Длинный перерыв' END AS error_comment FROM timestamps_table tt) AS result_table WHERE minutes_between_sessions <=0 OR minutes_between_sessions >=30;");

        while (resSet.next()) {
            String title = resSet.getString("title");
            String startsAt = resSet.getString("starts_at");
            String duration = resSet.getString("duration");
            String nextSessionStartsAt = resSet.getString("next_session_starts_at");
            String errorComment = resSet.getString("error_comment");
            System.out.println("Название фильма = " + title);
            System.out.println("время начала = " + startsAt);
            System.out.println("длительность = " + duration);
            System.out.println("время начала второго фильма = " + nextSessionStartsAt);
            System.out.println("длительность перерыва = " + errorComment);
            System.out.println();
        }

        System.out.println("Таблица выведена");
    }

    public static void ThreeRequestDB() throws ClassNotFoundException, SQLException {

//      список фильмов, для каждого — с указанием общего числа посетителей за все время, среднего числа зрителей за сеанс и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли). Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;
        resSet = statmt.executeQuery(
                "(WITH tmp AS (SELECT s.id, s.movie_id, SUM(p.price) total_sum, COUNT(t.id) viewers FROM tickets t JOIN sessions s ON s.id = t.session_id JOIN prices p ON s.id = p.session_id GROUP BY t.session_id) SELECT m.title, SUM(viewers) total_viewers, FORMAT(AVG(viewers), 1) avg_per_session, total_sum FROM tmp JOIN movies m ON m.id = tmp.movie_id GROUP BY movie_id ORDER BY total_sum DESC) UNION SELECT 'ИТОГО', SUM(viewers), FORMAT(AVG(viewers), 1), SUM(total_sum) FROM tmp;");

        while (resSet.next()) {
            String title = resSet.getString("title");
            String totalViewers = resSet.getString("total_viewers");
            String avgPerSession = resSet.getString("avg_per_session");
            String totalSum = resSet.getString("total_sum");
            System.out.println("title = " + title);
            System.out.println("total_viewers = " + totalViewers);
            System.out.println("avg_per_session = " + avgPerSession);
            System.out.println("total_sum = " + totalSum);
            System.out.println();
        }

        System.out.println("Таблица выведена");
    }

    public static void FourRequestDB() throws ClassNotFoundException, SQLException {

//      число посетителей и кассовые сборы, сгруппированные по времени начала фильма: с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).
        resSet = statmt.executeQuery(
                "SELECT m.title, SUM(CASE WHEN s.time >= \"09:00:00\" AND s.time < \"15:00:00\" THEN 1 ELSE 0 END) AS \"09_15_viewers\", SUM(CASE WHEN s.time >= \"09:00:00\" AND s.time < \"15:00:00\" THEN p.price ELSE 0 END) AS \"09_15_fee\", SUM(CASE WHEN s.time >= \"15:00:00\" AND s.time < \"18:00:00\" THEN 1 ELSE 0 END) AS \"15_18_viewers\", SUM(CASE WHEN s.time >= \"15:00:00\" AND s.time < \"18:00:00\" THEN p.price ELSE 0 END) AS \"15_18_fee\", SUM(CASE WHEN s.time >= \"18:00:00\" AND s.time < \"21:00:00\" THEN 1 ELSE 0 END) AS \"18_21_viewers\", SUM(CASE WHEN s.time >= \"18:00:00\" AND s.time < \"21:00:00\" THEN p.price ELSE 0 END) AS \"18_21_fee\", SUM(CASE WHEN s.time >= \"21:00:00\" AND s.time <= \"23:59:59\" THEN 1 ELSE 0 END) AS \"21_00_viewers\", SUM(CASE WHEN s.time >= \"21:00:00\" AND s.time <= \"23:59:59\" THEN p.price ELSE 0 END) AS \"21_00_fee\" FROM tickets t JOIN sessions s ON s.id = t.session_id JOIN prices p ON s.id = p.session_id JOIN movies m ON s.movie_id = m.id GROUP BY m.id;");

        while (resSet.next()) {
            String title = resSet.getString("title");
            String viewers1 = resSet.getString("09_15_viewers");
            String fee1 = resSet.getString("09_15_fee");
            String viewers2 = resSet.getString("15_18_viewers");
            String fee2 = resSet.getString("15_18_fee");
            String viewers3 = resSet.getString("18_21_viewers");
            String fee3 = resSet.getString("18_21_fee");
            String viewers4 = resSet.getString("21_00_viewers");
            String fee4 = resSet.getString("21_00_fee");
            System.out.println("Название фильма = " + title);
            System.out.println("с 9 до 15 посетителей = " + viewers1);
            System.out.println("с 9 до 15 сборы = " + fee1);
            System.out.println("с 15 до 18 посетителей = " + viewers2);
            System.out.println("с 15 до 18 сборы = " + fee2);
            System.out.println("с 18 до 21 посетителей = " + viewers3);
            System.out.println("с 18 до 21 сборы = " + fee3);
            System.out.println("с 21 до 00:00 посетителей = " + viewers4);
            System.out.println("с 21 до 00:00 сборы = " + fee4);
            System.out.println();
        }

        System.out.println("Таблица выведена");
    }

    // --------Закрытие--------
    public static void closeDB() throws ClassNotFoundException, SQLException {
        conn.close();

        System.out.println("Соединения закрыты");
    }
}
