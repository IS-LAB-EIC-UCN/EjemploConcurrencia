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
public void comenzarJuego ()  {

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

El código anterior es ejecutado cuando se presiona el botón ***Iniciar***.

