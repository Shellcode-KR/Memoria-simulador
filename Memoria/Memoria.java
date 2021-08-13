/*
 * pedir desde teclado la  cantidad de memoria,
        cantidad para el sistema operativo y pedir los proceso, 
   a los proceso se les debe dar 
    cantidad memoria y tiempo 
 */
 

/**
 *
 * @author FLAViO
 */
import java.util.LinkedList;
import java.util.Scanner;
//import java.util.Queue;

public class Memoria {

    //Cosas necesarias
    int memoTotal;
    int memoSO;
    int memoSobrante;

    //tiempo
    int[] tiempo = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

    //LISTA DE PROCESOS
    Proceso[] memoriaUso = new Proceso[15];
    //Queue<Proceso> encola = new LinkedList<Proceso>();
    //Scanner
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Memoria m1 = new Memoria();
        m1.crearMemandSO();
        m1.crearProcesos();
        m1.estado();
        for (int i = 0; i < m1.tiempo.length; i++) {

            System.out.println("TIEMPO " + m1.tiempo[i] + " : ");
            m1.estado();

            for (int i2 = 0; i2 < m1.memoriaUso.length; i2++) {
                if (m1.memoriaUso[i2] != null) {
                    //System.out.println(m1.memoriaUso[i]);
                    if (m1.memoriaUso[i2].getTiLlegada() == m1.tiempo[i]) {
                        System.out.println("Se empezo a atender el proceso #"+i2);

                    }
                    if (m1.memoriaUso[i2].getTiLlegada() <= m1.tiempo[i]) {
                        m1.memoriaUso[i2].atenderProceso();
                    }

                    if (m1.memoriaUso[i2].tiempoMemoria == 0) {
                        System.out.println("El Proceso #" + i2 + "salio de memoria");
                        m1.memoSobrante += m1.memoriaUso[i2].memoriaOcupada;
                        m1.memoriaUso[i2] = null;

                    }
                }

            }

            try {
                Thread.sleep(1500);
            } catch (InterruptedException ex) {
                System.out.println("Error");
            }

        }

    }

    //crea los procesos
    public void crearMemandSO() {
        //Declaracion de la memoria maxima

        System.out.println("Declara Memoria Maxima");
        memoTotal = sc.nextInt();
        memoSobrante = memoTotal;

        //Creacion del sistema operativo
        System.out.println("Declare el tamaño del SO en Memoria");
        do {
            memoSO = sc.nextInt();
            if (memoSO > memoTotal) {
                System.out.println("Eltamaño del SO tiene que ser menor que el de la memoria");
            }
            if (memoSO == 0) {
                System.out.println("Introduce un tamaño de SO valido");
            }
        } while (memoSO > memoTotal || memoSO == 0);

        memoSobrante -= memoSO;
        Proceso sisO = new Proceso(memoSO, true);
        memoriaUso[0] = sisO;
        System.out.println("");

        estado();
    }

    public void crearProcesos() {
        int creando = 9;
        int mempro1;
        int timeMem;
        while (creando == 9) {
            System.out.println("Se crearan procesos\n 1.Crear Proceso, si desea salir dijite 9");
            creando = sc.nextInt();
            switch (creando) {
                case 1:
                    //asignar memoria de un proceso
                    do {
                        System.out.println("Memoria del Proceso");
                        mempro1 = sc.nextInt();
                        if (memoSobrante < mempro1) {
                            System.out.println("NO hay suficiente memoria, memoria restante: " + memoSobrante);
                        }

                    } while (memoSobrante < mempro1 || mempro1 == 0);
                    memoSobrante -= mempro1;
                    //asignando tiempo en memoria
                    System.out.println("Tiempo en memoria");
                    timeMem = sc.nextInt();

                    //creando proceso
                    for (int i = 0; i < memoriaUso.length; i++) {
                        while (memoriaUso[i] != null) {
                            if ((i + 1) == memoriaUso.length) {
                                System.out.println("No Funciona");
                            }
                            i++;
                        }
                        memoriaUso[i] = new Proceso(mempro1, timeMem, i, i);
                        System.out.println("Se creo el proceso #"+i+"correctamente");

                        /* if (memoriaUso[i] != null) {

                            memoriaUso[i + 1] = new Proceso(mempro1, timeMem);
                            System.out.println(memoriaUso[1]);
                        }*/
                        System.out.println("Memoria sobrante: " + memoSobrante);
                        creando = 9;
                        break;

                    }

                    break;
                case 9:
                    creando = 2;
                    break;
                default:
                    System.out.println("introdusca un numero valido: 1 o 9");
                    creando = 9;

            }
        }
        estado();
        System.out.println("");
    }
    //Colsulta el estado actual de la memoria

    public void estado() {
        System.out.println("ID || Memoria Ocupada || Tiempo en Memoria || ATENDIDO||llegada");
        for (int i = 0; i < memoriaUso.length; i++) {
            if (memoriaUso[i] != null) {
                System.out.println(memoriaUso[i]);
            }

        }
        System.out.println("MEMORIA SOBRANTE: " + memoSobrante);
    }
}

