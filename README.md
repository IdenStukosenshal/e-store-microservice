**Тестовое задание для "ООО Информационные системы и сервисы"**

Запуск приложения:
1. Создать базу данных MySQL
2. В application.properties прописать url, username, password для подключения к БД
3. Скомпилировать, запустить, загрузить таблицы либо одним архивом, либо в порядке от родительских к дочерним.
При загрузке таблиц архивом, их имена должны совпадать с ниже перечисленными

Порядок таблиц: 
1. ElectroType, PositionType, PurchaseType, Shop,
2. ElectroItem, Employee,
3. ElectroEmployee, ElectroShop, Purchase

Сейчас ручное добавление таблиц реализовано только на 2-ух страницах: для ElectroType и для ElectroItem


 Доступ к web-интерфейсу приложения: http://localhost:8081/  
 Доступ к swagger-ui:  http://localhost:8081/estore-api.html