Example of SQL StoredProcedure: 

```
[root@slakade slakade]# sudo systemctl start mariadb
[root@slakade slakade]# mysql -u root -p
Enter password: 
Welcome to the MariaDB monitor.  Commands end with ; or \g.
Your MariaDB connection id is 8
Server version: 10.3.28-MariaDB MariaDB Server

Copyright (c) 2000, 2018, Oracle, MariaDB Corporation Ab and others.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

MariaDB [(none)]> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
+--------------------+
3 rows in set (0.000 sec)

MariaDB [(none)]> create database testdb;
Query OK, 1 row affected (0.000 sec)

MariaDB [(none)]> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| testdb             |
+--------------------+
4 rows in set (0.001 sec)

MariaDB [(none)]> use testdb;
Database changed
MariaDB [testdb]> create table item(id INT NOT NULL AUTO_INCREMENT, title VARCHAR(50) NOT NULL, description VARCHAR(200), price INT NOT NULL, create_date DATE, PRIMARY KEY(id));
Query OK, 0 rows affected (0.015 sec)

MariaDB [testdb]> insert into item values(1, "pencilbox", "plastic one side box", 40, '2021-06-12');
Query OK, 1 row affected (0.078 sec)

MariaDB [testdb]> insert into item values(2, "eraser", "eraset", 5, '2021-06-11');
Query OK, 1 row affected (0.004 sec)

MariaDB [testdb]> insert into item values(3, "sharpener", "sharpener", 6, '2021-06-11');
Query OK, 1 row affected (0.004 sec)

MariaDB [testdb]> insert into item values(4, "pencil", "pencil", 3, '2021-06-13');
Query OK, 1 row affected (0.005 sec)

MariaDB [testdb]> select * from item;
+----+-----------+----------------------+-------+-------------+
| id | title     | description          | price | create_date |
+----+-----------+----------------------+-------+-------------+
|  1 | pencilbox | plastic one side box |    40 | 2021-06-12  |
|  2 | eraser    | eraset               |     5 | 2021-06-11  |
|  3 | sharpener | sharpener            |     6 | 2021-06-11  |
|  4 | pencil    | pencil               |     3 | 2021-06-13  |
+----+-----------+----------------------+-------+-------------+
4 rows in set (0.000 sec)

MariaDB [testdb]> select * from item where price >= 5;
+----+-----------+----------------------+-------+-------------+
| id | title     | description          | price | create_date |
+----+-----------+----------------------+-------+-------------+
|  1 | pencilbox | plastic one side box |    40 | 2021-06-12  |
|  2 | eraser    | eraset               |     5 | 2021-06-11  |
|  3 | sharpener | sharpener            |     6 | 2021-06-11  |
+----+-----------+----------------------+-------+-------------+
3 rows in set (0.000 sec)

MariaDB [testdb]> CREATE PROCEDURE testdb.GetItems(IN cost INT)
    -> BEGIN
    -> SELECT * from item where price > cost;
ERROR 1064 (42000): You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near '' at line 3
MariaDB [testdb]> DELIMITER && 
MariaDB [testdb]> CREATE PROCEDURE testdb.GetItems(IN cost INT)
    -> BEGIN
    -> SELECT * from item where price > cost;
    -> END
    -> ;
    -> &&  
Query OK, 0 rows affected (0.001 sec)

MariaDB [testdb]> DELIMITER ;   
MariaDB [testdb]> SHOW PROCEDURE STATUS WHERE Db = 'testdb';
+--------+----------+-----------+----------------+---------------------+---------------------+---------------+---------+----------------------+----------------------+--------------------+
| Db     | Name     | Type      | Definer        | Modified            | Created             | Security_type | Comment | character_set_client | collation_connection | Database Collation |
+--------+----------+-----------+----------------+---------------------+---------------------+---------------+---------+----------------------+----------------------+--------------------+
| testdb | GetItems | PROCEDURE | root@localhost | 2021-07-15 17:50:11 | 2021-07-15 17:50:11 | DEFINER       |         | utf8                 | utf8_general_ci      | latin1_swedish_ci  |
+--------+----------+-----------+----------------+---------------------+---------------------+---------------+---------+----------------------+----------------------+--------------------+
1 row in set (0.001 sec)

MariaDB [testdb]> call GetItems(4);
+----+-----------+----------------------+-------+-------------+
| id | title     | description          | price | create_date |
+----+-----------+----------------------+-------+-------------+
|  1 | pencilbox | plastic one side box |    40 | 2021-06-12  |
|  2 | eraser    | eraset               |     5 | 2021-06-11  |
|  3 | sharpener | sharpener            |     6 | 2021-06-11  |
+----+-----------+----------------------+-------+-------------+
3 rows in set (0.001 sec)

Query OK, 0 rows affected (0.002 sec)

MariaDB [testdb]> call GetItems(5);
+----+-----------+----------------------+-------+-------------+
| id | title     | description          | price | create_date |
+----+-----------+----------------------+-------+-------------+
|  1 | pencilbox | plastic one side box |    40 | 2021-06-12  |
|  3 | sharpener | sharpener            |     6 | 2021-06-11  |
+----+-----------+----------------------+-------+-------------+
2 rows in set (0.001 sec)

Query OK, 0 rows affected (0.001 sec)

MariaDB [testdb]> call GetItems(6);
+----+-----------+----------------------+-------+-------------+
| id | title     | description          | price | create_date |
+----+-----------+----------------------+-------+-------------+
|  1 | pencilbox | plastic one side box |    40 | 2021-06-12  |
+----+-----------+----------------------+-------+-------------+
1 row in set (0.001 sec)

Query OK, 0 rows affected (0.001 sec)

MariaDB [testdb]> select * from  information_schema.routines where SPECIFIC_NAME="GetItems";
+---------------+-----------------+----------------+--------------+--------------+-----------+--------------------------+------------------------+-------------------+---------------+--------------------+--------------------+----------------+----------------+--------------+--------------------------------------------------+---------------+-------------------+-----------------+------------------+-----------------+----------+---------------+---------------------+---------------------+-------------------------------------------------------------------------------------------+-----------------+----------------+----------------------+----------------------+--------------------+
| SPECIFIC_NAME | ROUTINE_CATALOG | ROUTINE_SCHEMA | ROUTINE_NAME | ROUTINE_TYPE | DATA_TYPE | CHARACTER_MAXIMUM_LENGTH | CHARACTER_OCTET_LENGTH | NUMERIC_PRECISION | NUMERIC_SCALE | DATETIME_PRECISION | CHARACTER_SET_NAME | COLLATION_NAME | DTD_IDENTIFIER | ROUTINE_BODY | ROUTINE_DEFINITION                               | EXTERNAL_NAME | EXTERNAL_LANGUAGE | PARAMETER_STYLE | IS_DETERMINISTIC | SQL_DATA_ACCESS | SQL_PATH | SECURITY_TYPE | CREATED             | LAST_ALTERED        | SQL_MODE                                                                                  | ROUTINE_COMMENT | DEFINER        | CHARACTER_SET_CLIENT | COLLATION_CONNECTION | DATABASE_COLLATION |
+---------------+-----------------+----------------+--------------+--------------+-----------+--------------------------+------------------------+-------------------+---------------+--------------------+--------------------+----------------+----------------+--------------+--------------------------------------------------+---------------+-------------------+-----------------+------------------+-----------------+----------+---------------+---------------------+---------------------+-------------------------------------------------------------------------------------------+-----------------+----------------+----------------------+----------------------+--------------------+
| GetItems      | def             | testdb         | GetItems     | PROCEDURE    |           |                     NULL |                   NULL |              NULL |          NULL |               NULL | NULL               | NULL           | NULL           | SQL          | BEGIN
SELECT * from item where price > cost;
END | NULL          | NULL              | SQL             | NO               | CONTAINS SQL    | NULL     | DEFINER       | 2021-07-15 17:50:11 | 2021-07-15 17:50:11 | STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION |                 | root@localhost | utf8                 | utf8_general_ci      | latin1_swedish_ci  |
+---------------+-----------------+----------------+--------------+--------------+-----------+--------------------------+------------------------+-------------------+---------------+--------------------+--------------------+----------------+----------------+--------------+--------------------------------------------------+---------------+-------------------+-----------------+------------------+-----------------+----------+---------------+---------------------+---------------------+-------------------------------------------------------------------------------------------+-----------------+----------------+----------------------+----------------------+--------------------+
1 row in set (0.003 sec)
___________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________
___________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________
___________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________


