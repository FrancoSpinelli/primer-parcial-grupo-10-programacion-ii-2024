# Proyecto Rent A Car

## Descripción
Primer parcial de programación II de la carrera Infórmatica Aplicada de INSPT-UTN.
Comisión 2.603

## Docentes
- Prof. Dr. Diego Corsi
- Prof. Matías Ávalos
  
## Integrantes
- [Hugo Miranda Campos](https://github.com/hvgomiranda)
- [Franco Andrés Spinelli](https://github.com/FrancoSpinelli)

## Consigna
Desarrolle un sistema para gestionar la información de las reservas de una empresa dedicada al
alquiler de automóviles, teniendo en cuenta que:

- En un momento dado, un determinado cliente puede tener varias reservas.
- De cada cliente se desean almacenar su DNI, nombre, dirección, e-mail y teléfono. Además, dos clientes se diferencian por un código único.

- Una reserva la realiza un único cliente, pero puede involucrar varios vehículos.
  
- Es importante registrar la fecha de inicio y final de la reserva, el precio del alquiler de cada uno de los vehículos, los litros de gasolina en el depósito en el momento de realizar la reserva, el precio total de la reserva y un indicador de si el vehículo o los vehículos han sido entregados.
- Todo vehículo queda disponible en la oficina donde es devuelto hasta que se lo transporte de regreso a su ubicación original. De cada vehículo se requiere la patente, el modelo, el color y la marca.
- Cada reserva se hace en una determinada oficina, ya sea personalmente, por teléfono o por
e-mail.
- Al sistema podrán acceder tres tipos de usuarios: clientes (que sólo lo podrán consultar), vendedores (que administrarán las reservas y a los clientes) y administradores (que administrarán los vehículos y a los tres tipos de usuarios).


Para ello:
- Analice los requerimientos anteriores
  
- Determine los objetos requeridos para implementar ese sistema
  
- Establezca los atributos que deben tener estos objetos
  
- Fije los comportamientos que exhibirán estos objetos
  
- Especifique la forma en que los objetos deben interactuar entre sí para cumplir con los requerimientos del sistema
  
El sistema deberá utilizar abstracción, encapsulamiento, herencia, polimorfismo y persistencia (no BD).

La E/S del sistema será exclusivamente por consola (no GUI).

Se deberán subir a GitLab o GitHub el ejecutable (en formato jar), el código fuente, la documentación (generada con javadoc) y los diagramas UML de caso-uso, de clases y uno de secuencia (generados con http://plantuml.com/es o http://www.planttext.com y grabados en formato png).


## Diagrama de Clases

```plantuml

@startuml
!theme reddress-darkorange

class Main{
  +Main(): void
}

class Auto{
  -int id: int
  -String patente: String
  -String modelo: String
  -float precioPorDia: float
  -Oficina oficinaOriginal: Oficina
  -Oficina oficinaActual: Oficina
  -Gasolina gasolina: Gasolina
  -Color color: Color
  -Marca marca: Marca

  +Auto(String, String, float, Color, Marca, Gasolina)  
  +toString(): String
  +toString(boolean): String
  +validarDisponibilidad(Date, Date): bool
  +calcularPrecioTotal(int): float
  +verAuto(): String
  +getId(): int
  +getPrecioPorDia(): float
  +getOficinaOriginal(): Oficina
  +getPatente(): String
  +setOficinaActual(Oficina): void
  +setOficinaOriginal(Oficina): void
  +verListadoAutos(): void
  +entregarAuto(): void
  +consumirGasolina(int): void
  +consultarGasolina(): void
  +recargarGasolina(): void
  +tieneTanqueLleno(): boolean
  +estaEnOficinaOriginal(): boolean
  -transportarAOficinaOriginal(): void
}

class Gasolina {
  -capacidadMaxima: float
  -cantidad: float
  -consumoDiario: float
  
  +Gasolina(float)

  +tieneTanqueLleno(): bool
  +consultarGasolina(Auto): void
  +recargarGasolina(): void
  +consumirGasolina(int): void
  -calcularPorcentajeDeTanque(): float
}

class CasaMatriz {
  -Enviroment enviroment
  -ArrayList<Persona> personas
  -ArrayList<Auto> autos
  -ArrayList<Oficina> oficinas

  +CasaMatriz()
  +setEnviroment(Enviroment e): void
  +login(): void
  +logout(Persona): void
  +deserializar(String): CasaMatriz
  +serializar(String): void
  +agregarPersona(Persona): void
  +agregarAuto(Auto): void
  +agregarOficina(Oficina): void
  +buscarPersona(int): Persona
  +eliminarPersona(Persona): void
  +getPersonas(): ArrayList<Persona> 
  +getOficinas(): ArrayList<Oficina>
  +getAutos(): ArrayList<Auto>
  +getReservas(): ArrayList<Reserva>
  +seleccionarPersona(ArrayList<Persona>): Persona
  -seleccionarPersona(int): Persona
  -getPersona(int): Persona
  +getVendedores(): ArrayList<Persona> 
  +getVendedoresSinOficina(): ArrayList<Persona> 
  +getOficinasSinVendedor(): ArrayList<Oficina> 
  +listarVendedores(): void
  -cantidaDeOficinas(): int
  +verListadoDeAutosPorOficina(): void
  +seleccionarOficina(): Oficina
  +seleccionarOficina(ArrayList<Oficina>): Oficina
  +Oficina seleccionarOficina(ArrayList<Oficina> ofis)
  +seleccionarOficina(int): Oficina
  +seleccionarAuto(Oficina oficina): Auto
  +generarIdReserva(): int
  +generarIdAuto(): int
  +generarIdOficina(): int
  +generarIdPersona(): int
  +listarPersonas(): void
  +listarAutos(): void
  +listarOficinas(): void
  +listarReservas(): void
  +crearAuto(): void
  +crearCliente(): void
  +crearAdmin(): void
  +crearVendedor(): void
  +crearOficina(): void
  +eliminarOficina(): void
  +eliminarAuto(): void
  +eliminarPersona(): void
  +crearReserva(): void
  +eliminarReserva(): void
  +aceptarReserva(): void
  +rechazarReserva():  void
  -preCargarDatos(): void
  +aceptarReserva(): void
  +rechazarReserva(): void
}

class Control {
  +ejecutar(): void
}

class EntradaSalida {
  +leerChar(): char
  +leerString(String): String
  +leerBoolean(String, String, String): bool
  +leerEntero(String): int
  +leerEnteroConLimites(String, int, int): int
  +leerFechaSinLimites(String): LocalDate
  +leerFechaAnteriorAHoy(String): LocalDate
  +leerFechaPosteriorAHoy(String): LocalDate
  +mostrarString(String): void
  +mostrarStringSinSalto(String): void
  +leerPassword(String): String
  +saltoDeLinea(): void
  +error(String): void
  +limpiarPantalla(): void
  +advertencia(String): void
  +cualquierTeclaParaContinuar(): void
  -leerFecha(String): LocalDate
}

class Fecha {
  -inicio: LocalDate
  -fin: LocalDate
  -cantDias: int
  
  +Fecha(LocalDate, LocalDate)

  +toString(): String
  
  +estaEnRango(LocalDate): bool
  +calcularFin(): LocalDate
  +getInicio(): LocalDat
  +getFin(): LocalDate
  +calcularCantDias(): int
}

class Formulario {
  +crearAuto(): Auto
  +crearPersona(): Persona
  +crearOficina(): Oficina
  -seleccionMarca(): Marca
  -seleccionColor(): Color
}

class Oficina {
  -id: int
  -dirección: String
  -teléfono: String
  -vendedor: Vendedor
  -autos: ArrayList<Auto>
  -reservas: ArrayList<Reserva>
  
  +Oficina(String, String)
  
  +toString(): String
  +asignarVendedor(Vendedor): void
  +agregarAuto(Auto): void
  +tieneAutos(): bool
  +verListadoAutos(): void
  +verListadoReservas(): void
  +verListadoReservasPendientes(): void
  +aceptarReserva(Reserva): void
  +getAutos(): ArrayList<Auto>
  +getAuto(id: int): Auto
  +getReservas(): ArrayList<Reserva>
  +getReserva(int): Reserva
  +agregarReserva(Reserva): void
  +getVendedor(): Vendedor
  +getId(): int
  +recibirAutos(ArrayList<Auto>): void
  +seleccionarReserva(EstadoReserva): Reserva
  +hayReservasPendientes(): bool
  +verOficina(): String
  -tieneVendedor(): bool
  -verListadoReservasPorEstado(EstadoReserva): void
}

class Persona{
  -id: int
  -dni: int
  -nombre: String
  -fechaNacimiento: LocalDate
  -teléfono: String
  -email: String
  -contrasenia: String
  -menu: CapacidadDeVerMenu

  +Persona(int, String, Date, int, String, String)

  +toString(): String
  +setMenu(CapacidadDeVerMenu): void
  +getMenu(): CapacidadDeVerMenu
  +getNombre(): String
  +getEmail(): String
  +coincideDni(int): boolean
  +coincideContrasenia(String): boolean
  +coincideUsuario(String): boolean
  +coincideUsuarioYContrasenia(String , String): boolean
  +verPersona(): String
  +getId(): int
  +getDni(): int

}


class Admin{

  +Admin(Persona)
  +gestionarPersonas(): void
  +gestionarAutos(): void
  +gestionarOficinas(): void
  +gestionarReservas(): void
  +asignarAutoAOficina(): void
  +asignarAutoAOficina(Auto, Oficina):  void
  +getRol(): Rol
  +asignarVendedorAOficina(Vendedor vendedor, Oficina oficina): void
  -setMenu(): void
  -listarPersonas(): void
  -crearVendedor(): void
  -crearCliente(): void
  -crearAdmin(): void
  -eliminarPersona(): void
  -asignarAutoAOficial(): void
  -mostarMenuGeneral(): void
  -crearPersona(): void
  -asignarVendedorAOficina(): void
  -trasladarAutosAOficinaDeOrigen(): void
}


class Cliente{
  -reservas: ArrayList<Reserva>
  -gReservas: CapacidadDeGestionarReservas
  
  +Cliente(Persona)
  
  +crear(): void
  +retirarAutos(): void
  +devolverAutos(): void
  +generarReserva():  void
  +verReservas(): void
  +pagarReserva(): void
  +getRol(): Rol
  +getReservas(): ArrayList<Reserva> 
  +validoParaAgregarAlCarrito(ArrayList<Auto>, Auto, Oficina): bool
}


class Vendedor{
  -oficina: Oficina
  -gReservas: CapacidadDeGestionarReservas

  +Vendedor(Persona)
  
  +toString(): String
  +listarReservasPendientes():void
  +aceptarReserva(): void
  +rechazarReserva(): void
  +entregarAuto(Reserva): void
  +validoParaEntregarAutos(Reserva): boolean
  +asignarOficina(Oficina oficina): void
  +getOficina(): Oficina
  +getRol(): Rol
}

class Reserva {
  -id: int
  -precioFinal: float
  -fechas: Fecha
  -autos: ArrayList<Auto>
  -estado: EstadoReserva
  -pagado: boolean
  -oficina: Oficina
  -cliente: Cliente

  +Reserva(ArrayList<Auto>, Cliente, Oficina, Date, int)
  
  +toString(): String
  +crearReserva(): void
  +aceptarReserva(): void
  +rechazarReserva(): void
  +entregarAutos(ArrayList<Auto>): void
  +devolverAutos(Oficina): void
  +finalizarReserva(): void
  +pagarReserva(): void
  +cancelarReserva(): void
  +getOficina(): Oficina

  -calcularPrecioFinal(): void
  +verReserva(): String
  +getEstado(): EstadoReserva
  +getCliente(): Cliente
  +getId(): int
  +retirarAutos(): void
  +getAutos(): ArrayList<Auto>
  -validoParaDevolver(): bool
  -validoParaEntregar(): bool
  -validoParaCancelar(): bool
  -calcularPrecioFinal(): float
}

class Validador{
  +validarRango(int, int, int ): bool
  +validarString(String): bool
  +validarEmail(String): bool
  +validarContrasenia(String): bool
  +validoParaCrearPersona(int, String): bool
  +patenteValida(String): bool
  +telefonoValido(String): bool
  +validarReservasParaEliminar(ArrayList<Reserva>): bool
  +dniDisponible(int): bool
  +correoDisponible(String): bool
}

'INTERFACES
interface CapazDeGestionarReserva{
  +verListadoReservasPorEstado(EstadoReserva): void
  +seleccionarReserva(EstadoReserva, Cliente, Oficina): Reserva
  +getReserva(int, Cliente): Reserva
}

interface CapazDeVerMenu {
  +verMenu(): void
  +seleccionar(): void
}

'ENUMS

enum EstadoReserva {
  CREADO
  RESERVADO
  PENDIENTE
  ENTREGADO
  DEVUELTO
  FINALIZADO
}

enum Marca {
  FORD
  FIAT
  CHVROLET
  BMW
  FERRARI
}

enum Color {
  BLANCO
  ROJO
  NEGRO
  AZUL
  GRIS
}

'RELACIONES

Main .down..> Oficina
Main ..right..> Admin
Main .down..> Auto

Main -down-- CasaMatriz

Main --> Control

CasaMatriz "1"*-up->"*"Admin: admins
CasaMatriz "1"*-down->"*" Auto: autos
CasaMatriz "1"*-up->"*" Oficina: oficinas
CasaMatriz -up->"*" Cliente: clientes

EntradaSalida <-- Persona
EntradaSalida <-- Admin
EntradaSalida <-- Cliente
EntradaSalida <-- Vendedor
EntradaSalida <-- Oficina
EntradaSalida <-- CasaMatriz
EntradaSalida <-- Reserva

Validador <-- EntradaSalida

CasaMatriz <-- Formulario

Persona <|---left- Admin
Persona <|---down- Cliente
Persona <|---down---- Vendedor

Persona *-up---> CapazDeGestionarReserva
Persona *-up-> CapazDeVerMenu

CapacidadDeVerMenuAdmin  .down.|> CapazDeVerMenu
CapacidadDeVerMenuVendedor .right.|> CapazDeVerMenu
CapacidadDeVerMenuCliente .left.|> CapazDeVerMenu

CapacidadDeGestionarReservas .down.|> CapazDeGestionarReserva

Oficina --->"*" Reserva: reservas
Oficina o-right--> Vendedor
Oficina o-->"*" Auto: autos

Cliente ---->"*" Reserva: reservas

Reserva -->"*" Auto: autos
Reserva -->"1" EstadoReserva
Reserva *-->"1" Fecha: fecha

Auto *-down-->"1" Gasolina: gasolina
Auto -down-->"1" Marca: marca
Auto -down-->"1" Color: color
@enduml
```

## Diagrama de Casos de Uso

```plantuml
@startuml

left to right direction

skinparam actorStyle Hollow

actor "Cliente" as cl  #magenta;line:black;line.bold;text:black
actor "Vendedor" as ve #cyan;line:black;line.bold;text:black
actor "Admin" as ad #yellow;line:black;line.bold;text:black


rectangle RentACar #grey;text:white{
  usecase "Login" as Login #black;line:white;line.bold;text:white
  usecase "Logout" as Logout #black;line:white;line.bold;text:white
  usecase "Agregar a favorito" as AFavoritos #magenta;line:black;line.bold;text:black
  usecase "Eliminar de favorito" as EFavoritos #magenta;line:black;line.bold;text:black
  usecase "Retirar auto" as RetAuto #magenta;line:black;line.bold;text:black
  usecase "Devolver auto" as DevAuto #magenta;line:black;line.bold;text:black
  usecase "Listar autos" as ListarAutos #magenta;line:black;line.bold;text:black
  usecase "Crear reserva" as Reservar #blue;line:black;line.bold;text:black
  usecase "ListarReservas" as ListarReservas #blue;line:black;line.bold;text:black
  usecase "Aceptar reserva" as AcepReservar #cyan;line:black;line.bold;text:black
  usecase "Rechazar reserva" as RecReservar #cyan;line:black;line.bold;text:black
  usecase "Entregar Auto" as EntregarAuto #cyan;line:black;line.bold;text:black
  usecase "Crear auto" as CrearAuto #yellow;line:black;line.bold;text:black
  usecase "Eliminar auto" as EliminarAuto #yellow;line:black;line.bold;text:black
  usecase "Listar clientes" as ListarClientes #yellow;line:black;line.bold;text:black
  usecase "Crear cliente" as CrearCliente #yellow;line:black;line.bold;text:black
  usecase "Eliminar cliente" as EliminarCliente #yellow;line:black;line.bold;text:black
  usecase "Listar oficinas" as ListarOficina #yellow;line:black;line.bold;text:black
  usecase "Crear oficina" as CrearOficina #yellow;line:black;line.bold;text:black
  usecase "Eliminar oficina" as EliminarOficina #yellow;line:black;line.bold;text:black
  usecase "Asignar auto a oficina" as AsignarAutoAOfi #yellow;line:black;line.bold;text:black
  usecase "Eliminar Reserva" as EliminarReserva #yellow;line:black;line.bold;text:black
}


cl --> Login #line:magenta;line.bold
cl --> Logout #line:magenta;line.bold
cl --> ListarAutos #line:magenta;line.bold
cl --> Reservar #line:magenta;line.bold
cl --> AFavoritos #line:magenta;line.bold
cl --> EFavoritos #line:magenta;line.bold
cl --> RetAuto #line:magenta;line.bold
cl --> DevAuto #line:magenta;line.bold
cl --> ListarReservas #line:magenta;line.bold

ve --> Login #line:cyan;line.bold
ve --> Logout #line:cyan;line.bold
ve --> Reservar #line:cyan;line.bold
ve --> ListarReservas #line:cyan;line.bold
ve --> AcepReservar #line:cyan;line.bold
ve --> RecReservar #line:cyan;line.bold
ve --> EntregarAuto #line:cyan;line.bold

ad --> Login #line:yellow;line.bold
ad --> Logout #line:yellow;line.bold
ad --> CrearAuto #line:yellow;line.bold
ad --> EliminarAuto #line:yellow;line.bold
ad --> ListarClientes #line:yellow;line.bold
ad --> CrearCliente #line:yellow;line.bold
ad --> EliminarCliente #line:yellow;line.bold
ad --> ListarOficina #line:yellow;line.bold
ad --> CrearOficina #line:yellow;line.bold
ad --> EliminarOficina #line:yellow;line.bold
ad --> AsignarAutoAOfi #line:yellow;line.bold
ad --> EliminarReserva #line:yellow;line.bold


@enduml
```