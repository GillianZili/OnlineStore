# The command line of launching database:
& "C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql" -u root -p   
# The command line of launching backend server:
./mvnw spring-boot:run
# @PostMapping("/item/add")
```
{
    "id": "xxxx",
    "name": "xxxx",
    "price": xx,
    "storage": xx
}
```
# @PostMapping("/cart/update")
```
[
  {
    "user_id": 4,
    "item_id": "v1|134894087175|0",
    "amount": 1
  },
  {
    "user_id": 4,
    "item_id": "v1|127066517020|0",
    "amount": 2
  }
]
```