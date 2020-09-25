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
      <property name="javax.persistence.jdbc.url" value="jdbc:mysql://...?serverTimezone=UTC&amp;useSSL=false&amp;allowPublicKeyRetrieval=true"/> -- обязательно serverTimezone указать и useSSL == false, allowPublicKey... должен быть при получении метамодели
      <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect"/>
      <property name="javax.persistence.jdbc.user" value="..."/>
      <property name="javax.persistence.jdbc.password" value="..."/>
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

Для подключения javax.beanvalidation - подключить
    <dependency>
      <groupId>org.hibernate</groupId>
      <artifactId>hibernate-validator</artifactId>
      <version>5.2.1.Final</version>
    </dependency>
    <dependency>
      <groupId>javax.el</groupId>
      <artifactId>javax.el-api</artifactId>
      <version>2.2.4</version>
    </dependency>

--------------------------------------------------------------------------------------------------------------------------
Достаточный для работы hibernate.cfg.xml

<?xml version='1.0' encoding='utf-8'?>
<!DOCTYPE hibernate-configuration PUBLIC
  "-//Hibernate/Hibernate Configuration DTD//EN"
  "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
  <session-factory>
    <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
    <property name="connection.url">jdbc:mysql://localhost:3306/HibernateTestDB?serverTimezone=UTC&amp;useSSL=false&amp;allowPublicKeyRetrieval=true</property>
    <property name="connection.driver_class">com.mysql.cj.jdbc.Driver</property>
    <property name="connection.username">root</property>
    <property name="connection.password">root</property>
    <property name="show_sql">true</property>

    <mapping class="org.jpwh.model.helloworld.Message"/>

    <!-- DB schema will be updated if needed -->
  </session-factory>
</hibernate-configuration>

--------------------------------------------------------------------------------------------------------------------------
Каждое взаимодействие с базой должно быть внутри явно заданной открытой транзакции.
Когда классы сохраняются в httpSession - они должны быть Сериализуемыми.
Ни хранимые классы, ни их методы не должны быть final
Для коллекций в getter и setter проверяется идентичность, для примитивных типов и String - только значение
В методе добавление связи (ассоциации) первой сущности ко второй, нужно в этом же методе добавлять связь вторая к первой (обратная) - это целостность данных
Hibernate может использовать только конструктор без параметров или по умолчанию
Hibernate во время запуска читает классы и метаданные с помощью рефлексии
Hibernate-аннотации лучше писать с полным пакетным путём без import (@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE))
EntityManager - это из JPA, Session это из Hibernate
Аннотации из Hibernate @DynamicInsert и DynamicUpdate - формирование insert и update запросов не сразу после запуска, а на рантайме
@Immutable - неизменяемая сущность (экземпляры никогда не обновляются)
Не выполняется синхронизация перед entityManager.find, только перед выполнением запроса Query
@Subselect обязательно нужно потом сделать @Synchronize
@Embedded - для конкретного поля и @Embeddable для класса (ВСТАРИВАЕМЫЙ) - встроенный компонент класса-владельца (пользовательский тип)
@Transient - поле, которое не будет хранимым и не будет сохраняться в БД
@Basic(optional = false): optional = false - поле необязательное
@Column(name = "START_PRICE", nullable = false) - тот же самый Basic, но используют её. Name - переопределение имени столбца
