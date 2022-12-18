# Datos de ejemplo

Crea y levanta una instancia de MongoDB inicializada con una base de datos 'prices',
con una colección 'prices', que contiene los datos de ejemplo del problema.

La instancia de mongo se levanta en el puerto 27017, y es accesible en la
siguiente cadena de conexión:

```
mongodb://localhost:27017/prices
```

Comandos necesarios para manejar la imagen:

```
cd sample-data/docker/mongo

# Crear la imagen
docker build -t mongo-sample-data:1.0 .

# Arracar el contenedor
docker run --name mongo-sample-data -p 27017:27017 --restart=always -e MONGO_INITDB_DATABASE=prices -d mongo-sample-data:1.0

# Parar el contenedor
docker stop mongo-sample-data

# Borrar el contenedor
$ docker rm -f mongo-sample-data
```

Los datos de ejemplo están en __seed-data.js__.

## Reconocimiento

Esta imagen está basada en el repo de [steavetarver](https://github.com/stevetarver/sample-data) denominado [sample-data](https://github.com/stevetarver/sample-data).