import requests

url = 'http://localhost:8080/user/1'

user_data = {
    "id": 1,
    "name": "John Doe"
}

response = requests.put(url,json=user_data)

if response.status_code == 200:
  print("Response Data:")
  print(response.json()) 
else:
  print(f"Failed to retrieve data. Status code: {response.status_code}")