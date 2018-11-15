Se pide:
1)	Definir correctamente la APi a exponer dentro del API Manager siguiendo las buenas prácticas de definición de Apis. (entregar el swagger)
2)	Hacer un pequeño piloto que exponga las apis bien definidas en Apigee y que invoque a las Apis antiguas (utilizar el swagger que adjuntamos para generar el mockserver)
3)	Los parámetros de entrada y salida deben validarse dentro del API Manager utilizando como base el Swagger que se defina en la capa de Apigee.
4)	La seguridad debe ser oauth 2.0 a nivel de aplicación.
5)	Debe existir un control de cuota que no permita más de 1000 peticiones por minuto.
6)	Se deben cachear las respuestas del backend de obtener usuarios al menos 1 minuto.
7)	Se debe entregar una colección postman que nos permita realizar las pruebas.

Se valorará:
8) Que en vez de utilizar un mockserver como backend, se realice una implementación básica del servidor utilizando node.js + mongoDB.
9) Se entregue una versión ligera del portal del developer de Apigee para realizar las pruebas.
10) Se debe configurar alguna alerta que indique que la APi está caída. La monitorización de Apigee no está en todas las cuentas, por lo que se podrán utilizar sistemas de monitorización simple de terceros.

Se debe entregar:
1)	Swagger de la nueva API Definida.
2)	API Proxy implementado
3)	Postman de prueba
