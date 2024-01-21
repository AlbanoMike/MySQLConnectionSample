Baixe e instale o banco de dados MySQL
Crie um banco de dados em MySQL
Baixe o driver JDBC e inclua-o no Classpath
Conecte-se com MySQL
Teste a conexão JDBC
Conecte um banco de dados MySQL em Java

Este tutorial apresenta como conectar um banco de dados MySQL em Java. Também listamos alguns códigos de exemplo para que você possa entender melhor este tópico.

Para conectar o aplicativo Java ao banco de dados Mysql, precisamos seguir algumas etapas listadas abaixo:

Baixe e instale o MySQL
Crie um banco de dados em MySQL
Baixe o driver JDBC e coloque-o no classpath
Escreva o código Java para conectividade
Teste a conexão
Vamos entender o procedimento passo a passo aqui:


Baixe e instale o banco de dados MySQL
MySQL é um sistema de gerenciamento de banco de dados e presumimos que você já o tenha instalado. Vamos para a próxima etapa.

Crie um banco de dados em MySQL
Agora, vamos criar um banco de dados para que possamos testar a conexão. Para criar um banco de dados em MySQL, use a consulta SQL abaixo:


create database delftstackDB;
Depois de criar um banco de dados, lembre-se dele porque você o usará na parte de conectividade.

Baixe o driver JDBC e inclua-o no Classpath
O driver JDBC é um arquivo JAR fornecido pelo MySQL; é um conector que atua como uma ponte entre os aplicativos MySQL e Java.
 Para baixar o arquivo JAR visite o site oficial do MySQL e coloque os arquivos na pasta lib do seu projeto java. Agora, prossiga para a próxima etapa.

Conecte-se com MySQL
Depois de concluir os procedimentos acima, escreva o código Java para conectividade. Aqui, usamos o método class.forName() para carregar o JDBC Driver,
 que baixamos do site oficial do MySQL.

jdbc:mysql://localhost:3306/pessoa?useTimezone=true&serverTimezone=UTC","root", "root" (um detalhe, o usuário e senha tem que ser o configurado no momento
da instalação do mysql)

O método getConnection() é usado para passar a string de conexão: MySQL:Port/Database/,username,dbpassword. Esta string é usada para autenticar o usuário
e fornecer acesso apenas a usuários autorizados. Depois disso, usamos o método createStatement() para criar uma instância que será usada para executar
consultas SQL usando o código. Veja o exemplo abaixo:

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class SimpleTesting {
  public static void main(String[] args) {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/delftstackDB", "username", "dbPassword");
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("show databases;");
      System.out.println("Connected");
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}

Teste a conexão JDBC

Depois de escrever o código, basta executá-lo. Se o código funcionar bem, você obterá a seguinte saída no console:

Resultado:

Connected
