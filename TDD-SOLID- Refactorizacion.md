# TDD y SOLID
Hasta ahora, hemos creado algunas pruebas unitarias básicas que han generado un diseño simple
para un par de clases. Hemos experimentado cómo el desarrollo basado en pruebas (TDD) hace que
la toma de decisiones sobre las opciones de diseño sea central. Para desarrollar una aplicación más
grande, necesitaremos ser capaces de manejar diseños de mayor complejidad. Para hacer esto,
aplicaremos algunos enfoques recomendados para evaluar qué hace que un diseño sea preferible a
otro.

Los principios SOLID son cinco pautas de diseño que orientan los diseños para que sean más flexibles
y modulares.

Originalmente, los principios de SOLID se concibieron como patrones que se aplicaban a las clases de
programación orientada a objetos (POO), pero tienen un propósito más general que eso. Se aplican
igualmente a los métodos individuales de una clase, así como a la clase misma. También se aplican al
diseño de interconexiones de microservicios y diseño de funciones en programación funcional. 

## **Guía de prueba: impulsamos el diseño**

Ya escribimos algunas pruebas. Para hacer eso, analizamos una serie de decisiones de diseño.
Revisemos ese código de prueba inicial y enumeremos todas las decisiones de diseño que tuvimos
que tomar, de la siguiente manera: 

```java
@Test
public void oneIncorrectLetter() {
var word = new Word("A");
var score = word.guess("Z");
assertThat( score.letter(0) ).isEqualTo(Letter.INCORRECT);
}
```

Decidimos lo siguiente:
  - ¿Qué probar?
    
    Decidimos probar la funcionalidad de adivinanza de una palabra y verificar que una letra incorrecta se marque como incorrecta.
  - ¿ Cómo llamar a la prueba?

    Nombramos la prueba oneIncorrectLetter para indicar claramente que estamos probando el caso de una letra incorrecta.
  - ¿ Cómo llamar al método bajo prueba?

    Llamamos al método guess porque su propósito es adivinar letras de una palabra.
  - ¿ En qué clase poner ese método ?

    Decidimos poner el método guess en la clase Word, ya que la funcionalidad de adivinanza es inherentemente parte de la representación de una palabra.
  - La firma de ese método

    La firma del método guess es public Score guess(String guess):
    ```java
    public Score guess(String guess)
    ```
  - La firma del constructor de la clase.
    
    La firma del constructor de la clase Word es:
     ```java
    public Word(String word):
      ```
  - Qué otros objetos deberían colaborar

    Identificamos que necesitamos una clase Score para representar el resultado de la adivinanza.
    También identificamos que necesitamos una clase Letter para representar el estado de cada letra adivinada.
  - Las firmas de métodos involucradas en esa colaboración.

    La firma del método en la clase Score para acceder a la letra es public Letter letter(int index):
     ```java
    public Letter letter(int index)
      ```
  - ¿Qué forma tomará el resultado de este método?

    El resultado del método guess será una instancia de la clase Score que contiene la evaluación de cada letra adivinada.
  - ¿ Cómo acceder a esa salida y aseverar que funciona?

    Accedemos al resultado usando el método letter(int index) de la clase Score y usamos una aserción para verificar que el estado de la letra es Letter.INCORRECT.
 gradle -v
Todas estas son decisiones de diseño que se debe tomar. TDD nos deja muy involucrados cuando se
trata de diseñar el código y decidir cómo debe implementarse. Diseñar es gratificante y TDD
proporciona un andamiaje útil en lugar de un enfoque prescriptivo. TDD actúa como una guía para
recordarnos que debemos tomar estas decisiones de diseño con anticipación. También proporciona
una forma de documentar estas decisiones como código de prueba.

Puede ser útil usar técnicas como la programación en pares o el mobbing (también conocido como
programación en conjunto) cuando tomamos estas decisiones; luego, agregamos más experiencia y
más ideas a la solución. Trabajando solos, simplemente tenemos que tomar las mejores decisiones
que podamos, basándonos en nuestra propia experiencia.

El punto crítico que se debe transmitir aquí es que TDD no toma ni puede tomar estas decisiones por
nosotros. Debemos hacerlos. Como tal, es útil tener algunas pautas para guiarnos hacia mejores
diseños. Un conjunto de cinco principios de diseño conocidos como principios SOLID son útiles.
SOLID es un acrónimo de los siguientes cinco principios: 

  - SPR
  - OCP
  - LSP
  - ISP
  - DIP 
## **SRP (principio de responsabilidad única): bloques de construcción simples**

En esta sección, examinaremos el primer principio, conocido como SRP. Usaremos un solo ejemplo
de código en todas las secciones. Esto aclara cómo se aplica cada principio a un diseño orientado a
objetos (OO). Vamos a ver un ejemplo clásico de diseño OO: dibujar formas.

El siguiente diagrama es una descripción general del diseño en el lenguaje de modelado unificado
(UML), que describe el código presentado en la actividad: 
SRP nos guía para dividir el código en partes que encapsulen un solo aspecto de la solución. 
  
