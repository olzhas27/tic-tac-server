# tic-tac-server

Релизация сервера для игры "Крестики нолики" в режиме онлайн

Авторизация на сервере:

POST /register body:

```json
{
  "playerId": "3c3c3fc4-0bc8-467c-96d8-ac4144213789"
}
```

успешная авторизация:

```json
{
  "gameId": "1825b01d-2dc8-44df-85c1-47bc1679d63d",
  "role": "X"
}
```

в случае ожидания противника, нужно повторить запрос позже:

```json
{
  "errorMessage": "THERE IS NO PLAYER"
}
```

Отправка хода:
POST /game/step body:

```json
{
  "gameId": "1825b01d-2dc8-44df-85c1-47bc1679d63d",
  "playerId": "3c3c3fc4-0bc8-467c-96d8-ac4144213789",
  "x": 0,
  "y": 0
}
```

Успешный ход:

```json
{
  "gameId": "1825b01d-2dc8-44df-85c1-47bc1679d63d"
}
```

В случае отсутствия игровой сессии:

```json
{
  "errorMessage": "THERE IS NO GAME SESSION"
}
```

В случае ошибки синхронизации:

```json
{
  "errorMessage": "SYNCHRONIZATION ERROR"
}
```

Ожидание хода противника:
GET /game/wait

```json
{
  "gameId": "1825b01d-2dc8-44df-85c1-47bc1679d63d",
  "playerId": "3c3c3fc4-0bc8-467c-96d8-ac4144213789"
}
```

Пример ответа:

```json
{
  "code": "O_WIN",
  "x": 0,
  "y": 2
}
```

<table>
    <thead>
        <th>Код ответа</th>
        <th>Значение</th>
    </thead>
    <tbody>
        <tr>
            <td>X_WIN</td><td>Победил X игрок</td></tr>
        <tr><td>O_WIN</td><td>Победил O игрок</td></tr>
        <tr><td>NO_WIN</td><td>Ничья</td></tr>
        <tr><td>STILL_WAITING</td><td>Ожидание хода соперника</td></tr>
        <tr><td>YOUR_TURN</td><td>Ваш ход</td></tr>
    </tbody>
</table>

В случае ошибки синхронизации:

```json
{
  "errorMessage": "SYNCHRONIZATION ERROR"
}
```
