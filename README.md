# Memòria del Proyecto
## Proyecto Generador de Web Estática
**Curso**: 2DAM  
**Alumno**: Iker Perez Mata

---

### Índice
1. [Descripción del proyecto](#descripción-del-proyecto)
2. [Ficheros de entrada](#ficheros-de-entrada)
   - [Archivo INI](#archivo-ini)
   - [Archivo JSON](#archivo-json)
   - [Archivo JSON-schema](#archivo-json-schema)
3. [Descripción de librerías, clases y dependencias](#descripción-de-librerías-clases-y-dependencias)
4. [Descripción de las plantillas Thymeleaf](#descripción-de-las-plantillas-thymeleaf)
5. [Ficheros de salida](#ficheros-de-salida)
6. [Capturas de las páginas creadas](#capturas-de-las-páginas-creadas)

---

### Descripción del proyecto
La finalidad del proyecto es hacer un generador de una página web estática en **IntelliJ**, que mediante plantillas de **Thymeleaf** genera archivos **HTML**. La aplicación es capaz de mostrar tipos de Pokémon, con sus respectivas descripciones e imágenes, y permite visualizar los Pokémon de cada tipo.

---

### Ficheros de entrada
#### Archivo INI
Ejemplo de archivo INI:


nom=Pokemons
descripcio=Vista de tipos y sus pokemons
#### Archivo JSON
Ejemplo de una parte del archivo JSON que contiene la información de los tipos de Pokémon:

```json
{
  "types": [
    {
      "name": "Acero",
      "descrip": "Conocidos por su gran defensa y resistencia, estos Pokémon están hechos de metal o lo manipulan.",
      "img": "../src/main/resources/img/acero.png"
    },
    {
      "name": "Agua",
      "descrip": "Controlan el agua y suelen habitar en ríos, océanos o ambientes húmedos.",
      "img": "../src/main/resources/img/agua.png"
    },
    {
      "name": "Bicho",
      "descrip": "Pequeños pero ágiles, estos Pokémon son conocidos por su adaptación al entorno.",
      "img": "../src/main/resources/img/bicho.png"
    },
    {
      "name": "Dragón",
      "descrip": "Representan poder y majestuosidad; muchos son legendarios.",
      "img": "../src/main/resources/img/dragon.png"
    },
    {
      "name": "Eléctrico",
      "descrip": "Generan electricidad para atacar o protegerse; son rápidos y dinámicos.",
      "img": "../src/main/resources/img/electrico.png"
    },
    {
      "name": "Fantasma",
      "descrip": "Misteriosos y etéreos, suelen habitar lugares oscuros y desolados.",
      "img": "../src/main/resources/img/fantasma.png"
    },
    {
      "name": "Fuego",
      "descrip": "Manipulan el fuego y el calor; simbolizan pasión y agresividad.",
      "img": "../src/main/resources/img/fuego.png"
    },
    {
      "name": "Hada",
      "descrip": "Encantadores y mágicos, destacan en la lucha contra dragones.",
      "img": "../src/main/resources/img/hada.png"
    },
    {
      "name": "Hielo",
      "descrip": "Especialistas en el frío extremo, suelen habitar regiones heladas.",
      "img": "../src/main/resources/img/hielo.png"
    }
  ]
}
```
Archivo Json-schema:
```json

{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "description": "los 18 tipos de pokemon",
  "type": "object",
  "properties": {
      "types" : {
        "type": "array",
        "uniqueItems": true,
        "minItems": 1,
        "items": {
          "required": [
            "name",
            "descrip",
            "img"
          ],
          "properties": {
            "name" : {
              "type": "string",
              "minLength": 1
            },
            "descrip": {
              "type": "string",
              "minLength": 1
            },
            "img": {
              "type": "string",
              "minLength": 1
            }
          }
        }
      },
      "pokemon" : {
        "type": "array",
        "uniqueItems": true,
        "minItems": 1,
        "items": {
          "required": [
            "type",
            "name",
            "pokedex"
          ],
          "properties": {
            "type" : {
              "type": "string",
              "minLength": 1
            },
            "name" : {
              "type": "string",
              "minLength": 1
            },
            "pokedex" : {
              "type": "integer",
              "minLength": 0
            }
          }
        }
      }
  },
  "required": ["types","pokemon"]
}
```
## Descripción de librerías, clases y dependencias

### Librerías:
- **thymeleaf**: Esta librería se ha añadido para poder crear las plantillas de los archivos HTML.
- **jackson-databind** y **jackson-core**: Se han añadido para generar los POJOs en las clases.
- **slf4j-api**: Esta librería se añadió para resolver un error en la terminal relacionado con la configuración de logging.
- **json-schema-validator**: Se utiliza para validar el archivo JSON con el schema proporcionado.

### Clases:
- **Data.kt**: Esta clase contiene los POJOs que permiten obtener los datos de cada elemento (por ejemplo, los tipos de Pokémon y sus características).
- **Thymeleaf.kt**: Es la clase principal que ejecuta el `main`, carga las plantillas y genera las páginas HTML.

### Dependencias:
```xml
<dependencies>
    <dependency>
        <groupId>org.thymeleaf</groupId>
        <artifactId>thymeleaf</artifactId>
        <version>3.1.2.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.18.1</version>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-core</artifactId>
        <version>2.18.1</version>
    </dependency>
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>1.7.32</version>
    </dependency>
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.2.6</version>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.module</groupId>
        <artifactId>jackson-module-kotlin</artifactId>
        <version>2.18.1</version>
    </dependency>
    <dependency>
        <groupId>org.jetbrains.kotlin</groupId>
        <artifactId>kotlin-stdlib-jdk8</artifactId>
        <version>${kotlin.version}</version>
    </dependency>
    <dependency>
        <groupId>org.jetbrains.kotlin</groupId>
        <artifactId>kotlin-test</artifactId>
        <version>${kotlin.version}</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```
    
## Descripción de las plantillas Thymeleaf

### Plantilla de `index.html`
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Pagina Principal - Pokemon</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<header>
    <h1>Tipos de Pokemon</h1>
</header>

<main>
    <section>
        <h2>Lista de Tipos</h2>
        <ul>
            <li th:each="tipo : ${types}">
                <h3 th:text="${tipo.name}"></h3>
                <div>
                    <img th:src="@{${tipo.img}}" alt="IMAGEN" width="5%">
                </div>
                <p th:text="${tipo.descrip}">Descripcion del tipo</p>
                <a th:href="@{'Tipos/tipo_' + ${tipo.name} + '.html'}" class="btn-ver-pokemon">Ver pokemons de este tipo</a>
            </li>
        </ul>
    </section>
</main>
</body>
</html>
```

### Plantilla de los detalles de cada tipo.
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
  <meta charset="UTF-8">
  <title th:text="${tipo.name}">Tipo Pokémon</title>
  <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<header>
  <h1 th:text="'Pokémon de tipo ' + ${tipo.name}"></h1>
</header>

<main>
  <section>
    <h2>Descripción</h2>
    <p>
      <span th:text="${tipo.descrip}"></span>
    </p>

    <h2>Pokémon</h2>
    <ul>
      <li th:each="pokemon : ${pokemon}">
        <p>
          <span th:text="${pokemon.pokedex}"></span> -
          <span th:text="${pokemon.name}"></span>
        </p>
      </li>
    </ul>
  </section>

  <a th:href="@{../index.html}" class="btn">Volver</a>

</main>
</body>
</html>
```

### Ficheros de salida.
rss.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<rss version="2.0">
    <channel>
        <title>Tipos de Pokémon</title>
        <link>src/main/resources/html/index.html</link>
        <description>Explora los diferentes tipos de Pokémon y conoce sus habilidades y características.</description>
        <language>es</language>

        <item>
            <title>Acero</title>
            <link>src/main/resources/html/Tipos/tipo_Acero.html</link>
            <description>Conocidos por su gran defensa y resistencia, estos Pokémon están hechos de metal o lo manipulan.</description>
        </item>

        <item>
            <title>Agua</title>
            <link>src/main/resources/html/Tipos/tipo_Agua.html</link>
            <description>Controlan el agua y suelen habitar en ríos, océanos o ambientes húmedos.</description>
        </item>

        <item>
            <title>Bicho</title>
            <link>src/main/resources/html/Tipos/tipo_Bicho.html</link>
            <description>Pequeños pero ágiles, estos Pokémon son conocidos por su adaptación al entorno.</description>
        </item>

        <item>
            <title>Dragón</title>
            <link>src/main/resources/html/Tipos/tipo_Dragon.html</link>
            <description>Representan poder y majestuosidad; muchos son legendarios.</description>
        </item>

        <item>
            <title>Eléctrico</title>
            <link>src/main/resources/html/Tipos/tipo_Electrico.html</link>
            <description>Generan electricidad para atacar o protegerse; son rápidos y dinámicos.</description>
        </item>

        <item>
            <title>Fantasma</title>
            <link>src/main/resources/html/Tipos/tipo_Fantasma.html</link>
            <description>Misteriosos y etéreos, suelen habitar lugares oscuros y desolados.</description>
        </item>

        <item>
            <title>Fuego</title>
            <link>src/main/resources/html/Tipos/tipo_Fuego.html</link>
            <description>Manipulan el fuego y el calor; simbolizan pasión y agresividad.</description>
        </item>

        <item>
            <title>Hada</title>
            <link>src/main/resources/html/Tipos/tipo_Hada.html</link>
            <description>Encantadores y mágicos, destacan en la lucha contra dragones.</description>
        </item>

        <item>
            <title>Hielo</title>
            <link>src/main/resources/html/Tipos/tipo_Hielo.html</link>
            <description>Especialistas en el frío extremo, suelen habitar regiones heladas.</description>
        </item>

        <item>
            <title>Lucha</title>
            <link>src/main/resources/html/Tipos/tipo_Lucha.html</link>
            <description>Expertos en combate físico, representan fuerza y disciplina.</description>
        </item>

        <item>
            <title>Normal</title>
            <link>src/main/resources/html/Tipos/tipo_Normal.html</link>
            <description>Versátiles y equilibrados, suelen ser los más comunes.</description>
        </item>

        <item>
            <title>Planta</title>
            <link>src/main/resources/html/Tipos/tipo_Planta.html</link>
            <description>Representan la naturaleza y la vida; suelen usar el sol para fortalecerse.</description>
        </item>

        <item>
            <title>Psíquico</title>
            <link>src/main/resources/html/Tipos/tipo_Psiquico.html</link>
            <description>Poseen poderes mentales y ataques basados en la concentración.</description>
        </item>

        <item>
            <title>Roca</title>
            <link>src/main/resources/html/Tipos/tipo_Roca.html</link>
            <description>Sólidos y resistentes, aprovechan la fuerza de los minerales.</description>
        </item>

        <item>
            <title>Siniestro</title>
            <link>src/main/resources/html/Tipos/tipo_Siniestro.html</link>
            <description>Misteriosos y astutos, sobresalen en estrategias engañosas.</description>
        </item>

        <item>
            <title>Tierra</title>
            <link>src/main/resources/html/Tipos/tipo_Tierra.html</link>
            <description>Controlan el terreno y suelen habitar en zonas áridas.</description>
        </item>

        <item>
            <title>Veneno</title>
            <link>src/main/resources/html/Tipos/tipo_Veneno.html</link>
            <description>Especialistas en toxinas, utilizan veneno como ataque y defensa.</description>
        </item>

        <item>
            <title>Volador</title>
            <link>src/main/resources/html/Tipos/tipo_Volador.html</link>
            <description>Ágiles y con gran movilidad, dominan el aire con facilidad.</description>
        </item>

    </channel>
</rss>
```

# Capturas de las páginas creadas:
## Captura de el index.html.
![Alt](https://github.com/SleekNekro/IPM_ProjecteArchius/blob/master/captures/Captura%20de%20pantalla%202024-12-06%20121622.png)

Captura de detalles tipos
Captura2
![Alt](https://github.com/SleekNekro/IPM_ProjecteArchius/blob/master/captures/Captura%20de%20pantalla%202024-12-06%20121658.png)
