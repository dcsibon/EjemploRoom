# TasksManageApp

Este código es un ejemplo completo de una aplicación Android utilizando Jetpack Compose, Room, Flow, y la inyección de dependencias con Dagger Hilt, 
siguiendo el patrón de arquitectura MVVM (Model-View-ViewModel). 

Vamos a ver cómo funciona, cómo comienza la app, la inyección de dependencias y cómo se distribuyen las responsabilidades en las distintas capas del modelo MVVM.

### Flujo de la Aplicación y Inyección de Dependencias

1. **Inicio de la Aplicación:**
   La app comienza en `MainActivity`, donde se muestra la interfaz de usuario. Usando Jetpack Compose, se llama a `TasksScreen` con una instancia de `TasksViewModel`.

2. **Inyección de Dependencias con Hilt:**
   Dagger Hilt se encarga de la inyección de dependencias. `TasksViewModel` se anota con `@HiltViewModel`, lo que significa que Hilt lo proporcionará automáticamente con
   las dependencias necesarias, en este caso, las instancias de `AddTaskUseCase`, `UpdateTaskUseCase`, `DeleteTaskUseCase`, y `GetTasksUseCase`.

4. **Creación de ViewModel:**
   Cuando se crea `TasksViewModel`, Hilt inyecta las instancias de los casos de uso, que a su vez tienen inyectado `TaskRepository`. `TaskRepository` tiene inyectado `TaskDao`,
   que es provisto por `DatabaseModule`.

### Distribución en el Modelo MVVM

1. **Modelo (Model):**
   - **`TaskEntity`:** Representa la entidad de la base de datos en Room.
   - **`TaskRepository`:** Encapsula el acceso a datos, utilizando `TaskDao` para operaciones de base de datos.
   - **`TaskDao`:** Interfaz DAO para definir operaciones de base de datos con Room.
   - **`TasksManageDatabase`:** Define la base de datos Room.
   - **`DatabaseModule`:** Módulo de Hilt para proveer dependencias relacionadas con la base de datos.

2. **Vista (View):**
   - **`TasksScreen` y componentes relacionados (`FabDialog`, `AddTasksDialog`, `TasksList`, `ItemTask`):** Composables que definen la interfaz de usuario.

3. **ViewModel:**
   - **`TaskModel`:** Representa los datos (modelo) que se muestran en la UI.
   - **`TasksViewModel`:** Mantiene el estado de la UI y maneja la lógica de la interfaz de usuario. Interactúa con los casos de uso para realizar operaciones de datos.

### Funcionamiento del Código

- **UI (Vista):** `TasksScreen` es el punto de entrada de la UI. Observa el estado del `TasksViewModel` y muestra diferentes UI basadas en el estado actual (`TaskUiState`).
- **ViewModel:** `TasksViewModel` contiene la lógica de negocio y el estado de la UI. Maneja eventos de la UI, como clics y cambios de texto, y utiliza casos de uso para interactuar con el repositorio.
- **Casos de Uso:** Cada caso de uso (`AddTaskUseCase`, `UpdateTaskUseCase`, etc.) encapsula una operación específica relacionada con las tareas.
- **Repositorio:** `TaskRepository` actúa como un intermediario entre la base de datos y la lógica de negocio.
- **Base de Datos:** Las operaciones de base de datos se definen en `TaskDao` y se implementan mediante Room en `TasksManageDatabase`.

### Inyección de Dependencias

- Hilt proporciona las dependencias necesarias para cada clase. Por ejemplo, `TasksViewModel` recibe los casos de uso, y `TaskRepository` recibe `TaskDao`,
- que a su vez es proporcionado por el módulo de Hilt (`DatabaseModule`).

Este código es un ejemplo de cómo utilizar estas tecnologías juntas para crear una aplicación Android robusta y bien estructurada siguiendo el patrón MVVM.
