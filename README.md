# Тестирование UI

Проект представляет собой набор автотестов для сайта Wikipedia (Selenium + TestNG) и Android‑приложения Wikipedia (Appium + TestNG).

---

## Структура проекта
- `src/test/java/com/wikipedia/pages/web` — Page Object’ы для веб-версии.
- `src/test/java/com/wikipedia/pages/mobile` — Page Object’ы для Android-приложения.
- `src/test/java/com/wikipedia/tests/web` — веб-тесты TestNG.
- `src/test/java/com/wikipedia/tests/mobile` — мобильные тесты TestNG.
- `src/test/resources/testng.xml` — сьют с группами `web` и `mobile`.

---

## Веб-тесты
- Сценарии: английская главная страница, поиск статьи “Java (programming language)”, проверка блока Featured article, открытие случайной статьи.
- Запуск только веб-группы:
  ```bash
  mvn test -Dgroups=web
  ```
- Полезные свойства:
  - `-Dbrowser=chrome|firefox|edge`
  - `-DwebBaseUrl=https://en.wikipedia.org`

## Мобильные тесты
- Сценарии: поиск *"Java"* с проверкой результатов и описаний, открытие статьи *"Software testing"* из поиска, очистка запроса и проверка, что результаты исчезают.
- Подготовка окружения:
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
  - Запустите Appium Server (по умолчанию `http://127.0.0.1:4723`, при необходимости можно указать `/wd/hub`, код попробует оба пути):
    ```bash
    appium --address 127.0.0.1 --port 4723
    ```
  - Запустите эмулятор или подключите устройство.
  - Убедитесь, что приложение Wikipedia установлено.
- Запуск только мобильной группы (пример для эмулятора):
  ```bash
  mvn test -Dgroups=mobile \
    -DappiumServerUrl=http://127.0.0.1:4723/wd/hub \
    -DdeviceName="emulator-5554" \
    -DplatformVersion=16
  ```
- Свойства для мобильных:
  - `-DappiumServerUrl=http://127.0.0.1:4723`
  - `-DdeviceName=Pixel_6_API_34` (ваш эмулятор/устройство)
  - `-DplatformVersion=14` (опционально)

## Требования
- JDK 23+
- Maven 3.8+
- Браузер (по-умолчанию Chrome, поддерживаются Firefox/Edge)
- Для мобильных тестов: Android SDK + эмулятор/устройство, запущенный Appium Server, установленное приложение Wikipedia (`org.wikipedia`)

## Примечания
- Драйверы браузеров ставятся автоматически через WebDriverManager; Chrome стартует в maximized.
- Мобильные тесты используют UiAutomator2 и `noReset=true`; онбординг пропускается, если доступна кнопка Skip.
- Все ключевые параметры можно переопределять через системные свойства без правок кода.
