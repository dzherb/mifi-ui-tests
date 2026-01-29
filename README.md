# Тестирование UI

Проект представляет собой набор автотестов для сайта IMDb (Selenium + TestNG) и Android‑приложения Wikipedia (Appium + TestNG).

---

## Веб-тесты
### Сценарии
  - открытие и загрузка главной страницы
  - поиск фильма и переход на соответствующую карточку
  - навигация через всплывающее меню
  - переход к топ-250

### Запуск
```bash
mvn test -Dgroups=web -Dbrowser=safari
```

Под флагом `Dbrowser` можно передать любой из доступных браузеров:
- safari
- firefox
- edge
- chrome

---

## Мобильные тесты
- Сценарии:
- смена языка
- открытие статьи из поиска
- очистка запроса и проверка, что результаты исчезают

### Подготовка окружения:
  - Android SDK установлен (в macOS по умолчанию `~/Library/Android/sdk`).
  - Переменные окружения:
    ```bash
    export ANDROID_HOME="$HOME/Library/Android/sdk"
    export ANDROID_SDK_ROOT="$ANDROID_HOME"
    export PATH="$PATH:$ANDROID_HOME/platform-tools:$ANDROID_HOME/emulator"
    ```
  - Установлен Appium и драйвер UiAutomator2:
    ```bash
    npm install -g appium
    appium driver install uiautomator2
    ```
  - Запустите Appium Server:
    ```bash
    appium --address 127.0.0.1 --port 4723
    ```
  - Запустите эмулятор или подключите устройство.
  - Убедитесь, что приложение Wikipedia установлено.

### Запуск (пример для эмулятора):
  ```bash
  mvn test -Dgroups=mobile \
    -DappiumServerUrl=http://127.0.0.1:4723/wd/hub \
    -DdeviceName="emulator-5554" \
    -DplatformVersion=16
  ```
Доступные флаги:
- `-DappiumServerUrl=http://127.0.0.1:4723`
- `-DdeviceName=Pixel_6_API_34` (ваш эмулятор/устройство)
- `-DplatformVersion=14` (опционально)

---

## Требования
- JDK 23+
- Maven 3.8+
- Браузер (по-умолчанию Chrome, поддерживаются Safari/Firefox/Edge)
- Для мобильных тестов: Android SDK + эмулятор/устройство, запущенный Appium Server, установленное приложение Wikipedia (`org.wikipedia`)

## Примечания
- Для тестов с использованием Safari необходимо в настройках браузера включить `Allow remote automation`

## Структура проекта
- `src/main/java/com/ui_testing/pages/web` — Page Object’ы для веб-версии.
- `src/main/java/com/ui_testing/pages/mobile` — Page Object’ы для Android-приложения.
- `src/test/java/com/ui_testing/tests/web` — веб-тесты
- `src/test/java/com/ui_testing/tests/mobile` — мобильные тесты.
- `src/test/resources/testng.xml` — сьют с группами `web` и `mobile`.
