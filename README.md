# tic-tac-server
Релизация сервера для игры "Крестики нолики" в режиме онлайн


Авторизация на сервере: 

POST /register
body:
```json
{
    "playerId" : "3c3c3fc4-0bc8-467c-96d8-ac4144213789"
}
```
успешная авторизация:
```json
{
    "gameId" : "1825b01d-2dc8-44df-85c1-47bc1679d63d",
    "role" : "X"
}
```
в случае ожидания противника, нужно повторить запрос позже:
```json
{
  "errorMessage": "THERE IS NO PLAYER"
}
```

Отправка хода:
POST /game/step
body:
```json
{
    "gameId" :  "1825b01d-2dc8-44df-85c1-47bc1679d63d",
    "playerId" : "3c3c3fc4-0bc8-467c-96d8-ac4144213789",
    "x" :  0,
    "y" : 0
}
```

Успешный ход:
```json
{
    "gameId" : "1825b01d-2dc8-44df-85c1-47bc1679d63d"
}
```
В случае отсутствия игровой сессии:
```json
{
    "errorMessage" : "THERE IS NO GAME SESSION"
}
```

В случае ошибки синхронизации:
```json
{
    "errorMessage" : "SYNCHRONIZATION ERROR"
}
```