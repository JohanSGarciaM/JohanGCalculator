## Realizado por JOHAN SEBASTIAN GARCIA MARTINEZ

Para hacer disposición de la aplicación primero es necesario descargar el contenido de este repositorio en la ubicación del directorio donde desee con el siguiente comando:

```
git clone https://github.com/JohanSGarciaM/JohanGCalculator.git
```

Ahora es necesario dirigirnos a la carpeta del proyecto que se acabó de clonar:

```
cd JohanGCalculator
```

Debemos compilar el código fuente con el siguiente comando

```
mvn clean install
```
Para hacer uso de la aplicación utilizamos el comando (el cuál va a ejecutar la clase Main que se defina en el build del Pom):

```
mvn exec:java
```

A partir de este momento el socket esta activo para recibir la información al puerto 35000 por lo que nos dirijimos desde nuestro browser al puerto, seguido del nombre del archivo que deseamos obtener junto con su extensión.

```
localhost:36000/calculadora
```

Para poder revisar la documentación del proyecto es necesario ejecutar el siguiente comando en la terminar para que se ejecute en el navegador.

```
mvn javadoc:javadoc
```