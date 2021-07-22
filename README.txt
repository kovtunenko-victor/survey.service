1. Задание в файле TASK.txt
2. Сервис развертывается на Docker
3. Для build проекта нужно настроить application.properties указав путь к серверу postgre 
   (в настройке указан реальный сервер, если есть интернет - можно не менять)
4. После сборки проекта зайти в .\survey.service\target\zip:survey.service-0.0.1.jar:\BOOT-INF\classes\application.properties
   указать там настройку spring.datasource.url=jdbc:postgresql://db/postgres
   (как сделать лучше я не придумал =( )
5. Запустить находясь в папке .\survey.service запустить "docker-compose up --build survey-service" (Запустится терминалом)
6. Если все ок можно пробовать обращатся к сервису. 
7. Для авторизации в базе зарегистрировано 2 пользователя admin user (pass admin, user) и 2 роли ROLE_ADMIN ROLE_USER (admin = ROLE_ADMIN, user = ROLE_USER)
8. Для авторизации используется Baisc Auth
8. Документация API: http://your_address:9991/swagger-ui.html  (авторизироватся пользователем с правами ROLE_ADMIN)
9. Все операции /survey-service/admin/** тербуют авторизации пользователем с правами ROLE_ADMIN 
10. Приложение запущено на 5.63.152.97 (http://5.63.152.97:9991/swagger-ui.html)


UPD.
3й пункт нужно перед build проекта настроить переменные среды:
DB_HOST = 5.63.152.97:5436 (или любой другой сервер)
DB_NAME = postgres
DB_USER = postgres
DB_PASSWORD = test 
application.properties уже настроен на environment variables
 
4й пункт исключаем. эта настройка перенесена в docker-compose.yml







 
