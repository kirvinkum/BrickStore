
Brikstore demo

## Create order

### Invalid content
```
curl -i -X POST -H "Content-Type:application/json" -d ''  http://localhost:8080/order/create  
```
**Output**
```
HTTP/1.1 400
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 24 Oct 2022 21:45:42 GMT
Connection: close

{
"timestamp":"2022-10-24T21:45:42.345+00:00",
"status":400,
"error":"Bad Request",
"path":"/order/create"
}
```

### Invalid Params
```
curl -i -X POST -H "Content-Type:application/json" -d '{"amountx": 10 }'  http://localhost:8080/order/create 
```
**Output**
```
HTTP/1.1 406 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 24 Oct 2022 21:48:05 GMT


{
"status":"NOT_ACCEPTABLE",
"message":"Invalid or Missing Parameter(s).",
"errors":["amount: must be greater than or equal to 1"],
"timestamp":"2022-10-24T21:48:05.608Z"
}
```


### Valid Request

```
curl -i -X POST -H "Content-Type:application/json" -d '{"amount": 10 }'  http://localhost:8080/order/create
```

**Output**
```
HTTP/1.1 201
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 24 Oct 2022 21:42:23 GMT

{
"order_ref":"ORDER00002"
}
```

## List order

### Invalid order ref
```
curl -i -X GET "http://localhost:8080/order/list?order_ref=ORDER00004343"
```
**Output** 
```
HTTP/1.1 200
Content-Length: 0
Date: Mon, 24 Oct 2022 21:53:02 GMT
```

### Valid order ref

```
curl -i -X GET "http://localhost:8080/order/list?order_ref=ORDER00001"
```
**Output**
```
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 24 Oct 2022 21:52:15 GMT

{
"order_ref":"ORDER00001",
"amount":10
}
```


## Update order amount

### Invalid order ref and amount
```
curl -i -X PATCH -H "Content-Type:application/json" -d '{"order_refx":"ORDER00001", "amountx": 20 }'  http://localhost:8080/order/update
```
**Output**
```
HTTP/1.1 406
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 24 Oct 2022 22:04:50 GMT

{
"status":"NOT_ACCEPTABLE",
"message":"Invalid or Missing Parameter(s).",
"errors":[
    "amount: must be greater than or equal to 1",
    "orderRef: Invalid order refrence"
],
"timestamp":"2022-10-24T22:04:50.140Z"
}
```

### Valid order ref and amount

```
curl -i -X PATCH -H "Content-Type:application/json" -d '{"order_ref":"ORDER00001", "amount": 20 }'  http://localhost:8080/order/update
```
**Output**
```
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 24 Oct 2022 22:03:19 GMT

{
"order_ref":"ORDER00001"
}
```

## Update/Fullfill order 

### Valid request

```
curl -i -X PATCH -H "Content-Type:application/json" -d '{"order_ref":"ORDER00001", "status": "DISPATCHED" }' http://localhost:8080/order/fullfill
```
**Output**
```
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 24 Oct 2022 22:07:21 GMT

{
"order_ref":"ORDER00001"
}
```

## Update dispatched order

```
curl -i -X PATCH -H "Content-Type:application/json" -d '{"order_ref":"ORDER00001", "amount": 20 }'  http://localhost:8080/order/update         
```

```
HTTP/1.1 400
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 24 Oct 2022 22:08:49 GMT
Connection: close

{
"status":"BAD_REQUEST",
"message":"Order update not allowed.",
"errors":[],
"timestamp":"2022-10-24T22:08:49.578Z"
}
```