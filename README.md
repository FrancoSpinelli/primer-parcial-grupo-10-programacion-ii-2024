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

class CasaMatriz {
  +CasaMatriz(Admin, Auto, Oficina)
  
  +login(): Persona
  +logout(): void
}


class Oficina {
  -sucursalId: int
  -dirección: String
  -teléfono: String
  
  +Oficina(Vendedor, String, String)
}

class Persona{
  -id: int
  -nombre: String
  -fechaNacimiento: Date
  -documento: int
  -teléfono: String
  -email: String
  -contrasenia: String

  +Persona(int, String, Date, int, String, String)
}


class Admin{
  +Admin(Persona)
  
  +listar(): void
  +crear(): void
  +eliminar(): void
  +asignarAutoAOficina() void
}


class Cliente{
  -favoritos: Auto
  
  +Cliente(Persona)
  
  +agregarFavoritos(Auto): void
  +sacarFavoritos(Auto): void
  +crear(): void
  +retirarAutos(Reserva): void
  +devolverAuto(Reserva, Oficina): void
}


class Vendedor{
  +Vendedor(Persona)
  
  +listarReservasPendientes():void
  +aceptarReserva(Reserva): void
  +rechazarReserva(Reserva): void
  +entregarAuto(Reserva): void
}



class Reserva {
  -precioFinal: float

  +Reserva(Auto, Cliente, Date, Date)  
  
  +crearReserva(): void
  +rechazarReserva(): void
  +aceptarReserva(): void
}

class Fecha {
  -inicio: Date
  -fin: Date
  
  +Fecha(Date, Date)
  
  +estaEnRango(Date): bool
  +calcularDias(): int
}

class Auto{
  -patente: String
  -marca: Marca
  -modelo: String
  -color: Colores
  -disponibilidad: bool
  -precioPorDia: float
  -oficinaActual: String

  +Auto(String, String, String, String, float, float, String)  
  
  +validarDisponibilidad(Date, Date): bool
  +calcularPrecioTotal(int): float
  +verListadoAutos(): void
  +entregarAuto(): void
  
  -transportarAOficinaOriginal(): void
}

class Gasolina {
  -capacidadMaxima: float
  -cantidad: float
  
  +Gasolina(float, float)

  +consultarGasolina(): void
  +recargarGasolina(): void
  +consumirGasolina(): void
}


'INTERFACES

interface Listador {
  +listar(): void
}

interface Creador {
  +crear(): void
}
interface Eliminador {
  +eliminar(): void
}

interface Menu {
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

Main ..> Oficina
Main ..> Admin
Main ..> Auto

Main -- CasaMatriz

CasaMatriz "1"*-->"*"Admin: admins
CasaMatriz "1"*-->"*" Auto: autos
CasaMatriz "1"*-->"*" Oficina: oficinas
CasaMatriz  -->"*" Cliente: clientes

Persona <|-- Admin
Persona <|-- Vendedor
Persona <|-- Cliente


Persona --|> Menu
Persona --|> Listador
Persona --|> Creador
Persona --|> Eliminador


MenuAdmin  ..> Menu
MenuVendedor ..> Menu
MenuCliente ..> Menu


ListadorAutos ..> Listador
ListadorClientes ..> Listador
ListadorOficinas ..> Listador
ListadorReservas ..> Listador

CreadorAutos ..> Creador
CreadorClientes ..> Creador
CreadorOficinas ..> Creador
CreadorReservas ..> Creador

EliminadorAutos ..> Eliminador
EliminadorClientes ..> Eliminador
EliminadorOficinas ..> Eliminador
EliminadorReservas ..> Eliminador

Oficina -->"*" Reserva: reservas
Oficina o--> Vendedor
Oficina o-->"*" Auto: autos

Cliente --->"*" Reserva: reservas

Reserva -->"*" Auto: autos
Reserva -->"1" EstadoReserva
Reserva *-->"1" Fecha: fecha

Auto -->"1" Gasolina: gasolina
Auto -->"1" Marca: marca
Auto -->"1" Color: color

@enduml
```

## Diagrama de Casos de Uso

```plantuml
@startuml
!theme toy

left to right direction

actor "Client" as cl
actor "Vendedor" as ve
actor "Admin" as ad


rectangle RentACar {
  usecase "Login" as Login
  usecase "Logout" as Logout
  usecase "Agregar a favorito" as AFavoritos
  usecase "Eliminar de favorito" as EFavoritos
  usecase "Retirar auto" as RetAuto
  usecase "Devolver auto" as DevAuto
  usecase "Listar autos" as ListarAutos
  usecase "Crear reserva" as Reservar
  usecase "ListarReservas" as ListarReservas
  usecase "Aceptar reserva" as AcepReservar
  usecase "Rechazar reserva" as RecReservar
  usecase "Entregar Auto" as EntregarAuto
  usecase "Crear auto" as CrearAuto
  usecase "Eliminar auto" as EliminarAuto
  usecase "Listar clientes" as ListarClientes
  usecase "Crear cliente" as CrearCliente
  usecase "Eliminar cliente" as EliminarCliente
  usecase "Listar oficinas" as ListarOficina
  usecase "Crear oficina" as CrearOficina
  usecase "Eliminar oficina" as EliminarOficina
  usecase "Asignar auto a oficina" as AsignarAutoAOfi
  usecase "Eliminar Reserva" as EliminarReserva
}

cl --> Login
cl --> Logout
cl --> ListarAutos
cl --> Reservar
cl --> AFavoritos
cl --> EFavoritos
cl --> RetAuto
cl --> DevAuto
cl --> Reservar
cl --> ListarReservas

ve --> Login
ve --> Logout
ve --> Reservar
ve --> ListarReservas
ve --> AcepReservar
ve --> RecReservar
ve --> EntregarAuto

ad --> Login
ad --> Logout
ad --> CrearAuto
ad --> EliminarAuto
ad --> ListarClientes
ad --> CrearCliente
ad --> EliminarCliente
ad --> ListarOficina
ad --> CrearOficina
ad --> EliminarOficina
ad --> AsignarAutoAOfi
ad --> EliminarReserva


@enduml
```