# OOP-HR
<br>Задача на ООП
<br>Условия:
<br>Ты - глава маленькой IT-компании. Тебе надо нанять сотрудников, но денег на эйчара у тебя нет. Ты хочешь написать для этих целей программу!<br>
<br>Итак, прими на работу 3 кандидатов из 10.
<br>К тебе приходят Студенты, Джуниоры, Миддлы и Сениоры.
<br>Критерии приёма:
- Студент - 5-й курс и выше; знает HTML;
- Джуниор - знает HTML и SQL;
- Миддл - знает HTML, JavaScript и SQL, опыт работы от 2 лет включительно;
- Сениор - знает HTML, JavaScript и SQL, опыт работы от 5 лет включительно.

Приоритет приёма: в первую очередь берём Миддлов, во вторую - Сениоров, дальше - Джуниоры и Студенты. Если среди соискателей найдутся 3 подходящих Миддла, мы наберём их и этим ограничимся; если нет - пойдём по списку дальше.
<br>Программа должна уметь:
- выводить анкеты соискателей (имя и упорядоченная информация по параметрам);
- выводить имена счастливчиков;
- выводить расширенную информацию (почему этого взяли, почему этому отказали).
<br>На вход в любом удобном тебе формате поступает 10 анкет. Либо готовые, либо генерируются, опять же, твоей программой.

Решение:
<p>На входе:
<br>candidates.xml - список кандидатов с соответсвующими полями.
<br>openPositions1.xml (2,3) - три разных варианта открытых позиций и приоритет приёма.
<br>positionsRequirements.xml - требования к каждой позиции (критерий приёма).
<p>На выходе:
<br>approvedCandidates1.xml (2,3) - список счастливчиков.
<br>processedCandidates1.xml (2,3) - список всех кандидатов с решением(взяли/не взяли) и причиной отказа.

класс HRRunner.java содержит метод main и служит для демонстрации применение обработки анкет кандидатов.
<br>umlmodel.png - диаграмма классов пакета com.epam.hr
