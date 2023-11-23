# Дипломный проект курса "Тестировщик ПО"

Цель: внедрение автоматизации тестирования для приложения — веб-сервиса, по покупке тура по определённой цене двумя способами:
Обычная оплата по дебетовой карте.
Уникальная технология: выдача кредита по данным банковской карты.

<img width="705" alt="service" src="https://github.com/AleksandrSergeevi4/qa57-diplom/assets/123874945/85347ef2-ea60-45f3-be84-38499741028a">

## 1. Для запуска приложения:

    1.1 Для проведения тестирования на ПК должны быть установлены следующие программы:
 <ul>
 <li>IntelliJ IDEA.
 <li>Docker Desktop.
 <li>Git.
 <li>Google Chrome.
 </ul>

    1.2. Склонировать проект с GitHub:
<ul>
<li> https://github.com/AleksandrSergeevi4/qa57-diplom
</ul>

## 2. Запуск авто-тестов:

<ul>
<li> Запустить программу Docker Desktop.
<li> Открыть в программе IntelliJ IDEA склонированный проект.
<li> Запустить три контейнера: MySQL, PostgreSQL и эмулятор банковских сервисов командой в терминале программы:
<table border='2' width=250>
<td colspan='2'>
docker-compose up      
</table>

<li> Убедиться что контейнеры запустились командой в терминале:

<table border='2' width=250>
<td colspan='2'>
docker-compose ps      
</table>
</ul>
</p>

## 3. Для работы с СУБД MySQL:

    1. Запустить SUT командой в терминале:
<table border='2' width=550>
<td colspan='2'>
java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar aqa-shop.jar  
</table>

    2. Запустить авто-тесты командой в терминале:
<table border='2' width=550>
<td colspan='2'>
./gradlew clean test "-Ddatasource.url=jdbc:mysql://localhost:3306/app"   
</table>

<p>

## 3.1. Для работы с СУБД PostgreSQL:

    1. Запустить SUT командой в терминале:
<table border='2' width=550>
<td colspan='2'>
java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar aqa-shop.jar  
</table>

    2. Запустить авто-тесты командой в терминале:
<table border='2' width=550>
<td colspan='2'>
./gradlew clean test "-Ddatasource.url=jdbc:postgresql://localhost:5432/app"   
</table>

<p>

## 4. Отчет о проведении тестирования с помощью Allure:

<ul>
<li> Для просмотра отчета ввети в терминале команду:

<table border='2' width=250>
<td colspan='2'>
./gradlew allureServe        
</table>

<li> После просмотра отчета ввести в терминале сочетание клавиш:

<table border='2' width=250>
<td colspan='2'>
CTRL + C        
</table>
</ul>
</p>
