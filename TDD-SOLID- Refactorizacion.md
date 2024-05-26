
# SRP (principio de responsabilidad única): bloques de construcción simples
SRP nos guía para dividir el código en partes que encapsulen un solo aspecto de la solución. 

![image](https://github.com/MarceloLZR/SOFTWARE-NUEVO-GRUPO/assets/135175818/728c61da-57cb-4f45-ac79-ded47df88d00)

El bloque A se ocupa de tres cosas, por lo que un cambio en cualquiera de ellos implica un cambio en A. Para mejorar esto, aplicamos SRP y separamos el código responsable de crear HTML,  de aplicar reglas comerciales y de acceder a la base de datos. Cada uno de esos tres bloques de código, A, B y C, ahora solo tiene una razón para cambiar. Cambiar cualquier bloque de código único no debería resultar en cambios que se extiendan a los otros bloques. Esto lo podemos visualizar en el siguiente diagrama:  
![image](https://github.com/MarceloLZR/SOFTWARE-NUEVO-GRUPO/assets/135175818/fe6ebadc-49dc-41d1-9937-1637583dd738)

## Contraejemplo: código  que viola SRP  

![image](https://github.com/MarceloLZR/SOFTWARE-NUEVO-GRUPO/assets/135175818/5dc21d45-95f8-4f1a-af8d-be8d19a3d3b5)

Administrar la lista de formas con el método add()  

Dibujar todas las formas en la lista con el método draw() 

Conocer cada tipo de forma en la declaración switch

Tiene detalles de implementación para dibujar cada tipo de forma en las declaraciones case

## Aplicar SRP para simplificar el mantenimiento futuro  

![image](https://github.com/MarceloLZR/SOFTWARE-NUEVO-GRUPO/assets/135175818/ab7f967f-6b2b-4f5a-91c4-5f1e4d2a9033)

El código que solía estar en los bloques de declaraciones cases  se ha movido a las clases de formas (shape). Veamos los cambios en la clase Rectangle como un ejemplo; puedes ver lo que ha cambiado en el siguiente fragmento de código:  

![image](https://github.com/MarceloLZR/SOFTWARE-NUEVO-GRUPO/assets/135175818/989394cd-6b72-418d-860c-dcde28013798)

Podemos ver como la clase Rectangle ahora tiene la única responsabilidad de saber dibujar un rectángulo.

# DIP (Principio de Inversión de Dependencia): Ocultar detalles irrelevantes

El principio de inversión de dependencia (DIP) establece que debemos escribir código que dependa de abstracciones y no de detalles específicos. En este ejemplo Shapes depende de cambios en las formas TextBox y Rectangle

![image](https://github.com/MarceloLZR/SOFTWARE-NUEVO-GRUPO/assets/153737279/d0f8e907-29be-4d94-bea9-34b887c8f549)


## Contraejemplo: Código que viola el DIP

![image](https://github.com/MarceloLZR/SOFTWARE-NUEVO-GRUPO/assets/153737279/e920be8d-0d30-4189-bd25-036f72ca923f)


En el diagrama UML, se observan las dependencias de ambas clases con la clase `Shapes`, debido al método `DRAW`. La condicional `SWITCH` depende del tipo de shape, creando una fuerte dependencia entre las clases.

## Aplicación del DIP

![image](https://github.com/MarceloLZR/SOFTWARE-NUEVO-GRUPO/assets/153737279/bd1f3b0f-6805-49d2-a004-edac52f0e251)


Aplicamos el DIP eliminando las dependencias entre clases mediante la refactorización del método `DRAW`, de modo que dependa de la interfaz `Shape`.

![image](https://github.com/MarceloLZR/SOFTWARE-NUEVO-GRUPO/assets/153737279/0c6c0683-bb55-4d40-b00b-e73ea6c22a60)


Además, se realizó un pequeño cambio que mejora el orden y la legibilidad del código.

Al aplicar el DIP, conseguimos que las 3 clases dependan de la interfaz y evitamos que los bloques de código dependan unos de otros. Esto se ejemplifica en la siguiente imagen:

![image](https://github.com/MarceloLZR/SOFTWARE-NUEVO-GRUPO/assets/153737279/cf371cd3-68ec-45da-acb0-044f4c17e7cc)


# ISP(Principio de segregación de interfaz): interfaces efectivas 









