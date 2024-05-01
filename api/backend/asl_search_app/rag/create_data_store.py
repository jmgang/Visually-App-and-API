import requests
from utils import get_gcloud_access_token

# Get the access token
access_token = get_gcloud_access_token()

# Set up headers
headers = {
    'Authorization': f'Bearer {access_token}',
    'Content-Type': 'application/json',
    'X-Goog-User-Project': 'axiomatic-treat-421318'
}

# Define the URL
url = "https://discoveryengine.googleapis.com/v1alpha/projects/axiomatic-treat-421318/locations/global/collections/default_collection/dataStores?dataStoreId=visually-startasl-json-v2"

# Define the data payload
data = {
    "displayName": "VisuAlly ASL Learning JSON v2",
    "industryVertical": "GENERIC",
    "solutionTypes": ["SOLUTION_TYPE_SEARCH"]
}

# Make the POST request
response = requests.post(url, headers=headers, json=data)

# Print the response
print(response.text)
