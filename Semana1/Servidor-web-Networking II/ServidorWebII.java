import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ServidorWebII {
    public static void main(String argv[]) throws Exception {
        int puerto = 6789;
        ServerSocket serverSocket = new ServerSocket(puerto);
        ExecutorService executor = Executors.newFixedThreadPool(3);

        while (true) {
            Socket sc = serverSocket.accept();
            SolicitudHttp solicitud = new SolicitudHttp(sc);
            executor.execute(solicitud);
        }
    }
}

final class SolicitudHttp implements Runnable {
    static final String CRLF = "\r\n";
    Socket socket;

    public SolicitudHttp(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            proceseSolicitud();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void proceseSolicitud() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());

        String lineaDeSolicitud = br.readLine();
        System.out.println("Solicitud recibida: " + lineaDeSolicitud);

        StringTokenizer partesLinea = new StringTokenizer(lineaDeSolicitud);
        partesLinea.nextToken();  // "salta" sobre el método, se supone que debe ser "GET"
        String nombreArchivo = partesLinea.nextToken();
        nombreArchivo = "." + nombreArchivo;

        FileInputStream fis = null;
        boolean existeArchivo = true;
        try {
            fis = new FileInputStream(nombreArchivo);
        } catch (FileNotFoundException e) {
            existeArchivo = false;
        }

        String lineaDeEstado = null;
        String lineaDeTipoContenido = null;
        String cuerpoMensaje = null;
        if (existeArchivo) {
            lineaDeEstado = "HTTP/1.1 200 OK" + CRLF;
            lineaDeTipoContenido = "Content-type: " + contentType(nombreArchivo) + CRLF;
        } else {
            lineaDeEstado = "HTTP/1.1 404 Not Found" + CRLF;
            lineaDeTipoContenido = "Content-type: text/html" + CRLF;
            cuerpoMensaje = "<HTML>" +
                            "<HEAD><TITLE>404 Not Found</TITLE></HEAD>" +
                            "<BODY><b>404</b> Not Found</BODY></HTML>";
        }

        os.writeBytes(lineaDeEstado);
        os.writeBytes(lineaDeTipoContenido);
        os.writeBytes(CRLF);

        if (existeArchivo) {
            enviarBytes(fis, os);
            fis.close();
        } else {
            os.writeBytes(cuerpoMensaje);
        }

        os.close();
        br.close();
    }

    private static void enviarBytes(FileInputStream fis, OutputStream os) throws Exception {
        byte[] buffer = new byte[1024];
        int bytes = 0;
        while ((bytes = fis.read(buffer)) != -1) {
            os.write(buffer, 0, bytes);
        }
    }

    private static String contentType(String nombreArchivo) {
        if (nombreArchivo.endsWith(".htm") || nombreArchivo.endsWith(".html")) {
            return "text/html";
        }
        if (nombreArchivo.endsWith(".gif")) {
            return "image/gif";
        }
        if (nombreArchivo.endsWith(".jpeg") || nombreArchivo.endsWith(".jpg")) {
            return "image/jpeg";
        }
        return "application/octet-stream";
    }
}
