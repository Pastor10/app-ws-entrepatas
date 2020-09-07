package app.ws.entrepatas.enums;

public enum ErrorCode {


    V001("0001","El documento ya se encuentra registrado"),
    V002("0002","No existe el valor consultado"),
    V003("0003","El valor ingresado es incorrecto"),
    V004("0004","El celular ya se encuentra registrado"),
    V005("0005","No se pudo cargar la imagen"),
    V006("0006","Usted ya tiene una solicitud registrada a esta publicación"),
    V007("0007","El usuario ya se encuentra registrado."),
    V008("0008","Ya existe una solicitud pendiente para este doumento."),
    V013("0013","No se pudo realizar LA SUBIDA DE ARCHIVOS"),
    V018("0018","Cuenta de usuario inactiva"),
    V019("0019","La contraseña actual es incorrecta"),
    V020("0020","Ingrese todos los campos requeridos"),
    V021("0021","Las nuevas contraseñas ingresadas no coinciden"),
    V022("0022","Usuario o contraseña incorrectos"),
    V023("0023","Su sesión ha expirado"),
    V024("0024","Error de autenticación"),
    V025("0025","El correo no se encuentra registrado"),
    V026("0026","Código de cambio de contraseña no existe"),


    V030("0030","Valor de la puntuación debe ser entre 1 y 5"),
    V031("0031","El correo electrónico ya se encuentra registrado"),

    V100("0100","Token de restaurante no configurado"),
    V101("0101","Token inválido"),

    V110("0110","Slug inválido"),
    V111("0111","Slug ya existe"),

    V200("0200","Dirección no encontrada");

    String code;
    String message;

    ErrorCode(String code, String message){
        this.code = code;
        this.message=message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
