# football
# -------------------- ТЗ -----------

Групповая часть ЧМ по футболу

Команды объединены в группы (4 команды в группе). Групп 8 штук (обычно именуют от A до H). Пример: "Group G"
Внутри группы каждая команда играет с каждой, т.е. имеем 3 матча в каждой группе. Матч может быть ТОЛЬКО между командами находящимися в одной группе.

Задача:
Создать REST приложение (очень желательно на Spring). Позволяющее выполнять стандартные операции CRUD (create, read, update, delete) для сущностей Group, Team, Game. Приветствуется (но не обязательно) наличие unit test-ов, валидация (например, при вставке матча проверять, а обе ли команды из одной и той же группы)

Дополнительно требуется: вывод текущего состояния группы: отсортированной по количеству очков таблицы вида:
Команда     Победы       Ничьи      Поражения        Забито-Пропущено             Очки
Хоббитания    1            1            0               3-1                         4
Гондор        1            1            0               2-1                         4
победа = 3 очка, ничья = 1, поражение = 0

Вывод таблицы не обязательно на UI, достаточно ответа в JSON формате

# ------------------- my ------------

use liquibase for db migrations (postgresql 10)

use swagger for REST APIs documentations (http://localhost:8080/swagger-ui/index.html)

p.s swagger use 80 port

app address  - http://localhost:8080/

# --- run & build

to run   : gradle bootRun
to build : gradle build


