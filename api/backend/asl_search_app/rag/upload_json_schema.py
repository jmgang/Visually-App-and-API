import requests
from utils import get_gcloud_access_token

access_token = get_gcloud_access_token()
headers = {
    'Authorization': f'Bearer {access_token}',
    'Content-Type': 'application/json'
}

url = ("https://discoveryengine.googleapis.com/v1beta/projects/axiomatic-treat-421318/locations/global/"
       "collections/default_collection/dataStores/visually-startasl-json-v2/schemas/default_schema")

json_schema = {
  "$schema": "https://json-schema.org/draft/2020-12/schema",
  "type": "object",
  "properties": {
    "url": {
      "type": "string"
    },
    "title": {
      "type": "string"
    },
    "description": {
      "type": "string"
    },
    "video_url": {
      "type": "string"
    }
  }
}

response = requests.patch(url, headers=headers, json={"structSchema": json_schema})
print(response)
