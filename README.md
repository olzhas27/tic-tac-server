# tic-tac-server
Релизация сервера для игры "Крестики нолики" в режиме онлайн


Авторизация на сервере:


post /user

body:
{
    "login" : "someLogin",
    "port" : 8080
}

response:
{ 
    "token" : "someToken" 
}

Выйти из сервера:

delete /user
body:
{
    "token" : "someToken"
}

response:
{
}

Сообщить серверу о готовности играть:

post /game/ready
body:
{
    "token"" : "someToken"
}

response:
{

"sessionId" : "someSessionId",

"status" : "WAITING_FOR_X",

"role" : "X",

"gameFieldJson" : ""

}

Получить данные игровой сессии

get /game/{sessionId}?token=someToken

response:
{

"sessionId" : "someSessionId",

"status" : "WAITING_FOR_X",

"role" : "X",

"gameFieldJson" : ""

}

Выполнить ход:

post /game/{sessionId}

body:

{
    "token" : "someToken",
    
    "x" : 0,
    
    "y" : 0,
    
}

response: { }