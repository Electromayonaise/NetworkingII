/**
Este ejemplo permite consolidar los conocimientos sobre Cliente/Servidor y 
el protocolo HTTP. Tomado de Computer Networking de Kurose & Ross, segunda 
edici�n, p�ginas 151-155.

Este "servidor web":
1. Manipula s�lo una solicitud HTTP
2. Acepta y analiza sint�cticamente (parse) la solicitud HTTP
3. Busca el archivo solicitado en el sistema de archivos del servidor
4. Crea un mensaje de respuesta HTTP consistente de l�neas de encabezado y luego el archivo solicitado
5. Envia la respuesta directamente al cliente

Si lo desea probar este c�digo primero debe cambiar el nombre a ServidorWebSimple.java
y luego compilar el c�digo:

	c:\>javac ServidorWebSimple.java

luego debe ejecutar el servidor

	c:\>java ServidorWebSimple

y, desde un browser instalado en el mismo equipo debe escribir el URL como:

	http://127.0.0.1:6789/miarchivo.html

NOTA: asegurese que miarchivo.html est� en el mismo directorio que el ServidorWebSimple
*/

// Se importan los paquetes java.io y java.net
// Tambi�n se importa el paquete java.util que contiene la clase StringTokenizer que es la utilizada para 
// analizar sint�cticamente los mensajes de solicitud HTTP
import java.io.*; 
import java.net.*; 
import java.util.*; 
class ServidorWebSimple {
   public static void main(String argv[]) throws Exception 
   {

      // El objeto lineaDeLaSolicitudHttp es una cadena que contiene
      // la primera linea del mensaje de solicitud HTTP
      // El objeto nombreArchivo contendr� el nombre del archivo solicitado
      String lineaDeLaSolicitudHttp; 
      String nombreArchivo; 

      // El socket socketdeEscucha atender� servicios en el puerto 6789 
      ServerSocket socketdeEscucha = new ServerSocket(6789); 

      // el m�todo accept() de socketdeEscucha craer� un nuevo objeto: socketdeConexion
      Socket socketdeConexion = socketdeEscucha.accept(); 

      // ahora se crean dos "streams": mensajeDesdeCliente y mensajeParaCliente
      BufferedReader mensajeDesdeCliente = 
         new BufferedReader(new InputStreamReader(
            socketdeConexion.getInputStream())); 
      DataOutputStream mensajeParaCliente = 
         new DataOutputStream(
            socketdeConexion.getOutputStream());
 
     // se lee la primera linea del mensaje HTTP
     // se supone que esta linea tiene la forma: 
     //      GET nombre_archivo HTTP/1.0
     lineaDeLaSolicitudHttp = mensajeDesdeCliente.readLine(); 

     // Ahora el servidor debe analizar la l�nea para extraer el nombre 
     //del archivo solicitado
     // El objeto lineaSeparada puede imaginarse como la linea de la solicitud HTTP
     // separa en tres partes: "GET", "nombre_archivo" y "HTTP/1.0"
     StringTokenizer lineaSeparada =
        new StringTokenizer(lineaDeLaSolicitudHttp); 

     // Se comprueba que la primera parte de lineaSeparada sea el m�todo GET
     if (lineaSeparada.nextToken().equals("GET")) {

        // Si el m�todo es GET, entonces la siguiente palabra es el nombre
        // del archivo que el browser est� solicitando 
        nombreArchivo = lineaSeparada.nextToken(); 

        // Si el nombre del archivo trae un slash "/", se quita
        if (nombreArchivo.startsWith("/") == true) 
           nombreArchivo = nombreArchivo.substring(1); 

        File archivo = new File(nombreArchivo); 

        // La siguiente l�nea asocia un "stream", archivoDeEntrada, al archivo nombreArchivo
        FileInputStream archivoDeEntrada = new FileInputStream(nombreArchivo); 

        // las siguientes dos l�neas determinan el tama�o del archivo y construyen 
        // un arreglo de bytes de ese tama�o
        int cantidadDeBytes = (int) archivo.length(); 
        byte[] archivoEnBytes = new byte[cantidadDeBytes]; 
 
        // La siguiente l�nea lee desde el "stream" archivoDeEntrada y lo
        // coloca en el arreglo archivoEnBytes
        archivoDeEntrada.read(archivoEnBytes);
 
        // ahora el servidor se dispone a construir el mensaje de respuesta
        // para el browser, colocando la l�nea de respuesta en el "stream" mensajeParaCliente
        mensajeParaCliente.writeBytes("HTTP/1.0 200 Document Follows\r\n"); 

        // las siguientes l�neas crean los encabezados del mensaje HTTP
        // en caso de enviar una imagen en formato GIF o en formato JPEG
        if (nombreArchivo.endsWith(".jpg"))
           mensajeParaCliente.writeBytes("Content-Type: image/jpeg\r\n"); 
        if (nombreArchivo.endsWith(".gif")) 
           mensajeParaCliente.writeBytes("Content-Type: image/gif\r\n");

        // Construye luego el encabezado para indicar la longitud del archivo
        mensajeParaCliente.writeBytes("Content-Length: " + cantidadDeBytes + "\r\n");

        // Ahora envia la linea en blanco que estipula el RFC de HTTP/1.0
        mensajeParaCliente.writeBytes("\r\n"); 

        // Finalmente env�a el archivo solicitado al cliente
        mensajeParaCliente.write(archivoEnBytes, 0, cantidadDeBytes);

        // Despu�s de enviar el archivo, el servidor cierra el socket de conexi�n
        socketdeConexion.close();
        }

     // Si se utiliza un m�todo diferente a GET se env�a un mensaje de error
     else System.out.println("Bad Request Message");
     }
}