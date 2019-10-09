/**
 * La clase modela un sencillo pod�metro que registra informaci�n
 * acerca de los pasos, distancia, ..... que una persona (hombre o mujer)
 * ha dado en una semana. 
 * 
 * @author    - Asier Gogorza - 
 * 
 */
public class Podometro {
    // Constantes de Sexo:
    private final char MUJER = 'M';
    private final char HOMBRE = 'H';
    // Constantes de Zancada:
    private final double ZANCADA_HOMBRE = 0.45;
    private final double ZANCADA_MUJER = 0.41;
    // Constantes de d�as de la semana:
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    // Atributos:
    private double altura;
    private char sexo;
    private String marca;
    private double longitudZancada;
    private int totalDistanciaSemana;
    private int tiempo;
    private int totalDistanciaFinSemana;
    private int caminatasNoche;
    private int totalPasosLaborables;
    private int totalPasosSabado;
    private int totalPasosDomingo;

    /**
     * Inicializa el pod�metro con la marca indicada por el par�metro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca) {
        sexo = 'M';
        altura = 0; 
        marca = queMarca;
        longitudZancada = 0;
    }

    /**
     * Mediante este metodo veras la marca de tu dispositivo.
     *  
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Simula la configuraci�n del pod�metro.
     * Recibe como par�metros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna adem�s el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) {
        altura = queAltura ;
        sexo = queSexo;
        if (sexo == MUJER) {
            longitudZancada = Math.floor(altura * ZANCADA_MUJER);
        }
        else {
            longitudZancada = Math.ceil(altura * ZANCADA_HOMBRE);
        }
    }

    /**
     *  Recibe cuatro par�metros que supondremos correctos:
     *    pasos - el n� de pasos caminados
     *    dia - n� de d�a de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - S�bado, 7 - Domingo)
     *    horaInicio � hora de inicio de la caminata
     *    horaFina � hora de fin de la caminata
     *    
     *    A partir de estos par�metros el m�todo debe realizar ciertos c�lculos
     *    y  actualizar� el pod�metro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,int horaFin) {
        String nombreDia;
        switch (dia){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: nombreDia = "Dias laborables";
            break;
            case 6: dia = SABADO;
            break;
            case 7: dia = DOMINGO;
            break;
        }
        if (dia <= 5){
            totalPasosLaborables = pasos + totalPasosLaborables;
        }
        else if (dia == 6){
            totalPasosSabado = pasos + totalPasosSabado;
        }
        else {
            totalPasosDomingo = pasos + totalPasosDomingo;
        }
        if (horaInicio >= 2100){
            caminatasNoche++;
        }
        int totalHoraInicio = (horaInicio / 100) * 60 + (horaInicio % 100);
        int totalHoraFin = (horaFin / 100) * 60 + (horaFin % 100);
        int minutosCaminata = totalHoraFin - totalHoraInicio;
        tiempo = tiempo + minutosCaminata;
    }

    /**
     * Muestra en pantalla la configuraci�n del pod�metro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        System.out.println("#########################" + "\nAltura: " + altura * 0.01
            + "mtos" + "\nSexo: " + sexo 
            + "\nLongitud zancada: " + longitudZancada * 0.01 
            + "mtos" + "\n#########################");
    }

    /**
     * Muestra en pantalla informaci�n acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstad�sticas() {
        double totalDistanciaSemana = (totalPasosLaborables * longitudZancada) + 
            (totalPasosSabado * longitudZancada) +
            (totalPasosDomingo * longitudZancada);
        double totalDistanciaFinSemana = (totalPasosSabado * longitudZancada) +
            (totalPasosDomingo * longitudZancada);
        System.out.println("Estadisticas" + "\n########################" 
            + "\nDistancia recorrida toda la semana: " + totalDistanciaSemana 
            / 100000 + "Km" + "\nDistancia recorrida fin de semana: " + 
            totalDistanciaFinSemana / 100000 + "Km" + "\n" + "\n" +
            "\nN� pasos d�as laborables: " 
            + totalPasosLaborables + "\nN� pasos S�BADO: "+ totalPasosSabado
            + "\nN� pasos DOMINGO: "+ totalPasosDomingo + "\n" + "\n" + 
            "\nN� Caminatas realizadas a partir de las 21hH.:" 
            + caminatasNoche + "\n" + "\n" + 
            "\nTiempo total caminado en la semana: " + tiempo / 60 + "h. y " 
            + tiempo % 60 + "m." );
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del d�a
     *  en el que se ha caminado m�s pasos - "S�BADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        String dia = "";
        if (totalPasosLaborables > totalPasosSabado && 
        totalPasosLaborables > totalPasosDomingo){
            dia = "LABORABLES";
        }
        else if (totalPasosSabado > totalPasosDomingo && 
        totalPasosSabado > totalPasosLaborables){
            dia = "S�BADO";
        }
        else {
            dia = "DOMINGO";
        }
        return dia;
    }

    /**
     * Restablecer los valores iniciales del pod�metro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no var�a
     *  
     */    
    public void reset() {
        sexo = MUJER;
        altura = 0; 
        longitudZancada = 0;
    }
}
