# PetagramRecyclerView

En el presente repositorio está contenida la tarea de la semana 4 del tercer curso de Android de la Universidad Autónoma de México.

El fragment de profile si lo hard-codié porque me estaba retrasando mucho. Disculpe lo no tan preciso.



Otras notas:

## Arquitectura

La arquitectura MVC es la que por defecto se utiliza, sin embargo, una opción recomendada es la arquitectura Model View ViewModel (MVVM) o Modelo Vista Presentador. En la arquitectura MVP, el Modelo corresponde a los datos o clases de las estructuras de datos utilizados (POJOs), la Vista por su parte, está constituida por el layout y por la clase en Java de la actividad en cuestión. Finalmente, el presentador se encarga de interactuar con los datos, también es llamado interceptor.

Por ejemplo:
```Java
  public class Dog{
    //   Este es el pojo o clase custom que
    // representa los datos que se verán en la vista.
      String name;
      Dog(String name){
        this.name = name;
      }
  }
```

```Java
public class ConstructorDogs{
  // En esta clase van todos los métodos que leen de la base de datos
  // Los datos necesarios para la vista.

  private Context context;
  public ConstructorDogs(Context context){
    this.context = context;
  }


    public ArrayList<Dog> obtenerDatos(){
      // Aquí se ejecutaría el método del query para obtener los datos de la
      // base de datos

      // Donde BaseDatos es una clase para manejar la base de datos SQL;
      BaseDatos db = new BaseDatos(context);
      insertarDosContactos(db);
      return db.obtenerModeloDatosDeBaseDeDatos();

    }

    public void insertarDosContactos(BaseDatos db){
      // Este es un método de ejemplo para insertar tres elementos
      ContentValues contentValues = new ContentValues();
      contentValues.put(ConstantesBaseDatos.TABLE_NAME_ENTITYNAME_NAME, "Dummy name");
      // Aquí irían los otros atributos de la tabla:
      //contentValues.put(ConstantesBaseDatos.TABLE_NAME_ENTITYNAME_NAME2, "Dummy name2");
      db.insertarPOJOClassObject(contentValues);

      // Se inserta entonces el siguiente elemento o row
      ContentValues contentValues = new ContentValues();
      contentValues.put(ConstantesBaseDatos.TABLE_NAME_ENTITYNAME_NAME, "Dummy name otro");
      // Aquí irían los otros atributos de la tabla:
      //contentValues.put(ConstantesBaseDatos.TABLE_NAME_ENTITYNAME_NAME2, "Dummy name otro 2");
      db.insertarPOJOClassObject(contentValues);
    }
}




```

## Base de datos

Para la persistencia de datos en las aplicaciones Android una opción de bastante libertad es utilizando el motor SQLite incluido.

Para utilizar SQLite necesitamos primeramente una clase que herede de SQLiteOpenHelper y que implemente varios métodos:

```Java
public class BaseDatos extends SQLiteOpenHelper{
  @Override
  public BaseDatos(Context Context){
    // Constructor de la clase, recibe el contexto
    // name: Nombre de la base de datos
    // factory: No lo menciona la instructora en el curso
    // version: Versión de la base de datos
    String name = ConstantesBaseDatos.DATABASE_NAME;
    int version = ConstantesBaseDatos.DATABASE_VERSION;
    super(context, name, factory, version);
  }

  @Override
  public void onCreate(SQLiteDatabase db){
    // Aquí se crea la estructura de la db
    // CREATE TABLE...
    String query = "CREATE TABLE " + ConstantesBaseDatos.DATABASE_NAME + "(" +
    ConstantesBaseDatos.TABLE_NAME_ENTITYNAME_ID +
    " INTEGER PRIMARY KEY AUTOINCREMENT" + ConstantesBaseDatos.TABLE_NAME_ENTITYNAME_NAME + ")" ;

    // Luego que se tiene el query de crear table:
    db.execSQL(query);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
      // Este método se utiliza para migración entre versiones.

      // Si por ejemplo consigue una versión
      // vieja. Podemos eliminar la tabla
      db.execSQL("DROP TABLE IF EXIST" + ConstantesBaseDatos.TABLE_NAME);
      // Y entonces podemos crear la nueva base de datos
      onCreate(db);
  }

  // Aquí iría la función de obtener la lista de los datos de db
  public ArrayList<POJOClass> obtenerModeloDatosDeBaseDeDatos(){
    ArrayList<POJOClass> pojo = new ArrayList<>();

    // Aquí va el query para obtener los datos de la table
    String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_NAME;
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor registros = db.rawQuery(query, null);

    // Aquí se pasan registros al pojo
    while (registros.moveToNext()){
      POJOClass example = new POJOClass();
      // Este corresponde al primer campo proveniente de la tabla
      example.setAttribute1(registros.getString(0));
      // Segundo campo
      example.setAttribute2(registros.getString(1));

      pojo.add(example);
    }
     // Hay que cerrar la conexion para evitar errores:
     db.close()
    return pojo;
  }

  public void insertarPOJOClassObject(ContentValues contentValues){
    // En este método se insertan elementos en la base de datos
    db.insert(ConstantesBaseDatos.TABLE_NAME, null, contentValues);
    db.close();
  }

}
```

Por otra parte se recomienda el uso de una clase que contiene constantes:

```Java

public class ConstantesBaseDatos{
  public static final String DATABASE_NAME = "database_name";
  public static final int DATABASE_VERSION = 1;
  public static final String TABLE_NAME = "tableName";
  public static final String TABLE_NAME_ENTITYNAME_ID = "tableEntityName";
  public static final String TABLE_NAME_ENTITYNAME_NAME = "tableEntityNameOtherAttribute";
}

```
