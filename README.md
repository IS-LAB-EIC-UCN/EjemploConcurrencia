# Ejemplo de Concurrencia
***

Este sistema sirve de ejemplo para mostrar mecanismos básicos de concurrencia en programas en lenguaje java. El sistema
consta de 2 paquetes, modelo y vista. El paquete **modelo** tiene 3 clases: *LaminaPelota*, *Marco Pelota* y *Pelota*. El 
paquete **vista** tiene la clase principal *Main*. El prósito de este pequeño sistema es el de hacer rebotar una pelota 
en un marco cerrado. Esta pelota rebotara por los marcos de acuerdo a la cantidad de iteraciones definidas en el código 
fuente.

Clase *Pelota*:  Modela una pelota.
Clase *LaminaPelota*: Crea una ventana donde se dibujará una o mas pelotas.
Clase *MarcoRebote*: Dibuja el marco donde se agrega la lámina  anterior y los botones.

El código mas importante es el que ejecuta la pelota:

```java
public void comenzarJuego()  {

        Pelota pelota=new Pelota();
        lamina.add(pelota);

        for (int i=1; i<=3000; i++){

            pelota.mueve_pelota(lamina.getBounds());
            this.lamina.paint(lamina.getGraphics());

            try{
                Thread.sleep(10);
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
```

El código anterior es ejecutado cuando se presiona el botón ***Iniciar***. Sin embargo, este código en monotarea, es decir 
solo se ejecuta un hilo de ejecución al correr el programa. Si quisieramos añadir mas hilos de ejecución necesitamos converir
el programa a multihilos, utilizando los conceptos propios del lenguaje. Java proporciona el paquete **java.util.concurrent**
el cual contiene las clases e interfaces necesarias para implementar concurrencia.

En este ejemplo utilizaremos la interfaz Runnable y la clase Thread para implementar concurrencia. La interfaz obliga a 
implementar el método **run()** encargado de ejecutar la tarea concurrnte. La clase **Thread** crea los hilos de ejecución.
Así, para transformar nuestro código y soporte concurrencia para lanzar varias pelotas usaremos la siguiente receta:

* Crear clase que implemente la interfaz Runnable (método run()).
* Escribir el código de la tarea dentro del método run.
* Instanciar la clase creada y almacenar la instancia en variable de tipo. Runnable
* Crear instancia de la clase Thread pasando como parámetro al constructor de Thread
  el objeto Runnable anterior.
* Poner en ejecución el hilo de ejecución con el método start de la clase Thread.

---

1. Crear la clase PelotasHilos
```java
public class PelotasHilos implements Runnable {

    private Pelota pelota;
    private Component lamina;

    public PelotasHilos(Pelota pelota, Component lamina ) {

        this.pelota = pelota;
        this.lamina = lamina;

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

        for (int i=1; i<=3000; i++){

            pelota.mueve_pelota(lamina.getBounds());

            lamina.paint(lamina.getGraphics());


            try {
                Thread.sleep(4);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
```
Esta clase implementa **Runnable**, obligándola a implementar el método **run()**. El código que ejecuta la tarea en la 
versión monohilo se escribe dentro del método **run()**. 

2. Modificar el código que se ejecuta al presionar el botón inicia.

```java
public void comenzarJuego(){
    
        Pelota pelota=new Pelota();
        lamina.add(pelota);

        Runnable pelotaHilos = new PelotasHilos(pelota, lamina);
        hilo = new Thread(pelotaHilos);
        hilo.start();
}
```
Se crea una variable de tipo **Runnable** (pelotaHilos), se pasa como argumento esta variable al constructor de la clase **Thread**
y ejecutamos el hilo con el método **start()**. Ahora podemos presionar el botón cuantas veces queramos porque 
cada vez que se presiona se ejecutan hilos independientes.

