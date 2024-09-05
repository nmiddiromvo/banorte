# Ejercicio de Desarrollo en Equipo

## Instrucciones

### 1. Formar Equipos
   - Divídanse en equipos de hasta 4 personas para trabajar en el ejercicio.

### 2. Hacer un Fork del Proyecto
   - Luego, deben hacer un fork de este repositorio para comenzar a trabajar en tu propia copia.

### 3. Crear la API REST
   - Utilicen **Java Spring Boot** para desarrollar una API REST que manejará los datos de los movimientos de una cuenta bancaria (entradas y salidas de dinero).
   - Los datos deben incluir, al menos, los siguientes campos:
     - `id`
     - `amount`
     - `date`
     - `type` (entrada/salida)
     - `favorite` (indicador de favorito)

### 4. Maquetar la pantalla
   - A partir del siguiente [diseño en Figma](https://www.figma.com/design/UjRz2hm5r0ysFahd0J4a6f/Untitled?node-id=0-1&node-type=CANVAS&t=NJq23L6WEvoBj2Wu-0) (listado de movimientos), creen la interfaz de usuario en **Flutter** para mostrar los movimientos de la cuenta.

### 5. Armar los Endpoints
   - Definan y desarrollen los endpoints necesarios en la API para manejar las siguientes acciones:
     - Obtener la lista completa de movimientos.
     - Filtrar movimientos por tipo (entrada/salida).
     - Marcar un movimiento como favorito.
     - Eliminar un movimiento.

### 6. Consumir el Endpoint para Eliminar Movimientos
   - Implementen en **Flutter** la funcionalidad para eliminar un movimiento utilizando el endpoint correspondiente de la API.
   - Asegúrense de manejar adecuadamente los casos de error y mostrar confirmaciones al usuario.

### 7. Consumir el Endpoint para Editar Movimientos
   - Añadan la funcionalidad en la aplicación **Flutter** para marcar un movimiento como favorito, consumiendo el endpoint respectivo de la API.
   - Asegúrense de reflejar estos cambios en la interfaz de usuario.

### Extra (Opcional)
   - Implementen la funcionalidad de búsqueda para encontrar movimientos por fecha o por el nombre del concepto.


**Nota Importante:** Aunque completar el ejercicio es importante, lo que más valoraremos es **cómo se escribe el código, cómo se manejan las transiciones, el manejo de errores, y las buenas prácticas en general**. Es importante que el código sea limpio, bien estructurado y fácil de mantener. También se evaluará la capacidad para trabajar en equipo y cómo se gestionan las responsabilidades dentro del grupo.
