--------------------------------------------------------------------------------------------------------------------------
Достаточный persistence.xml для работы

<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence
             http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd"
             version="2.1">
  <persistence-unit name="какое-то имя"> <!--Persistence unit - единица хранения - это отображение классов предметной области и соединение с БД-->
    <class>...</class>
    <exclude-unlisted-classes>true</exclude-unlisted-classes> <!--ЭТО НУЖНО ДЛЯ ТОГО, чтобы подтягивались только классы, указанные тут в class-->
    <properties>
      <property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/> -- обязательно этот драйвер
      <property name="javax.persistence.jdbc.url" value="jdbc:mysql://...?serverTimezone=UTC&amp;useSSL=false&amp;allowPublicKeyRetrieval=true"/> -- обязательно serverTimezone указать и useSSL == false, allowPublicKey... должен быть при получении метамодели
      <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect"/>
      <property name="javax.persistence.jdbc.user" value="..."/>
      <property name="javax.persistence.jdbc.password" value="..."/>
      <property name="hibernate.hbm2ddl.auto" value="create"/> <!--АВТОГЕНЕРАЦИЯ ТАБЛИЦ ВООБЩЕ ВСЕГДА. ТО ЕСТЬ СОЗАДЮТСЯ ВСЕГДА НОВЫЕ-->
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
    <property name="hibernate.hbm2ddl.auto">create</property> <!--АВТОГЕНЕРАЦИЯ ТАБЛИЦ ВООБЩЕ ВСЕГДА. ТО ЕСТЬ СОЗАДЮТСЯ ВСЕГДА НОВЫЕ-->

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
EntityManager - это стандартизованный API из JPA (наподобие JDBC API), Session это из Hibernate
Аннотации из Hibernate @DynamicInsert и DynamicUpdate - формирование insert и update запросов не сразу после запуска, а на рантайме
@Immutable - неизменяемая сущность (экземпляры никогда не обновляются)
Не выполняется синхронизация перед entityManager.find, только перед выполнением запроса Query
@Subselect обязательно нужно потом сделать @Synchronize
@Embedded - для конкретного поля и @Embeddable для класса (ВСТАРИВАЕМЫЙ, отсутствует ID) - встроенный компонент класса-владельца (пользовательский тип) (Например тип Address)
@Transient - поле, которое не будет хранимым и не будет сохраняться в БД
@Basic(optional = false): optional = false - поле необязательное
@Column(name = "START_PRICE", nullable = false) - тот же самый Basic, но используют её. Name - переопределение имени столбца
    @Column(nullable = false) - используется при генерации DDL
@Formula - для вычисляемого поля
@ColumnTranformer - допустим в БД значение в фунтах, а надо читать и записывать в метрах => (read "COLUMN_SRC" / 2.20462 write (? * 2.20462)) - вычисление выполняется на уровне БД
Для автогенерации значения - @Generated(тут указывается GenerationTime: Always - генерация при каждом INSERT и UPDATE, Insrert - понятно) + @Column(insertable = false, updatable = false)
Можно задать значение по умолчанию @ColumnDefault
Если поле в классе типа Date, нужно с помощью @Temporal задать конкретный тип даты (Дата, время или timestamp), который нужно сохранять в БД, пример: @Temporal(TemporalType.TIMESTAMP)
    по умолчанию в Hibernate тип даты - timestamp (если не использовать @Temporal)
@CreationTimestamp - аналогично @Generated присваивает значение текущего timestamp при вставке. Есть аналогичная @UpdateTimestamp - при обновлении.
@Enumerated - для поля с типом enum, по умолчанию сохранится ORDINAL (то есть просто номер элемента в enum). @Enumerated(EnumType.STRING) - сохранится строковое значение
!!!!@NotNull - это не NOT NULL в БД, это имеено проверка при beanValidations, это баг hibernate
    Чтобы в схеме было NOT NULL, нужно @Column(nullable = false)
@Access: @Access(AccessType.FIELD) - все аннотации должны быть над полями непосредственно. @Access(AccessType.PROPERTY) - все аннотации должны быть над геттерами
Сравнение методов persist и save в hibernate session:
1) save():
     Возвращает сгенерированный идентификатор после сохранения. Его возвращаемый тип Serializable;
     Сохраняет изменения в базе данных вне транзакции;
     Назначает сгенерированный идентификатор сохраняемой сущности;
     session.save() для отдельного объекта создаст новую строку в таблице.
