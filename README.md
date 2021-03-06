
<p align="center">
  TRABAJO DE FIN DE GRADO 2019/2020. UCM. Facultad de Informática 
</p>

<p align="center">
  <img src="imgs/logo_UCM.jpg" width="500">
</p>


## Esd2: Cuaderno de recogida de datos para un estudio médico

**-Autores:** Eduardo Gonzalo Montero & Sergio Pacheco Fernández

**-Proyecto Backend:** Este repositorio contiene el proyecto Java Spring Boot el cual se trata de una API-REST implementada en el lenguaje de programación Java 8.

**-Requisitos para ejecutar la aplicación:**
  - Tener instalado un jdk en tu dispositivo (en nuestro caso jdk 1.8).
  - IDE capaz de ejecutar proyectos Java Spring (en nuestro caso Eclipse).
  
**-Pasos a seguir para inicializar el proyecto:**

  1. Ejecutar los comandos:                                                          
    - ``` mvn clean ``` : Elimina los archivos generados anteriormente y descarga las librerías añadidas como dependencias en el archivo [pom.xml](https://github.com/myscel/tfg-estudio-medico-back/tree/master/tfg-estudio-medico-2019/pom.xml).                                          
    - ``` mvn install ``` : Genera el archivo .jar a partir del [pom.xml](https://github.com/myscel/tfg-estudio-medico-back/tree/master/tfg-estudio-medico-2019/pom.xml).
     
     ![Clean Install](imgs/cleaninstall.PNG)

    
  2. Si queremos ejecutar la aplicación backend de manera local, lo único que tenemos que hacer es
ejecutar la clase [TfgEstudioMedico2019Application](https://github.com/myscel/tfg-estudio-medico-back/tree/master/tfg-estudio-medico-2019/src/main/java/com/example/tfgestudiomedico2019/TfgEstudioMedico2019Application.java), la cual contiene el método *main*.

  3. Si queremos ejecutar la aplicación backend de manera remota, solo tenemos que desplegar el archivo .jar generado anteriormente en el servicio de Hosting escogido.

