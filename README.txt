1. ������� � ����� TASK.txt
2. ������ �������������� �� Docker
3. ��� build ������� ����� ��������� application.properties ������ ���� � ������� postgre 
   (� ��������� ������ �������� ������, ���� ���� �������� - ����� �� ������)
4. ����� ������ ������� ����� � .\survey.service\target\zip:survey.service-0.0.1.jar:\BOOT-INF\classes\application.properties
   ������� ��� ��������� spring.datasource.url=jdbc:postgresql://db/postgres
   (��� ������� ����� � �� �������� =( )
5. ��������� �������� � ����� .\survey.service ��������� "docker-compose up --build survey-service" (���������� ����������)
6. ���� ��� �� ����� ��������� ��������� � �������. 
7. ��� ����������� � ���� ���������������� 2 ������������ admin user (pass admin, user) � 2 ���� ROLE_ADMIN ROLE_USER (admin = ROLE_ADMIN, user = ROLE_USER)
8. ��� ����������� ������������ Baisc Auth
8. ������������ API: http://your_address:9991/swagger-ui.html  (��������������� ������������� � ������� ROLE_ADMIN)
9. ��� �������� /survey-service/admin/** ������� ����������� ������������� � ������� ROLE_ADMIN 
10. ���������� �������� �� 5.63.152.97 (http://5.63.152.97:9991/swagger-ui.html)







 