2) persist():
     Не возвращает сгенерированный идентификатор после сохранения. Его возвращаемый тип void;
     Не сохраняет изменения в базе данных вне транзакции;
     Назначает сгенерированный идентификатор сохраняемой сущности;
     session.persist()для отдельного объекта бросит PersistentObjectException, поскольку это не разрешено.

Если в классе несколько полей встраиваемого типа, хотя может быть только одно поле встраиваемого типа - конфликт отображения
Чтобы можно было добавить несколько полей встраиваемого типа, нужно @AttributeOverrides, который переопределит во втором объекте встраиваемого типа имена встраиваемых колонок (см. класс User)

BLOB — большой двоичный объект. Переменная этого типа содержит локатор LOB, указывающий на большой двоичный объект, хранящийся в базе данных.
CLOB — большой символьный объект. Переменная этого типа содержит локатор LOB, указывающий на хранящийся в базе данных большой блок текстовых данных в наборе символов базы данных.

@Converter - конвертер класса (который implements Serializable) в значение в БД. и всегда конвертер implements AttributeConverter<SomeClass, String> -
если классу SomeClass соответствует строка в БД.
  У конвертируемого класса обязательно должен быть toString переопределенный.
  Так же должны быть @equals и @hashcode, чтобы сравнивать объекты "по значению"
  @Converter(autoApply=true) - автоматически применяется к полям типа SomeClass

Наследование:
  можно использовать в суперклассе @MappedSuperclass, но тогда его поле должно в каждом подклассе переопределяться через @AttributeOverride
  можно в суперклассе @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) - для селекта сущности суперкласса делается селект из всех его потомков с UNION:
    select * from (select * from child1 union all select * from child2) as Parent
    + ко всему при union должны быть одинаковые столбцы, поэтому нужно явно дополнять несуществующие столбцы в одном, а существующие в другом через select null as Столбец1
  Самый производительный способ - для всех подклассов создать одну таблицу, которая будет содержать все столбцы. Но тут проблема в целостности, та как во многих столбцах будет null
    + тут проблема в нормализации: зависимости между неключевыми стобцами
    Нужно определять столбец селектора для нужд Hibernate через @DiscriminatorColumn(name = "BD_TYPE"), а у каждого класса-потомка будет @DiscriminatorValue("Some")
  3) Наследование через join: таблица предка содержит id, а все сыновья имеют тоже id , но он и PK, и FK одновременно (тут и нормализация, и целостность)
    @Inheritance(strategy = InheritanceType.JOINED), но потом в потомках можно указать, а можно и нет, @PrimaryKeyJoinColumn(name = "CHILD_ID")
    Для селекта родителя используется join всех сыновей, выбор всех полей всех сыновей, а там, где получится null, то там через case when задавать ккие-то суррогатные значения для того, чтобы понять, то тут было Null
  4) Смешивание стратегий: если к единой таблице всё же добавить ещё одну какую-то, отвечающую за какую-то сущность, то в классе этой сущности нужно:
     @SecondaryTable(name = "SOMETABLE", pkJoinColumns = @PrimaryKeyJoinColumn(name = "SOMETABLE_ID")), это переопределит для данной сущности её таблицу в стратегии единой таблицы

Если есть встраиваемые классы EmbChild1 и EmbChild2, которые наследуются от EmpParent, то ни в коем случае нельзя в хранимой сущности иметь поле типа EmpParent, только конкретные ссылки
  (полиморфные ассоциации встраиваемых классов не поддерживаются)
Рекомендации по выбору стратегии:
  1) если полиморфные ассоциации не  требуются (не нужно делать select * from parent) - нужно выбрать InheritanceType.TABLE_PER_CLASS
  2) если требуются полиморфные ассоциации и разница между подклассами в основном в поведении, а не в данных, то это InheritanceType.SINGLE_TABLE, то есть только для простых проблем
  3) если требуются полиморфные ассоциации и разница между подклассами в данных, обязательных к заполнению - InheritanceType.JOINED + тут нормализация

@ManyToOne(fetch = FetchType.LAZY) - ассоциация будет представлена прокси-объектом, только после вызова будет получен объект типа Сына.
EntityManager.getReference - из прокси-объекта парента получить  прокси-объект объект сына: Child ch = em.getReference(Child.class, parent.getId()); getid у парента тут вызовет select.
--Этих проблем можно избежать с помощью ухода от отложенной загрузки

