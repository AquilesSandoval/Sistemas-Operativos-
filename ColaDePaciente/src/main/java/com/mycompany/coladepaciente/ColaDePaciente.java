/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.coladepaciente;
// Clase Paciente

import java.util.Scanner;

class Paciente {
    private String nombre;
    private int prioridad;

    // Constructor
    public Paciente(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
    }

    // Getter para nombre
    public String getNombre() {
        return nombre;
    }

    // Getter para prioridad
    public int getPrioridad() {
        return prioridad;
    }

    @Override
    public String toString() {
        return "Paciente: " + nombre + ", Prioridad: " + prioridad;
    }
}

// Clase Nodo para la lista enlazada
class Nodo {
    Paciente paciente;
    Nodo siguiente;

    public Nodo(Paciente paciente) {
        this.paciente = paciente;
        this.siguiente = null;
    }
}

// Clase ColaPrioridad usando una lista enlazada
class ColaPrioridad {
    private Nodo cabeza;

    // Constructor
    public ColaPrioridad() {
        cabeza = null;
    }

    // Método para agregar pacientes en la posición correcta según su prioridad
    public void agregarPaciente(Paciente nuevoPaciente) {
        Nodo nuevoNodo = new Nodo(nuevoPaciente);

        // Si la lista está vacía o el nuevo paciente tiene mayor prioridad que el primero
        if (cabeza == null || nuevoPaciente.getPrioridad() < cabeza.paciente.getPrioridad()) {
            nuevoNodo.siguiente = cabeza;
            cabeza = nuevoNodo;
        } else {
            // Encontrar la posición adecuada
            Nodo actual = cabeza;
            while (actual.siguiente != null && actual.siguiente.paciente.getPrioridad() <= nuevoPaciente.getPrioridad()) {
                actual = actual.siguiente;
            }
            nuevoNodo.siguiente = actual.siguiente;
            actual.siguiente = nuevoNodo;
        }
    }

    // Método para atender al siguiente paciente (remover el paciente con mayor prioridad)
    public Paciente atenderPaciente() {
        if (cabeza == null) {
            System.out.println("No hay pacientes para atender.");
            return null;
        }
        Paciente siguientePaciente = cabeza.paciente;
        cabeza = cabeza.siguiente;
        return siguientePaciente;
    }

    // Método para verificar si la cola está vacía
    public boolean estaVacia() {
        return cabeza == null;
    }
}

// Clase principal
public class ColaDePaciente {
    public static void main(String[] args) {
        // Crear una cola de prioridad para pacientes
        ColaPrioridad colaDePacientes = new ColaPrioridad();
int i = 0;
Scanner sc = new Scanner(System.in);
Scanner sc1 = new Scanner(System.in);
        while(i==0){
            System.out.println("Agregar Paciente 1. Salir 0.");
            int eleccion=sc.nextInt();
            if(eleccion==1){
                System.out.println("Agregar Nombre");
                String nombre=sc1.nextLine();
                System.out.println("Agregar Prioridad");
                int prioridad =sc.nextInt();
                colaDePacientes.agregarPaciente(new Paciente(nombre, prioridad));
                
            } 
            else{
                i=1;
            }
        }
    
        // Atender pacientes según su prioridad
        System.out.println("Atendiendo a los pacientes en orden de prioridad:");
        while (!colaDePacientes.estaVacia()) {
            System.out.println(colaDePacientes.atenderPaciente());
        }
    }
}
