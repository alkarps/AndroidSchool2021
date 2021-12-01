## Курсовая работа: My little calendar

Реализация примитивного календаря. Для получения праздников используется [HolidayApi][HolidayApiSite].
В бесплатной версии доступны праздники за прошлый год.
[Описание API][HolidayApiSiteDoc]

### Архитектура
Приложение разбито на 3 слоя:
* Дата слой
* Бизнес слой
* UI слой

В дата слое находится клиенты для вызова [HolidayApi][HolidayApiSite] для получения праздников ([LanguageApi][LanguageApi]), 
доступных стран и их территориальных подразделений (ТП) ([CountryApi][CountryApi]). 
Так же реализовано кэширование полученных языков ([LanguageDao][LanguageDao]), стран и их ТП в БД([CountryDao][CountryDao]).
Кроме того, для управления событиями пользователя реализован [EventDao][EventDao].

В бизнес-слое реализована логика работы с праздниками ([HolidayInteractor][HolidayInteractor]), 
языками ([LanguageInteractor][LanguageInteractor]) и странами ([CountryInteractor][CountryInteractor]), полученными от [HolidayApi][HolidayApiSite].
Бизнес логика работы с пользовательскими событиями представлена в [EventInteractor][EventInteractor]

Для сбора приложения используется Dagger 2: [дата слой][DataComponent], [бизнес слой][DomenComponent], [ui слой][PresentationComponent].

### На текущий момент реализовано:
* Работа с праздниками:
* * Получение праздников на выбранный день
* * Отображение праздникав за выбранный день
* * Релоад данных
* Работа с событиями пользователя:
* * Получение списка событий на выбранный день
* * Создание события
* * Изменение события
* * Удаление события
* * Релоад данных
* Работа с языками:
* * Выбор текущего языка.
* * Управление языками в настройках.
* * Получение списка языков.
* * Запрос праздников с выбранным языком.
* Работа с странами:
* * Выбор страны.
* * Выбор страны или территориального подразделения.
* * Управление странами в настройках.
* * Получение списка стран.
* * Получение списка территориальных подразделений для страны.
* * Запрос праздников с выбранным территориального подразделения.

### В планах реализовать:
* Работа с событиями пользователя:
* * Создание событий на весь день
* * Возможность указания время завершения события
* * Длительные события (больше одного дня)
* Нотификация
* Записи пользователя (дневник)?
* Туду лист?
* Добавить возможность выбора местоположения события через геолокацию
* Актуализация документации
* Добавить UML
  

[HolidayApiSite]: https://holidayapi.com/
[HolidayApiSiteDoc]: https://holidayapi.com/docs
[HolidayApi]: ./src/main/java/ru/alkarps/android/school2021/hw18/data/holiday/api/HolidayApi.kt
[CountryApi]: ./src/main/java/ru/alkarps/android/school2021/hw18/data/country/api/CountryApi.kt
[LanguageApi]: ./src/main/java/ru/alkarps/android/school2021/hw18/data/language/api/LanguageApi.kt
[LanguageDao]: ./src/main/java/ru/alkarps/android/school2021/hw18/data/storage/dao/LanguageDao.kt
[CountryDao]: ./src/main/java/ru/alkarps/android/school2021/hw18/data/storage/dao/CountryDao.kt
[EventDao]: ./src/main/java/ru/alkarps/android/school2021/hw18/data/storage/dao/EventDao.kt
[HolidayInteractor]: src/main/java/ru/alkarps/android/school2021/hw18/domen/holiday/HolidayInteractor.kt
[LanguageInteractor]: src/main/java/ru/alkarps/android/school2021/hw18/domen/language/LanguageInteractor.kt
[CountryInteractor]: src/main/java/ru/alkarps/android/school2021/hw18/domen/country/CountryInteractor.kt
[EventInteractor]: src/main/java/ru/alkarps/android/school2021/hw18/domen/event/EventInteractor.kt
[DataComponent]: ./src/main/java/ru/alkarps/android/school2021/hw18/data/di/DataComponent.kt
[DomenComponent]: ./src/main/java/ru/alkarps/android/school2021/hw18/domen/di/DomenComponent.kt
[PresentationComponent]: src/main/java/ru/alkarps/android/school2021/hw18/presentation/di/holiday/HolidayMainComponent.kt
