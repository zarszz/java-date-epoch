# date.epochs

## Spring boot + JPA Application that handle date with UNIX epoch time millis
main reference : https://www.youtube.com/watch?v=nEOEvWm5yPA

### keyword
unix epoch time in milliseconds

### behaviour
UNIX epoch time millis always UTC | FE always send request with UNIX epoch time millis


### how change to current user timezone ?
the programming language actually auto convert epoch time milliseconds to date based on device configuration timezone <br/>
1. FE send user timezone and BE response with formatted date based on TZ from FE
2. (easiest way) BE just send raw UTC epoch time mili with number data type and format it in client side (web FE, mobile, etc)
   that's it

data type store in db : **long**

- request example when mode is **GET_WITH_TIMEZONE_FROM_FE**
```shell
curl --location --request GET 'http://localhost:8080/api/v1/transactions?startDate=1653207619000&endDate=1653670555000&mode=GET_WITH_TIMEZONE_FROM_FE&timezone=Asia/Jakarta'
```

- response
```json
[
    {
    "date": "2022-05-22T08:20:19Z",
    "id": 1
    },
    {
    "date": "2022-05-23T16:55:55Z",
    "id": 2
    },
    {
    "date": "2022-05-27T16:55:55Z",
    "id": 6
    },
    {
    "date": "2022-05-26T16:55:55Z",
    "id": 7
    }
]
```

- request example when mode is **GET_RAW_NUMBER**
```shell
curl --location --request GET 'http://localhost:8080/api/v1/transactions?startDate=1653207619000&endDate=1653670555000&mode=GET_RAW_NUMBER&timezone=Asia/Jakarta'
```

- response
```json
[
    {
    "id": 1,
    "date": 1653207619000
    },
    {
    "id": 2,
    "date": 1653324955000
    },
    {
    "id": 6,
    "date": 1653670555000
    },
    {
    "id": 7,
    "date": 1653584155000
    }
]
```