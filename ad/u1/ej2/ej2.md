# 2º DAM - Acceso a Datos
#### Joaquín Soro Castelló
---

### **Ejercicio 2: Investiga la viabilidad e inconvenientes del cambio de las propiedades del sistema desde nuestras aplicaciones Java, mediante consulta en Internet.**

---
Dentro de la clase `System`, podemos encontrarnos con métodos que permiten la lectura y la modificación de propiedades del sistema desde Java. Para trabajar con esa información se emplea la clase `Properties` y sus métodos derivados. La lectura de las propiedades del sistema se efectúa mediante el método `System.getProperties`.

En cuanto a la modificación, si echamos un vistazo a la documentación podemos encontrar dentro del método `System.setProperties` información relevante acerca de las propiedades del sistema:

> API Note:
    Changing a standard system property may have unpredictable results unless otherwise specified. Property values may be cached during initialization or on first use. Setting a standard property after initialization using getProperties(), setProperties(Properties), setProperty(String, String), or clearProperty(String) may not have the desired effect.

Por otro lado, la [documentación oficial de Oracle](https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html) hace el siguiente comentario, que advierte de que muchas propiedades no son recargadas tras el arranque del programa y sólo deben ser usadas para consultar información:

>Warning: Changing system properties is potentially dangerous and should be done with discretion. Many system properties are not reread after start-up and are there for informational purposes. Changing some properties may have unexpected side-effects.

Además, en la misma documentación, se advierte de la posibilidad de sobreescribir propiedades del sistema:

>Also note that the value of system properties can be overwritten! For example, if myProperties.txt contains the following line, the java.vendor system property will be overwritten: 
>
>`java.vendor=Acme Software Company`
>
>In general, be careful not to overwrite system properties.

En este ejemplo, estaríamos modificando el nombre de la empresa proveedora de Java.

Es recomendable emplear el método `setProperties` en lugar de `setProperty`, puesto que el primero cambia las propiedades del sistema para el programa en ejecución, de forma que los cambios no son persistentes en futuras ejecuciones o invocaciones a la JVM. Para hacer dicho cambios persistentes, deben ser escritos a un fichero antes de salir de la ejecución y ser leídos nuevamente durante el arranque. Así lo explica en la documentación anterior:

>The setProperties method changes the set of system properties for the current running application. These changes are not persistent. That is, changing the system properties within an application will not affect future invocations of the Java interpreter for this or any other application. The runtime system re-initializes the system properties each time its starts up. If changes to system properties are to be persistent, then the application must write the values to some file before exiting and read them in again upon startup.