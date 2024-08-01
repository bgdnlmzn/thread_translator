# Thread Translator

Thread Translator — это веб-приложение для перевода текста. Приложение принимает на вход строку, состоящую из набора слов, исходный язык и целевой язык в качестве параметров для перевода. В ответе возвращает переведенную строку.

## Установка и запуск

### Шаги для запуска

 1. Клонируйте репозиторий:

   ```bash
   git clone https://github.com/bgdnlmzn/thread_translator.git
   cd thread_translator
   ```

2. Создайте файл .env в корне проекта и добавьте ваши значения для YANDEX_FOLDER_ID и YANDEX_TOKEN:
    ```dotenv
    YANDEX_FOLDER_ID=YourYandexFolderId
    YANDEX_TOKEN=YourYandexToken
   ```
3. Соберите и запустите контейнеры с помощью Docker Compose:
   ```bash
    docker-compose up --build
   ```
### Приложение будет доступно по адресу: http://localhost:8080.

### Пример запроса:
 ```json
    {
    "inputText": "Привет!",
    "sourceLang": "ru",
    "targetLang": "en"
    }
```
### Пример ответа:
```json
{
    "translatedText": "Hello!"
}
```
inputText - текст, который нужно перевести

sourceLang - исходный язык

targetLang - целевой язык

translatedText - переведенный текст

Этот проект использует PostgreSQL в качестве базы данных, которая развернута в Docker контейнере. Приложение автоматически применяет миграции базы данных с использованием Flyway.
### Создание токена Yandex
Для использования сервиса Yandex Translate, вам нужно создать токен API. Следуйте этим шагам:

1. Перейдите на страницу создания API ключа.

2. Войдите в свою учетную запись Yandex Cloud.

3. Создайте сервисный аккаунт, если он еще не создан.

4. Сгенерируйте API ключ для сервисного аккаунта.

5. Сохраните YANDEX_FOLDER_ID и YANDEX_TOKEN и добавьте их в файл .env
### Более подробно о получении токена:
https://yandex.cloud/ru/docs/translate/