![image](https://github.com/MarceloLZR/SOFTWARE-NUEVO-GRUPO/assets/137519831/a93536f2-32aa-4fb7-969c-7b0cb275e15a)

Este diagrama muestra una descripción general del código Java disponible. Usaremos partes
específicas del código para ilustrar cómo se ha usado cada uno de los principios SOLID para crear
este diseño.

SRP nos guía para dividir el código en partes que encapsulen un solo aspecto de la solución. Tal vez
sea un aspecto técnico por naturaleza, como leer una tabla de base de datos, o tal vez sea una regla
comercial. De cualquier manera, dividimos diferentes aspectos en diferentes piezas de código. Cada
fragmento de código es responsable de un único detalle, de ahí el nombre SRP. Otra forma de ver
esto es que una pieza de código solo debería tener una razón para cambiar. 

Un error de programación común es combinar demasiadas responsabilidades en una sola pieza de
código. Si tenemos una clase que puede generar lenguaje de marcado de hipertexto (HTML),
ejecutar una regla comercial y obtener datos de una tabla de base de datos, esa clase tendrá tres
razones para cambiar. Cada vez que sea necesario un cambio en una de estas áreas, correremos el
riesgo de hacer un cambio de código que rompa los otros dos aspectos. El término técnico para esto
es que el código está **altamente acoplado**. Esto lleva a que los cambios en un área se propaguen y
afecten a otras áreas. 

Podemos visualizar esto como el bloque de código A en el siguiente diagrama: 
![image](https://github.com/MarceloLZR/SOFTWARE-NUEVO-GRUPO/assets/137519831/892f9f9f-23a6-47a2-8dd0-2ef7a77e4c10)

El bloque A se ocupa de tres cosas, por lo que un cambio en cualquiera de ellos implica un cambio en A. Para mejorar esto, aplicamos SRP y separamos el código responsable de crear HTML,  de aplicar reglas comerciales y de acceder a la base de datos. Cada uno de esos tres bloques de código, A, B y C, ahora solo tiene una razón para cambiar. Cambiar cualquier bloque de código único no debería resultar en cambios que se extiendan a los otros bloques. Esto lo podemos visualizar en el siguiente diagrama:  

![image](https://github.com/MarceloLZR/SOFTWARE-NUEVO-GRUPO/assets/135175818/fe6ebadc-49dc-41d1-9937-1637583dd738)

Cada bloque de código se ocupa de una cosa y solo tiene una razón para cambiar. Podemos ver que
SRP funciona para limitar el alcance de futuros cambios de código. También facilita la búsqueda de
código en una gran base de código, ya que está organizado lógicamente.

La aplicación de SRP brinda otros beneficios, como los siguientes: 

  - Capacidad para reutilizar el código
  - Mantenimiento futuro simplificado

La reutilización de código ha sido un objetivo de la ingeniería de software durante mucho tiempo.
Crear software desde cero lleva tiempo, cuesta dinero y evita que un ingeniero de software haga
otra cosa. Tiene sentido que si creamos algo que generalmente es útil, lo usemos de nuevo siempre
que sea posible. La barrera para esto ocurre cuando hemos creado piezas de software grandes y
específicas de la aplicación. El hecho de que sean altamente especializados significa que solo pueden
usarse en su contexto original. Al crear componentes de software más pequeños y de uso más
general, podremos usarlos nuevamente en diferentes contextos. **Cuanto menor sea el alcance de lo
que pretende hacer el componente, más probable es que podamos reutilizarlo sin modificarlo.** Si
tenemos una pequeña función o clase que hace una cosa, es fácil reutilizarla en nuestra base de
código. Incluso puede terminar como parte de un framework o biblioteca que podemos reutilizar en
múltiples proyectos. SRP no garantiza que el código sea reutilizable, pero tiene como objetivo
reducir el alcance de lo que hace cualquier pieza de código. Esta forma de pensar en el código como
una serie de bloques de construcción donde cada uno hace una pequeña parte de la tarea general
tiene más probabilidades de resultar en componentes reutilizables. 

A medida que escribimos código, somos conscientes de que no solo escribimos para resolver un
problema ahora, sino que también escribimos código que podría revisarse en el futuro. Esto puede
ser hecho por otras personas en el equipo o tal vez por nosotros mismos. Queremos que este
trabajo futuro sea lo más simple posible. Para lograr esto, necesitamos mantener nuestro código
bien diseñado, haciéndolo seguro y fácil de usar más adelante. 

El código duplicado es un problema para el mantenimiento: complica los cambios de código futuros.
Si copiamos y pegamos una sección de código tres veces, digamos, nos parece bastante obvio en ese
momento lo que estamos haciendo. Tenemos un concepto que debe suceder tres veces, así que lo
pegamos tres veces. Pero cuando llega el momento de volver a leer el código, ese proceso de
pensamiento se ha perdido. Simplemente se lee como tres piezas de código no relacionadas.
**Perdemos información de ingeniería al copiar y pegar.**

Tendremos que aplicar ingeniería inversa a ese código para determinar que hay tres lugares donde
debemos cambiarlo. 

## Contraejemplo: código  que viola SRP  

Para ver el valor de aplicar SRP, consideremos un fragmento de código que no lo usa. El siguiente
fragmento de código tiene una lista de formas que se dibujan cuando llamamos al método draw(): 
```java
public class Shapes {
 private final List<Shape> allShapes = new ArrayList<>();
 public void add(Shape s) {
   allShapes.add(s);
 }
public void draw(Graphics g) {
   for (Shape s : allShapes) {
   switch (s.getType()) {
   case "textbox":
     var t = (TextBox) s;
     g.drawText(t.getText());
     break;
   case "rectangle":
     var r = (Rectangle) s;
     for (int row = 0;
       row < r.getHeight();
       row++) {
     g.drawLine(0, r.getWidth());
     }
   }
  }
 }
}
```

![image](https://github.com/MarceloLZR/SOFTWARE-NUEVO-GRUPO/assets/135175818/5dc21d45-95f8-4f1a-af8d-be8d19a3d3b5)

Podemos ver que este código tiene cuatro responsabilidades, de la siguiente manera:
  - Administrar la lista de formas con el método add()
  - Dibujar todas las formas en la lista con el método draw()
  - Conocer cada tipo de forma en la declaración switch
  - Tiene detalles de implementación para dibujar cada tipo de forma en las declaraciones case.

Si queremos agregar un nuevo tipo de forma (triángulo, por ejemplo), entonces necesitaremos
cambiar este código. Esto lo hará más largo, ya que necesitamos agregar detalles sobre cómo dibujar
la forma dentro de una nueva declaración case. Esto hace que el código sea más difícil de leer. La
clase también tendrá que tener nuevas pruebas.

¿Podemos cambiar este código para que sea más fácil agregar un nuevo tipo de forma? Ciertamente.
Apliquemos SRP y refactoricemos. 

## Aplicar SRP para simplificar el mantenimiento futuro  

Refactorizaremos este código para aplicar SRP, dando pequeños pasos. Lo primero que hay que
hacer es trasladar ese conocimiento de cómo dibujar cada tipo de forma fuera de esta clase, de la
siguiente manera:

```java
package shapes;
import java.util.ArrayList;
import java.util.List;
public class Shapes {
 private final List<Shape> allShapes = new ArrayList<>();

 public void add(Shape s) {
 allShapes.add(s);
 }
 public void draw(Graphics g) {
 for (Shape s : allShapes) {
 switch (s.getType()) {
 case "textbox":
 var t = (TextBox) s;
 t.draw(g);
 break;

case "rectangle":
 var r = (Rectangle) s;
 r.draw(g);
 }
 }
 }
}
```

![image](https://github.com/MarceloLZR/SOFTWARE-NUEVO-GRUPO/assets/135175818/ab7f967f-6b2b-4f5a-91c4-5f1e4d2a9033)

El código que solía estar en los bloques de declaraciones cases se ha movido a las clases de formas
(shape). Veamos los cambios en la clase Rectangle como un ejemplo; puedes ver lo que ha cambiado
en el siguiente fragmento de código: 
```java
public class Rectangle {
 private final int width;
 private final int height;

 public Rectangle(int width, int height){
 this.width = width;
 this.height = height;
 }
 public void draw(Graphics g) {
 for (int row=0; row < height; row++) {
 g.drawHorizontalLine(width);
 }
 }
}
```
![image](https://github.com/MarceloLZR/SOFTWARE-NUEVO-GRUPO/assets/135175818/989394cd-6b72-418d-860c-dcde28013798)

Podemos ver como la clase Rectangle ahora tiene la única responsabilidad de saber dibujar un
rectángulo. No hace nada más. La única razón por la que tendrá que cambiar es si necesitamos
cambiar la forma en que se dibuja un rectángulo. Esto es poco probable, lo que significa que ahora
tenemos una abstracción estable. En otras palabras, la clase Rectangle es un bloque de construcción
en el que podemos confiar. Es poco probable que cambie.
Si examinamos la clase Shapes refactorizada, vemos que también ha mejorado. Tiene una
responsabilidad menos porque la trasladamos a las clases TextBox y Rectangle. Ya es más fácil de
leer y más fácil de probar. 

### **SRP**

Haz una cosa y hazla bien. Ten solo una razón para cambiar un bloque de código.
Se pueden hacer más mejoras. Vemos que la clase Shapes conserva su declaración de cambio y que
cada declaración case parece duplicadas. Todos hacen lo mismo, que es llamar a un método draw()
en una clase de forma. Podemos mejorar esto reemplazando la declaración de cambio por completo,
pero eso tendrá que esperar hasta la siguiente sección, donde presentamos el DIP.

Antes de hacer eso, pensemos en cómo SRP se aplica al propio código de prueba.
SRP también nos ayuda a organizar nuestras pruebas. Cada prueba debe probar solo una cosa. Tal
vez este sería un único camino feliz o una única condición límite. Esto hace que sea más fácil localizar
cualquier falla. Encontramos la prueba que falló, y debido a que se trata de un solo aspecto de
nuestro código, es fácil encontrar el código donde debe estar el defecto. La recomendación de tener
solo una sola aserción para cada prueba fluye naturalmente de esto.

A veces, un grupo de objetos se puede organizar para colaborar de múltiples maneras diferentes. Las
pruebas para este grupo suelen ser mejores si escribimos una sola prueba por configuración.
Terminamos con múltiples pruebas más pequeñas con las que es más fácil trabajar. 

Este es un ejemplo de cómo aplicar SRP a cada configuración de ese grupo de objetos y capturarlo
escribiendo una prueba para cada configuración específica. 

## DIP (Principio de Inversión de Dependencia): Ocultar detalles irrelevantes

En esta sección, aprenderemos cómo el DIP nos permite dividir el código en componentes separados
que pueden cambiar independientemente unos de otros. Luego veremos cómo esto conduce
naturalmente a la parte OCP de SOLID.

La inversión de dependencia (DI) significa que escribimos código para depender de abstracciones, no
de detalles. Lo opuesto a esto es tener dos bloques de código, uno que depende de la
implementación detallada del otro. Los cambios en un bloque provocarán cambios en otro.

Para ver cómo se ve este problema en la práctica, revisemos un contraejemplo. El siguiente
fragmento de código comienza donde lo dejamos con la clase Shapes después de aplicarle SRP: 

```java
package shapes;
import java.util.ArrayList;
import java.util.List;
public class Shapes {
 private final List<Shape> allShapes = new ArrayList<>();

 public void add(Shape s) {
 allShapes.add(s);
}
 public void draw(Graphics g) {
 for (Shape s : allShapes) {
 switch (s.getType()) {
 case "textbox":
 var t = (TextBox) s;
 t.draw(g);
 break;
 case "rectangle":
 var r = (Rectangle) s;
 r.draw(g);
 }
 }
 }
}
```
Este código funciona bien para mantener una lista de objetos Shape y dibujarlos. El problema es que
sabe demasiado sobre los tipos de formas que se supone que debe dibujar. El método draw()
presenta un switch-on-type de objeto que puedes ver. Eso significa que si algo cambia sobre qué
tipos de formas deben dibujarse, entonces este código también debe cambiar. Si queremos agregar
una nueva forma al sistema, entonces tenemos que modificar esta declaración de cambio y el código
de prueba TDD asociado.

El término técnico para que una clase conozca a otra es que existe una dependencia entre ellas. La
clase Shapes depende de las clases TextBox y Rectangle. Podemos representar eso visualmente en el
siguiente diagrama de clases UML: 

![image](https://github.com/MarceloLZR/SOFTWARE-NUEVO-GRUPO/assets/137519831/d972fae1-20da-4d61-acce-a27737f82c52)

Podemos ver que la clase Shapes depende directamente del detalle de las clases Rectangle y
TextBox. Esto se muestra por la dirección de las flechas en el diagrama de clases UML. Tener estas
dependencias hace que trabajar con la clase Shapes sea más difícil por las siguientes razones:

  - Tenemos que cambiar la clase Shapes para agregar un nuevo tipo de forma.
  - Cualquier cambio en las clases concretas como Rectangle hará que este código cambie
  - La clase Shapes será más larga y menos fácil de leer.
  - Terminaremos con más casos de prueba
  - Cada caso de prueba se acoplará a clases concretas como Rectangle

Este es un enfoque muy procedimental para crear una clase que trate con múltiples tipos de formas.
Viola SRP al hacer demasiado y conocer demasiados detalles sobre cada tipo de objeto de forma. La
clase Shapes depende de los detalles de clases concretas como Rectangle y TextBox, lo que provoca
directamente los problemas antes mencionados.
Afortunadamente, hay una mejor manera. Podemos usar el poder de una interfaz para mejorar esto,
haciendo que la clase Shapes no dependa de esos detalles. Esto se llama DI. Veamos cómo se ve eso
a continuación.

**Aplicando DIP**

Podemos mejorar el código de formas aplicando el Principio de Inversión de Dependencia (DIP).
Agreguemos un método draw() a la interfaz Shape, de la siguiente manera:

```java
package shapes;
public interface Shape {
 void draw(Graphics g);
}
```
Esta interfaz es la abstracción de la única responsabilidad que tiene cada forma. Cada forma debe
saber cómo dibujarse a sí misma cuando llamamos al método draw(). El siguiente paso es hacer que
las clases de formas concretas implementen esta interfaz. Tomemos la clase Rectangle como
ejemplo. Puedes ver esto aquí:

```java
public class Rectangle implements Shape {
 private final int width;
 private final int height;
 public Rectangle(int width, int height){
 this.width = width;
 this.height = height;
 }
 @Override
 public void draw(Graphics g) {
 for (int row=0; row < height; row++) {
 g.drawHorizontalLine(width);
 }
 }
}
```

Ahora hemos introducido el concepto OO de polimorfismo en las clases de forma. Esto rompe la
dependencia que tiene la clase Shapes de conocer las clases Rectangle y TextBox. Todo lo que ahora
depende de la clase Shapes es la interfaz Shape. Ya no necesita saber el tipo de cada forma.

Podemos refactorizar la clase Shapes para que se vea así:

```java
public class Shapes {
 private final List<Shape> all = new ArrayList<>();
 public void add(Shape s) {
 all.add(s);
}
 public void draw(Graphics graphics) {
 all.forEach(shape->shape.draw(graphics));
 }
}

```

Esta refactorización eliminó por completo la declaración switch y el método getType(), lo que hace
que el código sea mucho más fácil de entender y probar. Si agregamos un nuevo tipo de forma, la
clase Shapes ya no necesitas cambiar. Hemos roto esa dependencia de conocer los detalles de las
clases de forma.

Un refactor menor mueve el parámetro Graphics que pasamos al método draw() a un campo,
inicializado en el constructor, como se ilustra en el siguiente fragmento de código:
```java
public class Shapes {
 private final List<Shape> all = new ArrayList<>();
 private final Graphics graphics;
 public Shapes(Graphics graphics) {
 this.graphics = graphics;
}
 public void add(Shape s) {
 all.add(s);
 }
 public void draw() {
 all.forEach(shape->shape.draw(graphics));
}
}
```

Esto es DIP en el trabajo. Hemos creado una abstracción en la interfaz Shape. La clase Shapes es un
consumidor de esta abstracción. Las clases que implementan esa interfaz son proveedores. Ambos
conjuntos de clases dependen solo de la abstracción; no dependen de los detalles uno dentro del
otro. No hay referencias a la clase Rectangle en la clase Shapes, y no hay referencias a las Shapes
dentro de la clase Rectangle.

Podemos ver esta inversión de dependencias visualizada en el siguiente diagrama de clases UML:
vea cómo ha cambiado la dirección de las flechas de dependencia en comparación con la siguiente
figura:

![image](https://github.com/MarceloLZR/SOFTWARE-NUEVO-GRUPO/assets/137519831/a923a889-166b-4d22-b882-8d3f9f2d8686)

En esta versión del diagrama UML, las flechas que describen las dependencias entre clases apuntan
en dirección opuesta. Las dependencias se han invertido, de ahí el nombre de este principio. La clase
Shapes ahora depende de la abstracción, la interfaz Shape. Lo mismo ocurre con todas las
implementaciones concretas de la clase Rectangle y la clase TextBox. Hemos invertido el gráfico de
dependencia y hemos puesto las flechas al revés.

DI desacopla completamente las clases entre sí y, como tal, es muy poderoso.

**DIP**

Hacer que el código dependa de abstracciones y no de detalles.

Hemos visto cómo DIP es una herramienta importante que podemos usar para simplificar el código.
Nos permite escribir código que trata con una interfaz y luego usar ese código con cualquier clase
concreta que implemente esa interfaz. Esto plantea una pregunta: ¿podemos escribir una clase que
implemente una interfaz pero que no funcione correctamente? 

## LSP (principio de sustitución de Liskov): objetos intercambiables 

La ganadora del premio Turing, Barbara Liskov, es la creadora de una regla sobre la herencia que
ahora se conoce comúnmente como LSP. Fue provocado por una pregunta en OOP: si podemos
extender una clase y usarla en lugar de la clase que extendimos, ¿cómo podemos estar seguros de
que la nueva clase no romperá las cosas?

Hemos visto en la sección anterior sobre DIP cómo podemos usar cualquier clase que implemente
una interfaz en lugar de la interfaz misma. También vimos cómo esas clases pueden proporcionar
cualquier implementación que deseen para ese método. La interfaz en sí no ofrece ninguna garantía
sobre lo que podría esconderse dentro de ese código de implementación.

Hay, por supuesto, un lado malo en esto, que LSP pretende evitar. Expliquemos esto mirando un
contraejemplo en el código. Supongamos que creamos una nueva clase que implementa la interfaz
Shape, como esta (Advertencia: ¡NO ejecutes el código que sigue en la clase MaliciousShape!): 

```java
public class MaliciousShape implements Shape {
 @Override
 public void draw(Graphics g) {
 try {
 String[] deleteEverything = {"rm", "-Rf", "*"};
 Runtime.getRuntime().exec(deleteEverything,null);
 g.drawText("Nothing to see here...");
 } catch (Exception ex) {
 // No accion
 }
 }
}
```

¿Notas algo un poco extraño en esa nueva clase? ¡Contiene un comando de Unix para eliminar todos
los archivos! Esto no es lo que esperamos cuando llamamos al método draw() en un objeto de
forma. Debido a fallas en los permisos, es posible que no puedas eliminar nada, pero es un ejemplo
de lo que puede salir mal.

Una interfaz en Java solo puede proteger la sintaxis de las llamadas a métodos que esperamos. No
puede imponer ninguna semántica. El problema con la clase MaliciousShape anterior es que no
respeta la intención detrás de la interfaz.

LSP nos guía para evitar este error. En otras palabras, LSP establece que cualquier clase que
implemente una interfaz o amplíe otra clase debe manejar todas las combinaciones de entrada que
la clase/interfaz original podría. Debes proporcionar los resultados esperados, no debes ignorar las
entradas válidas y no debe producir un comportamiento completamente inesperado y no deseado.
Las clases escritas así son seguras de usar a través de una referencia a su interfaz. El problema con la
clase MaliciousShape es que no era compatible con LSP: agregaba un comportamiento extra
totalmente inesperado y no deseado.

### **Definición formal de LSP**

La científica informática estadounidense Barbara Liskov propuso una definición formal: si p(x) es una
propiedad demostrable sobre objetos x de tipo T, entonces p(y) debería ser cierta para objetos y de
tipo S, donde S es un subtipo de T.

### **Revisión del uso de LSP**

Todas las clases que implementan Shape se ajustan a LSP. Esto es claro en la clase TextBox, como
podemos ver aquí: 

```java
public class TextBox implements Shape {
 private final String text;

 public TextBox(String text) {
 this.text = text;
 }
 @Override
 public void draw(Graphics g) {
 g.drawText(text);
}
}
```

El código anterior claramente puede manejar dibujar cualquier texto válido proporcionado a su
constructor. Tampoco ofrece sorpresas. Dibuja el texto usando primitivas de la clase Graphics y no
hace nada más.

Se pueden ver otros ejemplos de cumplimiento de LSP en las siguientes clases:
  - Rectanlgle
  - Triangle 

**LSP**

Un bloque de código se puede intercambiar con seguridad por otro si puede manejar la gama
completa de entradas y proporcionar (al menos) todas las salidas esperadas, sin efectos secundarios
no deseados.

Hay algunas violaciones sorprendentes de LSP. Quizás el clásico para el ejemplo de código del
ejemplo se trata de agregar una clase Square. En matemáticas, un cuadrado es una especie de
rectángulo, con la restricción adicional de que su altura y anchura son iguales. En código Java,
¿deberíamos hacer que la clase Square amplíe la clase Rectangle? ¿Qué pasa con la clase Rectangle
extendiendo Square?

Apliquemos LSP para decidir. Imaginamos algún código que espera una clase Rectangle para que
pueda cambiar su alto, pero no su ancho. Si le pasamos una clase Square a ese código, ¿funcionaría
correctamente? La respuesta es no. Entonces tendrías un cuadrado con ancho y alto desiguales. Esto
falla LSP.

El objetivo de LSP es hacer que las clases se ajusten adecuadamente a las interfaces. En la siguiente
sección, veremos OCP, que está estrechamente relacionado con DI. 

## OCP (Principio de abierto y cerrado): diseño extensible

En esta sección, veremos cómo OCP nos ayuda a escribir código al que podemos agregar nuevas
funciones, sin cambiar el código en sí. Esto suena como una imposibilidad al principio, pero fluye
naturalmente de DIP combinado con LSP.
OCP da como resultado un código que está abierto a la extensión pero cerrado a la modificación.
Vimos esta idea en el trabajo cuando miramos DIP.

Revisemos la refactorización de código que hicimos a la luz de OCP. Comencemos con el código
original de la clase Shapes, de la siguiente manera: 

```java
public class Shapes {
 private final List<Shape> allShapes = new ArrayList<>();
 public void add(Shape s) {
 allShapes.add(s);
 }
 public void draw(Graphics g) {
 for (Shape s : allShapes) {
 switch (s.getType()) {
 case "textbox":
 var t = (TextBox) s;
 g.drawText(t.getText());
 break;
 case "rectangle":
 var r = (Rectangle) s;
 for (int row = 0;
 row < r.getHeight();
 row++) {
 g.drawLine(0, r.getWidth());
 }
 }
 }
 }
}
```

Agregar un nuevo tipo de forma requiere la modificación del código dentro del método draw().
Agregaremos una nueva declaración de caso para respaldar la nueva forma. La modificación del
código existente tiene varias desventajas, como se establece aquí:

  - Invalidamos las pruebas anteriores. Este es ahora un código diferente al que habíamos probado.
  - Podríamos introducir un error que rompa parte del soporte existente para las formas.
  - El código será más largo y más difícil de leer.
  - Es posible que tengamos varios desarrolladores que agreguen formas al mismo tiempo y obtengamos un conflicto de fusión cuando combinemos su trabajo.

Al aplicar DIP y refactorizar el código, terminamos con esto: 

```java
public class Shapes {
 private final List<Shape> all = new ArrayList<>();
 private final Graphics graphics;
 public Shapes(Graphics graphics) {
 this.graphics = graphics;
}
 public void add(Shape s) {
 all.add(s);
 }
 public void draw() {
 all.forEach(shape->shape.draw(graphics));
}
}
```

Ahora podemos ver que agregar un nuevo tipo de forma no necesita modificar este código. Este es
un ejemplo de OCP en el trabajo. La clase Shapes está abierta a la definición de nuevos tipos de
formas, pero está cerrada a la necesidad de modificaciones cuando se agrega esa nueva forma. Esto
también significa que cualquier prueba relacionada con la clase Shapes permanecerá sin cambios, ya
que no hay diferencia en el comportamiento de esta clase. Esa es una poderosa ventaja.

OCP confía en DI para trabajar. Es más o menos una reafirmación de una consecuencia de aplicar
DIP. También nos proporciona una técnica para admitir el comportamiento intercambiable.
Podemos usar DIP y OCP para crear sistemas de complementos.

**Agregar un nuevo tipo de forma **

Para ver cómo funciona esto en la práctica, creamos un nuevo tipo de forma, la clase RightArrow, de
la siguiente manera: 

```java
public class RightArrow implements Shape {
 public void draw(Graphics g) {
 g.drawText( " \" );
 g.drawText( "-----" );
 g.drawText( " /" );
 }
}
```
La clase RightArrow implementa la interfaz Shape y define un método draw(). Para demostrar que
no es necesario cambiar nada en la clase Shapes para usar esto, revisemos un código que usa tanto
Shapes como la nueva clase, RightArrow, de la siguiente manera:

```java
package shapes;
public class ShapesExample {
 public static void main(String[] args) {
 new ShapesExample().run();
}
 private void run() {
 Graphics console = new ConsoleGraphics();
// DIP: Inyectar dependencia Shapes en Graphics
 var shapes = new Shapes(console);
// A la clase Shapes OCP se le puede agregar cualquier tipo de Shape
// SRP cada subclase de forma, por ejemplo, Rectangle sabe cómo dibujar solo una forma // LSP cada
subclase de forma se puede usar donde sea que se necesite una interfaz de forma
 shapes.add(new TextBox("Hello!"));
 shapes.add(new Rectangle(32,1));
 shapes.add(new RightArrow());
 shapes.draw();
}
}
```

Vemos que la clase Shapes se está utilizando de forma completamente normal, sin cambios. De
hecho, el único cambio necesario para usar la nueva clase RightArrow es crear una instancia de
objeto y pasarla al método add() de formas.

### OCP

Haz que el código esté abierto para nuevos comportamientos, pero cerrado para modificaciones.
El poder de OCP ahora debería ser claro. Podemos extender las capacidades del código y mantener
los cambios limitados. Reducimos en gran medida el riesgo de romper el código que ya está
funcionando, ya que ya no necesitamos cambiar ese código. 

**OCP es una excelente manera de administrar la complejidad. **

## ISP(Principio de segregación de interfaz): interfaces efectivas 

En esta sección, veremos un principio que nos ayuda a escribir interfaces efectivas. Se conoce como
ISP. ISP nos aconseja mantener las interfaces pequeñas y dedicadas a lograr una sola
responsabilidad. Por interfaces pequeñas, nos referimos a tener la menor cantidad posible de
métodos en una sola interfaz. Todos estos métodos deben relacionarse con algún tema común.
Podemos ver que este principio es realmente solo SRP en otra forma.

Estamos diciendo que una interfaz efectiva debe describir una sola responsabilidad. Debe cubrir una
abstracción, no varias. Los métodos en la interfaz deben estar estrechamente relacionados entre sí y
también con esa única abstracción.

Si necesitamos más abstracciones, entonces usamos más interfaces. Mantenemos cada abstracción
en su propia interfaz separada, que es de donde proviene el término segregación de interfaz:
mantenemos diferentes abstracciones separadas.

El olor del código relacionado con esto es una gran interfaz que cubre varios temas diferentes en
uno. Podríamos imaginar una interfaz que tenga cientos de métodos en pequeños grupos, algunos
relacionados con la administración de archivos, algunos sobre la edición de documentos y otros
sobre la impresión de documentos. Tales interfaces rápidamente se vuelven difíciles de trabajar. ISP
sugiere que mejoremos esto dividiendo la interfaz en varias más pequeñas.

Esta división preservaría los grupos de métodos, por lo que es posible que vea interfaces para la
administración, edición e impresión de archivos, con métodos relevantes debajo de cada uno.
Hemos simplificado la comprensión del código separando estas abstracciones separadas.
Revisión del uso de ISP en el código de formas

El uso más notable de ISP está en la interfaz Shape, como se ilustra aquí: 

```java
interface Shape {
 void draw(Graphics g);
}
```
Esta interfaz claramente tiene un único enfoque. Es una interfaz con un enfoque muy limitado, tanto
que solo se necesita especificar un método: draw(). No hay confusión que surja de otros conceptos
mezclados aquí y no hay métodos innecesarios. Ese único método es a la vez necesario y suficiente.
El otro ejemplo importante está en la interfaz Graphics, como se muestra aquí: 

```java
public interface Graphics {
void drawText(String text);
void drawHorizontalLine(int width);
}
```
La interfaz Graphics contiene solo métodos relacionados con dibujar primitivas de gráficos en la
pantalla. Tiene dos métodos: drawText para mostrar una cadena de texto y drawHorizontalLine para
dibujar una línea en dirección horizontal. Como estos métodos están fuertemente relacionados
(conocidos técnicamente por exhibir una alta cohesión) y son pocos en número, el ISP está
completo. Esta es una abstracción efectiva sobre el subsistema de dibujo de gráficos, adaptada a
nuestros propósitos. 

Para completar, podemos implementar esta interfaz de varias maneras. El ejemplo en GitHub usa
una implementación de consola de texto simple: 

```java
public class ConsoleGraphics implements Graphics {
 @Override
 public void drawText(String text) {
 print(text);
 }
 @Override
 public void drawHorizontalLine(int width) {
 var rowText = new StringBuilder();

 for (int i = 0; i < width; i++) {
 rowText.append('X');
 }

 print(rowText.toString());
}
 private void print(String text) {
 System.out.println(text);
 }
}
```
Esa implementación también es compatible con LSP: se puede usar donde se espera la interfaz
Graphics.

### **ISP**

Mantenga las interfaces pequeñas y fuertemente relacionadas con una sola idea. Ahora cubrimos los
cinco principios SOLID y mostramos cómo se han aplicado al código de formas.
Han guiado el diseño hacia un código compacto, con una estructura bien diseñada para ayudar a los
futuros mantenedores. Sabemos cómo incorporar estos principios en el propio código para obtener
beneficios similares. 




# Ejercicios

## Ejercicio 1: Refactorizando para aplicar SRP

Refactoriza la clase Shapes para que cada clase tenga una única responsabilidad.

### Código original:

```java
public class Shapes {
 private final List<Shape> allShapes = new ArrayList<>();
 public void add(Shape s) {
 allShapes.add(s);
 }

 public void draw(Graphics g) {
 for (Shape s : allShapes) {
 switch (s.getType()) {
 case "textbox":
 var t = (TextBox) s;
 g.drawText(t.getText());
 break;
 case "rectangle":
 var r = (Rectangle) s;
 for (int row = 0; row < r.getHeight(); row++) {
 g.drawLine(0, r.getWidth());
 }
 }
 }
 }
}
```

**Tareas:**

  - Crea clases separadas para TextBox y Rectangle, cada una con su propia responsabilidad de dibujar.

    **TextBox**
    
    ```java
    public class TextBox implements Shape {
    private final String text;

    public TextBox(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String getType() {
        return "textbox";
    }

    public void draw(Graphics g) {
        g.drawText(text);
    }
    }

    ```
    
    **Rectangle**
    
    ```java
    public class Rectangle implements Shape {
    private final int width;
    private final int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String getType() {
        return "rectangle";
    }

    public void draw(Graphics g) {
        for (int row = 0; row < height; row++) {
            g.drawLine(0, row, width, row);
        }
    }
    }
    ```
    
  - Refactoriza Shapes para delegar el dibujo a estas clases.
    ```java
    public class Shapes {
    private final List<Shape> allShapes = new ArrayList<>();

    public void add(Shape s) {
        allShapes.add(s);
    }

    public void draw(Graphics g) {
        for (Shape s : allShapes) {
            switch (s.getType()) {
                case "textbox":
                    var t = (TextBox) s;
                    t.draw(g);
                    break;

                case "rectangle":
                    var r = (Rectangle) s;
                    r.draw(g);
            }
        }
        }
    }
    ```

## Ejercicio 2: Aplicando OCP y DIP

Modifica la clase **Shapes** para que sea abierta a la extensión pero cerrada a la modificación.

### Código inicial

```java
public interface Shape {
 void draw(Graphics g);
}
public class TextBox implements Shape {
 private final String text;
 public TextBox(String text) {
 this.text = text;
 }
 @Override
 public void draw(Graphics g) {
 g.drawText(text);
 }
}
public class Rectangle implements Shape {
 private final int width;
 private final int height;
public Rectangle(int width, int height) {
 this.width = width;
 this.height = height;
 }
 @Override
 public void draw(Graphics g) {
 for (int row = 0; row < height; row++) {
 g.drawLine(0, width);
 }
 }
}
```

**Tareas:**
  - Define una interfaz Shape con un método draw(Graphics g).
    ```java
    public class Shapes {
    private final List<Shape> allShapes = new ArrayList<>();
    private final Graphics graphics;
    public Shapes(Graphics graphics) {

        this.graphics = graphics;
    }
    public void add(Shape s) {

        allShapes.add(s);
    }

    public void draw() {
        allShapes.forEach(shape->shape.draw(graphics));
    }
    }
    ```
  - Haz que TextBox y Rectangle implementen esta interfaz.

    **TextBox**
    
    ```java
    public class TextBox implements Shape {
    private final String text;

    public TextBox(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String getType() {
        return "textbox";
    }

    public void draw(Graphics g) {
        g.drawText(text);
    }
    }
    ```
    
    **Rectangle**
    
    ```java
    public class Rectangle implements Shape {
    private final int width;
    private final int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String getType() {
        return "rectangle";
    }

    public void draw(Graphics g) {
        for (int row = 0; row < height; row++) {
            g.drawLine(0, row, width, row);
        }
    }
    }
    ```
  - Refactoriza Shapes para que dependa de la abstracción Shape en lugar de las clases concretas.
    ```java
    public class Shapes {
    private final List<Shape> allShapes = new ArrayList<>();
    private final Graphics graphics;
    public Shapes(Graphics graphics) {

        this.graphics = graphics;
    }
    public void add(Shape s) {

        allShapes.add(s);
    }

    public void draw() {
        allShapes.forEach(shape->shape.draw(graphics));
    }
    }
    ```

## Ejercicio 3 : Aplicando LSP

Asegúrate de que cualquier implementación de Shape pueda sustituir a otra sin alterar el
comportamiento esperado.

### Código inicial:

```java
public class Circle implements Shape {
 private final int radius;
 public Circle(int radius) {
 this.radius = radius;
 }
 @Override
 public void draw(Graphics g) {
 g.drawCircle(radius);
 }
}
```

**Tareas:**

  - Añade una nueva clase Circle que implemente Shape.
    ```java
    
    ```
  - Asegúrate de que Circle respete LSP y pueda sustituir a Rectangle y TextBox sin problemas.
    ```java
    
    ```


## Ejercicio 4: Aplicando ISP

Refactoriza las interfaces para asegurarte de que sean pequeñas y específicas.

### Código Inicial:

```java
public interface Shape {
 void draw(Graphics g);
}
```

**Tareas:**
  - Divide la interfaz Shape si es necesario para que cada interfaz tenga una única responsabilidad.

     TextGraphics
    ```java
    public interface TextGraphics {
    void drawText(String text);
    }
    ```

     LineGraphics
    ```java
    public interface LineGraphics {
    void drawHorizontalLine(int width);
    }
    ```
  - Si la interfaz Shape crece demasiado, considera dividirla en múltiples interfaces más pequeñas.


## Ejercicio 5: Refactorizando para Mejorar el Diseño y Pruebas

Refactoriza la clase Shapes y su método draw para mejorar la legibilidad y facilitar las pruebas
unitarias.

### Código Inicial:
```java
public class Shapes {
 private final List<Shape> allShapes = new ArrayList<>();
 public void add(Shape s) {
 allShapes.add(s);
 }
 public void draw(Graphics g) {
 for (Shape s : allShapes) {
 s.draw(g);
 }
}
}
```

**Tareas:**

  - Introduce el uso de un patrón de diseño como el Strategy Pattern para manejar el dibujo de diferentes tipos de formas.
  - Escribe pruebas unitarias para cada clase de forma para asegurarte de que se dibujen correctamente.


## Ejercicio 6: Aplicando LSP y refactorizando MaliciousShape

La clase **MaliciousShape** implementa la interfaz Shape, pero su comportamiento es claramente
malicioso y no respeta la intención de la interfaz. Tu tarea es refactorizar el diseño para que todas las
implementaciones de Shape cumplan con LSP y aseguren que no haya comportamiento no deseado.

### Código Inicial:

```java
public class MaliciousShape implements Shape {
 @Override
 public void draw(Graphics g) {
 try {
 String[] deleteEverything = {"rm", "-Rf", "*"};
 Runtime.getRuntime().exec(deleteEverything, null);
 g.drawText("Nothing to see here...");
 } catch (Exception ex) {
 // No action
 }
 }
}
```
**Tareas:**

Identificar la Violación de LSP:

  - **Explica por qué MaliciousShape viola el principio de sustitución de Liskov.**
    
  - **Analiza el comportamiento esperado de las implementaciones de Shape.**
    

Refactorizar el Diseño:

  - Introduce validaciones adicionales en el método draw de la interfaz Shape para prevenir comportamientos inesperados.
  - Asegúrate de que las clases que implementan Shape proporcionen un comportamiento seguro y esperado.

Crear una clase de prueba:

  - Implementa pruebas unitarias para verificar que todas las clases que implementan Shape se comporten de manera consistente y segura.
  - Asegúrate de que cualquier implementación maliciosa sea detectada durante las pruebas












