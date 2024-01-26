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
  que a su vez es proporcionado por el módulo de Hilt (`DatabaseModule`).

Este código es un ejemplo de cómo utilizar estas tecnologías juntas para crear una aplicación Android robusta y bien estructurada siguiendo el patrón MVVM.

-----

## Diferencias entre MVVM (Model-View-ViewModel) y Clean Architecture

MVVM (Model-View-ViewModel) y Clean Architecture son conceptos relacionados pero con enfoques distintos en el desarrollo de software. Ambos se utilizan para organizar y estructurar proyectos, pero a diferentes niveles y con distintos objetivos.

### MVVM (Model-View-ViewModel)

MVVM es un patrón de arquitectura de software específicamente diseñado para aplicaciones con interfaces gráficas de usuario, como las aplicaciones Android. Este patrón divide la aplicación en tres componentes principales:

1. **Modelo (Model):** Representa los datos y la lógica de negocio. En Android, esto generalmente incluye clases de datos (como tus entidades), acceso a bases de datos, y lógica de negocio.

2. **Vista (View):** Es la interfaz de usuario. En el contexto de Android con Jetpack Compose, serían tus funciones composable.

3. **ViewModel:** Actúa como intermediario entre el Modelo y la Vista. Contiene la lógica de la interfaz de usuario y mantiene el estado de esta.

El objetivo principal de MVVM es separar la lógica de la interfaz de usuario de la lógica de negocio para facilitar el mantenimiento y el testing.

### Clean Architecture

Clean Architecture, propuesta por Robert C. Martin, es un enfoque más amplio para el diseño de software que va más allá de la interfaz de usuario. Su objetivo es desacoplar el código en capas para que sea independiente de frameworks, interfaces de usuario, y bases de datos. Esto hace que el sistema sea más adaptable a cambios y más fácil de testear. Las capas típicas incluyen:

1. **Entities:** Representan los conceptos centrales o reglas de negocio de la aplicación.
2. **Use Cases (Casos de Uso):** Contienen la lógica de negocio específica de la aplicación.
3. **Interface Adapters:** Incluyen adaptadores, presentadores y controladores. Esta capa convierte los datos en formatos convenientes para las Entities y los Use Cases.
4. **Frameworks and Drivers:** Es la capa más externa e incluye detalles como la base de datos, frameworks, y dispositivos de entrada/salida.

Para esta aplicación, estamos siguiendo un enfoque inspirado en Clean Architecture:

- **data:** Sería equivalente a "Frameworks and Drivers", donde interactúas con la base de datos (a través de Room).
- **domain:** Contiene los casos de uso, que son parte central de la lógica de negocio.
- **ui:** Sería una mezcla de "Interface Adapters" y "View" en MVVM, manejando la presentación y la interacción con el usuario.

### Diferencias Clave

- **Nivel de Aplicación:** MVVM se centra en la estructuración de la interfaz de usuario, mientras que Clean Architecture se aplica a toda la estructura del software, incluyendo pero no limitándose a la interfaz de usuario.

- **Objetivos:** MVVM busca desacoplar la lógica de la interfaz de usuario de la lógica de negocio. Clean Architecture busca crear un sistema desacoplado y adaptable que sea independiente de frameworks, bases de datos y interfaces.

- **Implementación:** MVVM es más específico sobre cómo se deben estructurar las partes relacionadas con la interfaz de usuario, mientras que Clean Architecture proporciona un conjunto de pautas más generalizadas para la estructura global de la aplicación.

En resumen, mientras que MVVM es un patrón específico para la organización de la interfaz de usuario y la lógica de presentación, Clean Architecture es un enfoque más holístico para el diseño de toda la aplicación, y ambos pueden ser utilizados en conjunto para lograr un diseño de software robusto y mantenible.
