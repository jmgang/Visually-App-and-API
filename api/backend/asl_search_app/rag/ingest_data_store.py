import requests
from utils import get_gcloud_access_token, load_json_document

access_token = get_gcloud_access_token()

# Set up headers
headers = {
    'Authorization': f'Bearer {access_token}',
    'Content-Type': 'application/json'
}

json_document = load_json_document('preprocessed_startasl.json')

for idx, json_object in enumerate(json_document):
    document_id = f"visually-startasl-json-v2-document-{idx+1}"
    url = (f"https://discoveryengine.googleapis.com/v1beta/projects/axiomatic-treat-421318/locations/global/collections/"
           f"default_collection/dataStores/visually-startasl-json-v2/branches/0/documents?documentId={document_id}")
    response = requests.post(url, headers=headers, json={"structData": json_object})
    print(idx+1, ' ', response)
