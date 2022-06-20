# FirstRestApp
Первое REST приложение Из задания по курсу Наиля Алишева. 
Представляет собой реализацию работы модели сенсор погоды - база данных. 
Организована связь один ко многим через имя сенсора. 
Реализовано 4 адреса с соответсвующим функционалом: 
•POST /sensors/registration - регистрация нового сенсора в системе (единственное поле - name) 
•POST /measurements/add - добавление нового измерения (значение температуры - value, наличие дождя raining (true/false), и имя сенсора - sensor) 
•GET /measurements - возвращает все измерения из БД 
•GET /measurements/rainyDaysCount - возвращает кол-во дождливых дней из БД
