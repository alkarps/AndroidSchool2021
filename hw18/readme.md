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
Между бизнес слоем и дата слоем реализован [интерактор][ImplHolidayClient], реализующий [HolidayClient][HolidayClient].
На текущий момент в бизнес слое реализован [HolidayService][HolidayService].
Для взаимодействия между UI и бизнес слоем реализован [HolidayProvider][HolidayProvider].

Тк праздники предоставляются за прошлый год - выбор периода получения праздников не предполагается.

Для сбора приложения используется Dagger 2: [дата слой][DataComponent], [бизнес слой][DomenComponent], [ui слой][PresentationComponent].

### На текущий момент реализовано:
* Работа с праздниками:
** Получение праздников
** Отображение дней с праздниками
** Отображение праздников

### В планах реализовать:
* Работа с языками:
** Получение списка языков.
** Выбор текущего языка.
** Запрос праздников с выбранным языком.
** Управление языками в настройках.
* Работа с странами:
** Получение списка стран.
** Выбор страны.
** Выбор страны или региона/провинции/штата.
** Запрос праздников с выбранным регионом/провинции/штата.
** Управление странами в настройках.
  

[HolidayApiSite]: https://holidayapi.com/
[HolidayApiSiteDoc]: https://holidayapi.com/docs
[HolidayApi]: ./src/main/java/ru/alkarps/android/school2021/hw18/data/holiday/api/HolidayApi.kt
[ImplHolidayClient]: ./src/main/java/ru/alkarps/android/school2021/hw18/data/holiday/ImplHolidayClient.kt
[HolidayClient]: ./src/main/java/ru/alkarps/android/school2021/hw18/domen/holiday/HolidayClient.kt
[HolidayService]: ./src/main/java/ru/alkarps/android/school2021/hw18/domen/holiday/impl/ImplHolidayService.kt
[HolidayProvider]: ./src/main/java/ru/alkarps/android/school2021/hw18/presentation/provider/impl/ImplHolidaysProvider.kt
[DataComponent]: ./src/main/java/ru/alkarps/android/school2021/hw18/data/di/DataComponent.kt
[DomenComponent]: ./src/main/java/ru/alkarps/android/school2021/hw18/domen/di/DomenComponent.kt
[PresentationComponent]: ./src/main/java/ru/alkarps/android/school2021/hw18/presentation/di/HolidayMainComponent.kt