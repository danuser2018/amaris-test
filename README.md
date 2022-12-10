# Prueba para Inditex

## Enunciado

En la base de datos de comercio electrónico de la compañía disponemos de la tabla
PRICES que refleja el precio final (pvp) y la tarifa que aplica a un producto de 
una cadena entre unas fechas determinadas. A continuación se muestra un ejemplo de
la tabla con los campos relevantes:

###### PRICES
 
BRAND_ID | START_DATE          | END_DATE            | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE | CURR |
---------|---------------------|---------------------|------------|------------|----------|-------|------
1        | 2020-06-14-00.00.00 | 2020-12-31-23.59.59 | 1          | 35455      | 0        | 35.50 | EUR  |
1        | 2020-06-14-15.00.00 | 2020-06-14-18.30.00 | 2          | 35455      | 1        | 25.45 | EUR  |
1        | 2020-06-15-00.00.00 | 2020-06-15-11.00.00 | 3          | 35455      | 1        | 30.50 | EUR  |
1        | 2020-06-15-16.00.00 | 2020-12-31-23.59.59 | 4          | 35455      | 1        | 38.95 | EUR  |

###### CAMPOS 

- __BRAND_ID:__ foreign key de la cadena del grupo (1 = ZARA).
- __START_DATE , END_DATE:__ rango de fechas en el que aplica el precio tarifa indicado.
- __PRICE_LIST:__ Identificador de la tarifa de precios aplicable.
- __PRODUCT_ID:__ Identificador código de producto.
- __PRIORITY:__ Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).
- __PRICE:__ precio final de venta.
- __CURR:__ iso de la moneda.

### Se pide

Construir una aplicación/servicio en SpringBoot que provea una end point rest de consulta  tal que:

1. Acepte como parámetros de entrada: 
    - fecha de aplicación
    - identificador de producto
    - identificador de cadena
    
2. Devuelva como datos de salida:
    - identificador de producto
    - identificador de cadena
    - tarifa a aplicar
    - fechas de aplicación
    - precio final a aplicar
 
3. Utilize una base de datos en memoria (tipo h2) e inicialializada con los datos del ejemplo,
(se pueden cambiar el nombre de los campos y añadir otros nuevos si se quiere, elegir el tipo de
dato que se considere adecuado para los mismos).

4. Desarrollar unos test al endpoint rest que validen las siguientes peticiones al servicio con
los datos del ejemplo:
- __Test 1:__ petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
- __Test 2:__ petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
- __Test 3:__ petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
- __Test 4:__ petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
- __Test 5:__ petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)

### Se valorará

- Diseño y construcción del servicio
- Calidad de Código
- Resultados correctos en los test

## Solución

El servicio se ha implementado mediante un microservicio basado en spring-boot.

## Licencia

Copyright © 2021-2022 David Núñez Serrano

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and 
associated documentation files (the “Software”), to deal in the Software without restriction, 
including without limitation the rights to use, copy, modify, merge, publish, distribute, 
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software 
is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or 
substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE 
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
