import requests
import json
import base64
import io

api_key = open("api_key.txt")
client_id = api_key.readline().strip()
client_secret = api_key.readline().strip()
base_url = 'https://api.ebay.com/buy/browse/v1/item_summary/search'

# acquire OAuth token
def get_oauth_token():
    url = 'https://api.ebay.com/identity/v1/oauth2/token'
    
    # Create Basic Authentication
    credentials = f"{client_id}:{client_secret}"
    encoded_credentials = base64.b64encode(credentials.encode('utf-8')).decode('utf-8')
    
    headers = {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': f'Basic {encoded_credentials}'
    }
    
    data = {
        'grant_type': 'client_credentials',
        'scope': 'https://api.ebay.com/oauth/api_scope'
    }
    
    response = requests.post(url, headers=headers, data=data)
    if response.status_code == 200:
        return response.json()['access_token']
    else:
        print(f"Failed to get token: {response.text}")
        return None


def fetch_and_save_items(query, limit):
    token = get_oauth_token()
    if not token:
        return
    
    params = {
        'q': query,
        'limit': limit
    }
    
    headers = {
        'Authorization': f'Bearer {token}',
        'X-EBAY-C-MARKETPLACE-ID': 'EBAY_US'
    }
    
    response = requests.get(base_url, headers=headers, params=params)
    
    if response.status_code == 200:
        items_data = response.json()
        
        file_path = f'ebay_items_{query}_{limit}.json'
        with open(file_path, 'w', encoding='utf-8') as f:
            json.dump(items_data, f, ensure_ascii=False, indent=2)
        
        print(f"Successfully saved data to {file_path}")
        if 'itemSummaries' in items_data:
            print(f"Retrieved {len(items_data['itemSummaries'])} items")
        else:
            print("No items found in response")
    else:
        print(f"API call failed: {response.status_code} - {response.text}")


if __name__ == "__main__":
    categories = ['snacks', 'beverages', 'personal care', 'cleaning supplies', 'canned food', 'dry goods']
    for categorie in categories:
        fetch_and_save_items(query=categorie, limit=50)