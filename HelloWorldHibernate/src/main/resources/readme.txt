--------------------------------------------------------------------------------------------------------------------------
Достаточный persistance.xml для работы

<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence
             http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd"
             version="2.1">
  <persistence-unit name="какое-то имя"> <!--Persistence unit - единица хранения - это отображение классов предметной области и соединение с БД-->
    <class>...</class>
    <properties>
      <property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/> -- обязательно этот драйвер
      <property name="javax.persistence.jdbc.url" value="jdbc:mysql://...?serverTimezone=UTC"/> -- обязательно serverTimezone указать
      <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect"/>
      <property name="javax.persistence.jdbc.user" value="..."/>
      <property name="javax.persistence.jdbc.password" value="..."/>
      <property name="javax.persistence.schema-generation.database.action" value="drop-and-create"/>
    </properties>
  </persistence-unit>
</persistence>

--------------------------------------------------------------------------------------------------------------------------

Достаточный pom.xml

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>...</groupId>
  <artifactId>...</artifactId>
  <version>1.0-SNAPSHOT</version>

  <dependencies>
    <dependency>
      <groupId>org.hibernate</groupId>
      <artifactId>hibernate-entitymanager</artifactId>
      <version>5.0.0.Final</version>
    </dependency>
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId> -- коннектор обязательно (без него не работает)
      <version>8.0.15</version>
    </dependency>
  </dependencies>
</project>
--------------------------------------------------------------------------------------------------------------------------