ON DELETE CASCADE - если удаляется запись, которая являлась где-то FK, то и запись с FK тоже удалится

@ElementCollection - коллекция экземпляров типов-значений, если хотим переопределить имя таблицы - @CollectionTable
ElementCollection позволяет задавать политики отображения для необычных в JPA 1.0 видов коллекций: коллекций embeddable объектов, либо коллекций «простых» типов (Integer, String и т.д.). Также это компонент используется при в определениях отношений с Map, в роли ключа которого выступают любого рода объекты, а в роли значения — embeddable или «простые» объекты.
Значения ElementCollection всегда хранятся в отдельных таблицах, которые задаются аннотацией @CollectionTable. CollectionTable отпределяет имя таблицы и @JoinColumn или @JoinColumns в случае составного первичного ключа.
Когда отображается коллекция, то первичный ключ в таблице будет id главной сущности + значения всех столбцов, которые не могут быть null
@CollectionId - для Collection(почему-то называется Bag) генерирует суррогатный Id в БД
аннотации MapKeyColumn и AttributeOverrides могут не использоваться, когда роль ключа играет встраиваемый класс

Если коллекция - список, то можно сохранять элементы  порядке добавления (по умолчанию это хранимые индексы (просто числа, номера порядка добавления)) или в определенном порядке
Если нужно, чтобы была Map: для отображения ключа @MapKeyColumn(name = "KEYCOLUMNNAME"), значение - @Column(name = "IMAGENAME")
Сортировка - это при использовании java-компараторов, а упорядоченные - это order by из БД
@SortComparator(SomeComparator.class) - сортирует коллекцию, но не изменяет при этом схему БД
@OrderBy из hibernate поддерживает разные (и ASC, и DESC)
Обязательно во всех классах, которые представлены коллекциями, должны быть переопределены hashcode и equals и compareTo

JDBC - это стандарт доступа к базам данных, JPA - это стандарт персистентности, Hibernate - это реализующий его ORM, Spring Data -
 это механизм организации репозиториев, а репозиторий - это абстракция, лежащая на уровень выше ORM. То есть Spring Data использует Hibernate, а Hibernate использует JDBC.

cascade = CascadeType.PERSIST - все связанные сущности с данной будут сохранены (вызовется persist) в момент коммита транзакции. У EntityManager можно сразу persist сущность, а потом добавлять связанные, они тоже сохранятся. А при session нужно сперва добавить все связанные и потом сохранить нужную сущность, связанные тоже сохранятся.
orphanRemoval = true - аргумент должен быть удалён из БД, если он удалился из коллекции
Чтобы в схеме БД было ON DELETE CASCADE, нужно вставить @OnDelete(action = OnDeleteAction.CASCADE) - при удалении сущности все связанные с ней сущности тоже будут удалены.
Нужно OnDelete ставить на той стороне связи, где mappedBy и OneToMany!!!! - ДЛЯ ДВУНАПРАВЛЕННЫХ СВЯЗЕЙ, если не двунапрапвленная, то только где ManyToOne!!!!!!

@Id
@GeneratedValue(generator = "addressKeyGenerator")  При сохранении данного экземпляра класса этот генератор извлекает id из сущности user, у которого обязательно должен быть обычный генератор
@GenericGenerator(name = "addressKeyGenerator", strategy = "foreign",
			parameters = @Parameter(name = "property", value = "user"))
protected long id;
@PrimaryKeyJoinColumn - общий первичный ключ
@JoinColumn можно воспринимать как просто столбец внешнего ключа

@JoinTable(name = "ITEM1_ITEM2", //имя связующей таблицы для one-to-one (если обе сущности имеют связь с этой таблицей 1 к 1)
	        joinColumns = @JoinColumn(name = "ITEM1_ID"), // переопределение имени id-шки ITEM1
			inverseJoinColumns = @JoinColumn(name = "ITEM2_ID")) //переопределние имени id-шки ITEM2

Когда поле представленно Bag (Collection), то при добавлении ноового элемента контейнера не загрузится вся коллекция, а если
поле представлено Set или List - тогда перед вставкой нового элемента вся коллекция загружается, а потом новый элемент вставляется