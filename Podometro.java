/**
 * La clase modela un sencillo podómetro que registra información
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
    // Constantes de días de la semana:
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
     * Inicializa el podómetro con la marca indicada por el parámetro.
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
     * Simula la configuración del podómetro.
     * Recibe como parámetros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna además el valor adecuado al atributo longitudZancada
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
     *  Recibe cuatro parámetros que supondremos correctos:
     *    pasos - el nº de pasos caminados
     *    dia - nº de día de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - Sábado, 7 - Domingo)
     *    horaInicio – hora de inicio de la caminata
     *    horaFina – hora de fin de la caminata
     *    
     *    A partir de estos parámetros el método debe realizar ciertos cálculos
     *    y  actualizará el podómetro adecuadamente  
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
     * Muestra en pantalla la configuración del podómetro
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
     * Muestra en pantalla información acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        double totalDistanciaSemana = (totalPasosLaborables * longitudZancada) + 
            (totalPasosSabado * longitudZancada) +
            (totalPasosDomingo * longitudZancada);
        double totalDistanciaFinSemana = (totalPasosSabado * longitudZancada) +
            (totalPasosDomingo * longitudZancada);
        System.out.println("Estadisticas" + "\n########################" 
            + "\nDistancia recorrida toda la semana: " + totalDistanciaSemana 
            / 100000 + "Km" + "\nDistancia recorrida fin de semana: " + 
            totalDistanciaFinSemana / 100000 + "Km" + "\n" + "\n" +
            "\nNº pasos días laborables: " 
            + totalPasosLaborables + "\nNº pasos SÁBADO: "+ totalPasosSabado
            + "\nNº pasos DOMINGO: "+ totalPasosDomingo + "\n" + "\n" + 
            "\nNº Caminatas realizadas a partir de las 21hH.:" 
            + caminatasNoche + "\n" + "\n" + 
            "\nTiempo total caminado en la semana: " + tiempo / 60 + "h. y " 
            + tiempo % 60 + "m." );
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se ha caminado más pasos - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        String dia = "";
        if (totalPasosLaborables > totalPasosSabado && 
        totalPasosLaborables > totalPasosDomingo){
            dia = "LABORABLES";
        }
        else if (totalPasosSabado > totalPasosDomingo && 
        totalPasosSabado > totalPasosLaborables){
            dia = "SÁBADO";
        }
        else {
            dia = "DOMINGO";
        }
        return dia;
    }

    /**
     * Restablecer los valores iniciales del podómetro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no varía
     *  
     */    
    public void reset() {
        sexo = MUJER;
        altura = 0; 
        longitudZancada = 0;
    }
}
