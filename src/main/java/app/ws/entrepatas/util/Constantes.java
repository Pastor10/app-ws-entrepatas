package app.ws.entrepatas.util;

import java.io.File;

public class Constantes {
    public static final String FOLDER_SEPARATOR = File.separator;
    public static final String FOLDER_IMAGES = "public" + FOLDER_SEPARATOR +"imagenes"+ FOLDER_SEPARATOR;
    public static final String FILES_TIPO_EVENTO = FOLDER_IMAGES + "tipoevento";
    public static final String FILES_EVENTOS = FOLDER_IMAGES + "eventos";
    public static final String FILES_PUBLICACION = FOLDER_IMAGES + "publicaciones";
    public static final String FILES_USERS = FOLDER_IMAGES + "users";
    public static final String PATH_IMAGE_NOT_USER = "src".concat(FOLDER_SEPARATOR).concat("main");
    public static final String PERFIL_VISITANTE = "VISITANTE";
    public static final String PERFIL_ADMINISTRADOR = "ADMIN";
}
