## Домашняя работа по MVP/MVVM.

Клиент для просмотра праздников через [HolidayApi][HolidayApiSite].
В бесплатной версии доступны праздники за прошлый год.
[Описание API][HolidayApiSiteDoc]

### Архитектура
Приложение разбито на 3 слоя:
* Дата слой
* Бизнес слой
* UI слой

В дата слое находится клиент для получения праздников [HolidayApi][HolidayApi].
Для взаимодействия между бизнес слоем и дата слоем реализован [клиент][ImplHolidayClient], реализующий [HolidayClient][HolidayClient].
На текущий момент в бизнес слое реализован [HolidayInteractor][HolidayInteractor].
Для взаимодействия между UI и бизнес слоем реализован [HolidayProvider][HolidayProvider].

Тк праздники предоставляются за прошлый год - выбор периода получения праздников не предполагается.

Для сбора приложения используется Dagger 2: [дата слой][DataComponent], [бизнес слой][DomenComponent], [ui слой][PresentationComponent].

### На текущий момент реализовано:
* Работа с праздниками:
* * Получение праздников
* * Отображение дней с праздниками
* * Отображение праздников
* Работа с языками:
* * Получение списка языков.
* * Запрос праздников с выбранным языком.
* Работа с странами:
* * Получение списка стран.
* * Получение списка территориальных подразделений для страны.
* * Запрос праздников с выбранным территориального подразделения.

### В планах реализовать:
* Работа с языками:
* * Выбор текущего языка.
* * Управление языками в настройках.
* Работа с странами:
* * Выбор страны.
* * Выбор страны или территориального подразделения.
* * Управление странами в настройках.
* Добавление своих событий
* Нотификация
* Записки
* Актуализация документации
* Добавить UML
  

[HolidayApiSite]: https://holidayapi.com/
[HolidayApiSiteDoc]: https://holidayapi.com/docs
[HolidayApi]: ./src/main/java/ru/alkarps/android/school2021/hw18/data/holiday/api/HolidayApi.kt
[ImplHolidayClient]: ./src/main/java/ru/alkarps/android/school2021/hw18/data/holiday/ImplHolidayClient.kt
[HolidayClient]: ./src/main/java/ru/alkarps/android/school2021/hw18/domen/holiday/HolidayClient.kt
[HolidayInteractor]: src/main/java/ru/alkarps/android/school2021/hw18/domen/holiday/impl/ImplHolidayInteractor.kt
[HolidayProvider]: ./src/main/java/ru/alkarps/android/school2021/hw18/presentation/provider/impl/ImplHolidaysProvider.kt
[DataComponent]: ./src/main/java/ru/alkarps/android/school2021/hw18/data/di/DataComponent.kt
[DomenComponent]: ./src/main/java/ru/alkarps/android/school2021/hw18/domen/di/DomenComponent.kt
[PresentationComponent]: src/main/java/ru/alkarps/android/school2021/hw18/presentation/di/holiday/HolidayMainComponent.kt
